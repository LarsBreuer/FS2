package de.fivestrikes.fs2;

import android.content.Context;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class HelperSQL extends SQLiteOpenHelper {

	private static final String DATABASE_NAME="fs2.db";
	private static final int SCHEMA_VERSION=3;
	private String minutes,seconds;
	private long secs,mins;
	private String strResult=null;
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
	Integer Sprungwurf_id = null;
	Integer Schlagwurf_id = null;
	Integer Laufwurf_id = null;
	Integer Fallwurf_id = null;
	Integer Hueftwurf_id = null;
	Integer kempa_id = null;
	Integer Dreher_id = null;
	Integer Heber_id = null;
	Integer Leger_id = null;
	Integer Abknickwurf_id = null;
	
	public HelperSQL(Context context) {
		
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
		
	}

/* Tabellen definieren */
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE app (	_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
											"stat_game_activities STRING," +
											"first_time_tutorial INTEGER," +
											"smart_or_tab_version INTEGER," +
											"user_name STRING," +
											"server_user_id STRING);");
		db.execSQL("CREATE TABLE club (	_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
											"server_club_id INTEGER," +
											"club_name STRING," +
											"club_name_short STRING);");
		db.execSQL("CREATE TABLE team (	_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
											"server_team_id INTEGER," +
											"club_id INTEGER," +
											"server_club_id INTEGER," +
											"team_type_id STRING);");
		db.execSQL("CREATE TABLE player (	_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
											"server_player_id INTEGER," +
											"team_id INTEGER," +
											"server_team_id INTEGER," +
											"player_number INTEGER," +
											"player_forename STRING," +
											"player_surename STRING," +
											"player_position_first STRING," +
											"player_position_second STRING," +
											"player_position_third STRING);");
		db.execSQL("CREATE TABLE game (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
											"team_home_id INTEGER," +		// 1
											"team_away_id INTEGER, " +		// 2
											"current_halftime INTEGER, " +		// 3
											"possession INTEGER, " +			// 4
											"duration_halftime INTEGER, " +	// 5
											"game_date STRING, " +			// 6
											"goals_home INTEGER, " +			// 7
											"goals_away INTEGER, " +			// 8
											"time_sofar LONG, " +				// 9
											"time_start LONG, " +				// 10
											"time_ticker INTEGER, " +			// 11
											"gk_home_id INTEGER, " +			// 12
											"gk_away_id INTEGER, " +			// 13
	  			 							"game_note TEXT, " +					// 14
	  			 							"input_player INTEGER DEFAULT 0, " +	// 15	0
											"input_area INTEGER DEFAULT 0, " +	// 16	1
											"input_throwing_technique INTEGER DEFAULT 0, " +	// 17	2
											"input_assist INTEGER DEFAULT 0, " +	// 18	3
											"input_defense INTEGER DEFAULT 0, " +	// 19	4
											"input_mark INTEGER DEFAULT 0, " +	// 20	5
											"input_tech_fault INTEGER DEFAULT 0, " +	// 21	6
	  			 							"input_text INTEGER DEFAULT 0, " +		//22	7
											"server_game_id INTEGER);");				// 23			
		db.execSQL("CREATE TABLE ticker_event (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
												"game_id INTEGER," +
												"time INTEGER," +
												"ticker_event_note TEXT, " +
												"ticker_result STRING, " +
												"server_ticker_event_id INTEGER);");
		db.execSQL("CREATE TABLE ticker_activity (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
												"game_id INTEGER," +			// 1
												"ticker_event_id INTEGER," +	// 2
												"time INTEGER, " +				// 3
												"realtime STRING, " +			// 4
												"activity_id INTEGER, " +		// 5
												"home_or_away INTEGER, " +	// 6
												"player_id INTEGER, " +			// 7
												"goal_area STRING, " +			// 8
												"field_position_x INTEGER, " +	// 9
												"field_position_y INTEGER, " +	// 10
												"throwing_technique_id INTEGER, " +	// 11
												"ticker_activity_note TEXT, " +	// 12
												"activity_string STRING, " +		// 13
												"ticker_result STRING," +		// 14
												"mark INTEGER, " +				// 15
												"server_ticker_activity_id INTEGER);");	// 16
		
	}
		
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if (oldVersion<2) {
			
			db.execSQL("CREATE TABLE app (	_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"stat_game_activities STRING," +
					"first_time_tutorial INTEGER," +
					"smart_or_tab_version INTEGER);");
			db.execSQL("ALTER TABLE game ADD COLUMN server_game_id INTEGER");
			db.execSQL("ALTER TABLE app ADD COLUMN user_name STRING");
			db.execSQL("ALTER TABLE app ADD COLUMN server_user_id INTEGER");
			
		}
		
		if (oldVersion<3) {
			
			db.execSQL("ALTER TABLE ticker_event ADD COLUMN server_ticker_event_id INTEGER");
			db.execSQL("ALTER TABLE ticker_activity ADD COLUMN server_ticker_activity_id INTEGER");
			
		}
	}

