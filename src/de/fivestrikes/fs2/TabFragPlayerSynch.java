package de.fivestrikes.fs2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Context;

public class TabFragPlayerSynch extends ListFragment {
	private ProgressDialog progressDialog;
	private static Context context;
	String strTeamName=null;
	HelperOnlineGetJSON getJsonHelper=null;
	TextView headline = null;
	TextView text = null;
	View view = null;
	EditText edit_team_search = null;
	Bundle args = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list_with_text, container, false);
		context = view.getContext();
		
/* Daten aus Activity laden */
		
		args = getArguments();
		
/* Helper definieren */
		
		getJsonHelper = new HelperOnlineGetJSON();
		
/* Layoutfelder definieren und Inhalte setzen */
		
		headline = (TextView) view.findViewById(R.id.headline);
		headline.setText(getResources().getString(R.string.player_synchro));
		text = (TextView) view.findViewById(R.id.text);
		text.setText(getResources().getString(R.string.text_player_search_explain));
		LinearLayout lyt_button = (LinearLayout) view.findViewById(R.id.lyt_button);
		lyt_button.removeAllViews();
		LinearLayout lyt_button_all = (LinearLayout) view.findViewById(R.id.lyt_button_all);
		lyt_button_all.removeAllViews();
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setProgressDialog(ProgressDialog.show(getActivity(), null, getString(R.string.player_sync), true));
		new PlayerSyncTask().execute();
	}
	
	final class PlayerSyncTask extends AsyncTask<Context, Void, Void> {
		protected Void doInBackground(final Context... cargs) {
			FragmentManager fragmentManager = getFragmentManager();
			getJsonHelper.synchPlayer(null, context, fragmentManager, args);
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
