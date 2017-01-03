package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;;

public class SmartStatPlayerFirst extends Activity {

	Bundle args;
	HelperLayout lytHelper = null;
	View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.stat_player_overview);
		view = findViewById(android.R.id.content);
		
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
	
	private ProgressDialog progressDialog;
	private Handler uiHandler = new Handler();
	private void startTask() {
		args = getIntent().getExtras();
		if (getProgressDialog() == null) {
			uiHandler.post(new Runnable() {
				@Override
				public void run() {
					setProgressDialog(ProgressDialog.show(SmartStatPlayerFirst.this, null, getString(R.string.in_progress), true));
				}
			});
		}
		new LoadingTask().execute(getIntent().getExtras());
	}
	
	final class LoadingTask extends AsyncTask<Bundle, Void, Void> {
		protected Void doInBackground(final Bundle... args) {

			if (args == null) {
				return null;
			}
			
			Looper.prepare();
			
			/* Helper generieren */
			
			lytHelper = new HelperLayout();

			/* Layout festlegen */
			
			// Integer home_or_away = args[0].getInt("HomeOrAway");
			lytHelper.lytStatPlayerOverview(SmartStatPlayerFirst.this.args, view, null);
			
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