/* Datenbankabfrage App */
	
	// Cursor Abfrage

	public Cursor getApp() {
		return(getReadableDatabase().rawQuery("SELECT * FROM app", null));
	}
	
	// Appdaten einfügen
	
	public void insertApp() {
		
		ContentValues cv=new ContentValues();
		cv.put("stat_game_activities", "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		cv.put("first_time_tutorial", 1);
		cv.put("smart_or_tab_version", 0);
		getWritableDatabase().insert("app", "stat_game_activities", cv);
			
	}
	
	// Appdaten aktualisieren
	
	public void updateSmartOrTab(Integer smart_or_tab) {
		
		ContentValues cv=new ContentValues();
		String[] args={"1"};
		  
		cv.put("smart_or_tab_version", smart_or_tab);

		getWritableDatabase().update("app", cv, "_ID=?", args);
		
	}
	
	public void updateStatGameActivities(int[] activityList, Integer check) {
		
		ContentValues cv=new ContentValues();
		String[] args={"1"};
		String character = null;
		String stat_game_activities = "";
		
		if (activityList != null) {
			for (int x = 0; x<= activityList.length - 1; x++) {
				if (activityList[x] == 1) character = "I";
				if (activityList[x] == 0) character = "O";
				stat_game_activities = stat_game_activities + character;
			}
			cv.put("stat_game_activities", stat_game_activities);
		} else {
			if (check != null) {
				if (check.equals(1)) {
					cv.put("stat_game_activities", "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
				} else {
					cv.put("stat_game_activities", "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
				}
				
			} else {
				cv.put("stat_game_activities", "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
			}
		}

		getWritableDatabase().update("app", cv, "_ID=?", args);
		
	}
	
	public void updateUser(String server_user_id, String user_name) {
		
		ContentValues cv=new ContentValues();
		String[] args={"1"};
		  
		if (server_user_id != null) cv.put("server_user_id", server_user_id);
		if (user_name != null) cv.put("user_name", user_name);

		getWritableDatabase().update("app", cv, "_ID=?", args);
		
	}
	
	// Appdaten abfragen
	
	public Boolean dbExists() {
		
		Boolean rowExists;
		Cursor c = getApp();

		if (c.moveToFirst()) {
		  rowExists = true;
		} else {
		   rowExists = false;
		}	
		return(rowExists);
		
	}
	
	public String getStatGameActivities() {
		
		Cursor c = getApp();
		c.moveToFirst();		
		return(c.getString(1));
		
	}
	
	public String getUserName() {
		
		Cursor c = getApp();
		c.moveToFirst();
		String user_name = c.getString(4);
		c.close();
		return(user_name);
		
	}
	
	public String getUserID() {
		
		Cursor c = getApp();
		c.moveToFirst();
		String user_name = c.getString(5);
		c.close();
		return(user_name);
		
	}
	
	public int[] getActivityListFromActivityString() {
		
		int[] activityList = new int[42];
		String character = null;
		Integer integer = null;
		String stat_game_activities = getStatGameActivities();
		for (int x = 0; x<= stat_game_activities.length() - 1; x++) {
			character = stat_game_activities.substring(x, x+1);
			if (character.equals("I")) integer = 1;
			if (character.equals("O")) integer = 0;
			activityList[x] = integer;
		}		
		return(activityList);
		
	}
	
/* Datenbankabfrage Verein */

	// Cursor Abfrage

	public Cursor getAllClubCursor() {
		return(getReadableDatabase()
				.rawQuery("SELECT _id, server_club_id, club_name, club_name_short  FROM club ORDER BY club_name ASC", null));
	}
	
	// Appdaten abfragen
	
	public Cursor getClubByID(String id) {
		
		String[] args={id};
		return(getReadableDatabase()
				.rawQuery("SELECT _id, server_club_id, club_name, club_name_short FROM club WHERE _ID=?", args));
		
	}
	
	// Clubdaten eintragen
	
	public void insertClub(String club_name, String club_name_short) {
			
		ContentValues cv=new ContentValues();
		cv.put("club_name", club_name);
		cv.put("club_name_short", club_name_short);
		getWritableDatabase().insert("club", "club_name", cv);
			
	}
	
	public void deleteClub(String id) {
		
		 String[] args={id};
		 getWritableDatabase().delete("club", "_ID=?", args);
		  
	}
	
	public void deleteClubServerID(String club_id) {
		
		ContentValues cv=new ContentValues();
		String[] args={club_id};
		
		Integer server_club_id = null;
		
		cv.put("server_club_id", server_club_id);
		
		getWritableDatabase().update("club", cv, "_ID=?", args);
		
	}
	
	// Clubdaten aktualisiseren
	
	public void updateClub(String id, Integer server_club_id, String club_name, String club_name_short) {
		
		ContentValues cv=new ContentValues();
		String[] args={id};
		  
		cv.put("server_club_id", server_club_id);
		cv.put("club_name", club_name);
		cv.put("club_name_short", club_name_short);

		getWritableDatabase().update("club", cv, "_ID=?", args);
		
	}
	
	// Clubdaten abfragen
	
	public String getClubID(Cursor c) {
		return(c.getString(0));
	}
	
	public String getClubServerID(Cursor c) {
		return(c.getString(1));
	}
	
	public String getClubServerIDByID(String clubID) {
		return(getClubString(clubID, 1));
	}
	
	public String getClubName(Cursor c) {
		return(c.getString(2));
	}
	
	public String getClubNameByID(String clubID) {
		return(getClubString(clubID, 2));
	}
	
	public String getClubShortByID(String clubID) {
		return(getClubString(clubID, 3));
	}

	public String getClubNameByTeamCursor(Cursor c) {
		
		String club_id;
		String club_name;
		c.moveToFirst();
		if(c.getCount()>0){
			club_id=c.getString(2);
			club_name=getClubNameByID(club_id);
		} else {
			club_name="";
		}
		return(club_name);
		
	}
	
	public String getClubShortByTeamCursor(Cursor c) {
		
		String club_id;
		String club_short="";
		c.moveToFirst();
		if(c.getCount()>0){
			club_id=c.getString(1);
			club_short=getClubShortByID(club_id);
		} 
		return(club_short);
		
	}
	
	public String getClubNameByTeamID(String teamID) {
		
		Cursor c=getTeamCursorByID(teamID);
		c.moveToFirst();
		String club_name=getClubNameByTeamCursor(c);
		c.close();
		return(club_name);
		
	}
	
	public String getClubShortByTeamID(String teamID) {
		
		String club_id=getTeamClubIDByTeamID(teamID);
		String club_short=getClubShortByID(club_id);
		return(club_short);
		
	}
	
	public String getClubString(String clubID, Integer number) {
		
		Cursor c=getClubByID(clubID);
		c.moveToFirst();
		if(c.getCount()>0){
			strResult=c.getString(number);
		} else {
			strResult="";
		}
		c.close();
		return(strResult);
	}
	
	public String getLastClubID() {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM club WHERE _ID=(SELECT MAX(_ID) FROM club)", null);
		c.moveToFirst();
		if(c.getCount()!=0){
			strResult = getClubID(c);
		}
		c.close();
		return(strResult);

	}
	
	public String getClubIDByClubName(String club_name) {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM club WHERE club_name=?", new String[] { club_name });
		c.moveToFirst();
		if(c.getCount()!=0){
			strResult = getClubID(c);
		}
		c.close();
		return(strResult);

	}
	
	public String getServerClubIDByClubName(String club_name) {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM club WHERE club_name=?", new String[] { club_name });
		c.moveToFirst();
		if(c.getCount()!=0){
			strResult = getClubServerID(c);
		}
		c.close();
		return(strResult);

	}
	
	public int countClubByName(String club_name) {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor mCount= db.rawQuery("SELECT * FROM club WHERE club_name = ?", new String[] { club_name });
		mCount.moveToFirst();
		int count= mCount.getCount();
		mCount.close();
		return(count);

	}
	
/* Datenbankabfrage Mannschaften */
	
	// Cursor Abfrage
	
	public Cursor getAllTeamCursor() {
		return(getReadableDatabase()
				.rawQuery("SELECT *  FROM team ORDER BY club_id ASC", null));
	}
	
	public Cursor getTeamCursorByID(String id) {

		String[] args={id};
		return(getReadableDatabase()
				.rawQuery("SELECT *  FROM team WHERE _ID=?", args));
	}
	
	// Mannschaftsdaten eintragen und löschen
	
	public void insertTeam(String server_team_id, String club_id, String server_club_id, String team_type_id) {
		
		ContentValues cv=new ContentValues();
		if(server_team_id!=null) cv.put("server_team_id", Integer.parseInt(server_team_id));
		if(server_club_id!=null) cv.put("server_club_id", Integer.parseInt(server_club_id));
		if(club_id!=null) cv.put("club_id", Integer.parseInt(club_id));
		cv.put("team_type_id", team_type_id);
		getWritableDatabase().insert("team", "club_id", cv);
		
	}
	
	public void deleteTeam(String id) {
		
		 String[] args={id};
		 getWritableDatabase().delete("team", "_ID=?", args);
		  
	}
	
	public void deleteTeamServerIDTeamID(String team_id) {
		
		String[] args={String.valueOf(team_id)};
		
		// Server Team ID löschen
		deleteTeamServerID(team_id);
		
		// Server Club ID löschen
		String club_id = getTeamClubIDByTeamID(team_id);
		deleteClubServerID(club_id);
		
		// Server Player IDs löschen
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM player WHERE team_id = ?", args);
		c.moveToFirst();
		
		String player_id;

		// Alle Tickermeldungen abfragen und Daten in das Array eintragen eintragen
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			player_id = getPlayerID(c);
			deletePlayerServerID(player_id);

		}
		
		c.close();
		
	}
	
	public void deleteTeamServerID(String team_id) {
		
		ContentValues cv=new ContentValues();
		String[] args={team_id};
		
		Integer server_team_id = null;
		Integer server_club_id = null;
		
		cv.put("server_team_id", server_team_id);
		cv.put("server_club_id", server_club_id);
		
		getWritableDatabase().update("team", cv, "_ID=?", args);
		
	}
	
	// Mannschaftsdaten aktualisiseren
	
	public void updateTeam(String id, String server_team_id, String club_id, String server_club_id, String team_type_id) {
		
		ContentValues cv=new ContentValues();
		String[] args={id};
		cv.put("server_team_id", server_team_id);
		cv.put("club_id", club_id);
		cv.put("server_club_id", server_club_id);
		cv.put("team_type_id", team_type_id);
		getWritableDatabase().update("team", cv, "_ID=?", args);
		
	}
	
	public void updateAllTeamsServerClubID(String club_id, Integer club_id_server) {
		
		String[] args={club_id};
		SQLiteDatabase db=getWritableDatabase();
		Cursor cTeams=db.rawQuery("SELECT * FROM team WHERE club_id=?", args);
		
		for (cTeams.moveToFirst(); !cTeams.isAfterLast(); cTeams.moveToNext()) {
			
			String teamID=getTeamID(cTeams);
			updateTeamServerClubID(teamID, String.valueOf(club_id_server));
			
		}
		
	}
	
	public void updateTeamServerClubID(String id, String server_club_id) {
		
		ContentValues cv=new ContentValues();
		String[] args={id};
		  
		if (server_club_id != null) cv.put("server_club_id", Integer.parseInt(server_club_id));

		getWritableDatabase().update("team", cv, "_ID=?", args);
		
	}
	
	// Mannschaftsdaten abfragen
	
	public String getTeamID(Cursor c) {
		return(c.getString(0));
	}
	
	public String getTeamClubIDByTeamID(String teamID) {
		return(getTeamString(teamID, 2));
	}
	
	public String getLastTeamID() {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM team WHERE _ID=(SELECT MAX(_ID) FROM team)", null);
		c.moveToFirst();
		if(c.getCount()!=0){
			strResult = getClubID(c);
		}
		c.close();
		return(strResult);

	}
	
	public String getTeamClubNameByID(String teamID) {
		String clubID = getTeamClubIDByTeamID(teamID);
		return(getClubNameByID(clubID));
	}
	
	public String getTeamClubShortByID(String teamID) {
		String clubID = getTeamClubIDByTeamID(teamID);
		return(getClubShortByID(clubID));
	}
	
	public String getTeamServerClubIDByTeamID(String teamID) {
		String clubID = getTeamClubIDByTeamID(teamID);
		return(getClubServerIDByID(clubID));
	}
	
	public String getTeamServerTeamID(String teamID) {
		return(getTeamString(teamID, 1));
	}
	
	public String getTeamServerClubID(String teamID) {
		return(getTeamString(teamID, 3));
	}

	public String getTeamTypeID(String teamID) {
		return(getTeamString(teamID, 4));
	}
	
	public String getTeamTypeNameByTeamID(String teamID, Context ctxt) {
		
		return getTeamTypeName(getTeamTypeID(teamID), ctxt);
		
	}
	
	public String getTeamTypeNameByTeamCursor(Cursor c, Context ctxt) {
		
		String team_type_id;
		String teamTypeName = "";
		c.moveToFirst();
		if(c.getCount()>0){
			team_type_id=c.getString(4);
			teamTypeName=getTeamTypeName(team_type_id, ctxt);
		} 
		return(teamTypeName);
		
	}
	
	public String getTeamIDByClubIDAndTeamTypeID(String club_id, String team_type_id) {
		
		String teamID=null;
		if(club_id!=null){
			String[] args={club_id, team_type_id};
			SQLiteDatabase db = getReadableDatabase();
			Cursor c= db.rawQuery("SELECT * FROM team WHERE club_id = ? AND team_type_id = ?", args);
			c.moveToFirst();
			if (c.getCount() != 0) {
				teamID=c.getString(0);
			} 
			c.close();
		}
		return(teamID);

	}

	public String getTeamTypeName(String teamTypeID, Context ctxt) {
		
		String teamTypeName = "";
		teamTypeName=teamTypeID;
		if(teamTypeID.substring(2,3).equals("1")) {						// Männliche Mannschaft
			if(teamTypeID.substring(3,4).equals("1")) {					// Senioren
				teamTypeName=teamTypeID.substring(4)+". "+ctxt.getString(R.string.gents);
			}
			if(teamTypeID.substring(3,4).equals("2")) {					// A-Jugend
				teamTypeName="A"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.male);
			}
			if(teamTypeID.substring(3,4).equals("3")) {					// B-Jugend
				teamTypeName="B"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.male);
			}
			if(teamTypeID.substring(3,4).equals("4")) {					// C-Jugend
				teamTypeName="C"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.male);
			}
			if(teamTypeID.substring(3,4).equals("5")) {					// D-Jugend
				teamTypeName="D"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.male);
			}
			if(teamTypeID.substring(3,4).equals("6")) {					// E-Jugend
				teamTypeName="E"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.male);
			}
			if(teamTypeID.substring(3,4).equals("7")) {					// Minis
				teamTypeName=ctxt.getString(R.string.minis)+" "+teamTypeID.substring(4);
			}
		}
		if(teamTypeID.substring(2,3).equals("2")) {						// Weibliche Mannschaft
			if(teamTypeID.substring(3,4).equals("1")) {					// Senioren
				teamTypeName=teamTypeID.substring(4)+". "+ctxt.getString(R.string.ladies);
			}
			if(teamTypeID.substring(3,4).equals("2")) {					// A-Jugend
				teamTypeName="A"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.female);
			}
			if(teamTypeID.substring(3,4).equals("3")) {					// B-Jugend
				teamTypeName="B"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.female);
			}
			if(teamTypeID.substring(3,4).equals("4")) {					// C-Jugend
				teamTypeName="C"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.female);
			}
			if(teamTypeID.substring(3,4).equals("5")) {					// D-Jugend
				teamTypeName="D"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.female);
			}
			if(teamTypeID.substring(3,4).equals("6")) {					// E-Jugend
				teamTypeName="E"+teamTypeID.substring(4)+" "+ctxt.getString(R.string.female);
			}
			if(teamTypeID.substring(3,4).equals("7")) {					// Minis
				teamTypeName=ctxt.getString(R.string.minis)+" "+teamTypeID.substring(4);
			}
		}
		return(teamTypeName);
		
	}
	
	public String getGenderByTeamID(String team_id) {
		
		String teamTypeID=getTeamTypeID(team_id);
		return(teamTypeID.substring(2,3));
		
	}
	
	public String getStageByTeamID(String team_id) {
		
		String teamTypeID=getTeamTypeID(team_id);
		return(teamTypeID.substring(3,4));
		
	}
	
	public String getLevelByTeamID(String team_id) {
		
		String teamTypeID=getTeamTypeID(team_id);
		return(teamTypeID.substring(4));
		
	}
	
	public String getTeamString(String teamID, Integer number) {
		
		Cursor c=getTeamCursorByID(teamID);
		c.moveToFirst();
		if(c.getCount()>0){
			strResult=c.getString(number);
		} else {
			strResult="";
		}
		c.close();
		return(strResult);
		
	}
	
	public int count_team_game(String team_id) {
		
		int count=0;
		String[] args={team_id, team_id};
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = null;
		c= db.rawQuery("SELECT * FROM game WHERE team_home_id = ? OR team_away_id = ?", args);
		c.moveToFirst();
		if(c.getCount()>0){
			count= c.getCount();
		}
		c.close();
		return(count);
		
	}
	
	public int countTeam(String club_id, String team_type_id) {
		
		int count=0;
		String[] args={club_id, team_type_id};
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = null;
		if (club_id != null && team_type_id != null) {
			c= db.rawQuery("SELECT * FROM team WHERE club_id = ? AND team_type_id = ?", args);
		} else {
			c= db.rawQuery("SELECT * FROM team", null);
		}
		c.moveToFirst();
		if(c.getCount()>0){
			count= c.getCount();
		}
		c.close();
		return(count);

	}
	
	public int countTeamPlayerNotSynch(String team_id) {
		
		int count=0;
		String[] args={team_id};
		SQLiteDatabase db = getReadableDatabase();
		Cursor c= db.rawQuery("SELECT * FROM player WHERE team_id = ? AND server_player_id IS NULL", args);
		c.moveToFirst();
		if(c.getCount()>0){
			count= c.getCount();
		}
		c.close();
		return(count);
		
	}
	
	public int countTeamPlayer(String team_id) {
		
		int count=0;
		String[] args={team_id};
		SQLiteDatabase db = getReadableDatabase();
		Cursor c= db.rawQuery("SELECT * FROM player WHERE team_id = ?", args);
		c.moveToFirst();
		if(c.getCount()>0){
			count= c.getCount();
		}
		c.close();
		return(count);
		
	}
	
	// Mannschaft automatisch anlegen
	public void addFastTeam(Context ctxt) {
		
		// Neuen Verein anlegen
		insertClub(ctxt.getString(R.string.team) + " " + String.valueOf(countTeam(null, null) + 1),  ctxt.getString(R.string.team_short_string) + " " + String.valueOf(countTeam(null, null) + 1));
		String club_id = getLastClubID();
		
		// Neue Mannschaft des Vereins anlegen
		insertTeam(null, club_id, null, "10111"); 
		String team_id = getLastTeamID();
		
		// Spieler des Vereins anlegen
		insertPlayer(null, team_id, null, "1", ctxt.getString(R.string.player), "1", "1001", null, null);
		insertPlayer(null, team_id, null, "2", ctxt.getString(R.string.player), "2", "1002", null, null);
		insertPlayer(null, team_id, null, "3", ctxt.getString(R.string.player), "3", "1003", null, null);
		insertPlayer(null, team_id, null, "4", ctxt.getString(R.string.player), "4", "1004", null, null);
		insertPlayer(null, team_id, null, "5", ctxt.getString(R.string.player), "5", "1005", null, null);
		insertPlayer(null, team_id, null, "6", ctxt.getString(R.string.player), "6", "1006", null, null);
		insertPlayer(null, team_id, null, "7", ctxt.getString(R.string.player), "7", "1007", null, null);
		insertPlayer(null, team_id, null, "8", ctxt.getString(R.string.player), "8", "1002", null, null);
		insertPlayer(null, team_id, null, "9", ctxt.getString(R.string.player), "9", "1003", null, null);
		insertPlayer(null, team_id, null, "10", ctxt.getString(R.string.player), "10", "1004", null, null);
		insertPlayer(null, team_id, null, "11", ctxt.getString(R.string.player), "11", "1005", null, null);
		insertPlayer(null, team_id, null, "12", ctxt.getString(R.string.player), "12", "1001", null, null);
		insertPlayer(null, team_id, null, "13", ctxt.getString(R.string.player), "13", "1006", null, null);
		insertPlayer(null, team_id, null, "14", ctxt.getString(R.string.player), "14", "1007", null, null);

	}
	
