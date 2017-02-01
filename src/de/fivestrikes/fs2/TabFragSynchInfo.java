package de.fivestrikes.fs2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

public class TabFragSynchInfo extends Fragment {
	
	private View view;
	HelperLayout lytHelper = null;
	Cursor c = null;
	String overview_text = null;
	String strActivity = null;
	String team_id = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
/* Grundlayout setzen */

		view = inflater.inflate(R.layout.synch_info, container, false);
		
/* Helper generieren */

		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		Bundle args = getArguments();
		if (args != null) {
			
			strActivity = args.getString("Activity");
			team_id = args.getString("TeamID");
			
		}
		
/* Layout festlegen */
		
		FragmentManager fragmentManager = getFragmentManager();
		lytHelper.lytSynchInfo(args, view, fragmentManager);
		
		return view;
	}
}