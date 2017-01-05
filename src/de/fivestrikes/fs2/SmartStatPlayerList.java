package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class SmartStatPlayerList extends ListActivity {

	Bundle args;
	HelperLayout lytHelper = null;
	HelperSQL sqlHelper = null;
	View view;
	Cursor c = null;
	String team_id, game_id, player_id;
	ArrayList<String> player = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
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

	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {

		player_id = player.get(position);
		args.putString("PlayerID", player_id);

		Intent i = new Intent(SmartStatPlayerList.this, SmartStatPlayerFirst.class);
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
			setProgressDialog(ProgressDialog.show(SmartStatPlayerList.this, null, getString(R.string.in_progress), true));
		}
		new LoadingTask().execute();
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
			uiHandler.post(new Runnable() {
				@Override
				public void run() {
					/* Helper generieren */

					lytHelper = new HelperLayout();
					sqlHelper = new HelperSQL(SmartStatPlayerList.this);

					/* Daten aus Activity laden */

					team_id = SmartStatPlayerList.this.args.getString("TeamID");
					game_id = SmartStatPlayerList.this.args.getString("GameID");

					/* Spielerliste definieren und einrichten */

					// Nur Spieler einbinden, die in dem Spiel auch tatsächlich gespielt
					// haben
					c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
					for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

						player_id = sqlHelper.getPlayerID(c);
						if (sqlHelper.count_ticker_activity(game_id, null, player_id, null, null, null) > 0) {
							player.add(player_id);
						}

					}
					
					HelperAdapterStatPlayerList adapter = new HelperAdapterStatPlayerList(SmartStatPlayerList.this, player, null);
					setListAdapter(adapter);
					
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