/* Datenbankabfrage Spieler */
	
	// Cursor Abfrage
	
	public Cursor getAllPlayerCursor() {
		return(getReadableDatabase()
				.rawQuery("SELECT * FROM player ORDER BY player_surename ASC", null));
	}
	
	public Cursor getAllPlayerCursorByTeamID(String id) {

		String[] args={id};
		return(getReadableDatabase()
				.rawQuery("SELECT * FROM player WHERE team_id=? ORDER BY player_number ASC", args));
		
	}
	
	public Cursor getPlayerCursorByID(String id) {

		String[] args={id};
		return(getReadableDatabase()
				.rawQuery("SELECT * FROM player WHERE _ID=?", args));
		
	}
	
	public Cursor getPlayerCursorByServerID(String id) {

		String[] args={id};
		return(getReadableDatabase()
				.rawQuery("SELECT * FROM player WHERE server_player_id=?", args));
		
	}
	
	public Cursor getAllPlayerCursorServerPlayerID() {

		return(getReadableDatabase()
				.rawQuery("SELECT * FROM player WHERE server_player_id IS NOT ?", null));
		
	}
	
	// Spielerdaten anlegen
	
	public void insertPlayer(String server_player_id, String team_id, String server_team_id, String player_number, String player_forename, String player_surename, String player_position_first, String player_position_second, String player_position_third) {
		
		ContentValues cv=new ContentValues();
		if (server_player_id!=null) {cv.put("server_player_id", Integer.parseInt(server_player_id)); }
		if (team_id!=null) {cv.put("team_id", Integer.parseInt(team_id)); }
		if (server_team_id!=null) {cv.put("server_team_id", Integer.parseInt(server_team_id)); }
		if (player_number!=null && !player_number.equals("")) {cv.put("player_number", Integer.parseInt(player_number)); }
		cv.put("player_forename", player_forename);
		cv.put("player_surename", player_surename);
		cv.put("player_position_first", player_position_first);
		cv.put("player_position_second", player_position_second);
		cv.put("player_position_third", player_position_third);
		getWritableDatabase().insert("player", "player_surename", cv);
		
	}
	
	// Spielerdaten aktualisiseren
	
	public void updatePlayer(String id, String server_player_id, String team_id, String server_team_id, String player_number, String player_forename, String player_surename, String player_position_first, String player_position_second, String player_position_third) {
		
		ContentValues cv=new ContentValues();
		String[] args={id};
		  
		if (server_player_id!=null) {cv.put("server_player_id", Integer.parseInt(server_player_id)); }
		if (team_id!=null) {cv.put("team_id", Integer.parseInt(team_id)); }
		if (server_team_id!=null) {cv.put("server_team_id", Integer.parseInt(server_team_id)); }
		if (player_number!=null) {cv.put("player_number", Integer.parseInt(player_number)); }
		cv.put("player_forename", player_forename);
		cv.put("player_surename", player_surename);
		cv.put("player_position_first", player_position_first);
		cv.put("player_position_second", player_position_second);
		cv.put("player_position_third", player_position_third);

		getWritableDatabase().update("player", cv, "_ID=?", args);
		
	}
	
	public void updatePlayerOnline(String id, String server_player_id, String server_team_id) {
		
		ContentValues cv=new ContentValues();
		String[] args={id};
		  
		if (server_player_id != null) cv.put("server_player_id", Integer.parseInt(server_player_id));
		if (server_team_id != null) cv.put("server_team_id", Integer.parseInt(server_team_id));

		getWritableDatabase().update("player", cv, "_ID=?", args);
		
	}
	
	public void deletePlayer(String id) {
		
		 String[] args={id};
		 getWritableDatabase().delete("player", "_ID=?", args);
		  
	}
	
	public void deletePlayerServerID(String player_id) {
		
		ContentValues cv=new ContentValues();
		String[] args={player_id};
		
		Integer server_player_id = null;
		
		cv.put("server_player_id", server_player_id);
		
		getWritableDatabase().update("player", cv, "_ID=?", args);
		
	}
	
	// Spielerdaten abfragen
		
	public String getPlayerID(Cursor c) {
		return(c.getString(0));
	}
	
	public String getPlayerServerIDByID(String playerID) {
		return(getPlayerString(playerID, 1));
	}
	
	public String getPlayerTeamIDByID(String playerID) {
		return(getPlayerString(playerID, 2));
	}
	
	public String getPlayerServerTeamIDByID(String playerID) {
		String team_id = getPlayerTeamIDByID(playerID);
		return(getTeamServerTeamID(team_id));
	}
	
	public String getPlayerNumberByID(String playerID) {
		return(getPlayerString(playerID, 4));
	}
	
	public String getPlayerForenameByID(String playerID) {
		return(getPlayerString(playerID, 5));
	}
	
	public String getPlayerSurenameByID(String playerID) {
		return(getPlayerString(playerID, 6));
	}
	
	public String getPlayerPositionFirstByID(String playerID) {
		return(getPlayerString(playerID, 7));
	}
	
	public String getPlayerPositionSecondByID(String playerID) {
		return(getPlayerString(playerID, 8));
	}
	
	public String getPlayerPositionThirdByID(String playerID) {
		return(getPlayerString(playerID, 9));
	}
	
	public String getPlayerString(String playerID, Integer number) {
		
		Cursor c = null;
		if (playerID != null) {
			c = getPlayerCursorByID(playerID);
			c.moveToFirst();
			if(c.getCount()>0){
				strResult=c.getString(number);
			} else {
				strResult="";
			}
		}else {
			strResult="";
		}
		
		c.close();
		return(strResult);
		
	}
	
	public String getLastPlayerID() {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM player WHERE _ID=(SELECT MAX(_ID) FROM player)", null);
		c.moveToFirst();
		if(c.getCount()!=0){
			strResult = getClubID(c);
		}
		c.close();
		return(strResult);

	}
	
	public Integer getPlayerGameHomeOrAway(String game_id, String player_id) {
		
		Integer home_or_away = 2;
		String team_id = getPlayerTeamIDByID(player_id);
		String team_home_id = getGameTeamHomeIDByID(game_id);
		String team_away_id = getGameTeamAwayIDByID(game_id);
		
		if (team_id.equals(team_home_id)) home_or_away = 1;
		if (team_id.equals(team_away_id)) home_or_away = 0;

		return(home_or_away);
		
	}
		
	public String getPlayerIDByPlayerNameAndTeamID(String player_forename, String player_surename, String team_id) {
		
		String player_id=null;
		String[] args={player_forename, player_surename, team_id};
		SQLiteDatabase db = getReadableDatabase();
		Cursor c= db.rawQuery("SELECT * FROM player WHERE player_forename=? AND player_surename=? AND team_id=?", args);
		c.moveToFirst();
		if(c.getCount()>0){
			player_id=c.getString(0);
		}
		c.close();
		return(player_id);
		
	}
	
	public String getPlayerPositionNameByID(String player_position_id, Context ctxt) {
		
		String position_name="";
		if (player_position_id != null) {
		if (player_position_id.length() == 4) {
			
			if (player_position_id.substring(2,4).equals("01")) position_name=ctxt.getString(R.string.goalkeeper);
			if (player_position_id.substring(2,4).equals("02")) position_name=ctxt.getString(R.string.left_wing);
			if (player_position_id.substring(2,4).equals("03")) position_name=ctxt.getString(R.string.left_back);
			if (player_position_id.substring(2,4).equals("04")) position_name=ctxt.getString(R.string.center_back);
			if (player_position_id.substring(2,4).equals("05")) position_name=ctxt.getString(R.string.right_back);
			if (player_position_id.substring(2,4).equals("06")) position_name=ctxt.getString(R.string.right_wing);
			if (player_position_id.substring(2,4).equals("07")) position_name=ctxt.getString(R.string.pivot);
			
		}}
		return(position_name);
		
	}
	
	public int countPlayerOfTeam(String teamID) {
		
		Cursor c = getAllPlayerCursorByTeamID(teamID);
		c.moveToFirst();
		int count=c.getCount();
		c.close();
		return(count);
		
	}
	
