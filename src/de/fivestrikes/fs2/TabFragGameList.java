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

public class TabFragGameList extends ListFragment {
	
	Cursor model=null;
	HelperAdapterGame adapter=null;
	HelperSQL helper=null;
	String game_id = null;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		       
/* Datenbank laden */
        
		helper=new HelperSQL(getActivity());
		refreshContent(game_id);
		
		return inflater.inflate(R.layout.list, container, false);
	
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
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		TabFragGameEdit fragment = new TabFragGameEdit();
		fragment.setArguments(args);
		refreshContent(game_id);
		fragmentTransaction.replace(R.id.frag_game_edit, fragment);
		fragmentTransaction.commit();
        
	}
}
