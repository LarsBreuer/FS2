package de.fivestrikes.fs2;

import java.util.Calendar;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartTickerList extends ListActivity {

	HelperSQL sqlHelper=null;
	Cursor model=null;
	HelperAdapterTicker adapter=null;
	Integer possession_id = null;
	Integer goal_id = null;
	Integer goal_7m_id = null;
	Integer goal_fb_id = null;
	Integer miss_id = null;
	Integer miss_7m_id = null;
	Integer miss_fb_id = null;
	Integer assist_goal_id = null;
	Integer assist_miss_id = null;
	Integer defense_error_id = null;
	Integer defense_success_id = null;
	Integer block_error_id = null;
	Integer block_success_id = null;
	Integer foul_id = null;
	Integer save_id = null;
	Integer save_7m_id = null;
	Integer save_fb_id = null;
	Integer goal_against_id = null;
	Integer goal_against_7m_id = null;
	Integer goal_against_fb_id = null;
	Integer tech_fault_id = null;
	Integer fehlpass_id = null;
	Integer steps_id = null;
	Integer three_seconds_id = null;
	Integer doppeldribbel_id = null;
	Integer fuss_id = null;
	Integer zeitspiel_id = null;
	Integer kreis_id = null;
	Integer stuermerfoul_id = null;
	Integer yellow_card_id = null;
	Integer two_minutes_id = null;
	Integer twoplustwo_id = null;
	Integer red_card_id = null;
	Integer lineup_id = null;
	Integer sub_in_id = null;
	Integer sub_out_id = null;
	Integer two_in_id = null;
	Integer timeout_id = null;
	Integer tactic_60_id = null;
	Integer tactic_51_id = null;
	Integer tactic_42_id = null;
	Integer tactic_321_id = null;
	Integer tactic_guarding_id = null;
	Integer activity_id = null, ticker_activity_id = null;
	String game_id=null, strInput = null;
	Bundle args = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		
/* Daten aus Activity laden */ 
	        
		game_id = getIntent().getStringExtra("GameID");
		
/* Teamliste einrichten */
		
		refreshContent(game_id);		
		
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();	  

	}
	