/* Datenbankabfrage Spiele */

	// Cursor Abfrage
	
	public Cursor getAllGameCursor() {
		
		return(getReadableDatabase().rawQuery("SELECT *  FROM game ORDER BY game_date DESC", null));
		
	}
	
	public Cursor getGameCursorByID(String id) {

		String[] args={id};
		return(getReadableDatabase().rawQuery("SELECT * FROM game WHERE _ID=?", args));
		
	}
	
	// Spieldaten anlegen und löschen
	
	public void insertGame() {
		
		ContentValues cv=new ContentValues();
				    	
	    	// Aktuelles Datum ermitteln
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		Date game_date=new Date(year-1900, month, day);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	String str_game_date = dateFormat.format(game_date);
		  
	    	cv.put("game_date", str_game_date);
	    	cv.put("duration_halftime", 30);
	    	cv.put("possession", 2);
	    	cv.put("current_halftime", 0);
/** TODO -1- => Bei kostenloser App andere Grundeinstellungen bei der Eingabetiefe machen */
	    	// Eingabetiefe einstellen
	    	cv.put("input_player", 1);
	    	cv.put("input_area", 1);
	    	cv.put("input_throwing_technique", 0);
	    	cv.put("input_assist", 0);
	    	cv.put("input_defense", 0);
	    	cv.put("input_mark", 0);
	    	cv.put("input_tech_fault", 1);
	    	cv.put("input_text", 1);

	    	getWritableDatabase().insert("game", "game_date", cv);
		
	}
	
	public void deleteGame(String id) {
		
		String[] args={id};
		getWritableDatabase().delete("game", "_ID=?", args);
		
	}
	
	// Spieldaten aktualisiseren

	public void updateGame(String id, Integer team_home_id, Integer team_away_id, Integer current_halftime, Integer possession, Integer duration_halftime, String game_date, Integer goals_home, Integer goals_away, 
			Long time_sofar, Long time_start, Integer time_ticker, Integer goalie_home, Integer goalie_away, Integer input_player, Integer input_area, Integer input_throwing_technique,Integer input_assist, 
			Integer input_defense, Integer input_mark, Integer input_tech_fault, Integer input_text, String server_game_id) {
			
		ContentValues cv=new ContentValues();
		String[] args={id};
		
		if (team_home_id != null) {cv.put("team_home_id", team_home_id); }
		if (team_away_id != null) {cv.put("team_away_id", team_away_id); }
		if (current_halftime != null) {cv.put("current_halftime", current_halftime); }
		if (possession != null) {cv.put("possession", possession); }
		if (duration_halftime != null) {cv.put("duration_halftime", duration_halftime); }
		if (team_home_id != null) {cv.put("team_home_id", team_home_id); }
		if (game_date != null) {cv.put("game_date", game_date); }
		if (goals_home != null) {cv.put("goals_home", goals_home); }
		if (goals_away != null) {cv.put("goals_away", goals_away); }
		if (time_sofar != null) {cv.put("time_sofar", time_sofar); }
		if (time_start != null) {cv.put("time_start", time_start); }
		if (time_ticker != null) {cv.put("time_ticker", time_ticker); }
		if (goalie_home != null) {cv.put(" gk_home_id",  goalie_home); }
		if (goalie_away != null) {cv.put("gk_away_id", goalie_away); }
		if (input_player != null) {cv.put("input_player", input_player); }
		if (input_area != null) {cv.put("input_area", input_area); }
		if (input_throwing_technique != null) cv.put("input_throwing_technique", input_throwing_technique); 
		if (input_assist != null) cv.put("input_assist", input_assist); 
		if (input_defense != null) cv.put("input_defense", input_defense);
		if (input_mark != null) cv.put("input_mark", input_mark);
		if (input_tech_fault != null) cv.put("input_tech_fault", input_tech_fault);
		if (input_text != null) cv.put("input_text", input_text);
		if (server_game_id != null) cv.put("server_game_id", server_game_id);

		getWritableDatabase().update("game", cv, "_ID=?", args);
			
	}
	
	public void deleteGameServerID(String id) {
		
		ContentValues cv=new ContentValues();
		String[] args={id};
		
		String server_game_id = null;
		
		cv.put("server_game_id", server_game_id);
		
		getWritableDatabase().update("game", cv, "_ID=?", args);
		
	}
	
	public void deleteTickerEventServerIDByGameID(String game_id) {
		
		String[] args={String.valueOf(game_id)};
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM ticker_event WHERE game_id = ? ORDER BY time ASC", args);
		c.moveToFirst();
		
		String ticker_id;

		// Alle Tickermeldungen abfragen und Daten in das Array eintragen eintragen
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			ticker_id = getTickerEventID(c);
			deleteTickerEventServerID(ticker_id);

		}
		
		c.close();
		
	}
	
	public void deleteTickerActivityServerIDByGameID(String game_id) {
		
		String[] args={String.valueOf(game_id)};
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? ORDER BY time ASC", args);
		c.moveToFirst();
		String ticker_id;

		// Alle Tickeraktivitäten abfragen und Daten in das Array eintragen eintragen
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			ticker_id = getTickerActivityID(c);
			deleteTickerActvitiyServerID(ticker_id);
			
		}
		
		c.close();
		
	}
	
	public void deleteTickerEventServerID(String ticker_id) {
		
		ContentValues cv=new ContentValues();
		String[] args={ticker_id};
		
		Integer server_ticker_event_id = null;
		
		cv.put("server_ticker_event_id", server_ticker_event_id);
		
		getWritableDatabase().update("ticker_event", cv, "_ID=?", args);
		
	}
	
	public void deleteTickerActvitiyServerID(String ticker_id) {
		
		ContentValues cv=new ContentValues();
		String[] args={ticker_id};
		
		Integer server_ticker_activity_id = null;
		
		cv.put("server_ticker_activity_id", server_ticker_activity_id);
		
		getWritableDatabase().update("ticker_activity", cv, "_ID=?", args);
		
	}
	
	// Spieldaten abfragen
	
	/*
	"team_home_id INTEGER," +		// 1
	"team_away_id INTEGER, " +		// 2
	"current_halftime INTEGER, " +		// 3
	"possession INTEGER, " +			// 4
	"duration_halftime INTEGER, " +	// 5
	"game_date STRING, " +			// 6
	"goals_home INTEGER, " +			// 7
	"goals_away INTEGER, " +			// 8
	"time_sofar LONG, " +				// 9
	"time_start LONG, " +				// 10
	"time_ticker INTEGER, " +			// 11
	"gk_home_id INTEGER, " +			// 12
	"gk_away_id INTEGER, " +			// 13
	"game_note TEXT, " +					// 14
	"input_player INTEGER DEFAULT 0, " +	// 15
	"input_area INTEGER DEFAULT 0, " +	// 16
	"input_throwing_technique INTEGER DEFAULT 0, " +	// 17
	"input_assist INTEGER DEFAULT 0, " +	// 18
	"input_defense INTEGER DEFAULT 0, " +	// 19
	"input_mark INTEGER DEFAULT 0, " +	// 20
	"input_tech_fault INTEGER DEFAULT 0, " +	// 21
	 "input_text INTEGER DEFAULT 0);");		// 22	
		*/
	
	public String getGameID(Cursor c) {
		return(c.getString(0));
	}
	
	public String getGameTeamHomeIDByID(String game_id) {
		return(getGameString(game_id, 1));
	}
	
	public String getGameTeamHomeByID(String game_id) {
		String id = getGameTeamHomeIDByID(game_id);
		String club_name = null;
		if (id != null) {club_name = getTeamClubNameByID(id);}
		return(club_name);
	}
	
	public String getGameTeamHomeShortByID(String game_id) {
		String id = getGameTeamHomeIDByID(game_id);
		String club_name_short = null;
		if (id != null) {club_name_short = getTeamClubShortByID(id);}
		return(club_name_short);
	}
	
	public String getGameTeamAwayIDByID(String game_id) {
		return(getGameString(game_id, 2));
	}
	
	public String getGameTeamAwayByID(String game_id) {
		String id = getGameTeamAwayIDByID(game_id);
		String club_name = null;
		if (id != null) {club_name = getTeamClubNameByID(id);}
		return(club_name);
	}
	
	public String getGameTeamAwayShortByID(String game_id) {
		String id = getGameTeamAwayIDByID(game_id);
		String club_name_short = null;
		if (id != null) {club_name_short = getTeamClubShortByID(id);}
		return(club_name_short);
	}
	
	public Integer getGameCurrentHalftimeByID(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 3) != null) intValue = Integer.parseInt(getGameString(game_id, 3));
		return(intValue);
	}
	
	public Integer getGamePossession(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 4) != null) intValue = Integer.parseInt(getGameString(game_id, 4));
		return(intValue);
	}
	
	public Integer getGameDurationHalftimeByID(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 5) != null) intValue = Integer.parseInt(getGameString(game_id, 5));
		return(intValue);
	}
	
	public String getGameDateByID(String game_id) {
		return(getGameString(game_id, 6));
	}
	
	public String getGameTimeSoFar(String game_id) {
		return(getGameString(game_id, 9));
	}
	
	public String getGameTimeStart(String game_id) {
		return(getGameString(game_id, 10));
	}
	
	public Integer getGameGKHomeIDByID(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 12) != null) intValue = Integer.parseInt(getGameString(game_id, 12));
		return(intValue);
	}
	
	public Integer getGameGKAwayIDByID(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 13) != null) intValue = Integer.parseInt(getGameString(game_id, 13));
		return(intValue);
	}
	
	public String getGameNote(String game_id) {
		return(getGameString(game_id, 14));
	}
	
	public Integer getGameInputPlayer(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 15) != null) intValue = Integer.parseInt(getGameString(game_id, 15));
		return(intValue);
	}
	
	public Integer getGameInputArea(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 16) != null) intValue = Integer.parseInt(getGameString(game_id, 16));
		return(intValue);
	}
	
	public Integer getGameInputThrowingTechnique(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 17) != null) intValue = Integer.parseInt(getGameString(game_id, 17));
		return(intValue);
	}

	public Integer getGameInputAssist(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 18) != null) intValue = Integer.parseInt(getGameString(game_id, 18));
		return(intValue);
	}
	
	public Integer getGameInputDefense(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 19) != null) intValue = Integer.parseInt(getGameString(game_id, 19));
		return(intValue);
	}
	
	public Integer getGameInputMark(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 20) != null) intValue = Integer.parseInt(getGameString(game_id, 20));
		return(intValue);
	}
	
	public Integer getGameInputTechFault(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 21) != null) intValue = Integer.parseInt(getGameString(game_id, 21));
		return(intValue);
	}
	
	public Integer getGameInputText(String game_id) {
		Integer intValue = null;
		if (getGameString(game_id, 22) != null) intValue = Integer.parseInt(getGameString(game_id, 22));
		return(intValue);
	}
	
	public String getServerGameID(String game_id) {
		return(getGameString(game_id, 23));
	}
	
	public String getGameString(String game_id, Integer number) {
		
		Cursor c=getGameCursorByID(game_id);
		c.moveToFirst();
		if(c.getCount()>0){
			strResult=c.getString(number);
		} else {
			strResult="";
		}
		c.close();
		return(strResult);
		
	}
	
	public String getLastGameID() {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM game WHERE _ID=(SELECT MAX(_ID) FROM game)", null);
		c.moveToFirst();
		strResult = getGameID(c);
		c.close();
		return(strResult);
		
	}
	
	// Schnelles Spiel anlegen
	public void addFastGame(Context ctxt) {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = null;
		
		// Heimmannschaft anlegen
		addFastTeam(ctxt);
		Integer team_home_id = Integer.parseInt(getLastTeamID());
		c = db.rawQuery("SELECT * FROM player WHERE team_id = ? AND player_position_first = ?", new String[] { String.valueOf(team_home_id), "1001" });
		c.moveToFirst();
		Integer gk_home_id = Integer.parseInt(getPlayerID(c));
		
		// Auswärtsmannschaft anlegen
		addFastTeam(ctxt);
		Integer team_away_id = Integer.parseInt(getLastTeamID());
		c = null;
		c = db.rawQuery("SELECT * FROM player WHERE team_id = ? AND player_position_first = ?", new String[] { String.valueOf(team_away_id), "1001" });
		c.moveToFirst();
		Integer gk_away_id = Integer.parseInt(getPlayerID(c));
		
		// Neues Spiel einrichten
		insertGame();
		String game_id = getLastGameID();
		updateGame(game_id, team_home_id, team_away_id, null, null, null, null, null, null, null, null, null, gk_home_id, 
				gk_away_id, null, null, null, null, null, null, null, null, null);
		
		c.close();
		
	}
	
