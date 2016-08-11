package de.fivestrikes.fs2;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelperAdapterClub extends HelperBaseAdapter {
	HelperSQL sqlHelper=null;
	HelperOnlineGetJSON getJsonHelper=null;
	HelperLayout lytHelper = null;
	HelperFunction fctHelper = null;
	SmartClubChoose clubHelper = null;
	float screenDensity;
	int picSize=0;
	String club_id=null;
	String server_club_id=null;
	ArrayList<String> listClubData = new ArrayList<String>();

	public HelperAdapterClub(Context context, Cursor c) {
		super(context, c);
		sqlHelper=new HelperSQL(context);
		clubHelper= new SmartClubChoose();
		lytHelper=new HelperLayout();
		getJsonHelper = new HelperOnlineGetJSON();
		fctHelper=new HelperFunction();
		screenDensity=fctHelper.getScreenDensity(context);
	}

	@Override
	public void bindView(View row, Context ctxt,
			Cursor c) {
		
		ClubHolder holder=(ClubHolder)row.getTag();
		holder.populateFrom(c, sqlHelper);
	}
	
	@Override
	public View newView(Context ctxt, Cursor c,
			ViewGroup parent) {
		
		LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.row_club, parent, false);
		ClubHolder holder=new ClubHolder(row);
		row.setTag(holder);
		return row;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);  
		setCtxt(parent.getContext());
		
		// Synchronisationsbutton einrichten
		Button btn_synch=(Button) view.findViewById(R.id.btn_synch);
		
		// Falls Synchro-Button geklickt wird => Mannschaft mit der Server-Mannschaft synchronisieren
		btn_synch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				long id = (Long) v.getTag();
				club_id=String.valueOf(id);
				server_club_id=sqlHelper.getClubServerIDByID(club_id);
				
				if (server_club_id == null) {
					setProgressDialog(ProgressDialog.show(v.getContext(), null, v.getContext().getString(R.string.club_sync), true));
					new SingleClubSyncTask().execute(v.getContext());
				}
			}
		});
				
		return view;
	}

	final class SingleClubSyncTask extends AsyncTask<Context, Void, Void> {
		protected Void doInBackground(final Context... args) {
			// Wenn Server-ID fehlt => ID vom Server laden und abspeichern
			listClubData = getJsonHelper.getClubArray(null, sqlHelper.getClubNameByID(club_id), args[0]);
			if (!listClubData.isEmpty()) {
				server_club_id = listClubData.get(0);
				syncDoneHandler.sendEmptyMessage(0);
			}
			return null;
		}
	}
	
	final Handler syncDoneHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			dismissProgressDialog();
			clubHelper.refreshContent();
		}
	};
}

class ClubHolder {
	private TextView club=null;
    
	ClubHolder(View row) {
		club = (TextView)row.findViewById(R.id.rowClubName);
	}
    
	void populateFrom(Cursor c, HelperSQL helper) {
		String club_name=helper.getClubName(c);
		club.setText(club_name);
	}
}
