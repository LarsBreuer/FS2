package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SmartTeamOnline extends ListActivity {
	private ProgressDialog progressDialog;
	private static Context context;
	String strTeamName=null;
	String team_type_name=null;
	String url=null;
	String server_team_id=null;
	String server_club_id=null;
	String team_type_id=null;
	String club_name=null;
	String club_name_short=null;
	String club_id=null;
	String team_id=null;
	TextView text = null;
	HelperOnlineGetJSON getJsonHelper=null;
	EditText edit_team_search = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_online);
		context = this;
		
/* Helper definieren */
		
		getJsonHelper = new HelperOnlineGetJSON();
		
/* Daten aus Activity laden */ 
	        
		team_id=getIntent().getStringExtra("TeamID");
		
/* Layoutfelder definieren und Inhalte setzen */
		
		TextView headline = (TextView) findViewById(R.id.headline);
		text = (TextView) findViewById(R.id.text);
		headline.setText(getResources().getString(R.string.text_team_search)); 
		text.setText(getResources().getString(R.string.text_team_search_explain)); 
		Button btn_team_search=(Button) findViewById(R.id.btn_team_search);
		
		// Suche nach Mannschaftsnamen auf dem Server
		btn_team_search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				searchTeams();
			}
		});
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
	}
	
	public void searchTeams() {
		edit_team_search = (EditText) findViewById(R.id.team_search);
		strTeamName = edit_team_search.getText().toString();
		setProgressDialog(ProgressDialog.show(this, null, getString(R.string.team_search), true));
		new SearchTeamsTask().execute();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		dismissProgressDialog();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		dismissProgressDialog();
	}
	
	final class SearchTeamsTask extends AsyncTask<Context, Void, Void> {
		protected Void doInBackground(final Context... cargs) {
			getJsonHelper.synchTeam(strTeamName, context, team_id, null, null);
			syncDoneHandler.sendEmptyMessage(0);
			return null;
		}
	};
	
	final Handler syncDoneHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			dismissProgressDialog();
		}
	};
	
/* Action Bar einrichten */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
        
			case android.R.id.home: 
				onBackPressed();
				break;

			default:
				return super.onOptionsItemSelected(item);
      	  	
		}
        
		return true;
        
	}
	
	private void CreateMenu(Menu menu) {	
	}

	public void dismissProgressDialog() {
		if (getProgressDialog() != null && getProgressDialog().isShowing()) {
			getProgressDialog().dismiss();
		}
	}

	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}
}