/* Datenbankabfrage Ticker */
	
	// Cursor Abfrage
	
	public Cursor getAllTickerCursor() {
		return(getReadableDatabase()
				.rawQuery("SELECT *  FROM ticker_activity ASC", null));
	}
	
	public Cursor getAllGameTicker(String id) {
		
		String[] args={id};
		return(getReadableDatabase().rawQuery("SELECT * FROM ticker_activity WHERE game_id=? ORDER BY time DESC", args));
		
	}
	
	public Cursor getAllGameTickerEvent(String id) {
		
		String[] args={id, ""};
		return(getReadableDatabase().rawQuery("SELECT * FROM ticker_event WHERE game_id=? AND ticker_event_note != ? ORDER BY time DESC", args));
		
	}
	
	public Cursor getTickerEventCursorByID(String id) {

		String[] args={id};
		return(getReadableDatabase().rawQuery("SELECT * FROM ticker_event WHERE _ID=?", args));
		
	}
	
	public Cursor getTickerActivityCursorByID(String id) {

		String[] args={id};
		return(getReadableDatabase().rawQuery("SELECT * FROM ticker_activity WHERE _ID=?", args));
		
	}	
	
	// Tickerdaten anlegen
	
	public void insertTickerEvent(String game_id, Integer time, String ticker_event_note, String result) {

		ContentValues cv=new ContentValues();
		if (time != null) {
			if (time < 0) time = 0;
		}
	  
		cv.put("game_id", Integer.parseInt(game_id));
		cv.put("time", time);
		cv.put("ticker_event_note", ticker_event_note);
		cv.put("ticker_result", result);

		getWritableDatabase().insert("ticker_event", "time", cv);
		
	}
	
	public void insertTickerActivity(String game_id, Integer ticker_event_id, Integer time, String realtime, Integer activity_id, 
			Integer home_or_away, String player_id, String goal_area, Integer field_position_x, Integer field_position_y, String ticker_activity_note, String activity_string, String result) {

		ContentValues cv=new ContentValues();
		if (time != null) {
			if (time < 0) time = 0;
		}
	  
		cv.put("game_id", Integer.parseInt(game_id));
		cv.put("ticker_event_id", ticker_event_id);
		cv.put("time", time);
		cv.put("realtime", realtime);
		cv.put("activity_id", activity_id);
		cv.put("home_or_away", home_or_away);
		if (player_id != null) cv.put("player_id", Integer.parseInt(player_id));
		cv.put("goal_area", goal_area);
		if (field_position_x != null) cv.put("field_position_x", field_position_x);
		if (field_position_y != null) cv.put("field_position_y", field_position_y);
		cv.put("ticker_activity_note", ticker_activity_note);
		cv.put("activity_string", activity_string);
		cv.put("ticker_result", result);
		cv.put("mark", 3);

		getWritableDatabase().insert("ticker_activity", "ticker_event_id", cv);
		
	}
	
	public void updateTickerActivity(String id, String game_id, Integer ticker_event_id, Integer time, String realtime, Integer activity_id, 
			Integer home_or_away, String player_id, String goal_area, Integer field_position_x, Integer field_position_y, 
			Integer throwing_technique_id, String ticker_activity_note, String activity_string, String result, Integer mark) {
			
		ContentValues cv=new ContentValues();
		String[] args={id};
		
		if (game_id != null) cv.put("game_id", game_id); 
		if (ticker_event_id != null) cv.put("ticker_event_id", ticker_event_id); 
		if (time != null) {
			if (time < 0) time = 0;
			cv.put("time", time); 
		}
		if (realtime != null) cv.put("realtime", realtime); 
		if (activity_id != null) cv.put("activity_id", activity_id); 
		if (home_or_away != null) cv.put("home_or_away", home_or_away); 
		if (player_id != null) cv.put("player_id", Integer.parseInt(player_id));
		if (goal_area != null) cv.put("goal_area", goal_area); 
		if (field_position_x != null) cv.put("field_position_x", field_position_x); 
		if (field_position_y != null) cv.put("field_position_y", field_position_y); 
		if (throwing_technique_id != null) cv.put("throwing_technique_id", throwing_technique_id);
		if (ticker_activity_note != null) cv.put("ticker_activity_note", ticker_activity_note); 
		if (activity_string != null) cv.put("activity_string", activity_string); 
		if (result != null) cv.put("ticker_result", result);
		if (mark != null) cv.put("mark", mark);

		getWritableDatabase().update("ticker_activity", cv, "_ID=?", args);
			
	}
	
	public void updateTickerActivityID(String id, Integer activity_id) {
			
		ContentValues cv=new ContentValues();
		String[] args={id};
		
		if (activity_id != null) cv.put("activity_id", activity_id);

		getWritableDatabase().update("ticker_activity", cv, "_ID=?", args);
			
	}
	
	public void updateTickerEvent(String id, String game_id, Integer time, String ticker_event_note, String result) {

		ContentValues cv=new ContentValues();
		String[] args={id};
	  
		if (game_id != null) cv.put("game_id", Integer.parseInt(game_id));
		if (time != null) {
			if (time < 0) time = 0;
			cv.put("time", time);
		}
		if (ticker_event_note != null) cv.put("ticker_event_note", ticker_event_note);
		if (result != null) cv.put("ticker_result", result);
		
		getWritableDatabase().update("ticker_event", cv, "_ID=?", args);
		
	}
	
	public void updateTickerActivityServerID(String id) {

		ContentValues cv=new ContentValues();
		String[] args={id};
	  
		cv.put("server_ticker_activity_id", 1);
		
		getWritableDatabase().update("ticker_activity", cv, "_ID=?", args);
		
	}
	
	public void updateTickerEventServerID(String id) {

		ContentValues cv=new ContentValues();
		String[] args={id};
	  
		cv.put("server_ticker_event_id", 1);
		
		getWritableDatabase().update("ticker_event", cv, "_ID=?", args);
		
	}
	
	public void deleteTickerActivity(String id) {
		
		String[] args={id};
		getWritableDatabase().delete("ticker_activity", "_ID=?", args);
		
	}
	
	public void deleteTickerEvent(String id) {
		
		String[] args={id};
		getWritableDatabase().delete("ticker_activity", "ticker_event_id=?", args);
		getWritableDatabase().delete("ticker_event", "_ID=?", args);
		
	}
	
	// Tickerdaten abfragen
	
	public String getTickerEventID(Cursor c) {
		return(c.getString(0));
	}
	
	public Integer getTickerEventTimeByID(String ticker_event_id) {
		Integer intValue = null;
		if (getTickerEventString(ticker_event_id, 2) != null) intValue = Integer.parseInt(getTickerEventString(ticker_event_id, 2));
		return(intValue);
	}
	
	public String getTickerEventNoteByID(String ticker_event_id) {
		String result = getTickerEventString(ticker_event_id, 3);
		return(result);
	}
	
	public Integer getTickerServerTickerEventByID(String ticker_event_id) {
		Integer intValue = null;
		if (getTickerEventString(ticker_event_id, 5) != null) intValue = Integer.parseInt(getTickerEventString(ticker_event_id, 5));
		return(intValue);
	}
	
	public String getTickerEventString(String ticker_event_id, Integer number) {
		
		Cursor c = getTickerEventCursorByID(ticker_event_id);
		c.moveToFirst();
		if(c.getCount()>0){
			strResult=c.getString(number);
		} else {
			strResult="";
		}
		c.close();
		return(strResult);
		
	}
	
	public ArrayList getTickerEventMainActivity(String ticker_event_id, Resources res) {
		
		ArrayList<Integer> mainActivityList = new ArrayList<Integer>();
		String ticker_id;
		Integer activity_id = null;
		Integer value = 0;
		activityIDs(res);
		Integer home_or_away = null;
		
		// Durch die Ticker-Aktivitäten des Events gehen
		String[] tickerArgs={ticker_event_id};
		SQLiteDatabase db = getWritableDatabase();
		Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? ORDER BY time ASC", tickerArgs);
		cTicker.moveToFirst();
		
		// Alle Tickermeldungen abfragen und Tickeraktivität ermitteln
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {

			ticker_id = getTickerActivityID(cTicker);
	
			if (getTickerActivityIDByID(ticker_id).equals(defense_error_id) || getTickerActivityIDByID(ticker_id).equals(block_error_id) || 
					getTickerActivityIDByID(ticker_id).equals(defense_success_id) || getTickerActivityIDByID(ticker_id).equals(block_success_id)) {
				if (value < 1) {
					activity_id = defense_error_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 1;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(tech_fault_id) || getTickerActivityIDByID(ticker_id).equals(fehlpass_id) || getTickerActivityIDByID(ticker_id).equals(steps_id) ||
					getTickerActivityIDByID(ticker_id).equals(three_seconds_id) || getTickerActivityIDByID(ticker_id).equals(doppeldribbel_id) ||
					getTickerActivityIDByID(ticker_id).equals(fuss_id) || getTickerActivityIDByID(ticker_id).equals(zeitspiel_id) ||
					getTickerActivityIDByID(ticker_id).equals(kreis_id) || getTickerActivityIDByID(ticker_id).equals(stuermerfoul_id)) {
				if (value < 2) {
					activity_id = tech_fault_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 2;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(yellow_card_id)) {
				if (value < 3) {
					activity_id = yellow_card_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 3;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(two_minutes_id) || getTickerActivityIDByID(ticker_id).equals(twoplustwo_id)) {
				if (value < 4) {
					activity_id = two_minutes_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 4;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(red_card_id)) {
				if (value < 5) {
					activity_id = red_card_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 5;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(timeout_id)) {
				if (value < 1) {
					activity_id = timeout_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 1;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(sub_in_id) || getTickerActivityIDByID(ticker_id).equals(sub_out_id) || getTickerActivityIDByID(ticker_id).equals(two_in_id)) {
				if (value < 1) {
					activity_id = sub_in_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 1;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(tactic_60_id) || getTickerActivityIDByID(ticker_id).equals(tactic_51_id) || getTickerActivityIDByID(ticker_id).equals(tactic_42_id) ||
					getTickerActivityIDByID(ticker_id).equals(tactic_321_id) || getTickerActivityIDByID(ticker_id).equals(tactic_guarding_id)) {
				if (value < 1) {
					activity_id = tactic_60_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 1;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(miss_id) || getTickerActivityIDByID(ticker_id).equals(miss_7m_id) || getTickerActivityIDByID(ticker_id).equals(miss_fb_id)) {
				if (value < 6) {
					activity_id = miss_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 6;
				}
			}
			if (getTickerActivityIDByID(ticker_id).equals(goal_id) || getTickerActivityIDByID(ticker_id).equals(goal_7m_id) ||	getTickerActivityIDByID(ticker_id).equals(goal_fb_id)) {
				if (value < 7) {
					activity_id = goal_id;
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					value = 7;
				}
			}
		}
		
		cTicker.close();
		
		mainActivityList.add(activity_id);
		mainActivityList.add(home_or_away);
		
		return(mainActivityList);
		
	}
	
	/*
	"game_id INTEGER," +			// 1
	"ticker_event_id INTEGER," +	// 2
	"time INTEGER, " +				// 3
	"realtime STRING, " +			// 4
	"activity_id INTEGER, " +		// 5
	"home_or_away INTEGER, " +	// 6
	"player_id INTEGER, " +			// 7
	"goal_area STRING, " +			// 8
	"field_position_x INTEGER, " +	// 9
	"field_position_y INTEGER, " +	// 10
	"throwing_technique_id INTEGER, " +	// 11
	"ticker_activity_note TEXT, " +	// 12
	"activity_string STRING, " +		// 13
	"ticker_result STRING," +		// 14
	"mark INTEGER, " +				// 15
	"server_ticker_activity_id INTEGER);");	// 16);");
	*/
	
	public String getTickerActivityID(Cursor c) {
		return(c.getString(0));
	}
	
	public Integer getTickerGameIDByActivityID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 1) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 1));
		return(intValue);
	}
	
	public Integer getTickerEventIDByActivityID(String ticker_activity_id) {
		Integer intValue = null;
		String strActivity = null;
		if (getTickerActivityString(ticker_activity_id, 2) != null) strActivity = getTickerActivityString(ticker_activity_id, 2);
		if (!strActivity.equals("")) intValue = Integer.parseInt(strActivity);
		return(intValue);
	}
	
	public Integer getTickerTimeByActivityID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 3) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 3));
		return(intValue);
	}
	
	public String getTickerRealtimeByActivityID(String ticker_activity_id) {
		return(getTickerActivityString(ticker_activity_id, 4));
	}
	
	public Integer getTickerActivityIDByID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 5) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 5));
		return(intValue);
	}
	
	public Integer getTickerHomeOrAwayByID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 6) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 6));
		return(intValue);
	}
	
	public String getTickerPlayerIDByID(String ticker_activity_id) {
		Integer intValue = null;
		String player_id = null;
		if (getTickerActivityString(ticker_activity_id, 7) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 7));
		if (intValue != null) player_id = String.valueOf(intValue);
		return(player_id);
	}
	
	public String getTickerAreaByID(String ticker_activity_id) {
		return(getTickerActivityString(ticker_activity_id, 8));
	}
	
	public Integer getTickerFieldPositionXByID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 9) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 9));
		return(intValue);
	}
	
	public Integer getTickerFieldPositionYByID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 10) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 10));
		return(intValue);
	}
	
	public Integer getTickerThrowingTechniqueIDByID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 11) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 11));
		return(intValue);
	}
	
	public String getTickerActivityNoteByID(String ticker_activity_id) {
		String result = getTickerActivityString(ticker_activity_id, 12);
		return(result);
	}
	
	public String getTickerActivityStringByID(String ticker_activity_id) {
		String result = getTickerActivityString(ticker_activity_id, 13);
		return(result);
	}
	
	public String getTickerResultByID(String ticker_activity_id) {
		String result = getTickerActivityString(ticker_activity_id, 14);
		return(result);
	}
	
	public Integer getTickerMarkByID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 15) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 15));
		return(intValue);
	}
	
	public Integer getTickerServerTickerActivityByID(String ticker_activity_id) {
		Integer intValue = null;
		if (getTickerActivityString(ticker_activity_id, 16) != null) intValue = Integer.parseInt(getTickerActivityString(ticker_activity_id, 16));
		return(intValue);
	}
	
	public String getTickerActivityString(String ticker_activity_id, Integer number) {
		
		Cursor c = getTickerActivityCursorByID(ticker_activity_id);
		c.moveToFirst();
		if(c.getCount()>0){
			strResult=c.getString(number);
		} else {
			strResult="";
		}
		c.close();
		return(strResult);
		
	}
	
	public String getClubNameByTickerEventAndHomeOrAway(String ticker_id, Integer home_or_away) {
		
		Integer game_id = getTickerGameIDByActivityID(ticker_id);
		
		strResult = "";
		
		if (home_or_away.equals(1)) strResult = getGameTeamHomeByID(String.valueOf(game_id));
		if (home_or_away.equals(0)) strResult = getGameTeamAwayByID(String.valueOf(game_id));
		
		return(strResult);
		
	}
	
	public int count_ticker_goals(String game_id, String player_id, Integer home_or_away, Integer time) {
		
		SQLiteDatabase db = getReadableDatabase();
		if (time == null) time = 9999999;						// Zeit auf Ende stellen, falls null
		Cursor c = null;
		
		if (player_id != null) {
			
			c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ? " + 
					"AND player_id = ? "  + 
					"AND time <= ? " +
					"AND activity_id < 10150 " +
					"AND activity_id != 10099 ", 
					new String[] { game_id, 
									String.valueOf(player_id), 
									String.valueOf(time) });
			
		} else {
			
			c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ? " + 
					"AND home_or_away = ? "  + 
					"AND time <= ? " +
					"AND activity_id < 10150 " +
					"AND activity_id != 10099 ", 
					new String[] { game_id, 
									String.valueOf(home_or_away), 
									String.valueOf(time) });

		}
		
		c.moveToFirst();
		int count= c.getInt(0);
		c.close();
		return(count);
		
	}
	
	public int count_ticker_goal_attempts(String game_id, String player_id, Integer home_or_away, Integer time) {
		
		SQLiteDatabase db = getReadableDatabase();
		if (time == null) time = 9999999;						// Zeit auf Ende stellen, falls null
		Cursor c = null;
		
		if (player_id != null) {
			c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ? " + 
					"AND player_id = ? "  + 
					"AND time <= ? " +
					"AND activity_id < 10160 " +
					"AND activity_id != 10099 ", 
					new String[] { game_id, 
									String.valueOf(player_id), 
									String.valueOf(time) });
		} else {
			c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ? " + 
					"AND home_or_away = ? "  + 
					"AND time <= ? " +
					"AND activity_id < 10160 " +
					"AND activity_id != 10099 ", 
					new String[] { game_id, 
									String.valueOf(home_or_away), 
									String.valueOf(time) });
		}

		/*
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			String ticker_id = getTickerActivityID(c);
			Integer intTime = getTickerTimeByActivityID(ticker_id);
			Integer activity = getTickerActivityIDByID(ticker_id);
			String player = getTickerPlayerIDByID(ticker_id);
			
		}
		*/
		
		c.moveToFirst();
		int count= c.getInt(0);
		c.close();
		return(count);
		
	}

	public int count_ticker_activity(String game_id, Integer home_or_away, String player_id, Integer activity_id, Integer throwing_technique_id, Integer time) {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = null;
		if (time == null) time = 9999999;						// Zeit auf Ende stellen, falls null
		
		if (player_id != null) {
			
			if (activity_id != null) {
			
				if (throwing_technique_id != null) {
					
					c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ? " + 
							"AND time <= ? " +
							"AND player_id = ? " + 
							"AND activity_id = ? " +
							"AND throwing_technique_id = ? ", 
							new String[] { game_id, 
											String.valueOf(time), 
											player_id, 
											String.valueOf(activity_id),
											String.valueOf(throwing_technique_id)});
					
				} else {
					
					c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ? " + 
							"AND time <= ? " +
							"AND player_id = ? " + 
							"AND activity_id = ? ", 
							new String[] { game_id, 
											String.valueOf(time), 
											player_id, 
											String.valueOf(activity_id)});
					
				}
				
			} else {
				
				if (game_id == null && home_or_away == null && activity_id == null && throwing_technique_id == null) {
					
					c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE player_id = ?", new String[] { player_id });
					
				} else {
					
					c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ? " + 
							"AND time <= ? " +
							"AND player_id = ? ", 
							new String[] { game_id, 
											String.valueOf(time), 
											player_id});
					
				}				
			}
			
		} else {
			
			if (activity_id != null) {
				
				c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ?" + 
						"AND home_or_away = ? " + 
						"AND time <= ? " +
						"AND activity_id = ? ", 
						new String[] { game_id, 
										String.valueOf(home_or_away), 
										String.valueOf(time), 
										String.valueOf(activity_id)});
				
			} else {
				
				if (home_or_away != null) {
					
					c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ?" + 
							"AND home_or_away = ? " + 
							"AND time <= ? ", 
							new String[] { game_id, 
											String.valueOf(home_or_away), 
											String.valueOf(time)});
					
				}				
			}
		}
		
		if (home_or_away == null && player_id == null &&  activity_id == null &&  throwing_technique_id == null) {
			
			c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE game_id = ?", new String[] { game_id });
			
		}

		c.moveToFirst();
		int count= c.getInt(0);
		c.close();
		return(count);
		
	}
	
	public Integer count_ticker_event(Integer ticker_event_id, Integer activity_id) {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT count(*) FROM ticker_activity WHERE ticker_event_id = ? " + 
				"AND activity_id = ? ", 
				new String[] { String.valueOf(ticker_event_id), String.valueOf(activity_id)});
		c.moveToFirst();
		int count= c.getInt(0);
		c.close();
		return(count);
		
	}
	
	public int count_ticker_player_time(String game_id, String player_id, Integer home_or_away, Resources res) {

		String[] strGameID={game_id, String.valueOf(home_or_away)};
		SQLiteDatabase db = getWritableDatabase();
		String ticker_id = null;
		Integer ticker_event_id = null;
		Integer last_ticker_event_id = -1;
		Integer status = 0;
		Integer time_in = 0;
		Integer duration = getGameDurationHalftimeByID(game_id);
		int time = 0;
		activityIDs(res);
		
		Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND home_or_away = ? ORDER BY time ASC", strGameID);
		cTicker.moveToFirst();
				
		// Alle Tickermeldungen abfragen
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
			
			ticker_id = getTickerActivityID(cTicker);
			ticker_event_id = getTickerEventIDByActivityID(String.valueOf(ticker_id));
			
			// Überprüfen, ob es eine Aktion des Spielers ist
			String ticker_player = "";
			if (getTickerPlayerIDByID(ticker_id) != null) ticker_player = getTickerPlayerIDByID(ticker_id);
			if (ticker_player.equals(player_id)) {
				
				// Wurde Spieler eingewechselt?
				if (getTickerActivityIDByID(ticker_id).equals(sub_in_id) ||
						getTickerActivityIDByID(ticker_id).equals(two_in_id)) {
					
					if (status.equals(0)) time_in = getTickerTimeByActivityID(ticker_id);
					status = 1;
					
				}
				
				// Wurde Spieler ausgewechselt?
				if (getTickerActivityIDByID(ticker_id).equals(sub_out_id) || 
						getTickerActivityIDByID(ticker_id).equals(two_minutes_id) ||
						getTickerActivityIDByID(ticker_id).equals(twoplustwo_id) ||
						getTickerActivityIDByID(ticker_id).equals(red_card_id)) {
					if (status.equals(1)) {
						time = time + getTickerTimeByActivityID(ticker_id) - time_in;
						status = 0;
					}
				}
				
			} else {

				// Falls es zwar keine Aktion des Spielers ist, aber es eine Einwechselung
				// ist und der zu prüfende Spieler gerade eingewechselt ist, prüfe, ob 
				// es sich um eine Mannschaftsaufstellung mit sieben Spielern handelt 
				// und ob das Ticker Ereignis noch nicht abgefragt wurde
				// Falls ja: Wechsel den Spieler aus.
				if (getTickerActivityIDByID(ticker_id).equals(sub_in_id) && status.equals(1) && !ticker_event_id.equals(last_ticker_event_id)) {
					
					// Überprüfe, ob es zu dem Ticker Event sieben Einwechslungen gibt
					if (count_ticker_event(ticker_event_id, getTickerActivityIDByID(ticker_id)).equals(7)) {
						
						// Überprüfe, ob der Spieler auch aufgestellt wurde
						Boolean in = false;
						String[] strTickerID={String.valueOf(ticker_event_id), String.valueOf(getTickerActivityIDByID(ticker_id))};
						Cursor cTickerEvent = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? AND activity_id = ?", strTickerID);
						cTickerEvent.moveToFirst();
						
						// Alle Tickermeldungen des Events abfragen und mit der Spieler ID vergleichen
						for (cTickerEvent.moveToFirst(); !cTickerEvent.isAfterLast(); cTickerEvent.moveToNext()) {
							
							String ticker_lineup_id = getTickerActivityID(cTickerEvent);
							
							if (getTickerPlayerIDByID(ticker_lineup_id) != null) {
								if (getTickerPlayerIDByID(ticker_lineup_id).equals(player_id)) {
									in = true;
								}
							}
						}
						cTickerEvent.close();
						
						// Ticker Event ID speichern, damit diese nicht noch einmal abgefragt wird
						last_ticker_event_id = ticker_event_id;
						
						// Falls nicht, also falls sieben Spieler eingewechselt wurden und der 
						// zu prüfende Spieler nicht dabei war, wurde der zu prüfende 
						// Spieler ausgewechselt.
						if (in == false) {
							
							time = time + getTickerTimeByActivityID(ticker_id) - time_in;
							status = 0;
							
						}						
					}
				}
			}
		}
		cTicker.close();
		
		// Falls der Spieler auch am Ende des Spiels noch eingewechselt war, 
		// addiere die restliche Spielzeit zur Gesamtspielzeit.
		if (status.equals(1)) {
			
			time = time + (duration * 120000) - time_in;
			
		}
		
		return(time);
		
	}
	
	public int count_ticker_player_plusminus(String game_id, String player_id, Resources res) {

		String[] strGameID={game_id};
		SQLiteDatabase db = getWritableDatabase();
		String ticker_id = null;
		Integer ticker_event_id = null;
		Integer status = 0;
		Integer time_in = 0;
		Integer duration = getGameDurationHalftimeByID(game_id);
		Integer home_or_away = null;
		Integer player_home_or_away = getPlayerGameHomeOrAway(game_id, player_id);
		Boolean in = false;
		int time = 0;
		int plus_minus = 0;
		activityIDs(res);
		
		Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ?", strGameID);
		cTicker.moveToFirst();
				
		// Alle Tickermeldungen abfragen und Tor eintragen
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
			
			ticker_id = getTickerActivityID(cTicker);
			ticker_event_id = getTickerEventIDByActivityID(String.valueOf(ticker_id));
			
			// Überprüfen, ob der Spieler aktiv oder inaktiv ist
			// Überprüfen, ob es eine Aktion des Spielers ist
			String ticker_player = "";
			if (getTickerPlayerIDByID(ticker_id) != null) ticker_player = getTickerPlayerIDByID(ticker_id);
			if (ticker_player.equals(player_id)) {
				
				// Wurde Spieler eingewechselt?
				if (getTickerActivityIDByID(ticker_id).equals(sub_in_id) ||
						getTickerActivityIDByID(ticker_id).equals(two_in_id)) {
					status = 1;
				}
				
				// Wurde Spieler ausgewechselt?
				if (getTickerActivityIDByID(ticker_id).equals(sub_out_id)) {
					status = 0;
				}
				
			} else {
				
				// Falls es zwar keine Aktion des Spielers ist, aber es eine Einwechselung
				// ist und der zu prüfende Spieler gerade eingewechselt ist, prüfe, ob 
				// es sich um eine Mannschaftsaufstellung mit sieben Spielern handelt 
				// Falls ja: Wechsel den Spieler aus.
				if (getTickerActivityIDByID(ticker_id).equals(sub_in_id) &&
						status.equals(1)) {
					
					// Überprüfe, ob es zu dem Ticker Event sieben Einwechslungen gibt
					if (count_ticker_event(ticker_event_id, getTickerActivityIDByID(ticker_id)) == 7) {
						
						// Überprüfe, ob der Spieler auch aufgestellt wurde
						in = false;
						String[] strTickerID={String.valueOf(ticker_event_id), String.valueOf(getTickerActivityIDByID(ticker_id))};
						Cursor cTickerEvent = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? AND activity_id = ?", strTickerID);
						cTickerEvent.moveToFirst();
						
						// Alle Tickermeldungen des Events abfragen und mit der Spieler ID vergleichen
						for (cTickerEvent.moveToFirst(); !cTickerEvent.isAfterLast(); cTickerEvent.moveToNext()) {
							
							String ticker_lineup_id = getTickerActivityID(cTickerEvent);
							if (getTickerPlayerIDByID(ticker_lineup_id) != null) {
								if (getTickerPlayerIDByID(ticker_lineup_id).equals(player_id)) {
									in = true;
								}
							}
							
						}
						cTickerEvent.close();
						
						// Falls nicht, also falls sieben Spieler eingewechselt wurden und der 
						// zu prüfende Spieler nicht dabei war, wurde der zu prüfende 
						// Spieler ausgewechselt.
						if (in == false) {
							
							status = 0;
							
						}	
					}
				}
			}
			
			// Falls ein Tor geworfen wurde und Spieler aktiv war, ändere Plus / Minus Statistik
			if (getTickerActivityIDByID(ticker_id).equals(goal_id) ||
					getTickerActivityIDByID(ticker_id).equals(goal_7m_id) ||
					getTickerActivityIDByID(ticker_id).equals(goal_fb_id)) {
				
				if (status.equals(1)) {
					
					home_or_away = getTickerHomeOrAwayByID(ticker_id);
					if (home_or_away.equals(player_home_or_away)) {
						plus_minus = plus_minus + 1;
					} else {
						plus_minus = plus_minus - 1;
					}
				}
			}
		}
		
		return(plus_minus);
		
	}
	
	public Integer getLastTickerEventID() {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM ticker_event WHERE _ID=(SELECT MAX(_ID) FROM ticker_event)", null);
		c.moveToFirst();
		if(c.getCount()!=0){
			strResult = getClubID(c);
		}
		c.close();
		return(Integer.parseInt(strResult));

	}
	
	public Integer getLastTickerActivityID() {
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM ticker_activity WHERE _ID=(SELECT MAX(_ID) FROM ticker_activity)", null);
		c.moveToFirst();
		if(c.getCount()!=0){
			strResult = getClubID(c);
		}
		c.close();
		return(Integer.parseInt(strResult));

	}
	
	public Integer maxTickerTime(String game_id) {
		
		String[] args = {game_id};
		SQLiteDatabase db = getReadableDatabase();
		Cursor mTime = db.rawQuery("SELECT * FROM ticker_activity WHERE time = (SELECT MAX(time) FROM ticker_activity) " +
				"AND game_id = ?", args);
		mTime.moveToFirst();
		int maxTime = 0;
		if (mTime.getCount()>0) {
			maxTime = mTime.getInt(3);  
		} 
		mTime.close();
		return(maxTime);

	}
	
	public void changePossession(String game_id, Integer possession, Integer ticker_event_id, Integer time, String realtime, String result, Resources res) {
		
		String team_home_short = getGameTeamHomeShortByID(game_id);
		String team_away_short = getGameTeamAwayShortByID(game_id);
		String ticker_text = null;

		if (possession.equals(1)) ticker_text = res.getString(R.string.possession) + " " + team_home_short;
		if (possession.equals(0)) ticker_text = res.getString(R.string.possession) + " " + team_away_short;
			
		// Ballbesitzwechsel in Datenbank eintragen
		insertTickerActivity(game_id, ticker_event_id, time, realtime, res.getInteger(R.integer.possession_id), 
				possession, null, null, null, null, null, ticker_text, result);
		updateGame(game_id, null, null, null, possession, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

	}
	
	public String getGameCurrentResultString(String game_id) {
		
		String result = count_ticker_goals(game_id, null, 1, null) + ":" + count_ticker_goals(game_id, null, 0, null);
		return(result);
		
	}
	
	public String getActivityStringByActivityID(Integer id, Resources res) {
		
		String activity = null;
		if (id != null) {
			
			if (id == res.getInteger(R.integer.possession_id) ) activity = res.getString(R.string.possession);
			if (id == res.getInteger(R.integer.goal_id) ) activity = res.getString(R.string.goal);
			if (id == res.getInteger(R.integer.goal_7m_id) ) activity = res.getString(R.string.seven_goal);
			if (id == res.getInteger(R.integer.goal_fb_id) ) activity = res.getString(R.string.fb_goal);
			if (id == res.getInteger(R.integer.miss_id) ) activity = res.getString(R.string.miss);
			if (id == res.getInteger(R.integer.miss_7m_id) ) activity = res.getString(R.string.seven_miss);
			if (id == res.getInteger(R.integer.miss_fb_id) ) activity = res.getString(R.string.fb_miss);
			if (id == res.getInteger(R.integer.save_id) ) activity = res.getString(R.string.gk_save);
			if (id == res.getInteger(R.integer.save_7m_id) ) activity = res.getString(R.string.seven_save);
			if (id == res.getInteger(R.integer.save_fb_id) ) activity = res.getString(R.string.fb_save);
			if (id == res.getInteger(R.integer.goal_against_id) ) activity = res.getString(R.string.goalkeeper_goal_against);
			if (id == res.getInteger(R.integer.goal_against_7m_id) ) activity = res.getString(R.string.seven_goal_against);
			if (id == res.getInteger(R.integer.goal_against_fb_id) ) activity = res.getString(R.string.fb_goal_against);
			if (id == res.getInteger(R.integer.assist_goal_id) ) activity = res.getString(R.string.assist_goal);
			if (id == res.getInteger(R.integer.assist_miss_id) ) activity = res.getString(R.string.assist);
			if (id == res.getInteger(R.integer.defense_error_id) ) activity = res.getString(R.string.defensive_error);
			if (id == res.getInteger(R.integer.defense_success_id) ) activity = res.getString(R.string.defense_successful);
			if (id == res.getInteger(R.integer.block_error_id) ) activity = res.getString(R.string.block_error);
			if (id == res.getInteger(R.integer.block_success_id) ) activity = res.getString(R.string.block_successful);
			if (id == res.getInteger(R.integer.foul_id) ) activity = res.getString(R.string.foul);
			if (id == res.getInteger(R.integer.tech_fault_id) ) activity = res.getString(R.string.tech_fault);
			if (id == res.getInteger(R.integer.fehlpass_id) ) activity = res.getString(R.string.Fehlpass);
			if (id == res.getInteger(R.integer.steps_id) ) activity = res.getString(R.string.steps);
			if (id == res.getInteger(R.integer.three_seconds_id) ) activity = res.getString(R.string.three_seconds_rule);
			if (id == res.getInteger(R.integer.doppeldribbel_id) ) activity = res.getString(R.string.Doppeldribbel);
			if (id == res.getInteger(R.integer.fuss_id) ) activity = res.getString(R.string.Fuss);
			if (id == res.getInteger(R.integer.zeitspiel_id) ) activity = res.getString(R.string.Zeitspiel);
			if (id == res.getInteger(R.integer.kreis_id) ) activity = res.getString(R.string.Kreis);
			if (id == res.getInteger(R.integer.stuermerfoul_id) ) activity = res.getString(R.string.Stuermerfoul);
			if (id == res.getInteger(R.integer.yellow_card_id) ) activity = res.getString(R.string.yellow_card);
			if (id == res.getInteger(R.integer.two_minutes_id) ) activity = res.getString(R.string.two_minutes);
			if (id == res.getInteger(R.integer.twoplustwo_id) ) activity = res.getString(R.string.two_plus_two);
			if (id == res.getInteger(R.integer.red_card_id) ) activity = res.getString(R.string.red_card);
			if (id == res.getInteger(R.integer.lineup_id) ) activity = res.getString(R.string.lineup);
			if (id == res.getInteger(R.integer.sub_in_id) ) activity = res.getString(R.string.sub_in);
			if (id == res.getInteger(R.integer.sub_out_id) ) activity = res.getString(R.string.sub_out);
			if (id == res.getInteger(R.integer.two_in_id) ) activity = res.getString(R.string.suspension_end);
			if (id == res.getInteger(R.integer.timeout_id) ) activity = res.getString(R.string.timeout);
			if (id == res.getInteger(R.integer.tactic_60_id) ) activity = res.getString(R.string.tactic_60);
			if (id == res.getInteger(R.integer.tactic_51_id) ) activity = res.getString(R.string.tactic_51);
			if (id == res.getInteger(R.integer.tactic_42_id) ) activity = res.getString(R.string.tactic_42);
			if (id == res.getInteger(R.integer.tactic_321_id) ) activity = res.getString(R.string.tactic_321);
			if (id == res.getInteger(R.integer.tactic_guarding_id) ) activity = res.getString(R.string.guarding);
			if (id == res.getInteger(R.integer.timeout_id) ) activity = res.getString(R.string.timeout);

		}

		return(activity);
		
	}
	
/* Statistik berechnen */
	
	
	public ArrayList statPlayer(String game_id, String player_id, Integer home_or_away, Resources res) {
		
		ArrayList<Integer> statPlayerList = new ArrayList<Integer>();
		activityIDs(res);
		
		int goals = 0;
		int attempts = 0;
		int count_ticker = 0;

/* Wurfstatistik */
		
		// 0-2 => Torwürfe Gesamt
		goals = count_ticker_goals(game_id, player_id, null, null);		// Gesamttore
		statPlayerList.add(goals);
		attempts = count_ticker_goal_attempts(game_id, player_id, null, null);// Gesamtchancen
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Gesamtquote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 3-5 => Torwürfe - 7m
		goals = count_ticker_activity(game_id, null, player_id, goal_7m_id, null, null);// 7m-Tore
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, goal_7m_id, null, null) + 
				count_ticker_activity(game_id, null, player_id, miss_7m_id, null, null);// 7m-Chancen
		statPlayerList.add(attempts);
		if (attempts > 0) {												// 7m-Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 6-8 => Torwürfe - Tempogegenstoss 
		goals = count_ticker_activity(game_id, null, player_id, goal_fb_id, null, null);// TG-Tore
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, goal_fb_id, null, null) + 
				count_ticker_activity(game_id, null, player_id, miss_fb_id, null, null);// TG-Chancen
		statPlayerList.add(attempts);
		if (attempts > 0) {												// TG-Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 9-11 => Torwürfe - Sprungwurf 
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Sprungwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Sprungwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Sprungwurf_id, null);// Sprungwurf-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Sprungwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Sprungwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Sprungwurf_id, null);// Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Sprungwurf Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 12-14 => Torwürfe - Schlagwurf
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Schlagwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Schlagwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Schlagwurf_id, null);// Schlagwurf-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Schlagwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Schlagwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Schlagwurf_id, null);// Schlagwurf Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Schlagwurf Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 15-17 => Torwürfe - Laufwurf
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Laufwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Laufwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Laufwurf_id, null);// Laufwurf-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Laufwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Laufwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Laufwurf_id, null);// Laufwurf Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Laufwurf Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 18-20 => Torwürfe - Fallwurf
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Fallwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Fallwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Fallwurf_id, null);// Fallwurf-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Fallwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Fallwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Fallwurf_id, null);// Fallwurf Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Fallwurf Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 21-23 => Torwürfe - Hüftwurf
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Hueftwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Hueftwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Hueftwurf_id, null);// Hüftwurf-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Hueftwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Hueftwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Hueftwurf_id, null);// Hüftwurf Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Hüftwurf Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 24-26 => Torwürfe - Kempa
		goals = count_ticker_activity(game_id, null, player_id, goal_id, kempa_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, kempa_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, kempa_id, null);// Kempa-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, kempa_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, kempa_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, kempa_id, null);// Kempa Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Kempa Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 27-29 => Torwürfe - Dreher
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Dreher_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Dreher_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Dreher_id, null);// Dreher-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Dreher_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Dreher_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Dreher_id, null);// Dreher Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Dreher Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 30-32 => Torwürfe - Heber
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Heber_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Heber_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Heber_id, null);// Heber-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Heber_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Heber_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Heber_id, null);// Heber Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Heber Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 33-35 => Torwürfe - Leger
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Leger_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Leger_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Leger_id, null);// Leger-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Leger_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Leger_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Leger_id, null);// Leger Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Leger Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 36-38 => Torwürfe - Abknickwurf
		goals = count_ticker_activity(game_id, null, player_id, goal_id, Abknickwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_7m_id, Abknickwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, goal_fb_id, Abknickwurf_id, null);// Abknickwurf-Tore gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, miss_id, Abknickwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_7m_id, Abknickwurf_id, null) +
				count_ticker_activity(game_id, null, player_id, miss_fb_id, Abknickwurf_id, null);// Abknickwurf Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Abknickwurf Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}

