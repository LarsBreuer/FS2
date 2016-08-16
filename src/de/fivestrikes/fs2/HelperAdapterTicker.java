package de.fivestrikes.fs2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HelperAdapterTicker extends CursorAdapter {
	
	HelperSQL sqlHelper=null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	float screenDensity;
	int picSize = 0;
	String game_id = null;
	String ticker_activity_id = null;
	Integer ticker_event_id = null;
	Integer current_ticker_event_id = null;
	Integer ticker_size = null;
	Context ctxt = null;
	TickerHolder holder;
	Resources res;
	ArrayList<Integer> ticker = null;
	
	public HelperAdapterTicker(Context context, Cursor c, String id) {
		
		super(context, c);
		
		game_id = id;
		sqlHelper=new HelperSQL(context);
		fctHelper=new HelperFunction();
		lytHelper = new HelperLayout();
		screenDensity=fctHelper.getScreenDensity(context);
		
		ticker = new ArrayList<Integer>();
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				
			ticker_activity_id = sqlHelper.getTickerActivityID(c);
			ticker_event_id = sqlHelper.getTickerEventIDByActivityID(ticker_activity_id);
			ticker.add(ticker_event_id);

		}
		
		ticker_size = ticker.size();

	}

	@Override
	public void bindView(View row, Context ctxt, Cursor c) {
			
		TickerHolder holder=(TickerHolder)row.getTag();
		holder.populateFrom(c, sqlHelper, fctHelper, lytHelper);
		
	}
	
	@Override
	public View newView(Context ctxt, Cursor c, ViewGroup parent) {
		
		LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.row_ticker, parent, false);
		TickerHolder holder = new TickerHolder(row, ctxt);
		row.setTag(holder);
		return(row);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		View view = super.getView(position, convertView, parent);
		ctxt = parent.getContext();
		res = ctxt.getResources();
		Cursor c = (Cursor) getItem(position);

		ticker_activity_id = sqlHelper.getTickerActivityID(c);
		ticker_event_id = sqlHelper.getTickerEventIDByActivityID(ticker_activity_id);

		RelativeLayout row_ticker = (RelativeLayout) view.findViewById(R.id.row_ticker);
		LayoutParams params = row_ticker.getLayoutParams();
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, res.getDisplayMetrics());
		params.height = height; // Standardhöhe setzen, da die Reihe widerverwendet wird und ggfs. schon in der Höhe manipuliert wurde
		
		// Falls sich das Ticker Event geändert hat, füge eine größere Lücke ein
		if (position + 1 < ticker_size) {
			if (ticker.get(position + 1) != null) {
				if (!ticker.get(position + 1).equals(ticker.get(position))) {
					// Höhe des Tickers vergrößern
					height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 68, res.getDisplayMetrics());
					params.height = height;
				} 
			}
		}
		
		return view;
	}

	private static class ViewHolder {
		View view;
	}
}

class TickerHolder {

	private TextView txt_activity = null;
	private TextView txt_time = null;
	private TextView txt_player = null;
	private TextView txt_icon = null;
	private Integer time = null;
	private static int picSize = 0;
	public ImageView icon = null;
	private View team_color = null;
	Context ctxt=null;
    
	TickerHolder(View row, Context context) {
      
		txt_activity = (TextView) row.findViewById(R.id.rowTickerActivity);
		txt_time = (TextView) row.findViewById(R.id.rowTickerTime);
		txt_player = (TextView) row.findViewById(R.id.rowTickerPlayer);
		txt_icon = (TextView) row.findViewById(R.id.rowTickerText);
	      icon = (ImageView) row.findViewById(R.id.rowTickerIcon);
	      team_color = (View) row.findViewById(R.id.rowTickerTeamColor);
		ctxt = context;
    	
	}
    
