package de.fivestrikes.fs2;

import android.content.Context;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HelperText {
	
	String strText = null;
	String activity_string = null;
/** TODO -4- => Lösung finden, um die Spielaktionen-IDs zentral zu definieren */
	Integer goal_id, goal_7m_id, goal_fb_id, miss_id, miss_7m_id, miss_fb_id, assist_goal_id, assist_miss_id, defense_error_id, defense_success_id, block_error_id	, block_success_id, foul_id, tech_fault_id, fehlpass_id, steps_id, three_seconds_id, doppeldribbel_id, fuss_id, zeitspiel_id, kreis_id, stuermerfoul_id, yellow_card_id, two_minutes_id, twoplustwo_id, red_card_id, timeout_id	, tactic_60_id, tactic_51_id, tactic_42_id, tactic_321_id, tactic_guarding_id = null;
	HelperSQL sqlHelper = null;
	
	public String txtOverviewTeam(String count) {
		
		strText = "Du hast bereits " + count + " Mannschaften angelegt. Weitere Mannschaften kannst Du oben rechts über ‚+’ anlegen oder bereits vorhandene Mannschaften in der Liste links auswählen.";
		return(strText);
		
	}
	
	public String txtOverviewPlayer(String count) {
		
		strText = "Diese Mannschaft hat bereits " + count + " Spieler. Weitere Spieler kannst Du oben rechts über ‚+’ anlegen oder bereits vorhandene Spieler in der Liste links auswählen.";
		return(strText);
		
	}
	
	public String txtOverviewGame(String count) {
		
		strText = "Du hast bereits " + count + "  Spiele angelegt. Weitere Spiele kannst Du oben rechts über ‚+’ anlegen oder bereits vorhandene Spiele in der Liste links auswählen..";
		return(strText);
		
	}
	
	public String txtOverviewStatistic(String count, String count2) {
		
		strText = "Du hast bereits " + count + "  Spiele mit insgesamt " + count2 + "  Aktionen aufgenommen.\n\nLass Dir hier die Statistik zu Deinen Spielen anzeigen und analysiere das Spielverhalten Deiner Mannschaft. Wähle aus der Liste auf der linken Seite das Spiel aus, dass Du auswerten möchtest.";
		return(strText);
		
	}
	
	// Tickermeldungen generieren
	public String txtTickerThrowoff(String teamname) {
		
		strText = teamname + "  hat Anwurf.";
		return(strText);
		
	}
	
	
/*
 * 
 * 	Generieren der Tickermeldungen
 * 
 */
	
/** TODO -2- => Generieren der Tickermeldungen überarbeiten - gerade bei Torwurf um weitere Informationen ergänzen*/
	public String generateTickerEventNote(Integer ticker_event_id, Context ctxt, Resources res) {
		
		Integer goal_id = res.getInteger(R.integer.goal_id);
		Integer goal_7m_id = res.getInteger(R.integer.goal_7m_id);
		Integer goal_fb_id = res.getInteger(R.integer.goal_fb_id);
		Integer miss_id = res.getInteger(R.integer.miss_id);
		Integer miss_7m_id = res.getInteger(R.integer.miss_7m_id);
		Integer miss_fb_id = res.getInteger(R.integer.miss_fb_id);
		Integer assist_goal_id = res.getInteger(R.integer.assist_goal_id);
		Integer assist_miss_id = res.getInteger(R.integer.assist_miss_id);
		Integer defense_error_id = res.getInteger(R.integer.defense_error_id);
		Integer defense_success_id = res.getInteger(R.integer.defense_success_id);
		Integer block_error_id = res.getInteger(R.integer.block_error_id);
		Integer block_success_id = res.getInteger(R.integer.block_success_id);
		Integer foul_id = res.getInteger(R.integer.foul_id);
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
		Integer timeout_id = res.getInteger(R.integer.timeout_id);
		Integer tactic_60_id = res.getInteger(R.integer.tactic_60_id);
		Integer tactic_51_id = res.getInteger(R.integer.tactic_51_id);
		Integer tactic_42_id = res.getInteger(R.integer.tactic_42_id);
		Integer tactic_321_id = res.getInteger(R.integer.tactic_321_id);
		Integer tactic_guarding_id = res.getInteger(R.integer.tactic_guarding_id);
		
		sqlHelper = new HelperSQL(ctxt);
		
		String ticker_id = null;
		String ticker_goal_id = null;
		String ticker_miss_id = null;
		String ticker_defense_id = null;
		String ticker_foul_id = null;
		String ticker_assist_goal_id = null;
		String ticker_assist_miss_id = null;
		String ticker_tech_fault_id = null;
		String ticker_yellow_card_id = null;
		String ticker_two_minutes_id = null;
		String ticker_twoplustwo_id = null;
		String ticker_red_card_id = null;
		String ticker_timeout_id = null;
		String ticker_tactic_id   = null;
		strText = "";
		
		// Durch die Ticker-Aktivitäten des Events gehen  
		String[] tickerArgs={String.valueOf(ticker_event_id)};
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? ORDER BY time ASC", tickerArgs);
		cTicker.moveToFirst();
		
		// Alle Tickermeldungen abfragen und Tickeraktivität ermitteln
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
			
			ticker_id = sqlHelper.getTickerActivityID(cTicker);
			
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_7m_id) ||	sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_fb_id)) {
				ticker_goal_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_7m_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_fb_id)) {
				ticker_miss_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(defense_error_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(block_error_id) || 
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(defense_success_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(block_success_id)) {
				ticker_defense_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(tech_fault_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(fehlpass_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(steps_id) ||
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(three_seconds_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(doppeldribbel_id) ||
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(fuss_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(zeitspiel_id) ||
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(kreis_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(stuermerfoul_id)) {
				ticker_tech_fault_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(yellow_card_id)) {
				ticker_yellow_card_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(two_minutes_id)) {
				ticker_two_minutes_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(twoplustwo_id)) {
				ticker_twoplustwo_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(red_card_id)) {
				ticker_red_card_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(timeout_id)) {
				ticker_timeout_id = ticker_id;
			}
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_60_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_51_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_42_id) ||
					sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_321_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(tactic_guarding_id)) {
				ticker_tactic_id = ticker_id;
			}
		}
		
		// Je nach Aktion die Tickermeldung einrichten
		if (ticker_tactic_id != null) {
			strText = ticker_tactic(ticker_tactic_id, res);
		}
		if (ticker_timeout_id != null) {
			strText = ticker_timeout(ticker_timeout_id, res);
		}
		if (ticker_red_card_id != null || ticker_twoplustwo_id != null || ticker_two_minutes_id != null || ticker_yellow_card_id != null) {
			strText = ticker_penalty(ticker_red_card_id, ticker_twoplustwo_id, ticker_two_minutes_id, ticker_yellow_card_id, res);
		}
		if (ticker_tech_fault_id != null) {
			strText = ticker_tech_fault(ticker_tech_fault_id, ticker_event_id, res);
		}
		if (ticker_defense_id != null) {
			strText = ticker_defense(ticker_defense_id, res);
		}
		if (ticker_miss_id != null) {
			strText = ticker_miss(ticker_miss_id, ticker_event_id, res);
		}
		if (ticker_goal_id != null) {
			strText = ticker_goal(ticker_goal_id, ticker_event_id, res);
		}
		
		cTicker.close();
		
		return(strText);
		
	}
	
	public String ticker_tactic(String ticker_id, Resources res) {
		
		Integer home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
		String club_name = sqlHelper.getClubNameByTickerEventAndHomeOrAway(ticker_id, home_or_away);
		String tactic_name = sqlHelper.getActivityStringByActivityID(sqlHelper.getTickerActivityIDByID(ticker_id), res);
		
		String strText = club_name + " stellt um auf " + tactic_name +".";
		
		return(strText);
	
	}
	
	public String ticker_timeout(String ticker_id, Resources res) {
		
		Integer home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
		String club_name = sqlHelper.getClubNameByTickerEventAndHomeOrAway(ticker_id, home_or_away);
		
		String strText = club_name + " nimmt eine Auszeit.";
		
		return(strText);
	
	}
	
	public String ticker_penalty(String ticker_red_card_id, String ticker_twoplustwo_id, String ticker_two_minutes_id, String ticker_yellow_card_id, Resources res) {
		
		String penalty = null;
		String ticker_id = null;
		
		if (ticker_red_card_id != null) {
			penalty = res.getString(R.string.red_card);
			ticker_id = ticker_red_card_id;
		}
		if (ticker_twoplustwo_id != null) {
			penalty = res.getString(R.string.two_plus_two);
			ticker_id = ticker_twoplustwo_id;
		}
		if (ticker_two_minutes_id != null) {
			penalty = res.getString(R.string.two_minutes);
			ticker_id = ticker_two_minutes_id;
		}
		if (ticker_yellow_card_id != null) {
			penalty = res.getString(R.string.yellow_card);
			ticker_id = ticker_yellow_card_id;
		}
		
		Integer home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
		String club_name = sqlHelper.getClubNameByTickerEventAndHomeOrAway(ticker_id, home_or_away);
		String player_id = sqlHelper.getTickerPlayerIDByID(ticker_id);
		String player_name = sqlHelper.getPlayerForenameByID(player_id) + " " + sqlHelper.getPlayerSurenameByID(player_id);
		String player_number = sqlHelper.getPlayerNumberByID(player_id);
		
		String strText = penalty + " für den Spieler " + player_name + " (" + player_number + ") von " + club_name + ".";
		
		return(strText);
	
	}
	
	public String ticker_tech_fault(String ticker_id, Integer ticker_event_id, Resources res) {
		
		Integer home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
		Integer possession_id = res.getInteger(R.integer.possession_id);
		String club_name = sqlHelper.getClubNameByTickerEventAndHomeOrAway(ticker_id, home_or_away);
		String tech_fault = sqlHelper.getActivityStringByActivityID(sqlHelper.getTickerActivityIDByID(ticker_id), res);
		String player_id = sqlHelper.getTickerPlayerIDByID(ticker_id);
		String player_name = sqlHelper.getPlayerForenameByID(player_id) + " " + sqlHelper.getPlayerSurenameByID(player_id);
		String player_number = sqlHelper.getPlayerNumberByID(player_id);
		Boolean turnover = false;
		String strTurnover = null;
		
		// Kontrollieren, ob der technische Fehler zu einem Ballverlust führte
		// Durch die Ticker-Aktivitäten des Events gehen  
		String[] tickerArgs={String.valueOf(ticker_event_id)};
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? ORDER BY time ASC", tickerArgs);
		cTicker.moveToFirst();
		
		// Alle Tickermeldungen abfragen und Tickeraktivität ermitteln
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
			
			ticker_id = sqlHelper.getTickerActivityID(cTicker);
			
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(possession_id)) {
				turnover = true;
			}
		}
		
		if (turnover == true) {
			strTurnover = "Der technische Fehler führte zu einem Ballverlust für " + club_name + ".";
		} else {
			strTurnover = "Aber " + club_name + " ist weiterhin im Ballbesitz.";
		}
		
		String strText = tech_fault + " durch den Spieler " + player_name + " (" + player_number + ") von " + club_name + ". " + strTurnover;
		
		return(strText);
	
	}
	
	public String ticker_defense(String ticker_id, Resources res) {
		
		Integer home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
		String club_name = sqlHelper.getClubNameByTickerEventAndHomeOrAway(ticker_id, home_or_away);
		String defense = sqlHelper.getActivityStringByActivityID(sqlHelper.getTickerActivityIDByID(ticker_id), res);
		String player_id = sqlHelper.getTickerPlayerIDByID(ticker_id);
		String player_name = sqlHelper.getPlayerForenameByID(player_id) + " " + sqlHelper.getPlayerSurenameByID(player_id);
		String player_number = sqlHelper.getPlayerNumberByID(player_id);	
		
		String strText = defense + " durch den Spieler " + player_name + " (" + player_number + ") von " + club_name + ".";
		
		return(strText);
	
	}
	
	public String ticker_miss(String ticker_id, Integer ticker_event_id, Resources res) {
		
		Integer home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
		Integer possession_id = res.getInteger(R.integer.possession_id);
		String club_name = sqlHelper.getClubNameByTickerEventAndHomeOrAway(ticker_id, home_or_away);
		String miss = sqlHelper.getActivityStringByActivityID(sqlHelper.getTickerActivityIDByID(ticker_id), res);
		String player_id = sqlHelper.getTickerPlayerIDByID(ticker_id);
		String player_name = sqlHelper.getPlayerForenameByID(player_id) + " " + sqlHelper.getPlayerSurenameByID(player_id);
		String player_number = sqlHelper.getPlayerNumberByID(player_id);
		Boolean turnover = false;
		String strTurnover = null;
		
		// Kontrollieren, ob der technische Fehler zu einem Ballverlust führte
		// Durch die Ticker-Aktivitäten des Events gehen  
		String[] tickerArgs={String.valueOf(ticker_event_id)};
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? ORDER BY time ASC", tickerArgs);
		cTicker.moveToFirst();
		
		// Alle Tickermeldungen abfragen und Tickeraktivität ermitteln
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
			
			ticker_id = sqlHelper.getTickerActivityID(cTicker);
			if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(possession_id)) {
				turnover = true;
			}
		}
		
		if (turnover == true) {
			strTurnover = "Der Fehlwurf führte zu einem Ballverlust für " + club_name + ".";
		} else {
			strTurnover = "Aber " + club_name + " ist weiterhin im Ballbesitz.";
		}
		
		String strText = miss + " des Spielers " + player_name + " (" + player_number + ") von " + club_name + ". " + strTurnover;
		
		return(strText);
	
	}
	
	public String ticker_goal(String ticker_id, Integer ticker_event_id, Resources res) {
		
		Integer home_or_away = sqlHelper.getTickerHomeOrAwayByID(ticker_id);
		String club_name = sqlHelper.getClubNameByTickerEventAndHomeOrAway(ticker_id, home_or_away);
		String goal = sqlHelper.getActivityStringByActivityID(sqlHelper.getTickerActivityIDByID(ticker_id), res);
		String player_id = sqlHelper.getTickerPlayerIDByID(ticker_id);
		String player_name = sqlHelper.getPlayerForenameByID(player_id) + " " + sqlHelper.getPlayerSurenameByID(player_id);
		String player_number = sqlHelper.getPlayerNumberByID(player_id);	
		
		String strText = goal + " durch den Spieler " + player_name + " (" + player_number + ") von " + club_name + ".";
		
		return(strText);
	
	}
	
}