/* Torwartstatistik */
		
		// 39-41 => Torwartstatistik - Gesamt
		goals = count_ticker_activity(game_id, null, player_id, save_id, null, null) +
				count_ticker_activity(game_id, null, player_id, save_7m_id, null, null) +
				count_ticker_activity(game_id, null, player_id, save_fb_id, null, null);// Gesamt gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, goal_against_id, null, null) +
				count_ticker_activity(game_id, null, player_id, goal_against_7m_id, null, null) +
				count_ticker_activity(game_id, null, player_id, goal_against_fb_id, null, null) +
				count_ticker_activity(game_id, null, player_id, save_id, null, null) +
				count_ticker_activity(game_id, null, player_id, save_7m_id, null, null) +
				count_ticker_activity(game_id, null, player_id, save_fb_id, null, null);// Gesamt Würfe aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Gesamt Torwart Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 42-44 => Torwartstatistik - 7m
		goals = count_ticker_activity(game_id, null, player_id, save_7m_id, null, null);// 7m gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, save_7m_id, null, null) +
				count_ticker_activity(game_id, null, player_id, goal_against_7m_id, null, null);// 7m aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Gesamt Torwart Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		// 45-47 => Torwartstatistik - Tempogegenstoss
		goals = count_ticker_activity(game_id, null, player_id, save_fb_id, null, null);// Tempogegenstoss gehalten
		statPlayerList.add(goals);
		attempts = count_ticker_activity(game_id, null, player_id, save_fb_id, null, null) +
				count_ticker_activity(game_id, null, player_id, goal_against_fb_id, null, null);// Tempogegenstoss aufs Tor
		statPlayerList.add(attempts);
		if (attempts > 0) {												// Gesamt Torwart Quote
			statPlayerList.add(goals * 100 / attempts);
		} else {
			statPlayerList.add(0);
		}
		