	void populateFrom(Cursor c, HelperSQL sqlHelper, HelperFunction fctHelper, HelperLayout lytHelper) {
		
		Resources res = ctxt.getResources(); 
		Integer possession_id = res.getInteger(R.integer.possession_id);
		Integer goal_id = res.getInteger(R.integer.goal_id);
		Integer goal_7m_id = res.getInteger(R.integer.goal_7m_id);
		Integer goal_fb_id = res.getInteger(R.integer.goal_fb_id);
		Integer miss_id = res.getInteger(R.integer.miss_id);
		Integer miss_7m_id = res.getInteger(R.integer.miss_7m_id);
		Integer miss_fb_id = res.getInteger(R.integer.miss_fb_id);
		Integer save_id = res.getInteger(R.integer.save_id);
		Integer save_7m_id = res.getInteger(R.integer.save_7m_id);
		Integer save_fb_id = res.getInteger(R.integer.save_fb_id);
		Integer goal_against_id = res.getInteger(R.integer.goal_against_id);
		Integer goal_against_7m_id = res.getInteger(R.integer.goal_against_7m_id);
		Integer goal_against_fb_id = res.getInteger(R.integer.goal_against_fb_id);
		Integer tech_fault_id = res.getInteger(R.integer.tech_fault_id);
		Integer fehlpass_id = res.getInteger(R.integer.fehlpass_id);
		Integer steps_id = res.getInteger(R.integer.steps_id);
		Integer three_seconds_id = res.getInteger(R.integer.three_seconds_id);
		Integer doppeldribbel_id = res.getInteger(R.integer.doppeldribbel_id);
		Integer fuss_id = res.getInteger(R.integer.fuss_id);
		Integer zeitspiel_id = res.getInteger(R.integer.zeitspiel_id);
		Integer kreis_id = res.getInteger(R.integer.kreis_id);
		Integer stuermerfoul_id = res.getInteger(R.integer.stuermerfoul_id);
		Integer yellow_card_id = res.getInteger(R.integer.yellow_card_id);
		Integer two_minutes_id = res.getInteger(R.integer.two_minutes_id);
		Integer twoplustwo_id = res.getInteger(R.integer.twoplustwo_id);
		Integer red_card_id = res.getInteger(R.integer.red_card_id);
		Integer lineup_id = res.getInteger(R.integer.lineup_id);
		Integer sub_in_id = res.getInteger(R.integer.sub_in_id);
		Integer sub_out_id = res.getInteger(R.integer.sub_out_id);
		Integer two_in_id = res.getInteger(R.integer.two_in_id);
		Integer timeout_id = res.getInteger(R.integer.timeout_id);
		Integer tactic_60_id = res.getInteger(R.integer.tactic_60_id);
		Integer tactic_51_id = res.getInteger(R.integer.tactic_51_id);
		Integer tactic_42_id = res.getInteger(R.integer.tactic_42_id);
		Integer tactic_guarding_id = res.getInteger(R.integer.tactic_guarding_id);
		Integer tactic_321_id = res.getInteger(R.integer.tactic_321_id);
		Integer defense_error_id = res.getInteger(R.integer.defense_error_id);
		Integer defense_success_id = res.getInteger(R.integer.defense_success_id);
		Integer assist_goal_id = res.getInteger(R.integer.assist_goal_id);
		Integer assist_miss_id = res.getInteger(R.integer.assist_miss_id);
		Integer block_error_id = res.getInteger(R.integer.block_error_id);
		Integer block_success_id = res.getInteger(R.integer.block_success_id);
		Integer foul_id = res.getInteger(R.integer.foul_id);
		String player_id = null;
		
		// Activity ermitteln		
		String ticker_activity_id = sqlHelper.getTickerActivityID(c);
		Integer ticker_activity = sqlHelper.getTickerActivityIDByID(ticker_activity_id);
		
		// Text setzen
		txt_activity.setText(sqlHelper.getTickerActivityStringByID(ticker_activity_id));
		time = sqlHelper.getTickerTimeByActivityID(ticker_activity_id);
		
		txt_time.setText(fctHelper.updateTimer(time) + " Min.");
		
		if (sqlHelper.getTickerPlayerIDByID(ticker_activity_id) != null) {
			
			player_id = sqlHelper.getTickerPlayerIDByID(ticker_activity_id);
			txt_player.setText(sqlHelper.getPlayerForenameByID(player_id) + " " +
					sqlHelper.getPlayerSurenameByID(player_id) + " (" +
					sqlHelper.getPlayerNumberByID(player_id) + ")");
			
		} else {
			
			if (ticker_activity.equals(possession_id) || ticker_activity.equals(tactic_60_id) ||
					ticker_activity.equals(tactic_51_id) || ticker_activity.equals(tactic_42_id) ||
					ticker_activity.equals(tactic_guarding_id) || ticker_activity.equals(tactic_321_id) || ticker_activity.equals(timeout_id)) {
				txt_player.setText("");
			} else {
				txt_player.setText("N.N.");
			}
			
		}
		
		// Teamfarbe setzen
/** TODO -4- => Farbe an die Vereinsfarbe anpassen */
		if (sqlHelper.getTickerHomeOrAwayByID(ticker_activity_id) == 1){
			team_color.setBackgroundColor(0xFF404895);
		}
		if (sqlHelper.getTickerHomeOrAwayByID(ticker_activity_id) == 0){
			team_color.setBackgroundColor(0xFFCB061D);
		}
		
		// Aktions-Logo setzen
		if (ticker_activity.equals(goal_id) || ticker_activity.equals(goal_7m_id) || ticker_activity.equals(goal_fb_id)){
			icon.setImageResource(R.drawable.ticker_symbol_none);
			txt_icon.setText(sqlHelper.getTickerResultByID(ticker_activity_id)); 
		}
		if (ticker_activity.equals(miss_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_miss);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(tech_fault_id) || ticker_activity.equals(fehlpass_id) ||
				ticker_activity.equals(steps_id) || ticker_activity.equals(three_seconds_id) ||
				ticker_activity.equals(doppeldribbel_id) || ticker_activity.equals(fuss_id) ||
				ticker_activity.equals(zeitspiel_id) || ticker_activity.equals(kreis_id) || ticker_activity.equals(stuermerfoul_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_tech_fault);
			txt_icon.setText("");
	      }
		if (ticker_activity.equals(miss_7m_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_7m_miss);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(miss_fb_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_fb_miss);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(yellow_card_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_yellow_card);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(red_card_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_red_card);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(two_minutes_id) || ticker_activity.equals(twoplustwo_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_two_out);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(possession_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_possession);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(sub_out_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_sub_out);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(two_in_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_two_in);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(sub_in_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_sub_in);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(save_id) || ticker_activity.equals(save_fb_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_gk_save);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(goal_against_id) || ticker_activity.equals(goal_against_fb_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_gk_goal);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(save_7m_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_gk_7m_save);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(goal_against_7m_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_gk_7m_goal);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(timeout_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_timeout);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(defense_error_id) || ticker_activity.equals(defense_success_id) ||
				ticker_activity.equals(block_error_id) || ticker_activity.equals(block_success_id) ||
				ticker_activity.equals(foul_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_defense);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(assist_goal_id) || ticker_activity.equals(assist_miss_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_assist);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(tactic_60_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_tactic_60);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(tactic_51_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_tactic_51);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(tactic_42_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_tactic_42);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(tactic_321_id)) {
			icon.setImageResource(R.drawable.ticker_symbol_tactic_321);
			txt_icon.setText("");
		}
		if (ticker_activity.equals(tactic_guarding_id)) {
/** TODO -3- => Symbol für Manndeckung erstellen */
			icon.setImageResource(R.drawable.ticker_symbol_defense);
			txt_icon.setText("");
		}
		
		DisplayMetrics metrics = res.getDisplayMetrics();
		picSize=95;
		if (metrics.density < 2.1) {
			picSize=75;
		}
		if(metrics.density<1.6){
			picSize=45;
		}

		lytHelper.scaleImage(icon, picSize, "RelativeLayout");
  
	}
	
}


