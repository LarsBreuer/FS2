package de.fivestrikes.fs2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.lang.Math;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class HelperLayout {
	
	private SharedPreferences mPreferences;
	private static final int GET_CODE = 0;
	Context ctxt = null;
	Activity actTicker = null;
	HelperFunction fctHelper = null;
	HelperSQL sqlHelper = null;
	HelperText txtHelper = null;
	double screenInch = 0;
	float screenDensity;
	String gender_id = null;
	String stage_of_life_id = null;
	String level_id = null;
	String club_name = null;
	String club_name_short = null;
	String team_id = null;
	String team_home_id = null;
	String team_away_id = null;
	String server_team_home_id = null;
	String server_team_away_id = null;
	String player_team_id = null;
	String activity_team_id = null;
	String activity_against_team_id = null;
	String activity_team_name = null;
	String activity_against_team_name = null;
	String club_id = null;
	String server_team_id = null;
	String server_club_id = null;
	String player_id = null;
	String goalkeeper_id = null;
	String gk_home_id = null;
	String gk_away_id = null;
	String server_player_id = null;
	String player = null;
	String player_number = null;
	String player_forename = null;
	String player_surename = null;
	String player_name = null;
	String player_position = null;
	String player_position_first = null;
	String player_position_second = null;
	String player_position_third = null;
	String game = null;
	String game_id = null;
	String server_game_id = null;
	String str_game_date = null;
	String home_id = null;
	String away_id = null;
	String teamname = null;
	String team_home = null;
	String team_away = null;
	String team_home_short = null;
	String team_away_short = null;
	String club_home = null;
	String club_away = null;
	String realtime=null;
	String ticker_text = null;
	String ticker_note = null;
	String ticker_event_note = null;
	String activity_string = null;
	String activity_against_string = null;
	String activity_result = null;
	String strInput = "";
	String area = null;
	String stat_game_activities = null;
	String possession_active, goal_active, goal_7m_active, goal_fb_active, miss_active, miss_7m_active, miss_fb_active, 
	save_active, save_7m_active, save_fb_active, goal_against_active, goal_against_7m_active, goal_against_fb_active,
	assist_goal_active, assist_miss_active, defense_goal_active, defense_miss_active, block_goal_active, block_miss_active, foul_active, 
	tech_fault_active, fehlpass_active, steps_active, three_seconds_active, doppeldribbel_active, fuss_active, zeitspiel_active,
	kreis_active, stuermerfoul_active,
	yellow_card_active, two_minutes_active, twoplustwo_active, red_card_active, sub_in_active, sub_out_active, two_in_active, 
	timeout_active, tactic_60_active, tactic_51_active, tactic_42_active, tactic_321_active, tactic_guarding_active;
	Integer ticker_event_id= null;
	Integer ticker_activity_id = null;
	Integer ticker_activity_against_id = null;
	Integer ticker_possession_id = null;
	Integer ticker_assist_id = null;
	Integer ticker_defense_id = null;
	Integer ticker_player_back_id = null;
	Integer ticker_foul_id = null;
	Integer activity_ticker_time = null;
	Integer possession = null;
	Integer current_possession = null;
	Integer possession_against = null;
	Integer home_or_away = null;
	Integer activity_id = null;
	Integer activity_against_id = null;
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
	Integer tactic_60_id = null;
	Integer tactic_51_id = null;
	Integer tactic_42_id = null;
	Integer tactic_guarding_id = null;
	Integer tactic_321_id = null;
	Integer game_halftime = null;
	Integer duration = null;
	Integer count_ticker_activity = null;
	Integer player_input  = null;
	Integer time = null;
	Integer turnover = null;
	Integer x_coord = null;
	Integer y_coord = null;
	Boolean edit = false;
	int picSize=0;
	int maxTime = 0;
	private int year;
	private int month;
	private int day;
	private long time_start = 0;
	private long time_sofar = 0;
	private Handler mHandler = new Handler();
	private final int REFRESH_RATE = 100;
	private boolean stopped = false;
	public static Long elapsedTime=Long.parseLong("0");
	static final int DATE_DIALOG_ID = 999;
	Date game_date = null;
	Button btn_team_home = null;
	Button btn_team_away = null;
	EditText edit_club_name = null;
	EditText edit_club_name_short = null;
	EditText edit_player_forename = null;
	EditText edit_player_surename = null;
	EditText edit_player_number = null;
	EditText edit_game_halftime = null;
	EditText txt_ticker_event = null;
	Spinner spinner_position_first = null; 
	Spinner spinner_position_second = null; 
	Spinner spinner_position_third = null;
	TextView player_club = null;
	TextView player_team = null;
	ArrayList<String> arr_position = new ArrayList<String>();
	TabFragTeamList fragTeamList;
	TabFragPlayerList fragPlayerList;
	TabFragGameList fragGameList;
	TabFragTickerList fragTickerList;
	TabFragTickerPlayer fragTickerPlayer;
	TabFragListWithText fragListWithText;
	TabFragStatTeam fragStatTeam;
	TabFragStatPlayerStatList fragStatPlayerList;
	TabFragStatPlayerStat fragStatPlayerStat;
	TabFragStatPlayerTicker fragStatPlayerTicker;
	SmartTickerActivity smartActivity;
	SmartTickerPlayer tickerPlayerActivity;
	SmartListWithText listActivity;
	SmartStatTeam statTeamListActivity;
	SmartStatScore statScoreListActivity;
	SmartStatPlayerList statPlayerListActivity;
	SmartStatPlayerStat statPlayerStatActivity;
	SmartStatPlayerTicker smartPlayerTickerActivity;
	SmartStatGameActivity smartStatGameActivity;
	View view;
	Resources res;
	FragmentManager fragmentManager;
	Bundle args;
	Intent i;
	ImageView icon;
	Cursor c;
	int[] activityList = null;
	Boolean set_area = false;
	Boolean set_position = false;
	private static final String TAG = "MainActivity";
	private static final String URL_GAMES = "http://calm-waters-7359.herokuapp.com/games.json";
	private static final String URL_TICKER = "http://calm-waters-7359.herokuapp.com/multiple_ticker_activities.json";
	
/*
 * 
 * Imagegröße einrichten 
 *
 */
	
	public void scaleImage(ImageView imageView, int boundBoxInDp, String layout) {
		
		Drawable drawing = imageView.getDrawable();
		
		if (drawing != null) {
			
			// Get the ImageView and its bitmap
			Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();
		
			// Get current dimensions
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();

			// Determine how much to scale: the dimension requiring less scaling is
			// closer to the its side. This way the image always stays inside your
			// bounding box AND either x/y axis touches it.
			float xScale = ((float) boundBoxInDp) / width;
			float yScale = ((float) boundBoxInDp) / height;
			float scale = (xScale <= yScale) ? xScale : yScale;

			// Create a matrix for the scaling and add the scaling data
			Matrix matrix = new Matrix();
			matrix.postScale(scale, scale);
			
			// Create a new bitmap and convert it to a format understood by the ImageView
			Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
			BitmapDrawable result = new BitmapDrawable(scaledBitmap);
			width = scaledBitmap.getWidth();
			height = scaledBitmap.getHeight();
			
			// Apply the scaled bitmap
			imageView.setImageDrawable(result);
			
			// Now change ImageView's dimensions to match the scaled image
			if (layout.equals("LinearLayout")) {
				
				LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
				linearParams.width = width;
				linearParams.height = height;
				imageView.setLayoutParams(linearParams);
				
			}
			
			if (layout.equals("RelativeLayout")) {
				
				RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
				linearParams.width = width;
				linearParams.height = height;
				imageView.setLayoutParams(linearParams);
				
			}
		}
	}
	
/*
 * 
 * Layout Mannschaft editieren einrichten 
 *
 */
	
	public void lytTeamEdit(View contentView, FragmentManager contentFragmentManager, Bundle contentArgs) {
		
		view = contentView;
		ctxt = view.getContext();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper=new HelperSQL(ctxt);
		fctHelper=new HelperFunction();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if (screenInch > 6) {
			
			fragTeamList = (TabFragTeamList) fragmentManager.findFragmentById(R.id.frag_team_list);
			
		}
		
/* Daten aus Activity laden */ 
	        
		if (args != null) {
			
			team_id = args.getString("TeamID");
			server_team_id = args.getString("ServerTeamID");
			club_id = args.getString("ClubID");
			server_club_id = args.getString("ServerClubID");
			gender_id = args.getString("GenderID");
			stage_of_life_id = args.getString("StageOfLifeID");
			level_id = args.getString("LevelID");
			club_name = args.getString("ClubName");
			club_name_short = args.getString("ClubNameShort");

		}
		
/* Layoutfelder definieren */
		
		edit_club_name = (EditText) view.findViewById(R.id.team_name);
		edit_club_name_short = (EditText) view.findViewById(R.id.team_short);
		
		Button link_to_choose_club=(Button) view.findViewById(R.id.link_to_choose_club);
		Button btn_okay = (Button) view.findViewById(R.id.btn_okay);
		Button player_manage = (Button) view.findViewById(R.id.player_manage);
		Button btn_synch = (Button) view.findViewById(R.id.synch);
		Button btn_save=(Button) view.findViewById(R.id.btn_save);
		Button btn_delete = (Button) view.findViewById(R.id.btn_delete);
		Button btn_delete_team_name = (Button) view.findViewById(R.id.btn_delete_team_name);
		Button btn_delete_team_short = (Button) view.findViewById(R.id.btn_delete_team_short);
	    
		Spinner spinner_male_female = (Spinner) view.findViewById(R.id.spinner_male_female);
		Spinner spinner_stage_of_life = (Spinner) view.findViewById(R.id.spinner_stage_of_life);
		Spinner spinner_level = (Spinner) view.findViewById(R.id.spinner_level);
		
/* Daten übertragen und Layoutfelder mit Daten füllen */
		
		if (team_id != null) {
			
			if (club_name == null) {club_name = sqlHelper.getTeamClubNameByID(team_id);}
			if (club_name_short == null) {club_name_short = sqlHelper.getTeamClubShortByID(team_id);}
			if (gender_id == null) {gender_id = sqlHelper.getGenderByTeamID(team_id);}
			if (stage_of_life_id == null) {stage_of_life_id = sqlHelper.getStageByTeamID(team_id);}
			if (level_id == null) {level_id = sqlHelper.getLevelByTeamID(team_id);}
			edit_club_name.setText(club_name);
			edit_club_name_short.setText(club_name_short);
			spinner_male_female.setSelection(Integer.parseInt(gender_id)-1);
			spinner_stage_of_life.setSelection(Integer.parseInt(stage_of_life_id)-1);
			spinner_level.setSelection(Integer.parseInt(level_id)-1);
			
		} else {
			
			if (club_id != null) {
				
				edit_club_name.setText(club_name);
				edit_club_name_short.setText(club_name_short);
				
			} else {
				
				edit_club_name.setHint(ctxt.getString(R.string.put_clubname));
				edit_club_name_short.setHint(ctxt.getString(R.string.put));
				
			}
			
		}
		
/* Layoutfelder einrichten */
		
		// Mannschaftsfeld löschen
		btn_delete_team_name.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				
				edit_club_name.setText("");
				edit_club_name.setHint(ctxt.getString(R.string.put_clubname));
				
			}
		});
		
		// Feld Kürzel Mannschaft löschen
		btn_delete_team_short.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
						
				edit_club_name_short.setText("");
				edit_club_name_short.setHint(ctxt.getString(R.string.put));
						
			}
		});
		
		// Verlinkung zur Vereinsauswahl
		link_to_choose_club.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				
				if(screenInch > 6) {
										
					args = new Bundle();
					args.putString("GenderID", gender_id);
					args.putString("StageOfLifeID", stage_of_life_id);
					args.putString("LevelID", level_id);
					args.putString("ClubName", club_name);
					args.putString("ClubNameShort", club_name_short);
					args.putString("TeamID", team_id);
					args.putString("ClubID", club_id);
					args.putString("ServerTeamID", server_team_id);
					args.putString("ServerClubID", server_team_id);
				        
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragClubSelect fragment = new TabFragClubSelect();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_team_edit, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					((Activity)ctxt).startActivityForResult(new Intent(ctxt, SmartClubChoose.class), GET_CODE);
					
				}
			}
		});
		
		// Okay-Button
		btn_okay.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
						
				InputMethodManager inputManager = (InputMethodManager) ((Activity)ctxt).getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(((Activity)ctxt).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
						
			}
		});
		
		// Verlinkung zur Spielerauswahl
		player_manage.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				if (team_id != null) {

					if(screenInch > 6) {
						
						Intent i=new Intent(ctxt, TabPlayerActivity.class);
						i.putExtra("TeamID", team_id);
						ctxt.startActivity(i);
						
					} else {
						
						Intent i=new Intent(ctxt, SmartPlayerList.class);
						i.putExtra("TeamID", team_id);
						ctxt.startActivity(i);
						
					}
					
				} else {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(ctxt);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.team);
					text.setText(R.string.text_team_save_player);
					
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
						}
					});

					dialog.show();
					// Ende Nachrichtenbox
					
				}						
			}
		});
		
		// Verlinkung zur Synchronisierung
		btn_synch.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				mPreferences = ctxt.getSharedPreferences("CurrentUser", ctxt.MODE_PRIVATE);
				String auth_token = mPreferences.getString("AuthToken", "");
				
				if (auth_token.equals("")) {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(ctxt);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.synchro);
					text.setText(R.string.text_login_not_possible);
					
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
						}
					});

					dialog.show();
					// Ende Nachrichtenbox
				
				} else {
					
					if (team_id != null) {
						
						if(screenInch > 6) {
							
							args = new Bundle();
							args.putString("GenderID", gender_id);
							args.putString("StageOfLifeID", stage_of_life_id);
							args.putString("LevelID", level_id);
							args.putString("ClubName", club_name);
							args.putString("ClubNameShort", club_name_short);
							args.putString("TeamID", team_id);
							args.putString("ClubID", club_id);
							args.putString("ServerTeamID", server_team_id);
							args.putString("ServerClubID", server_team_id);
						        
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							TabFragTeamOnline fragment = new TabFragTeamOnline();
							fragment.setArguments(args);
							fragmentTransaction.replace(R.id.frag_team_edit, fragment);
							fragmentTransaction.commit();
							
						} else {
							
							Intent i = new Intent(ctxt, SmartTeamOnline.class);
							i.putExtra("TeamID", team_id);
							ctxt.startActivity(i);
							finishActivity(team_id, args);
							
						}
						
					} else {
						
/** TODO -3- => Einfache OK-Dialogboxen rausziehen und nur einmal einrichten */
						// DialogBox einrichten
						final Dialog dialog = new Dialog(ctxt);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.custom_dialog);

						// Texte setzen
						TextView title = (TextView) dialog.findViewById(R.id.title);
						TextView text = (TextView) dialog.findViewById(R.id.text);
						title.setText(R.string.team);
						text.setText(R.string.text_team_save);
						
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
							}
						});

						dialog.show();
						
					}
				}
			}
		});
		
		// Save-Button
		btn_save.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				
				club_name = edit_club_name.getText().toString();
				club_name_short = edit_club_name_short.getText().toString();
				Integer count_club_by_name=sqlHelper.countClubByName(club_name);
				
				if (count_club_by_name.equals(0)) {									// Neuen Verein und neue Mannschaft anlegen?
					
					sqlHelper.insertClub(club_name, club_name_short);
					String last_club_id = sqlHelper.getLastClubID();
					String team_type_id = ctxt.getString(R.string.sport_id)+gender_id+stage_of_life_id+level_id;
					
					if (team_id != null) {											// Wenn Mannschaft bereits existiert und nur editiert werden soll
						
						sqlHelper.updateTeam(team_id, server_team_id, last_club_id, server_club_id, team_type_id);
						
					} else {														// Falls keine vorhandene Mannschaft ausgewählt wurde
						
						sqlHelper.insertTeam(server_team_id, last_club_id, server_club_id, team_type_id);
						team_id = sqlHelper.getLastTeamID();
						
					}
					
					finishActivity(team_id, args);
					
				} else {															// Existiert bereits ein Verein mit demselben Namen?
					
					String club_id = sqlHelper.getClubIDByClubName(club_name);
					String team_type_id = ctxt.getString(R.string.sport_id)+gender_id+stage_of_life_id+level_id;
					Integer count_team = sqlHelper.countTeam(club_id, team_type_id);
					
					if (team_id != null) {											// Wenn Mannschaft editiert werden soll
						
						sqlHelper.updateTeam(team_id, server_team_id, club_id, server_club_id, team_type_id);
						if (server_club_id ==null) sqlHelper.updateClub(club_id, null, club_name, club_name_short);
						finishActivity(team_id, args);
						
					} else {														// Falls keine vorhandene Mannschaft ausgewählt wurde
							
						if (count_team.equals(0)) {									// Falls die Mannschaft noch nicht existiert
						
							// ... nur neue Mannschaft einrichten
							sqlHelper.insertTeam(null, club_id, null, team_type_id);
							team_id = sqlHelper.getLastTeamID();
							finishActivity(team_id, args);
						
						} else {													// ... ansonsten benachrichtigen, dass Mannschaft schon vorhanden ist
													
							// DialogBox einrichten
							final Dialog dialog = new Dialog(ctxt);
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.custom_dialog);

							// Texte setzen
							TextView title = (TextView) dialog.findViewById(R.id.title);
							TextView text = (TextView) dialog.findViewById(R.id.text);
							title.setText(R.string.text_team_exists_title);
							text.setText(R.string.text_team_exists);
							
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
									finishActivity(team_id, args);
								}
							});

							dialog.show();
							// Dialogbox Ende
						
						}
					}
				}
			}
		});
		
		// Delete-Button
		btn_delete.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				
				// Überprüfen, ob die Mannschaft in einem Spiel eingesetzt wurde und nur löschen, wenn nicht
				// DialogBox einrichten
		   		final Dialog dialog = new Dialog(ctxt);
		   		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		   		dialog.setContentView(R.layout.custom_dialog);

		   		// Texte setzen
		   		TextView title = (TextView) dialog.findViewById(R.id.title);
		   		TextView text = (TextView) dialog.findViewById(R.id.text);
		   		title.setText(R.string.delete_team);
		   		text.setText(R.string.text_team_delete);
		   				
		   		// Button definieren
		   		LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
		   		lyt_button3.removeAllViews();

		   		Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
		   		dialogButton1.setText(R.string.yes);
		   	
		   		dialogButton1.setOnClickListener(new View.OnClickListener() {
		   		
		   			@Override
		   			public void onClick(View v) {
		   			
		   				dialog.dismiss();
		   			
		   				if (sqlHelper.count_team_game(team_id)	> 0) {
		   					
		   					// DialogBox einrichten
							final Dialog dialog = new Dialog(ctxt);
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.custom_dialog);

							// Texte setzen
							TextView title = (TextView) dialog.findViewById(R.id.title);
							TextView text = (TextView) dialog.findViewById(R.id.text);
							title.setText(R.string.delete_team);
							text.setText(R.string.text_deletion_team_not_possible);
							
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
								}
							});

							dialog.show();
							// Ende Dialogbox einrichten
		   					
		   				} else {
		   					
		   					sqlHelper.deleteTeam(team_id);
		   					((Activity)ctxt).finish();
		   					
		   				}
		   			}
		   		});
		   				
		   		Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
		   		dialogButton2.setText(R.string.no);
		   			
		   		dialogButton2.setOnClickListener(new View.OnClickListener() {
		   			@Override
		   			public void onClick(View v) {
		   			
		   				dialog.dismiss();
		   						
		   			}
		   		});

		   		dialog.show();
		   		// Ende Dialogbox einrichten
				/*
				// Nachrichtenbox einrichten
				AlertDialog.Builder builder = new AlertDialog.Builder(TeamEditActivity.this);
				builder
					.setTitle(R.string.delete)
					.setMessage(R.string.text_team_delete)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							
							if(sqlHelper.countSpielTeamId(team_id)>0){	// Existiert noch ein Spiel mit dieser Mannschaft?
								
								AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TeamEditActivity.this);
								alertDialogBuilder
									.setTitle(R.string.text_deletion_not_possible)
					        	    		.setMessage(R.string.text_deletion_team_not_possible)
					        	    		.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					        					public void onClick(DialogInterface dialog,int id) {

					        					}
					        	    		});
								AlertDialog alertDialog = alertDialogBuilder.create();
					        	    	alertDialog.show();
								
							} else {
								
								sqlHelper.deleteTeam(team_id);
								
							}
						}
					})
					.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {			      	
							
						}
					})
					.show();
				// Ende Nachrichtenbox
				*/
			}
		});
		
		// Geschlecht-Spinner
		spinner_male_female.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				
				gender_id=String.valueOf(position+1);
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// vorübergehend freigelassen
			}
			
		});
		
		// Altersstufe-Spinner
		spinner_stage_of_life.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				
				stage_of_life_id=String.valueOf(position+1);
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// vorübergehend freigelassen
			}
			
		});
		
		// Spielstärke-Spinner
		spinner_level.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				
				level_id=String.valueOf(position+1);
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// vorübergehend freigelassen
			}
		});
	}
	
	public void finishActivity(String team_id, Bundle args) {
		
		screenInch = fctHelper.getScreenInch(ctxt);
		args.putString("TeamID", team_id);
		
		if(screenInch > 6) {
			
			fragTeamList.refreshContent(team_id, args);
			
		} else {
			
			((Activity)ctxt).finish();
			
		}
	}

/*
 * 
* Layout Spieler editieren einrichten 
*
*/
	
	public void lytPlayerEdit(View contentView, FragmentManager contentFragmentManager, Bundle contentArgs) {
		
		view = contentView;
		ctxt = view.getContext();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper=new HelperSQL(ctxt);
		fctHelper=new HelperFunction();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			fragPlayerList = (TabFragPlayerList) fragmentManager.findFragmentById(R.id.frag_player_list);
			
		}
		
/* Daten aus Activity laden */ 
/** TODO -3- => Spieler Wurfarm einstellen */
/** TODO -4- => Geburtsdatum des Spielers einstellen */
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
		
/* Daten aus Datenbank laden */ 
		
		if (player_id != null) {
			
			if (server_player_id == null) {server_player_id = sqlHelper.getPlayerServerIDByID(player_id);}
			if (team_id == null) {team_id = sqlHelper.getPlayerTeamIDByID(player_id);}
			if (server_team_id == null) {server_team_id = sqlHelper.getTeamServerTeamID(team_id);}
			if (player_number == null) {player_number = sqlHelper.getPlayerNumberByID(player_id);}
			if (player_forename == null) {player_forename = sqlHelper.getPlayerForenameByID(player_id);}
			if (player_surename == null) {player_surename = sqlHelper.getPlayerSurenameByID(player_id);}
			if (player_position_first == null) {player_position_first = sqlHelper.getPlayerPositionFirstByID(player_id);}
			if (player_position_second == null) {player_position_second = sqlHelper.getPlayerPositionSecondByID(player_id);}
			if (player_position_third == null) {player_position_third = sqlHelper.getPlayerPositionThirdByID(player_id);}
			
		}

/* Layoutfelder definieren */
		
		edit_player_forename = (EditText) view.findViewById(R.id.player_forename);
		edit_player_surename = (EditText) view.findViewById(R.id.player_surename);
		edit_player_number = (EditText) view.findViewById(R.id.player_number);
	    
		Button btn_okay = (Button) view.findViewById(R.id.okay);
		Button btn_choose_team = (Button) view.findViewById(R.id.btn_choose_team);
		Button btn_synch = (Button) view.findViewById(R.id.synch);
		Button btn_connect = (Button) view.findViewById(R.id.connect);
		Button btn_save = (Button) view.findViewById(R.id.btn_save);
		Button btn_delete = (Button) view.findViewById(R.id.btn_delete);
		Button btn_delete_player_forename = (Button) view.findViewById(R.id.btn_delete_player_forename);
		Button btn_delete_player_surename = (Button) view.findViewById(R.id.btn_delete_player_surename);
		Button btn_delete_player_number = (Button) view.findViewById(R.id.btn_delete_player_number);
	    
		spinner_position_first = (Spinner) view.findViewById(R.id.spinner_position_first);
		spinner_position_second = (Spinner) view.findViewById(R.id.spinner_position_second);
		spinner_position_third = (Spinner) view.findViewById(R.id.spinner_position_third);
		
		player_club = (TextView) view.findViewById(R.id.player_club);
		player_team = (TextView) view.findViewById(R.id.player_team);

/* Textfelder mit Daten füllen */
		
		if (player_forename != null) { 
			edit_player_forename.setText(player_forename); 
		} else {
			edit_player_forename.setText(ctxt.getString(R.string.player));
		}
		if (player_surename != null) { 
			edit_player_surename.setText(player_surename); 
		} else {
			edit_player_surename.setText(String.valueOf(sqlHelper.countTeamPlayer(team_id) + 1));
		}
		if (player_number != null) { 
			edit_player_number.setText(player_number); 
		} else {
			edit_player_number.setText(String.valueOf(sqlHelper.countTeamPlayer(team_id) + 1));
		}
		if (team_id != null) { 
			player_club.setText(sqlHelper.getTeamClubNameByID(team_id)); 
			player_team.setText(sqlHelper.getTeamTypeNameByTeamID(team_id, ctxt)); 
		}
		if (server_player_id != null) {
			edit_player_forename.setEnabled(false);
			edit_player_surename.setEnabled(false);
			edit_player_number.setEnabled(false);
		}
		
/* Daten für den Position-Spinner vorbereiten und Werte einfügen */
		
		// Inhalt des Spinners definieren und setzen
		arr_position.add(ctxt.getString(R.string.please_choose));
		arr_position.add(ctxt.getString(R.string.goalkeeper));
		arr_position.add(ctxt.getString(R.string.left_wing));
		arr_position.add(ctxt.getString(R.string.left_back));
		arr_position.add(ctxt.getString(R.string.center_back));
		arr_position.add(ctxt.getString(R.string.right_back));
		arr_position.add(ctxt.getString(R.string.right_wing));
		arr_position.add(ctxt.getString(R.string.pivot));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctxt, android.R.layout.simple_spinner_item, arr_position);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_position_first.setAdapter(adapter);
		spinner_position_second.setAdapter(adapter);
		spinner_position_third.setAdapter(adapter);
		
		// Spinner-Einträge vornehmen
		if (fctHelper.isInteger(player_position_first)) {
			if (player_position_first.length() == 4) { 
				spinner_position_first.setSelection(Integer.parseInt(player_position_first.substring(3,4)));
			}
		}
		if (fctHelper.isInteger(player_position_second)) {
			if (player_position_second.length() == 4) { 
				spinner_position_second.setSelection(Integer.parseInt(player_position_second.substring(3,4)));
			}
		}
		if (fctHelper.isInteger(player_position_third)) {
			if (player_position_third.length() == 4) { 
				spinner_position_third.setSelection(Integer.parseInt(player_position_third.substring(3,4)));
			}
		}
		
/* Layoutfelder einrichten */
		
		// Feld Spieler-Vorname löschen
		btn_delete_player_forename.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
						
				edit_player_forename.setText("");
				edit_player_forename.setHint(ctxt.getString(R.string.put_forename));
						
			}
		});
		
		// Feld Spieler-Nachname löschen
		btn_delete_player_surename.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
						
				edit_player_surename.setText("");
				edit_player_surename.setHint(ctxt.getString(R.string.put_surename));
						
			}
		});
		
		// Feld Spieler-Rückenneummer löschen
		btn_delete_player_number.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
						
				edit_player_number.setText("");
				edit_player_number.setHint(ctxt.getString(R.string.put));
						
			}
		});
		
		// Okay-Button
		btn_okay.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				
				InputMethodManager inputManager = (InputMethodManager) ((Activity)ctxt).getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(((Activity)ctxt).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				
			}
		});
		
		// Verlinkung zur Vereinsauswahl
		btn_choose_team.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				
				args = new Bundle();
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
				
				if(screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTeamSelect fragment = new TabFragTeamSelect();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_player_edit, fragment);
					fragmentTransaction.commit();
					
				} else {
/** TODO -3- => Spieler verschieben funktiniert nicht */
					Intent i = new Intent(ctxt, SmartTeamSelect.class);
					i.putExtras(args);
					((Activity)ctxt).startActivityForResult(i, GET_CODE);
					
				}
			}
		});
		
		// Synchronisierung-Button
		btn_synch.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				mPreferences = ctxt.getSharedPreferences("CurrentUser", ctxt.MODE_PRIVATE);
				String auth_token = mPreferences.getString("AuthToken", "");
				
				if (auth_token.equals("")) {

					// DialogBox einrichten
					final Dialog dialog = new Dialog(ctxt);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.synchro);
					text.setText(R.string.text_login_not_possible);
					
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
						}
					});

					dialog.show();
					// Ende Nachrichtenbox
				
				} else {
					
					if (server_player_id != null) {					// Falls der Spieler schon eine Server-ID hat, Spieler nicht mehr synchronisieren
						
						// DialogBox einrichten
						final Dialog dialog = new Dialog(ctxt);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.custom_dialog);

						// Texte setzen
						TextView title = (TextView) dialog.findViewById(R.id.title);
						TextView text = (TextView) dialog.findViewById(R.id.text);
						title.setText(R.string.synchro);
						text.setText(R.string.text_player_server);
						
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
							}
						});

						dialog.show();
						// Ende Nachrichtenbox
						
					} else {
						
						if (server_team_id == null) {				// Falls das Team nicht synchronisert ist, kann der Spieler nicht synchronisiert werden
							
							// DialogBox einrichten
							final Dialog dialog = new Dialog(ctxt);
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.custom_dialog);

							// Texte setzen
							TextView title = (TextView) dialog.findViewById(R.id.title);
							TextView text = (TextView) dialog.findViewById(R.id.text);
							title.setText(R.string.synchro);
							text.setText(R.string.text_player_synch_not_possible);
							
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
								}
							});

							dialog.show();
							// Ende Nachrichtenbox
							
						} else {
							
							if(screenInch > 6) {
								
								args = new Bundle();
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
							      
								FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
								TabFragPlayerSynch fragment = new TabFragPlayerSynch();
								fragment.setArguments(args);
								fragmentTransaction.replace(R.id.frag_player_edit, fragment);
								fragmentTransaction.commit();
								
							} else {
								
								Intent i = new Intent(ctxt, SmartPlayerSynch.class);
								i.putExtra("TeamID", team_id);
								i.putExtra("ServerTeamID", server_team_id);
								ctxt.startActivity(i);
								((Activity)ctxt).finish();
								
							}						
						}
					}
					
				}
			}
		});
		
		// Spieler verbinden Button
		btn_connect.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				// Verbindung nur bei lokal angelegtem Spieler einrichten
				if (server_player_id == null && player_id != null) {
/** TODO -3- => Verschmelzen funktiniert nicht */
					if(screenInch > 6) {
						
						args = new Bundle();
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
					        
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragPlayerMerge fragment = new TabFragPlayerMerge();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_player_edit, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						Intent i = new Intent(ctxt, SmartPlayerMerge.class);
						i.putExtra("PlayerID", player_id);
						ctxt.startActivity(i);
						((Activity)ctxt).finish();
						
					}	
					
				} else {
					
					// Falls Spieler schon mit dem Server synchronisiert ist, Meldung, dass Verbindung nicht möglich ist
					// DialogBox einrichten
					final Dialog dialog = new Dialog(ctxt);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.merge);
					text.setText(R.string.text_player_connect_not_possible);
					
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
						}
					});

					dialog.show();
					// Ende Nachrichtenbox
					
				}
				
			}
		});
					
		
/* Spinner definieren */

		// 1. Position
		spinner_position_first.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				
				player_position_first = ctxt.getString(R.string.sport_id)+"0"+ String.valueOf(position);
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// vorübergehend freigelassen
			}
			
		});
		
		// 2. Position
		spinner_position_second.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
						
				player_position_second = ctxt.getString(R.string.sport_id)+"0"  + String.valueOf(position);
						
			}
					
			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
						// vorübergehend freigelassen
			}
					
		});
		
		// 3. Position
		spinner_position_third.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
						
				player_position_third = ctxt.getString(R.string.sport_id)+"0" + String.valueOf(position);
						
			}
					
			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
						// vorübergehend freigelassen
			}
					
		});
		
		// Save-Button
		btn_save.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				
				if (player_position_first.equals(ctxt.getString(R.string.sport_id) + "00")) {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(ctxt);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.text_choose_position);
					text.setText(R.string.text_choose_position_before_save);
					
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
						}
					});

					dialog.show();
					// Ende Dialogbox einrichten
					
				} else {
					
					// Datenfelder auslesen
					player_forename = edit_player_forename.getText().toString();
					player_surename = edit_player_surename.getText().toString();
					player_number = edit_player_number.getText().toString();
					if (player_position_first.equals(ctxt.getString(R.string.sport_id) + "00")) player_position_first = null;
					if (player_position_second.equals(ctxt.getString(R.string.sport_id) + "00")) player_position_second = null;
					if (player_position_third.equals(ctxt.getString(R.string.sport_id) + "00")) player_position_third = null;
					if (player_id != null) {
						sqlHelper.updatePlayer(player_id, server_player_id, team_id, server_team_id, player_number, player_forename, player_surename, player_position_first, player_position_second, player_position_third);
					} else {
						sqlHelper.insertPlayer(server_player_id, team_id, server_team_id, player_number, player_forename, player_surename, player_position_first, player_position_second, player_position_third);
						player_id = sqlHelper.getLastPlayerID();
						args.putString("PlayerID", player_id);
					}
					
					if(screenInch > 6) {
						fragPlayerList.refreshContent(team_id, player_id, args);
					} else {
						((Activity)ctxt).finish();
					}	
					
				}						
			}
		});
		
		// Delete-Button
		
		btn_delete.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				
				// Überprüfen, ob Spieler in einem Spiel eingesetzt wurde und nur löschen, wenn nicht
				// DialogBox einrichten
		   		final Dialog dialog = new Dialog(ctxt);
		   		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		   		dialog.setContentView(R.layout.custom_dialog);

		   		// Texte setzen
		   		TextView title = (TextView) dialog.findViewById(R.id.title);
		   		TextView text = (TextView) dialog.findViewById(R.id.text);
		   		title.setText(R.string.delete_player);
		   		text.setText(R.string.text_player_delete);
		   				
		   		// Button definieren
		   		LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
		   		lyt_button3.removeAllViews();

		   		Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
		   		dialogButton1.setText(R.string.yes);
		   	
		   		dialogButton1.setOnClickListener(new View.OnClickListener() {
		   		
		   			@Override
		   			public void onClick(View v) {
		   			
		   				dialog.dismiss();
		   			
		   				if (sqlHelper.count_ticker_activity(null, null, player_id, null, null, null)	> 0) {
		   					
		   					// DialogBox einrichten
							final Dialog dialog = new Dialog(ctxt);
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.custom_dialog);

							// Texte setzen
							TextView title = (TextView) dialog.findViewById(R.id.title);
							TextView text = (TextView) dialog.findViewById(R.id.text);
							title.setText(R.string.delete_player);
							text.setText(R.string.text_deletion_player_not_possible);
							
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
								}
							});

							dialog.show();
							// Ende Dialogbox einrichten
		   					
		   				} else {
		   					
		   					sqlHelper.deletePlayer(player_id);
		   					((Activity)ctxt).finish();
		   					
		   				}
		   			}
		   		});
		   				
		   		Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
		   		dialogButton2.setText(R.string.no);
		   			
		   		dialogButton2.setOnClickListener(new View.OnClickListener() {
		   			@Override
		   			public void onClick(View v) {
		   			
		   				dialog.dismiss();
		   						
		   			}
		   		});

		   		dialog.show();
		   		// Ende Dialogbox einrichten
				
			}
		});
	}
	
/*
 * 
 * Layout Mannschaft editieren einrichten 
 *
 */
		
	public void lytGameEdit(String id, final View contentView, FragmentManager contentFragmentManager, Bundle contentArgs) {
		
		view = contentView;
		ctxt = view.getContext();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper=new HelperSQL(ctxt);
		fctHelper=new HelperFunction();
		
/* Bildschirmgröße ermitteln */
		
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			fragGameList = (TabFragGameList) fragmentManager.findFragmentById(R.id.frag_game_list);
			
		}
		
/* Daten aus Activity laden */ 
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			
		} else {
			
			game_id = id;
			
		}
		
		if (game_id != null) {
			
			game_halftime = sqlHelper.getGameDurationHalftimeByID(game_id);
			home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			if (home_id != null) {
				server_team_home_id = sqlHelper. getTeamServerTeamID(home_id);
				club_home = sqlHelper.getClubNameByTeamID(home_id);
			}
			if (away_id != null) {
				server_team_away_id = sqlHelper. getTeamServerTeamID(away_id);
				club_away = sqlHelper.getClubNameByTeamID(away_id);
			}
			str_game_date = sqlHelper.getGameDateByID(game_id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				game_date = sdf.parse(str_game_date);
				day = game_date.getDate();    // getDate => Day of month; getDay => day of week
				month = game_date.getMonth();
				year = game_date.getYear()+1900;
			}
			catch (ParseException e) {}
			
		}
		
		// Verhindern, dass Tastatur automatisch aufpoppt
		((Activity)ctxt).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
/* Layoutfelder definieren */
		
		edit_game_halftime = (EditText) view.findViewById(R.id.game_halftime);
		
		Button btn_ticker = (Button) view.findViewById(R.id.ticker);
		Button btn_game_date = (Button) view.findViewById(R.id.game_date);
		btn_team_home = (Button) view.findViewById(R.id.team_home);
		btn_team_away = (Button) view.findViewById(R.id.team_away);
		Button btn_input_depth = (Button) view.findViewById(R.id.input_depth);
		Button btn_synch = (Button) view.findViewById(R.id.synch);
		Button btn_save = (Button) view.findViewById(R.id.save);
		Button btn_delete = (Button) view.findViewById(R.id.delete);
/** TODO -2- => Runde Button bei Spiel editieren einbauen */

/* Button beschriften */
	        
		if(home_id != null){
			
		    	btn_team_home.setText(sqlHelper.getTeamClubNameByID(home_id));
		    	
		}
		
		if(away_id != null){
			
			btn_team_away.setText(sqlHelper.getTeamClubNameByID(away_id));

		}
		    
		btn_game_date.setText(new StringBuilder()
					// Month is 0 based, just add 1
					.append(day).append(".")
					.append(month + 1).append(".")
					.append(year));
		    
		edit_game_halftime.setText(String.valueOf(game_halftime));
		
		// Button Datum
		btn_game_date.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				
				if (screenInch > 6) {
					
					showDatePicker();
					
				}
			            	
			}
		});
		
		/* Button Heimmannschaft aufrufen */
		btn_team_home.setOnClickListener(new View.OnClickListener() {

			@Override
	            public void onClick(View v) {
				
				teamSelect(game_id, 1);
				
	            }
	        });
		
		/* Button Auswärtsmannschaft aufrufen */
		btn_team_away.setOnClickListener(new View.OnClickListener() {

			@Override
	            public void onClick(View v) {
				
				teamSelect(game_id, 0);
				
	            }
		});
		
		// Button Eingabetiefe
		btn_input_depth.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				
				args = new Bundle();
				args.putString("GameID", game_id);
				args.putString("InputString", "InputDepth");

				if (screenInch > 6) {
				        
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithClickbox fragment = new TabFragListWithClickbox();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_game_edit, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					Intent i = new Intent(ctxt, SmartListWithClickbox.class);
        				i.putExtras(args);
        				((Activity)ctxt).startActivity(i);
					
				}
			}         	
		});
		
		/* Button Ticker aufrufen */
		btn_ticker.setOnClickListener(new View.OnClickListener() {

			@Override
	            public void onClick(View v) {
				
				if (home_id == null || away_id == null) {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(ctxt);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.text_start_game_not_possible_title);
					text.setText(R.string.text_start_game_not_possible);
					
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
						}
					});

					dialog.show();
					// Ende Nachrichtenbox
					
				} else {
					
					sqlHelper.updateGame(game_id, null, null, null, null, Integer.parseInt(edit_game_halftime.getText().toString()), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
					
					if (screenInch > 6) {
						
						Intent i = new Intent(ctxt, TabTickerActivity.class);
	        				i.putExtra("GameID", game_id);
	        				((Activity)ctxt).startActivity(i);
						
					} else {
						
						Intent i = new Intent(ctxt, SmartTickerActivity.class);
	        				i.putExtra("GameID", game_id);
	        				((Activity)ctxt).startActivity(i);
						
					}
					
				}
				
	            }
		});
		
		/* Synchronisieren */
		btn_synch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if (server_team_home_id != null && server_team_away_id != null) {
					
					/* 
					 * 
					 *  Das Spiel synchronisieren 
					 * 
					 */
					
					// JSON object to hold the information, which is sent to the server
					JSONObject jsonObjSend = new JSONObject();
					
					try {
						
		                        // Add key/value pairs
		                        jsonObjSend.put("user_id", "1");
		                        jsonObjSend.put("team_home_id", server_team_home_id);
		                        jsonObjSend.put("team_away_id", server_team_away_id);
		                        jsonObjSend.put("club_home_name", club_home);
		                        jsonObjSend.put("club_away_name", club_away);
		                        jsonObjSend.put("duration_halftime", 30);
		                        jsonObjSend.put("game_date", "1");

		                        // Add a nested JSONObject (e.g. for header information)
		                        JSONObject header = new JSONObject();
		                        header.put("deviceType","Android"); // Device type
		                        header.put("deviceVersion","2.0"); // Device OS version
		                        header.put("language", "es-es");        // Language of the Android client
		                        jsonObjSend.put("header", header);
		                        
		                        // Output the JSON object we're sending to Logcat:
		                        Log.i(TAG, jsonObjSend.toString(2));
		                        
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					// Send the HttpPostRequest and receive a JSONObject in return
					JSONObject jsonObjRecv = HttpClient.SendHttpPost(URL_GAMES, jsonObjSend, null);
					try {
						Log.v("Die ID lautet: ", jsonObjRecv.get("id").toString());
						server_game_id = jsonObjRecv.get("id").toString();
						sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, server_game_id);
					}
					catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*
					 *  From here on do whatever you want with your JSONObject, e.g.
					 *  1) Get the value for a key: jsonObjRecv.get("key");
					 *  2) Get a nested JSONObject: jsonObjRecv.getJSONObject("key")
					 *  3) Get a nested JSONArray: jsonObjRecv.getJSONArray("key") 
					 */
					
					/* 
					 * 
					 *  Die Ticker übertragen 
					 * 
					 */
/** TODO -2- => Vor Übertragung des Tickers Mannschaftseinwechslungen (7 Spieler) abfragen und ersetzen durch Ein- bzw. Auswechslung - siehe count_ticker_player_time unter HelperSQL */
					JSONArray tickerArray = new JSONArray();
					
					try {
						
						for(int i = 0; i < 3; i++) {
							
							JSONObject jsonObjForArray = new JSONObject();
							
							jsonObjForArray.put("activity_id", "10100");
							jsonObjForArray.put("player_id", i);
							jsonObjForArray.put("time", i * 100 + i * 5);
							jsonObjForArray.put("game_id", game_id);
							jsonObjForArray.put("team_id", 1);
							jsonObjForArray.put("realtime", null);
							jsonObjForArray.put("home_or_away", 1);
							jsonObjForArray.put("goal_area", "ML");
							jsonObjForArray.put("field_position_x", 100);
							jsonObjForArray.put("field_position_y", 50);
							jsonObjForArray.put("throwing_technique_id", 10180);
							jsonObjForArray.put("ticker_activity_note", "Das war ein schoenes Spiel");
							jsonObjForArray.put("mark", 3);
							
							tickerArray.put(jsonObjForArray);
							
						}
/*
		                        // Add a nested JSONObject (e.g. for header information)
		                        JSONObject header = new JSONObject();
		                        header.put("deviceType","Android"); // Device type
		                        header.put("deviceVersion","2.0"); // Device OS version
		                        header.put("language", "es-es");        // Language of the Android client
		                        tickerArray.put("header", header);
*/	                        
		                        // Output the JSON object we're sending to Logcat:
		                        Log.i(TAG, tickerArray.toString(2));
		                        
					} catch (JSONException e) {
						e.printStackTrace(); 
					}
					
					// Send the HttpPostRequest and receive a JSONObject in return
					JSONObject jsonObjRecvArray = HttpClient.SendHttpPost(URL_TICKER, null, tickerArray);
					try {
						Log.v("Die ID lautet: ", jsonObjRecvArray.get("id").toString()); 
					}
					catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
/** TODO -0- => Async einrichten während laden. Am Ende Meldung, dass Synchronisation erfolgreich war */
				} else {
					
/** TODO -2- => Meldung einrichten, falls keine Server ID vorhanden sein sollte */
					
				}
			}
		});
		
		/* Spiel speichern */
		btn_save.setOnClickListener(new View.OnClickListener() {
	
			@Override
	            public void onClick(View v) {
				
				sqlHelper.updateGame(game_id, null, null, null, null, Integer.parseInt(edit_game_halftime.getText().toString()), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
				
				if (screenInch > 6) {
					
					Toast.makeText(((Activity)ctxt), ctxt.getString(R.string.text_game_save), Toast.LENGTH_SHORT).show();
					
				} else {
					
					((Activity)ctxt).finish();	
					
				}
				
	            }
		});
		
		/* Spiel löschen */
		btn_delete.setOnClickListener(new View.OnClickListener() {
	
			@Override
	            public void onClick(View v) {

      				// DialogBox einrichten
      		   		final Dialog dialog = new Dialog(ctxt);
      		   		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      		   		dialog.setContentView(R.layout.custom_dialog);

      		   		// Texte setzen
      		   		TextView title = (TextView) dialog.findViewById(R.id.title);
      		   		TextView text = (TextView) dialog.findViewById(R.id.text);
      		   		title.setText(R.string.delete);
      		   		text.setText(R.string.text_game_delete);
      		   				
      		   		// Button definieren
      		   		LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
      		   		lyt_button3.removeAllViews();

      		   		Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
      		   		dialogButton1.setText(R.string.yes);
      		   	
      		   		dialogButton1.setOnClickListener(new View.OnClickListener() {
      		   		
      		   			@Override
      		   			public void onClick(View v) {
      		   			
      		   				dialog.dismiss();
      		   			
      		   				if (game_id != null) {sqlHelper.deleteGame(game_id);}
  	    					if (screenInch > 6) {
  	    							
  	    						// Aktualisiere die Spielliste
  	    						fragGameList = (TabFragGameList) fragmentManager.findFragmentById(R.id.frag_game_list);
  	    						fragGameList.refreshContent(game_id);
  	    						
  	    						// Füge ein leeres Fragment ein
  	    						args = new Bundle();
  	    						args.putString("Activity", "Game");

  	    						TabFragEmpty secondFragment = new TabFragEmpty();
 	    						secondFragment.setArguments(args);
  	    						fragmentManager.beginTransaction().add(R.id.frag_game_edit, secondFragment).commit();
  	    							
  	    					} else {
  	    							
  	    						((Activity)ctxt).finish();	
  	    							
  	    					}  			
      		   			}
      		   		});
      		   				
      		   		Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
      		   		dialogButton2.setText(R.string.no);
      		   			
      		   		dialogButton2.setOnClickListener(new View.OnClickListener() {
      		   			@Override
      		   			public void onClick(View v) {
      		   			
      		   				dialog.dismiss();
      		   						
      		   			}
      		   		});

      		   		dialog.show();
      		   		// Ende Dialogbox einrichten
				
	            }
		});
	}
	
/*
 * 
 * Layout Anzeigetafel einrichten 
 *
 */
			
	public void lytTickerBoard(String id, final View contentView, final FragmentManager contentFragmentManager, Bundle contentArgs) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources(); 
		fragmentManager = contentFragmentManager;
		game_id = id;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper=new HelperSQL(ctxt);
		fctHelper=new HelperFunction();
		txtHelper=new HelperText();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
			
		}
		
/* Daten aus Datenbank laden */
		
		team_home = sqlHelper.getGameTeamHomeByID(game_id);
		team_away = sqlHelper.getGameTeamAwayByID(game_id);
		team_home_short = sqlHelper.getGameTeamHomeShortByID(game_id);
		team_away_short = sqlHelper.getGameTeamAwayShortByID(game_id);
		possession = sqlHelper.getGamePossession(game_id);
		
/* Button einrichten */
		
		Button btn_home=(Button) view.findViewById(R.id.btn_home);
		Button btn_away=(Button) view.findViewById(R.id.btn_away);
		Button btn_goals_home=(Button) view.findViewById(R.id.btn_goals_home);
		Button btn_goals_away=(Button) view.findViewById(R.id.btn_goals_away);
		Button btn_timer=(Button) view.findViewById(R.id.btn_timer);
		
/* Activity IDs laden */
		
		possession_id = res.getInteger(R.integer.possession_id);
		yellow_card_id = res.getInteger(R.integer.yellow_card_id);
		
/* Tore einstellen */
		btn_goals_home.setText(String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, null)));
		btn_goals_away.setText(String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, null)));
		
/* Button beschriften */
		
		btn_home.setText(team_home_short);
		btn_away.setText(team_away_short);
		
/* Zeit stellen */
		
		if (sqlHelper.getGameTimeSoFar(game_id) != null) time_sofar = Long.parseLong(sqlHelper.getGameTimeSoFar(game_id));
		if (sqlHelper.getGameTimeStart(game_id) != null) time_start = Long.parseLong(sqlHelper.getGameTimeStart(game_id));
		
		if (time_start == 0) {
			elapsedTime = time_sofar;
		} else {
			elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
		}
		btn_timer.setText(fctHelper.updateTimer(elapsedTime));
		
		if (time_start == 0) {							// wenn Zeit beim letzten Verlassen gestoppt war

			stopped = true;
			btn_timer.setBackgroundResource(R.drawable.button_timer_red);
			
		} else {
			
			mHandler.removeCallbacks(startTimer);
			mHandler.postDelayed(startTimer, 0);
			stopped = false;
			btn_timer.setBackgroundResource(R.drawable.button_timer_green);
		
		}
		
/* Button Ballbesitz stellen */
		
	    	switch(possession) {
	    	
			case 1:
				btn_goals_home.setBackgroundResource(R.drawable.button_result_active);
				btn_goals_away.setBackgroundResource(R.drawable.button_result_inactive);
				break;
				
			case 0:
				btn_goals_away.setBackgroundResource(R.drawable.button_result_active);
				btn_goals_home.setBackgroundResource(R.drawable.button_result_inactive);
				break;
				
			case 2:
				btn_goals_home.setBackgroundResource(R.drawable.button_result_inactive);
				btn_goals_away.setBackgroundResource(R.drawable.button_result_inactive);
				break;
				
	    	}
	    	
/* Button Team und Tore Heim einrichten*/
	        
	    	btn_home.setOnClickListener(new View.OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View v) {
	    			
	        		if (possession == 2) {
	        			
	        			// Falls noch keine Mannschaft im Ballbesitz: Abfrage, Anwurf einrichten
	        			possession = 1;
			        	long lngNull = 0;
			        	setPossession(possession, lngNull, true);
	        			
	        		} else if (possession != 1) {
	        			
	        			if (stopped == true) {
	        				elapsedTime = time_sofar;
	        			}
	        			if (stopped == false) {
	        				elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
	        			}
	        			possession = 1;
	        			setPossession(possession, elapsedTime, false);
					
	        		}
	            }
	      });
	    	
	    	btn_goals_home.setOnClickListener(new View.OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View v) {
	    			
	        		if (possession == 2) {
	        			
	        			// Falls noch keine Mannschaft im Ballbesitz: Abfrage, Anwurf einrichten
			        	possession = 1;
			        	long lngNull = 0;
			        	setPossession(possession, lngNull, true);
	        			
	        		} else if (possession != 1) {
	        			
	        			if (stopped == true) {
	        				elapsedTime = time_sofar;
	        			}
	        			if (stopped == false) {
	        				elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
	        			}
	        			possession = 1;
	        			setPossession(possession, elapsedTime, false);
					
	        		}
	            }
	      });
	    	
/* Button Team und Tore Auswärts einrichten*/
	    	
	    	btn_away.setOnClickListener(new View.OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View v) {
	    			
	        		if (possession == 2) {
	        			
	        			// Falls noch keine Mannschaft im Ballbesitz: Abfrage, Anwurf einrichten
			        	possession = 0;
			        	long lngNull = 0;
			        	setPossession(possession, lngNull, true);
	        			
	        		} else if (possession != 0) {
	        			
	        			if (stopped == true) {
	        				elapsedTime = time_sofar;
	        			}
	        			if (stopped == false) {
	        				elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
	        			}
	        			possession = 0;
	        			setPossession(possession, elapsedTime, false);
					
	        		}
	            }
	      });
	    	
	    	btn_goals_away.setOnClickListener(new View.OnClickListener() {

	    		@Override
	    		public void onClick(View v) {
	    			
	        		if (possession == 2) {
	        			
	        			// Falls noch keine Mannschaft im Ballbesitz: Abfrage, Anwurf einrichten
	        			possession = 0;
			        	long lngNull = 0;
			        	setPossession(possession, lngNull, true);

	        		} else if (possession != 0) {
	        			
	        			if (stopped == true) {
	        				elapsedTime = time_sofar;
	        			}
	        			if (stopped == false) {
	        				elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
	        			}
	        			possession = 0;
	        			setPossession(possession, elapsedTime, false);
					
	        		}
	            }
	      });
	    	
/* Stoppuhr einrichten */
	    	
	    	btn_timer.setOnClickListener(new View.OnClickListener() {
	            
	    		@Override
	            public void onClick(View view) {
	            	
	    			timerStartStop();
	            	
	            }
	    	});
	    	
/* Uhr stellen */
/** TODO -3- => Anzeige der Uhr und Ergebnisse rund. Größe Ergebis abhängig von Anzahl der Tore */
	    	btn_timer.setOnLongClickListener(new OnLongClickListener() { 
	    		
	    		@Override
	    		public boolean onLongClick(View v) {
	    			
	    			// Stopuhr stoppen 
	    			if (time_start == 0) {
	    				elapsedTime = time_sofar;
	    			} else {
	    				elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
	    			}
	    			time_sofar = elapsedTime;
	    			time_start = 0;
	        		mHandler.removeCallbacks(startTimer);
	        		stopped = true;
	        		((Button) view.findViewById(R.id.btn_timer)).setBackgroundResource(R.drawable.button_timer_red);
	        		sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, time_sofar, time_start, null, null, null, null, null, null, null, null, null, null, null, null);
	        		
	        		// Fenster einrichten
	        		LayoutInflater li = LayoutInflater.from(ctxt);
	        		View promptsView = li.inflate(R.layout.box_set_watch, null);
	        		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctxt);
	        		alertDialogBuilder.setView(promptsView);
	        		final EditText editMinutes = (EditText) promptsView.findViewById(R.id.setStopWatchMinutes);
	        		final EditText editSeconds = (EditText) promptsView.findViewById(R.id.setStopWatchSeconds);
	        		// Aktuelle Zeit in Minuten und Sekunden umrechnen und in die Felder schreiben
	        		int min=0;
				int sec=0;
				min = (int) time_sofar / 1000 / 60;
				sec = (int) (time_sofar / 1000) - (min * 60);
				editMinutes.setText(String.valueOf(min));
				editSeconds.setText(String.valueOf(sec));
				
	        		// Dialog einrichten
	        		alertDialogBuilder
	        			.setCancelable(false)
	        			.setPositiveButton(R.string.set,
	        					new DialogInterface.OnClickListener() {
	        						public void onClick(DialogInterface dialog,	int id) {
	        							
									int min=0;
									int sec=0;
									if(editMinutes.getText().length() != 0){
										min=Integer.parseInt(editMinutes.getText().toString());
									} else{
										min=0;
									}
									if(editSeconds.getText().length() != 0){
										sec=Integer.parseInt(editSeconds.getText().toString());
									} else{
										sec=0;
									}
									if (sec>59) {
										sec=59;
									}
									elapsedTime = (long) (min * 60000) + (sec * 1000);
									time_sofar = elapsedTime;
									Button btn_timer = (Button) view.findViewById(R.id.btn_timer);
									btn_timer.setText(fctHelper.updateTimer(elapsedTime));
									
									/* Schreibe in die Datenbank, in welcher Halbzeit man sich befindet */
									if (min < sqlHelper.getGameDurationHalftimeByID(game_id)) {
										sqlHelper.updateGame(game_id, null, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
									}
									if (min <= sqlHelper.getGameDurationHalftimeByID(game_id) * 2 && min >= 30){
										sqlHelper.updateGame(game_id, null, null, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
									}
									if (min > sqlHelper.getGameDurationHalftimeByID(game_id) * 2) {
										sqlHelper.updateGame(game_id, null, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
									}
									
									if (screenInch > 6) {
										
										// Aktualisiere die Tickerliste
		  	    							// fragTickerList.refreshContent(game_id);
										
									} else {
										
										SmartTickerList activity = (SmartTickerList) ((ActivityGroup) ctxt).getLocalActivityManager().getActivity(res.getString(R.string.ticker));
										try {
											activity.refreshContent(game_id);
										} catch(Exception e){

										}
									}
	        						}
							});

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				return true;
				
	            }
	    	});
	    	
/* Überprüfen, ob vor dem letzten Aufruf die Halbzeitpause oder das Spielende erreicht wurde */
	    	
	    	if (!stopped) {											// Überprüfung nur notwenidg, wenn Zeit nicht gestoppt
			
	    		/* Wenn noch 1. Halbzeit und Zeit über der Halbzeitlänge */
			if (sqlHelper.getGameCurrentHalftimeByID(game_id) == 0 &&
					((System.currentTimeMillis() - time_start + time_sofar) / 60000) > sqlHelper.getGameDurationHalftimeByID(game_id)) {
				
				/* Dann Uhrzeit stoppen */
				mHandler.removeCallbacks(startTimer);
				((Button) view.findViewById(R.id.btn_timer)).setBackgroundResource(R.drawable.button_timer_red);
				/* Stoppuhr auf Halbzeit stellen */
				elapsedTime = (long) (sqlHelper.getGameDurationHalftimeByID(game_id)) * 60000;
				time_sofar = elapsedTime;
	    			time_start = 0;
				((TextView) view.findViewById(R.id.btn_timer)).setText(fctHelper.updateTimer(elapsedTime));
				sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, time_sofar, time_start, null, null, null, null, null, null, null, null, null, null, null, null);
		            
			}
			
			/* Wenn noch 2. Halbzeit und Zeit über Spielende */
			if(sqlHelper.getGameCurrentHalftimeByID(game_id) == 1 &&
					((System.currentTimeMillis() - time_start + time_sofar) / 60000) > (sqlHelper.getGameDurationHalftimeByID(game_id)) * 2) {
				
				/* Dann Uhrzeit stoppen */
				mHandler.removeCallbacks(startTimer);
				stopped = true;
				((Button) view.findViewById(R.id.btn_timer)).setBackgroundResource(R.drawable.button_timer_red);
				/* Stoppuhr auf Halbzeit stellen */
				elapsedTime = (long) (sqlHelper.getGameDurationHalftimeByID(game_id) * 2 * 60000);
				time_sofar = elapsedTime;
	    			time_start = 0;
				((TextView) view.findViewById(R.id.btn_timer)).setText(fctHelper.updateTimer(elapsedTime));
				sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, time_sofar, time_start, null, null, null, null, null, null, null, null, null, null, null, null);
				
			}
		}
	    	
/* Gelbe Karten einrichten*/
	    	
	    	setYellowCards();
	    	
	}
	
/*
 * 
 * Layout Ticker Menu einrichten 
 *
 */
			
	public void lytTickerMenu(String id, final View contentView, final FragmentManager contentFragmentManager, Bundle contentArgs, final SmartTickerActivity activity) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		game_id = id;
		smartActivity = activity;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper=new HelperSQL(ctxt);
		fctHelper=new HelperFunction();
		txtHelper=new HelperText();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
			
		}
		
/* Daten aus Datenbank laden */
	        
		home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
		away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
		team_home = sqlHelper.getGameTeamHomeByID(game_id);
		team_away = sqlHelper.getGameTeamAwayByID(game_id);
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Button einrichten */
		
		DisplayMetrics metrics = res.getDisplayMetrics();
		picSize = 700;
		if(metrics.density<2.1){
			// picSize=400;
			picSize = 225;
		} 
		if(metrics.density<1.6){
			picSize = 150;
		}
/** TODO -2- => Unter die Button auch Bezeichnung schreiben, z.B. "Torwurf" unter den Torwurfbutton */
		ImageButton btn_goal = (ImageButton) view.findViewById(R.id.menu_goal);
		btn_goal.setImageResource(R.drawable.activity_goal);
		scaleImage(btn_goal, picSize, "LinearLayout");
		
		ImageButton btn_goal_7m = (ImageButton) view.findViewById(R.id.menu_goal_7m);
		btn_goal_7m.setImageResource(R.drawable.activity_goal_7m);
		scaleImage(btn_goal_7m, picSize, "LinearLayout");
		
		ImageButton btn_goal_tg = (ImageButton) view.findViewById(R.id.menu_goal_tg);
		btn_goal_tg.setImageResource(R.drawable.activity_goal_fb);
		scaleImage(btn_goal_tg, picSize, "LinearLayout");
		
		ImageButton btn_yellow_card = (ImageButton) view.findViewById(R.id.menu_yellow_card);
		btn_yellow_card.setImageResource(R.drawable.activity_yellow_card);
		scaleImage(btn_yellow_card, picSize, "LinearLayout");
		
		ImageButton btn_miss = (ImageButton) view.findViewById(R.id.menu_miss);
		btn_miss.setImageResource(R.drawable.activity_miss);
		scaleImage(btn_miss, picSize, "LinearLayout");
		
		ImageButton btn_miss_7m = (ImageButton) view.findViewById(R.id.menu_miss_7m);
		btn_miss_7m.setImageResource(R.drawable.activity_miss_7m);
		scaleImage(btn_miss_7m, picSize, "LinearLayout");
		
		ImageButton btn_miss_tg = (ImageButton) view.findViewById(R.id.menu_miss_tg);
		btn_miss_tg.setImageResource(R.drawable.activity_miss_fb);
		scaleImage(btn_miss_tg, picSize, "LinearLayout");
		
		ImageButton btn_two_minutes=(ImageButton) view.findViewById(R.id.menu_two_minutes);
		btn_two_minutes.setImageResource(R.drawable.activity_two_minutes);
		scaleImage(btn_two_minutes, picSize, "LinearLayout");
		
		ImageButton btn_tech_fault = (ImageButton) view.findViewById(R.id.menu_tech_fault);
		btn_tech_fault.setImageResource(R.drawable.activity_tech_fault);
		scaleImage(btn_tech_fault, picSize, "LinearLayout");
		
		ImageButton btn_change = (ImageButton) view.findViewById(R.id.menu_change);
		btn_change.setImageResource(R.drawable.activity_change);
		scaleImage(btn_change, picSize, "LinearLayout");
		
		ImageButton btn_lineup = (ImageButton) view.findViewById(R.id.menu_lineup);
		btn_lineup.setImageResource(R.drawable.activity_lineup);
		scaleImage(btn_lineup, picSize, "LinearLayout");
		
		ImageButton btn_twoplustwo = (ImageButton) view.findViewById(R.id.menu_twoplustwo);
		btn_twoplustwo.setImageResource(R.drawable.activity_twoplustwo);
		scaleImage(btn_twoplustwo, picSize, "LinearLayout");
		
		ImageButton btn_defense = (ImageButton) view.findViewById(R.id.menu_defense);
		btn_defense.setImageResource(R.drawable.activity_defense);
		scaleImage(btn_defense, picSize, "LinearLayout");
		
		ImageButton btn_timeout = (ImageButton) view.findViewById(R.id.menu_timeout);
		btn_timeout.setImageResource(R.drawable.activity_timeout);
		scaleImage(btn_timeout, picSize, "LinearLayout");
		
		ImageButton btn_tactic = (ImageButton) view.findViewById(R.id.menu_tactic);
		btn_tactic.setImageResource(R.drawable.activity_tactic);
		scaleImage(btn_tactic, picSize, "LinearLayout");
		
		ImageButton btn_red_card = (ImageButton) view.findViewById(R.id.menu_red_card);
		btn_red_card.setImageResource(R.drawable.activity_red_card);
		scaleImage(btn_red_card, picSize, "LinearLayout");
				
/* Button verlinken */
		
		btn_goal.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = goal_id;
	            		activity_string = res.getString(R.string.goal);
				startActivity();
					
	            }
		});
		
		btn_goal_7m.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = goal_7m_id;
	            		activity_string = res.getString(R.string.seven_goal);
				startActivity();
					
	            }
		});
		
		btn_goal_tg.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = goal_fb_id;
	            		activity_string = res.getString(R.string.fb_goal);
				startActivity();
					
	            }
		});
		
		btn_miss.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = miss_id;
	            		activity_string = res.getString(R.string.miss);
				startActivity();
					
	            }
		});
		
		btn_miss_7m.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = miss_7m_id;
	            		activity_string = res.getString(R.string.seven_miss);
				startActivity();
					
	            }
		});
		
		btn_miss_tg.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = miss_fb_id;
	            		activity_string = res.getString(R.string.fb_miss);
				startActivity();
					
	            }
		});
		
		btn_tech_fault.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = tech_fault_id;
	            		activity_string = res.getString(R.string.tech_fault);
				startActivity();
				
	            }
		});
		
		btn_yellow_card.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = yellow_card_id;
	            		activity_string = res.getString(R.string.yellow_card);
				startActivity();
					
	            }
		});
		
		btn_two_minutes.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = two_minutes_id;
	            		activity_string = res.getString(R.string.two_minutes);
				startActivity();
					
	            }
		});
		
		btn_twoplustwo.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = twoplustwo_id;
	            		activity_string = res.getString(R.string.two_plus_two);
				startActivity();
					
	            }
		});
		
		btn_red_card.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = red_card_id;
	            		activity_string = res.getString(R.string.red_card);
				startActivity();
					
	            }
		});
		
		btn_change.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = sub_in_id;
	            		activity_string = res.getString(R.string.sub_in);
				startActivity();
					
	            }
		});
		
		btn_lineup.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = lineup_id;
	            		activity_string = res.getString(R.string.lineup);
	            		home_or_away = 1;
				startActivity();
					
	            }
		});
		
		btn_timeout.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = timeout_id;
	            		activity_string = res.getString(R.string.timeout);
	            		
	            		// DialogBox einrichten
				final Dialog dialog = new Dialog(ctxt);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				TextView title = (TextView) dialog.findViewById(R.id.title);
				TextView text = (TextView) dialog.findViewById(R.id.text);
				title.setText(R.string.timeout);
				text.setText(R.string.text_team_timeout);
					
				// Button definieren
				LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
				lyt_button3.removeAllViews();
				
				Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
				dialogButton1.setText(team_home);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.dismiss();
						activityTimeout(smartActivity, 1);
						
					}
				});
				
				Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
				dialogButton2.setText(team_away);
				
				dialogButton2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.dismiss();
						activityTimeout(smartActivity, 0);
						
					}
				});

				dialog.show();
				// Ende Dialogbox einrichten
					
	            }
		});
		
		btn_defense.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = defense_success_id;
	            		activity_string = res.getString(R.string.defense_successful);
	            		startActivity();
					
	            }
		});
		
		btn_tactic.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View view) {
	            	
	            		activity_id = tactic_60_id;
	            		activity_string = sqlHelper.getActivityStringByActivityID(activity_id, res);
	            		home_or_away = 1;
	            		startActivity();
					
	            }
		});
	}
	
/*
 * 
 * Layout Ticker Player Menu einrichten 
 *
 */
			
	public void lytTickerPlayer(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager, SmartTickerPlayer activity) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		tickerPlayerActivity = activity;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			fragTickerPlayer = (TabFragTickerPlayer) fragmentManager.findFragmentById(R.id.frag_ticker_setup);
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			activity_id = args.getInt("ActivityID");
			home_or_away = args.getInt("HomeOrAway");
			game_id = args.getString("GameID");
			strInput = args.getString("InputString");
			activity_string = sqlHelper.getActivityStringByActivityID(activity_id, res);
			team_home_short = sqlHelper.getGameTeamHomeShortByID(game_id);
			team_away_short = sqlHelper.getGameTeamAwayShortByID(game_id);
			team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			
			if (strInput.equals("Assist") || strInput.equals("EditAssist")) activity_string = res.getString(R.string.assist);

		}
		
/* Layout setzen */
		
		// Radio-Button definieren, falls Radio-Button vorhanden ist
		if ((RadioGroup) view.findViewById(R.id.radio_turnover) != null) {
			
			RadioButton radio_yes = (RadioButton) view.findViewById(R.id.radio_yes);
			radio_yes.setChecked(true);
			
		}
		
		// Überschrift setzen

		TextView headline=(TextView)view.findViewById(R.id.headline);
		headline.setText(activity_string);
		
		// Button einrichten und beschriften
		
		LinearLayout lyt_button = (LinearLayout) view.findViewById(R.id.lyt_button);
		btn_team_home = (Button) view.findViewById(R.id.home);
		btn_team_away = (Button) view.findViewById(R.id.away);
		btn_team_home.setText(team_home_short);
		btn_team_away.setText(team_away_short);
		
		if (home_or_away.equals(1)) {
			
			btn_team_home.setBackgroundResource(R.drawable.button_green);
			btn_team_away.setBackgroundResource(R.drawable.button_grey);

		}
		
		if (home_or_away.equals(0)) {
			
			btn_team_home.setBackgroundResource(R.drawable.button_grey);
			btn_team_away.setBackgroundResource(R.drawable.button_green);
			
		}
		
		// Button verlinken
		
		btn_team_home.setOnClickListener(new View.OnClickListener() {
			
			@Override
	            public void onClick(View v) {
				
				team_id = team_home_id;
				home_or_away = 1;
				args.putInt("HomeOrAway", home_or_away);
				btn_team_home.setBackgroundResource(R.drawable.button_green);
				btn_team_away.setBackgroundResource(R.drawable.button_grey);
				
				// Aktualisiere die Spielerliste
				if (screenInch > 6) {
					
					fragTickerPlayer.refreshContent(team_id, game_id);
					
				} else {
					
					tickerPlayerActivity.refreshContent(team_id, game_id, args);

				}	
			}
		});
		
		btn_team_away.setOnClickListener(new View.OnClickListener() {
			
			@Override
	            public void onClick(View v) {
				
				team_id = team_away_id;
				home_or_away = 0;
				args.putInt("HomeOrAway", home_or_away);
				btn_team_home.setBackgroundResource(R.drawable.button_grey);
				btn_team_away.setBackgroundResource(R.drawable.button_green);
				
				// Aktualisiere die Spielerliste
				if (screenInch > 6) {
					
					fragTickerPlayer.refreshContent(team_id, game_id);
					
				} else {
					
					tickerPlayerActivity.refreshContent(team_id, game_id, args);
					
				}	
			}
		});
		
		sub_out_id = res.getInteger(R.integer.sub_out_id);
		if (!strInput.equals("") && !strInput.equals("Miss") && !strInput.equals("Goal")) lyt_button.removeAllViews();
		if (activity_id.equals(sub_out_id)) lyt_button.removeAllViews();

	}
	

/*
 * 
 * Eingabe der Wurfecke 
 *
 */
		
	public void lytTickerArea(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager) {
		
/** TODO -1- => Wurfposition bei Siebenmeter automatisch auf den Siebenmeterpunkt setzen */
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		set_area = false;
		set_position = false;
		
/* Helper definieren */

		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			activity_id = args.getInt("ActivityID");
			ticker_activity_id = args.getInt("TickerActivityID");
			ticker_activity_against_id = args.getInt("TickerActivityAgainstID");
			ticker_event_id = args.getInt("TickerEventID");
			strInput = args.getString("InputString");
			if (strInput.equals("EditGoal") || strInput.equals("EditMiss") || strInput.equals("EditSave") || strInput.equals("EditGoalAgainst")) {
				area = sqlHelper.getTickerAreaByID(String.valueOf(ticker_activity_id));
			}
			x_coord = sqlHelper.getTickerFieldPositionXByID(String.valueOf(ticker_activity_id));
			y_coord = sqlHelper.getTickerFieldPositionYByID(String.valueOf(ticker_activity_id));

		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Button einrichten */
		
		Button btn_goal_uull = (Button) view.findViewById(R.id.goal_uull);
		Button btn_goal_uul = (Button) view.findViewById(R.id.goal_uul);
		Button btn_goal_uum = (Button) view.findViewById(R.id.goal_uum);
		Button btn_goal_uur = (Button) view.findViewById(R.id.goal_uur);
		Button btn_goal_uurr = (Button) view.findViewById(R.id.goal_uurr);
		Button btn_goal_ull = (Button) view.findViewById(R.id.goal_ull);
		Button btn_goal_ul = (Button) view.findViewById(R.id.goal_ul);
		Button btn_goal_um = (Button) view.findViewById(R.id.goal_um);
		Button btn_goal_ur = (Button) view.findViewById(R.id.goal_ur);
		Button btn_goal_urr = (Button) view.findViewById(R.id.goal_urr);
		Button btn_goal_mll = (Button) view.findViewById(R.id.goal_mll);
		Button btn_goal_ml = (Button) view.findViewById(R.id.goal_ml);
		Button btn_goal_mm = (Button) view.findViewById(R.id.goal_mm);
		Button btn_goal_mr = (Button) view.findViewById(R.id.goal_mr);
		Button btn_goal_mrr = (Button) view.findViewById(R.id.goal_mrr);
		Button btn_goal_lll = (Button) view.findViewById(R.id.goal_lll);
		Button btn_goal_ll = (Button) view.findViewById(R.id.goal_ll);
		Button btn_goal_lm = (Button) view.findViewById(R.id.goal_lm);
		Button btn_goal_lr = (Button) view.findViewById(R.id.goal_lr);
		Button btn_goal_lrr = (Button) view.findViewById(R.id.goal_lrr);
		final ImageButton btn_field = (ImageButton) view.findViewById(R.id.field);
		
/* Wurfecke auswählen */
		
		// Ausgewählten Button anzeigen, falls Auswahl vorhanden
		if (area != null) {
			
			if (strInput.equals("EditGoal") || strInput.equals("EditMiss") || strInput.equals("EditSave") || strInput.equals("EditGoalAgainst")) {

/** TODO -4- => Kann man sich die Aufzählung der Button sparen indem man die Wurfeck in die Bezeichnung einbindet, z.B. String button = "btn_goal_" + area und dann abfragt button.setText("X") */
				if (area.equals("uull")) btn_goal_uull.setText("X");
				if (area.equals("uul")) btn_goal_uul.setText("X");
				if (area.equals("uum")) btn_goal_uum.setText("X");
				if (area.equals("uur")) btn_goal_uur.setText("X");
				if (area.equals("uurr")) btn_goal_uurr.setText("X");
				if (area.equals("ull")) btn_goal_ull.setText("X");
				if (area.equals("ul")) btn_goal_ul.setText("X");
				if (area.equals("um")) btn_goal_um.setText("X");
				if (area.equals("ur")) btn_goal_ur.setText("X");
				if (area.equals("urr")) btn_goal_urr.setText("X");
				if (area.equals("mll")) btn_goal_mll.setText("X");
				if (area.equals("ml")) btn_goal_ml.setText("X");
				if (area.equals("mm")) btn_goal_mm.setText("X");
				if (area.equals("mr")) btn_goal_mr.setText("X");
				if (area.equals("mrr")) btn_goal_mrr.setText("X");
				if (area.equals("lll")) btn_goal_lll.setText("X");
				if (area.equals("ll")) btn_goal_ll.setText("X");
				if (area.equals("lm")) btn_goal_lm.setText("X");
				if (area.equals("lr")) btn_goal_lr.setText("X");
				if (area.equals("lrr")) btn_goal_lrr.setText("X");
			
			}
		} 
		
		btn_goal_uull.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("uull");
				}
			}
		});
		
		btn_goal_uul.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("uul");
				}
			}
		});
		
		btn_goal_uum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("uum");
				}
			}
		});
		
		btn_goal_uur.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("uur");
				}
			}
		});
		
		btn_goal_uurr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("uurr");
				}
			}
		});
		
		btn_goal_ull.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("ull");
				}
			}
		});
		
		btn_goal_ul.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("ul");
			}
		});
		
		btn_goal_um.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("um");
			}
		});
		
		btn_goal_ur.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("ur");
			}
		});
		
		btn_goal_urr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("urr");
				}
			}
		});
		
		btn_goal_mll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("mll");
				}
			}
		});
		
		btn_goal_ml.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("ml");
			}
		});
		
		btn_goal_mm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("mm");
			}
		});
		
		btn_goal_mr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("mr");
			}
		});
		
		btn_goal_mrr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("mrr");
				}
			}
		});
		
		btn_goal_lll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("lll");
				}
			}
		});
		
		btn_goal_ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("ll");
			}
		});
		
		btn_goal_lm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("lm");
			}
		});
		
		btn_goal_lr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finishArea("lr");
			}
		});
		
		btn_goal_lrr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id)  || activity_id.equals(miss_fb_id)) {
					finishArea("lrr");
				}
			}
		});
		
/* Wurfposition */
		
		// Wurfposition anzeigen
		
		view.post(new Runnable() {
			
			@Override
			public void run() {

				if (x_coord != null && y_coord != null) {
					
					int btn_width = btn_field.getWidth();
		  			int btn_height = btn_field.getHeight();
		  			int circle_radius = btn_width / 50;
					
					// recreate the new Bitmap   
		  			Bitmap bitMap = Bitmap.createBitmap(btn_width, btn_height, Bitmap.Config.ARGB_8888);
		  	  		bitMap = bitMap.copy(bitMap.getConfig(), true);
		  	  		Canvas canvas = new Canvas(bitMap);
		  	  		Paint paint = new Paint();
		  			paint.setColor(res.getColor(android.R.color.white));
			  		
			  		// Kreis zeichnen
			  		x_coord = x_coord * btn_width / 200;
			  		y_coord = y_coord * btn_height / 120;
					
			  		canvas.drawCircle (x_coord, y_coord, circle_radius, paint);
			  		btn_field.setImageBitmap(bitMap);
					
				}
			}
		});

		// Falls der Button geklickt wird: Position auslesen und in das Verhältnis von breite und Höhe setzen
		btn_field.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					
					// Koordinaten des Click Events und Höhe / Breite des Button bestimmen
					Integer btn_width = btn_field.getWidth();
					Integer btn_height = btn_field.getHeight();
					Integer btn_click_x = Math.round(event.getX());
					Integer btn_click_y = Math.round(event.getY());
					
					// Click Event ins Verhältnis zu Breite und Höhe setzen und in die Datenbank schreiben
					x_coord = btn_click_x * 200 / btn_width;
					// Bei den y-Koordinaten den Außenbereich abziehen
					Integer outfield = 10 * btn_height / 130;
					if (btn_click_y <= outfield) btn_click_y = outfield;
					y_coord = (btn_click_y - outfield) * 120 / (btn_height - outfield);
					
					//
					// Stelle des Klicks markieren
					//
		  			int circle_radius = btn_width / 50;
					
					// recreate the new Bitmap   
		  			Bitmap bitMap = Bitmap.createBitmap(btn_width, btn_height, Bitmap.Config.ARGB_8888);
		  	  		bitMap = bitMap.copy(bitMap.getConfig(), true);
		  	  		Canvas canvas = new Canvas(bitMap);
		  	  		Paint paint = new Paint();
		  			paint.setColor(res.getColor(android.R.color.white));
			  		
			  		// Kreis zeichnen
			  		int circle_x_coord = x_coord * btn_width / 200;
			  		int circle_y_coord = y_coord * btn_height / 120;
					
			  		canvas.drawCircle (circle_x_coord, circle_y_coord, circle_radius, paint);
			  		btn_field.setImageBitmap(bitMap);
					
			  		//
					// Wurfposition in Datenbank einfügen
					//
					if (strInput.equals("EditGoal") || strInput.equals("EditMiss") || strInput.equals("EditSave") || strInput.equals("EditGoalAgainst")) {
						
						if (ticker_activity_id != null) sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, null, null, x_coord, y_coord, null, null, null, null, null);
						
						// Die Wurfecke auch bei den übrigen Toraktionen des Tickereintrags ändern
			 			String[] tickerArgs={String.valueOf(ticker_event_id)};
						SQLiteDatabase db = sqlHelper.getWritableDatabase();
						Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? ORDER BY time ASC", tickerArgs);
						cTicker.moveToFirst();
						String ticker_id = null;
						
						// Alle Tickermeldungen abfragen und Wurfecke eintragen
						for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
							
							ticker_id = sqlHelper.getTickerActivityID(cTicker);
							if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_7m_id) ||	sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_fb_id) || 
									sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_7m_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_fb_id)) {
								sqlHelper.updateTickerActivity(String.valueOf(ticker_id), null, null, null, null, null, null, null, null, x_coord, y_coord, null, null, null, null, null);
							}
							
						}
						
						cTicker.close();
						
					} else {
						
						if (ticker_activity_id != null) sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, null, null, x_coord, y_coord, null, null, null, null, null);
						if (ticker_activity_against_id != null) sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_against_id), null, null, null, null, null, null, null, null, x_coord, y_coord, null, null, null, null, null);
					
						// InputString für weiterer Eingabe setzen
						if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id)) args.putString("InputString", "Goal");
						if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) args.putString("InputString", "Miss");
					}
					
					// Eingeben, dass Position gesetzt
					set_position = true;
					// Falls alle Eingaben gemacht => weitergehen
					if (set_area == true) leave_input_area();
					
				}
		            return true;
			}
		});
	}
	
/*
 * 
 * Layout Ticker Benotung einrichten 
 *
 */
				
	public void lytTickerMark(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
		
			
			
		}
		
		// Auflösung
		screenDensity=fctHelper.getScreenDensity(ctxt);
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			activity_id = args.getInt("ActivityID");
			ticker_activity_id = args.getInt("TickerActivityID");
			ticker_activity_against_id = args.getInt("TickerActivityAgainstID");
			strInput = args.getString("InputString");
		
		}
		
/* Layout setzen */
		
		// Texte
		TextView headline = (TextView) view.findViewById(R.id.headline);
		Button explanation_one = (Button) view.findViewById(R.id.explanation_one);
		Button explanation_two = (Button) view.findViewById(R.id.explanation_two);
		Button explanation_three = (Button) view.findViewById(R.id.explanation_three);
		Button explanation_four = (Button) view.findViewById(R.id.explanation_four);
		Button explanation_five = (Button) view.findViewById(R.id.explanation_five);
		
		if (strInput.equals("Goal") || strInput.equals("EditGoal")  || strInput.equals("EditMarkGoal")) {
			
			headline.setText(res.getString(R.string.mark_goal));
			explanation_one.setText(res.getString(R.string.explanation_one_goal));
			explanation_two.setText(res.getString(R.string.explanation_two_goal));
			explanation_three.setText(res.getString(R.string.explanation_three_goal));
			explanation_four.setText(res.getString(R.string.explanation_four_goal));
			explanation_five.setText(res.getString(R.string.explanation_five_goal));
			
		}
		
		if (strInput.equals("Miss") || strInput.equals("EditMiss")  || strInput.equals("EditMarkMiss")) {
			
			headline.setText(res.getString(R.string.mark_miss));
			explanation_one.setText(res.getString(R.string.explanation_one_miss));
			explanation_two.setText(res.getString(R.string.explanation_two_miss));
			explanation_three.setText(res.getString(R.string.explanation_three_miss));
			explanation_four.setText(res.getString(R.string.explanation_four_miss));
			explanation_five.setText(res.getString(R.string.explanation_five_miss));
			
		}
		
		if (strInput.equals("GoalAgainst") || strInput.equals("EditGoalAgainst")  || strInput.equals("EditMarkGoalAgainst")) {
			
			headline.setText(res.getString(R.string.mark_goal_against));
			explanation_one.setText(res.getString(R.string.explanation_one_goal_against));
			explanation_two.setText(res.getString(R.string.explanation_two_goal_against));
			explanation_three.setText(res.getString(R.string.explanation_three_goal_against));
			explanation_four.setText(res.getString(R.string.explanation_four_goal_against));
			explanation_five.setText(res.getString(R.string.explanation_five_goal_against));
			
		}
		
		if (strInput.equals("Save") || strInput.equals("EditSave") || strInput.equals("EditMarkSave")) {
			
			headline.setText(res.getString(R.string.mark_save));
			explanation_one.setText(res.getString(R.string.explanation_one_save));
			explanation_two.setText(res.getString(R.string.explanation_two_save));
			explanation_three.setText(res.getString(R.string.explanation_three_save));
			explanation_four.setText(res.getString(R.string.explanation_four_save));
			explanation_five.setText(res.getString(R.string.explanation_five_save));
			
		}
		
		if (strInput.equals("Assist") || strInput.equals("EditAssist") || strInput.equals("EditMarkAssist")) {
			
			headline.setText(res.getString(R.string.mark_assist));
			explanation_one.setText(res.getString(R.string.explanation_one_assist));
			explanation_two.setText(res.getString(R.string.explanation_two_assist));
			explanation_three.setText(res.getString(R.string.explanation_three_assist));
			explanation_four.setText(res.getString(R.string.explanation_four_assist));
			explanation_five.setText(res.getString(R.string.explanation_five_assist));
			
		}
		
		if (strInput.equals("Defense") || strInput.equals("EditDefense") || strInput.equals("EditMarkDefense")) {
			
			headline.setText(res.getString(R.string.mark_defense));
			explanation_one.setText(res.getString(R.string.explanation_one_defense));
			explanation_two.setText(res.getString(R.string.explanation_two_defense));
			explanation_three.setText(res.getString(R.string.explanation_three_defense));
			explanation_four.setText(res.getString(R.string.explanation_four_defense));
			explanation_five.setText(res.getString(R.string.explanation_five_defense));
			
		}
		
		if (strInput.equals("TechFault") || strInput.equals("EditTechFault") || strInput.equals("EditTechFault2") || strInput.equals("EditMarkTechFault")) {
			
			headline.setText(res.getString(R.string.mark_techfault));
			explanation_one.setText(res.getString(R.string.explanation_one_techfault));
			explanation_two.setText(res.getString(R.string.explanation_two_techfault));
			explanation_three.setText(res.getString(R.string.explanation_three_techfault));
			explanation_four.setText(res.getString(R.string.explanation_four_techfault));
			explanation_five.setText(res.getString(R.string.explanation_five_techfault));
			
		}

		if (strInput.equals("Activity")) {
			
			headline.setText(res.getString(R.string.mark_activity));
			explanation_one.setText(res.getString(R.string.explanation_one_activity));
			explanation_two.setText(res.getString(R.string.explanation_two_activity));
			explanation_three.setText(res.getString(R.string.explanation_three_activity));
			explanation_four.setText(res.getString(R.string.explanation_four_activity));
			explanation_five.setText(res.getString(R.string.explanation_five_activity));
			
		}
		
		// Button definieren
		Button btn_one = (Button) view.findViewById(R.id.mark_one);
		Button btn_two = (Button) view.findViewById(R.id.mark_two);
		Button btn_three = (Button) view.findViewById(R.id.mark_three);
		Button btn_four = (Button) view.findViewById(R.id.mark_four);
		Button btn_five = (Button) view.findViewById(R.id.mark_five);
		
		btn_one.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(1);	
			}
		});
		
		explanation_one.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(1);	
			}
		});
		
		btn_two.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(2);	
			}
		});
		
		explanation_two.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(2);	
			}
		});
		
		btn_three.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(3);	
			}
		});
		
		explanation_three.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(3);	
			}
		});
		
		btn_four.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(4);	
			}
		});
		
		explanation_four.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(4);	
			}
		});
		
		btn_five.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(5);	
			}
		});
		
		explanation_five.setOnClickListener(new View.OnClickListener() {	
			@Override
	            public void onClick(View v) {
				markActivity(5);	
			}
		});
		
	}

/*
 * 
 * Layout Tickertext  einrichten 
 *
 */
				
	public void lytTickerText(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
		
			
			
		}
		
		// Auflösung
		screenDensity=fctHelper.getScreenDensity(ctxt);
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			activity_id = args.getInt("ActivityID");
			ticker_event_id = args.getInt("TickerEventID");
			ticker_activity_id = args.getInt("TickerActivityID");
			ticker_activity_against_id = args.getInt("TickerActivityAgainstID");
			strInput = args.getString("InputString");
		
		}
		
/* Layout setzen */
		
		// Texte
		TextView headline = (TextView) view.findViewById(R.id.headline);
		TextView explanation = (TextView) view.findViewById(R.id.explanation);
		txt_ticker_event = (EditText) view.findViewById(R.id.ticker_text);
		
		if (strInput.equals("EventText")) {
			
			headline.setText(res.getString(R.string.ticker_message));
			explanation.setText(res.getString(R.string.ticker_message_explanation));
			if (sqlHelper.getTickerEventNoteByID(String.valueOf(ticker_event_id)) != null) {
				txt_ticker_event.setText(sqlHelper.getTickerEventNoteByID(String.valueOf(ticker_event_id)));
			}
			
		}
		
		if (strInput.equals("ActivityText")) {
			
			headline.setText(res.getString(R.string.ticker_note));
			explanation.setText(res.getString(R.string.ticker_note_explanation));
			if (sqlHelper.getTickerActivityNoteByID(String.valueOf(ticker_activity_id)) != null) {
				txt_ticker_event.setText(sqlHelper.getTickerActivityNoteByID(String.valueOf(ticker_activity_id)));
			}
			
		}
		
/* Button definieren */
		Button btn_okay = (Button) view.findViewById(R.id.btn_okay);
		
		btn_okay.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				ticker_note = txt_ticker_event.getText().toString();
				
				if (strInput.equals("ActivityText")) {
					
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, null, null, null, null, null, ticker_note, null, null, null);
					
					if(screenInch > 6) {
/** TODO -3- => Statistik => Spielaktivitäten => Notiz zur Spielaktion => Bei Statistikanzeige andere Bezeichnung des dritten Fragments beachten */
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
						fragTickerList.refreshContent(game_id);
					
						TabFragEmpty thirdFragment = new TabFragEmpty();
						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
					
					} else {
					
						((Activity)ctxt).finish();
					
					}
				}
				
				if (strInput.equals("EventText")) {
					
					sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null , null, ticker_note, null);
					
					if(screenInch > 6) {
					
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
						fragTickerList.refreshContent(game_id);
					
						TabFragEmpty thirdFragment = new TabFragEmpty();
						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
					
					} else {
					
						((Activity)ctxt).finish();
					
					}
				}
			}
		});
		
	}
	
/*
 * 
 * Liste mit Texten definieren 
 *
 */
				
	public void lytListWithText(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager, final SmartListWithText activity) {
		
		view = contentView;
		listActivity = activity;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		String[] values = null;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
		
			
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			// Get arguments
			game_id = args.getString("GameID");
			activity_id = args.getInt("ActivityID");
			ticker_activity_id = args.getInt("TickerActivityID");
			ticker_activity_against_id = args.getInt("TickerActivityAgainstID");
			strInput = args.getString("InputString");
			home_or_away = args.getInt("HomeOrAway");
			
			// Generiere Daten aus der Ticker ID
			if (!ticker_activity_id.equals(0)) {
				
				ticker_event_id = sqlHelper.getTickerEventIDByActivityID(String.valueOf(ticker_activity_id));
				if (activity_id.equals(0)) activity_id = sqlHelper.getTickerActivityIDByID(String.valueOf(ticker_activity_id));
				activity_string = sqlHelper.getActivityStringByActivityID(activity_id, res);
				
				player_id = sqlHelper.getTickerPlayerIDByID(String.valueOf(ticker_activity_id));
				if (player_id != null) {
					player_name = sqlHelper.getPlayerForenameByID(player_id) + " " + sqlHelper.getPlayerSurenameByID(player_id);
				} else {
					player_name = "N.N.";
				}
				
			}
			
			if (activity_id != null) activity_string = sqlHelper.getActivityStringByActivityID(activity_id, res);
			
			// Generiere Daten aus der Game ID
			if (game_id != null) {
				
				team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
				team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
				
			}
			if (team_home_id != null) team_home_short = sqlHelper.getTeamClubShortByID(team_home_id);
			if (team_away_id != null) team_away_short = sqlHelper.getTeamClubShortByID(team_away_id);
		
		}
		
/* Layout setzen */
		
		// Layoutfelder definieren und Inhalte setzen
		
		TextView headline = (TextView) view.findViewById(R.id.headline);
		TextView text = (TextView) view.findViewById(R.id.text);
		LinearLayout lyt_text = (LinearLayout) view.findViewById(R.id.lyt_text);
		LinearLayout lyt_headline = (LinearLayout) view.findViewById(R.id.lyt_headline);
		LinearLayout lyt_button = (LinearLayout) view.findViewById(R.id.lyt_button);
		LinearLayout lyt_button_all = (LinearLayout) view.findViewById(R.id.lyt_button_all);
		lyt_button_all.removeAllViews();
		
		if (strInput.equals("TeamAdd")) {
			headline.setText(res.getString(R.string.team_add)); 
			text.setText(res.getString(R.string.text_team_online_offline));
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("Goal") || strInput.equals("Miss")) {
			headline.setText(res.getString(R.string.throwing_technique)); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("Defense") || strInput.equals("EditDefense2")) {
			headline.setText(res.getString(R.string.defense)); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("TechFault") || strInput.equals("EditTechFault") || strInput.equals("EditTechFault2")) {
			headline.setText(res.getString(R.string.tech_fault)); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		/* Da "ListWithText" sowohl angesprochen wird, wenn eine 
		 * Tickermeldung aktiviert wird, als auch, wenn im Editier-
		 * bildschirm der Punkt Taktik ausgewählt wurde, muss 
		 * zwischen EditTactic und EditTactic2 unterschieden werden. 
		 */
		if (strInput.equals("Tactic") || strInput.equals("EditTactic2")) {
			
			headline.setText(res.getString(R.string.tactic));
			lyt_text.removeAllViews();
			if (strInput.equals("Tactic")) {
				// Button Heim und Auswärts einrichten
				btn_team_home = (Button) view.findViewById(R.id.home);
				btn_team_away = (Button) view.findViewById(R.id.away);
				
				btn_team_home.setText(team_home_short); 
				btn_team_away.setText(team_away_short); 
				
				if (home_or_away.equals(1)) {
					
					btn_team_home.setBackgroundResource(R.drawable.button_green);
					btn_team_away.setBackgroundResource(R.drawable.button_grey);

				}
				
				if (home_or_away.equals(0)) {
					
					btn_team_home.setBackgroundResource(R.drawable.button_grey);
					btn_team_away.setBackgroundResource(R.drawable.button_green);
					
				}
				
				btn_team_home.setOnClickListener(new View.OnClickListener() {
					
					@Override
			            public void onClick(View v) {

						home_or_away = 1;
						btn_team_home.setBackgroundResource(R.drawable.button_green);
						btn_team_away.setBackgroundResource(R.drawable.button_grey);
						
					}
				});
				
				btn_team_away.setOnClickListener(new View.OnClickListener() {
					
					@Override
			            public void onClick(View v) {

						home_or_away = 0;
						btn_team_home.setBackgroundResource(R.drawable.button_grey);
						btn_team_away.setBackgroundResource(R.drawable.button_green);
						
					}
				});
			} else {
				lyt_button.removeAllViews();
				strInput = "Tactic";
				args.putString("InputString", "Tactic");
			}
		}
		
		if (strInput.equals("EditGoal") || strInput.equals("EditMiss") || strInput.equals("EditSave") || strInput.equals("EditGoalAgainst")) {
			String txt_headline = activity_string + " " + res.getString(R.string.edit);
			headline.setText(txt_headline); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("EditPossession")) {
			String txt_headline = res.getString(R.string.possession) + " " + res.getString(R.string.edit);
			headline.setText(txt_headline); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("EditAssist") | strInput.equals("EditAssist2")) {
			String txt_headline = res.getString(R.string.assist) + " " + res.getString(R.string.edit);
			headline.setText(txt_headline); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("EditDefense")) {
			String txt_headline = res.getString(R.string.defense) + " " + res.getString(R.string.edit);
			headline.setText(txt_headline); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("EditThrowingTechnique")) {
			headline.setText(res.getString(R.string.throwing_technique));
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("DelActivity")) {
			headline.setText(res.getString(R.string.delete_activity)); 
			text.setText(res.getString(R.string.text_delete_activity)); 
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("DelEvent")) {
			headline.setText(res.getString(R.string.delete_event)); 
			text.setText(res.getString(R.string.text_delete_event)); 
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("EditPlayer")) {
			String txt_headline = activity_string + " " + res.getString(R.string.edit);
			headline.setText(txt_headline); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("EditTimeout")) {
			String txt_headline = activity_string + " " + res.getString(R.string.edit);
			headline.setText(txt_headline); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("EditTactic")) {
			String txt_headline = res.getString(R.string.tactic) + " " + res.getString(R.string.edit);
			headline.setText(txt_headline); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("StatOverview")) {
			headline.setText(res.getString(R.string.overview)); 
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
		
		if (strInput.equals("TabStatOverview")) {
			lyt_headline.removeAllViews();
			lyt_text.removeAllViews();
			lyt_button.removeAllViews();
		}
	
		// Zelltexte anlegen
		
		if (strInput.equals("TeamAdd")) {
			values = new String[] { res.getString(R.string.team_choose_online), 
									res.getString(R.string.Local), 
									res.getString(R.string.Local_fast)};
		}
		if (strInput.equals("Goal") || strInput.equals("Miss") || strInput.equals("EditThrowingTechnique")) {
			values = new String[] { res.getString(R.string.Sprungwurf), 
									res.getString(R.string.Schlagwurf), 
									res.getString(R.string.Laufwurf), 
									res.getString(R.string.Fallwurf), 
									res.getString(R.string.Hüftwurf), 
									res.getString(R.string.kempa), 
									res.getString(R.string.Dreher), 
									res.getString(R.string.Heber), 
									res.getString(R.string.Leger), 
									res.getString(R.string.Abknickwurf)};
		}
		if (strInput.equals("EditAssist2")) {
			values = new String[] { res.getString(R.string.assist_goal), 
									res.getString(R.string.assist)};
		}
		if (strInput.equals("Defense") || strInput.equals("EditDefense2")) {
			values = new String[] { res.getString(R.string.defense_successful), 
									res.getString(R.string.defensive_error),
									res.getString(R.string.block_successful),
									res.getString(R.string.block_error),
									res.getString(R.string.foul)};
		}
		if (strInput.equals("TechFault") || strInput.equals("EditTechFault2")) {
			values = new String[] { res.getString(R.string.Fehlpass), 
									res.getString(R.string.steps),
									res.getString(R.string.three_seconds_rule),
									res.getString(R.string.Doppeldribbel),
									res.getString(R.string.Fuss),
									res.getString(R.string.Zeitspiel),
									res.getString(R.string.Kreis),
									res.getString(R.string.Stuermerfoul)};
		}
		if (strInput.equals("Tactic")) {
			
			values = new String[] { res.getString(R.string.tactic_60), 
									res.getString(R.string.tactic_51),
									res.getString(R.string.tactic_42),
									res.getString(R.string.guarding),
									res.getString(R.string.tactic_321)};
		}
		if (strInput.equals("EditGoal") || strInput.equals("EditMiss")) {

			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									player_name,
									res.getString(R.string.goal_area),
									res.getString(R.string.throw_position),
									res.getString(R.string.throwing_technique),
									res.getString(R.string.mark),
									res.getString(R.string.add_assist),
									res.getString(R.string.add_defense),
									res.getString(R.string.input_text),
									res.getString(R.string.memo),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("EditSave") || strInput.equals("EditGoalAgainst")) {
			
			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									player_name,
									res.getString(R.string.goal_area),
									res.getString(R.string.throw_position),
									res.getString(R.string.mark),
									res.getString(R.string.input_text),
									res.getString(R.string.memo),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("EditPossession")) {
			
			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									res.getString(R.string.input_text),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("EditAssist")) {
			
			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									player_name,
									activity_string,
									res.getString(R.string.mark),
									res.getString(R.string.input_text),
									res.getString(R.string.memo),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("EditDefense")) {
			
			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									player_name,
									activity_string,
									res.getString(R.string.mark),
									res.getString(R.string.input_text),
									res.getString(R.string.memo),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("EditPlayer")) {
			
			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									player_name,
									res.getString(R.string.input_text),
									res.getString(R.string.memo),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("EditTimeout")) {
			
			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									res.getString(R.string.input_text),
									res.getString(R.string.memo),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("EditTechFault")) {
			
			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									player_name,
									res.getString(R.string.tech_fault_define),
									res.getString(R.string.mark),
									res.getString(R.string.input_text),
									res.getString(R.string.memo),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("EditTactic")) {
			
			values = new String[] { res.getString(R.string.playing_time) + " " + fctHelper.updateTimer(sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id))), 
									res.getString(R.string.tactic),
									res.getString(R.string.input_text),
									res.getString(R.string.memo),
									res.getString(R.string.delete_activity),
									res.getString(R.string.delete_event)};
			
		}
		if (strInput.equals("DelActivity") || strInput.equals("DelEvent")) {
			
			values = new String[] { 	res.getString(R.string.yes),
									res.getString(R.string.no)};
			
		}
		
		if (strInput.equals("StatOverview")) {
			
			values = new String[] { 	res.getString(R.string.game_statistic),
									res.getString(R.string.score_chart),
									res.getString(R.string.player_statistic) + " " + team_home_short,
									res.getString(R.string.player_statistic) + " " + team_away_short,
									res.getString(R.string.activities),
									res.getString(R.string.ticker),
									res.getString(R.string.export)};
			
		}
		
		if (strInput.equals("TabStatOverview")) {
			
			values = new String[] { 	res.getString(R.string.game_statistic),
									res.getString(R.string.score_chart),
									res.getString(R.string.player_statistic) + " " + team_home_short,
									res.getString(R.string.player_statistic) + " " + team_away_short,
									res.getString(R.string.activities),
									res.getString(R.string.ticker),
									res.getString(R.string.export)};
			
		}
		
		HelperAdapterSimpleText adapter = new HelperAdapterSimpleText(ctxt, values);
		
		if (screenInch > 6) {
			
			if (strInput.equals("TeamAdd")) {
				fragListWithText = (TabFragListWithText) fragmentManager.findFragmentById(R.id.frag_team_edit);
			} else {
				if (strInput.equals("TabStatOverview")) {
					fragListWithText = (TabFragListWithText) fragmentManager.findFragmentById(R.id.frag_stat_list);
				} else {
					fragListWithText = (TabFragListWithText) fragmentManager.findFragmentById(R.id.frag_ticker_setup);
				}
			}
			fragListWithText.setListAdapter(adapter);
			
		} else {
			
			listActivity.setListAdapter(adapter);
			
		}		
	}
	
/*
 * 
 * Auswahl in der Liste mit Texten definieren 
 *
 */
				
	public void lytListWithTextClick(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager, int position) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
		
			
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			activity_id = args.getInt("ActivityID");
			ticker_activity_id = args.getInt("TickerActivityID");
			if (ticker_activity_id != null) {
				ticker_event_id = sqlHelper.getTickerEventIDByActivityID(String.valueOf(ticker_activity_id));
				if (ticker_event_id != null) args.putInt("TickerEventID", ticker_event_id);
			}
			ticker_activity_against_id = args.getInt("TickerActivityAgainstID");
			ticker_defense_id = args.getInt("TickerDefenseID");
			ticker_assist_id = args.getInt("TickerAssistID");
			strInput = args.getString("InputString");
			if (strInput.length() > 3) { 
				if (strInput.substring(0,4).equals("Edit")) {
					activity_ticker_time = sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id));
					realtime = sqlHelper.getTickerRealtimeByActivityID(String.valueOf(ticker_activity_id));
					possession = sqlHelper.getTickerHomeOrAwayByID(String.valueOf(ticker_activity_id));
				}
			}
		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Verlinkung einrichten */
		
		if (strInput.equals("TeamAdd")) {
			
			switch(position) {
			case 0:
				// Öffne Team Auswahl online
				
				mPreferences = ctxt.getSharedPreferences("CurrentUser", ctxt.MODE_PRIVATE);
				String auth_token = mPreferences.getString("AuthToken", "");
				
				if (auth_token.equals("")) {
					
					// Nachrichtenbox einrichten
					final Dialog dialog = new Dialog(ctxt);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.synchro);
					text.setText(R.string.text_login_not_possible);
					
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
							finishActivity(team_id, args);
						}
					});

					dialog.show();
					// Ende Nachrichtenbox
				
				} else {
					
					if (screenInch > 6) {
						
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTeamOnline fragment = new TabFragTeamOnline();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_team_edit, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						i = new Intent(ctxt, SmartTeamOnline.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();
						
					}
					
				}

				break;
			case 1:
				// Öffne Team Auswahl Offline
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTeamEdit fragment = new TabFragTeamEdit();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_team_edit, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTeamEdit.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				
				break;
			case 2:
				// Öffne Team Auswahl Offline (schnell)
				sqlHelper.addFastTeam(ctxt);
				team_id = sqlHelper.getLastTeamID();
				args.putString("TeamID", team_id);
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTeamEdit fragment = new TabFragTeamEdit();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_team_edit, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTeamEdit.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				
				break;
			}
			
		}
		
		if (strInput.equals("Goal") || strInput.equals("Miss") || strInput.equals("EditThrowingTechnique")) {

			// ID für die Wurftechnik eingeben
			sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, null, null, null, null, position+180, null, null, null, null);
			
			ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
			sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
			
			if (strInput.equals("EditThrowingTechnique")) {
				
				if (screenInch > 6) {
/** TODO -3- => Kontrollieren, ob diese Funktion funktioniert: Tablet beim Editieren der Wurftechnik die Spielleiste aktualisieren und das Setup-Bildschirm löschen */
					// Aktualisiere die Spielliste
					fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
					fragTickerList.refreshContent(game_id);
					
					TabFragEmpty thirdFragment = new TabFragEmpty();
					fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
					
				} else {
					
					((Activity)ctxt).finish();
					
				}
				
			} else {
				
			// Abfrage nach weiterer Eingabe
			if (screenInch > 6) {
				
				// Benotung für den Wurf
				if (sqlHelper.getGameInputMark(game_id).equals(1)) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerMark fragment = new TabFragTickerMark();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					// Vorlage
					if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
						
						args.putString("InputString", "Assist");
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerPlayer fragment = new TabFragTickerPlayer();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						// Abwehr
						if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
							
							args.putString("InputString", "Defense");
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							TabFragTickerPlayer fragment = new TabFragTickerPlayer();
							fragment.setArguments(args);
							fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
							fragmentTransaction.commit();
							
						}  else {
							
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
								args.putString("InputString", "EventText");
								FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
								TabFragTickerText fragment = new TabFragTickerText();
								fragment.setArguments(args);
								fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
								fragmentTransaction.commit();
								
							} else {
								
								// Aktualisiere die Spielliste
								fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
								fragTickerList.refreshContent(game_id);
	    						
								TabFragEmpty thirdFragment = new TabFragEmpty();
								fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
								
							}
						}
					}
				}

			} else {
				
				// Benotung für den Wurf
				if (sqlHelper.getGameInputMark(game_id).equals(1)) {
					Log.v("HelperLayout", "Mark 1");
					Intent i = new Intent(ctxt, SmartTickerMark.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				} else {
					
					// Vorlage
					if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
						
						Intent i = new Intent(ctxt, SmartTickerPlayer.class);
						args.putString("InputString", "Assist");
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();
						
					} else {
						
						// Abwehr
						if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
							
							Intent i = new Intent(ctxt, SmartTickerPlayer.class);
							args.putString("InputString", "Defense");
							i.putExtras(args);
							((Activity)ctxt).startActivity(i);
							((Activity)ctxt).finish();
							
						} else {
							
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
								args.putString("InputString", "EventText");
								Intent i = new Intent(ctxt, SmartTickerText.class);
								i.putExtras(args);
								((Activity)ctxt).startActivity(i);
								((Activity)ctxt).finish();
								
							} else {
								((Activity)ctxt).finish();
								
							}
						}
					} 
				}	
			}
			}
		}
		
		if (strInput.equals("EditAssist2")) {
			
			switch(position) {
			case 0:
				sqlHelper.updateTickerActivity(String.valueOf(ticker_assist_id), null, null, null, null, assist_goal_id, null, null, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(assist_goal_id,  res), null, null);
				break;
			case 1:
				sqlHelper.updateTickerActivity(String.valueOf(ticker_assist_id), null, null, null, null, assist_miss_id, null, null, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(assist_miss_id,  res), null, null);
				break;
			}
			
			if (screenInch > 6) {
				
				// Aktualisiere die Spielliste
				fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
				fragTickerList.refreshContent(game_id);
					
				TabFragEmpty thirdFragment = new TabFragEmpty();
				fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
				
			} else {
				
				((Activity)ctxt).finish();
				
			}
		}
		if (strInput.equals("Defense") || strInput.equals("EditDefense2")) {
			
			switch(position) {
			case 0:
				sqlHelper.updateTickerActivity(String.valueOf(ticker_defense_id), null, null, null, null, defense_success_id, null, null, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(defense_success_id,  res), null, null);
				break;
			case 1:
				sqlHelper.updateTickerActivity(String.valueOf(ticker_defense_id), null, null, null, null, defense_error_id, null, null, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(defense_error_id,  res), null, null);
				break;
			case 2:
				sqlHelper.updateTickerActivity(String.valueOf(ticker_defense_id), null, null, null, null, block_success_id, null, null, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(block_success_id,  res), null, null);
				break;
			case 3:
				sqlHelper.updateTickerActivity(String.valueOf(ticker_defense_id), null, null, null, null, block_error_id, null, null, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(block_error_id,  res), null, null);
				break;
			case 4:
				sqlHelper.updateTickerActivity(String.valueOf(ticker_defense_id), null, null, null, null, foul_id, null, null, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(foul_id,  res), null, null);
				break;
			}
			
			ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
			sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);

			if (!strInput.equals("EditDefense2")) {
				if (sqlHelper.getGameInputMark(game_id).equals(1)) {											// Benotung
				
					args.putString("InputString", "Defense");
				
					if (screenInch > 6) {
					
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerMark fragment = new TabFragTickerMark();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
					
					} else {
						Log.v("HelperLayout", "Mark 2");
						Intent i = new Intent(ctxt, SmartTickerMark.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();
					
					}
				} else {
				
					if (screenInch > 6) {
					
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
						fragTickerList.refreshContent(game_id);
						
						TabFragEmpty thirdFragment = new TabFragEmpty();
						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
					
					}
				}
			} else {
				
				if (screenInch > 6) {
					
					// Aktualisiere die Spielliste
					fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
					fragTickerList.refreshContent(game_id);
						
					TabFragEmpty thirdFragment = new TabFragEmpty();
					fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
					
				} else {
					
					((Activity)ctxt).finish();
					
				}
			}
		}
		
		if (strInput.equals("TechFault") || strInput.equals("EditTechFault2")) {
			
			sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, tech_fault_id + position + 1, null, null, null, null, null, null, null, null, null, null);
			
			ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
			sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
			
			if (!strInput.equals("EditTechFault2")) {
				if (sqlHelper.getGameInputMark(game_id).equals(1)) {			// Benotung
				
					if (screenInch > 6) {
					
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerMark fragment = new TabFragTickerMark();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
					
					} else {
						Log.v("HelperLayout", "Mark 3");
						Intent i = new Intent(ctxt, SmartTickerMark.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();

					}
				} else {
				
					if (screenInch > 6) {
					
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
						fragTickerList.refreshContent(game_id);
						
						TabFragEmpty thirdFragment = new TabFragEmpty();
						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
					
					} else {
						
						((Activity)ctxt).finish();
						
					}
				}
			} else {
				
				if (screenInch > 6) {
					
					// Aktualisiere die Spielliste
					fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
					fragTickerList.refreshContent(game_id);
						
					TabFragEmpty thirdFragment = new TabFragEmpty();
					fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
					
				} else {
					
					((Activity)ctxt).finish();
					
				}
			}
		}
		
		if (strInput.equals("Tactic")) {
			
			sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, tactic_60_id + position, home_or_away, null, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(tactic_60_id + position, res), null, null);
			
			ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
			sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
			
			if (screenInch > 6) {
				
				// Aktualisiere die Spielliste
				fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
				fragTickerList.refreshContent(game_id);
					
				TabFragEmpty thirdFragment = new TabFragEmpty();
				fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
				
			} else {
				
				((Activity)ctxt).finish();
				
			}
		}
		
		if (strInput.equals("EditGoal") || strInput.equals("EditMiss")) {
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerTime fragment = new TabFragTickerTime();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 2:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerArea fragment = new TabFragTickerArea();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerArea.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 3:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerArea fragment = new TabFragTickerArea();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerArea.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 4:								// throwing_technique
				
				args.putString("InputString", "EditThrowingTechnique");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();		
					
				} else {
						
					Intent i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 5:								// Benotung
				
				if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id)) args.putString("InputString", "EditMarkGoal");
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) args.putString("InputString", "EditMarkMiss");
				if (activity_id.equals(goal_against_id) || activity_id.equals(goal_against_7m_id) || activity_id.equals(goal_against_fb_id)) args.putString("InputString", "EditMarkGoalAgainst");
				if (activity_id.equals(save_id) || activity_id.equals(save_7m_id) || activity_id.equals(save_fb_id)) args.putString("InputString", "EditMarkGoalAgainst");
				if (activity_id.equals(assist_goal_id) || activity_id.equals(assist_miss_id)) args.putString("InputString", "EditMarkAssist");
				if (activity_id.equals(defense_error_id) || activity_id.equals(defense_success_id) || activity_id.equals(block_error_id) || activity_id.equals(block_success_id) || activity_id.equals(foul_id)) args.putString("InputString", "EditMarkDefense");
				if (activity_id.equals(tech_fault_id) || activity_id.equals(fehlpass_id) || activity_id.equals(steps_id) || activity_id.equals(three_seconds_id) || activity_id.equals(doppeldribbel_id) || activity_id.equals(fuss_id) || activity_id.equals(zeitspiel_id) || activity_id.equals(kreis_id) || activity_id.equals(stuermerfoul_id)) args.putString("InputString", "EditMarkTechFault");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerMark fragment = new TabFragTickerMark();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					Log.v("HelperLayout", "Mark 4");
					Intent i = new Intent(ctxt, SmartTickerMark.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 6:								// add_assist
				
				if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id)) activity_id =  assist_goal_id; activity_string = res.getString(R.string.assist_goal);
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) activity_id =  assist_miss_id; activity_string = res.getString(R.string.assist);
				sqlHelper.insertTickerActivity(game_id, ticker_event_id, activity_ticker_time + 6, realtime, activity_id, possession, null, null, null, null, null, activity_string, null);
				ticker_activity_id = sqlHelper.getLastTickerActivityID();
				args.putString("InputString", "EditAssist");
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putInt("HomeOrAway", home_or_away);
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 7:								// add_defense
				
				if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id)) activity_id =  defense_error_id;
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) activity_id =  defense_success_id;
				if (possession.equals(0)) possession_against = 1;
				if (possession.equals(1)) possession_against = 0;
				sqlHelper.insertTickerActivity(game_id, ticker_event_id, activity_ticker_time + 6, realtime, activity_id, possession_against, null, null, null, null, null, sqlHelper.getActivityStringByActivityID(activity_id, res), null);
				ticker_activity_id = sqlHelper.getLastTickerActivityID();
				args.putString("InputString", "EditDefense");
				args.putInt("TickerActivityID", ticker_activity_id);
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 8:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putString("InputString", "EventText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 9:							// Notiz
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();		
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 10:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 11:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}

		if (strInput.equals("EditSave") || strInput.equals("EditGoalAgainst")) {
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerTime fragment = new TabFragTickerTime();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();		
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 2:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerArea fragment = new TabFragTickerArea();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();			
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerArea.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 3:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerArea fragment = new TabFragTickerArea();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerArea.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 4:								// Benotung
				
				if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id)) args.putString("InputString", "EditMarkGoal");
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) args.putString("InputString", "EditMarkMiss");
				if (activity_id.equals(goal_against_id) || activity_id.equals(goal_against_7m_id) || activity_id.equals(goal_against_fb_id)) args.putString("InputString", "EditMarkGoalAgainst");
				if (activity_id.equals(save_id) || activity_id.equals(save_7m_id) || activity_id.equals(save_fb_id)) args.putString("InputString", "EditMarkGoalAgainst");
				if (activity_id.equals(assist_goal_id) || activity_id.equals(assist_miss_id)) args.putString("InputString", "EditMarkAssist");
				if (activity_id.equals(defense_error_id) || activity_id.equals(defense_success_id) || activity_id.equals(block_error_id) || activity_id.equals(block_success_id) || activity_id.equals(foul_id)) args.putString("InputString", "EditMarkDefense");
				if (activity_id.equals(tech_fault_id) || activity_id.equals(fehlpass_id) || activity_id.equals(steps_id) || activity_id.equals(three_seconds_id) || activity_id.equals(doppeldribbel_id) || activity_id.equals(fuss_id) || activity_id.equals(zeitspiel_id) || activity_id.equals(kreis_id) || activity_id.equals(stuermerfoul_id)) args.putString("InputString", "EditMarkTechFault");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerMark fragment = new TabFragTickerMark();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					Log.v("HelperLayout", "Mark 5");
					Intent i = new Intent(ctxt, SmartTickerMark.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 5:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putString("InputString", "EventText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 6:							// Notiz
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 7:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 8:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}
		
		if (strInput.equals("EditPossession")) {
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerTime fragment = new TabFragTickerTime();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 2:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 3:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}
		
		if (strInput.equals("EditAssist")) {
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerTime fragment = new TabFragTickerTime();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:								// Spieler
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();		
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 2:								// Art der Vorlage
				
				ticker_assist_id = ticker_activity_id;
				args.putInt("TickerAssistID", ticker_assist_id);
				args.putString("InputString", "EditAssist2");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 3:								// Benotung
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerMark fragment = new TabFragTickerMark();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					Log.v("HelperLayout", "Mark 6");
					Intent i = new Intent(ctxt, SmartTickerMark.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 4:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putString("InputString", "EventText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 5:							// Notiz
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 6:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 7:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}
		
		if (strInput.equals("EditDefense")) {
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerTime fragment = new TabFragTickerTime();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 2:								// Auswahl der Abwehrart
				
				ticker_defense_id = ticker_activity_id;
				args.putInt("TickerDefenseID", ticker_defense_id);
				args.putString("InputString", "EditDefense2");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 3:								// Benotung
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					Log.v("HelperLayout", "Mark 7");
					Intent i = new Intent(ctxt, SmartTickerMark.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 4:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putString("InputString", "EventText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 5:							// Notiz
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 6:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 7:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}
		
		if (strInput.equals("EditTechFault")) {
			
			args.putString("InputString", "EditTechFault2");
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerTime fragment = new TabFragTickerTime();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 2:								// Auswahl des technischen Fehlers
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 3:								// Benotung
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerMark fragment = new TabFragTickerMark();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					Log.v("HelperLayout", "Mark 8");
					Intent i = new Intent(ctxt, SmartTickerMark.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 4:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putString("InputString", "EventText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 5:							// Notiz
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 6:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 7:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}
		
		if (strInput.equals("EditPlayer")) {
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 2:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putString("InputString", "EventText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 3:							// Notiz
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 4:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 5:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}
		
		if (strInput.equals("EditTimeout")) {
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putString("InputString", "EventText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 2:							// Notiz
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();	
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 3:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 4:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}
		
		if (strInput.equals("EditTactic")) {
			
			switch(position) {
			case 0:
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerTime fragment = new TabFragTickerTime();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
						
					Intent i = new Intent(ctxt, SmartTickerTime.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();

				}
				break;
			case 1:								// Auswahl der Taktik
				
				/* Da "ListWithText" sowohl angesprochen wird, wenn eine 
				 * Tickermeldung aktiviert wird, als auch, wenn im Editier=
				 * bildschirm der Punkt Taktik ausgewählt wurde, muss 
				 * zwischen EditTactic und EditTactic2 unterschieden werden. 
				 */
				args.putString("InputString", "EditTactic2");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 2:							// Tickermeldung
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putString("InputString", "EventText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 3:							// Notiz
				
				args.putInt("TickerEventID", ticker_event_id);
				args.putInt("TickerActivityID", ticker_activity_id);
				args.putString("InputString", "ActivityText");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerText fragment = new TabFragTickerText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartTickerText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 4:						// delete_activity
				
				args.putString("InputString", "DelActivity");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			case 5:						// delete_event
				
				args.putString("InputString", "DelEvent");
				
				if (screenInch > 6) {
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
					
				}
				break;
			}
		}
		
		if (strInput.equals("DelActivity") || strInput.equals("DelEvent")) {
			
			switch(position) {
			case 0:
				
				if (strInput.equals("DelActivity")) {
					
					sqlHelper.deleteTickerActivity(String.valueOf(ticker_activity_id));
					
					if (screenInch > 6) {
						
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
						fragTickerList.refreshContent(game_id);
							
						TabFragEmpty thirdFragment = new TabFragEmpty();
						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
						
					} else {
						
						((Activity)ctxt).finish();
						
					}
				}
				if (strInput.equals("DelEvent")) {
					
					sqlHelper.deleteTickerEvent(String.valueOf(ticker_event_id));
					
					if (screenInch > 6) {
						
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
						fragTickerList.refreshContent(game_id);
							
						TabFragEmpty thirdFragment = new TabFragEmpty();
						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
						
					} else {
						
						((Activity)ctxt).finish();
						
					}
				}
				break;
			case 1:
				if (screenInch > 6) {
					
					// Aktualisiere die Spielliste
					fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
					fragTickerList.refreshContent(game_id);
						
					TabFragEmpty thirdFragment = new TabFragEmpty();
					fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
					
				} else {
					
					((Activity)ctxt).finish();
					
				}
				break;
			}
			
		}
		Log.v("Helperayout strInput", strInput);
		if (strInput.equals("StatOverview")) {
			
			final Dialog dialog = new Dialog(ctxt);
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			LinearLayout lyt_button2 = (LinearLayout) dialog.findViewById(R.id.lyt_button2);
			LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
			Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
			
			switch(position) {
			case 0:						// Teamstatistik
				
				i = new Intent(ctxt, SmartStatTeam.class);
				i.putExtras(args);
				((Activity)ctxt).startActivity(i);
				strInput = "StatOverview";
				args.putString("InputString", strInput);

				break;
			case 1:						// Torschützenliste
				
				args.putString("InputString", "ScoreTotal");
				
				i = new Intent(ctxt, SmartStatScore.class);
				i.putExtras(args);
				((Activity)ctxt).startActivity(i);
				strInput = "StatOverview";
				args.putString("InputString", strInput);
				
				break;
			case 2:						// Spieler Heimmannschaft
				
				args.putString("TeamID", team_home_id);
				args.putInt("HomeOrAway", 1);
				
				i = new Intent(ctxt, SmartStatPlayerList.class);
				i.putExtras(args);
				((Activity)ctxt).startActivity(i);
				strInput = "StatOverview";
				args.putString("InputString", strInput);
				
				break;
			case 3:						// Spieler Auswärtsmannschaft
				
				args.putString("TeamID", team_away_id);
				args.putInt("HomeOrAway", 0);
				
				i = new Intent(ctxt, SmartStatPlayerList.class);
				i.putExtras(args);
				((Activity)ctxt).startActivity(i);
				strInput = "StatOverview";
				args.putString("InputString", strInput);
				
				break;
			case 4:						// Spielaktionen
				
				args.putString("GameID", game_id);
				
				i = new Intent(ctxt, SmartStatGameActivity.class);
				i.putExtras(args);
				((Activity)ctxt).startActivity(i);
				strInput = "StatOverview";
				args.putString("InputString", strInput);
				
				break;
			case 5:						// Tickermeldungen
/** TODO -1- => Statistik zur Tickermeldung einbauen (das Gleiche auch bei Tablet) */
				
				// Nachrichtenbox einrichten
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				title.setText(R.string.text_patience);
				text.setText(R.string.text_function_not_available);
				
				// Button definieren
				lyt_button2.removeAllViews();
				lyt_button3.removeAllViews();
				
				dialogButton1.setText(R.string.okay);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();
				// Ende Nachrichtenbox

				strInput = "StatOverview";
				args.putString("InputString", strInput);
				
				break;
			case 6:						// Export
/** TODO -2- => Export eines Spiels auf den Server einbauen */
				
				// DialogBox einrichten
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				title.setText(R.string.synchro);
				text.setText(R.string.text_login_not_possible);
				
				// Button definieren
				lyt_button2.removeAllViews();
				lyt_button3.removeAllViews();
				
				dialogButton1.setText(R.string.okay);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();
				// Ende Nachrichtenbox
				
				strInput = "StatOverview";
				args.putString("InputString", strInput);
				
				break;
			}
			
		}
				
		if (strInput.equals("TabStatOverview") || 
				strInput.equals("TabScoreHome") || 
				strInput.equals("TabScoreAway") || 
				strInput.equals("TabScoreTotal")) {
			
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			final Dialog dialog = new Dialog(ctxt);
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			LinearLayout lyt_button2 = (LinearLayout) dialog.findViewById(R.id.lyt_button2);
			LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
			Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
			
			switch(position) {
			case 0:						// Teamstatistik
				
				TabFragStatTeam fragTeam = new TabFragStatTeam();
				fragTeam.setArguments(args);
				fragmentTransaction.replace(R.id.frag_stat_content, fragTeam);
				fragmentTransaction.commit();

				break;
			case 1:						// Torschützenliste
				
				args.putString("InputString", "TabScoreTotal");
				
				TabFragStatPlayerStatList fragList = new TabFragStatPlayerStatList();
				fragList.setArguments(args);
				fragmentTransaction.replace(R.id.frag_stat_content, fragList);
				fragmentTransaction.commit();
				
				break;
			case 2:						// Spieler Heimmannschaft
				
				args.putString("TeamID", team_home_id);
				args.putInt("HomeOrAway", 1);
				
				Intent i = new Intent(ctxt, TabStatPlayerOverviewActivity.class);
				i.putExtras(args);
				((Activity)ctxt).startActivity(i);
				
				break;
			case 3:						// Spieler Auswärtsmannschaft
				
				args.putString("TeamID", team_away_id);
				args.putInt("HomeOrAway", 0);
				
				i = new Intent(ctxt, TabStatPlayerOverviewActivity.class);
				i.putExtras(args);
				((Activity)ctxt).startActivity(i);
				
				
				break;
			case 4:						// Spielaktionen

/** TODO -3- => Tablet-Version: Neue Activity für Spielaktionen einfügen: links Filter, Mitte Liste der Spielaktionen, rechts Kommentar zur Spielaktion, wenn eine Spielaktion ausgewählt wurde.  */ 

				// DialogBox einrichten
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				title.setText(R.string.text_patience);
				text.setText(R.string.text_function_not_available);
				
				// Button definieren
				lyt_button2.removeAllViews();
				lyt_button3.removeAllViews();
				
				dialogButton1.setText(R.string.okay);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				// Ende Nachrichtenbox

				dialog.show();
				
				break;
			case 5:						// Tickermeldungen
/** TODO -3- => Tickermeldungen auf dem Tablet einbauen */
				
				// DialogBox einrichten
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				title.setText(R.string.text_patience);
				text.setText(R.string.text_function_not_available);
				
				// Button definieren
				lyt_button2.removeAllViews();
				lyt_button3.removeAllViews();
				
				dialogButton1.setText(R.string.okay);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				// Ende Nachrichtenbox
				
				break;
			case 6:						// Export
/** TODO -2- => Export eines Spiels auf den Server einbauen */
				
				// DialogBox einrichten
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				title.setText(R.string.synchro);
				text.setText(R.string.text_login_not_possible);
				
				// Button definieren
				lyt_button2.removeAllViews();
				lyt_button3.removeAllViews();
				
				dialogButton1.setText(R.string.okay);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				// Ende Nachrichtenbox
				
				break;
			}
		}
	}
	
/*
 * 
 * Auswahl eines Spielers einrichten
 *
 */
	
	public void updateTickerPlayer(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Spiel-Aktivitäten definieren */
		
		activityIDs();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			if (activity_id != null) activity_id = args.getInt("ActivityID");
			ticker_event_id = args.getInt("TickerEventID");
			ticker_activity_id = args.getInt("TickerActivityID");
			ticker_activity_against_id = args.getInt("TickerActivityAgainstID");
			ticker_assist_id = args.getInt("TickerAssistID");
			ticker_defense_id = args.getInt("TickerDefenseID");
			ticker_player_back_id = args.getInt("TickerPlayerBackID");
			ticker_foul_id = args.getInt("TickerFoulID");
			ticker_possession_id = args.getInt("TickerPossessionID");
			home_or_away = args.getInt("HomeOrAway");
			game_id = args.getString("GameID");
			time = args.getInt("Time");
			realtime = args.getString("RealTime");
			activity_result = args.getString("ActivityResult");
			player_id = args.getString("PlayerID");
			if (turnover != null) turnover = args.getInt("Turnover");
			current_possession = args.getInt("CurrentPossession");
			strInput = args.getString("InputString");
			activity_string = sqlHelper.getActivityStringByActivityID(activity_id, res);
			team_home_short = sqlHelper.getGameTeamHomeShortByID(game_id);
			team_away_short = sqlHelper.getGameTeamAwayShortByID(game_id);
			team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			player_team_id = sqlHelper.getPlayerTeamIDByID(player_id);
			player_position_first = sqlHelper.getPlayerPositionFirstByID(player_id);
			if (current_possession.equals(1)) {
				activity_team_id = team_home_id;
				activity_against_team_id = team_away_id;
			}
			if (current_possession.equals(0)) {
				activity_team_id = team_away_id;
				activity_against_team_id = team_home_id;
			}
			if (player_position_first.length() == 4) player_position = (player_position_first.substring(3,4));
			if (activity_id.equals(defense_success_id)) {
				ticker_defense_id = ticker_activity_id;
				strInput = "Defense";
			}
		}
		
		// Ballbesitzwechsel auslesen, falls Eingabe vorhanden
		if ((RadioGroup) view.findViewById(R.id.radio_turnover) != null) {
			if (!player_team_id.equals(activity_against_team_id)) {
				
				RadioButton radio_yes = (RadioButton) view.findViewById(R.id.radio_yes);
				RadioButton radio_no = (RadioButton) view.findViewById(R.id.radio_no);
				if (radio_yes.isChecked()) turnover = 1;
				if (radio_no.isChecked()) turnover = 0;
				
			}
		}
		
		// Editiermodus einstellen
		edit = false;
		if (strInput.length() > 3) { 
			if (strInput.substring(0,4).equals("Edit")) {
				edit = true;
			}
		}
		
/* Ticker updaten */
		
		// Falls Spieler für eine Vorlage oder Abwehr ausgewählt werden soll, nur den Ticker Updaten
		if (strInput.equals("Assist") || strInput.equals("Defense")) {

			if (strInput.equals("Assist")) {
				
				sqlHelper.updateTickerActivity(String.valueOf(ticker_assist_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
			
				ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
				sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
				
				// Abfrage nach weiterer Eingabe
				if (screenInch > 6) {
					
					// Benotung der Vorlage
					if (sqlHelper.getGameInputMark(game_id).equals(1)) {
						
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerMark fragment = new TabFragTickerMark();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						// Abwehr
						if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
							
							args.putString("InputString", "Defense");
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							TabFragTickerPlayer fragment = new TabFragTickerPlayer();
							fragment.setArguments(args);
							fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
							fragmentTransaction.commit();
							
						} else {
						
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
								args.putString("InputString", "EventText");
								FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
								TabFragTickerText fragment = new TabFragTickerText();
								fragment.setArguments(args);
								fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
								fragmentTransaction.commit();
								
							} else {
								
								// Aktualisiere die Spielliste
								fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
								fragTickerList.refreshContent(game_id);
	    						
								TabFragEmpty thirdFragment = new TabFragEmpty();
								fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
								
							}
						}
					}
					
				} else {
					
					// Benotung der Vorlage
					if (sqlHelper.getGameInputMark(game_id).equals(1)) {
						Log.v("HelperLayout", "Mark 9");
						Intent i = new Intent(ctxt, SmartTickerMark.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
						sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
						((Activity)ctxt).finish();
								
					} else {
								
						// Abwehr
						if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
							
							Intent i = new Intent(ctxt, SmartTickerPlayer.class);
							args.putString("InputString", "Defense");
							i.putExtras(args);
							((Activity)ctxt).startActivity(i);
							ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
							sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
							((Activity)ctxt).finish();
							
						} else {
							
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
								Intent i = new Intent(ctxt, SmartTickerText.class);
								args.putString("InputString", "EventText");
								i.putExtras(args);
								((Activity)ctxt).startActivity(i);
								ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
								sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
								((Activity)ctxt).finish();
								
							} else {
/** TODO -4- => Ticker generieren wird eventuell mehrmals durchlaufen, wenn alle Fenster geschlossen werde => prüfen und Ticker generieren nur einmal aufrufen */
								ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
								sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
								((Activity)ctxt).finish();
								
							}
						}
					}
				}
			}

			if (strInput.equals("Defense")) {
				
				Integer ticker_home_or_away = sqlHelper.getTickerHomeOrAwayByID(String.valueOf(ticker_activity_id));
				if (home_or_away.equals(ticker_home_or_away)) {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_defense_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
				} else {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_defense_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
				}
				
				// Abfrage nach weiterer Eingabe
				if (screenInch > 6) {
					
					// Benotung der Abwehraktion
					if (sqlHelper.getGameInputMark(game_id).equals(1)) {
						
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerMark fragment = new TabFragTickerMark();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
			
					} else {
						
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
    						fragTickerList.refreshContent(game_id);
    						
    						TabFragEmpty thirdFragment = new TabFragEmpty();
    						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
						
					}
					
				} else {
					
					// Benotung der Abwehraktion
					if (sqlHelper.getGameInputMark(game_id).equals(1)) {
						Log.v("HelperLayout", "Mark 10");
						Intent i = new Intent(ctxt, SmartTickerMark.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();
						
					} else {
						
						((Activity)ctxt).finish();
						
					}
				}
			}	
		} 
		
		// Falls es sich um die Bearbeitung eines Eintrags handelt, ändere nur den Namen
		if (edit == true) { 

			sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
			
			// Bei Änderung Einwechselung des Torwarts diesen als Torwart im Spiel eintragen
/** TODO -4- => Prüfen, ob es die letzte Einwechselung im Spiel war, da andernfalls der Torwart nicht im Spiel eingetragen werden muss */
			if (sqlHelper.getPlayerPositionFirstByID(player_id).equals("1001")) {
				if (sqlHelper.getTickerActivityIDByID(String.valueOf(ticker_activity_id)).equals(sub_in_id)) {
					if (sqlHelper.getTickerHomeOrAwayByID(String.valueOf(ticker_activity_id)).equals(1)) {
						sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, Integer.parseInt(player_id), null, null, null, null, null, null, null, null, null, null);
					}
					if (sqlHelper.getTickerHomeOrAwayByID(String.valueOf(ticker_activity_id)).equals(0)) {
						sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, Integer.parseInt(player_id), null, null, null, null, null, null, null, null, null);
					}
				}
			}
			
			if (screenInch > 6) {
 				
				// Aktualisiere die Spielliste
				fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
				fragTickerList.refreshContent(game_id);
				
				TabFragEmpty thirdFragment = new TabFragEmpty();
				fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
				
			} else {
					
				((Activity)ctxt).finish();
					
			}
		}
				
		// In allen übrigen Fällen
		if (!strInput.equals("Assist") && !strInput.equals("Defense") && edit == false) {

			// Abfrage, ob der Spieler aus der Mannschaft kommt, der die Aktion auch zugeordnet wurde
			if (!strInput.equals("")) {
				
				if (activity_team_id.equals(player_team_id)) {
					
					// Falls ja, muss die Aktion nur um den Spieler ergänzt werden
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
					
				} else {
					
					/* Falls die Mannschaft gewechselt wurde... */
					// ... geänderte Mannschaft eingeben
					
					if (sqlHelper.getTickerHomeOrAwayByID(String.valueOf(ticker_activity_id)).equals(1)) {
						home_or_away = 0;
						possession_against = 1;
						activity_team_id = team_away_id;
						activity_against_team_id = team_home_id;
					}
					if (sqlHelper.getTickerHomeOrAwayByID(String.valueOf(ticker_activity_id)).equals(0)) {
						home_or_away = 1;
						possession_against = 0;
						activity_team_id = team_home_id;
						activity_against_team_id = team_away_id;
					}
					args.putInt("HomeOrAway", home_or_away);
					
					/* ... Aktion umschreiben */
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);		
					
					/* ... Gegenaktion umschreiben */
					if (!ticker_activity_against_id.equals(0)) {
						
						if (sqlHelper.getGameGKHomeIDByID(game_id) != null) gk_home_id = String.valueOf(sqlHelper.getGameGKHomeIDByID(game_id));
						if (sqlHelper.getGameGKAwayIDByID(game_id) != null) 	gk_away_id = String.valueOf(sqlHelper.getGameGKAwayIDByID(game_id));
						if (home_or_away.equals(1)) {
							goalkeeper_id = gk_away_id;
						}
						if (home_or_away.equals(0)) {
							goalkeeper_id = gk_home_id;
						}
						sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_against_id), null, null, null, null, null, possession_against, goalkeeper_id, null, null, null, null, null, null, null, null);		
						
					}
					
					// Vorlage umschreiben
					if (ticker_assist_id != null) {
						sqlHelper.updateTickerActivity(String.valueOf(ticker_assist_id), null, null, null, null, null, home_or_away, null, null, null, null, null, null, null, null, null);
					}
					
					// Abwehr umschreiben
					if (ticker_defense_id != null) {
						sqlHelper.updateTickerActivity(String.valueOf(ticker_defense_id), null, null, null, null, null, possession_against, null, null, null, null, null, null, null, null, null);
					}

					// ... Ballbesitzwechsel löschen, falls einer eingetragen wurde
					if (!ticker_possession_id.equals(0)) sqlHelper.deleteTickerActivity(String.valueOf(ticker_possession_id));
					sqlHelper.updateGame(game_id, null, null, null, possession_against, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
					ticker_possession_id = null;
					
					ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
					sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
					
				}
			}
			
			// Die zu übertragenden Variablen erneuern
			/*
			if (ticker_activity_id != null) args.putInt("TickerActivityID", ticker_activity_id);
			if (ticker_event_id != null) args.putInt("TickerEventID", ticker_event_id);
			if (!ticker_activity_against_id.equals(0)) args.putInt("TickerActivityAgainstID", ticker_activity_against_id);
			if (ticker_possession_id != null) {
				if (!ticker_possession_id.equals(0)) args.putInt("TickerPossessionID", ticker_possession_id);
			}
			args.putInt("HomeOrAway", home_or_away);
			if (activity_ticker_time != null) args.putInt("Time", activity_ticker_time);	
			*/
			
			// Namen der Heim- und Auswärtsmannschaft ermitteln
			activity_team_name = sqlHelper.getTeamClubNameByID(activity_team_id);
			activity_against_team_name = sqlHelper.getTeamClubNameByID(activity_against_team_id);
			
/* Tor- bzw. Fehlwurf-Aktion */
			
			if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id) ||
					activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) {
				
				if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id)) {
					args.putString("InputString", "Goal");
				}
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) {
					args.putString("InputString", "Miss");
				}
				
				// Kontrollieren, ob der Fehlwurf zu einem Ballbesitzwechsel führte
				if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) {
					if (turnover != null) {
						if (turnover.equals(1)) {
							
							maxTime = sqlHelper.maxTickerTime(game_id);
							if (home_or_away.equals(1)) possession_against = 0;
							if (home_or_away.equals(0)) possession_against = 1;
							if (maxTime <= time) sqlHelper.changePossession(game_id, possession_against, ticker_event_id, time + 10, realtime, activity_result, res);
							
						}
					}
				}
				
				ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
				sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
				
				// Abfrage nach weiterer Eingabe
				if (screenInch > 6) {
					
					// Wurfecke
					if (sqlHelper.getGameInputArea(game_id).equals(1)) {
						
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerArea fragment = new TabFragTickerArea();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						// Wurftechnik
						if (sqlHelper.getGameInputThrowingTechnique(game_id).equals(1)) {
							
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							TabFragListWithText fragment = new TabFragListWithText();
							fragment.setArguments(args);
							fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
							fragmentTransaction.commit();
							
						} else {
							
							// Benotung für den Wurf
							if (sqlHelper.getGameInputMark(game_id).equals(1)) {
								
								FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
								TabFragTickerMark fragment = new TabFragTickerMark();
								fragment.setArguments(args);
								fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
								fragmentTransaction.commit();
								
							} else {
								
								// Vorlage
								if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
									
									args.putString("InputString", "Assist");
									FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
									TabFragTickerPlayer fragment = new TabFragTickerPlayer();
									fragment.setArguments(args);
									fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
									fragmentTransaction.commit();
									
								} else {
									
									// Abwehr
									if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
										
										args.putString("InputString", "Defense");
										FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
										TabFragTickerPlayer fragment = new TabFragTickerPlayer();
										fragment.setArguments(args);
										fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
										fragmentTransaction.commit();
										
									}  else {
										
										// Tickermeldung
										if (sqlHelper.getGameInputText(game_id).equals(1)) {
											
											args.putString("InputString", "EventText");
											FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
											TabFragTickerText fragment = new TabFragTickerText();
											fragment.setArguments(args);
											fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
											fragmentTransaction.commit();
											
										} else {
											
											// Aktualisiere die Spielliste
											fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
											fragTickerList.refreshContent(game_id);
				    						
											TabFragEmpty thirdFragment = new TabFragEmpty();
											fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
											
										}
									}
								}
							}
						}
					}
					
				} else {
					
					// Wurfecke
					if (sqlHelper.getGameInputArea(game_id).equals(1)) {
						
						Intent i = new Intent(ctxt, SmartTickerArea.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish(); 
						
					} else {
						
						// Wurftechnik
						if (sqlHelper.getGameInputThrowingTechnique(game_id).equals(1)) {
							
							Intent i = new Intent(ctxt, SmartListWithText.class);
							i.putExtras(args);
							((Activity)ctxt).startActivity(i);
							((Activity)ctxt).finish();
							
						} else {
							
							// Benotung für den Wurf
							if (sqlHelper.getGameInputMark(game_id).equals(1)) {
								Log.v("HelperLayout", "Mark 11");
								Intent i = new Intent(ctxt, SmartTickerMark.class);
								i.putExtras(args);
								((Activity)ctxt).startActivity(i);
								((Activity)ctxt).finish();
								
							} else {
								
								// Vorlage
								if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
									
									Intent i = new Intent(ctxt, SmartTickerPlayer.class);
									args.putString("InputString", "Assist");
									i.putExtras(args);
									((Activity)ctxt).startActivity(i);
									((Activity)ctxt).finish();
									
								} else {
									
									// Abwehr
									if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
										
										Intent i = new Intent(ctxt, SmartTickerPlayer.class);
										args.putString("InputString", "Defense");
										i.putExtras(args);
										((Activity)ctxt).startActivity(i);
										((Activity)ctxt).finish();
										
									} else {
										
										// Tickermeldung
										if (sqlHelper.getGameInputText(game_id).equals(1)) {
											
											args.putString("InputString", "EventText");
											Intent i = new Intent(ctxt, SmartTickerText.class);
											i.putExtras(args);
											((Activity)ctxt).startActivity(i);
											((Activity)ctxt).finish();
											
										} else {
											
											((Activity)ctxt).finish();
											
										}
									}
								}
							}
						}
					}
					Log.v("HelperSQL", "fertig");
				}
			}
		
/* Technischer Fehler */
			
			if (activity_id.equals(tech_fault_id)) {
				
				// Überprüfen, ob Mannschaft gewechselt wurde
				if (player_team_id.equals(activity_against_team_id)) {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
				} else {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
				}
				
				// Kontrollieren, ob der technische Fehler zu einem Ballbesitzwechsel führte
				if (turnover != null) {
					
					if (turnover.equals(1)) {
						
						maxTime = sqlHelper.maxTickerTime(game_id);
						if (home_or_away.equals(1)) possession_against = 0;
						if (home_or_away.equals(0)) possession_against = 1;
						if (maxTime <= time) sqlHelper.changePossession(game_id, possession_against, ticker_event_id, time + 10, realtime, activity_result, res);
						
					}
				}
			
				ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
				sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
			
				// Abfragen, ob der technische Fehler genauer definiert werden soll
				if (sqlHelper.getGameInputTechFault(game_id).equals(1)) {
				
					args.putString("InputString", "TechFault");
					
					if (screenInch > 6) {
					
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragListWithText fragment = new TabFragListWithText();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
				
					} else {
										
						Intent i = new Intent(ctxt, SmartListWithText.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();
										
					}				
				} else {
					
					if (screenInch > 6) {
						
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
    						fragTickerList.refreshContent(game_id);
    						
    						TabFragEmpty thirdFragment = new TabFragEmpty();
    						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
						
					}
				}
			}
			
/* Gelbe Karte */
			
			if (activity_id.equals(yellow_card_id)) {
				
				// Überprüfen, ob Mannschaft gewechselt wurde
				if (player_team_id.equals(activity_against_team_id)) {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_foul_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
				} else {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_foul_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
				}
				ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
				sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
				
			}

/* Zwei Minuten */
		
			if (activity_id.equals(two_minutes_id) || activity_id.equals(twoplustwo_id)) {
				
/** TODO -2- => Nach Eingabe von Zwei Minuten kommt dann beim nächsten Mal z.B. bei Torwurf nach Eingabe eines Spielers die Abfrage nach der Abwehr */
				// Überprüfen, ob Mannschaft gewechselt wurde
				if (player_team_id.equals(activity_against_team_id)) {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_foul_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_player_back_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
				} else {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_foul_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_player_back_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
				}
				ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
				sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
				
			}
		
/* Rote Karte */
		
			if (activity_id.equals(red_card_id)) {
				
				// Überprüfen, ob Mannschaft gewechselt wurde
				if (player_team_id.equals(activity_against_team_id)) {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_foul_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_player_back_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
				} else {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_foul_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
					sqlHelper.updateTickerActivity(String.valueOf(ticker_player_back_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
				}
				ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
				sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
				
			}
			
/* Foul */
			
			if (!ticker_foul_id.equals(0)) {
			
				sqlHelper.updateTickerActivity(String.valueOf(ticker_foul_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
				args.putString("InputString", "Defense");
				
				// Abfrage nach weiterer Eingabe
				if (screenInch > 6) {
					
					// Benotung des Zweikampfs
					if (sqlHelper.getGameInputMark(game_id).equals(1)) {
						
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerMark fragment = new TabFragTickerMark();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						// Aktualisiere die Spielliste
						fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
    						fragTickerList.refreshContent(game_id);
    						
    						TabFragEmpty thirdFragment = new TabFragEmpty();
    						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
						
					}
					
				} else {
					
					if (sqlHelper.getGameInputMark(game_id).equals(1)) {
						Log.v("HelperLayout", "Mark 12");
						Intent i = new Intent(ctxt, SmartTickerMark.class);
						i.putExtras(args); 
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();
						
					} else {
						
						((Activity)ctxt).finish();
						
					}
				}
			}
		
/* Einwechselung */
			
			if (activity_id.equals(sub_in_id)) {
			
				// Überprüfen, ob Mannschaft gewechselt wurde
				if (player_team_id.equals(activity_against_team_id)) {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
				} else {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, home_or_away, player_id, null, null, null, null, null, null, null, null);
				}
				
				// Torwart eintragen, falls ein Torwart ausgewählt wurde
				if (sqlHelper.getPlayerPositionFirstByID(player_id).equals("1001")) {
					
					if (home_or_away.equals(1)) sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, Integer.parseInt(player_id), null, null, null, null, null, null, null, null, null, null);
					if (home_or_away.equals(0)) sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, Integer.parseInt(player_id), null, null, null, null, null, null, null, null, null);
				
				}
			
				// Neue Activity Auswechselung eintragen
				activity_id = sub_out_id;
				activity_string = sqlHelper.getActivityStringByActivityID(activity_id, res);
				time = time +1;
				sqlHelper.insertTickerActivity(game_id, ticker_event_id, time, realtime, activity_id, home_or_away, null, null, null, null, null, activity_string, null);
				ticker_activity_id = sqlHelper.getLastTickerActivityID();
			
				if (!activity_id.equals(0)) args.putInt("ActivityID", activity_id);
				if (!ticker_activity_id.equals(0)) args.putInt("TickerActivityID", ticker_activity_id);
				strInput = "";
				args.putString("InputString", strInput);
			
				// Activity Auswechselung aufrufen
				if (screenInch > 6) {
				
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerPlayer fragment = new TabFragTickerPlayer();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
									
				} else {
				
					Intent i = new Intent(ctxt, SmartTickerPlayer.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
									
				}			
			}
			
/* Auswechselung */
			
			if (activity_id.equals(sub_out_id)) {
			
				sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, player_id, null, null, null, null, null, null, null, null);
				
			}
		}
	}
	
/*
 * 
 * Layout Zeit einstellen  einrichten 
 *
 */
				
	public void lytTickerTime(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
		
			
			
		}
		
		// Auflösung
		screenDensity=fctHelper.getScreenDensity(ctxt);
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			activity_id = args.getInt("ActivityID");
			ticker_activity_id = args.getInt("TickerActivityID");
			ticker_activity_against_id = args.getInt("TickerActivityAgainstID");
			strInput = args.getString("InputString");
			
			activity_ticker_time = sqlHelper.getTickerTimeByActivityID(String.valueOf(ticker_activity_id));
			ticker_event_id = sqlHelper.getTickerEventIDByActivityID(String.valueOf(ticker_activity_id));
		
		}
		
/* Layout setzen */
		
		// Layoutfelder definieren
		TextView headline=(TextView)view.findViewById(R.id.headline);
		final EditText editMinutes = (EditText) view.findViewById(R.id.setStopWatchMinutes);
  		final EditText editSeconds = (EditText) view.findViewById(R.id.setStopWatchSeconds);
  		Button btn_okay = (Button) view.findViewById(R.id.btn_okay);
  		
  		// Überschrift setzen
  		headline.setText(res.getString(R.string.set_time));
  		
  		// Uhrzeit berechnen und Zeitfelder setzen
  		String minutes,seconds;
		long secs,mins;
		
		secs = (long)(activity_ticker_time/1000);
		mins = (long)((activity_ticker_time/1000)/60);

		secs = secs % 60;
		seconds=String.valueOf(secs);
		if(secs == 0){
			seconds = "00";
		}
		if(secs <10 && secs > 0){
			seconds = "0"+seconds;
		}
	    	
		/* Convert the minutes to String and format the String */
	    	
		minutes=String.valueOf(mins);
		if(mins == 0){
			minutes = "00";
		}
		if(mins <10 && mins > 0){
			minutes = "0"+minutes;
		}
		editMinutes.setText(minutes);
		editSeconds.setText(seconds);
  		
  		// Okay-Button definieren
  		btn_okay.setOnClickListener(new View.OnClickListener() {	
  			@Override
  			public void onClick(View v) {
  				
  				int min=0;
  				int sec=0;
  				if (editMinutes.getText().length() != 0) {
  					min = Integer.parseInt(editMinutes.getText().toString());
  				} else{
  					min=0;
  				}
  				if(editSeconds.getText().length() != 0){
  					sec=Integer.parseInt(editSeconds.getText().toString());
  				} else{
  					sec=0;
  				}
  				if (sec>59) {
  					sec=59;
  				}
  				
  				// Zeit errechnen
  				elapsedTime = (long) (min * 60000) + (sec * 1000);
  				
  				// Alle Ticker, die zu der Event ID passen updaten mit aktueller Zeit
  				String[] tickerArgs={String.valueOf(ticker_event_id)};
  				SQLiteDatabase db = sqlHelper.getWritableDatabase();
  				Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? ORDER BY time ASC", tickerArgs);
  				cTicker.moveToFirst();
  				String ticker_id = null;
  				
  				// Alle Tickermeldungen abfragen und Wurfecke eintragen
  				for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
  					
  					ticker_id = sqlHelper.getTickerActivityID(cTicker);
  					sqlHelper.updateTickerActivity(String.valueOf(ticker_id), null, null, (int) (long) elapsedTime, null, null, null, null, null, null, null, null, null, null, null, null);
  					
  				}
			
  				if (screenInch > 6) {
  					
  					// Aktualisiere die Spielliste
  					fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
  					fragTickerList.refreshContent(game_id);
  						
  					TabFragEmpty thirdFragment = new TabFragEmpty();
  					fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
  					
  					
  				} else {
  					
  					((Activity)ctxt).finish();
  					
  				}			
  			}
  		});		
	}

/*
 * 
 * Wurfecke abschliessen
 *
 */
	
 	public void finishArea(String strArea) {
 		
/** TODO -1- => Wurf neben das Tor bei Torwurf nicht möglich */
 		sqlHelper = new HelperSQL(ctxt);
 		activityIDs();
 		
 		Button btn_goal_uull = (Button) view.findViewById(R.id.goal_uull);
		Button btn_goal_uul = (Button) view.findViewById(R.id.goal_uul);
		Button btn_goal_uum = (Button) view.findViewById(R.id.goal_uum);
		Button btn_goal_uur = (Button) view.findViewById(R.id.goal_uur);
		Button btn_goal_uurr = (Button) view.findViewById(R.id.goal_uurr);
		Button btn_goal_ull = (Button) view.findViewById(R.id.goal_ull);
		Button btn_goal_ul = (Button) view.findViewById(R.id.goal_ul);
		Button btn_goal_um = (Button) view.findViewById(R.id.goal_um);
		Button btn_goal_ur = (Button) view.findViewById(R.id.goal_ur);
		Button btn_goal_urr = (Button) view.findViewById(R.id.goal_urr);
		Button btn_goal_mll = (Button) view.findViewById(R.id.goal_mll);
		Button btn_goal_ml = (Button) view.findViewById(R.id.goal_ml);
		Button btn_goal_mm = (Button) view.findViewById(R.id.goal_mm);
		Button btn_goal_mr = (Button) view.findViewById(R.id.goal_mr);
		Button btn_goal_mrr = (Button) view.findViewById(R.id.goal_mrr);
		Button btn_goal_lll = (Button) view.findViewById(R.id.goal_lll);
		Button btn_goal_ll = (Button) view.findViewById(R.id.goal_ll);
		Button btn_goal_lm = (Button) view.findViewById(R.id.goal_lm);
		Button btn_goal_lr = (Button) view.findViewById(R.id.goal_lr);
		Button btn_goal_lrr = (Button) view.findViewById(R.id.goal_lrr);
		
 		if (strArea.equals("uull")) btn_goal_uull.setText("X");
		if (strArea.equals("uul")) btn_goal_uul.setText("X");
		if (strArea.equals("uum")) btn_goal_uum.setText("X");
		if (strArea.equals("uur")) btn_goal_uur.setText("X");
		if (strArea.equals("uurr")) btn_goal_uurr.setText("X");
		if (strArea.equals("ull")) btn_goal_ull.setText("X");
		if (strArea.equals("ul")) btn_goal_ul.setText("X");
		if (strArea.equals("um")) btn_goal_um.setText("X");
		if (strArea.equals("ur")) btn_goal_ur.setText("X");
		if (strArea.equals("urr")) btn_goal_urr.setText("X");
		if (strArea.equals("mll")) btn_goal_mll.setText("X");
		if (strArea.equals("ml")) btn_goal_ml.setText("X");
		if (strArea.equals("mm")) btn_goal_mm.setText("X");
		if (strArea.equals("mr")) btn_goal_mr.setText("X");
		if (strArea.equals("mrr")) btn_goal_mrr.setText("X");
		if (strArea.equals("lll")) btn_goal_lll.setText("X");
		if (strArea.equals("ll")) btn_goal_ll.setText("X");
		if (strArea.equals("lm")) btn_goal_lm.setText("X");
		if (strArea.equals("lr")) btn_goal_lr.setText("X");
		if (strArea.equals("lrr")) btn_goal_lrr.setText("X");
		
		if (strInput.equals("EditGoal") || strInput.equals("EditMiss") || strInput.equals("EditSave") || strInput.equals("EditGoalAgainst")) {
 			
 			// Wurfecke des ausgewählten Tickers
 			if (!ticker_activity_id.equals(null)) {
 	 			sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, null, strArea, null, null, null, null, null, null, null);
 	 		}
 			
 			// Die Wurfecke auch bei den übrigen Toraktionen des Tickereintrags ändern
 			String[] tickerArgs={String.valueOf(ticker_event_id)};
			SQLiteDatabase db = sqlHelper.getWritableDatabase();
			Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE ticker_event_id = ? ORDER BY time ASC", tickerArgs);
			cTicker.moveToFirst();
			String ticker_id = null;
			
			// Alle Tickermeldungen abfragen und Wurfecke eintragen
			for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
				
				ticker_id = sqlHelper.getTickerActivityID(cTicker);
				if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_7m_id) ||	sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_fb_id) || 
						sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_7m_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(miss_fb_id) || 
						sqlHelper.getTickerActivityIDByID(ticker_id).equals(save_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(save_7m_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(save_fb_id) ||
						sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_against_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_against_7m_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(goal_against_fb_id)) {
					sqlHelper.updateTickerActivity(String.valueOf(ticker_id), null, null, null, null, null, null, null, strArea, null, null, null, null, null, null, null);
				}
				// Torwart gehalten entfernen, wenn Wurf neben das Tor ging
				if (strInput.equals("EditMiss")) {
					if (sqlHelper.getTickerActivityIDByID(ticker_id).equals(save_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(save_7m_id) || sqlHelper.getTickerActivityIDByID(ticker_id).equals(save_fb_id)) { 
						if (strArea.equals("uull") || strArea.equals("uul") || strArea.equals("uum") || strArea.equals("uur") || strArea.equals("uurr") || strArea.equals("ull") || strArea.equals("urr") || strArea.equals("mll") || strArea.equals("mrr") || strArea.equals("lll") || strArea.equals("lrr")) {
							
							sqlHelper.deleteTickerActivity(ticker_id);
							
						}
					}
				}
			}
 			
 		} else {
 /** TODO -1- => Torwart gehalten entfernen, wenn Wurf neben das Tor ging */
 			if (!ticker_activity_id.equals(null)) {
 	 			sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, null, strArea, null, null, null, null, null, null, null);
 	 		}
 	 		if (!ticker_activity_against_id.equals(null)) {
 	 			sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_against_id), null, null, null, null, null, null, null, strArea, null, null, null, null, null, null, null);
 	 		}
 	 		
 		}

		// Eingeben, dass Porition gesetzt
		set_area = true;
		// Falls alle Eingaben gemacht => weitergehen
		if (set_position == true) leave_input_area();
		
 	}
 	
/*
 * 
 * Verlassen der Wurfecke- und Positionseingabe einrichten
 *
 */
 	
 	public void leave_input_area() {
 		
 		if (strInput.equals("EditGoal") || strInput.equals("EditMiss") || strInput.equals("EditSave") || strInput.equals("EditGoalAgainst")) {
 			
			if (screenInch > 6) {
 				
 	 			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
 				TabFragEmpty fragment = new TabFragEmpty();
 				fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
 				fragmentTransaction.commit();
 				
 			} else {
 				
 				((Activity)ctxt).finish();
 				
 			}
 			
 		} else {
 			
 			if (screenInch > 6) {
				
				// Wurftechnik
				if (sqlHelper.getGameInputThrowingTechnique(game_id).equals(1)) {
				
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragListWithText fragment = new TabFragListWithText();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
				
				} else {
				
					// Benotung für den Wurf
					if (sqlHelper.getGameInputMark(game_id).equals(1)) {
					
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerMark fragment = new TabFragTickerMark();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
					
					} else {
					
						// Vorlage
						if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
						
							args.putString("InputString", "Assist");
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							TabFragTickerPlayer fragment = new TabFragTickerPlayer();
							fragment.setArguments(args);
							fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
							fragmentTransaction.commit();
						
						} else {
						
							// Abwehr
							if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
							
								args.putString("InputString", "Defense");
								FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
								TabFragTickerPlayer fragment = new TabFragTickerPlayer();
								fragment.setArguments(args);
								fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
								fragmentTransaction.commit();
							
							}  else {
							
								// Tickermeldung
								if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
									args.putString("InputString", "EventText");
									FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
									TabFragTickerText fragment = new TabFragTickerText();
									fragment.setArguments(args);
									fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
									fragmentTransaction.commit();
								
								} else {
								
									// Aktualisiere die Spielliste
									fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
									fragTickerList.refreshContent(game_id);
	    						
									TabFragEmpty thirdFragment = new TabFragEmpty();
									fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
								
								}
							}
						}
					}
				}
			
			} else {
			
				// Wurftechnik
				if (sqlHelper.getGameInputThrowingTechnique(game_id).equals(1)) {
				
					Intent i = new Intent(ctxt, SmartListWithText.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
				
				} else {
				
					// Benotung für den Wurf
					if (sqlHelper.getGameInputMark(game_id).equals(1)) {
						Log.v("HelperLayout", "Mark 13");
						Intent i = new Intent(ctxt, SmartTickerMark.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();
					
					} else {
					
						// Vorlage
						if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
						
							Intent i = new Intent(ctxt, SmartTickerPlayer.class);
							args.putString("InputString", "Assist");
							i.putExtras(args);
							((Activity)ctxt).startActivity(i);
							((Activity)ctxt).finish();
						
						} else {
						
							// Abwehr
							if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
							
								Intent i = new Intent(ctxt, SmartTickerPlayer.class);
								args.putString("InputString", "Defense");
								i.putExtras(args);
								((Activity)ctxt).startActivity(i);
								((Activity)ctxt).finish();
							
							} else {
							
								// Tickermeldung
								if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
									args.putString("InputString", "EventText");
									Intent i = new Intent(ctxt, SmartTickerText.class);
									i.putExtras(args);
									((Activity)ctxt).startActivity(i);
									((Activity)ctxt).finish();
								
								} else {
								
									((Activity)ctxt).finish();
								
								}
							}
						}
					}
				}
			}
 		}
 	}
 	
/*
 * 
 * Eingabetiefe einrichten
 *
 */
	
	public void InputDepth(Context ctxt, String game_id, int position, Boolean bool) {
		
		sqlHelper=new HelperSQL(ctxt);
		
		switch(position){
	    	
			case 0:
			
				if (bool.equals(true)) {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, null, null, null, null, null, null, null, null);
				} else {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, null, null, null);
				}
				break;
			
			case 1:
			
				if (bool.equals(true)) {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, null, null, null, null, null, null, null);
				} else {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, null, null);
				}
				break;

			case 2:
			
				if (bool.equals(true)) {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, null, null, null, null, null, null);
				} else {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, null);
				}
				break;
			
			case 3:
			
				if (bool.equals(true)) {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, null, null, null, null, null);
				} else {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null);
				}
				break;
			
			case 4:
			
				if (bool.equals(true)) {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, null, null, null, null);
				} else {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null);
				}
				break;
			
			case 5:
	
				if (bool.equals(true)) {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, null, null, null);
				} else {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null);
				}
				break;
				
			case 6:
				
				if (bool.equals(true)) {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, null, null);
				} else {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null);
				}
				break;
				
			case 7:
				
				if (bool.equals(true)) {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, null);
				} else {
					sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null);
				}
				break;
			
		}
	}
	
	
/*
 * 
 * Note eingeben
 *
 */
	
	public void markActivity(Integer intMark) {
		
		Integer ticker_id = null;
		if (strInput.length() > 3) if (strInput.substring(0,4).equals("Edit"))  ticker_id = ticker_activity_id;
		if (strInput.equals("Goal") || strInput.equals("Miss") || strInput.equals("Acticity") || strInput.equals("TechFault")) ticker_id = ticker_activity_id;
		if (strInput.equals("GoalAgainst") || strInput.equals("Save")) ticker_id = ticker_activity_against_id;
		if (strInput.equals("Assist")) ticker_id = ticker_assist_id;
		if (strInput.equals("Defense")) ticker_id = ticker_defense_id;
		
		sqlHelper.updateTickerActivity(String.valueOf(ticker_id), null, null, null, null, null, null, null, null, null, null, null, null, null, null, intMark);
		Log.v("HelperLayout markActivity strInput", strInput);
		// Abfrage nach weiterer Eingabe
		edit = false;
		if (strInput.length() > 3) {
			if (strInput.substring(0,4).equals("Edit")) {
				edit = true;
			}
		}
		if (edit == true) {								// Bei Ticker Bearbeitung keine weitere Eingabe
			Log.v("HelperLayout", "1");
			if (screenInch > 6) {
			
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				TabFragEmpty fragment = new TabFragEmpty();
				fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
				fragmentTransaction.commit();
			
			} else {
			
				((Activity)ctxt).finish();
			
			}
			
		} else if (screenInch > 6) {
			
			// Falls Benotung Wurf, dann Benotung Torwart
			if (strInput.equals("Goal") || strInput.equals("Miss")) {
				
				args.putString("InputString", "GoalAgainst");
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				TabFragTickerMark fragment = new TabFragTickerMark();
				fragment.setArguments(args);
				fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
				fragmentTransaction.commit();
				
			} else {
				
				// Falls Torwart
				if (strInput.equals("GoalAgainst") || strInput.equals("Save")) {
					// Abfrage, ob Vorlage eingegeben werden soll
					if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
					
						args.putString("InputString", "Assist");
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerPlayer fragment = new TabFragTickerPlayer();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						// Abfrage, ob Abwehr eingegeben werden soll
						if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
						
							args.putString("InputString", "Defense");
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							TabFragTickerPlayer fragment = new TabFragTickerPlayer();
							fragment.setArguments(args);
							fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
							fragmentTransaction.commit();
							
						} else {
							
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
								args.putString("InputString", "EventText");
								FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
								TabFragTickerText fragment = new TabFragTickerText();
								fragment.setArguments(args);
								fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
								fragmentTransaction.commit();
								
							} else {
								
								// Aktualisiere die Spielliste
								fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
								fragTickerList.refreshContent(game_id);
	    						
								TabFragEmpty thirdFragment = new TabFragEmpty();
								fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
								
							}
						}
					}
					
				} else {
					
					// Falls Vorlage
					if (strInput.equals("Assist")) {
						// Abfrage, ob Abwehr eingegeben werden soll
						if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
						
							args.putString("InputString", "Defense");
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							TabFragTickerPlayer fragment = new TabFragTickerPlayer();
							fragment.setArguments(args);
							fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
							fragmentTransaction.commit();
							
						} else {
							
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
								args.putString("InputString", "EventText");
								FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
								TabFragTickerText fragment = new TabFragTickerText();
								fragment.setArguments(args);
								fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
								fragmentTransaction.commit();
								
							} else {
								
								// Aktualisiere die Spielliste
								fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
								fragTickerList.refreshContent(game_id);
	    						
								TabFragEmpty thirdFragment = new TabFragEmpty();
								fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
								
							}
						}
					} else {
						
						// Falls Abwehr, wieder zum Ursprungsbildschirm zurück
						if (strInput.equals("Defense") || strInput.equals("TechFault")) {
							
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								
								args.putString("InputString", "EventText");
								FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
								TabFragTickerText fragment = new TabFragTickerText();
								fragment.setArguments(args);
								fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
								fragmentTransaction.commit();
								
							} else {
								
								// Aktualisiere die Spielliste
								fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
								fragTickerList.refreshContent(game_id);
	    						
								TabFragEmpty thirdFragment = new TabFragEmpty();
								fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
								
							}
							
						} else {
							
							// Aktualisiere die Spielliste
							fragTickerList = (TabFragTickerList) fragmentManager.findFragmentById(R.id.frag_ticker_list);
	    						fragTickerList.refreshContent(game_id);
	    						
	    						TabFragEmpty thirdFragment = new TabFragEmpty();
	    						fragmentManager.beginTransaction().add(R.id.frag_ticker_setup, thirdFragment).commit();
							
						}
					}
				}
			}
			
		} else {
			Log.v("HelperLayout markActivity", "2");
			// Falls Benotung Wurf, dann Benotung Torwart
			if (strInput.equals("Goal") || strInput.equals("Miss")) {
				Log.v("HelperLayout markActivity", "3");
				if (strInput.equals("Goal")) args.putString("InputString", "GoalAgainst");
				if (strInput.equals("Miss")) args.putString("InputString", "Save");
				Log.v("HelperLayout", "Mark 14");
				Intent i = new Intent(ctxt, SmartTickerMark.class);
				i.putExtras(args);
				((Activity)ctxt).startActivity(i);
				((Activity)ctxt).finish();
				
			} else {
				Log.v("HelperLayout markActivity", "4");
				// Falls Benotung Torwart
				if (strInput.equals("GoalAgainst") || strInput.equals("Save")) {
					// dann Abfrage, ob Vorlage eingegeben werden soll
					if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
						Log.v("HelperLayout markActivity", "5");
						args.putString("InputString", "Assist");
						Intent i = new Intent(ctxt, SmartTickerPlayer.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						((Activity)ctxt).finish();
						
					} else {
						Log.v("HelperLayout markActivity", "6");
						// Abfrage, ob Abwehr eingegeben werden soll
						if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
							Log.v("HelperLayout markActivity", "7");
							args.putString("InputString", "Defense");
							Intent i = new Intent(ctxt, SmartTickerPlayer.class);
							i.putExtras(args);
							((Activity)ctxt).startActivity(i);
							((Activity)ctxt).finish();
							
						} else {
							Log.v("HelperLayout markActivity", "8");
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								Log.v("HelperLayout markActivity", "9");
								args.putString("InputString", "EventText");
								Intent i = new Intent(ctxt, SmartTickerText.class);
								i.putExtras(args);
								((Activity)ctxt).startActivity(i);
								((Activity)ctxt).finish();
								
							} else {
								Log.v("HelperLayout markActivity", "10");
								((Activity)ctxt).finish();
								
							}
						}
					}
					
				} else {
					Log.v("HelperLayout markActivity", "11");
					// Falls Vorlage
					if (strInput.equals("Assist")) {
						Log.v("HelperLayout markActivity", "12");
						// Abfrage, ob Abwehr eingegeben werden soll
						if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
							Log.v("HelperLayout markActivity", "13");
							args.putString("InputString", "Defense");
							Intent i = new Intent(ctxt, SmartTickerPlayer.class);
							i.putExtras(args);
							((Activity)ctxt).startActivity(i);
							((Activity)ctxt).finish();
							
						} else {
							Log.v("HelperLayout markActivity", "14");
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								Log.v("HelperLayout markActivity", "15");
								args.putString("InputString", "EventText");
								Intent i = new Intent(ctxt, SmartTickerText.class);
								i.putExtras(args);
								((Activity)ctxt).startActivity(i);
								((Activity)ctxt).finish();
								
							} else {
								Log.v("HelperLayout markActivity", "15");
								((Activity)ctxt).finish();
								
							}
						}
					} else {
						Log.v("HelperLayout markActivity", "16");
						// Falls Abwehr
						if (strInput.equals("Defense") || strInput.equals("TechFault")) {
							Log.v("HelperLayout markActivity", "17");
							Log.v("HelperLayout", "Mark Defense aufgerufen");
							// Tickermeldung
							if (sqlHelper.getGameInputText(game_id).equals(1)) {
								Log.v("HelperLayout markActivity", "18");
								args.putString("InputString", "EventText");
								Intent i = new Intent(ctxt, SmartTickerText.class);
								i.putExtras(args);
								((Activity)ctxt).startActivity(i);
								((Activity)ctxt).finish();
								
							} else {
								Log.v("HelperLayout markActivity", "19");
								((Activity)ctxt).finish();
								
							}
						} else {
							Log.v("HelperLayout markActivity", "20");
							((Activity)ctxt).finish();
							
						}
					}
				} 
			}
		}
	}
	
/*
 * 
 * Teamstatistik definieren 
 *
 */
				
	public void lytStatTeam(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager, final SmartStatTeam activity) {
		
		view = contentView;
		statTeamListActivity = activity;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		String[] statHomeValues = null;
		String[] statAwayValues = null;
		int[] intHomeValues = null;
		int[] intAwayValues = null;
		String[] statTeamValues = null;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
		
			
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			// Get arguments
			game_id = args.getString("GameID");
			
		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Tabelle einrichten */
		
		// Statitsche Daten ermitteln
		duration = sqlHelper.getGameDurationHalftimeByID(game_id);
		int goals_home = sqlHelper.count_ticker_goals(game_id, null, 1, null);
		int goals_away = sqlHelper.count_ticker_goals(game_id, null, 0, null);
		int attempts_home = sqlHelper.count_ticker_goal_attempts(game_id, null, 1, null);
		int attempts_away = sqlHelper.count_ticker_goal_attempts(game_id, null, 0, null);
		int goals_percent_home = 0;
		if (attempts_home != 0) goals_percent_home = (goals_home * 100) / attempts_home;
		int goals_percent_away = 0;
		if (attempts_away != 0) goals_percent_away = (goals_away * 100) / attempts_away;
		int goals_7m_home = sqlHelper.count_ticker_activity(game_id, 1, null, goal_7m_id, null, null);
		int goals_7m_away = sqlHelper.count_ticker_activity(game_id, 0, null, goal_7m_id, null, null);
		int attempts_7m_home = sqlHelper.count_ticker_activity(game_id, 1, null, goal_7m_id, null, null) +
									sqlHelper.count_ticker_activity(game_id, 1, null, miss_7m_id, null, null);
		int attempts_7m_away = sqlHelper.count_ticker_activity(game_id, 0, null, goal_7m_id, null, null) +
				 					sqlHelper.count_ticker_activity(game_id, 0, null, miss_7m_id, null, null);
		int percent_7m_home = 0;
		if (attempts_7m_home != 0) percent_7m_home = (goals_7m_home * 100) / attempts_7m_home;
		int percent_7m_away = 0;
		if (attempts_7m_away != 0) percent_7m_away = (goals_7m_away * 100) / attempts_7m_away;
		int goals_fb_home = sqlHelper.count_ticker_activity(game_id, 1, null, goal_fb_id, null, null);
		int goals_fb_away = sqlHelper.count_ticker_activity(game_id, 0, null, goal_fb_id, null, null);
		int attempts_fb_home = sqlHelper.count_ticker_activity(game_id, 1, null, goal_fb_id, null, null) +
									sqlHelper.count_ticker_activity(game_id, 1, null, miss_fb_id, null, null);
		int attempts_fb_away = sqlHelper.count_ticker_activity(game_id, 0, null, goal_fb_id, null, null) +
				 					sqlHelper.count_ticker_activity(game_id, 0, null, miss_fb_id, null, null);
		int percent_fb_home = 0;
		if (attempts_fb_home != 0) percent_fb_home = (goals_fb_home * 100) / attempts_fb_home;
		int percent_fb_away = 0;
		if (attempts_fb_away != 0) percent_fb_away = (goals_fb_away * 100) / attempts_fb_away;
		int tech_faults_home = sqlHelper.count_ticker_activity(game_id, 1, null, tech_fault_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 1, null, fehlpass_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 1, null, steps_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 1, null, three_seconds_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 1, null, doppeldribbel_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 1, null, fuss_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 1, null, zeitspiel_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 1, null, kreis_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 1, null, stuermerfoul_id, null, null);
		int tech_faults_away = sqlHelper.count_ticker_activity(game_id, 0, null, tech_fault_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 0, null, fehlpass_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 0, null, steps_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 0, null, three_seconds_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 0, null, doppeldribbel_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 0, null, fuss_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 0, null, zeitspiel_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 0, null, kreis_id, null, null) +
								sqlHelper.count_ticker_activity(game_id, 0, null, stuermerfoul_id, null, null);;
		
		// Dynamische Daten ermitteln
		Integer intTickerActivity = null;
		Integer intTickerTime = null;
		Integer intTickerHomeAway = null;
		Integer intCurrentPossession = 2;
		Integer intPossessionHome = 0;
		Integer intPossessionAway = 0;
		Integer intPossessionTimeHome = 0;
		Integer intPossessionTimeAway = 0;
		Integer intTimePossessionAttemptHome = 0;
		Integer intTimePossessionAttemptAway = 0;
		Integer intChangePossessionTime = 0;
		Integer intGoalDifference = 0;
		Integer intTimeLeadHome = 0;
		Integer intTimeLeadAway = 0;
		Integer intMaxLeadHome = 0;
		Integer intMaxLeadAway = 0;
		Integer intTimeDraw = 0;
		Integer intTimeLeadChange = 0;
		Integer intPowerplay = 0;
		Integer intGoalsPowerplayHome = 0;
		Integer intGoalsPowerplayAway = 0;
		Integer intGoalsShorthandedHome = 0;
		Integer intGoalsShorthandedAway = 0;
		Integer intTimePowerplayHome = 0;
		Integer intTimePowerplayAway = 0;
		Integer intTimePowerplayChange = 0;
		String strTickerActivityID = null;
		
		String[] tickerArgs={game_id};
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		Cursor cTicker=db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? ORDER BY time ASC", tickerArgs);
		cTicker.moveToFirst();
		for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
			
			strTickerActivityID = String.valueOf(sqlHelper.getTickerActivityID(cTicker));
			intTickerActivity = sqlHelper.getTickerActivityIDByID(strTickerActivityID);
			intTickerTime = sqlHelper.getTickerTimeByActivityID(strTickerActivityID);
			if (intTickerTime < 0) intTickerTime = 0;
			intTickerHomeAway = sqlHelper.getTickerHomeOrAwayByID(strTickerActivityID);
			
			if (strTickerActivityID != null && intTickerHomeAway != null) {
				
/* Ballbesitzwechsel ermitteln */
				
				// Falls die Heimmannschaft in Ballbesitz gekommen ist...
				if (intTickerActivity.equals(possession_id) && intTickerHomeAway.equals(1)) {
					
					// ... und Heimmannschaft vorher nicht in Ballbesitz ist
		    			if (!intCurrentPossession.equals(1)) {
		    				
		    				intPossessionHome = intPossessionHome + 1;				// Zähle Angriff hinzu
		    				intPossessionTimeAway = intPossessionTimeAway + intTickerTime - intChangePossessionTime;	// Trage Zeit ein, die die Auswärtsmannschaft in Ballbesitz war
		    				intChangePossessionTime = intTickerTime;
		    				intCurrentPossession = 1;
		    			
		    			}
		    		}
				
				// Falls die Auswärtsmannschaft in Ballbesitz gekommen ist...
				if (intTickerActivity.equals(possession_id) && intTickerHomeAway.equals(0)) {
					
					// ... und Auswärtsmannschaft vorher nicht in Ballbesitz ist
		    			if (!intCurrentPossession.equals(0)) {
		    				intPossessionAway = intPossessionAway + 1;				// Zähle Angriff hinzu
		    				intPossessionTimeHome = intPossessionTimeHome + intTickerTime - intChangePossessionTime;	// Trage Zeit ein, die die Heimmannschaft in Ballbesitz war
		    				intChangePossessionTime = intTickerTime;
		    				intCurrentPossession = 0;
		    			}
		    		}
				
/* Führung / Unentschieden + Tore Überzahl / Unterzahl ermitteln */
				
				// Wurde ein Tor geschossen?
				if (intTickerActivity.equals(goal_id) || intTickerActivity.equals(goal_7m_id) || intTickerActivity.equals(goal_fb_id) ){
		    			
					// Hat das Heimteam das Tor geschossen?
					if (intTickerHomeAway.equals(1)) {
						
						intGoalDifference = intGoalDifference + 1; 
						
						// Maximale Führung überprüfen
						if (intGoalDifference > intMaxLeadHome) intMaxLeadHome = intGoalDifference;
						
						// Kommt es durch das Tor zum Unentschieden?
						if (intGoalDifference.equals(0)) {
							
							intTimeLeadAway = intTimeLeadAway + intTickerTime - intTimeLeadChange;
							intTimeLeadChange = intTickerTime;
							
						}
						
						// Geht die Heimmannschaft durch das Tor in Führung?
						if (intGoalDifference.equals(1)) {
							
							intTimeDraw = intTimeDraw + intTickerTime - intTimeLeadChange;
							intTimeLeadChange = intTickerTime;
							
						}
						
						// Überzahl- und Unterzahltore ermitteln
						if (intPowerplay > 0) {
							
							intGoalsPowerplayHome = intGoalsPowerplayHome + 1;
							
						}
						if (intPowerplay < 0) {
							
							intGoalsShorthandedHome = intGoalsShorthandedHome + 1;
							
						}
					}
						
					// Hat das Auswärtsteam das Tor geschossen?
					if (intTickerHomeAway.equals(0)) {
						
						intGoalDifference = intGoalDifference - 1; 
						
						// Maximale Führung überprüfen
						if (intGoalDifference < intMaxLeadAway) intMaxLeadAway = intGoalDifference;
						
						// Kommt es durch das Tor zum Unentschieden?
						if (intGoalDifference.equals(0)) {
							
							intTimeLeadHome = intTimeLeadHome + intTickerTime - intTimeLeadChange;
							intTimeLeadChange = intTickerTime;
							
						}
						
						// Geht die Auswärtsmannschaft durch das Tor in Führung?
						if (intGoalDifference.equals(-1)) {
							
							intTimeDraw = intTimeDraw + intTickerTime - intTimeLeadChange;
							intTimeLeadChange = intTickerTime;
							
						}
						
						// Überzahl- und Unterzahltore ermitteln
						if (intPowerplay < 0) {
							
							intGoalsPowerplayAway = intGoalsPowerplayAway + 1;
							
						}
						if (intPowerplay > 0) {
							
							intGoalsShorthandedAway = intGoalsShorthandedAway + 1;
							
						}
					}
	    			}
				
/* Überzahl / Unterzahl eintragen */
				
				// Falls eine Zeitstrafe gegeben wurde
				if (intTickerActivity.equals(two_minutes_id) || intTickerActivity.equals(twoplustwo_id) || intTickerActivity.equals(red_card_id) ){
			    		
					if (intTickerHomeAway.equals(1)) {		// Zeitstrafe für die Heimmannschaft
						
						intPowerplay = intPowerplay - 1;
						
		    				if (intPowerplay.equals(-1)) {		// Ist Auswärtsmannschaft in Überzahl gekommen?
		    					intTimePowerplayChange = intTickerTime;
		    				}
		    				if (intPowerplay.equals(0)) {		// Wurde Überzahl der Heimmannschaft durch Zeitstrafe beendet
		    					intTimePowerplayHome = intTimePowerplayHome + intTickerTime - intTimePowerplayChange;
		    				}
		    			}
					if (intTickerHomeAway.equals(0)) {		// Zeitstrafe für die Auswärtsmannschaft
						
						intPowerplay = intPowerplay + 1;
						
		    				if (intPowerplay.equals(1)) {		// Ist Auswärtsmannschaft in Überzahl gekommen?
		    					intTimePowerplayChange = intTickerTime;
		    				}
		    				if (intPowerplay.equals(0)) {		// Wurde Überzahl der Heimmannschaft durch Zeitstrafe beendet
		    					intTimePowerplayAway = intTimePowerplayAway + intTickerTime - intTimePowerplayChange;
		    				}
		    			}
				}
				
				// Falls Spieler von Zeitstrafe zurück kommt
				if (intTickerActivity.equals(two_in_id)) {
					
					if (intTickerHomeAway.equals(1)) {
						
						intPowerplay = intPowerplay + 1;

		    				if (intPowerplay.equals(1)) {		// Ist Heimmannschaft durch Rückkehr in Überzahl gekommen?
		    					intTimePowerplayChange = intTickerTime;
		    				}
		    				if (intPowerplay.equals(0)) {		// Wurde Überzahl der Auswärtsmannschaft durch Zeitstrafe beendet?
		    					intTimePowerplayAway = intTimePowerplayAway + intTickerTime - intTimePowerplayChange;
		    				}
		    			}
					
					if (intTickerHomeAway.equals(0)) {
						
						intPowerplay = intPowerplay - 1;

		    				if (intPowerplay.equals(1)) {		// Ist Auswärtsmannschaft durch Rückkehr in Überzahl gekommen?
		    					intTimePowerplayChange = intTickerTime;
		    				}
		    				if (intPowerplay.equals(0)) {		// Wurde Überzahl der Heimmannschaft durch Zeitstrafe beendet?
		    					intTimePowerplayHome = intTimePowerplayHome + intTickerTime - intTimePowerplayChange;
		    				}
		    			}
					
				}
			}
		}
		
/* Statistik zum Spielende angleichen */
		
		// Wenn zum Spielende eine Mannschaft in Ballbesitz, dann Restzeit eintragen
		
		if (intCurrentPossession.equals(1)) {
			intPossessionTimeHome = intPossessionTimeHome + (duration * 120000) - intChangePossessionTime;
		}
		if (intCurrentPossession.equals(0)) {
			intPossessionTimeAway = intPossessionTimeAway + (duration * 120000) - intChangePossessionTime;
		}
		
		// Durchschnittliche Zeit pro Angriff errechnen
	    	if (!intPossessionHome.equals(0)) intTimePossessionAttemptHome = intPossessionTimeHome / intPossessionHome;
	    	if (!intPossessionAway.equals(0)) intTimePossessionAttemptAway = intPossessionTimeAway / intPossessionAway;
	    	
	    	// Führung oder Unentschieden zum Spielende eintragen
	    	
	    	if (intGoalDifference > 0) {
	    		intTimeLeadHome = intTimeLeadHome + (duration * 120000) - intTimeLeadChange;
	      	}
	    	if (intGoalDifference < 0) {
	    		intTimeLeadAway = intTimeLeadAway + (duration * 120000) - intTimeLeadChange;
	    	}
	    	if (intGoalDifference.equals(0)) {
	    		intTimeDraw = intTimeDraw + (duration * 120000) - intTimeLeadChange;
	    	}
	    	
	    	// Falls eine Mannschaft zum Ende des Spiels in Überzahl ist
	    	if (intPowerplay > 0) {
	    		intTimePowerplayHome = intTimePowerplayHome + (duration * 120000) - intTimePowerplayChange;
	    	}
	    	if (intPowerplay < 0) {
	    		intTimePowerplayAway = intTimePowerplayAway + (duration * 120000) - intTimePowerplayChange;
	    	}

	    	cTicker.close();
		
	    	// Betrag der maximalen Führung der Auswärtsmannschaft, da sonst negativ 
	    	intMaxLeadAway= Math.abs(intMaxLeadAway);
	    	
		// Zelltexte anlegen
		if(screenInch > 6) {
		
			statTeamValues = new String[] { res.getString(R.string.game_statistic),
					res.getString(R.string.goals),
					res.getString(R.string.total),
					res.getString(R.string.attempts),
					res.getString(R.string.efficiency),
					res.getString(R.string.seven_goal),
					res.getString(R.string.seven_attempts),
					res.getString(R.string.seven_efficiency),
					res.getString(R.string.fb_goal),
					res.getString(R.string.fb_attempts),
					res.getString(R.string.fb_efficiency),
					res.getString(R.string.tech_faults),
					res.getString(R.string.possession),
					res.getString(R.string.attacks),
					res.getString(R.string.time),
					res.getString(R.string.time_attack),
					res.getString(R.string.game_history),
					String.valueOf(duration / 3) + " " + res.getString(R.string.minutes),
					String.valueOf(duration / 3 * 2) + " " + res.getString(R.string.minutes),
					String.valueOf(duration / 3 * 3) + " " + res.getString(R.string.minutes),
					String.valueOf(duration / 3 * 4) + " " + res.getString(R.string.minutes),
					String.valueOf(duration / 3 * 5) + " " + res.getString(R.string.minutes),
					String.valueOf(duration * 2) + " " + res.getString(R.string.minutes),
					res.getString(R.string.lead),
					res.getString(R.string.draw),
					res.getString(R.string.max_lead),
					res.getString(R.string.penalties),
					res.getString(R.string.yellow_cards),
					res.getString(R.string.red_cards),
					res.getString(R.string.two_minutes),
					res.getString(R.string.two_plus_two),
					res.getString(R.string.powerplay),
					res.getString(R.string.goals_powerplay),
					res.getString(R.string.goals_shorthanded)};

			statHomeValues = new String[] { 
					"",
					"",
					String.valueOf(goals_home),
					String.valueOf(attempts_home),
					String.valueOf(goals_percent_home) + "%",
					String.valueOf(goals_7m_home),
					String.valueOf(attempts_7m_home),
					String.valueOf(percent_7m_home) + "%",
					String.valueOf(goals_fb_home),
					String.valueOf(attempts_fb_home),
					String.valueOf(percent_fb_home) + "%",
					String.valueOf(tech_faults_home),
					"",
					String.valueOf(intPossessionHome),
					String.valueOf(fctHelper.updateTimer(intPossessionTimeHome)),
					String.valueOf(fctHelper.updateTimer(intTimePossessionAttemptHome)),
					"",
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 2)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 3)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 4)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 5)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000)),
					fctHelper.updateTimer(intTimeLeadHome),
					fctHelper.updateTimer(intTimeDraw),
					String.valueOf(intMaxLeadHome),
					"",
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 1, null, yellow_card_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 1, null, red_card_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 1, null, two_minutes_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 1, null, twoplustwo_id, null, null)),
					fctHelper.updateTimer(intTimePowerplayHome),
					String.valueOf(intGoalsPowerplayHome),
					String.valueOf(intGoalsShorthandedHome)};

			intHomeValues = new int[] { 
					0,
					0,
					goals_home,
					attempts_home,
					goals_percent_home,
					goals_7m_home,
					attempts_7m_home,
					percent_7m_home,
					goals_fb_home,
					attempts_fb_home,
					percent_fb_home,
					tech_faults_home,
					0,
					intPossessionHome,
					intPossessionTimeHome,
					intTimePossessionAttemptHome,
					0,
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 2),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 3),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 4),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 5),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000),
					intTimeLeadHome,
					intTimeDraw,
					intMaxLeadHome,
					0,
					sqlHelper.count_ticker_activity(game_id, 1, null, yellow_card_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 1, null, red_card_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 1, null, two_minutes_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 1, null, twoplustwo_id, null, null),
					intTimePowerplayHome,
					intGoalsPowerplayHome,
					intGoalsShorthandedHome};

			statAwayValues = new String[] { 
					"",
					"",
					String.valueOf(goals_away),
					String.valueOf(attempts_away),
					String.valueOf(goals_percent_away) + "%",
					String.valueOf(goals_7m_away),
					String.valueOf(attempts_7m_away),
					String.valueOf(percent_7m_away) + "%",
					String.valueOf(goals_fb_away),
					String.valueOf(attempts_fb_away),
					String.valueOf(percent_fb_away) + "%",
					String.valueOf(tech_faults_away),
					"",
					String.valueOf(intPossessionAway),
					String.valueOf(fctHelper.updateTimer(intPossessionTimeAway)),
					String.valueOf(fctHelper.updateTimer(intTimePossessionAttemptAway)),
					"",
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 2)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 3)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 4)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 5)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000)),
					fctHelper.updateTimer(intTimeLeadAway),
					fctHelper.updateTimer(intTimeDraw),
					String.valueOf(intMaxLeadAway),
					"",
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 0, null, yellow_card_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 0, null, red_card_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 0, null, two_minutes_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 0, null, twoplustwo_id, null, null)),
					fctHelper.updateTimer(intTimePowerplayAway),
					String.valueOf(intGoalsPowerplayAway),
					String.valueOf(intGoalsShorthandedAway)};

			intAwayValues = new int[] { 
					0,
					0,
					goals_away,
					attempts_away,
					goals_percent_away,
					goals_7m_away,
					attempts_7m_away,
					percent_7m_away,
					goals_fb_away,
					attempts_fb_away,
					percent_fb_away,
					tech_faults_away,
					0,
					intPossessionAway,
					intPossessionTimeAway,
					intTimePossessionAttemptAway,
					0,
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 2),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 3),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 4),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 5),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000),
					intTimeLeadAway,
					intTimeDraw,
					intMaxLeadAway,
					0,
					sqlHelper.count_ticker_activity(game_id, 0, null, yellow_card_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 0, null, red_card_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 0, null, two_minutes_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 0, null, twoplustwo_id, null, null),
					intTimePowerplayAway,
					intGoalsPowerplayAway,
					intGoalsShorthandedAway};
			
		} else {
			
			statTeamValues = new String[] { res.getString(R.string.game_statistic),
											res.getString(R.string.goals),
											res.getString(R.string.total),
											res.getString(R.string.attempts),
											res.getString(R.string.efficiency),
											res.getString(R.string.seven_goal),
											res.getString(R.string.seven_attempts),
											res.getString(R.string.seven_efficiency),
											res.getString(R.string.fb_goal),
											res.getString(R.string.fb_attempts),
											res.getString(R.string.fb_efficiency),
											res.getString(R.string.tech_faults),
											res.getString(R.string.possession),
											res.getString(R.string.attacks),
											res.getString(R.string.time),
											res.getString(R.string.time_attack),
											res.getString(R.string.game_history),
											String.valueOf(duration / 3) + " " + res.getString(R.string.minutes),
											String.valueOf(duration / 3 * 2) + " " + res.getString(R.string.minutes),
											String.valueOf(duration / 3 * 3) + " " + res.getString(R.string.minutes),
											String.valueOf(duration / 3 * 4) + " " + res.getString(R.string.minutes),
											String.valueOf(duration / 3 * 5) + " " + res.getString(R.string.minutes),
											String.valueOf(duration * 2) + " " + res.getString(R.string.minutes),
											res.getString(R.string.lead),
											res.getString(R.string.draw),
											res.getString(R.string.max_lead),
											res.getString(R.string.penalties),
											res.getString(R.string.yellow_cards),
											res.getString(R.string.red_cards),
											res.getString(R.string.two_minutes),
											res.getString(R.string.two_plus_two),
											res.getString(R.string.powerplay),
											res.getString(R.string.goals_powerplay),
											res.getString(R.string.goals_shorthanded)};
			
			statHomeValues = new String[] { 
					"",
					"",
					String.valueOf(goals_home),
					String.valueOf(attempts_home),
					String.valueOf(goals_percent_home) + "%",
					String.valueOf(goals_7m_home),
					String.valueOf(attempts_7m_home),
					String.valueOf(percent_7m_home) + "%",
					String.valueOf(goals_fb_home),
					String.valueOf(attempts_fb_home),
					String.valueOf(percent_fb_home) + "%",
					String.valueOf(tech_faults_home),
					"",
					String.valueOf(intPossessionHome),
					String.valueOf(fctHelper.updateTimer(intPossessionTimeHome)),
					String.valueOf(fctHelper.updateTimer(intTimePossessionAttemptHome)),
					"",
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 2)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 3)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 4)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 5)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000)),
					fctHelper.updateTimer(intTimeLeadHome),
					fctHelper.updateTimer(intTimeDraw),
					String.valueOf(intMaxLeadHome),
					"",
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 1, null, yellow_card_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 1, null, red_card_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 1, null, two_minutes_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 1, null, twoplustwo_id, null, null)),
					fctHelper.updateTimer(intTimePowerplayHome),
					String.valueOf(intGoalsPowerplayHome),
					String.valueOf(intGoalsShorthandedHome)};
			
			intHomeValues = new int[] { 
					0,
					0,
					goals_home,
					attempts_home,
					goals_percent_home,
					goals_7m_home,
					attempts_7m_home,
					percent_7m_home,
					goals_fb_home,
					attempts_fb_home,
					percent_fb_home,
					tech_faults_home,
					0,
					intPossessionHome,
					intPossessionTimeHome,
					intTimePossessionAttemptHome,
					0,
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 2),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 3),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 4),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000 / 6 * 5),
					sqlHelper.count_ticker_goals(game_id, null, 1, duration * 120000),
					intTimeLeadHome,
					intTimeDraw,
					intMaxLeadHome,
					0,
					sqlHelper.count_ticker_activity(game_id, 1, null, yellow_card_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 1, null, red_card_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 1, null, two_minutes_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 1, null, twoplustwo_id, null, null),
					intTimePowerplayHome,
					intGoalsPowerplayHome,
					intGoalsShorthandedHome};
			
			statAwayValues = new String[] { 
					"",
					"",
					String.valueOf(goals_away),
					String.valueOf(attempts_away),
					String.valueOf(goals_percent_away) + "%",
					String.valueOf(goals_7m_away),
					String.valueOf(attempts_7m_away),
					String.valueOf(percent_7m_away) + "%",
					String.valueOf(goals_fb_away),
					String.valueOf(attempts_fb_away),
					String.valueOf(percent_fb_away) + "%",
					String.valueOf(tech_faults_away),
					"",
					String.valueOf(intPossessionAway),
					String.valueOf(fctHelper.updateTimer(intPossessionTimeAway)),
					String.valueOf(fctHelper.updateTimer(intTimePossessionAttemptAway)),
					"",
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 2)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 3)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 4)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 5)),
					String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000)),
					fctHelper.updateTimer(intTimeLeadAway),
					fctHelper.updateTimer(intTimeDraw),
					String.valueOf(intMaxLeadAway),
					"",
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 0, null, yellow_card_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 0, null, red_card_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 0, null, two_minutes_id, null, null)),
					String.valueOf(sqlHelper.count_ticker_activity(game_id, 0, null, twoplustwo_id, null, null)),
					fctHelper.updateTimer(intTimePowerplayAway),
					String.valueOf(intGoalsPowerplayAway),
					String.valueOf(intGoalsShorthandedAway)};
			
			intAwayValues = new int[] { 
					0,
					0,
					goals_away,
					attempts_away,
					goals_percent_away,
					goals_7m_away,
					attempts_7m_away,
					percent_7m_away,
					goals_fb_away,
					attempts_fb_away,
					percent_fb_away,
					tech_faults_away,
					0,
					intPossessionAway,
					intPossessionTimeAway,
					intTimePossessionAttemptAway,
					0,
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 2),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 3),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 4),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000 / 6 * 5),
					sqlHelper.count_ticker_goals(game_id, null, 0, duration * 120000),
					intTimeLeadAway,
					intTimeDraw,
					intMaxLeadAway,
					0,
					sqlHelper.count_ticker_activity(game_id, 0, null, yellow_card_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 0, null, red_card_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 0, null, two_minutes_id, null, null),
					sqlHelper.count_ticker_activity(game_id, 0, null, twoplustwo_id, null, null),
					intTimePowerplayAway,
					intGoalsPowerplayAway,
					intGoalsShorthandedAway};
			
		}
		
		HelperAdapterStatTeam adapter = new HelperAdapterStatTeam(ctxt, statHomeValues, statTeamValues, statAwayValues, intHomeValues, intAwayValues, game_id);
		
		if (screenInch > 6) {

			fragStatTeam = (TabFragStatTeam) fragmentManager.findFragmentById(R.id.frag_stat_content);
			fragStatTeam.setListAdapter(adapter);
			
		} else {
			
			statTeamListActivity.setListAdapter(adapter);
			
		}
		
	}

/*
 * 
 * Torschützenliste 
 *
 */
				
	public void lytStatScore(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager, final SmartStatScore activity) {
		
		view = contentView;
		statScoreListActivity = activity;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			// Get arguments
			game_id = args.getString("GameID");
			strInput = args.getString("InputString");
			
			team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			
		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Button einrichten */
		
		
		Button btn_left=(Button) view.findViewById(R.id.left);
		Button btn_right=(Button) view.findViewById(R.id.right);
		TextView text=(TextView) view.findViewById(R.id.text);
		
		if (strInput.equals("ScoreTotal") || strInput.equals("TabScoreTotal")) text.setText(res.getString(R.string.all));
		if (strInput.equals("ScoreHome") || strInput.equals("TabScoreHome")) text.setText(sqlHelper.getTeamClubNameByID(team_home_id));
		if (strInput.equals("ScoreAway") || strInput.equals("TabScoreAway")) text.setText(sqlHelper.getTeamClubNameByID(team_away_id));
		
		btn_left.setOnClickListener(new View.OnClickListener() {
		   		
			@Override
			public void onClick(View v) {
		
				if(screenInch > 6) {
				       
					if (strInput.equals("TabScoreHome")) args.putString("InputString", "TabScoreTotal");
					if (strInput.equals("TabScoreAway")) args.putString("InputString", "TabScoreHome");
					if (strInput.equals("TabScoreTotal")) args.putString("InputString", "TabScoreAway");
					
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragStatPlayerStatList fragment = new TabFragStatPlayerStatList();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_stat_content, fragment);
					fragmentTransaction.commit();
					
		    		} else {
		    			
		    			if (strInput.equals("ScoreHome")) args.putString("InputString", "ScoreTotal");
					if (strInput.equals("ScoreAway")) args.putString("InputString", "ScoreHome");
					if (strInput.equals("ScoreTotal")) args.putString("InputString", "ScoreAway");
					
		    			i = new Intent(ctxt, SmartStatScore.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
		    			
		    		}
			}
		});
			
		btn_right.setOnClickListener(new View.OnClickListener() {
		    		
		    	@Override
		    	public void onClick(View v) {
		    				
		    		if(screenInch > 6) {
					
		    			if (strInput.equals("TabScoreHome")) args.putString("InputString", "TabScoreAway");
			    		if (strInput.equals("TabScoreAway")) args.putString("InputString", "TabScoreTotal");
			    		if (strInput.equals("TabScoreTotal")) args.putString("InputString", "TabScoreHome");
			    		
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragStatPlayerStatList fragment = new TabFragStatPlayerStatList();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_stat_content, fragment);
					fragmentTransaction.commit();
						
		    		} else {
		    			
		    			if (strInput.equals("ScoreHome")) args.putString("InputString", "ScoreAway");
			    		if (strInput.equals("ScoreAway")) args.putString("InputString", "ScoreTotal");
			    		if (strInput.equals("ScoreTotal")) args.putString("InputString", "ScoreHome");
			    		
		    			i = new Intent(ctxt, SmartStatScore.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					((Activity)ctxt).finish();
		    				
		    		}
		    	}
		});
		
/* Tabelle einrichten */
		
		// Spieler eines Spiels auslesen
		HashMap<String, Integer> scoreMap = new HashMap<String, Integer>();
		int goals = 0;
		int goals_7m = 0;
		int goals_fb = 0;
		
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		Cursor cPlayer = null;
/** TODO -3- => In der Tablet-Version mehr Statistiken einbauen */ 
		if (strInput.equals("ScoreTotal") || strInput.equals("TabScoreTotal")) cPlayer = db.rawQuery("SELECT * FROM player WHERE team_id = ? OR team_id = ?", new String[] {team_home_id, team_away_id});
		if (strInput.equals("ScoreHome") || strInput.equals("TabScoreHome")) cPlayer = db.rawQuery("SELECT * FROM player WHERE team_id = ?", new String[] {team_home_id});
		if (strInput.equals("ScoreAway") || strInput.equals("TabScoreAway")) cPlayer = db.rawQuery("SELECT * FROM player WHERE team_id = ?", new String[] {team_away_id});
		
		// Alle Spieler eines Spiels erfassen, die ein Tor geworfen haben
		for (cPlayer.moveToFirst(); !cPlayer.isAfterLast(); cPlayer.moveToNext()) {
			
			player_id = sqlHelper.getPlayerID(cPlayer);
			goals = sqlHelper.count_ticker_activity(game_id, null, player_id, goal_id, null, null);
			goals_7m = sqlHelper.count_ticker_activity(game_id, null, player_id, goal_7m_id, null, null);
			goals_fb = sqlHelper.count_ticker_activity(game_id, null, player_id, goal_fb_id, null, null);
			
			if (goals + goals_7m + goals_fb > 0) {
				
				scoreMap.put(player_id, goals + goals_7m + goals_fb);
				
			}
		}
		
		// Spieler sortieren nach Toren
		// Convert Map to List
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(scoreMap.entrySet());
		 
		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		
		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		
		// Erste Reihe einfügenfür die Überschrift
		sortedMap.put("First Row", 999);
/** TODO -4- => Andere Lösung finden, um eine erste Reihe einzufügen */
		
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		HelperAdapterStatScore adapter = new HelperAdapterStatScore(ctxt, sortedMap, game_id, strInput);
		
		if (screenInch > 6) {
			
			fragStatPlayerList = (TabFragStatPlayerStatList) fragmentManager.findFragmentById(R.id.frag_stat_content);
			fragStatPlayerList.setListAdapter(adapter);
			
		} else {
			
			statScoreListActivity.setListAdapter(adapter);
			
		}	
	}
	
	
/*
 * 
 * Übersicht Spieler 
 *
 */
				
	public void lytStatPlayerOverview(final Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
		
			
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			// Get arguments
			player_id = args.getString("PlayerID");
			game_id = args.getString("GameID");
			home_or_away = args.getInt("HomeOrAway");
			
			team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			
			if (sqlHelper.getPlayerGameHomeOrAway(game_id, player_id).equals(1)) team_id = team_home_id;
			if (sqlHelper.getPlayerGameHomeOrAway(game_id, player_id).equals(0)) team_id = team_away_id;
			
			str_game_date = sqlHelper.getGameDateByID(game_id);
			team_home_short = sqlHelper.getClubShortByTeamID(team_home_id);
			team_away_short = sqlHelper.getClubShortByTeamID(team_away_id);
			
			player_number = sqlHelper.getPlayerNumberByID(player_id);
			player_forename = sqlHelper.getPlayerForenameByID(player_id);
			player_surename = sqlHelper.getPlayerSurenameByID(player_id);
			
			player = player_number + " " + player_forename + " " + player_surename;
			
			duration = sqlHelper.getGameDurationHalftimeByID(game_id);
			
			// Datum einrichten
			int year = 0;
			int day = 0;
			int month = 0;
			Date game_date = null;
			String str_game_date = sqlHelper.getGameDateByID(game_id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				game_date = sdf.parse(str_game_date);
				day = game_date.getDate();    // getDate => Day of month; getDay => day of week
				month = game_date.getMonth();
				year = game_date.getYear()+1900;
			}
			catch (ParseException e) {}
			
			str_game_date = (new StringBuilder()
				.append(day).append(".")
				.append(month + 1).append(".")
				.append(year))
				.toString();
			
			game = str_game_date + " " + team_home_short + " - " + team_away_short;
			
		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Layout einrichten */
		
		Button btn_left = null, btn_right = null, btn_up = null, btn_down = null, btn_tab_left = null;
		
		final TextView txt_goals = (TextView) view.findViewById(R.id.txt_goals);
		final TextView txt_effective = (TextView) view.findViewById(R.id.txt_effective);
		final TextView txt_time = (TextView) view.findViewById(R.id.txt_time);
		final TextView txt_mark = (TextView) view.findViewById(R.id.txt_mark);
		
		final ImageView img_goals = (ImageView) view.findViewById(R.id.img_goals);
		final ImageView img_effective = (ImageView) view.findViewById(R.id.img_effective);
		final ImageView img_time = (ImageView) view.findViewById(R.id.img_time);
		final ImageView img_mark = (ImageView) view.findViewById(R.id.img_mark);
		
		final LinearLayout lyt_view = (LinearLayout) view.findViewById(R.id.lyt_view);
		final LinearLayout lyt_overview = (LinearLayout) view.findViewById(R.id.lyt_overview_smart);
		final LinearLayout lyt_label1 = (LinearLayout) view.findViewById(R.id.lyt_label1);
		final LinearLayout lyt_label2 = (LinearLayout) view.findViewById(R.id.lyt_label2);
		
/* Statistik berechnen */

		// Tore berechnen
		final Integer goals = sqlHelper.count_ticker_goals(game_id, player_id, null, null);
		final Integer goals_total = sqlHelper.count_ticker_goals(game_id, null, home_or_away, null);
		String str_goals = String.valueOf(goals);
		
		// Effektivität berechnen
		final Integer attempts = sqlHelper.count_ticker_goal_attempts(game_id, player_id, null, null);
		String str_effective = "-";
		if (attempts > 0) str_effective = String.valueOf(goals * 100 / attempts) + "%";
		
		// Spielzeit berechnen
		time = sqlHelper.count_ticker_player_time(game_id, player_id,home_or_away, res);
		String str_time = fctHelper.updateTimer(time);
		
		// Spielernote berechnen
/** TODO -3- => Spielernote berechnen */
		final double mark = 2.5;
		String str_mark = String.valueOf(mark);
		
/* Text setzen */
		txt_goals.setText(str_goals);
		txt_effective.setText(str_effective);
		txt_time.setText(str_time);
		txt_mark.setText(str_mark);
		
/* Button definieren */
		
		final LinearLayout lyt_overview_smart = (LinearLayout) view.findViewById(R.id.lyt_overview_smart);
		final LinearLayout lyt_overview_tab = (LinearLayout) view.findViewById(R.id.lyt_overview_tab);
		
		if (screenInch > 6) {
			
			btn_tab_left = (Button) view.findViewById(R.id.tab_left);
			lyt_overview_smart.removeAllViews();
			
			btn_tab_left.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    			
		    			TabFragStatPlayerPositionOverview fragOverview = new TabFragStatPlayerPositionOverview();
		    			fragOverview.setArguments(args);
		    			fragmentTransaction.replace(R.id.frag_stat_player_content_1, fragOverview);
		    			
		    			TabFragStatPlayerPositionIndividual fragIndividual = new TabFragStatPlayerPositionIndividual();
		    			fragIndividual.setArguments(args);
		    			fragmentTransaction.replace(R.id.frag_stat_player_content_2, fragIndividual);
		    			fragmentTransaction.commit();

		            }
		      });
			
		} else {
			
			btn_left = (Button) view.findViewById(R.id.left);
			btn_right = (Button) view.findViewById(R.id.right);
			btn_up = (Button) view.findViewById(R.id.up);
			btn_down = (Button) view.findViewById(R.id.down);
			
			lyt_overview_tab.removeAllViews();
			
			TextView txt_player = (TextView) view.findViewById(R.id.player);
			TextView txt_game = (TextView) view.findViewById(R.id.game);
			
/* Text setzen */
			txt_player.setText(player);
			txt_game.setText(game);
			
/* Button definieren */
			
			btn_left.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			i = new Intent(ctxt, SmartStatPlayerPositionIndividual.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
			btn_up.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			// Vorherigen Spieler ermitteln
		    			// Cursor aller Spieler ermitteln
		    			c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		    			ArrayList<String> player = new ArrayList<String>();
		    			Integer player_active_nr = null;
		    			int counter = 0;
		    			
		    			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		    				
		    				player_team_id = sqlHelper.getPlayerID(c);
		    				
		    				// Überprüfen, ob der Spieler in dem Spiel gespielt hat
		    				if (sqlHelper.count_ticker_activity(game_id, null, player_team_id, null, null, null) > 0) {
		    					
		    					player.add(player_team_id);	
		    					// Falls Spieler identisch mit dem angezeigten Spieler, dann Nummer notieren
			    				if (player_id.equals(player_team_id)) player_active_nr = counter;
		    					// Zählen, wie viele Spieler in dem Spiel aktiv waren
			    				counter = counter + 1;
		    				
		    				}
		    			}
		    			
		    			// ID des vorherigen Spieler ermitteln; falls es der erste Spieler war, ID des letzten Spielers ermitteln
		    			if (player_active_nr.equals(0)) {
		    				player_id = player.get(counter - 1);
		    			} else {
		    				player_id = player.get(player_active_nr - 1);
		    			}
		    			
		    			args.putString("PlayerID", player_id);
		    			
					i = new Intent(ctxt, SmartStatPlayerStat.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
			btn_down.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			// Vorherigen Spieler ermitteln
		    			// Cursor aller Spieler ermitteln
		    			c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		    			ArrayList<String> player = new ArrayList<String>();
		    			Integer player_active_nr = null;
		    			int counter = 0;
		    			
		    			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		    				
		    				player_team_id = sqlHelper.getPlayerID(c);
		    				
		    				// Überprüfen, ob der Spieler in dem Spiel gespielt hat
		    				if (sqlHelper.count_ticker_activity(game_id, null, player_team_id, null, null, null) > 0) {
		    					
		    					player.add(player_team_id);	
		    					// Falls Spieler identisch mit dem angezeigten Spieler, dann Nummer notieren
			    				if (player_id.equals(player_team_id)) player_active_nr = counter;
		    					// Zählen, wie viele Spieler in dem Spiel aktiv waren
			    				counter = counter + 1;
		    				
		    				}
		    			}
		    			
		    			// ID des vorherigen Spieler ermitteln; falls es der erste Spieler war, ID des letzten Spielers ermitteln
		    			if (player_active_nr.equals(counter - 1)) {
		    				player_id = player.get(0);
		    			} else {
		    				player_id = player.get(player_active_nr + 1);
		    			}
		    			
		    			args.putString("PlayerID", player_id);
	
					i = new Intent(ctxt, SmartStatPlayerStat.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
		    			
		            }
		      });
			
			btn_right.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {

					i = new Intent(ctxt, SmartStatPlayerStat.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
		}
		
/* Kreisgraph zeichnen */
		
		// Die Kreisgrößen können erst zur Laufzeit der Activity gesetzt werden, da sonst die Breite und Höhe 0 ist 
		view.post(new Runnable() {
			
			@Override
			public void run() {
				
				int ly_width_overview, ly_height_overview, balance;
				
				if (screenInch > 6) {
					
					ly_width_overview = lyt_overview_tab.getWidth();
			  		ly_height_overview = lyt_overview_tab.getHeight();
					
				} else {
					
					ly_width_overview = lyt_overview_smart.getWidth();
			  		ly_height_overview = lyt_overview_smart.getHeight();
					
				}
				
				int ly_height_total = lyt_view.getHeight();
		  		int ly_height_label1 = lyt_label1.getHeight();
		  		int ly_height_label2 = lyt_label2.getHeight();
		  		
		  		int x_length = ly_width_overview / 2;
		  		int y_length = (ly_height_total - ly_height_overview - ly_height_label1 - ly_height_label2) / 2;
		  		int size = 0;
		  		int x_left_coord = 0;
		  		int y_up_coord = 0;
		  		int x_right_coord = 0;
		  		int y_down_coord = 0;
		  		
		  		if (x_length < y_length) {
		  			
		  			size = x_length;
		  			balance = (y_length - size) / 2;
		  			x_left_coord = size / 10;
		  			y_up_coord = (size / 10) + balance;
		  			x_right_coord = size - (size / 10);
		  			y_down_coord = y_length - (size / 10) - balance;
		  			
		  		} else {
		  			
		  			size = y_length;
		  			balance = (x_length - size) / 2;
		  			x_left_coord = (size / 10) + balance;
		  			y_up_coord = size / 10;
		  			x_right_coord = x_length - (size / 10) - balance;
		  			y_down_coord = size - (size / 10);
		  			
		  		}
		  		
		  		// Kreislayout definieren
		  		Paint paintStat = new Paint();
		  		Paint paint360 = new Paint();
		  		paint360.setAntiAlias(true);
		  		paint360.setColor(res.getColor(R.color.grey_dark));
		  		paint360.setStyle(Paint.Style.STROKE);
		  		paint360.setStrokeWidth(size / 10);
		  		
		  		paintStat.setAntiAlias(true);
		  		paintStat.setColor(Color.WHITE);
		  		paintStat.setStyle(Paint.Style.STROKE);
		  		paintStat.setStrokeWidth(size / 10);
		  		
		  		// Textgröße setzen
		  		txt_goals.setTextSize(size / 15); 
		  		txt_effective.setTextSize(size / 15); 
		  		txt_time.setTextSize(size / 15); 
		  		txt_mark.setTextSize(size / 15); 			
				
				for (int x = 1; x <= 4; x++) {
					
					int grade = 0;
					
					switch(x) {
				    	
						case 1:
							
							// Grafik einrichten
					  		Bitmap bitMap = Bitmap.createBitmap(x_length, y_length, Bitmap.Config.ARGB_8888);
					  		bitMap = bitMap.copy(bitMap.getConfig(), true);
					  		RectF rectF = new RectF(x_left_coord, y_up_coord, x_right_coord, y_down_coord);
					  		Canvas canvas = new Canvas(bitMap);
					  		
							icon = img_goals;
							if (goals_total > 0) grade = (goals * 360 / goals_total); else grade = 0;
							// Gesamtkreis zeichnen
					  		canvas.drawArc (rectF, 0, 360, false, paint360);
					  		// Anteiligen Kreis zeichnen
					  		canvas.drawArc (rectF, -90, grade, false, paintStat);
					  		icon.setImageBitmap(bitMap);
					  		
							break;
						
						case 2:
							
							icon = img_effective;
							if (attempts > 0) grade = goals * 360 / attempts; else grade = 0;
							
							// Grafik einrichten
					  		Bitmap bitMap2 = Bitmap.createBitmap(x_length, y_length, Bitmap.Config.ARGB_8888);
					  		bitMap = bitMap2.copy(bitMap2.getConfig(), true);
					  		RectF rectF2 = new RectF(x_left_coord, y_up_coord, x_right_coord, y_down_coord);
					  		Canvas canvas2 = new Canvas(bitMap2);
					  		
							// Gesamtkreis zeichnen
					  		canvas2.drawArc (rectF2, 0, 360, false, paint360);
					  		// Anteiligen Kreis zeichnen
					  		canvas2.drawArc (rectF2, -90, grade, false, paintStat);
					  		icon.setImageBitmap(bitMap2);
					  		
							break;
						
						case 3:
							
							icon = img_time;
							if (duration > 0) grade = time * 360 / (duration * 120000); else grade = 0;
							
							// Grafik einrichten
					  		Bitmap bitMap3 = Bitmap.createBitmap(x_length, y_length, Bitmap.Config.ARGB_8888);
					  		bitMap = bitMap3.copy(bitMap3.getConfig(), true);
					  		RectF rectF3 = new RectF(x_left_coord, y_up_coord, x_right_coord, y_down_coord);
					  		Canvas canvas3 = new Canvas(bitMap3);
					  		
							// Gesamtkreis zeichnen
					  		canvas3.drawArc (rectF3, 0, 360, false, paint360);
					  		// Anteiligen Kreis zeichnen
					  		canvas3.drawArc (rectF3, -90, grade, false, paintStat);
					  		icon.setImageBitmap(bitMap3);
					  		
							break;
	
						case 4:
							
							icon = img_mark;
							grade = (int) (long) (mark * 360 / 5);
							
							// Grafik einrichten
					  		Bitmap bitMap4 = Bitmap.createBitmap(x_length, y_length, Bitmap.Config.ARGB_8888);
					  		bitMap = bitMap4.copy(bitMap4.getConfig(), true);
					  		RectF rectF4 = new RectF(x_left_coord, y_up_coord, x_right_coord, y_down_coord);
					  		Canvas canvas4 = new Canvas(bitMap4);
					  		
							// Gesamtkreis zeichnen
					  		canvas4.drawArc (rectF4, 0, 360, false, paint360);
					  		// Anteiligen Kreis zeichnen
					  		canvas4.drawArc (rectF4, -90, grade, false, paintStat);
					  		icon.setImageBitmap(bitMap4);
							
							break;
						
					}	
				}
			}
		} );

	}
	

/*
 * 
 * Torschützenliste 
 *
 */
				
	public void lytStatPlayerStat(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager, final SmartStatPlayerStat activity) {
		
		view = contentView;
		statPlayerStatActivity = activity;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			// Get arguments
			game_id = args.getString("GameID");
			player_id = args.getString("PlayerID");
			
			team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			
			if (sqlHelper.getPlayerGameHomeOrAway(game_id, player_id).equals(1)) team_id = team_home_id;
			if (sqlHelper.getPlayerGameHomeOrAway(game_id, player_id).equals(0)) team_id = team_away_id;
			
			str_game_date = sqlHelper.getGameDateByID(game_id);
			team_home_short = sqlHelper.getClubNameByTeamID(team_home_id);
			team_away_short = sqlHelper.getClubNameByTeamID(team_away_id);
			
			player_number = sqlHelper.getPlayerNumberByID(player_id);
			player_forename = sqlHelper.getPlayerForenameByID(player_id);
			player_surename = sqlHelper.getPlayerSurenameByID(player_id);
			
			player = player_number + " " + player_forename + " " + player_surename;
			
			duration = sqlHelper.getGameDurationHalftimeByID(game_id);
			
			home_or_away = args.getInt("HomeOrAway");
			
			// Datum einrichten
			int year = 0;
			int day = 0;
			int month = 0;
			Date game_date = null;
			String str_game_date = sqlHelper.getGameDateByID(game_id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				game_date = sdf.parse(str_game_date);
				day = game_date.getDate();    // getDate => Day of month; getDay => day of week
				month = game_date.getMonth();
				year = game_date.getYear()+1900;
			}
			catch (ParseException e) {}
			
			str_game_date = (new StringBuilder()
				.append(day).append(".")
				.append(month + 1).append(".")
				.append(year))
				.toString();
			
			game = str_game_date + " " + team_home_short + " - " + team_away_short;
			
		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Layout einrichten */
		
		Button btn_left = null, btn_right = null, btn_up = null, btn_down = null, btn_tab_right = null;
		
		if(screenInch > 6) {
		
			btn_right = (Button) view.findViewById(R.id.tab_right);
			
			LinearLayout lyt_overview_smart = (LinearLayout) view.findViewById(R.id.lyt_overview_smart);
			lyt_overview_smart.removeAllViews();
			
			btn_right.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    			
		    			TabFragStatPlayerPositionOverview fragOverview = new TabFragStatPlayerPositionOverview();
		    			fragOverview.setArguments(args);
		    			fragmentTransaction.replace(R.id.frag_stat_player_content_1, fragOverview);
		    			
		    			TabFragStatPlayerPositionIndividual fragIndividual = new TabFragStatPlayerPositionIndividual();
		    			fragIndividual.setArguments(args);
		    			fragmentTransaction.replace(R.id.frag_stat_player_content_2, fragIndividual);
		    			fragmentTransaction.commit();

		            }
		      });
			
		} else {
			
			btn_left = (Button) view.findViewById(R.id.left);
			btn_right = (Button) view.findViewById(R.id.right);
			btn_up = (Button) view.findViewById(R.id.up);
			btn_down = (Button) view.findViewById(R.id.down);
			
			LinearLayout lyt_overview_tab = (LinearLayout) view.findViewById(R.id.lyt_overview_tab);
			lyt_overview_tab.removeAllViews();
			
			TextView txt_player = (TextView) view.findViewById(R.id.player);
			TextView txt_game = (TextView) view.findViewById(R.id.game);
			
/* Text setzen */
			txt_player.setText(player);
			txt_game.setText(game);
			
/* Button definieren */
			
			btn_left.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
					i = new Intent(ctxt, SmartStatPlayerFirst.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
			btn_up.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			// Vorherigen Spieler ermitteln
		    			// Cursor aller Spieler ermitteln
		    			c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		    			ArrayList<String> player = new ArrayList<String>();
		    			Integer player_active_nr = null;
		    			int counter = 0;
		    			
		    			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		    				
		    				player_team_id = sqlHelper.getPlayerID(c);
		    				
		    				// Überprüfen, ob der Spieler in dem Spiel gespielt hat
		    				if (sqlHelper.count_ticker_activity(game_id, null, player_team_id, null, null, null) > 0) {
		    					
		    					player.add(player_team_id);	
		    					// Falls Spieler identisch mit dem angezeigten Spieler, dann Nummer notieren
			    				if (player_id.equals(player_team_id)) player_active_nr = counter;
		    					// Zählen, wie viele Spieler in dem Spiel aktiv waren
			    				counter = counter + 1;
		    				
		    				}
		    			}
		    			
		    			// ID des vorherigen Spieler ermitteln; falls es der erste Spieler war, ID des letzten Spielers ermitteln
		    			if (player_active_nr.equals(0)) {
		    				player_id = player.get(counter - 1);
		    			} else {
		    				player_id = player.get(player_active_nr - 1);
		    			}
		    			
		    			args.putString("PlayerID", player_id);
		    			
					i = new Intent(ctxt, SmartStatPlayerStat.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
			btn_down.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			// Vorherigen Spieler ermitteln
		    			// Cursor aller Spieler ermitteln
		    			c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		    			ArrayList<String> player = new ArrayList<String>();
		    			Integer player_active_nr = null;
		    			int counter = 0;
		    			
		    			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		    				
		    				player_team_id = sqlHelper.getPlayerID(c);
		    				
		    				// Überprüfen, ob der Spieler in dem Spiel gespielt hat
		    				if (sqlHelper.count_ticker_activity(game_id, null, player_team_id, null, null, null) > 0) {
		    					
		    					player.add(player_team_id);	
		    					// Falls Spieler identisch mit dem angezeigten Spieler, dann Nummer notieren
			    				if (player_id.equals(player_team_id)) player_active_nr = counter;
		    					// Zählen, wie viele Spieler in dem Spiel aktiv waren
			    				counter = counter + 1;
		    				
		    				}
		    			}
		    			
		    			// ID des vorherigen Spieler ermitteln; falls es der erste Spieler war, ID des letzten Spielers ermitteln
		    			if (player_active_nr.equals(counter - 1)) {
		    				player_id = player.get(0);
		    			} else {
		    				player_id = player.get(player_active_nr + 1);
		    			}
		    			
		    			args.putString("PlayerID", player_id);
	
					i = new Intent(ctxt, SmartStatPlayerStat.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
		    			
		            }
		      });
			
			btn_right.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {

					i = new Intent(ctxt, SmartStatPlayerPositionOverview.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
		}

/* Tabelle einrichten */
		
		ArrayList<Integer> statPlayerList = sqlHelper.statPlayer(game_id, player_id, home_or_away, res);
		LinkedHashMap<String, String> playerStatMap = new LinkedHashMap<String, String>();
		String stat_title = null;
/** TODO -4- => Für Statistik-Liste der Spielaktionen andere Lösung finden, die das Problem abfängt, wenn Spielaktion hinzugefügt wird. */
		for (int x = 0; x <= 69; x++) {
			
			// Listenbezeichnungen festsetzen
			if (x == 0) stat_title = res.getString(R.string.goals);
			if (x == 1) stat_title = res.getString(R.string.attempts);
			if (x == 2) stat_title = res.getString(R.string.percentage);
			if (x == 3) stat_title = res.getString(R.string.seven_goals);
			if (x == 4) stat_title = res.getString(R.string.seven_attempts);
			if (x == 5) stat_title = res.getString(R.string.seven_percentage);
			if (x == 6) stat_title = res.getString(R.string.fb_goals);
			if (x == 7) stat_title = res.getString(R.string.fb_attempts);
			if (x == 8) stat_title = res.getString(R.string.fb_percentage);
			if (x == 9) stat_title = res.getString(R.string.Sprungwurf);
			if (x == 10) stat_title = res.getString(R.string.Sprungwurf_attempts);
			if (x == 11) stat_title = res.getString(R.string.Sprungwurf_percentage);
			if (x == 12) stat_title = res.getString(R.string.Schlagwurf);
			if (x == 13) stat_title = res.getString(R.string.Schlagwurf_attempts);
			if (x == 14) stat_title = res.getString(R.string.Schlagwurf_percentage);
			if (x == 15) stat_title = res.getString(R.string.Laufwurf);
			if (x == 16) stat_title = res.getString(R.string.Laufwurf_attempts);
			if (x == 17) stat_title = res.getString(R.string.Laufwurf_percentage);
			if (x == 18) stat_title = res.getString(R.string.Fallwurf);
			if (x == 19) stat_title = res.getString(R.string.Fallwurf_attempts);
			if (x == 20) stat_title = res.getString(R.string.Fallwurf_percentage);
			if (x == 21) stat_title = res.getString(R.string.Hüftwurf);
			if (x == 22) stat_title = res.getString(R.string.Hüftwurf_attempts);
			if (x == 23) stat_title = res.getString(R.string.Hüftwurf_percentage);
			if (x == 24) stat_title = res.getString(R.string.kempa);
			if (x == 25) stat_title = res.getString(R.string.kempa_attempts);
			if (x == 26) stat_title = res.getString(R.string.kempa_percentage);
			if (x == 27) stat_title = res.getString(R.string.Dreher);
			if (x == 28) stat_title = res.getString(R.string.Dreher_attempts);
			if (x == 29) stat_title = res.getString(R.string.Dreher_percentage);
			if (x == 30) stat_title = res.getString(R.string.Heber);
			if (x == 31) stat_title = res.getString(R.string.Heber_attempts);
			if (x == 32) stat_title = res.getString(R.string.Heber_percentage);
			if (x == 33) stat_title = res.getString(R.string.Leger);
			if (x == 34) stat_title = res.getString(R.string.Leger_attempts);
			if (x == 35) stat_title = res.getString(R.string.Leger_percentage);
			if (x == 36) stat_title = res.getString(R.string.Abknickwurf);
			if (x == 37) stat_title = res.getString(R.string.Abknickwurf_attempts);
			if (x == 38) stat_title = res.getString(R.string.Abknickwurf_percentage);
			if (x == 39) stat_title = res.getString(R.string.gk_save);
			if (x == 40) stat_title = res.getString(R.string.throws_str);
			if (x == 41) stat_title = res.getString(R.string.throws_percentage);
			if (x == 42) stat_title = res.getString(R.string.seven_save);
			if (x == 43) stat_title = res.getString(R.string.seven_throws_str);
			if (x == 44) stat_title = res.getString(R.string.seven_throws_percentage);
			if (x == 45) stat_title = res.getString(R.string.fb_save);
			if (x == 46) stat_title = res.getString(R.string.fb_throws_str);
			if (x == 47) stat_title = res.getString(R.string.fb_throws_percentage);
			if (x == 48) stat_title = res.getString(R.string.assists_goal);
			if (x == 49) stat_title = res.getString(R.string.assists);
			if (x == 50) stat_title = res.getString(R.string.defense_successful);
			if (x == 51) stat_title = res.getString(R.string.defensive_error);
			if (x == 52) stat_title = res.getString(R.string.block_successful);
			if (x == 53) stat_title = res.getString(R.string.block_error);
			if (x == 54) stat_title = res.getString(R.string.fouls);
			if (x == 55) stat_title = res.getString(R.string.tech_faults);
			if (x == 56) stat_title = res.getString(R.string.Fehlpass);
			if (x == 57) stat_title = res.getString(R.string.steps);
			if (x == 58) stat_title = res.getString(R.string.three_seconds_rule);
			if (x == 59) stat_title = res.getString(R.string.Doppeldribbel);
			if (x == 60) stat_title = res.getString(R.string.Fuss);
			if (x == 61) stat_title = res.getString(R.string.Zeitspiel);
			if (x == 62) stat_title = res.getString(R.string.Kreis);
			if (x == 63) stat_title = res.getString(R.string.Stuermerfoul);
			if (x == 64) stat_title = res.getString(R.string.yellow_cards);
			if (x == 65) stat_title = res.getString(R.string.two_minutes);
			if (x == 66) stat_title = res.getString(R.string.two_plus_two);
			if (x == 67) stat_title = res.getString(R.string.red_cards);
			if (x == 68) stat_title = res.getString(R.string.playing_time);
			if (x == 69) stat_title = res.getString(R.string.plus_minus);
			
			switch(x) {
		    	
			case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
			case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
			case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29:
			case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
			case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47:
				
				// Kontrollieren, ob Torwürfe erfasst, sonst Torstatistik überspringen
				if (statPlayerList.get(0) + statPlayerList.get(1) + statPlayerList.get(2) + statPlayerList.get(3) + statPlayerList.get(4) +
						statPlayerList.get(5) + statPlayerList.get(6) + statPlayerList.get(7) + statPlayerList.get(8) + statPlayerList.get(9) +
						statPlayerList.get(10) + statPlayerList.get(11) + statPlayerList.get(12) + statPlayerList.get(13) + statPlayerList.get(14) +
						statPlayerList.get(15) + statPlayerList.get(16) + statPlayerList.get(17) + statPlayerList.get(18) + statPlayerList.get(19) +
						statPlayerList.get(20) + statPlayerList.get(21) + statPlayerList.get(22) + statPlayerList.get(23) + statPlayerList.get(24) +
						statPlayerList.get(25) + statPlayerList.get(26) + statPlayerList.get(27) + statPlayerList.get(28) + statPlayerList.get(29) +
						statPlayerList.get(30) + statPlayerList.get(31) + statPlayerList.get(32) + statPlayerList.get(33) + statPlayerList.get(34) +
						statPlayerList.get(35) + statPlayerList.get(36) + statPlayerList.get(37) + statPlayerList.get(38) + statPlayerList.get(39) +
						statPlayerList.get(40) + statPlayerList.get(41) + statPlayerList.get(42) + statPlayerList.get(43) + statPlayerList.get(44) +
						statPlayerList.get(45) + statPlayerList.get(46) + statPlayerList.get(47) > 0) {
					
					if (x == 0) {
						
						// Erste Überschrift für Würfe eintragen
						playerStatMap.put(res.getString(R.string.shooting_statistic) ,"");
						
					}
					
					if (statPlayerList.get(x) > 0) {

						playerStatMap.put(stat_title, String.valueOf(statPlayerList.get(x)));
						
					}
					
				} else {
					
					x = 47;
					
				}
				
				break;
				
			case 48: case 49: 
				
				// Kontrollieren, ob Vorlagen erfasst, sonst Vorlagen überspringen
				if (statPlayerList.get(48) + statPlayerList.get(49) > 0) {
					
					if (x == 48) {
						
						// Erste Überschrift für Würfe eintragen
						playerStatMap.put(res.getString(R.string.assists) ,"");
						
					}
					
					if (statPlayerList.get(x) > 0) {

						playerStatMap.put(stat_title, String.valueOf(statPlayerList.get(x)));
						
					}
					
				} else {
					
					x = 49;
					
				}
				
				break;
				
			case 50: case 51: case 52: case 53: case 54: 
				
				// Kontrollieren, ob Abwehr erfasst, sonst Abwehr überspringen
				if (statPlayerList.get(50) + statPlayerList.get(51) + statPlayerList.get(52) + 
						statPlayerList.get(53) +statPlayerList.get(54) > 0) {
					
					if (x == 50) {
						
						// Erste Überschrift für Abwehr eintragen
						playerStatMap.put(res.getString(R.string.defense) ,"");
						
					}
					
					if (statPlayerList.get(x) > 0) {

						playerStatMap.put(stat_title, String.valueOf(statPlayerList.get(x)));
						
					}
					
				} else {
					
					x = 54;
					
				}
				
				break;
				
			case 55: case 56: case 57: case 58: case 59: case 60: case 61: case 62: case 63:
				
				// Kontrollieren, ob Technische Fehler erfasst, sonst Technische Fehler überspringen
				if (statPlayerList.get(55) + statPlayerList.get(56) + statPlayerList.get(57) + 
						statPlayerList.get(58) +statPlayerList.get(59) + statPlayerList.get(59) + 
						statPlayerList.get(60) + statPlayerList.get(61)  + statPlayerList.get(62)  + statPlayerList.get(63) > 0) {
					
					if (x == 55) {
						
						// Erste Überschrift für Technische Fehler eintragen
						playerStatMap.put(res.getString(R.string.fault) ,"");
						
					}
					
					if (statPlayerList.get(x) > 0) {

						playerStatMap.put(stat_title, String.valueOf(statPlayerList.get(x)));
						
					}
					
				} else {
					
					x = 63;
					
				}
				
				break;
				
			case 64: case 65: case 66: case 67:  
				
				// Kontrollieren, ob Strafen erfasst, sonst Strafen überspringen
				if (statPlayerList.get(64) + statPlayerList.get(65) +
						statPlayerList.get(66) + statPlayerList.get(67) > 0) {
					
					if (x == 64) {
						
						// Erste Überschrift für Strafen eintragen
						playerStatMap.put(res.getString(R.string.penalties) ,"");
						
					}
					
					if (statPlayerList.get(x) > 0) {

						playerStatMap.put(stat_title, String.valueOf(statPlayerList.get(x)));
						
					}
					
				} else {
					
					x = 67;
					
				}
				
				break;
				
			case 68: case 69:   
				
				// Kontrollieren, ob Spielzeit erfasst, sonst Spielzeit überspringen
				if (statPlayerList.get(68) + statPlayerList.get(69) > 0) {
					
					if (x == 68) {
						
						// Erste Überschrift für Spielzeit eintragen
						playerStatMap.put(res.getString(R.string.Spielanteil) ,"");
						
					}
					
					if (statPlayerList.get(x) > 0) {

						playerStatMap.put(stat_title, String.valueOf(statPlayerList.get(x)));
						
					}
					
				} else {
					
					x = 69;
					
				}
				
				break;
			}
		}
		
		HelperAdapterStatPlayer adapter = new HelperAdapterStatPlayer(ctxt, playerStatMap);
		
		if (screenInch > 6) {
			
			fragStatPlayerStat = (TabFragStatPlayerStat) fragmentManager.findFragmentById(R.id.frag_stat_player_content_2);
			fragStatPlayerStat.setListAdapter(adapter);
			
		} else {
			
			statPlayerStatActivity.setListAdapter(adapter);
			
		}
	}
	
/*
 * 
 * Auswahl Spielerstatistik definieren 
 *
 */
				
	public void lytStatPlayerTicker(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager, final SmartStatPlayerTicker activity) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		smartPlayerTickerActivity = activity;
		String statLabel = null;
		
/* Helper definieren */
		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */
		// Inch
		screenInch = fctHelper.getScreenInch(ctxt);
		
		if (screenInch > 6) {
		
			Button btn_back = (Button) view.findViewById(R.id.left);
			
			btn_back.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
							
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragStatPlayerStat fragment = new TabFragStatPlayerStat();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_stat_player_content_2, fragment);
					fragmentTransaction.commit();
				}
			});
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			// Get arguments
			game_id = args.getString("GameID");
			player_id = args.getString("PlayerID");
			statLabel = args.getString("Stat");
			
		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Statistik abfragen */
		
		if (statLabel.equals(res.getString(R.string.goals)) || 
				statLabel.equals(res.getString(R.string.seven_goals)) ||
				statLabel.equals(res.getString(R.string.fb_goals)) ||
				statLabel.equals(res.getString(R.string.Sprungwurf)) ||
				statLabel.equals(res.getString(R.string.Schlagwurf)) ||
				statLabel.equals(res.getString(R.string.Laufwurf)) ||
				statLabel.equals(res.getString(R.string.Fallwurf)) ||
				statLabel.equals(res.getString(R.string.Hüftwurf)) ||
				statLabel.equals(res.getString(R.string.Dreher)) ||
				statLabel.equals(res.getString(R.string.Heber)) ||
				statLabel.equals(res.getString(R.string.Leger)) ||
				statLabel.equals(res.getString(R.string.Abknickwurf)) ||
				statLabel.equals(res.getString(R.string.gk_save)) ||
				statLabel.equals(res.getString(R.string.seven_save)) ||
				statLabel.equals(res.getString(R.string.fb_save)) ||
				statLabel.equals(res.getString(R.string.assists_goal)) ||
				statLabel.equals(res.getString(R.string.assists)) ||
				statLabel.equals(res.getString(R.string.attempts)) ||
				statLabel.equals(res.getString(R.string.percentage)) ||
				statLabel.equals(res.getString(R.string.throws_str))) {
			
			String[] statArgs = {game_id, player_id, String.valueOf(goal_id), String.valueOf(goal_7m_id), String.valueOf(goal_fb_id), String.valueOf(miss_id), 
					    String.valueOf(miss_7m_id), String.valueOf(miss_fb_id), String.valueOf(save_id), String.valueOf(save_7m_id), 
					    String.valueOf(save_fb_id), String.valueOf(goal_against_id), String.valueOf(goal_against_7m_id), String.valueOf(goal_against_fb_id), 
					    String.valueOf(assist_goal_id), String.valueOf(assist_miss_id)};
			SQLiteDatabase db = sqlHelper.getWritableDatabase();
			c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
						"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
						"activity_id = ? OR activity_id = ?) ORDER BY time DESC", statArgs);
			
		}
		
		if (statLabel.equals(res.getString(R.string.defense_successful)) || 
				statLabel.equals(res.getString(R.string.defensive_error)) ||
				statLabel.equals(res.getString(R.string.block_successful)) ||
				statLabel.equals(res.getString(R.string.block_error))) {
			
			String[] statArgs = {game_id, player_id, String.valueOf(defense_error_id), String.valueOf(defense_success_id), String.valueOf(block_error_id), 
					    String.valueOf(block_success_id), String.valueOf(foul_id)};
			SQLiteDatabase db = sqlHelper.getWritableDatabase();
			c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
						"activity_id = ? OR activity_id = ?) ORDER BY time DESC", statArgs);
			
		}
		
		if (statLabel.equals(res.getString(R.string.tech_faults)) || 
				statLabel.equals(res.getString(R.string.Fehlpass)) ||
				statLabel.equals(res.getString(R.string.steps)) ||
				statLabel.equals(res.getString(R.string.three_seconds_rule)) ||
				statLabel.equals(res.getString(R.string.Doppeldribbel)) ||
				statLabel.equals(res.getString(R.string.Fuss)) ||
				statLabel.equals(res.getString(R.string.Zeitspiel)) ||
				statLabel.equals(res.getString(R.string.Kreis)) ||
				statLabel.equals(res.getString(R.string.Stuermerfoul))) {
			
			String[] statArgs = {game_id, player_id, String.valueOf(tech_fault_id), String.valueOf(fehlpass_id), String.valueOf(steps_id),  String.valueOf(three_seconds_id), 
					    String.valueOf(doppeldribbel_id), String.valueOf(fuss_id), String.valueOf(zeitspiel_id), String.valueOf(kreis_id), String.valueOf(stuermerfoul_id)};
			SQLiteDatabase db = sqlHelper.getWritableDatabase();
			c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
						"activity_id = ? OR activity_id = ? OR activity_id = ?  OR activity_id = ?  OR activity_id = ?  OR activity_id = ?) ORDER BY time DESC", statArgs);
			
		}
		
		if (statLabel.equals(res.getString(R.string.yellow_cards)) || 
				statLabel.equals(res.getString(R.string.two_minutes)) ||
				statLabel.equals(res.getString(R.string.two_plus_two)) ||
				statLabel.equals(res.getString(R.string.two_plus_two)) ||
				statLabel.equals(res.getString(R.string.red_cards))) {
			
			String[] statArgs = {game_id, player_id, String.valueOf(yellow_card_id), String.valueOf(two_minutes_id), String.valueOf(twoplustwo_id), 
					    String.valueOf(red_card_id)};
			SQLiteDatabase db = sqlHelper.getWritableDatabase();
			c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
						"activity_id = ?) ORDER BY time DESC", statArgs);
			
		}
		
		if (statLabel.equals(res.getString(R.string.playing_time)) || 
				statLabel.equals(res.getString(R.string.plus_minus))) {
			
			String[] statArgs = {game_id, player_id, String.valueOf(sub_in_id), String.valueOf(sub_out_id), String.valueOf(two_in_id)};
			SQLiteDatabase db = sqlHelper.getWritableDatabase();
			c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR " +
						"activity_id = ?) ORDER BY time DESC", statArgs);
			
		}
		
		HelperAdapterTicker adapter = new HelperAdapterTicker(ctxt, c, null);
		
		if (screenInch > 6) {
			
			fragStatPlayerTicker = (TabFragStatPlayerTicker) fragmentManager.findFragmentById(R.id.frag_stat_player_content_2);
			fragStatPlayerTicker.setListAdapter(adapter);
			
		} else {
			
			smartPlayerTickerActivity.setListAdapter(adapter);
			
		}	
	}
	
	
/*
 * 
 * Wurfstatistik - Übersicht 
 *
 */
	
	public void lytStatPlayerPositionOverview(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			player_id = args.getString("PlayerID");
			
			// Spieldaten ermitteln
			team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			team_home_short = sqlHelper.getClubShortByTeamID(team_home_id);
			team_away_short = sqlHelper.getClubShortByTeamID(team_away_id);
			
			if (sqlHelper.getPlayerGameHomeOrAway(game_id, player_id).equals(1)) team_id = team_home_id;
			if (sqlHelper.getPlayerGameHomeOrAway(game_id, player_id).equals(0)) team_id = team_away_id;
			
			// Datum einrichten
			int year = 0;
			int day = 0;
			int month = 0;
			Date game_date = null;
			String str_game_date = sqlHelper.getGameDateByID(game_id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				game_date = sdf.parse(str_game_date);
				day = game_date.getDate();    // getDate => Day of month; getDay => day of week
				month = game_date.getMonth();
				year = game_date.getYear()+1900;
			}
			catch (ParseException e) {}
			
			str_game_date = (new StringBuilder()
				.append(day).append(".")
				.append(month + 1).append(".")
				.append(year))
				.toString();
			
			game = str_game_date + " " + team_home_short + " - " + team_away_short;
			
			// Spielerdaten ermitteln
			player_number = sqlHelper.getPlayerNumberByID(player_id);
			player_forename = sqlHelper.getPlayerForenameByID(player_id);
			player_surename = sqlHelper.getPlayerSurenameByID(player_id);
			
			player = player_number + " " + player_forename + " " + player_surename;
						
		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Layout einrichten */

		Button btn_left = null, btn_right = null, btn_up = null, btn_down = null, btn_tab_left = null;
		
		if(screenInch > 6) {
		
			btn_tab_left = (Button) view.findViewById(R.id.tab_left);
			
			LinearLayout lyt_overview_smart = (LinearLayout) view.findViewById(R.id.lyt_overview_smart);
			lyt_overview_smart.removeAllViews();
			
			btn_tab_left.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    			
		    			TabFragStatPlayerOverview fragOverview = new TabFragStatPlayerOverview();
		    			fragOverview.setArguments(args);
		    			fragmentTransaction.replace(R.id.frag_stat_player_content_1, fragOverview);
		    			
		    			TabFragStatPlayerStat fragIndividual = new TabFragStatPlayerStat();
		    			fragIndividual.setArguments(args);
		    			fragmentTransaction.replace(R.id.frag_stat_player_content_2, fragIndividual);
		    			fragmentTransaction.commit();

		            }
		      });
			
		} else {
			
			btn_left = (Button) view.findViewById(R.id.left);
			btn_right = (Button) view.findViewById(R.id.right);
			btn_up = (Button) view.findViewById(R.id.up);
			btn_down = (Button) view.findViewById(R.id.down);
			
			LinearLayout lyt_overview_tab = (LinearLayout) view.findViewById(R.id.lyt_overview_tab);
			lyt_overview_tab.removeAllViews();
			
			TextView txt_player = (TextView) view.findViewById(R.id.player);
			TextView txt_game = (TextView) view.findViewById(R.id.game);
			
/* Text setzen */
			txt_player.setText(player);
			txt_game.setText(game);
						
/* Button definieren */
			
			btn_left.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			i = new Intent(ctxt, SmartStatPlayerStat.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
			btn_up.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			// Vorherigen Spieler ermitteln
		    			// Cursor aller Spieler ermitteln
		    			c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		    			ArrayList<String> player = new ArrayList<String>();
		    			Integer player_active_nr = null;
		    			int counter = 0;
		    			
		    			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		    				
		    				player_team_id = sqlHelper.getPlayerID(c);
		    				
		    				// Überprüfen, ob der Spieler in dem Spiel gespielt hat
		    				if (sqlHelper.count_ticker_activity(game_id, null, player_team_id, null, null, null) > 0) {
		    					
		    					player.add(player_team_id);	
		    					// Falls Spieler identisch mit dem angezeigten Spieler, dann Nummer notieren
			    				if (player_id.equals(player_team_id)) player_active_nr = counter;
		    					// Zählen, wie viele Spieler in dem Spiel aktiv waren
			    				counter = counter + 1;
		    				
		    				}
		    			}
		    			
		    			// ID des vorherigen Spieler ermitteln; falls es der erste Spieler war, ID des letzten Spielers ermitteln
		    			if (player_active_nr.equals(0)) {
		    				player_id = player.get(counter - 1);
		    			} else {
		    				player_id = player.get(player_active_nr - 1);
		    			}
		    			
		    			args.putString("PlayerID", player_id);
		    			
		    			i = new Intent(ctxt, SmartStatPlayerPositionOverview.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
			btn_down.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			// Vorherigen Spieler ermitteln
		    			// Cursor aller Spieler ermitteln
		    			c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		    			ArrayList<String> player = new ArrayList<String>();
		    			Integer player_active_nr = null;
		    			int counter = 0;
		    			
		    			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		    				
		    				player_team_id = sqlHelper.getPlayerID(c);
		    				
		    				// Überprüfen, ob der Spieler in dem Spiel gespielt hat
		    				if (sqlHelper.count_ticker_activity(game_id, null, player_team_id, null, null, null) > 0) {
		    					
		    					player.add(player_team_id);	
		    					// Falls Spieler identisch mit dem angezeigten Spieler, dann Nummer notieren
			    				if (player_id.equals(player_team_id)) player_active_nr = counter;
		    					// Zählen, wie viele Spieler in dem Spiel aktiv waren
			    				counter = counter + 1;
		    				
		    				}
		    			}
		    			
		    			// ID des vorherigen Spieler ermitteln; falls es der erste Spieler war, ID des letzten Spielers ermitteln
		    			if (player_active_nr.equals(counter - 1)) {
		    				player_id = player.get(0);
		    			} else {
		    				player_id = player.get(player_active_nr + 1);
		    			}
		    			
		    			args.putString("PlayerID", player_id);
		    			
		    			i = new Intent(ctxt, SmartStatPlayerPositionOverview.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
		    			
		            }
		      });
			
			btn_right.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {

		    			i = new Intent(ctxt, SmartStatPlayerPositionIndividual.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
		}
		
/* Wurfpositionen */
		
		view.post(new Runnable() {
			
			@Override
			public void run() {
				
/* Button  definieren */
				
				final ImageButton btn_field = (ImageButton) view.findViewById(R.id.btn_field);
				Button btn_goal_uull = (Button) view.findViewById(R.id.goal_uull);
				Button btn_goal_uul = (Button) view.findViewById(R.id.goal_uul);
				Button btn_goal_uum = (Button) view.findViewById(R.id.goal_uum);
				Button btn_goal_uur = (Button) view.findViewById(R.id.goal_uur);
				Button btn_goal_uurr = (Button) view.findViewById(R.id.goal_uurr);
				Button btn_goal_ull = (Button) view.findViewById(R.id.goal_ull);
				Button btn_goal_ul = (Button) view.findViewById(R.id.goal_ul);
				Button btn_goal_um = (Button) view.findViewById(R.id.goal_um);
				Button btn_goal_ur = (Button) view.findViewById(R.id.goal_ur);
				Button btn_goal_urr = (Button) view.findViewById(R.id.goal_urr);
				Button btn_goal_mll = (Button) view.findViewById(R.id.goal_mll);
				Button btn_goal_ml = (Button) view.findViewById(R.id.goal_ml);
				Button btn_goal_mm = (Button) view.findViewById(R.id.goal_mm);
				Button btn_goal_mr = (Button) view.findViewById(R.id.goal_mr);
				Button btn_goal_mrr = (Button) view.findViewById(R.id.goal_mrr);
				Button btn_goal_lll = (Button) view.findViewById(R.id.goal_lll);
				Button btn_goal_ll = (Button) view.findViewById(R.id.goal_ll);
				Button btn_goal_lm = (Button) view.findViewById(R.id.goal_lm);
				Button btn_goal_lr = (Button) view.findViewById(R.id.goal_lr);
				Button btn_goal_lrr = (Button) view.findViewById(R.id.goal_lrr);
				Button total = (Button) view.findViewById(R.id.total);
				Button btn_left = (Button) view.findViewById(R.id.left);
				Button btn_right = (Button) view.findViewById(R.id.right);
				Button btn_up = (Button) view.findViewById(R.id.up);
				Button btn_down = (Button) view.findViewById(R.id.down);
				
/* Cursor erstellen */
				
				String[] statArgs = {game_id, player_id, String.valueOf(goal_id), String.valueOf(goal_7m_id), String.valueOf(goal_fb_id), String.valueOf(miss_id), 
						    String.valueOf(miss_7m_id), String.valueOf(miss_fb_id), String.valueOf(save_id), String.valueOf(save_7m_id), 
						    String.valueOf(save_fb_id), String.valueOf(goal_against_id), String.valueOf(goal_against_7m_id), String.valueOf(goal_against_fb_id)};
				SQLiteDatabase db = sqlHelper.getWritableDatabase();
				c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
							"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ?) ORDER BY time ASC", statArgs);
				
				drawAreaPosition(c, null, null, null);
				
/* Auswahl Wurfecke definieren */
				
				btn_goal_uull.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("uull");
					}
				});
				
				btn_goal_uul.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("uul");
					}
				});
				
				btn_goal_uum.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("uum");
					}
				});
				
				btn_goal_uur.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("uur");
					}
				});
				
				btn_goal_uurr.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("uurr");
					}
				});
				
				btn_goal_ull.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("ull");
					}
				});
				
				btn_goal_ul.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("ul");
					}
				});
				
				btn_goal_um.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("um");
					}
				});
				
				btn_goal_ur.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("ur");
					}
				});
				
				btn_goal_urr.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("urr");
					}
				});
				
				btn_goal_mll.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("mll");
					}
				});
				
				btn_goal_ml.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("ml");
					}
				});
				
				btn_goal_mm.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("mm");
					}
				});
				
				btn_goal_mr.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("mr");
					}
				});
				
				btn_goal_mrr.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("mrr");
					}
				});
				
				btn_goal_lll.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("lll");
					}
				});
				
				btn_goal_ll.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("ll");
					}
				});
				
				btn_goal_lm.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("lm");
					}
				});
				
				btn_goal_lr.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("lr");
					}
				});
				
				btn_goal_lrr.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						buttonAreaClick("lrr");
					}
				});
				
/* Auswahl Spielfeld definieren */
				
				btn_field.setOnTouchListener(new View.OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							
							// Koordinaten des Click Events und Höhe / Breite des Button bestimmen
							Integer btn_width = btn_field.getWidth();
							Integer btn_height = btn_field.getHeight();
							Integer btn_click_x = Math.round(event.getX());
							Integer btn_click_y = Math.round(event.getY());
							
							// Click Event ins Verhältnis zu Breite und Höhe setzen und in die Datenbank schreiben
							x_coord = btn_click_x * 200 / btn_width;
							// Bei den y-Koordinaten den Außenbereich abziehen
							Integer outfield = 10 * btn_height / 130;
							if (btn_click_y <= outfield) btn_click_y = outfield;
							y_coord = (btn_click_y - outfield) * 120 / (btn_height - outfield);
							
							String[] statArgs = {game_id, player_id, String.valueOf(goal_id), String.valueOf(goal_7m_id), String.valueOf(goal_fb_id), String.valueOf(miss_id), 
									    String.valueOf(miss_7m_id), String.valueOf(miss_fb_id), String.valueOf(save_id), String.valueOf(save_7m_id), 
									    String.valueOf(save_fb_id), String.valueOf(goal_against_id), String.valueOf(goal_against_7m_id), String.valueOf(goal_against_fb_id)};
							SQLiteDatabase db = sqlHelper.getWritableDatabase();
							c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
										"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ?) ORDER BY time ASC", statArgs);
							
							// Neues Wurfbild zeichnen
							drawAreaPosition(c, x_coord, y_coord, null);

						}
				            return true;
					}
				});
				
/* Gesamtauswahl definieren */
				
				total.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
								
						String[] statArgs = {game_id, player_id, String.valueOf(goal_id), String.valueOf(goal_7m_id), String.valueOf(goal_fb_id), String.valueOf(miss_id), 
								    String.valueOf(miss_7m_id), String.valueOf(miss_fb_id), String.valueOf(save_id), String.valueOf(save_7m_id), 
								    String.valueOf(save_fb_id), String.valueOf(goal_against_id), String.valueOf(goal_against_7m_id), String.valueOf(goal_against_fb_id)};
						SQLiteDatabase db = sqlHelper.getWritableDatabase();
						c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
									"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ?) ORDER BY time ASC", statArgs);
						
						drawAreaPosition(c, null, null, null);
						
					}
				});
			}
		});
	}

/*
 * 
 * Wurfstatistik - Individuell 
 *
 */
	
	public void lytStatPlayerPositionIndividual(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		
/* Helper definieren */

		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			player_id = args.getString("PlayerID");
			
			// Spieldaten ermitteln
			team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			team_home_short = sqlHelper.getClubShortByTeamID(team_home_id);
			team_away_short = sqlHelper.getClubShortByTeamID(team_away_id);
			
			if (sqlHelper.getPlayerGameHomeOrAway(game_id, player_id).equals(1)) team_id = team_home_id;
			if (sqlHelper.getPlayerGameHomeOrAway(game_id, player_id).equals(0)) team_id = team_away_id;
					
			// Datum einrichten
			int year = 0;
			int day = 0;
			int month = 0;
			Date game_date = null;
			String str_game_date = sqlHelper.getGameDateByID(game_id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				game_date = sdf.parse(str_game_date);
				day = game_date.getDate();    // getDate => Day of month; getDay => day of week
				month = game_date.getMonth();
				year = game_date.getYear()+1900;
			}
			catch (ParseException e) {}
			
			str_game_date = (new StringBuilder()
				.append(day).append(".")
				.append(month + 1).append(".")
				.append(year))
				.toString();
			
			game = str_game_date + " " + team_home_short + " - " + team_away_short;
			
			// Spieldaten ermitteln
			player_number = sqlHelper.getPlayerNumberByID(player_id);
			player_forename = sqlHelper.getPlayerForenameByID(player_id);
			player_surename = sqlHelper.getPlayerSurenameByID(player_id);
			
			player = player_number + " " + player_forename + " " + player_surename;
			
		}
		
/* Activity IDs laden */
		
		activityIDs();

/* Cursorabfrage + Anzahl Cursor ermitteln */
		
		String[] statArgs = {game_id, player_id, String.valueOf(goal_id), String.valueOf(goal_7m_id), String.valueOf(goal_fb_id), String.valueOf(miss_id), 
				    String.valueOf(miss_7m_id), String.valueOf(miss_fb_id), String.valueOf(save_id), String.valueOf(save_7m_id), 
				    String.valueOf(save_fb_id), String.valueOf(goal_against_id), String.valueOf(goal_against_7m_id), String.valueOf(goal_against_fb_id)};
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		Cursor c_count = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ?) ORDER BY time ASC", statArgs);
		
		final int act_count = c_count.getCount() - 1;
		
		c_count.close();
		
/* Layout definieren */
		
		Button btn_left = null, btn_right = null, btn_up = null, btn_down = null, btn_tab_right = null;
		
		if(screenInch > 6) {
		
			btn_tab_right = (Button) view.findViewById(R.id.tab_right);
			
			LinearLayout lyt_overview_smart = (LinearLayout) view.findViewById(R.id.lyt_overview_smart);
			lyt_overview_smart.removeAllViews();
			
			btn_tab_right.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    			
		    			TabFragStatPlayerOverview fragOverview = new TabFragStatPlayerOverview();
		    			fragOverview.setArguments(args);
		    			fragmentTransaction.replace(R.id.frag_stat_player_content_1, fragOverview);
		    			
		    			TabFragStatPlayerStat fragIndividual = new TabFragStatPlayerStat();
		    			fragIndividual.setArguments(args);
		    			fragmentTransaction.replace(R.id.frag_stat_player_content_2, fragIndividual);
		    			fragmentTransaction.commit();

		            }
		      });
			
		} else {
			
			btn_left = (Button) view.findViewById(R.id.left);
			btn_right = (Button) view.findViewById(R.id.right);
			btn_up = (Button) view.findViewById(R.id.up);
			btn_down = (Button) view.findViewById(R.id.down);
			
			LinearLayout lyt_overview_tab = (LinearLayout) view.findViewById(R.id.lyt_overview_tab);
			lyt_overview_tab.removeAllViews();
			
			TextView txt_player = (TextView) view.findViewById(R.id.player);
			TextView txt_game = (TextView) view.findViewById(R.id.game);
			
/* Text setzen */
			txt_player.setText(player);
			txt_game.setText(game);
						
/* Button definieren */
			
			btn_left.setOnClickListener(new View.OnClickListener() {
		    		
		    		@Override
		    		public void onClick(View v) {
		    			
		    			i = new Intent(ctxt, SmartStatPlayerPositionOverview.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
			btn_up.setOnClickListener(new View.OnClickListener() {
				
		    		@Override
		    		public void onClick(View v) {
		    			
		    			// Vorherigen Spieler ermitteln
		    			// Cursor aller Spieler ermitteln
		    			c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		    			ArrayList<String> player = new ArrayList<String>();
		    			Integer player_active_nr = null;
		    			int counter = 0;
		    			
		    			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		    				
		    				player_team_id = sqlHelper.getPlayerID(c);
		    				
		    				// Überprüfen, ob der Spieler in dem Spiel gespielt hat
		    				if (sqlHelper.count_ticker_activity(game_id, null, player_team_id, null, null, null) > 0) {
		    					
		    					player.add(player_team_id);	
		    					// Falls Spieler identisch mit dem angezeigten Spieler, dann Nummer notieren
			    				if (player_id.equals(player_team_id)) player_active_nr = counter;
		    					// Zählen, wie viele Spieler in dem Spiel aktiv waren
			    				counter = counter + 1;
		    				
		    				}
		    			}
		    			
		    			// ID des vorherigen Spieler ermitteln; falls es der erste Spieler war, ID des letzten Spielers ermitteln
		    			if (player_active_nr.equals(0)) {
		    				player_id = player.get(counter - 1);
		    			} else {
		    				player_id = player.get(player_active_nr - 1);
		    			}
		    			
		    			args.putString("PlayerID", player_id);
		    			
		    			i = new Intent(ctxt, SmartStatPlayerPositionIndividual.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
			
			btn_down.setOnClickListener(new View.OnClickListener() {
				
		    		@Override
		    		public void onClick(View v) {
		    			
		    			// Vorherigen Spieler ermitteln
		    			// Cursor aller Spieler ermitteln
		    			c = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		    			ArrayList<String> player = new ArrayList<String>();
		    			Integer player_active_nr = null;
		    			int counter = 0;
		    			
		    			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		    				
		    				player_team_id = sqlHelper.getPlayerID(c);
		    				
		    				// Überprüfen, ob der Spieler in dem Spiel gespielt hat
		    				if (sqlHelper.count_ticker_activity(game_id, null, player_team_id, null, null, null) > 0) {
		    					
		    					player.add(player_team_id);	
		    					// Falls Spieler identisch mit dem angezeigten Spieler, dann Nummer notieren
			    				if (player_id.equals(player_team_id)) player_active_nr = counter;
		    					// Zählen, wie viele Spieler in dem Spiel aktiv waren
			    				counter = counter + 1;
		    				
		    				}
		    			}
		    			
		    			// ID des vorherigen Spieler ermitteln; falls es der erste Spieler war, ID des letzten Spielers ermitteln
		    			if (player_active_nr.equals(counter - 1)) {
		    				player_id = player.get(0);
		    			} else {
		    				player_id = player.get(player_active_nr + 1);
		    			}
		    			
		    			args.putString("PlayerID", player_id);
		    			
		    			i = new Intent(ctxt, SmartStatPlayerPositionIndividual.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
		    			
		            }
		      });
			
			btn_right.setOnClickListener(new View.OnClickListener() {
				
		    		@Override
		    		public void onClick(View v) {

		    			i = new Intent(ctxt, SmartStatPlayerFirst.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);

		            }
		      });
		}
		
/* Wurfpositionen */
		
		view.post(new Runnable() {
			
			@Override
			public void run() {
				
/* Seekbar definieren */
				
				SeekBar position_control = (SeekBar) view.findViewById(R.id.volume_bar);
				position_control.setMax(act_count);
				
				// Zu Beginn den ersten Wurf abfragen
				String[] statArgs = {game_id, player_id, String.valueOf(goal_id), String.valueOf(goal_7m_id), String.valueOf(goal_fb_id), String.valueOf(miss_id), 
						    String.valueOf(miss_7m_id), String.valueOf(miss_fb_id), String.valueOf(save_id), String.valueOf(save_7m_id), 
						    String.valueOf(save_fb_id), String.valueOf(goal_against_id), String.valueOf(goal_against_7m_id), String.valueOf(goal_against_fb_id)};
				SQLiteDatabase db = sqlHelper.getWritableDatabase();
				c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
							"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ?) ORDER BY time ASC", statArgs);
				
				c.moveToFirst();
				// Ticker ID ermitteln
				String ticker_id = sqlHelper.getTickerActivityID(c);
				
				// Zeit und Aktivität der Tickerposition ermitteln
				final TextView txt_time = (TextView) view.findViewById(R.id.time);
				final TextView txt_activity = (TextView) view.findViewById(R.id.activity);
				txt_activity.setText(sqlHelper.getTickerActivityStringByID(ticker_id));
				time = sqlHelper.getTickerTimeByActivityID(ticker_id);
				txt_time.setText(fctHelper.updateTimer(time) + " Min.");
				
				// Ermittle die Position des Wurfes
				x_coord = sqlHelper.getTickerFieldPositionXByID(ticker_id);
				y_coord = sqlHelper.getTickerFieldPositionYByID(ticker_id);

				drawAreaPosition(c, x_coord, y_coord, 0);
				
/* Seekbar einrichten */

				position_control.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					
					int progressChanged = 0;

					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						
						// Used to notify that the progress level has changed.
						progressChanged = progress;
						
						String[] statArgs = {game_id, player_id, String.valueOf(goal_id), String.valueOf(goal_7m_id), String.valueOf(goal_fb_id), String.valueOf(miss_id), 
								    String.valueOf(miss_7m_id), String.valueOf(miss_fb_id), String.valueOf(save_id), String.valueOf(save_7m_id), 
								    String.valueOf(save_fb_id), String.valueOf(goal_against_id), String.valueOf(goal_against_7m_id), String.valueOf(goal_against_fb_id)};
						SQLiteDatabase db = sqlHelper.getWritableDatabase();
						c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
									"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ?) ORDER BY time ASC", statArgs);
						
						// Ermittle die Position des Wurfes
						c.moveToPosition(progressChanged);
						String ticker_id = sqlHelper.getTickerActivityID(c);
						
						// Text setzen
						txt_activity.setText(sqlHelper.getTickerActivityStringByID(ticker_id));
						time = sqlHelper.getTickerTimeByActivityID(ticker_id);
						txt_time.setText(fctHelper.updateTimer(time) + " Min.");
						
						// Koordinaten auf dem Spielfeld setzen
						x_coord = sqlHelper.getTickerFieldPositionXByID(ticker_id);
						y_coord = sqlHelper.getTickerFieldPositionYByID(ticker_id);
						drawAreaPosition(c, x_coord, y_coord, progressChanged);
						
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
						// Used to notify that the user has started a touch gesture.
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
						// 
					}
				});
			}
		});
	}
	
/*
 * 
 * Wurfstatistik - Datenbankabfrage für Wurfecke 
 *
 */

	public void buttonAreaClick(String area) {
		
		String[] statArgs = {area, game_id, player_id, String.valueOf(goal_id), String.valueOf(goal_7m_id), String.valueOf(goal_fb_id), String.valueOf(miss_id), 
				    String.valueOf(miss_7m_id), String.valueOf(miss_fb_id), String.valueOf(save_id), String.valueOf(save_7m_id), 
				    String.valueOf(save_fb_id), String.valueOf(goal_against_id), String.valueOf(goal_against_7m_id), String.valueOf(goal_against_fb_id)};
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		c = db.rawQuery("SELECT * FROM ticker_activity WHERE goal_area = ? AND game_id = ? AND player_id = ? AND (activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ?) ORDER BY time ASC", statArgs);
		
		drawAreaPosition(c, null, null, null);
		
	}
	
/*
 * 
 * Wurfstatistik - Wurfecke und -position zeichnen 
 *
 */
	
	public void drawAreaPosition(Cursor c, Integer x_click, Integer y_click, Integer position_controll) {
		
/* Button  definieren */
		
		ImageButton btn_field = (ImageButton) view.findViewById(R.id.btn_field);
		Button btn_goal_uull = (Button) view.findViewById(R.id.goal_uull);
		Button btn_goal_uul = (Button) view.findViewById(R.id.goal_uul);
		Button btn_goal_uum = (Button) view.findViewById(R.id.goal_uum);
		Button btn_goal_uur = (Button) view.findViewById(R.id.goal_uur);
		Button btn_goal_uurr = (Button) view.findViewById(R.id.goal_uurr);
		Button btn_goal_ull = (Button) view.findViewById(R.id.goal_ull);
		Button btn_goal_ul = (Button) view.findViewById(R.id.goal_ul);
		Button btn_goal_um = (Button) view.findViewById(R.id.goal_um);
		Button btn_goal_ur = (Button) view.findViewById(R.id.goal_ur);
		Button btn_goal_urr = (Button) view.findViewById(R.id.goal_urr);
		Button btn_goal_mll = (Button) view.findViewById(R.id.goal_mll);
		Button btn_goal_ml = (Button) view.findViewById(R.id.goal_ml);
		Button btn_goal_mm = (Button) view.findViewById(R.id.goal_mm);
		Button btn_goal_mr = (Button) view.findViewById(R.id.goal_mr);
		Button btn_goal_mrr = (Button) view.findViewById(R.id.goal_mrr);
		Button btn_goal_lll = (Button) view.findViewById(R.id.goal_lll);
		Button btn_goal_ll = (Button) view.findViewById(R.id.goal_ll);
		Button btn_goal_lm = (Button) view.findViewById(R.id.goal_lm);
		Button btn_goal_lr = (Button) view.findViewById(R.id.goal_lr);
		Button btn_goal_lrr = (Button) view.findViewById(R.id.goal_lrr);
		
/* Variablen definieren */
		
		int btn_width = btn_field.getWidth();
		int btn_height = btn_field.getHeight();
		int outfield = 10 * btn_height / 130; 
		int rect_width = btn_width / 15;
		int rect_height = (btn_height-outfield) / 10;
		int coord_left = 0;
		int coord_right = 0;
		int coord_top = 0;
		int coord_bottom = 0;
		
		String ticker_id = null;
		Integer ticker_activity_id = null;
		String position_text = null;
		String goal_area = null;
		
		Integer x_coord_original = 0;
		Integer y_coord_original = 0;
		Integer x_coord_matrix = 0;
		Integer y_coord_matrix = 0;
		Integer x_matrix = 0;
		Integer y_matrix = 0;
		Integer x_area = null;
		Integer y_area = null;
		int position = 0;
		
		int plus = 0;
		int minus = 0;
		int attempts = 0;
		int percent = 0;
		int text_x = 0;
		int text_y = 0;
		int text_size = 0;
		int circle_radius = 0;
		boolean goalkeeper = false;
		Button btn = null;
		int bckgrnd = 0;
		final int sdk = android.os.Build.VERSION.SDK_INT;
		double click_distance = 0;
		int max_distance = 50;
		int position_counter = 0;
		boolean bool_distance = false;
		if (x_click != null && y_click != null) bool_distance = true;
		
		int click_position_goal = 0;
		int click_position_goal_7m = 0;
		int click_position_goal_fb = 0;
		int click_position_miss = 0;
		int click_position_miss_7m = 0;
		int click_position_miss_fb = 0;
		int click_position_save = 0;
		int click_position_save_7m = 0;
		int click_position_save_fb = 0;
		int click_position_goal_against = 0;
		int click_position_goal_against_7m = 0;
		int click_position_goal_against_fb = 0;
		
		Integer[][] field_goal_array = new Integer[15][10];
		Integer[][] field_goal_7m_array = new Integer[15][10];
		Integer[][] field_goal_fb_array = new Integer[15][10];
		Integer[][] field_miss_array = new Integer[15][10];
		Integer[][] field_miss_7m_array = new Integer[15][10];
		Integer[][] field_miss_fb_array = new Integer[15][10];
		Integer[][] field_save_array = new Integer[15][10];
		Integer[][] field_save_7m_array = new Integer[15][10];
		Integer[][] field_save_fb_array = new Integer[15][10];
		Integer[][] field_goal_against_array = new Integer[15][10];
		Integer[][] field_goal_against_7m_array = new Integer[15][10];
		Integer[][] field_goal_against_fb_array = new Integer[15][10];
		
		Integer[][] area_goal_array = new Integer[5][4];
		Integer[][] area_goal_7m_array = new Integer[5][4];
		Integer[][] area_goal_fb_array = new Integer[5][4];
		Integer[][] area_miss_array = new Integer[5][4];
		Integer[][] area_miss_7m_array = new Integer[5][4];
		Integer[][] area_miss_fb_array = new Integer[5][4];
		Integer[][] area_save_array = new Integer[5][4];
		Integer[][] area_save_7m_array = new Integer[5][4];
		Integer[][] area_save_fb_array = new Integer[5][4];
		Integer[][] area_goal_against_array = new Integer[5][4];
		Integer[][] area_goal_against_7m_array = new Integer[5][4];
		Integer[][] area_goal_against_fb_array = new Integer[5][4];
		
		Integer[] position_goal_array = new Integer[8];
		Integer[] position_goal_7m_array = new Integer[8];
		Integer[] position_goal_fb_array = new Integer[8];
		Integer[] position_miss_array = new Integer[8];
		Integer[] position_miss_7m_array = new Integer[8];
		Integer[] position_miss_fb_array = new Integer[8];
		Integer[] position_save_array = new Integer[8];
		Integer[] position_save_7m_array = new Integer[8];
		Integer[] position_save_fb_array = new Integer[8];
		Integer[] position_goal_against_array = new Integer[8];
		Integer[] position_goal_against_7m_array = new Integer[8];
		Integer[] position_goal_against_fb_array = new Integer[8];
		
		Integer[] position_x = new Integer[8];
		Integer[] position_y = new Integer[8];
		position_x[0] = 20;
		position_x[1] = 34;
		position_x[2] = 54;
		position_x[3] = 100;
		position_x[4] = 100;
		position_x[5] = 164;
		position_x[6] = 144;
		position_x[7] = 180;
		
		position_y[0] = 30;
		position_y[1] = 94;
		position_y[2] = 69;
		position_y[3] = 99;
		position_y[4] = 69;
		position_y[5] = 94;
		position_y[6] = 69;
		position_y[7] = 30;
		
		for (int x = 0; x <= 14; x++) {
			for (int y = 0; y <= 9; y++) {
				
				field_goal_array[x][y] = 0; 
				field_goal_7m_array[x][y] = 0; 
				field_goal_fb_array[x][y] = 0; 
				field_miss_array[x][y] = 0; 
				field_miss_7m_array[x][y] = 0; 
				field_miss_fb_array[x][y] = 0; 
				field_save_array[x][y] = 0; 
				field_save_7m_array[x][y] = 0; 
				field_save_fb_array[x][y] = 0; 
				field_goal_against_array[x][y] = 0; 
				field_goal_against_7m_array[x][y] = 0; 
				field_goal_against_fb_array[x][y] = 0; 
				
			}
		}
		
		for (int x = 0; x <= 4; x++) {
			for (int y = 0; y <= 3; y++) {
				
				area_goal_array[x][y] = 0; 
				area_goal_7m_array[x][y] = 0; 
				area_goal_fb_array[x][y] = 0; 
				area_miss_array[x][y] = 0; 
				area_miss_7m_array[x][y] = 0; 
				area_miss_fb_array[x][y] = 0; 
				area_save_array[x][y] = 0; 
				area_save_7m_array[x][y] = 0; 
				area_save_fb_array[x][y] = 0; 
				area_goal_against_array[x][y] = 0; 
				area_goal_against_7m_array[x][y] = 0; 
				area_goal_against_fb_array[x][y] = 0; 
				
			}
		}
		
		for (int x = 0; x <= 7; x++) {
			
			position_goal_array[x] = 0;
			position_goal_7m_array[x] = 0;
			position_goal_fb_array[x] = 0;
			position_miss_array[x] = 0;
			position_miss_7m_array[x] = 0;
			position_miss_fb_array[x] = 0;
			position_save_array[x] = 0;
			position_save_7m_array[x] = 0;
			position_save_fb_array[x] = 0;
			position_goal_against_array[x] = 0;
			position_goal_against_7m_array[x] = 0;
			position_goal_against_fb_array[x] = 0;
			
		}
		
/* Alle Tickermeldungen abfragen und Wurfecke / -position berechnen */
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			// Abfrage, ob nur eine bestimmter Wurf abgefragt werden soll oder alle Würfe abgefragt werden sollen
			if (position_controll == null || position_controll == position_counter) {
				
				ticker_id = sqlHelper.getTickerActivityID(c);
				ticker_activity_id = sqlHelper.getTickerActivityIDByID(ticker_id);
				
				// Falls Tor- oder Fehlwurf...
				if (ticker_activity_id.equals(goal_id) || ticker_activity_id.equals(goal_7m_id) ||	ticker_activity_id.equals(goal_fb_id) || 
						ticker_activity_id.equals(miss_id) || ticker_activity_id.equals(miss_7m_id) || ticker_activity_id.equals(miss_fb_id)) {
					
/* Statistiken Wurfposition berechnen */
					
					// Koordinaten abfragen
					
					x_coord_original = sqlHelper.getTickerFieldPositionXByID(ticker_id);
					y_coord_original = sqlHelper.getTickerFieldPositionYByID(ticker_id);

					if (x_coord_original != null && y_coord_original != null) {
						
						// Wurf auf dem Spielfeld zuordnen
						// Abfrage, ob eine bestimmte Spielposition, oder ob alle Positionen abgefragt werden sollen 
						if (bool_distance == false) {			// Wenn kein fester Punkt angegeben wurde...
						
							// ... alle Positionen in die Auswertung einbeziehen
							x_coord_matrix = (x_coord_original * 3 / 40) + 1;
							y_coord_matrix = (y_coord_original / 12) + 1;
							if (x_coord_matrix > 15) x_coord_matrix = 15;
							if (y_coord_matrix > 10) y_coord_matrix = 10;

							// Umliegende Felder bearbeiten
							for (int x_counter = -2; x_counter <= 2; x_counter++) {
								for (int y_counter = -2; y_counter <= 2; y_counter++) {
								
									x_matrix = x_coord_matrix + x_counter;
									y_matrix = y_coord_matrix + y_counter;
								
									if (x_matrix >= 1 && x_matrix <=15 && 
											y_matrix >= 1 && y_matrix <=10) {
									
										if (ticker_activity_id.equals(goal_id)) field_goal_array[x_matrix - 1][y_matrix - 1] = field_goal_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(goal_7m_id)) field_goal_7m_array[x_matrix - 1][y_matrix - 1] = field_goal_7m_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(goal_fb_id)) field_goal_fb_array[x_matrix - 1][y_matrix - 1] = field_goal_fb_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(miss_id)) field_miss_array[x_matrix - 1][y_matrix - 1] = field_miss_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(miss_7m_id)) field_miss_7m_array[x_matrix - 1][y_matrix - 1] = field_miss_7m_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(miss_fb_id)) field_miss_fb_array[x_matrix - 1][y_matrix - 1] = field_miss_fb_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(save_id)) field_save_array[x_matrix - 1][y_matrix - 1] = field_save_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(save_7m_id)) field_save_7m_array[x_matrix - 1][y_matrix - 1] = field_save_7m_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(save_fb_id)) field_save_fb_array[x_matrix - 1][y_matrix - 1] = field_save_fb_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(goal_against_id)) field_goal_against_array[x_matrix - 1][y_matrix - 1] = field_goal_against_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(goal_against_7m_id)) field_goal_against_7m_array[x_matrix - 1][y_matrix - 1] = field_goal_against_7m_array[x_matrix - 1][y_matrix - 1] + 1;
										if (ticker_activity_id.equals(goal_against_fb_id)) field_goal_against_fb_array[x_matrix - 1][y_matrix - 1] = field_goal_against_fb_array[x_matrix - 1][y_matrix - 1] + 1;

									}
								}
							}
						
							// ... Wurf einer Spielerposition zuordnen
							position = getBestPosition(x_coord_original, y_coord_original);
							if (ticker_activity_id.equals(goal_id)) position_goal_array[position] = position_goal_array[position] + 1;
							if (ticker_activity_id.equals(goal_7m_id)) position_goal_7m_array[position] = position_goal_7m_array[position] + 1;
							if (ticker_activity_id.equals(goal_fb_id)) position_goal_fb_array[position] = position_goal_fb_array[position] + 1;
							if (ticker_activity_id.equals(miss_id)) position_miss_array[position] = position_miss_array[position] + 1;
							if (ticker_activity_id.equals(miss_7m_id)) position_miss_7m_array[position] = position_miss_7m_array[position] + 1;
							if (ticker_activity_id.equals(miss_fb_id)) position_miss_fb_array[position] = position_miss_fb_array[position] + 1;
							if (ticker_activity_id.equals(save_id)) position_save_array[position] = position_save_array[position] + 1;
							if (ticker_activity_id.equals(save_7m_id)) position_save_7m_array[position] = position_save_7m_array[position] + 1;
							if (ticker_activity_id.equals(save_fb_id)) position_save_fb_array[position] = position_save_fb_array[position] + 1;
							if (ticker_activity_id.equals(goal_against_id)) position_goal_against_array[position] = position_goal_against_array[position] + 1;
							if (ticker_activity_id.equals(goal_against_7m_id)) position_goal_against_7m_array[position] = position_goal_against_7m_array[position] + 1;
							if (ticker_activity_id.equals(goal_against_fb_id)) position_goal_against_fb_array[position] = position_goal_against_fb_array[position] + 1;

						} else {						// Wenn eine konkrete Position angegeben wurde
						
							// Nur die Positionen im Umkreis einbeziehen
							click_distance = Math.sqrt(Math.pow(x_coord_original - x_click, 2) +
		     						    					Math.pow(y_coord_original - y_click, 2));
						
							if (click_distance <= max_distance) {
							
								if (ticker_activity_id.equals(goal_id)) click_position_goal = click_position_goal + 1;
								if (ticker_activity_id.equals(goal_7m_id)) click_position_goal_7m = click_position_goal_7m + 1;
								if (ticker_activity_id.equals(goal_fb_id)) click_position_goal_fb = click_position_goal_fb + 1;
								if (ticker_activity_id.equals(miss_id)) click_position_miss = click_position_miss + 1;
								if (ticker_activity_id.equals(miss_7m_id)) click_position_miss_7m = click_position_miss_7m + 1;
								if (ticker_activity_id.equals(miss_fb_id)) click_position_miss_fb = click_position_miss_fb + 1;
								if (ticker_activity_id.equals(save_id)) click_position_save = click_position_save + 1;
								if (ticker_activity_id.equals(save_7m_id)) click_position_save_7m = click_position_save_7m + 1;
								if (ticker_activity_id.equals(save_fb_id)) click_position_save_fb = click_position_save_fb + 1;
								if (ticker_activity_id.equals(goal_against_id)) click_position_goal_against = click_position_goal_against + 1;
								if (ticker_activity_id.equals(goal_against_7m_id)) click_position_goal_against_7m = click_position_goal_against_7m + 1;
							
							}
						}
					}

/* Statistiken Wurfecke berechnen */
					
					goal_area = sqlHelper.getTickerAreaByID(ticker_id);
					
					if (goal_area != null) {
						
						x_area = null;
						y_area = null;
						if (goal_area.equals("uull")) {
							x_area = 0; 
							y_area = 0;
						}
						if (goal_area.equals("uul")) {
							x_area = 1; 
							y_area = 0;
						}
						if (goal_area.equals("uum")) {
							x_area = 2; 
							y_area = 0;
						}
						if (goal_area.equals("uur")) {
							x_area = 3; 
							y_area = 0;
						}
						if (goal_area.equals("uurr")) {
							x_area = 4; 
							y_area = 0;
						}
						if (goal_area.equals("ull")) {
							x_area = 0; 
							y_area = 1;
						}
						if (goal_area.equals("ul")) {
							x_area = 1;
							y_area = 1;
						}
						if (goal_area.equals("um")) {
							x_area = 2; 
							y_area = 1;
						}
						if (goal_area.equals("ur")) {
							x_area = 3; 
							y_area = 1;
						}
						if (goal_area.equals("urr")) {
							x_area = 4; 
							y_area = 1;
						}
						if (goal_area.equals("mll")) {
							x_area = 0; 
							y_area = 2;
						}
						if (goal_area.equals("ml")) {
							x_area = 1; 
							y_area = 2;
						}
						if (goal_area.equals("mm")) {
							x_area = 2; 
							y_area = 2;
						}
						if (goal_area.equals("mr")) {
							x_area = 3; 
							y_area = 2;
						}
						if (goal_area.equals("mrr")) {
							x_area = 4; 
							y_area = 2;
						}
						if (goal_area.equals("lll")) {
							x_area = 0; 
							y_area = 3;
						}
						if (goal_area.equals("ll")) {
							x_area = 1; 
							y_area = 3;
						}
						if (goal_area.equals("lm")) {
							x_area = 2; 
							y_area = 3;
						}
						if (goal_area.equals("lr")) {
							x_area = 3;
							y_area = 3;
						}
						if (goal_area.equals("lrr")) {
							x_area = 4; 
							y_area = 3;
						}
						
						if (x_area != null && y_area != null) {
							
							if ((bool_distance == true && click_distance <= max_distance) ||
								bool_distance == false) {
								
								if (ticker_activity_id.equals(goal_id)) area_goal_array[x_area][y_area] = area_goal_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(goal_7m_id)) area_goal_7m_array[x_area][y_area] = area_goal_7m_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(goal_fb_id)) area_goal_fb_array[x_area][y_area] = area_goal_fb_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(miss_id)) area_miss_array[x_area][y_area] = area_miss_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(miss_7m_id)) area_miss_7m_array[x_area][y_area] = area_miss_7m_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(miss_fb_id)) area_miss_fb_array[x_area][y_area] = area_miss_fb_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(save_id)) area_save_array[x_area][y_area] = area_save_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(save_7m_id)) area_save_7m_array[x_area][y_area] = area_save_7m_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(save_fb_id)) area_save_fb_array[x_area][y_area] = area_save_fb_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(goal_against_id)) area_goal_against_array[x_area][y_area] = area_goal_against_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(goal_against_7m_id)) area_goal_against_7m_array[x_area][y_area] = area_goal_against_7m_array[x_area][y_area] + 1;
								if (ticker_activity_id.equals(goal_against_fb_id)) area_goal_against_fb_array[x_area][y_area] = area_goal_against_fb_array[x_area][y_area] + 1;
								
							} 
						}
					}
				}
			}

			position_counter = position_counter + 1;
		}
		
		c.close();
		
/* Felder Wurfecke einrichten */

		if (sqlHelper.getPlayerPositionFirstByID(player_id).substring(2,4).equals("01")) goalkeeper = true;
		text_size = btn_goal_uull.getWidth() / 21;
		
		// Wurfecke kennzeichnen und beschriften
		for (Integer x = 0; x <= 4; x++) {
			for (Integer y = 0; y <= 3; y++) {
				
				if (x.equals(0) && y.equals(0)) {
					btn = btn_goal_uull; 
					bckgrnd = R.drawable.goal_uull;
				}
				if (x.equals(1) && y.equals(0)) {
					btn = btn_goal_uul; 
					bckgrnd = R.drawable.goal_uul;
				}
				if (x.equals(2) && y.equals(0)) {
					btn = btn_goal_uum; 
					bckgrnd = R.drawable.goal_uum;
				}
				if (x.equals(3) && y.equals(0)) {
					btn = btn_goal_uur; 
					bckgrnd = R.drawable.goal_uur;
				}
				if (x.equals(4) && y.equals(0)) {
					btn = btn_goal_uurr; 
					bckgrnd = R.drawable.goal_uurr;
				}
				if (x.equals(0) && y.equals(1)) {
					btn = btn_goal_ull; 
					bckgrnd = R.drawable.goal_ull;
				}
				if (x.equals(1) && y.equals(1)) {
					btn = btn_goal_ul; 
					bckgrnd = R.drawable.goal_ul;
				}
				if (x.equals(2) && y.equals(1)) {
					btn = btn_goal_um; 
					bckgrnd = R.drawable.goal_um;
				}
				if (x.equals(3) && y.equals(1)) {
					btn = btn_goal_ur; 
					bckgrnd = R.drawable.goal_ur;
				}
				if (x.equals(4) && y.equals(1)) {
					btn = btn_goal_urr; 
					bckgrnd = R.drawable.goal_urr;
				}
				if (x.equals(0) && y.equals(2)) {
					btn = btn_goal_mll; 
					bckgrnd = R.drawable.goal_mll;
				}
				if (x.equals(1) && y.equals(2)) {
					btn = btn_goal_ml; 
					bckgrnd = R.drawable.goal_ml;
				}
				if (x.equals(2) && y.equals(2)) {
					btn = btn_goal_mm; 
					bckgrnd = R.drawable.goal_mm;
				}
				if (x.equals(3) && y.equals(2)) {
					btn = btn_goal_mr; 
					bckgrnd = R.drawable.goal_mr;
				}
				if (x.equals(4) && y.equals(2)) {
					btn = btn_goal_mrr; 
					bckgrnd = R.drawable.goal_mrr;
				}
				if (x.equals(0) && y.equals(3)) {
					btn = btn_goal_lll; 
					bckgrnd = R.drawable.goal_lll;
				}
				if (x.equals(1) && y.equals(3)) {
					btn = btn_goal_ll; 
					bckgrnd = R.drawable.goal_ll;
				}
				if (x.equals(2) && y.equals(3)) {
					btn = btn_goal_lm; 
					bckgrnd = R.drawable.goal_lm;
				}
				if (x.equals(3) && y.equals(3)) {
					btn = btn_goal_lr; 
					bckgrnd = R.drawable.goal_lr;
				}
				if (x.equals(4) && y.equals(3)) {
					btn = btn_goal_lrr; 
					bckgrnd = R.drawable.goal_lrr;
				}
				
				if (goalkeeper == true) {

					plus = area_save_array[x][y] + area_save_7m_array[x][y] + area_save_fb_array[x][y];
					minus = area_goal_against_array[x][y] + area_goal_against_7m_array[x][y] + area_goal_against_fb_array[x][y];
					
				} else {
					
					plus = area_goal_array[x][y] + area_goal_7m_array[x][y] + area_goal_fb_array[x][y];
					minus = area_miss_array[x][y] + area_miss_7m_array[x][y] + area_miss_fb_array[x][y];
						
				}
				
				attempts = plus + minus;
				if (attempts > 0) percent = plus * 100 / attempts;
				
				if (attempts > 0) {
					
					if (percent <=33) btn.setBackgroundColor(res.getColor(R.color.red));
			  		if (percent > 33 && percent < 66) btn.setBackgroundColor(res.getColor(R.color.yellow));
			  		if (percent >=66) btn.setBackgroundColor(res.getColor(R.color.green));
					
			  		position_text = String.valueOf(plus) + " / " + String.valueOf(attempts) + "\n" + String.valueOf(percent) + " %";
			  		btn.setTextSize(text_size);
			  		btn.setText(position_text);
					
				} else {

					btn.setText("");
					if (bckgrnd != 0) {
						if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
							btn.setBackgroundDrawable(res.getDrawable(bckgrnd) );
						} else {
							btn.setBackground(res.getDrawable(bckgrnd));
						}
					}
				}
			}
		}

/* Spiefeld mit Matrix kennzeichnen */
		
		// Grafik einrichten
  		Bitmap bitMap = Bitmap.createBitmap(btn_width, btn_height, Bitmap.Config.ARGB_8888);
  		bitMap = bitMap.copy(bitMap.getConfig(), true);
  		Canvas canvas = new Canvas(bitMap);
  		text_size = btn_width / 20;
  		circle_radius = max_distance * btn_width / 400;
  		
  		if (bool_distance == false) {
  			
  			for (int x_counter = 1; x_counter <= 15; x_counter++) {
  				for (int y_counter = 1; y_counter <= 10; y_counter++) {
  					
  					plus = 0;
  					minus = 0;
  					attempts = 0;
  					percent = 0;
  					
  					if (goalkeeper == true) {
  						
  						plus = field_save_array[x_counter -1][y_counter -1] + field_save_7m_array[x_counter -1][y_counter -1] + field_save_fb_array[x_counter -1][y_counter -1];
  						minus = field_goal_against_array[x_counter -1][y_counter -1] + field_goal_against_7m_array[x_counter -1][y_counter -1] + field_goal_against_fb_array[x_counter -1][y_counter -1];
  						attempts = plus + minus;
  						if (attempts > 0) percent = plus * 100 / attempts;
  						
  					} else {
  						
  						plus = field_goal_array[x_counter -1][y_counter -1] + field_goal_7m_array[x_counter -1][y_counter -1] + field_goal_fb_array[x_counter -1][y_counter -1];
  						minus = field_miss_array[x_counter -1][y_counter -1] + field_miss_7m_array[x_counter -1][y_counter -1] + field_miss_fb_array[x_counter -1][y_counter -1];
  						attempts = plus + minus;
  						if (attempts > 0) percent = plus * 100 / attempts;
  						
  					}
  					
  					// Layout definieren
  			  		Paint paint = new Paint();
  			  		paint.setAntiAlias(true);
  			  		// Farbe je nach Erfolgsquote
  			  		if (attempts > 0)  {
  			  			if (percent <=33) paint.setColor(res.getColor(R.color.red));
  			  			if (percent > 33 && percent < 66) paint.setColor(res.getColor(R.color.yellow));
  			  			if (percent >=66) paint.setColor(res.getColor(R.color.green));
  			  		}
  			  		
  			  		// Box zeichnen
  			  		if (attempts > 0) {
  			  			
  			  			coord_left = (x_counter - 1) * rect_width;
  			  			coord_right = x_counter * rect_width;
  			  			coord_top = outfield + ((y_counter - 1) * rect_height);
  			  			coord_bottom = outfield + (y_counter * rect_height);
  			  			canvas.drawRect(coord_left, coord_top, coord_right, coord_bottom, paint);
  			  			
  			  		}						
  				}
  			}
  			
  			// Wurffelder setzen
  			btn_field.setImageBitmap(bitMap);
  			
/* Spielfeldgrafik über die Wurffelder setzen */
  			
  			Bitmap BitmapOrg = BitmapFactory.decodeResource(ctxt.getResources(), R.drawable.field);

  			int width = BitmapOrg.getWidth();
  			int height = BitmapOrg.getHeight();

  			// calculate the scale
  			float scaleWidth = ((float) btn_width) / width;
  			float scaleHeight = ((float) btn_height) / height;

  			// create a matrix for the manipulation
  			Matrix matrix = new Matrix();
  			// resize the Bitmap
  			matrix.postScale(scaleWidth, scaleHeight);

  			// recreate the new Bitmap
  			Bitmap bm = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);
  			Paint paint = new Paint();   
  			canvas.drawBitmap(bm, 0, 0, null);
  			
  			paint.setColor(res.getColor(R.color.grey_dark));
  			paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
  			paint.setTextSize(text_size);
  			paint.setTextAlign(Align.CENTER);
  			
/* Wurfpositionen beschriften */
  			
  			for (int x = 0; x <= 7; x++) {
  				
  				if (goalkeeper == true) {
  					
  					plus = position_save_array[x] + position_save_7m_array[x] + position_save_fb_array[x];
  					minus = position_goal_against_array[x] + position_goal_against_7m_array[x] + position_goal_against_fb_array[x];
  					attempts = plus + minus;
  					if (attempts > 0) percent = plus * 100 / attempts;
  					
  				} else {
  					
  					plus = position_goal_array[x] + position_goal_7m_array[x] + position_goal_fb_array[x];
  					minus = position_miss_array[x] + position_miss_7m_array[x] + position_miss_fb_array[x];
  					attempts = plus + minus;
  					if (attempts > 0) percent = plus * 100 / attempts;
  					
  				}
  				
  				if (attempts > 0) {
  					
  					position_text = String.valueOf(plus) + " / " + String.valueOf(attempts);
  					text_x = position_x[x] * btn_width / 200;
  					text_y = position_y[x] * btn_height / 120;
  					canvas.drawText(position_text, text_x, text_y, paint);
  					position_text = String.valueOf(percent) + " %";
  					canvas.drawText(position_text, text_x, text_y + text_size, paint);
  					
  				}
  			}
  		} else {
  			
/* Spielfeldgrafik über die Wurffelder setzen */
  			
  			Bitmap BitmapOrg = BitmapFactory.decodeResource(ctxt.getResources(), R.drawable.field);

  			int width = BitmapOrg.getWidth();
  			int height = BitmapOrg.getHeight();

  			// calculate the scale
  			float scaleWidth = ((float) btn_width) / width;
  			float scaleHeight = ((float) btn_height) / height;

  			// create a matrix for the manipulation
  			Matrix matrix = new Matrix();
  			// resize the Bitmap
  			matrix.postScale(scaleWidth, scaleHeight);

  			// recreate the new Bitmap
  			Bitmap bm = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);
  			Paint paint = new Paint();   
  			canvas.drawBitmap(bm, 0, 0, null);
  			
  			paint.setColor(res.getColor(R.color.grey_dark));
  			paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
  			paint.setTextSize(text_size);
  			paint.setTextAlign(Align.CENTER);
  			
  			if (goalkeeper == true) {
				
				plus = click_position_save + click_position_save_7m + click_position_save_fb;
				minus = click_position_goal_against + click_position_goal_against_7m + click_position_goal_against_fb;
				
			} else {
				
				plus = click_position_goal + click_position_goal_7m + click_position_goal_fb;
				minus = click_position_miss + click_position_miss_7m + click_position_miss_fb;
				
			}
  			
  			attempts = plus + minus;
			if (attempts > 0) percent = plus * 100 / attempts;
			
			// Kreislayout definieren
	  		paint.setAntiAlias(true);
	  		if (attempts > 0)  {
		  		if (percent <=33) paint.setColor(res.getColor(R.color.red));
		  		if (percent > 33 && percent < 66) paint.setColor(res.getColor(R.color.yellow));
		  		if (percent >=66) paint.setColor(res.getColor(R.color.green));
		  	}
	  		
	  		// Kreis zeichnen
	  		text_x = x_click * btn_width / 200;
			text_y = y_click * btn_height / 120;
	  		canvas.drawCircle (text_x, text_y, circle_radius, paint);
	  		btn_field.setImageBitmap(bitMap);
	  		paint.setColor((res.getColor(R.color.grey_dark)));
	  		position_text = String.valueOf(plus) + " / " + String.valueOf(attempts);
			canvas.drawText(position_text, text_x, text_y, paint);
			position_text = String.valueOf(percent) + " %";
			canvas.drawText(position_text, text_x, text_y + text_size, paint);
  			
  		}
	}

	public int getBestPosition(int x_coord_original, int y_coord_original) {
		
		int best_position = 0;
		double best_distance = 250;
		Integer[] position_x = new Integer[8];
		Integer[] position_y = new Integer[8];
		position_x[0] = 20;
		position_x[1] = 34;
		position_x[2] = 54;
		position_x[3] = 100;
		position_x[4] = 100;
		position_x[5] = 164;
		position_x[6] = 144;
		position_x[7] = 180;
		
		position_y[0] = 30;
		position_y[1] = 94;
		position_y[2] = 69;
		position_y[3] = 99;
		position_y[4] = 69;
		position_y[5] = 94;
		position_y[6] = 69;
		position_y[7] = 30;
		
		for (int position = 0; position <= 7; position ++) {
			
			// Abstand der Wurdposition von der Position bestimmen
			double distance = Math.sqrt(Math.pow(x_coord_original - position_x[position], 2) +
										   Math.pow(y_coord_original - position_y[position], 2));
			
			if (distance < best_distance) {
				
				best_distance = distance;
				best_position = position;
				
			}
			
		}
		
		return (best_position);
		
	}
	

/*
 * 
 * Wurfstatistik - Individuell 
 *
 */
	
	public void lytStatTickerActicity(Bundle contentArgs, final View contentView, final FragmentManager contentFragmentManager, SmartStatGameActivity activity) {
		
		view = contentView;
		ctxt = view.getContext();
		res = ctxt.getResources();
		fragmentManager = contentFragmentManager;
		args = contentArgs;
		smartStatGameActivity = activity;
		
/* Helper definieren */

		sqlHelper = new HelperSQL(ctxt);
		fctHelper = new HelperFunction();
		txtHelper = new HelperText();
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(ctxt);
		
		if(screenInch > 6) {
			
			
			
		}
		
/* Daten aus Activity laden */
		
		if (args != null) {
			
			game_id = args.getString("GameID");
			
		}
		
/* Activity IDs laden */
		
		activityIDs();
		
/* Arraylist definieren */
		
		stat_game_activities = sqlHelper.getStatGameActivities();
		Log.v("HelperLayout stat_game_activities", stat_game_activities);
		// Falls alle Aktivitäten angezeigt werden sollen, wähle die ganze Datenbank
		if (stat_game_activities.equals("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII")) {
			Log.v("HelperLayout", "Alle Statistiken ausgewählt");
			c = sqlHelper.getAllGameTicker(game_id);
			
		} else {
			Log.v("HelperLayout", "Einzelabfrage ausgewählt");
			activityList = sqlHelper.getActivityListFromActivityString();

			// Abfrage, ob die einzelnen Spielaktionen angezeigt werden sollen
			if (activityList[0] == 1) {
				possession_active = String.valueOf(res.getInteger(R.integer.possession_id));
			} else {
				possession_active = "0";
			}
			if (activityList[1] == 1) {
				goal_active = String.valueOf(res.getInteger(R.integer.goal_id));
			} else {
				goal_active = "0";
			}
			if (activityList[2] == 1) {
				goal_7m_active = String.valueOf(res.getInteger(R.integer.goal_7m_id));
			} else {
				goal_7m_active = "0";
			}
			if (activityList[3] == 1) {
				goal_fb_active = String.valueOf(res.getInteger(R.integer.goal_fb_id));
			} else {
				goal_fb_active = "0";
			}
			if (activityList[4] == 1) {
				miss_active = String.valueOf(res.getInteger(R.integer.miss_id));
			} else {
				miss_active = "0";
			}
			if (activityList[5] == 1) {
				miss_7m_active = String.valueOf(res.getInteger(R.integer.miss_7m_id));
			} else {
				miss_7m_active = "0";
			}
			if (activityList[6] == 1) {
				miss_fb_active = String.valueOf(res.getInteger(R.integer.miss_fb_id));
			} else {
				miss_fb_active = "0";
			}
			if (activityList[7] == 1) {
				save_active = String.valueOf(res.getInteger(R.integer.save_id));
			} else {
				save_active = "0";
			}
			if (activityList[8] == 1) {
				save_7m_active = String.valueOf(res.getInteger(R.integer.save_7m_id));
			} else {
				save_7m_active = "0";
			}
			if (activityList[9] == 1) {
				save_fb_active = String.valueOf(res.getInteger(R.integer.save_fb_id));
			} else {
				save_fb_active = "0";
			}
			if (activityList[10] == 1) {
				goal_against_active = String.valueOf(res.getInteger(R.integer.goal_against_id));
			} else {
				goal_against_active = "0";
			}
			if (activityList[11] == 1) {
				goal_against_7m_active = String.valueOf(res.getInteger(R.integer.goal_against_7m_id));
			} else {
				goal_against_7m_active = "0";
			}
			if (activityList[12] == 1) {
				goal_against_fb_active = String.valueOf(res.getInteger(R.integer.goal_against_fb_id));
			} else {
				goal_against_fb_active = "0";
			}
			if (activityList[13] == 1) {
				assist_goal_active = String.valueOf(res.getInteger(R.integer.assist_goal_id));
			} else {
				assist_goal_active = "0";
			}
			if (activityList[14] == 1) {
				assist_miss_active = String.valueOf(res.getInteger(R.integer.assist_miss_id));
			} else {
				assist_miss_active = "0";
			}
			if (activityList[15] == 1) {
				defense_goal_active = String.valueOf(res.getInteger(R.integer.defense_error_id));
			} else {
				defense_goal_active = "0";
			}
			if (activityList[16] == 1) {
				defense_miss_active = String.valueOf(res.getInteger(R.integer.defense_success_id));
			} else {
				defense_miss_active = "0";
			}
			if (activityList[17] == 1) {
				block_goal_active = String.valueOf(res.getInteger(R.integer.block_error_id));
			} else {
				block_goal_active = "0";
			}
			if (activityList[18] == 1) {
				block_miss_active = String.valueOf(res.getInteger(R.integer.block_success_id));
			} else {
				block_miss_active = "0";
			}
			if (activityList[19] == 1) {
				foul_active = String.valueOf(res.getInteger(R.integer.foul_id));
			} else {
				foul_active = "0";
			}
			if (activityList[20] == 1) {
				tech_fault_active = String.valueOf(res.getInteger(R.integer.tech_fault_id));
			} else {
				tech_fault_active = "0";
			}
			if (activityList[21] == 1) {
				fehlpass_active = String.valueOf(res.getInteger(R.integer.fehlpass_id));
			} else {
				fehlpass_active = "0";
			}
			if (activityList[22] == 1) {
				steps_active = String.valueOf(res.getInteger(R.integer.steps_id));
			} else {
				steps_active = "0";
			}
			if (activityList[23] == 1) {
				three_seconds_active = String.valueOf(res.getInteger(R.integer.three_seconds_id));
			} else {
				three_seconds_active = "0";
			}
			if (activityList[24] == 1) {
				doppeldribbel_active = String.valueOf(res.getInteger(R.integer.doppeldribbel_id));
			} else {
				doppeldribbel_active = "0";
			}
			if (activityList[25] == 1) {
				fuss_active = String.valueOf(res.getInteger(R.integer.fuss_id));
			} else {
				fuss_active = "0";
			}
			if (activityList[26] == 1) {
				zeitspiel_active = String.valueOf(res.getInteger(R.integer.zeitspiel_id));
			} else {
				zeitspiel_active = "0";
			}
			if (activityList[27] == 1) {
				kreis_active = String.valueOf(res.getInteger(R.integer.kreis_id));
			} else {
				kreis_active = "0";
			}
			if (activityList[28] == 1) {
				stuermerfoul_active = String.valueOf(res.getInteger(R.integer.stuermerfoul_id));
			} else {
				stuermerfoul_active = "0";
			}
			if (activityList[29] == 1) {
				yellow_card_active = String.valueOf(res.getInteger(R.integer.yellow_card_id));
			} else {
				yellow_card_active = "0";
			}
			if (activityList[30] == 1) {
				two_minutes_active = String.valueOf(res.getInteger(R.integer.two_minutes_id));
			} else {
				two_minutes_active = "0";
			}
			if (activityList[31] == 1) {
				twoplustwo_active = String.valueOf(res.getInteger(R.integer.twoplustwo_id));
			} else {
				twoplustwo_active = "0";
			}
			if (activityList[32] == 1) {
				red_card_active = String.valueOf(res.getInteger(R.integer.red_card_id));
			} else {
				red_card_active = "0";
			}
			if (activityList[33] == 1) {
				sub_in_active = String.valueOf(res.getInteger(R.integer.sub_in_id));
			} else {
				sub_in_active = "0";
			}
			if (activityList[34] == 1) {
				sub_out_active = String.valueOf(res.getInteger(R.integer.sub_out_id));
			} else {
				sub_out_active = "0";
			}
			if (activityList[35] == 1) {
				timeout_active = String.valueOf(res.getInteger(R.integer.timeout_id));
			} else {
				timeout_active = "0";
			}
			if (activityList[36] == 1) {
				tactic_60_active = String.valueOf(res.getInteger(R.integer.tactic_60_id));
			} else {
				tactic_60_active = "0";
			}
			if (activityList[37] == 1) {
				tactic_51_active = String.valueOf(res.getInteger(R.integer.tactic_51_id));
			} else {
				tactic_51_active = "0";
			}
			if (activityList[38] == 1) {
				tactic_42_active = String.valueOf(res.getInteger(R.integer.tactic_42_id));
			} else {
				tactic_42_active = "0";
			}
			if (activityList[39] == 1) {
				tactic_321_active = String.valueOf(res.getInteger(R.integer.tactic_321_id));
			} else {
				tactic_321_active = "0";
			}
			if (activityList[40] == 1) {
				tactic_guarding_active = String.valueOf(res.getInteger(R.integer.tactic_guarding_id));
			} else {
				tactic_guarding_active = "0";
			}
			
			// Datenbank abfragen
			String[] args={game_id, possession_active, goal_active, 
					goal_7m_active, goal_fb_active, miss_active, miss_7m_active, miss_fb_active, save_active, 
					save_7m_active, save_fb_active, goal_against_active, goal_against_7m_active, goal_against_fb_active, assist_goal_active, 
					assist_miss_active, defense_goal_active, defense_miss_active, block_goal_active, block_miss_active, foul_active, 
					tech_fault_active, fehlpass_active, steps_active, three_seconds_active, doppeldribbel_active, fuss_active, 
					zeitspiel_active, kreis_active, stuermerfoul_active, yellow_card_active, two_minutes_active, twoplustwo_active, 
					red_card_active, sub_in_active, sub_out_active, timeout_active, tactic_60_active, 
					tactic_51_active, tactic_42_active, tactic_321_active, tactic_guarding_active};
			SQLiteDatabase db = sqlHelper.getWritableDatabase();
			c = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id=? AND (activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ? OR " +
					"activity_id = ? OR activity_id = ? OR activity_id = ? OR activity_id = ?) ORDER BY time DESC", args);

		}
		
/* Teamliste einrichten */
		
		smartStatGameActivity.startManagingCursor(c);
		HelperAdapterTicker adapter=new HelperAdapterTicker(ctxt, c, null);
		
		if (screenInch > 6) {
			
			
			
		} else {
			
			smartStatGameActivity.setListAdapter(adapter);
			
		}
				
/* Button  definieren */
				
		Button btn_set = (Button) view.findViewById(R.id.set);
		
		// Einstell-Button
		btn_set.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				args.putString("InputString", "TickerActivity");
				
				if (screenInch > 6) {
					
					
					
				} else {
					
					Intent i = new Intent(ctxt, SmartListWithClickbox.class);
					i.putExtras(args);
        				((Activity)ctxt).startActivity(i);
					
				}
			}
		});
	}
	
	
/*
 * 
 * Date Picker definieren
 *
 */

/* DatePicker für die Tablet-Version definieren */

	private void showDatePicker() {
		
		HelperDatePicker date = new HelperDatePicker();

		Calendar calender = Calendar.getInstance();
		
		args = new Bundle();
		args.putInt("year", calender.get(Calendar.YEAR));
		args.putInt("month", calender.get(Calendar.MONTH));
		args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		
		date.setArguments(args);
		date.setCallBack(ondate);
		date.show(fragmentManager, "Date Picker");
		
	}

	OnDateSetListener ondate = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker v, int selectedYear, int selectedMonth, int selectedDay) {
				  
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set current date to buttontext

			Button btn_date = (Button) view.findViewById(R.id.game_date);
			btn_date.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(day).append(".")
				.append(month + 1).append(".")
				.append(year));
			
			game_date = new Date(year-1900, month, day);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	    		str_game_date = dateFormat.format(game_date);
			sqlHelper.updateGame(game_id, null, null, null, null, null, str_game_date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
			fragGameList.refreshContent(game_id);
			
		}
	};
	
/*
 * 
 * Mannschaftsauswahl aufrufen
 *
 */
	
	public void teamSelect(String game_id, Integer home_or_away) {

		// Abfragen, ob schon eine Aktion im Spiel eingegeben wurde
		// Falls ja, kann die Mannschaft nicht mehr geändert werden
		
		if (sqlHelper.count_ticker_activity(game_id, home_or_away, null, null, null, null) > 0) {
			
			// DialogBox einrichten
			final Dialog dialog = new Dialog(ctxt);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_dialog);

			// Texte setzen
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			title.setText(R.string.text_selection_not_possible);
			text.setText(R.string.text_team_change);
			
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
				}
			});

			dialog.show();

		} else {
      				
			if (screenInch > 6) {
				
				args = new Bundle();
				args.putString("GameID", game_id);
				args.putInt("HomeOrAway", home_or_away);
				args.putString("HomeID", home_id);
				args.putString("AwayID", away_id);
			        
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				TabFragTeamSelect fragment = new TabFragTeamSelect();
				fragment.setArguments(args);
				fragmentTransaction.replace(R.id.frag_game_edit, fragment);
				fragmentTransaction.commit();
				
			} else {
				
				Intent i = new Intent(ctxt, SmartTeamSelect.class);
				args = new Bundle();
				
				args.putString("GameID", game_id);
				args.putInt("HomeOrAway", home_or_away);
				args.putString("HomeID", home_id);
				args.putString("AwayID", away_id);
				i.putExtras(args);
				((Activity)ctxt).startActivityForResult(i, GET_CODE);
				
			}
		}
	} 
	
/*
 * 
 * Ticker Uhr einrichten
 *
 */
	
	public void timerStartStop() {
		
		if (stopped) {
			
	    		time_start = System.currentTimeMillis(); 
	        	mHandler.removeCallbacks(startTimer);
	            mHandler.postDelayed(startTimer, 0);
	            stopped = false;
	            sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, time_sofar, time_start, null, null, null, null, null, null, null, null, null, null, null, null);
	            ((Button) view.findViewById(R.id.btn_timer)).setBackgroundResource(R.drawable.button_timer_green);
	            
	            // Falls noch keine Mannschaft im Ballbesitz: Abfrage, welche Mannschaft im Ballbesitz ist
			if (possession == 2) {
				
				// DialogBox einrichten
				final Dialog dialog = new Dialog(ctxt);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				TextView title = (TextView) dialog.findViewById(R.id.title);
				TextView text = (TextView) dialog.findViewById(R.id.text);
				title.setText(R.string.throw_off);
				text.setText(R.string.text_team_throwoff);
					
				// Button definieren
				LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
				lyt_button3.removeAllViews();

				Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
				dialogButton1.setText(R.string.home);
				
				dialogButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.dismiss();
						possession = 1;
        					long lngNull = 0;
        					setPossession(possession, lngNull, true);
						
					}
				});
				
				Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
				dialogButton2.setText(R.string.away);
				
				dialogButton2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.dismiss();
						possession = 0;
        					long lngNull = 0;
        					setPossession(possession, lngNull, true);
						
					}
				});

				dialog.show();
				// Ende Dialogbox einrichten
				
			}
	    	} else {
	    			
	    		elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
    			time_sofar = elapsedTime;
    			time_start = 0;
	    		mHandler.removeCallbacks(startTimer);
	    		stopped = true;
	    		sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, time_sofar, time_start, null, null, null, null, null, null, null, null, null, null, null, null);
	    		((Button)view.findViewById(R.id.btn_timer)).setBackgroundResource(R.drawable.button_timer_red);
	    		
	    	}		
	}
	
	private Runnable startTimer = new Runnable() {
		
	    	public void run() {
	    		
	    		elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
	    		((TextView) view.findViewById(R.id.btn_timer)).setText(fctHelper.updateTimer(elapsedTime));
	    		mHandler.postDelayed(this,REFRESH_RATE);
	    		
	    		/* Uhr stoppen bei Halbzeitpause */
	    		if (elapsedTime > sqlHelper.getGameDurationHalftimeByID(game_id) * 60 * 1000 &&
	    				sqlHelper.getGameCurrentHalftimeByID(game_id) == 0) {
	    			
	        		/* Stoppe die Zeit */
	    			elapsedTime = Long.valueOf(sqlHelper.getGameDurationHalftimeByID(game_id) * 60 * 1000);
	    			time_sofar = elapsedTime;
	    			time_start = 0;
	    			mHandler.removeCallbacks(startTimer);
	    			stopped = true;
	    			sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, time_sofar, time_start, null, null, null, null, null, null, null, null, null, null, null, null);
	            	
	    			/* Schreibe in die Datenbank, dass die zweite Halbzeit begonnen hat */
	    			sqlHelper.updateGame(game_id, null, null, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	    			((Button) view.findViewById(R.id.btn_timer)).setBackgroundResource(R.drawable.button_timer_red);

	    		}
	    		
	    		/* Uhr stoppen bei Spielende */
	    		if (elapsedTime > sqlHelper.getGameDurationHalftimeByID(game_id) * 2 * 60 * 1000 &&
	    				sqlHelper.getGameCurrentHalftimeByID(game_id) == 1){
	    			
	        		/* Stoppe die Zeit */
	    			elapsedTime = Long.valueOf(sqlHelper.getGameDurationHalftimeByID(game_id) * 2 * 60 * 1000);
	    			time_sofar = elapsedTime;
	    			time_start = 0;
	    			mHandler.removeCallbacks(startTimer);
	    			stopped = true;
	    			sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, time_sofar, time_start, null, null, null, null, null, null, null, null, null, null, null, null);
	            	
	    			/* Schreibe in die Datenbank, dass die zweite Halbzeit begonnen hat */
	    			sqlHelper.updateGame(game_id, null, null, 2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	    			((Button) view.findViewById(R.id.btn_timer)).setBackgroundResource(R.drawable.button_timer_red);
	    			
	    		}
	    	}
	};
	
/*
 * 
 * Gelbe Karten einrichten
 *
 */
	
	public void setYellowCards() {
		
		ImageView img_yellow_card_home_1 = (ImageView) view.findViewById(R.id.yellow_card_home_1);
		ImageView img_yellow_card_home_2 = (ImageView) view.findViewById(R.id.yellow_card_home_2);
		ImageView img_yellow_card_home_3 = (ImageView) view.findViewById(R.id.yellow_card_home_3);
		ImageView img_yellow_card_away_1 = (ImageView) view.findViewById(R.id.yellow_card_away_1);
		ImageView img_yellow_card_away_2 = (ImageView) view.findViewById(R.id.yellow_card_away_2);
		ImageView img_yellow_card_away_3 = (ImageView) view.findViewById(R.id.yellow_card_away_3);

		count_ticker_activity = sqlHelper.count_ticker_activity(game_id, 1, null, yellow_card_id, null, null);
		if (count_ticker_activity > 0) {	
			img_yellow_card_home_1.setImageResource(R.drawable.yellow_card_mini);
			if (count_ticker_activity > 1) {
				img_yellow_card_home_2.setImageResource(R.drawable.yellow_card_mini);
				if (count_ticker_activity > 2) {
					img_yellow_card_home_3.setImageResource(R.drawable.yellow_card_mini);
				}
			}
		}
		
		count_ticker_activity = sqlHelper.count_ticker_activity(game_id, 0, null, yellow_card_id, null, null);
		if (count_ticker_activity > 0) {	
			img_yellow_card_away_1.setImageResource(R.drawable.yellow_card_mini);
			if (count_ticker_activity > 1) {
				img_yellow_card_away_2.setImageResource(R.drawable.yellow_card_mini);
				if (count_ticker_activity > 2) {
					img_yellow_card_away_3.setImageResource(R.drawable.yellow_card_mini);
				}
			}
		}
		
	}
	
/*
 * 
 * Ballbesitzwechsel einfügen
 *
 */
	
	public void setPossession(Integer possession, Long time, Boolean throwoff) {
		
		// Button definieren
		Button btn_goals_home = (Button) view.findViewById(R.id.btn_goals_home);
		Button btn_goals_away = (Button) view.findViewById(R.id.btn_goals_away);
		
		// Variablen einrichten
		realtime = DateFormat.getDateTimeInstance().format(new Date());
		if (throwoff == true) {
			if (possession == 1) ticker_event_note = txtHelper.txtTickerThrowoff(team_home);
			if (possession == 0) ticker_event_note = txtHelper.txtTickerThrowoff(team_away);
		} 
		
		// Button einrichten
		if (possession == 1) {
			btn_goals_home.setBackgroundResource(R.drawable.button_result_active);
			btn_goals_away.setBackgroundResource(R.drawable.button_result_inactive);
			ticker_text = res.getString(R.string.possession) + " " + team_home_short;
		}
		if (possession == 0) {
			btn_goals_home.setBackgroundResource(R.drawable.button_result_inactive);
			btn_goals_away.setBackgroundResource(R.drawable.button_result_active);
			ticker_text = res.getString(R.string.possession) + " " + team_away_short;
		}		
		
		// Ballbesitzwechsel in Datenbank eintragen
		sqlHelper.insertTickerEvent(game_id, (int) (long) time, ticker_event_note, null);
		ticker_event_id = sqlHelper.getLastTickerEventID();
		sqlHelper.insertTickerActivity(game_id, ticker_event_id, (int) (long) time, realtime, possession_id, 
				possession, null, null, null, null, null, ticker_text, null);
		sqlHelper.updateGame(game_id, null, null, null, possession, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);				// aktuellen Ballbesitz in Spiel eintragen
		
		if(screenInch > 6) {
			
			// Aktualisiere die Tickerliste
			fragTickerList.refreshContent(game_id);
			
		} else {
			
			SmartTickerList activity = (SmartTickerList) ((ActivityGroup) ctxt).getLocalActivityManager().getActivity(res.getString(R.string.ticker));
			try {
				activity.refreshContent(game_id);
			} catch(Exception e){

			}
		}
	}

/*
 * 
 * Activity IDs einrichten 
 *
 */
	
	public void activityIDs() {
		
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
	
/*
 * 
 * Start einer Aktion 
 *
 */
	
	public void startActivity() {
		
		// Ermittlung des Ballbesitzes. Bei Spielbeginn: Abfrage, welche Mannschaft Anwurf hat und Anwurf eintragen.
		
		if (sqlHelper.getGamePossession(game_id) != 2) {
			
			possession = sqlHelper.getGamePossession(game_id);
			setActivity();
			
		} else {
			
			if (sqlHelper.getGameTimeSoFar(game_id) != null) time_sofar = Long.parseLong(sqlHelper.getGameTimeSoFar(game_id));
			if (sqlHelper.getGameTimeStart(game_id) != null) time_start = Long.parseLong(sqlHelper.getGameTimeStart(game_id));
			
			if (time_start == 0) {
				elapsedTime = time_sofar;
			} else {
				elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
			}
			
			// Falls noch keine Mannschaft im Ballbesitz: Abfrage, welche Mannschaft im Ballbesitz ist        			
			// DialogBox einrichten
			final Dialog dialog = new Dialog(ctxt);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_dialog);

			// Texte setzen
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			title.setText(R.string.throw_off);
			text.setText(R.string.text_team_throwoff);
				
			// Button definieren
			LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
			lyt_button3.removeAllViews();

			Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
			dialogButton1.setText(R.string.home);
			
			dialogButton1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					dialog.dismiss();
					
					if (sqlHelper.getGamePossession(game_id) != 1 ){
        					
	        				possession = 1;
	        				realtime = DateFormat.getDateTimeInstance().format(new Date());
	        				ticker_event_note = txtHelper.txtTickerThrowoff(team_home);
	        				ticker_text = res.getString(R.string.possession) + " " + team_home_short;
	        				// Ballbesitzwechsel in Datenbank eintragen
	        				sqlHelper.insertTickerEvent(game_id, (int) (long) 0, ticker_event_note, null);
	        				ticker_event_id = sqlHelper.getLastTickerEventID();
	        				sqlHelper.insertTickerActivity(game_id, ticker_event_id, (int) (long) 0, realtime, possession_id, 
	        				possession, null, null, null, null, null, ticker_text, null);
	        				sqlHelper.updateGame(game_id, null, null, null, possession, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);	
	        				setActivity();

	        			}
					
				}
			});
			
			Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
			dialogButton2.setText(R.string.away);
			
			dialogButton2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					dialog.dismiss();
					if (sqlHelper.getGamePossession(game_id) != 0) {
        					
	        				possession = 0;
	        				realtime = DateFormat.getDateTimeInstance().format(new Date());
	        				ticker_event_note = txtHelper.txtTickerThrowoff(team_away);
	        				ticker_text = res.getString(R.string.possession) + " " + team_away_short;
	        				// Ballbesitzwechsel in Datenbank eintragen
	        				sqlHelper.insertTickerEvent(game_id, (int) (long) 0, ticker_event_note, null);
	        				ticker_event_id = sqlHelper.getLastTickerEventID();
	        				sqlHelper.insertTickerActivity(game_id, ticker_event_id, (int) (long) 0, realtime, possession_id, 
	        						possession, null, null, null, null, null, ticker_text, null);
	        				sqlHelper.updateGame(game_id, null, null, null, possession, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);	
	        				setActivity();

	        			}
					
				}
			});

			dialog.show();
			// Ende Dialogbox einrichten
		}
		
	}
	
	public void setActivity() {
		
		strInput = "";
/* Ermittlung der Grunddaten der Aktion (Zeit, Mannschaft der Aktion). Weiterleitung entweder an Spielerauswahl oder direkte Eintragung der Aktion. */
		
		// Zeitdaten
		realtime = DateFormat.getDateTimeInstance().format(new Date());
		if (sqlHelper.getGameTimeSoFar(game_id) != null) time_sofar = Long.parseLong(sqlHelper.getGameTimeSoFar(game_id));
		if (sqlHelper.getGameTimeStart(game_id) != null) time_start = Long.parseLong(sqlHelper.getGameTimeStart(game_id));
		game_halftime = sqlHelper.getGameDurationHalftimeByID(game_id);
		
		if (time_start == 0) {
			elapsedTime = time_sofar + 1;		// Bei Spielbeginn wurde bereits der Anwurf als Ballbesitz eingetragen. Damit der Ballbesitzwechsel aufgrund des Tors danach erscheint, wird die Zeit um1 erhöht
		} else {
			elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
		}
		activity_ticker_time = (int) (long) elapsedTime;
	
		if (sqlHelper.getGameInputPlayer(game_id) != null) {
			player_input = sqlHelper.getGameInputPlayer(game_id);
		} else {
			player_input = 1;
		}
		
		// Mannschaft, die die Aktion ausgeführt hat, ermitteln
		if (activity_id == goal_id || activity_id == goal_7m_id || activity_id == goal_fb_id || activity_id == miss_id ||
				activity_id == miss_7m_id || activity_id == miss_fb_id || activity_id == tech_fault_id) {
			home_or_away = possession;
		}
		if (activity_id == yellow_card_id || activity_id == two_minutes_id || activity_id == twoplustwo_id || 
				activity_id == sub_in_id || activity_id == red_card_id || activity_id == defense_success_id) {
			if (possession == 1) {
				home_or_away = 0;
			} else {
				home_or_away = 1;
			}
		}
		
/* Tickermeldungen eintragen */
		
		if (home_or_away.equals(1)) {
			teamname = sqlHelper.getGameTeamHomeByID(game_id);
		} else {
			teamname = sqlHelper.getGameTeamAwayByID(game_id);
		}
		
		// Keine Aktion eintragen, falls Aktion = Aufstellung eingeben 
		
		if (activity_id != lineup_id) {
			
			sqlHelper.insertTickerEvent(game_id, (int) (long) elapsedTime, ticker_event_note, null);
			ticker_event_id = sqlHelper.getLastTickerEventID();
			sqlHelper.insertTickerActivity(game_id, ticker_event_id, (int) (long) elapsedTime, realtime, activity_id, home_or_away, null, null, null, null, null, activity_string, null);
			activity_result = sqlHelper.getGameCurrentResultString(game_id);
			ticker_activity_id = sqlHelper.getLastTickerActivityID();
			sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, null, activity_result);
			sqlHelper.updateTickerActivity(String.valueOf(ticker_activity_id), null, null, null, null, null, null, null, null, null, null, null, null, null, activity_result, null);
			activity_ticker_time = activity_ticker_time + 1;	// Nachfolgende Aktionen sollen im Ticker später angezeigt werden
			
			maxTime = sqlHelper.maxTickerTime(game_id);
			
			// Falls ein Tor geworfen wurde...
			if (activity_id == goal_id || activity_id == goal_7m_id || activity_id == goal_fb_id ||
					activity_id == miss_id || activity_id == miss_7m_id || activity_id == miss_fb_id) {
				
				// ... Gegentor bzw. gehalten eintragen
				
				// Activity ID ermitteln
				if (activity_id == goal_id)  activity_against_id = goal_against_id;
				if (activity_id == goal_7m_id)  activity_against_id = goal_against_7m_id;
				if (activity_id == goal_fb_id)  activity_against_id = goal_against_fb_id;
				if (activity_id == miss_id)  activity_against_id = save_id;
				if (activity_id == miss_7m_id)  activity_against_id = save_7m_id;
				if (activity_id == miss_fb_id)  activity_against_id = save_fb_id;
				
				// Gegnertorwart ermitteln
				goalkeeper_id = null;
				gk_home_id = null;
				gk_away_id = null;
				if (sqlHelper.getGameGKHomeIDByID(game_id) != null) gk_home_id = String.valueOf(sqlHelper.getGameGKHomeIDByID(game_id));
				if (sqlHelper.getGameGKAwayIDByID(game_id) != null) 	gk_away_id = String.valueOf(sqlHelper.getGameGKAwayIDByID(game_id));
				if (home_or_away.equals(1)) {
					goalkeeper_id = gk_away_id;
					possession_against = 0;
				}
				if (home_or_away.equals(0)) {
					goalkeeper_id = gk_home_id;
					possession_against = 1;
				}
				
				// Gegentor bzw. gehalten eintragen
				activity_against_string = sqlHelper.getActivityStringByActivityID(activity_against_id, res);
				ticker_activity_against_id = sqlHelper.getLastTickerActivityID();
				activity_ticker_time = activity_ticker_time +1;
				sqlHelper.insertTickerActivity(game_id, ticker_event_id, activity_ticker_time, realtime, activity_against_id, possession_against, goalkeeper_id, null, null, null, null, activity_against_string, activity_result);
				ticker_activity_against_id = sqlHelper.getLastTickerActivityID();
				
				// Assist eintragen, falls Assist eingetragen werden sollen
				if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
					
					activity_ticker_time = activity_ticker_time + 1;
					if (activity_id == goal_id || activity_id == goal_7m_id || activity_id == goal_fb_id) sqlHelper.insertTickerActivity(game_id, ticker_event_id, activity_ticker_time, realtime, assist_goal_id, possession, null, null, null, null, null, res.getString(R.string.assist_goal), null);
					if (activity_id == miss_id || activity_id == miss_7m_id || activity_id == miss_fb_id) sqlHelper.insertTickerActivity(game_id, ticker_event_id, activity_ticker_time, realtime, assist_miss_id, possession, null, null, null, null, null, res.getString(R.string.assist), null);
					ticker_assist_id = sqlHelper.getLastTickerActivityID();
					
				}
				
				// Abwehr eintragen, falls Abwehr eingetragen werden sollen
				if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
					
					activity_ticker_time = activity_ticker_time + 1;
					if (activity_id == goal_id || activity_id == goal_7m_id || activity_id == goal_fb_id) sqlHelper.insertTickerActivity(game_id, ticker_event_id, activity_ticker_time, realtime, defense_error_id, possession_against, null, null, null, null, null, res.getString(R.string.defense), null);
					if (activity_id == miss_id || activity_id == miss_7m_id || activity_id == miss_fb_id) sqlHelper.insertTickerActivity(game_id, ticker_event_id, activity_ticker_time, realtime, defense_success_id, possession_against, null, null, null, null, null, res.getString(R.string.defense), null);
					ticker_defense_id = sqlHelper.getLastTickerActivityID();
					
				}
				
				// ... Ballbesitz ändern, wenn der Eintrag der zeitlich letzte Tickereintrag war und wenn ein Tor geworfen wurde
				if (maxTime <= activity_ticker_time) {
					if (!activity_id.equals(miss_id) && !activity_id.equals(miss_7m_id) && !activity_id.equals(miss_fb_id)) {
						
						sqlHelper.changePossession(game_id, possession_against, ticker_event_id, activity_ticker_time + 10, realtime, activity_result, res);
						ticker_possession_id = sqlHelper.getLastTickerActivityID();
					}
				}
				
				if (!activity_id.equals(miss_id) && !activity_id.equals(miss_7m_id) && !activity_id.equals(miss_fb_id)) {
					
					// ... Spielstand eintragen
					
					Integer goals_home = 0;
					Integer goals_away = 0;
					String result = "0:0";
				
					// Wenn die Spielzeit des letzten Tickereintrags größer ist als der aktuelle Tickereintrag, dann ändere alle Spielresultate
					if (maxTime > activity_ticker_time) {
						
						String[] strArgs = {game_id};
						SQLiteDatabase db = sqlHelper.getWritableDatabase();
						Cursor cTicker = db.rawQuery("SELECT * FROM ticker_activity WHERE game_id = ? ORDER BY time ASC", strArgs);
						cTicker.moveToFirst();
						String ticker_id = null;
						
						// Alle Tickermeldungen abfragen und Tor eintragen
						for (cTicker.moveToFirst(); !cTicker.isAfterLast(); cTicker.moveToNext()) {
							
							ticker_id = sqlHelper.getTickerActivityID(cTicker);
							
							if (sqlHelper.getTickerActivityIDByID(ticker_id) == goal_id ||
									sqlHelper.getTickerActivityIDByID(ticker_id) == goal_7m_id ||
									sqlHelper.getTickerActivityIDByID(ticker_id) == goal_fb_id) {
								
								if (sqlHelper.getTickerHomeOrAwayByID(ticker_id) == 1) {
									goals_home = goals_home + 1;
								}
								if (sqlHelper.getTickerHomeOrAwayByID(ticker_id) == 0) {
									goals_away = goals_away + 1;
								}
								
								result = goals_home + ":" + goals_away;
								sqlHelper.updateTickerEvent(String.valueOf(sqlHelper.getTickerEventIDByActivityID(ticker_id)), null, null, null, result);
								sqlHelper.updateTickerActivity(ticker_id, null, null, null, null, null, null, null, null, null, null, null, null, null, result, null);
								
								// Falls der Ticker dem aktuell eingetragenen entspricht, speichere das Resultat für zukünftige Eintragungen
								if (Integer.parseInt(ticker_id) == ticker_activity_id) activity_result = result;
								
							}
						}
						
						cTicker.close();
					}
				}
			}
		}
		
/* Abwehr eintragen */
		
		if (activity_id.equals(defense_success_id)) strInput = "Defense";
		
/* Gelbe Karte eintragen */
		
		if (activity_id.equals(yellow_card_id)) {

			// Foul des Spielers eintragen
			activity_ticker_time = activity_ticker_time - 1;
			sqlHelper.insertTickerActivity(game_id, ticker_event_id, (int) (long) activity_ticker_time, realtime, foul_id, 
					home_or_away, null, null, null, null, null, res.getString(R.string.foul), null);
			ticker_foul_id = sqlHelper.getLastTickerActivityID();
			
		}
		
/* Zeitstrafe eintragen */
		
		if (activity_id.equals(two_minutes_id) || activity_id.equals(twoplustwo_id) || activity_id.equals(red_card_id)) {
			
			// Foul des Spielers eintragen
			activity_ticker_time = activity_ticker_time - 1;
			sqlHelper.insertTickerActivity(game_id, ticker_event_id, (int) (long) activity_ticker_time, realtime, foul_id, 
					home_or_away, null, null, null, null, null, res.getString(R.string.foul), null);
			ticker_foul_id = sqlHelper.getLastTickerActivityID();
			
/** TODO -4- => Nach Spiellänge 2 Minuten auf Spiellänge setzen */
			
			// Rückkehr des Spielers eintragen
			int time_back = (int) (long) activity_ticker_time;
			if (activity_id.equals(two_minutes_id) || activity_id.equals(red_card_id)) {
				time_back = time_back + 2*60000;
			}
			if (activity_id.equals(twoplustwo_id)) {
				time_back = time_back + 4*60000;
			}
			sqlHelper.insertTickerActivity(game_id, ticker_event_id, time_back, realtime, two_in_id, 
					home_or_away, null, null, null, null, null, res.getString(R.string.suspension_end), null);
			ticker_player_back_id = sqlHelper.getLastTickerActivityID();
			
		}
		
		if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id)) {
			strInput = "Goal";
		}
		if (activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) {
			strInput = "Miss";
		}
		
/* Falls nur Aktion ohne Spielernamen eingetragen werden soll, gehe zur Direkteingabe der Aktion */
		
		if (player_input == 0) {
			if (activity_id != lineup_id && activity_id != sub_in_id) {
				
				activity_direct();
				
			} 
		} else {
			
		// Andernfalls starte die Spielereingabe

			args = new Bundle();
			if (activity_id != null) args.putInt("ActivityID", activity_id);
			if (ticker_activity_id != null) args.putInt("TickerActivityID", ticker_activity_id);
			if (ticker_event_id != null) args.putInt("TickerEventID", ticker_event_id);
			if (ticker_activity_against_id != null) args.putInt("TickerActivityAgainstID", ticker_activity_against_id);
			if (ticker_assist_id != null) args.putInt("TickerAssistID", ticker_assist_id);
			if (ticker_defense_id != null) args.putInt("TickerDefenseID", ticker_defense_id);
			if (ticker_player_back_id != null) args.putInt("TickerPlayerBackID", ticker_player_back_id);
			if (ticker_foul_id != null) args.putInt("TickerFoulID", ticker_foul_id);
			if (ticker_possession_id != null) args.putInt("TickerPossessionID", ticker_possession_id);
			args.putInt("CurrentPossession", possession);
			args.putInt("HomeOrAway", home_or_away);
			args.putString("GameID", game_id);
			if (activity_ticker_time != null) args.putInt("Time", activity_ticker_time);
			args.putString("RealTime", realtime);
			args.putString("ActivityResult", activity_result);
			args.putString("InputString", strInput);
			
			if (activity_id.equals(lineup_id)) {
				
				if(screenInch > 6) {
			    		
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TabFragTickerLineup fragment = new TabFragTickerLineup();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					Intent i = new Intent(ctxt,SmartTickerLineup.class);
					i.putExtras(args);
					((Activity)ctxt).startActivity(i);
					
				}
				
			} else {
				
				if (activity_id.equals(tactic_60_id)) {
					
					home_or_away = 1;
					args.putString("InputString", "Tactic");
					args.putInt("HomeOrAway", home_or_away);
					
					if(screenInch > 6) {
				    		
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragListWithText fragment = new TabFragListWithText();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						Intent i = new Intent(ctxt, SmartListWithText.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						
					}
					
				} else {
					
					if(screenInch > 6) {
				    		
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						TabFragTickerPlayer fragment = new TabFragTickerPlayer();
						fragment.setArguments(args);
						fragmentTransaction.replace(R.id.frag_ticker_setup, fragment);
						fragmentTransaction.commit();
						
					} else {
						
						Log.v("HelperLayout", "Starte SmartTickerPlayer");
						Intent i = new Intent(ctxt,SmartTickerPlayer.class);
						i.putExtras(args);
						((Activity)ctxt).startActivity(i);
						Log.v("HelperLayout", "Beende SmartTickerPlayer");
						
					}
				}
			}
		}
	}
	

/*
 * 
 * Direkte Eingabe einer Aktion 
 *
 */
	
	public void activity_direct() {
		
		Toast.makeText(ctxt, activity_string, Toast.LENGTH_SHORT).show();
		
		// Bei Fehlwurf nach Ballbesitzwechsel fragen
	    	if (activity_id == miss_id || 
	    			activity_id == miss_7m_id || 
	    			activity_id == miss_fb_id || 
	    			activity_id == tech_fault_id) {
	   		
	   		// DialogBox einrichten
	   		final Dialog dialog = new Dialog(ctxt);
	   		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	   		dialog.setContentView(R.layout.custom_dialog);

	   		// Texte setzen
	   		TextView title = (TextView) dialog.findViewById(R.id.title);
	   		TextView text = (TextView) dialog.findViewById(R.id.text);
	   		title.setText(R.string.turnover);
	   		text.setText(R.string.text_losing_possession);
	   				
	   		// Button definieren
	   		LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
	   		lyt_button3.removeAllViews();

	   		Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
	   		dialogButton1.setText(R.string.yes);
	   	
	   		dialogButton1.setOnClickListener(new View.OnClickListener() {
	   		
	   			@Override
	   			public void onClick(View v) {
	   			
	   				dialog.dismiss();
	   			
	   				if (home_or_away.equals(1)) possession_against = 0;
	   				if (home_or_away.equals(0)) possession_against = 1;
							
	   				// ... Ballbesitz ändern, wenn der Eintrag der zeitlich letzte Tickereintrag war
	   				if (maxTime <= activity_ticker_time) sqlHelper.changePossession(game_id, possession_against, ticker_event_id, activity_ticker_time + 10, realtime, activity_result, res);
		   					
	   				if (screenInch > 6) {
		   						
	   					fragTickerList.refreshContent(team_id);
		   						
	   				} else {
		   						
	   					smartActivity.refreshTickerButton();
		   						
	   				}	   	
	   						
	   			}
	   		});
	   				
	   		Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
	   		dialogButton2.setText(R.string.no);
	   			
	   		dialogButton2.setOnClickListener(new View.OnClickListener() {
	   			@Override
	   			public void onClick(View v) {
	   			
	   				dialog.dismiss();
	   						
	   			}
	   		});

	   		dialog.show();
	   		// Ende Dialogbox einrichten
	    	}
		
	    	// Bei Zeitstrafen Spieler zurück eintragen
	    	if (activity_id == two_minutes_id || 
	    			activity_id == twoplustwo_id || 
	    			activity_id == red_card_id) {
	    		
/** TODO -4- => Nach Spiellänge 2 Minuten auf Spiellänge setzen */

			int time_back = (int) (long) activity_ticker_time;
			if (activity_id == two_minutes_id || activity_id == red_card_id) {
				time_back = time_back + 2*60000;
			}
			if (activity_id == twoplustwo_id) {
				time_back = time_back + 4*60000;
			}
			sqlHelper.insertTickerActivity(game_id, ticker_event_id, time_back, realtime, two_in_id, 
					home_or_away, null, null, null, null, null, res.getString(R.string.suspension_end), null);
			
	    	}
	    	
	    	if(screenInch > 6) {
	    		
			fragTickerList.refreshContent(game_id);
				
		} else {
				
			smartActivity.refreshTickerButton();
				
		}
    	}
	
/*
 * 
 * Eingabe der Auszeit
 *
 */
	
	public void activityTimeout(SmartTickerActivity smartActivity, Integer timeout_home_or_away) {
		
		// Timeout eintragen
		if (timeout_home_or_away == 1) {
			teamname = sqlHelper.getGameTeamHomeByID(game_id);
		} else {
			teamname = sqlHelper.getGameTeamAwayByID(game_id);
		}
		activity_string = res.getString(R.string.timeout) + " " + teamname;
		sqlHelper.insertTickerEvent(game_id, (int) (long) elapsedTime, null, null);
		ticker_event_id = sqlHelper.getLastTickerEventID();
		sqlHelper.insertTickerActivity(game_id, ticker_event_id, (int) (long) elapsedTime, realtime, activity_id, 
				timeout_home_or_away, null, null, null, null, null, activity_string, null);
		ticker_event_note = txtHelper.generateTickerEventNote(ticker_event_id, ctxt, res);
		sqlHelper.updateTickerEvent(String.valueOf(ticker_event_id), null, null, ticker_event_note, null);
		
		// Stoppen der Zeit einrichten
		if (sqlHelper.getGameTimeSoFar(game_id) != null) time_sofar = Long.parseLong(sqlHelper.getGameTimeSoFar(game_id));
		if (sqlHelper.getGameTimeStart(game_id) != null) time_start = Long.parseLong(sqlHelper.getGameTimeStart(game_id));
/** TODO -4- => Wenn Uhr angehalten ist und man drückt TimeOut, läuft die Uhr weiter */
		// Uhr stoppen, falls Uhr noch läuft
		if (time_start != 0) {
			
			elapsedTime = System.currentTimeMillis() - time_start + time_sofar;
			time_sofar = elapsedTime;
    			time_start = 0;
	    		sqlHelper.updateGame(game_id, null, null, null, null, null, null, null, null, time_sofar, time_start, null, null, null, null, null, null, null, null, null, null, null, null);
	    		
		}
		
		if (screenInch > 6) {
			
			fragTickerList.refreshContent(game_id);
			
		} else {
			
			smartActivity.startStop();
			
		}
	}
}