/* Vorlage */
		
		// 48 => Torvorlage
		count_ticker = count_ticker_activity(game_id, null, player_id, assist_goal_id, null, null);
		statPlayerList.add(count_ticker);
		// 49 => Vorlage (Fehlwurf)
		count_ticker = count_ticker_activity(game_id, null, player_id, assist_miss_id, null, null);
		statPlayerList.add(count_ticker);
		
/* Abwehr */
		
		// 50 => Erfolgreiche Abwehr
		count_ticker = count_ticker_activity(game_id, null, player_id, defense_success_id, null, null);
		statPlayerList.add(count_ticker);
		// 51 => Erfolgslose Abwehr
		count_ticker = count_ticker_activity(game_id, null, player_id, defense_error_id, null, null);
		statPlayerList.add(count_ticker);
		// 52 => Erfolgreicher Block
		count_ticker = count_ticker_activity(game_id, null, player_id, block_success_id, null, null);
		statPlayerList.add(count_ticker);
		// 53 => Erfolgsloser Block
		count_ticker = count_ticker_activity(game_id, null, player_id, block_error_id, null, null);
		statPlayerList.add(count_ticker);
		// 54 => Fouls
		count_ticker = count_ticker_activity(game_id, null, player_id, foul_id, null, null);
		statPlayerList.add(count_ticker);
		
