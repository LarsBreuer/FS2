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
import android.database.sqlite.SQLiteDatabase;
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

public class HelperAdapterStatGameTicker extends CursorAdapter {
	
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
	TickerEventHolder holder;
	Resources res;
	
	public HelperAdapterStatGameTicker(Context context, Cursor c, String id) {
		
		super(context, c);
		
		game_id = id;
		sqlHelper=new HelperSQL(context);
		fctHelper=new HelperFunction();
		lytHelper = new HelperLayout();
		screenDensity=fctHelper.getScreenDensity(context);

	}

	@Override
	public void bindView(View row, Context ctxt, Cursor c) {
			
		TickerEventHolder holder=(TickerEventHolder)row.getTag();
		holder.populateFrom(c, sqlHelper, fctHelper, lytHelper);
		
	}
	
	@Override
	public View newView(Context ctxt, Cursor c, ViewGroup parent) {
		
		LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.row_ticker_text, parent, false);
		TickerEventHolder holder = new TickerEventHolder(row, ctxt);
		row.setTag(holder);
		return(row);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);		
		return view;
		
	}
	
	private static class ViewHolder {
		View view;
	}  
}

class TickerEventHolder {

	private TextView txt_headline = null;
	private TextView txt_event = null;
	private TextView txt_time = null;
	private TextView txt_player = null;
	private TextView txt_icon = null;
	private Integer time = null;
	private static int picSize = 0;
	public ImageView icon = null;
	private View team_color = null;
	Context ctxt=null;
    
	TickerEventHolder(View row, Context context) {
      
		txt_headline = (TextView) row.findViewById(R.id.rowTickerHeadline);
		txt_event = (TextView) row.findViewById(R.id.rowTickerEventText);
		txt_time = (TextView) row.findViewById(R.id.rowTickerTime);
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
		String ticker_id = null;
		Integer home_or_away = 1;
		
		// Event ermitteln		
		String ticker_event_id = sqlHelper.getTickerActivityID(c);
		
		// Text für Tickermeldung und Zeit setzen
		txt_event.setText(sqlHelper.getTickerEventNoteByID(ticker_event_id));
		time = sqlHelper.getTickerEventTimeByID(ticker_event_id);
		txt_time.setText(fctHelper.updateTimer(time) + " Min.");
		
/* Hauptkation ermittelen und dementsprechend Mannschaftsfare Logo und Überschrift setzen */
		
		// Durch die Ticker-Aktivitäten des Events gehen  
		String[] tickerArgs={String.valueOf(ticker_event_id)};
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? ORDER BY time ASC", tickerArgs);
		cTicker.moveToFirst();
		
		// Alle Tickermeldungen abfragen und Tickeraktivität ermitteln
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
			
			ticker_id = sqlHelper.getTickerActivityID(cTicker);
			
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(defense_error_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(block_error_id) || 
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(defense_success_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(block_success_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_defense);
				txt_icon.setText("");
				txt_headline.setText(res.getString(R.string.defense));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(tech_fault_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(fehlpass_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(steps_id) ||
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(three_seconds_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(doppeldribbel_id) ||
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(fuss_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(zeitspiel_id) ||
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(kreis_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(stuermerfoul_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_tech_fault);
				txt_icon.setText("");
				txt_headline.setText(res.getString(R.string.tech_fault));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(yellow_card_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_yellow_card);
				txt_icon.setText("");
				txt_headline.setText(res.getString(R.string.yellow_card));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(two_minutes_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(twoplustwo_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_two_out);
				txt_icon.setText("");
				txt_headline.setText(res.getString(R.string.two_minutes));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(red_card_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_red_card);
				txt_icon.setText("");
				txt_headline.setText(res.getString(R.string.red_card));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(timeout_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_timeout);
				txt_icon.setText("");
				txt_headline.setText(res.getString(R.string.timeout));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_60_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_51_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_42_id) ||
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_321_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_guarding_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_tactic);
				txt_icon.setText("");
				txt_headline.setText(res.getString(R.string.tactic));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_7m_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_fb_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_miss);
				txt_icon.setText("");
				txt_headline.setText(res.getString(R.string.miss));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_7m_id) ||	sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_fb_id)) {
				icon.setImageResource(R.drawable.ticker_symbol_none);
				txt_icon.setText(sqlHelper.getTickerResultByID(ticker_id));
				txt_headline.setText(res.getString(R.string.goal));
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
			}
		}
		
		// Teamfarbe setzen
/** TODO -4- => Farbe an die Vereinsfarbe anpassen */
		if (home_or_away == 1){
			team_color.setBackgroundColor(0xFF404895);
		}
		if (home_or_away == 0){
			team_color.setBackgroundColor(0xFFCB061D);
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


