package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelperAdapterPlayer extends HelperBaseAdapter {
	private SharedPreferences mPreferences;
	HelperSQL sqlHelper=null;
	HelperOnlineGetJSON getJsonHelper=null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	SmartPlayerList playerHelper = null;
	float screenDensity;
	int picSize=0;
	
	String team_id=null;
	String player_id=null;
	String server_player_id=null;
	String server_team_id=null;
	String player_number=null;
	String player_forename=null;
	String player_surename=null;
	String player_position_first=null;
	String player_position_second=null;
	String player_position_third=null;
	ArrayList<String> listPlayerData = new ArrayList<String>();
	FragmentManager fragmentManager;
	double screenInch = 0;
	
	public HelperAdapterPlayer(Context context, Cursor c, String id, FragmentManager contentFragmentManager) {
		super(context, c);
		player_id = id;
		sqlHelper=new HelperSQL(context);
		getJsonHelper = new HelperOnlineGetJSON();
		fctHelper=new HelperFunction();
		screenDensity=fctHelper.getScreenDensity(context);
		lytHelper=new HelperLayout();
		playerHelper= new SmartPlayerList();
		fragmentManager = contentFragmentManager;
		screenInch = fctHelper.getScreenInch(context);
	}

	@Override
	public void bindView(View row, Context ctxt, Cursor c) {
		PlayerServerIDHolder holder=(PlayerServerIDHolder)row.getTag();
		holder.populateFrom(c, sqlHelper);
	}
	
	@Override
	public View newView(Context ctxt, Cursor c, ViewGroup parent) {
		LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.row_player, parent, false);
		PlayerServerIDHolder holder=new PlayerServerIDHolder(row, ctxt);
		row.setTag(holder);
		return(row);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent); 
		setCtxt(parent.getContext());
		Cursor c = (Cursor) getItem(position);
		if (player_id != null){
			if(player_id.equals(sqlHelper.getPlayerID(c))){
				view.setBackgroundColor(getCtxt().getResources().getColor(R.color.green));
			}
		}
		
		// Synchronisationsbutton einrichten
		ImageButton btn_synch = (ImageButton) view.findViewById(R.id.btn_synch);
		btn_synch.setTag(new Long(getItemId(position)));
		
		// Falls Synchro-Button geklickt wird => Mannschaft mit der Server-Mannschaft synchronisieren
		btn_synch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				long id = (Long) v.getTag();
				player_id=String.valueOf(id);
				server_player_id=sqlHelper.getPlayerServerIDByID(player_id);
				team_id=sqlHelper.getPlayerTeamIDByID(player_id);
				server_team_id=sqlHelper.getPlayerServerTeamIDByID(player_id);
				player_number=sqlHelper.getPlayerNumberByID(player_id);
				player_forename=sqlHelper.getPlayerForenameByID(player_id);
				player_surename=sqlHelper.getPlayerSurenameByID(player_id);
				player_position_first=sqlHelper.getPlayerPositionFirstByID(player_id);
				player_position_second=sqlHelper.getPlayerPositionSecondByID(player_id);
				player_position_third=sqlHelper.getPlayerPositionThirdByID(player_id);
				
				mPreferences = getCtxt().getSharedPreferences("CurrentUser", getCtxt().MODE_PRIVATE);
				String auth_token = mPreferences.getString("AuthToken", "");
				
				if (auth_token.equals("")) {
					loginNotPossibleHandler.sendEmptyMessage(0);
				} else {
					setProgressDialog(ProgressDialog.show(getCtxt(), null, getCtxt().getString(R.string.player_sync), true));
					new SinglePlayerSyncTask().execute(v.getContext());
				}
			}
		});
		
		return view;
	}

	final class SinglePlayerSyncTask extends AsyncTask<Context, Void, Void> {
		protected Void doInBackground(final Context... args) {
			if (server_team_id != null) {
				// Wenn Server-ID fehlt => ID vom Server laden und abspeichern
				listPlayerData = getJsonHelper.getPlayerArray(server_team_id, player_forename, player_surename, getCtxt());
				
				if (!listPlayerData.isEmpty()) {
					server_player_id = listPlayerData.get(0);
					server_team_id = listPlayerData.get(1);
					player_number = listPlayerData.get(2);
					player_forename = listPlayerData.get(3);
					player_surename = listPlayerData.get(4);
					player_position_first = listPlayerData.get(5);
					player_position_second = listPlayerData.get(6);
					player_position_third = listPlayerData.get(7);
					
					// Spieler mit Daten vom Server updaten
					sqlHelper.updatePlayer(player_id, server_player_id, team_id, server_team_id, player_number, player_forename, player_surename, player_position_first, player_position_second, player_position_third);
					
					// Benachrichtigung, dass der Spieler synchronisiert wurde
					syncDoneHandler.sendEmptyMessage(0);
				} else {
					// Benachrichtigung, dass der Spieler nicht synchronisiert werden konnte
					syncFailedHandler.sendEmptyMessage(0);
				}
			} else {
				// Benachrichtigung, dass der Spieler nicht synchronisiert werden konnte
				syncFailedHandler.sendEmptyMessage(0);
			}
			
			return null;
		}
	};

	final Handler syncDoneHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			dismissProgressDialog();

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
					if(screenInch > 6) {
						Bundle args = new Bundle();
						args.putString("PlayerID", player_id);
						args.putString("TeamID", team_id);
						dialog.dismiss();
						TabFragPlayerList fragPlayerList = (TabFragPlayerList) fragmentManager.findFragmentById(R.id.frag_player_list);
						fragPlayerList.refreshContent(team_id, player_id, args);
					} else {
						dialog.dismiss();
						Intent i=new Intent(getCtxt(), SmartPlayerList.class);
						i.putExtra("TeamID", team_id);
						getCtxt().startActivity(i);
					}
				}
			});

			dialog.show();
			// Ende Nachrichtenbox
		}
	};
}

