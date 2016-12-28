package de.fivestrikes.fs2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class TabFragStatPlayerStat extends ListFragment {
	
	HelperLayout lytHelper = null;
	Bundle args;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
		View view = inflater.inflate(R.layout.stat_player_stat, container, false);
		
/* Helper generieren */
        
		lytHelper = new HelperLayout();

/* Daten aus Activity laden */ 
        
		args = getArguments();
		
/* Layout festlegen */
		
		FragmentManager fragmentManager = getFragmentManager();
		lytHelper.lytStatPlayerStat(args, view, fragmentManager, null);

		return view;
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
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		TabFragStatPlayerTicker fragStat = new TabFragStatPlayerTicker();
		fragStat.setArguments(args);
		fragmentTransaction.replace(R.id.frag_stat_player_content_2, fragStat);
		fragmentTransaction.commit();
		
	}
}