/* Technische Fehler */
		
		// 55 => Technische Fehler gesamt
		count_ticker = count_ticker_activity(game_id, null, player_id, tech_fault_id, null, null) +
				count_ticker_activity(game_id, null, player_id, fehlpass_id, null, null) +
				count_ticker_activity(game_id, null, player_id, steps_id, null, null) +
				count_ticker_activity(game_id, null, player_id, three_seconds_id, null, null) +
				count_ticker_activity(game_id, null, player_id, doppeldribbel_id, null, null) +
				count_ticker_activity(game_id, null, player_id, fuss_id, null, null) +
				count_ticker_activity(game_id, null, player_id, zeitspiel_id, null, null) +
				count_ticker_activity(game_id, null, player_id, kreis_id, null, null) +
				count_ticker_activity(game_id, null, player_id, stuermerfoul_id, null, null);
		statPlayerList.add(count_ticker);
		// 56 => davon Fehlpass
		count_ticker = count_ticker_activity(game_id, null, player_id, fehlpass_id, null, null);
		statPlayerList.add(count_ticker);
		// 57 => davon Schritte
		count_ticker = count_ticker_activity(game_id, null, player_id, steps_id, null, null);
		statPlayerList.add(count_ticker);
		// 58 => davon 3-Sekunden-Regel
		count_ticker = count_ticker_activity(game_id, null, player_id, three_seconds_id, null, null);
		statPlayerList.add(count_ticker);
		// 59 => davon Doppeldribbel
		count_ticker = count_ticker_activity(game_id, null, player_id, doppeldribbel_id, null, null);
		statPlayerList.add(count_ticker);
		// 60 => davon Fuss
		count_ticker = count_ticker_activity(game_id, null, player_id, fuss_id, null, null);
		statPlayerList.add(count_ticker);
		// 61 => davon Zeitspiel
		count_ticker = count_ticker_activity(game_id, null, player_id, zeitspiel_id, null, null);
		statPlayerList.add(count_ticker);
		// 62 => davon Kreis
		count_ticker = count_ticker_activity(game_id, null, player_id, kreis_id, null, null);
		statPlayerList.add(count_ticker);
		// 63 => davon Stürmerfoul
		count_ticker = count_ticker_activity(game_id, null, player_id, stuermerfoul_id, null, null);
		statPlayerList.add(count_ticker);
		
/* Strafen */
		
		// 64 => Gelbe Karte
		count_ticker = count_ticker_activity(game_id, null, player_id, yellow_card_id, null, null);
		statPlayerList.add(count_ticker);
		// 65 => Zwei Minuten
		count_ticker = count_ticker_activity(game_id, null, player_id, two_minutes_id, null, null);
		statPlayerList.add(count_ticker);
		// 66 => Zwei mal zwei Minuten
		count_ticker = count_ticker_activity(game_id, null, player_id, twoplustwo_id, null, null);
		statPlayerList.add(count_ticker);
		// 67 => Rote Karte
		count_ticker = count_ticker_activity(game_id, null, player_id, red_card_id, null, null);
		statPlayerList.add(count_ticker);
		
/* Spielzeit */
		
		// 68 => Spielzeit des Spielers
		count_ticker = count_ticker_player_time(game_id, player_id, home_or_away, res);
		statPlayerList.add(count_ticker);
		// 69 => +/- Statistik des Spielers 
		count_ticker = count_ticker_player_plusminus(game_id, player_id, res);
		statPlayerList.add(count_ticker);
		
		return(statPlayerList);
		
	}

	public void activityIDs(Resources res) {
		
		possession_id = res.getInteger(R.integer.possession_id);
		goal_id = res.getInteger(R.integer.goal_id);
		goal_7m_id = res.getInteger(R.integer.goal_7m_id);
		goal_fb_id = res.getInteger(R.integer.goal_fb_id);
		miss_id = res.getInteger(R.integer.miss_id);
		miss_7m_id = res.getInteger(R.integer.miss_7m_id);
		miss_fb_id = res.getInteger(R.integer.miss_fb_id);
		assist_goal_id = res.getInteger(R.integer.assist_goal_id);
		assist_miss_id = res.getInteger(R.integer.assist_miss_id);
		defense_error_id = res.getInteger(R.integer.defense_error_id);
		defense_success_id = res.getInteger(R.integer.defense_success_id);
		block_error_id = res.getInteger(R.integer.block_error_id);
		block_success_id = res.getInteger(R.integer.block_success_id);
		foul_id = res.getInteger(R.integer.foul_id);
		save_id = res.getInteger(R.integer.save_id);
		save_7m_id = res.getInteger(R.integer.save_7m_id);
		save_fb_id = res.getInteger(R.integer.save_fb_id);
		goal_against_id = res.getInteger(R.integer.goal_against_id);
		goal_against_7m_id = res.getInteger(R.integer.goal_against_7m_id);
		goal_against_fb_id = res.getInteger(R.integer.goal_against_fb_id);
		tech_fault_id = res.getInteger(R.integer.tech_fault_id);
		fehlpass_id = res.getInteger(R.integer.fehlpass_id);
		steps_id = res.getInteger(R.integer.steps_id);
		three_seconds_id = res.getInteger(R.integer.three_seconds_id);
		doppeldribbel_id = res.getInteger(R.integer.doppeldribbel_id);
		fuss_id = res.getInteger(R.integer.fuss_id);
		zeitspiel_id = res.getInteger(R.integer.zeitspiel_id);
		kreis_id = res.getInteger(R.integer.kreis_id);
		stuermerfoul_id = res.getInteger(R.integer.stuermerfoul_id);
		yellow_card_id = res.getInteger(R.integer.yellow_card_id);
		two_minutes_id = res.getInteger(R.integer.two_minutes_id);
		twoplustwo_id = res.getInteger(R.integer.twoplustwo_id);
		red_card_id = res.getInteger(R.integer.red_card_id);
		lineup_id = res.getInteger(R.integer.lineup_id);
		sub_in_id = res.getInteger(R.integer.sub_in_id);
		sub_out_id = res.getInteger(R.integer.sub_out_id);
		two_in_id = res.getInteger(R.integer.two_in_id);
		timeout_id = res.getInteger(R.integer.timeout_id);
		tactic_60_id = res.getInteger(R.integer.tactic_60_id);
		tactic_51_id = res.getInteger(R.integer.tactic_51_id);
		tactic_42_id = res.getInteger(R.integer.tactic_42_id);
		tactic_321_id = res.getInteger(R.integer.tactic_321_id);
		tactic_guarding_id = res.getInteger(R.integer.tactic_guarding_id);
		Sprungwurf_id = res.getInteger(R.integer.Sprungwurf_id);
		Schlagwurf_id = res.getInteger(R.integer.Schlagwurf_id);
		Laufwurf_id = res.getInteger(R.integer.Laufwurf_id);
		Fallwurf_id = res.getInteger(R.integer.Fallwurf_id);
		Hueftwurf_id = res.getInteger(R.integer.Hueftwurf_id);
		kempa_id = res.getInteger(R.integer.kempa_id);
		Dreher_id = res.getInteger(R.integer.Dreher_id);
		Heber_id = res.getInteger(R.integer.Heber_id);
		Leger_id = res.getInteger(R.integer.Leger_id);
		Abknickwurf_id = res.getInteger(R.integer.Abknickwurf_id);
		
	}
	
	public void updateTickerActivityID() {
		
		SQLiteDatabase db = getWritableDatabase();
		Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity", null);
		String ticker_id = null;
		Integer ticker_activity_id = null;
		String str_ticker_activity_id = null;
		cTicker.moveToFirst();
		
		// Alle Tickermeldungen abfragen
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
			
			ticker_id = getTickerActivityID(cTicker);
			ticker_activity_id = getTickerActivityIDByID(ticker_id);
			str_ticker_activity_id = String.valueOf(ticker_activity_id);
			if (str_ticker_activity_id.length() == 3) {
				
				str_ticker_activity_id = "10" + str_ticker_activity_id;
				ticker_activity_id = Integer.parseInt(str_ticker_activity_id);
				updateTickerActivityID(ticker_id, ticker_activity_id);
				
			}
			if (str_ticker_activity_id.equals("1099") || str_ticker_activity_id.equals("99")) {
				
				str_ticker_activity_id = "10099";
				ticker_activity_id = Integer.parseInt(str_ticker_activity_id);
				updateTickerActivityID(ticker_id, ticker_activity_id);
				
			}
		}
		cTicker.close();
		
	}
}