class PlayerServerIDHolder {
	private TextView tv_player_number=null;
	private TextView tv_player_name=null;
	private TextView tv_player_position=null;
	private ImageView img_synch=null;
	private ImageButton btn_synch=null;
	Context ctxt=null;
    
	PlayerServerIDHolder(View row, Context context) {
		tv_player_number = (TextView)row.findViewById(R.id.player_number);
		tv_player_name = (TextView)row.findViewById(R.id.player_name);
		tv_player_position = (TextView)row.findViewById(R.id.player_position);
		img_synch = (ImageView)row.findViewById(R.id.logo_synch);
		btn_synch = (ImageButton)row.findViewById(R.id.btn_synch);
		ctxt = context;
	}
    
	void populateFrom(Cursor c, HelperSQL helper) {
		Integer int_synch=0;
		String player_id = helper.getPlayerID(c);
		String player_number=helper.getPlayerNumberByID(player_id);
		String player_name=helper.getPlayerForenameByID(player_id)+" "+helper.getPlayerSurenameByID(player_id);
		String player_position_id=helper.getPlayerPositionFirstByID(player_id);
		String server_player_id=helper.getPlayerServerIDByID(player_id);
		
		// Überprüfen, ob Spieler synchron mit dem Server ist
		if(server_player_id != null){
			int_synch=int_synch+1;
		}
		
		// Synchronisationsmarke und Text setzen
		if(int_synch.equals(0)) {
			img_synch.setImageResource(R.drawable.cloud_red);
			btn_synch.setBackgroundResource(R.drawable.cloud_sync);
		}
		if(int_synch.equals(1)) {
			img_synch.setImageResource(R.drawable.cloud_green);
			btn_synch.setBackgroundResource(0);
		}
		
		// Texte setzen
		tv_player_number.setText(player_number);
		tv_player_name.setText(player_name);
		tv_player_position.setText(helper.getPlayerPositionNameByID(player_position_id, ctxt));
	}
}
