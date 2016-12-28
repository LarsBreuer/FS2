package de.fivestrikes.fs2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragStatPlayerOverview extends Fragment {
	
	HelperLayout lytHelper = null;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.stat_player_overview, container, false);
		
/* Helper generieren */
        
		lytHelper = new HelperLayout();

/* Daten aus Activity laden */ 
        
		Bundle args = getArguments();
		
/* Layout festlegen */
		
		FragmentManager fragmentManager = getFragmentManager();
		lytHelper.lytStatPlayerOverview(args, view, fragmentManager);

		return view;
	}
}