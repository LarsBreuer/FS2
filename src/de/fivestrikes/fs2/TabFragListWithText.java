package de.fivestrikes.fs2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.ListView;

public class TabFragListWithText extends ListFragment {
	
	Bundle args;
	HelperLayout lytHelper = null;
	View view;
	FragmentManager fragmentManager;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

/* Grundlayout setzen */

		view = inflater.inflate(R.layout.list_with_text, container, false);
		
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		args = getArguments();
		
/* Layout festlegen */
		
		fragmentManager = getFragmentManager();
		lytHelper.lytListWithText(args, view, fragmentManager, null);
		
		return view;
	
	}

	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		lytHelper.lytListWithTextClick(args, view, fragmentManager, position);
        
	}
}
