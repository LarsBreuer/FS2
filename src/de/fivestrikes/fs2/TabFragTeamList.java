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

public class TabFragTeamList extends ListFragment {
	
	Cursor model=null;
	HelperAdapterTeam adapter=null;
	HelperSQL sqlHelper=null;
	String team_id = null;
	String server_team_id = null;
	String club_id = null;
	String server_club_id = null;
	String gender_id = null;
	String stage_of_life_id = null;
	String level_id = null;
	String club_name = null;
	String club_name_short = null;
	Bundle args;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

/* Daten aus Activity laden */ 
	        
		 args = getArguments();
		 
/* Helper generieren */
        
		sqlHelper = new HelperSQL(getActivity());
		
/* Layout festlegen */
		
		refreshContent(team_id, args);
		
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
		if (args != null ) {
			team_id = args.getString("TeamID");
			args = null;
		}
		refreshContent(team_id, args);
    	
	}
    
	public void refreshContent(String team_id, Bundle args) {
		
		if (team_id != null) Log.v("TabFragTeamList team_id", team_id);
		model=sqlHelper.getAllTeamCursor();
		getActivity().startManagingCursor(model);
		adapter = new HelperAdapterTeam(getActivity(), model, team_id);
		setListAdapter(adapter);
		
		if (team_id != null) {
			
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			TabFragTeamEdit fragment = new TabFragTeamEdit();
			fragment.setArguments(args);
			fragmentTransaction.replace(R.id.frag_team_edit, fragment);
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
		team_id = String.valueOf(id);
		club_id = sqlHelper.getTeamClubIDByTeamID(team_id);
		server_team_id = sqlHelper.getTeamServerTeamID(team_id);
		gender_id = sqlHelper.getGenderByTeamID(team_id);
		stage_of_life_id = sqlHelper.getStageByTeamID(team_id);
		level_id = sqlHelper.getLevelByTeamID(team_id);
		club_name = sqlHelper.getClubNameByTeamID(team_id);
		club_name_short = sqlHelper.getClubShortByTeamID(team_id);
		server_club_id = sqlHelper.getTeamServerClubIDByTeamID(team_id);
		
		args = new Bundle();
		args.putString("TeamID", team_id);
		args.putString("ServerTeamID", server_team_id);
		args.putString("ClubID", club_id);
		args.putString("ServerClubID", server_club_id);
		args.putString("GenderID", gender_id);
		args.putString("StageOfLifeID", stage_of_life_id);
		args.putString("LevelID", level_id);
		args.putString("ClubName", club_name);
		args.putString("ClubNameShort", club_name_short);
		
		// FragmentManager fragmentManager = getFragmentManager();
		// FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		// TabFragTeamEdit fragment = new TabFragTeamEdit();
		// fragment.setArguments(args);
		refreshContent(team_id, args);
		// fragmentTransaction.replace(R.id.frag_team_edit, fragment);
		// fragmentTransaction.commit();
        
	}
}
