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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

public class TabFragPlayerMerge extends ListFragment {
	
	String player_id = null;
	String server_player_id=null;
	String team_id=null;
	String server_team_id=null;
	String player_number=null;
	String player_forename=null;
	String player_surename=null;
	String player_position_first=null;
	String player_position_second=null;
	String player_position_third=null;
	TextView text = null;
	HelperSQL sqlHelper=null;
	Cursor model=null;
	HelperAdapterPlayerServerID adapter=null;
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list_with_text, container, false);
		       
/* Helper generieren */
		
		sqlHelper=new HelperSQL(getActivity());
		
/* Layoutfelder definieren und Inhalte setzen */
		
		text = (TextView) view.findViewById(R.id.text);
		text.setText(getResources().getString(R.string.text_player_merge_explain));
		LinearLayout lyt_button_all = (LinearLayout) view.findViewById(R.id.lyt_button_all);
		lyt_button_all.removeAllViews();
		
/* Daten aus Activity laden */ 
	        
		Bundle args = getArguments();
		if (args != null) {
			
			player_id = args.getString("PlayerID");
			server_player_id = args.getString("ServerPlayerID");
			team_id = args.getString("TeamID");
			server_team_id = args.getString("ServerTeamID");
			player_number = args.getString("PlayerNumber");
			player_forename = args.getString("PlayerForename");
			player_surename = args.getString("PlayerSurename");
			player_position_first = args.getString("PlayerPositionFirst");
			player_position_second = args.getString("PlayerPositionSecond");
			player_position_third = args.getString("PlayerPositionThird");

		}

/* Teamliste einrichten */
		
		model=sqlHelper.getAllPlayerCursorServerPlayerID();
		getActivity().startManagingCursor(model);
		adapter=new HelperAdapterPlayerServerID(getActivity(), model);
		setListAdapter(adapter);	
		
		return inflater.inflate(R.layout.list, container, false);
	
	}
    
/*
 * 
 * Mannschaftauswahl 
 *
 */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		// Mannschaftsdaten ermitteln
		String connect_player_id = String.valueOf(id);
		
		
		
		
		// Überprüfen, ob die beiden Spieler schon einmal in einem gemeinsamen Spiel eingesetzt wurden
		/** TODO -2- => Überprüfen, ob die beiden Spieler schon einmal in einem gemeinsamen Spiel eingesetzt wurden */
				
				// Falls nicht => Alle Spielaktionen mit dem Spieler ändern...
		/** TODO -2- => Die Player Server ID auch in den Spielaktionen eintragen */
				
				// ... und den Spieler löschen
		/** TODO -2- => sqlHelper.deletePlayer(player_id);	 */	
				
		Toast.makeText(getActivity(), getString(R.string.text_player_connect_suceeded), Toast.LENGTH_LONG).show();
		
		Bundle args = new Bundle();
		args.putString("PlayerID", player_id);
		args.putString("ServerPlayerID", server_player_id);
		args.putString("TeamID", team_id);
		args.putString("ServerTeamID", server_team_id);
		args.putString("PlayerNumber", player_number);
		args.putString("PlayerForename", player_forename);
		args.putString("PlayerSurename", player_surename);
		args.putString("PlayerPositionFirst", player_position_first);
		args.putString("PlayerPositionSecond", player_position_second);
		args.putString("PlayerPositionSecond", player_position_third);
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		TabFragPlayerEdit fragment = new TabFragPlayerEdit();
		fragment.setArguments(args);
		fragmentTransaction.replace(R.id.frag_player_edit, fragment);
		fragmentTransaction.commit();
        
	}
}