/* Inhalt neu laden, wenn Activity ernuet aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent(game_id);
	    	
	}
	
	public void refreshContent(String game_id) {
	
		sqlHelper=new HelperSQL(this);
		model=sqlHelper.getAllGameTicker(game_id);
		startManagingCursor(model);
		adapter=new HelperAdapterTicker(SmartTickerList.this, model, null);
		setListAdapter(adapter);
		        
	}
	
/* Tickerauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
/** TODO -4- => IDs eventuell global definieren */
		possession_id = getResources().getInteger(R.integer.possession_id);
		goal_id = getResources().getInteger(R.integer.goal_id);
		goal_7m_id = getResources().getInteger(R.integer.goal_7m_id);
		goal_fb_id = getResources().getInteger(R.integer.goal_fb_id);
		miss_id = getResources().getInteger(R.integer.miss_id);
		miss_7m_id = getResources().getInteger(R.integer.miss_7m_id);
		miss_fb_id = getResources().getInteger(R.integer.miss_fb_id);
		assist_goal_id = getResources().getInteger(R.integer.assist_goal_id);
		assist_miss_id = getResources().getInteger(R.integer.assist_miss_id);
		defense_error_id = getResources().getInteger(R.integer.defense_error_id);
		defense_success_id = getResources().getInteger(R.integer.defense_success_id);
		block_error_id = getResources().getInteger(R.integer.block_error_id);
		block_success_id = getResources().getInteger(R.integer.block_success_id);
		foul_id = getResources().getInteger(R.integer.foul_id);
		save_id = getResources().getInteger(R.integer.save_id);
		save_7m_id = getResources().getInteger(R.integer.save_7m_id);
		save_fb_id = getResources().getInteger(R.integer.save_fb_id);
		goal_against_id = getResources().getInteger(R.integer.goal_against_id);
		goal_against_7m_id = getResources().getInteger(R.integer.goal_against_7m_id);
		goal_against_fb_id = getResources().getInteger(R.integer.goal_against_fb_id);
		tech_fault_id = getResources().getInteger(R.integer.tech_fault_id);
		fehlpass_id = getResources().getInteger(R.integer.fehlpass_id);
		steps_id = getResources().getInteger(R.integer.steps_id);
		three_seconds_id = getResources().getInteger(R.integer.three_seconds_id);
		doppeldribbel_id = getResources().getInteger(R.integer.doppeldribbel_id);
		fuss_id = getResources().getInteger(R.integer.fuss_id);
		zeitspiel_id = getResources().getInteger(R.integer.zeitspiel_id);
		kreis_id = getResources().getInteger(R.integer.kreis_id);
		stuermerfoul_id = getResources().getInteger(R.integer.stuermerfoul_id);
		yellow_card_id = getResources().getInteger(R.integer.yellow_card_id);
		two_minutes_id = getResources().getInteger(R.integer.two_minutes_id);
		twoplustwo_id = getResources().getInteger(R.integer.twoplustwo_id);
		red_card_id = getResources().getInteger(R.integer.red_card_id);
		lineup_id = getResources().getInteger(R.integer.lineup_id);
		sub_in_id = getResources().getInteger(R.integer.sub_in_id);
		sub_out_id = getResources().getInteger(R.integer.sub_out_id);
		two_in_id = getResources().getInteger(R.integer.two_in_id);
		timeout_id = getResources().getInteger(R.integer.timeout_id);
		tactic_60_id = getResources().getInteger(R.integer.tactic_60_id);
		tactic_51_id = getResources().getInteger(R.integer.tactic_51_id);
		tactic_42_id = getResources().getInteger(R.integer.tactic_42_id);
		tactic_321_id = getResources().getInteger(R.integer.tactic_321_id);
		tactic_guarding_id = getResources().getInteger(R.integer.tactic_guarding_id);
		
		args = new Bundle();
		ticker_activity_id = (int) (long) id;
		activity_id = sqlHelper.getTickerActivityIDByID(String.valueOf(ticker_activity_id));
		
		if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id)) {
			strInput = "EditGoal";
		}
		if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) {
			strInput = "EditMiss";
		}
		if (activity_id.equals(save_id) || activity_id.equals(save_7m_id) || activity_id.equals(save_fb_id)) {
			strInput = "EditSave";
		}
		if (activity_id.equals(goal_against_id) || activity_id.equals(goal_against_7m_id) || activity_id.equals(goal_against_fb_id)) {
			strInput = "EditGoalAgainst";
		}
		if (activity_id.equals(possession_id)) {
			strInput = "EditPossession";
		}
		if (activity_id.equals(assist_goal_id) || activity_id.equals(assist_miss_id)) {
			strInput = "EditAssist";
		}
		if (activity_id.equals(defense_error_id) || activity_id.equals(defense_success_id) || activity_id.equals(block_error_id) || activity_id.equals(block_success_id) || activity_id.equals(foul_id)) {
			strInput = "EditDefense";
		}
		if (activity_id.equals(tech_fault_id) || activity_id.equals(fehlpass_id) || activity_id.equals(steps_id) || activity_id.equals(three_seconds_id) || activity_id.equals(doppeldribbel_id) || activity_id.equals(fuss_id) || activity_id.equals(zeitspiel_id) || activity_id.equals(kreis_id) || activity_id.equals(stuermerfoul_id)) {
			strInput = "EditTechFault";
		}
		if (activity_id.equals(yellow_card_id) || activity_id.equals(two_minutes_id) || activity_id.equals(twoplustwo_id) || 
				activity_id.equals(red_card_id) || activity_id.equals(sub_in_id) || activity_id.equals(sub_out_id) || 
				activity_id.equals(two_in_id)) {
			strInput = "EditPlayer";
		}
		if (activity_id.equals(timeout_id)) {
			strInput = "EditTimeout";
		}
		if (activity_id.equals(tactic_60_id) || activity_id.equals(tactic_51_id) || activity_id.equals(tactic_42_id) || 
				activity_id.equals(tactic_321_id) || activity_id.equals(tactic_guarding_id)) {
			strInput = "EditTactic";
		}
		args.putInt("ActivityID", activity_id);
		args.putInt("TickerActivityID", ticker_activity_id);
		args.putString("InputString", strInput);
		args.putString("GameID", game_id);
				
		Intent i = new Intent(SmartTickerList.this, SmartListWithText.class);
		i.putExtras(args);
		startActivity(i);
		
	}

}