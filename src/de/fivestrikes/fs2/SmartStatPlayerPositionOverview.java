package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SmartStatPlayerPositionOverview extends Activity {

	Bundle args;
	HelperLayout lytHelper = null;
	View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.stat_position_overview);
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
			uiHandler.post(new Runnable() {
				@Override
				public void run() {
					setProgressDialog(ProgressDialog.show(SmartStatPlayerPositionOverview.this, null, getString(R.string.in_progress), true));
				}
			});
		}
		new LoadingTask().execute(args);
	}
	
	private ProgressDialog progressDialog;
	private Handler uiHandler = new Handler();
	
	final class LoadingTask extends AsyncTask<Bundle, Void, Void> {
		protected Void doInBackground(final Bundle... args) {
			if (args == null) {
				return null;
			}
			
			/* Helper generieren */
			
			lytHelper = new HelperLayout();

			/* Layout festlegen */
			
			lytHelper.lytStatPlayerPositionOverview(SmartStatPlayerPositionOverview.this.args, view, null);
			
			uiHandler.post(new Runnable() {
				@Override
				public void run() {
					dismissProgressDialog();
				}
			});
			
			return null;
		}
	};

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
	
	///
}
