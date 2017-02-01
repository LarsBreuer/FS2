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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class TabFragTeamOnline extends ListFragment {
	private ProgressDialog progressDialog;
	private static Context context;
	String strTeamName=null;
	HelperOnlineGetJSON getJsonHelper=null;
	TextView text = null;
	View view = null;
	EditText edit_team_search = null;
	Bundle args = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.team_online, container, false);
		context = view.getContext();
		
/* Daten aus Activity laden */
		
		args = getArguments();
		
/* Layoutfelder definieren und Inhalte setzen */
		
		TextView headline = (TextView) view.findViewById(R.id.headline);
		text = (TextView) view.findViewById(R.id.text);
		headline.setText(getResources().getString(R.string.text_team_search)); 
		text.setText(getResources().getString(R.string.text_team_search_explain)); 
		Button btn_team_search=(Button) view.findViewById(R.id.btn_team_search);
		
		// Suche nach Mannschaftsnamen auf dem Server
		btn_team_search.setOnClickListener(new View.OnClickListener() {
			@Override
            public void onClick(View v) {
				InputMethodManager inputManager = (InputMethodManager) ((Activity)context).getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(((Activity)context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				searchTeams();
			}
		});
		
		return view;
	}
	
	public void searchTeams() {
		getJsonHelper = new HelperOnlineGetJSON();
		edit_team_search = (EditText) view.findViewById(R.id.team_search);
		strTeamName = edit_team_search.getText().toString();
		setProgressDialog(ProgressDialog.show(getActivity(), null, getString(R.string.team_search), true));
		new SearchTeamsTask().execute();
	}
	
	final class SearchTeamsTask extends AsyncTask<Context, Void, Void> {
		protected Void doInBackground(final Context... cargs) {
			FragmentManager fragmentManager = getFragmentManager();
			getJsonHelper.synchTeam(strTeamName, context, null, fragmentManager, args);
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