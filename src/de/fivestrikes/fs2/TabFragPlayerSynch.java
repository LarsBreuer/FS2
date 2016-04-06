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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class TabFragPlayerSynch extends ListFragment {

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
		
		view = inflater.inflate(R.layout.list_with_text, container, false);
		context = view.getContext();
		
/* Daten aus Activity laden */
		
		args = getArguments();
		
/* Helper definieren */
		
		getJsonHelper = new HelperOnlineGetJSON();
		
/* Layoutfelder definieren und Inhalte setzen */
		
		text = (TextView) view.findViewById(R.id.text);
		text.setText(getResources().getString(R.string.text_player_search_explain));
		LinearLayout lyt_button_all = (LinearLayout) view.findViewById(R.id.lyt_button_all);
		lyt_button_all.removeAllViews();
		FragmentManager fragmentManager = getFragmentManager();
		getJsonHelper.synchPlayer(null, context, fragmentManager, args);
		
		return view;
		
	}
	
	@Override
	public void onResume() {

		super.onResume();
	    	
	}
}