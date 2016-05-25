package de.fivestrikes.fs2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ListView;

public class TabFragStatTickerActivity extends ListFragment {
	
	HelperLayout lytHelper = null;
	View view;
	Bundle args;
	FragmentManager fragmentManager;
	String strInput = null;
	Integer ticker_activity_id = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list_with_button, container, false);
		       
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		args = getArguments();
		
/* Layout einrichten */
		
		refreshContent(args);
		
		return view;
	
	}
	
	public void refreshContent(Bundle contentArgs) {
		
		fragmentManager = getFragmentManager();
		lytHelper.lytStatTickerActicity(contentArgs, view, fragmentManager, null);
		
	}
	
/*
 * 
 * Tickerauswahl 
 *
 */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		ticker_activity_id = (int) (long) id;
		strInput = "StatActivityText";
		
		args.putString("InputString", strInput);
		args.putInt("TickerActivityID", ticker_activity_id);
		
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		TabFragTickerText fragment = new TabFragTickerText();
		fragment.setArguments(args);
		fragmentTransaction.replace(R.id.frag_stat_ticker_content_2, fragment);
		fragmentTransaction.commit();
		
	}
}
