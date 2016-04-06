package de.fivestrikes.fs2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class TabFragStatList extends ListFragment {
	
	Cursor model=null;
	HelperAdapterGame adapter=null;
	HelperSQL helper=null;
	String game_id = null;
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		       
/* Datenbank laden */
        
		helper=new HelperSQL(getActivity());
		refreshContent(game_id);
		
		view = inflater.inflate(R.layout.list, container, false);
		
		return view;
	
	}

/*
 * 
 * Inhalt neu laden, wenn Activity ernuet aufgerufen wird 
 *
 */
	
	@Override
	public void onResume() {
		
		super.onResume();  // Always call the superclass method first
		
	    	Bundle args = getArguments();
		if(args!=null){
			game_id = args.getString("GameID");
			args = null;
		}
		
		refreshContent(game_id);
    	
	}
    
	public void refreshContent(String game_id) {
		
/* Spielliste einrichten */
		model=helper.getAllGameCursor();
		getActivity().startManagingCursor(model);
		adapter = new HelperAdapterGame(getActivity(), model, game_id);
		setListAdapter(adapter);  
        
	}
    
/*
 * 
 * Spielauswahl 
 *
 */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		helper=new HelperSQL(getActivity());
		game_id = String.valueOf(id);
		
		Bundle args = new Bundle();
		args.putString("GameID", game_id);

		Intent i = new Intent(getActivity().getApplicationContext(), TabStatOverviewActivity.class);
		i.putExtras(args);
		startActivity(i);
        
	}
}
