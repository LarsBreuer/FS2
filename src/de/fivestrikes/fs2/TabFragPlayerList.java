package de.fivestrikes.fs2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;

public class TabFragPlayerList extends ListFragment {
	
	Cursor model=null;
	HelperAdapterPlayer adapter=null;
	HelperSQL sqlHelper=null;
	String team_id = null;
	String server_team_id = null;
	String player_id = null;
	String server_player_id = null;
	Bundle args = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		      
/* Daten aus Activity laden */ 
	        
		args = getArguments();
		if (args != null && args.getString("TeamID") != null){
			
			team_id = args.getString("TeamID");
			player_id = args.getString("PlayerID");
			
		} 
		
/* Helper generieren */
        
		sqlHelper=new HelperSQL(getActivity());
		
/* Daten aus Datenbank laden */ 
		
		if (team_id != null) {server_team_id = sqlHelper.getTeamServerTeamID(team_id);}
		
/* Teamliste einrichten */
		
		refreshContent(team_id, player_id, args);	
		
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
			team_id = args.getString("TeamID");
			player_id = args.getString("PlayerID");
		}
		refreshContent(team_id, player_id, args);
    	
	}
    
	public void refreshContent(String team_id, String player_id, Bundle args) {
    	
		if (team_id != null) {
		
			sqlHelper=new HelperSQL(getActivity());
			model=sqlHelper.getAllPlayerCursorByTeamID(team_id);
			getActivity().startManagingCursor(model);
			adapter=new HelperAdapterPlayer(getActivity(), model, player_id);
			setListAdapter(adapter);  
			
		}
		
		if (player_id != null) {
			
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			TabFragPlayerEdit fragment = new TabFragPlayerEdit();
			fragment.setArguments(args);
			fragmentTransaction.replace(R.id.frag_player_edit, fragment);
			fragmentTransaction.commit();
			
		}
	}
    
/*
 * 
 * Mannschaftauswahl 
 *
 */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		sqlHelper=new HelperSQL(getActivity());
		player_id = String.valueOf(id);
		server_player_id = sqlHelper.getPlayerServerIDByID(player_id);
		args = new Bundle();
		args.putString("PlayerID", player_id);
		
		// Werte übermitteln
		if(server_player_id!=null) {
			
			// Benachrichtigen, dass lokale Änderungen nicht auf dem Server abgespeichert werden
			// DialogBox einrichten
			final Dialog dialog = new Dialog(getActivity());
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_dialog);

			// Texte setzen
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			title.setText(R.string.synchro);
			text.setText(R.string.text_player_edit);
			
			// Button definieren
			LinearLayout lyt_button2 = (LinearLayout) dialog.findViewById(R.id.lyt_button2);
			lyt_button2.removeAllViews();
			LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
			lyt_button3.removeAllViews();
			
			Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
			dialogButton1.setText(R.string.okay);
			
			dialogButton1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					refreshContent(team_id, player_id, args);
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragPlayerEdit fragment = new TabFragPlayerEdit();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_player_edit, fragment);
					fragmentTransaction.commit();
				}
			});

			dialog.show();
			// Ende Nachrichtenbox
			
			
		} else {
			
			refreshContent(team_id, player_id, args);
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			TabFragPlayerEdit fragment = new TabFragPlayerEdit();
			fragment.setArguments(args);
			fragmentTransaction.replace(R.id.frag_player_edit, fragment);
			fragmentTransaction.commit();
			
		}       
	}
}
