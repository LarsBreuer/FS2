package de.fivestrikes.fs2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class TabFragTeamOnline extends ListFragment {

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

/** TODO -0- => ProgressDialog bzw. Animated Gif mit Ladesymbol einbauen - siehe meine Beschreibung  in der ToDo Handball App */
				
				getJsonHelper = new HelperOnlineGetJSON();
				edit_team_search = (EditText) view.findViewById(R.id.team_search);
				strTeamName = edit_team_search.getText().toString();
				FragmentManager fragmentManager = getFragmentManager();
				getJsonHelper.synchTeam(strTeamName, context, null, fragmentManager, args);
			
			}
		});
		
		return view;
		
	}
	
	@Override
	public void onResume() {

		super.onResume();
	    	
	}
}