package de.fivestrikes.fs2;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

public class TabFragEmpty extends Fragment {
	
	private View view;
	HelperSQL sqlHelper = null;
	HelperText txtHelper = null;
	Cursor c = null;
	String overview_text = null;
	String strActivity = null;
	String team_id = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
/* Grundlayout setzen */

		view = inflater.inflate(R.layout.frag_empty_text, container, false);
		TextView text = (TextView) view.findViewById(R.id.text);
		
/* Helper generieren */
		
		sqlHelper  =new HelperSQL(getActivity());
		txtHelper = new HelperText();
		
/* Daten aus Activity laden */ 
	        
		Bundle args = getArguments();
		if (args != null) {
			
			strActivity = args.getString("Activity");
			team_id = args.getString("TeamID");
			
		}
		
/* Text setzen */
		
		// Text bei Team setzen
		if (strActivity != null && strActivity.equals("Team")) {
			
			c = sqlHelper.getAllTeamCursor();			
			c.moveToFirst();
			
			if(c.getCount() != 0){
				
				if(c.getCount() > 5){
					
					overview_text = txtHelper.txtOverviewTeam(String.valueOf(c.getCount()));
					text.setText(overview_text);
					
				} else {
					
					text.setText(getString(R.string.text_overview_team_first));
					
				}
				 
			} else {
				
				text.setText(getString(R.string.text_overview_team_null));
				
			}
			c.close();
			
		}
		
		// Text bei Player setzen
		if (strActivity != null && strActivity.equals("Player") && team_id != null) {
			
			c=sqlHelper.getAllPlayerCursorByTeamID(team_id);
			c.moveToFirst();
			
			if(c.getCount()!=0){
				
				overview_text = txtHelper.txtOverviewPlayer(String.valueOf(c.getCount()));
				text.setText(overview_text);
				 
			} else {
				
				 text.setText(getString(R.string.text_overview_player_null));
				
			}
			
			c.close();
			
		}
	      
		// Text bei Spiel setzen
		if (strActivity != null && strActivity.equals("Game")) {
					
			c = sqlHelper.getAllGameCursor();			
			c.moveToFirst();
			
			if (c.getCount() != 0) {

				overview_text = txtHelper.txtOverviewGame(String.valueOf(c.getCount()));
				text.setText(overview_text);
				 
			} else {
						
				text.setText(getString(R.string.text_overview_game_null));
						
			}
			c.close();
					
		}
		
		// Text bei Statistik setzen
		if (strActivity != null && strActivity.equals("Stat")) {
			
			Integer count_games = 0;
			Integer count_avtivities = 0;
			
			// Spiele zählen
			c = sqlHelper.getAllGameCursor();
			if (c != null) {
				c.moveToFirst();
				count_games = c.getCount();
			}
			c.close();
			
			// Aktivitäten zählen
			c = sqlHelper.getAllTickerCursor();
			if (c != null) {
				c.moveToFirst();
				count_avtivities = c.getCount();
			}
			c.close();
			
			if (count_games > 0) {

				overview_text = txtHelper.txtOverviewStatistic(String.valueOf(count_games), String.valueOf(count_avtivities));
				text.setText(overview_text);
					 
			} else {
						
				text.setText(getString(R.string.text_overview_statistic_null));
						
			}
					
		}
		
		return view;
	}
}