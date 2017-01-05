package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SmartStatPlayerStat extends ListActivity {
	
	Bundle args;
	HelperLayout lytHelper = null;
	View view;
	SmartStatPlayerStat activity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.stat_player_stat);
		view = findViewById(android.R.id.content);
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		startTask();
	}
	
/*
 * 
 * Statistikauswahl
 *
*/
	
	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {
	
		TextView labelTextView = (TextView) v.findViewById(R.id.stat_player_label);
		String statLabel = labelTextView.getText().toString();
		args.putString("Stat", statLabel);
		
		Intent i=new Intent(SmartStatPlayerStat.this, SmartStatPlayerTicker.class);
		i.putExtras(args);
		startActivity(i);
		
	}
	
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
	
	/// Async Task + Progress Dialog
	
	private void startTask() {
		if (getProgressDialog() == null) {
			setProgressDialog(ProgressDialog.show(SmartStatPlayerStat.this, null, getString(R.string.in_progress), true));
		}
		new LoadingTask().execute(args);
	}
	
	private ProgressDialog progressDialog;
	final Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			dismissProgressDialog();
		}
	};
	
	final class LoadingTask extends AsyncTask<Bundle, Void, Void> {
		protected Void doInBackground(final Bundle... args) {
			if (args == null) {
				return null;
			}
			
			uiHandler.post(new Runnable() {
				@Override
				public void run() {
					/* Helper generieren */
					
					lytHelper = new HelperLayout();
					
					/* Layout festlegen */
					
					lytHelper.lytStatPlayerStat(SmartStatPlayerStat.this.args, view, null, activity);
					
					dismissProgressDialog();
				}
			});
			
			return null;
		}
	};

	public void dismissProgressDialog() {
		if (getProgressDialog() != null && getProgressDialog().isShowing()) {
			uiHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					getProgressDialog().dismiss();
					setProgressDialog(null);
				}
			}, 500);
		}
	}

	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}
	
	///
}
