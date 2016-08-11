package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelperAdapterTeam extends HelperBaseAdapter {
	private SharedPreferences mPreferences;
	HelperSQL sqlHelper=null;
	HelperOnlineGetJSON getJsonHelper=null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	float screenDensity;
	int picSize=0;
	String team_id=null;
	String server_team_id=null;	
	String club_id=null;
	String server_club_id=null;
	String team_type_id=null;
	ArrayList<String> listClubData = new ArrayList<String>();
	ArrayList<String> listTeamData = new ArrayList<String>();

	public HelperAdapterTeam(Context context, Cursor c, String id) {
		super(context, c);
		team_id = id;
		sqlHelper=new HelperSQL(context);
		getJsonHelper = new HelperOnlineGetJSON();
		fctHelper=new HelperFunction();
		screenDensity=fctHelper.getScreenDensity(context);
		lytHelper=new HelperLayout();
	}

	@Override
	public void bindView(View row, Context ctxt,
			Cursor c) {
		
		TeamHolder holder=(TeamHolder)row.getTag();
		holder.populateFrom(c, sqlHelper);
	}
	
	@Override
	public View newView(Context ctxt, Cursor c, ViewGroup parent) {
		
		LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.row_team, parent, false);
		TeamHolder holder=new TeamHolder(row, ctxt);
		row.setTag(holder);
		return(row);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);  
		setCtxt(parent.getContext());
		Cursor c = (Cursor) getItem(position);
		if(team_id != null){
			if(team_id.equals(sqlHelper.getTeamID(c))){
				view.setBackgroundColor(getCtxt().getResources().getColor(R.color.green));
			} 
		}
		
		// Synchronisationsbutton einrichten
		Button btn_synch=(Button) view.findViewById(R.id.btn_synch);
		btn_synch.setTag(new Long(getItemId(position)));
		
		// Falls Synchro-Button geklickt wird => Mannschaft mit der Server-Mannschaft synchronisieren
		btn_synch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				long id = (Long) v.getTag();
				team_id = String.valueOf(id);
				server_team_id = sqlHelper.getTeamServerTeamID(team_id);
				club_id = sqlHelper.getTeamClubIDByTeamID(team_id);
				server_club_id = sqlHelper.getTeamServerClubID(team_id);
				team_type_id = sqlHelper.getTeamTypeID(team_id);
				// Zunächst überprüfen, ob Nutzer eingeloggt ist
				mPreferences = getCtxt().getSharedPreferences("CurrentUser", getCtxt().MODE_PRIVATE);
				String auth_token = mPreferences.getString("AuthToken", "");
				
				if (auth_token.equals("")) {
					loginNotPossibleHandler.sendEmptyMessage(0);
				} else {
					setProgressDialog(ProgressDialog.show(getCtxt(), null, getCtxt().getString(R.string.team_sync), true));
					new SingleTeamSyncTask().execute();
				}
			}
		});
		
		return view;
	}
	
	final class SingleTeamSyncTask extends AsyncTask<Context, Void, Void> {
		protected Void doInBackground(final Context... args) {
			if (server_club_id == null) {
				// Wenn Server-ID fehlt => ID vom Server laden und abspeichern
				listClubData = getJsonHelper.getClubArray(null, sqlHelper.getClubNameByTeamID(team_id), getCtxt());
				
				if (!listClubData.isEmpty()) {
					server_club_id = listClubData.get(0);
					sqlHelper.updateTeam(team_id, server_team_id, club_id, server_club_id, team_type_id);
				}
			}
			
			if (server_club_id != null) {
				if (server_team_id == null) {
					// Wenn die Server-ID der Mannschaft fehlt => ID vom Server laden und abspeichern
					listTeamData = getJsonHelper.getTeamArray(server_club_id, team_type_id, getCtxt());
					
					if (!listTeamData.isEmpty()) {
						server_team_id = listTeamData.get(0);
						sqlHelper.updateTeam(team_id, server_team_id, club_id, server_club_id, team_type_id);
					}
				}
			}
			
			if(server_club_id != null && server_team_id != null) {
				// Spieler der Mannschaft vom Server übertragen
				getJsonHelper.loadPlayerFromServer(team_id, server_team_id, getCtxt());
				syncDoneHandler.sendEmptyMessage(0);
			} else {
				syncFailedHandler.sendEmptyMessage(0);
			}
			return null;
		}
	}

	final Handler syncDoneHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			dismissProgressDialog();
			
			// Benachrichtigung, dass die Mannschaft synchronisiert wurde
			// Nachrichtenbox einrichten
			final Dialog dialog = new Dialog(getCtxt());
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_dialog);

			// Texte setzen
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			title.setText(R.string.synchro);
			text.setText(R.string.text_synchro_succeeded);
			
			// Button definieren
			LinearLayout lyt_button2 = (LinearLayout) dialog.findViewById(R.id.lyt_button2);
			lyt_button2.removeAllViews();
			LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
			lyt_button3.removeAllViews();
			
			Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
			dialogButton1.setText(R.string.okay);
			
			dialogButton1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					getCtxt().startActivity(new Intent(getCtxt(), SmartTeamList.class));
				}
			});

			dialog.show();
			// Ende Nachrichtenbox
		}
	};
}

class TeamHolder {
	private TextView club=null;
	private TextView team=null;
	private ImageView img_synch=null;
	Context ctxt=null;
    
	TeamHolder(View row, Context context) {
		club = (TextView)row.findViewById(R.id.rowClubName);
		team = (TextView)row.findViewById(R.id.rowTeamType);
		img_synch = (ImageView)row.findViewById(R.id.logo_synch);
		ctxt = context;
	}
    
	void populateFrom(Cursor c, HelperSQL helper) {
		Integer int_synch=0;
		String team_id = helper.getTeamID(c);
		String club_name=helper.getClubNameByTeamID(team_id);
		String club_name_short=helper.getClubShortByTeamID(team_id);
		String team_type_name=helper.getTeamTypeNameByTeamID(team_id, ctxt);
		
		String server_club_id=null;
		String server_team_id=null;
		if(helper.getTeamServerClubID(team_id)!=null){
			server_club_id=helper.getTeamServerClubID(team_id);
		} else {
			server_club_id="N.N.";
		}
		if(helper.getTeamServerTeamID(team_id)!=null){
			server_team_id=helper.getTeamServerTeamID(team_id);
		} else {
			server_team_id="N.N.";
		}
		
		// Überprüfen, ob Verein und Mannschaft synchron mit dem Server sind
		if (helper.getTeamServerClubID(team_id) != null && helper.getTeamServerTeamID(team_id) != null){
			int_synch=int_synch+1;
		}
		
		// Überprüfen, ob alle Spieler der Mannschaft synchronisiert sind
		if (helper.countTeamPlayerNotSynch(team_id) == 0 && helper.countPlayerOfTeam(team_id) > 0) {
			int_synch=int_synch+1;
		}
		
		// Synchronisationsmarke und Text setzen
		
		if (int_synch.equals(0)) {
			img_synch.setImageResource(R.drawable.cloud_red);
		}
		if (int_synch.equals(1)) {
			img_synch.setImageResource(R.drawable.cloud_yellow);
		}
		if (int_synch.equals(2)) {
			img_synch.setImageResource(R.drawable.cloud_green);
		}
		
		club.setText(club_name);
		team.setText(team_type_name);
	}
}
