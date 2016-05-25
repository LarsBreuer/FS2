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

public class TabFragStatTickerEvent extends ListFragment {
	
	HelperSQL sqlHelper=null;
	View view;
	Bundle args;
	Cursor model=null;
	FragmentManager fragmentManager;
	HelperAdapterStatGameTicker adapter=null;
	String strInput = null;
	Integer ticker_activity_id = null;
	String game_id;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false);
		
/* Daten aus Activity laden */ 
	        
		args = getArguments();
		game_id = args.getString("GameID");
		
/* Layout einrichten */
		
		refreshContent(game_id);
		
		return view;
	
	}
	
	public void refreshContent(String game_id) {
		
		sqlHelper = new HelperSQL(getActivity());
		model = sqlHelper.getAllGameTickerEvent(game_id);
		getActivity().startManagingCursor(model);
		adapter = new HelperAdapterStatGameTicker(getActivity(), model, game_id);
		setListAdapter(adapter);
		
	}
	
/*
 * 
 * Tickerauswahl 
 *
 */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		int ticker_event_id = (int) (long) id;
		String strInput = "StatEventText";
		
		args.putString("InputString", strInput);
		args.putInt("TickerEventID", ticker_event_id);
		
		fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		TabFragTickerText fragment = new TabFragTickerText();
		fragment.setArguments(args);
		fragmentTransaction.replace(R.id.frag_stat_ticker_content_2, fragment);
		fragmentTransaction.commit();
		
	}
}
