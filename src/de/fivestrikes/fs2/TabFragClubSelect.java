package de.fivestrikes.fs2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class TabFragClubSelect extends ListFragment {

	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	HelperSQL sqlHelper=null;
	double screenInch = 0;
	float screenDensity;
	Cursor model=null;
	HelperAdapterClub adapter=null;
	String gender_id = null;
	String stage_of_life_id = null;
	String level_id = null;
	String club_name = null;
	String club_name_short = null;
	String team_id = null;
	String server_team_id = null;
	String server_club_id = null;
	String club_id = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

/* Helper generieren */
		
		sqlHelper = new HelperSQL(getActivity());
		
/* Daten aus Activity laden */
		
		Bundle args = getArguments();
		if (args != null) {
			
			gender_id = args.getString("GenderID");
			stage_of_life_id = args.getString("StageOfLifeID");
			level_id = args.getString("LevelID");
			club_name = args.getString("ClubName");
			club_name_short = args.getString("ClubNameShort");
			team_id = args.getString("TeamID");
			club_id = args.getString("ClubID");
			server_team_id = args.getString("ServerTeamID");
			server_club_id = args.getString("ServerClubID");
			
		}
		
/* Vereinsliste einrichten */
		
		model=sqlHelper.getAllClubCursor();
		getActivity().startManagingCursor(model);
		adapter=new HelperAdapterClub(getActivity(), model);
		setListAdapter(adapter);
		
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.list, container, false);
		
	}
	
	@Override
	public void onResume() {

		super.onResume();
	    	
	}
	
/* Auswahl eines Vereins */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		club_id = String.valueOf(id);
		server_club_id = sqlHelper.getClubServerIDByID(club_id);
		club_name = sqlHelper.getClubNameByID(club_id);
		club_name_short = sqlHelper.getClubShortByID(club_id);
		
		Bundle args = new Bundle();
		args.putString("TeamID", team_id);
		args.putString("ServerTeamID", server_team_id);
		args.putString("ClubID", club_id);
		args.putString("ServerClubID", server_club_id);
		args.putString("GenderID", gender_id);
		args.putString("StageOfLifeID", stage_of_life_id);
		args.putString("LevelID", level_id);
		args.putString("ClubName", club_name);
		args.putString("ClubNameShort", club_name_short);
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		TabFragTeamEdit fragment = new TabFragTeamEdit();
		fragment.setArguments(args);
		fragmentTransaction.replace(R.id.frag_team_edit, fragment);
		fragmentTransaction.commit();
		
	}	    
}