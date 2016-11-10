package de.fivestrikes.fs2;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HelperOnlineGetJSON {
	
	private SharedPreferences mPreferences;
	Context ctxt = null;
	double screenInch = 0;
	String url=null;
	HelperOnlineJSONParser jsonHelper=null;
	HelperSQL sqlHelper=null;
	HelperFunction fctHelper = null;
	JSONArray clubs = null;
	JSONArray teams = null;
	JSONArray players = null;
	Bundle args;
	String club_id=null;
	String server_club_id = null;
	String club_name=null;
	String club_name_short=null;
	String team_id=null;
	String server_team_id = null;
	String gender_id = null;
	String stage_of_life_id = null;
	String level_id = null;
	String team_club_id=null;
	String team_type_id=null;
	String team_club_name=null;
	String player_id=null;
	String player_number=null;
	String server_player_id = null;
	String player_forename = null;
	String player_surename = null;
	String server_player_forename = null;
	String server_player_surename = null;
	String server_player_number = null;
	String player_position_first = null;
	String player_position_second = null;
	String player_position_third = null;
	ListView lv = null;
	ArrayList<String> listClubData = new ArrayList<String>();
	ArrayList<String> listTeamData = new ArrayList<String>();
	ArrayList<String> listPlayerData = new ArrayList<String>();
	private static final String TAG_ID = "id";
	private static final String TAG_CLUBS = "clubs";
	private static final String TAG_CLUB_NAME = "club_name";
	private static final String TAG_CLUB_SHORT = "club_name_short";
	private static final String TAG_TEAMS = "teams";
	private static final String TAG_TEAM_CLUB_ID = "club_id";
	private static final String TAG_TEAM_TYPE_ID = "team_type_id";
	private static final String TAG_TEAM_TYPE_NAME = "team_type_name";
	private static final String TAG_TEAM_CLUB_NAME = "team_club_name";
	private static final String TAG_PLAYERS = "players";
	private static final String TAG_PLAYER_TEAM_ID = "team_id";
	private static final String TAG_PLAYER_NUMBER = "player_number";
	private static final String TAG_PLAYER_FORENAME = "player_forename";
	private static final String TAG_PLAYER_SURENAME = "player_surename";
	private static final String TAG_PLAYER_NAME = "player_name";
	private static final String TAG_PLAYER_POSITION_FIRST = "player_position_first";
	private static final String TAG_PLAYER_POSITION_SECOND = "player_position_second";
	private static final String TAG_PLAYER_POSITION_THIRD = "player_position_third";
	TabFragTeamOnline fragTeamOnline;
	TabFragTeamList fragTeamList;
	TabFragPlayerSynch fragPlayerSync;

/*
 * 
 * Suche nach Mannschaft und synchronisiere Mannschaft mit lokaler Mannschaft
 *
 */
	
	public void synchTeam(String team_name, final Context context, String synch_team_id, final FragmentManager fragmentManager, Bundle synch_args) {
		
/* Helper definieren */

		fctHelper = new HelperFunction();
		jsonHelper = new HelperOnlineJSONParser();
		sqlHelper=new HelperSQL(context);
		
/* Bildschirmgröße ermitteln */

		screenInch = fctHelper.getScreenInch(context);		

/* Daten aus Activity laden */ 
		
		team_id = synch_team_id;
		args = synch_args;
	        
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
		
		mPreferences = context.getSharedPreferences("CurrentUser", context.MODE_PRIVATE);
		if (team_name != null){
			url = context.getResources().getString(R.string.url)+"teams.json"+"?auth_token=" + mPreferences.getString("AuthToken", "") + "&team_name="+team_name;
		}
		
		// Leerzeichen in dem url-String bearbeiten
		url = fctHelper.revise(url);
		
		// Hashmap for ListView
		final ArrayList<HashMap<String, String>> teamList = new ArrayList<HashMap<String, String>>();

		// getting JSON string from URL
		Log.v("HelperOnlineGetJSON url 0", url);
		JSONObject json = jsonHelper.getJSONFromUrl(url);
/** TODO -0- => Fehlermeldung, falls json null ist */
		try {

	            // Getting Array of Contacts
	            teams = json.getJSONArray(TAG_TEAMS);
	             
	            // looping through All Contacts
	            for(int i = 0; i < teams.length(); i++) {

	            		JSONObject c = teams.getJSONObject(i);
	                 
	            		// Storing each json item in variable
	            		String id = c.getString(TAG_ID);
	            		String club_id = c.getString(TAG_TEAM_CLUB_ID);
	            		String team_type_id = c.getString(TAG_TEAM_TYPE_ID);
	            		String team_type_name = sqlHelper.getTeamTypeName(team_type_id, context);
	            		String name = c.getString(TAG_TEAM_CLUB_NAME);
	            		
	            		// creating new HashMap
	            		HashMap<String, String> map = new HashMap<String, String>();

	            		// adding each child node to HashMap key => value
	            		map.put(TAG_ID, id);
	            		map.put(TAG_TEAM_CLUB_ID, club_id);
	            		map.put(TAG_TEAM_TYPE_ID, team_type_id);
	            		map.put(TAG_TEAM_TYPE_NAME, team_type_name);
	            		map.put(TAG_TEAM_CLUB_NAME, name);
	            		
	            		// adding HashList to ArrayList
	            		teamList.add(map);
	            		Log.v("HelperOnlineGetJSON teamList", String.valueOf(teamList));
	            }
	            
		} catch (JSONException e) {
			e.printStackTrace();
		}

/* Erstellung der Mannschaftsliste und Auswahl einer Mannschaft definieren */
		
		final ListAdapter adapter = new SimpleAdapter(context, teamList, R.layout.row_team_raw, 
				new String[] { TAG_TEAM_CLUB_NAME, TAG_TEAM_TYPE_NAME }, new int[] {
	                        R.id.rowClubName, R.id.rowTeamType });
		
		if(screenInch > 6) {
			
			fragTeamOnline = (TabFragTeamOnline) fragmentManager.findFragmentById(R.id.frag_team_edit);
			fragTeamList = (TabFragTeamList) fragmentManager.findFragmentById(R.id.frag_team_list);
			
			fragTeamOnline.getActivity().runOnUiThread(
					new Runnable() {
						@Override
						public void run() {
							fragTeamOnline.setListAdapter(adapter);
							lv = fragTeamOnline.getListView(); 
							
/** TODO -1- => Testen, ob das auch bei Tablet funktioniert */
/** TODO -0- => Gleicher Code bei Tablet und Smartphone. Kann man den ausgliedern? */
							
							if (lv != null) {
								Log.v("HelperOnlineGetJSON lv 3", String.valueOf(lv));
								lv.setOnItemClickListener(new OnItemClickListener() {
									
									@Override
							            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Log.v("HelperOnlineGetJSON", "1 aufgerufen");
										// getting values from selected ListItem
										server_team_id = teamList.get(position).get(TAG_ID);
										server_club_id = teamList.get(position).get(TAG_TEAM_CLUB_ID);
										team_type_id = teamList.get(position).get(TAG_TEAM_TYPE_ID);
										club_name = teamList.get(position).get(TAG_TEAM_CLUB_NAME);
										club_id=sqlHelper.getClubIDByClubName(club_name);
										listClubData = getClubArray(server_club_id, null, context.getApplicationContext());
					Log.v("HelperOnlineGetJSON listClubData", String.valueOf(listClubData));
										if (listClubData.size() > 0) {
											
											club_name = listClubData.get(1);
											club_name_short = listClubData.get(2);
											
										}
										
										// Prüfen, ob anhand der club_id und der Mannschaftsart schon eine bestehende Mannschaft ausgewählt werden kann
										if (team_id == null) {
											
											// ... nur die Server-Daten übertragen...
											team_id = sqlHelper.getTeamIDByClubIDAndTeamTypeID(club_id, team_type_id);
											
										} 

					/* Überprüfen, ob Verein mit gleichem Namen bereits lokal vorhanden ist */
										
										if (club_id != null) {														// Falls der Verein schon angelegt wurde
											if (sqlHelper.getServerClubIDByClubName(club_name) == null) {		// Falls die Server ID noch nicht übertragen wurde...
												
												// ... Server ID auf den lokalen Verein übertragen
												sqlHelper.updateClub(club_id, Integer.parseInt(server_club_id), club_name, club_name_short);
												
												// ... Server ID auf alle Mannschaften mit dem gleichen Vereinsnamen übertragen
												sqlHelper.updateAllTeamsServerClubID(club_id, Integer.parseInt(server_club_id));
																
											}
										} else {																	// Falls der Verein noch nicht angelegt wurde
											Log.v("HelperOnlineGetJSON", "Verein anlegen");
											sqlHelper.insertClub(club_name, club_name_short);					// Verein anlegen
											club_id=sqlHelper.getLastClubID();
															
										}
															
						/* Teamdaten übertragen */
															
										if (club_id != null && team_id != null) {									// Falls die Mannschaft schon angelegt wurde

											sqlHelper.updateTeam(team_id, server_team_id, club_id, server_club_id, team_type_id);		// ... Server Team ID auf die Mannschaft übertragen
																
										} else {																	// Falls noch keine Mannschaft existiert...
															
											sqlHelper.insertTeam(server_team_id, club_id, server_club_id, team_type_id);	// ... neue Mannschaft anlegen
											team_id=sqlHelper.getLastTeamID();
															
										}
															
						/* Spielerdaten übertragen */
										
										// DialogBox einrichten
								   		final Dialog dialog = new Dialog(context);
								   		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
								   		dialog.setContentView(R.layout.custom_dialog);

								   		// Texte setzen
								   		TextView title = (TextView) dialog.findViewById(R.id.title);
								   		TextView text = (TextView) dialog.findViewById(R.id.text);
								   		title.setText(R.string.text_player_load_server_title);
								   		text.setText(R.string.text_player_load_server);
								   				
								   		// Button definieren
								   		LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
								   		lyt_button3.removeAllViews();

								   		Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
								   		dialogButton1.setText(R.string.yes);
								   	
								   		dialogButton1.setOnClickListener(new View.OnClickListener() {
								   		
								   			@Override
								   			public void onClick(View v) {
								   			
								   				dialog.dismiss();
								   			
								   				loadPlayerFromServer(team_id, server_team_id, context.getApplicationContext());
												
												if(screenInch > 6) {
													
													Toast.makeText(((FragmentActivity) context).getBaseContext(), context.getString(R.string.text_synch_complete), Toast.LENGTH_LONG).show();
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
													
													fragTeamList.refreshContent(team_id, args);
													
													FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
													TabFragTeamEdit fragment = new TabFragTeamEdit();
													fragment.setArguments(args);
													fragmentTransaction.replace(R.id.frag_team_edit, fragment);
													fragmentTransaction.commit();
													
												} else {
													
													Toast.makeText(((ListActivity) context).getBaseContext(), context.getString(R.string.text_synch_complete), Toast.LENGTH_LONG).show();
													((ListActivity) context).finish();
													
												}  	
								   						
								   			}
								   		});
								   				
								   		Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
								   		dialogButton2.setText(R.string.no);
								   			
								   		dialogButton2.setOnClickListener(new View.OnClickListener() {
								   			@Override
								   			public void onClick(View v) {
								   			
								   				dialog.dismiss();
								   				
								   				if(screenInch > 6) {
													
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
													
													FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
													TabFragTeamEdit fragment = new TabFragTeamEdit();
													fragment.setArguments(args);
													fragmentTransaction.replace(R.id.frag_team_edit, fragment);
													fragmentTransaction.commit();
													
												} else {
													
													((ListActivity) context).finish();
													
												}
								   						
								   			}
								   		});

								   		dialog.show();
								   		// Ende Dialogbox einrichten
											
									}
								});			
							}
						}
			});

		} else {
Log.v("HelperOnlineGetJSON adapter", String.valueOf(adapter));
			((ListActivity) context).runOnUiThread(
					new Runnable() {
						@Override
						public void run() {
							((ListActivity) context).setListAdapter(adapter);
							lv = ((ListActivity) context).getListView();
							Log.v("HelperOnlineGetJSON lv 1", String.valueOf(lv));
							
							if (lv != null) {
								Log.v("HelperOnlineGetJSON lv 3", String.valueOf(lv));
								lv.setOnItemClickListener(new OnItemClickListener() {
									
									@Override
							            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Log.v("HelperOnlineGetJSON", "1 aufgerufen");
										// getting values from selected ListItem
										server_team_id = teamList.get(position).get(TAG_ID);
										server_club_id = teamList.get(position).get(TAG_TEAM_CLUB_ID);
										team_type_id = teamList.get(position).get(TAG_TEAM_TYPE_ID);
										club_name = teamList.get(position).get(TAG_TEAM_CLUB_NAME);
										club_id=sqlHelper.getClubIDByClubName(club_name);
										listClubData = getClubArray(server_club_id, null, context.getApplicationContext());
					Log.v("HelperOnlineGetJSON listClubData", String.valueOf(listClubData));
										if (listClubData.size() > 0) {
											
											club_name = listClubData.get(1);
											club_name_short = listClubData.get(2);
											
										}
										
										// Prüfen, ob anhand der club_id und der Mannschaftsart schon eine bestehende Mannschaft ausgewählt werden kann
										if (team_id == null) {
											
											// ... nur die Server-Daten übertragen...
											team_id = sqlHelper.getTeamIDByClubIDAndTeamTypeID(club_id, team_type_id);
											
										} 

					/* Überprüfen, ob Verein mit gleichem Namen bereits lokal vorhanden ist */
										
										if (club_id != null) {														// Falls der Verein schon angelegt wurde
											if (sqlHelper.getServerClubIDByClubName(club_name) == null) {		// Falls die Server ID noch nicht übertragen wurde...
												
												// ... Server ID auf den lokalen Verein übertragen
												sqlHelper.updateClub(club_id, Integer.parseInt(server_club_id), club_name, club_name_short);
												
												// ... Server ID auf alle Mannschaften mit dem gleichen Vereinsnamen übertragen
												sqlHelper.updateAllTeamsServerClubID(club_id, Integer.parseInt(server_club_id));
																
											}
										} else {																	// Falls der Verein noch nicht angelegt wurde
											Log.v("HelperOnlineGetJSON", "Verein anlegen");
											sqlHelper.insertClub(club_name, club_name_short);					// Verein anlegen
											club_id=sqlHelper.getLastClubID();
															
										}
															
						/* Teamdaten übertragen */
															
										if (club_id != null && team_id != null) {									// Falls die Mannschaft schon angelegt wurde

											sqlHelper.updateTeam(team_id, server_team_id, club_id, server_club_id, team_type_id);		// ... Server Team ID auf die Mannschaft übertragen
																
										} else {																	// Falls noch keine Mannschaft existiert...
															
											sqlHelper.insertTeam(server_team_id, club_id, server_club_id, team_type_id);	// ... neue Mannschaft anlegen
											team_id=sqlHelper.getLastTeamID();
															
										}
															
						/* Spielerdaten übertragen */
										
										// DialogBox einrichten
								   		final Dialog dialog = new Dialog(context);
								   		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
								   		dialog.setContentView(R.layout.custom_dialog);

								   		// Texte setzen
								   		TextView title = (TextView) dialog.findViewById(R.id.title);
								   		TextView text = (TextView) dialog.findViewById(R.id.text);
								   		title.setText(R.string.text_player_load_server_title);
								   		text.setText(R.string.text_player_load_server);
								   				
								   		// Button definieren
								   		LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
								   		lyt_button3.removeAllViews();

								   		Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
								   		dialogButton1.setText(R.string.yes);
								   	
								   		dialogButton1.setOnClickListener(new View.OnClickListener() {
								   		
								   			@Override
								   			public void onClick(View v) {
								   			
								   				dialog.dismiss();
								   			
								   				loadPlayerFromServer(team_id, server_team_id, context.getApplicationContext());
												
												if(screenInch > 6) {
													
													Toast.makeText(((FragmentActivity) context).getBaseContext(), context.getString(R.string.text_synch_complete), Toast.LENGTH_LONG).show();
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
													
													fragTeamList.refreshContent(team_id, args);
													
													FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
													TabFragTeamEdit fragment = new TabFragTeamEdit();
													fragment.setArguments(args);
													fragmentTransaction.replace(R.id.frag_team_edit, fragment);
													fragmentTransaction.commit();
													
												} else {
													
													Toast.makeText(((ListActivity) context).getBaseContext(), context.getString(R.string.text_synch_complete), Toast.LENGTH_LONG).show();
													((ListActivity) context).finish();
													
												}  	
								   						
								   			}
								   		});
								   				
								   		Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
								   		dialogButton2.setText(R.string.no);
								   			
								   		dialogButton2.setOnClickListener(new View.OnClickListener() {
								   			@Override
								   			public void onClick(View v) {
								   			
								   				dialog.dismiss();
								   				
								   				if(screenInch > 6) {
													
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
													
													FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
													TabFragTeamEdit fragment = new TabFragTeamEdit();
													fragment.setArguments(args);
													fragmentTransaction.replace(R.id.frag_team_edit, fragment);
													fragmentTransaction.commit();
													
												} else {
													
													((ListActivity) context).finish();
													
												}
								   						
								   			}
								   		});

								   		dialog.show();
								   		// Ende Dialogbox einrichten
											
									}
								});			
							}
						}
			});
			
		}
		
Log.v("HelperOnlineGetJSON lv 2", String.valueOf(lv));
		
		
	}

/*
 * 
 * Suche nach Mannschaft und synchronisiere Mannschaft mit lokaler Mannschaft
 *
 */
	
	public void synchPlayer(String synch_server_team_id, final Context context, final FragmentManager fragmentManager, Bundle synch_args) {
		
		/* Daten aus Activity laden */ 
		
		server_team_id = synch_server_team_id;
		args = synch_args;
	        
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
		
/* Helper definieren */

		fctHelper = new HelperFunction();
		jsonHelper = new HelperOnlineJSONParser();
		sqlHelper=new HelperSQL(context);
		
		mPreferences = context.getSharedPreferences("CurrentUser", context.MODE_PRIVATE);
		url=context.getResources().getString(R.string.url)+"players.json"+"?auth_token=" + mPreferences.getString("AuthToken", "")+"&team_id="+server_team_id;
		
		// Leerzeichen in dem url-String bearbeiten
		url = fctHelper.revise(url);
				
		// Hashmap for ListView
		final ArrayList<HashMap<String, String>> playerList = new ArrayList<HashMap<String, String>>();
		
		// getting JSON string from URL
		Log.v("PlayerSynch url", url);
		JSONObject json = jsonHelper.getJSONFromUrl(url);
		
		try {

	            // Getting Array of Contacts
	            players = json.getJSONArray(TAG_PLAYERS);
	             
	            // looping through All Contacts
	            for(int i = 0; i < players.length(); i++) {
	            	
	            		JSONObject c = players.getJSONObject(i);
		                 
	            		// Storing each json item in variable
	            		String id = c.getString(TAG_ID);
	            		String player_team_id = c.getString(TAG_PLAYER_TEAM_ID);
	            		String player_number = c.getString(TAG_PLAYER_NUMBER);
	            		String player_forename = c.getString(TAG_PLAYER_FORENAME);
	            		String player_surename = c.getString(TAG_PLAYER_SURENAME);
	            		String player_position_first = c.getString(TAG_PLAYER_POSITION_FIRST);
	            		String player_position_second = c.getString(TAG_PLAYER_POSITION_SECOND);
	            		String player_position_third = c.getString(TAG_PLAYER_POSITION_THIRD);
	            		String player_name = player_forename + " " + player_surename;
            		
	            		// creating new HashMap
	            		HashMap<String, String> map = new HashMap<String, String>();

	            		// adding each child node to HashMap key => value
	            		map.put(TAG_ID, id);
	            		map.put(TAG_PLAYER_TEAM_ID, player_team_id);
	            		map.put(TAG_PLAYER_NUMBER, player_number);
	            		map.put(TAG_PLAYER_FORENAME, player_forename);
	            		map.put(TAG_PLAYER_SURENAME, player_surename);
	            		map.put(TAG_PLAYER_POSITION_FIRST, player_position_first);
	            		map.put(TAG_PLAYER_POSITION_SECOND, player_position_second);
	            		map.put(TAG_PLAYER_POSITION_THIRD, player_position_third);
	            		map.put(TAG_PLAYER_NAME, player_name);
            		
	            		// adding HashList to ArrayList
	            		playerList.add(map);
            		
	            }
            
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
/* Erstellung der Spielerliste und Auswahl eines Spielers definieren */
		
		final ListAdapter adapter = new SimpleAdapter(context, playerList, R.layout.row_player_raw, 
				new String[] { TAG_PLAYER_NUMBER, TAG_PLAYER_NAME }, new int[] {
	                        R.id.player_number, R.id.player_name });
		
		if(screenInch > 6) {
			
			fragPlayerSync = (TabFragPlayerSynch) fragmentManager.findFragmentById(R.id.frag_team_edit);
			fragTeamList = (TabFragTeamList) fragmentManager.findFragmentById(R.id.frag_team_list);
			fragPlayerSync.getActivity().runOnUiThread(
					new Runnable() {
						@Override
						public void run() {
							fragPlayerSync.setListAdapter(adapter);
							lv = fragPlayerSync.getListView();
						}
			});
			
		} else {
			
			((ListActivity) context).runOnUiThread(
					new Runnable() {
						@Override
						public void run() {
							((ListActivity) context).setListAdapter(adapter);
							lv = ((ListActivity) context).getListView();
						}
			});
			
		}
		
		if (lv != null) {
			lv.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
		            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					
					// Spielerdaten aus der LitItem ziehen
					server_player_id = playerList.get(position).get(TAG_ID);
					player_number = playerList.get(position).get(TAG_PLAYER_NUMBER);
					player_forename = playerList.get(position).get(TAG_PLAYER_FORENAME);
					player_surename = playerList.get(position).get(TAG_PLAYER_SURENAME);
					player_position_first = playerList.get(position).get(TAG_PLAYER_POSITION_FIRST);
					player_position_second = playerList.get(position).get(TAG_PLAYER_POSITION_SECOND);
					player_position_third = playerList.get(position).get(TAG_PLAYER_POSITION_THIRD);
					
					// Daten übermitteln
					// Wenn der Spieler Server Cursor bereits existiert, den Spieler nicht synchronisieren
					if (sqlHelper.getPlayerCursorByServerID(server_player_id) != null) {
						
						// DialogBox einrichten
						final Dialog dialog = new Dialog(ctxt);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.custom_dialog);

						// Texte setzen
						TextView title = (TextView) dialog.findViewById(R.id.title);
						TextView text = (TextView) dialog.findViewById(R.id.text);
						title.setText(R.string.synchro);
						text.setText(R.string.text_player_server_id);
						
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
								if(screenInch > 6) {
									
									FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
									TabFragPlayerEdit fragment = new TabFragPlayerEdit();
									fragment.setArguments(args);
									fragmentTransaction.replace(R.id.frag_team_edit, fragment);
									fragmentTransaction.commit();
									
								} else {
									
									((ListActivity) context).finish();
									
								}	
							}
						});

						dialog.show();
						// Ende Nachrichtenbox
						
					} else {
						
						if(screenInch > 6) {
							
							Bundle args = new Bundle();
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
							
							fragTeamList.refreshContent(team_id, args);
							
							FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
							TabFragPlayerEdit fragment = new TabFragPlayerEdit();
							fragment.setArguments(args);
							fragmentTransaction.replace(R.id.frag_team_edit, fragment);
							fragmentTransaction.commit();
							
						} else {
							
							Log.v("HelperOnlineGetJSON", "Spieler synchronisiert");
							Toast.makeText(((ListActivity) context).getBaseContext(), context.getString(R.string.text_synch_complete), Toast.LENGTH_LONG).show();
							Intent i=new Intent();
							i.putExtra("ServerPlayerID", server_player_id);
							i.putExtra("PlayerNumber", player_number);
							i.putExtra("PlayerForename", player_forename);
							i.putExtra("PlayerSurename", player_surename);
							i.putExtra("PlayerPositionFirst", player_position_first);
							i.putExtra("PlayerPositionSecond", player_position_second);
							i.putExtra("PlayerPositionThird", player_position_third);
							((ListActivity) context).setResult(((ListActivity) context).RESULT_OK, i);
							((ListActivity) context).finish();
							
						}	
						
					}
				}
			});		
		}
		
	}

/*
 * 
 * Daten des Vereins anhand der Vereins-ID oder des Vereinsnamens vom Server laden
 *
 */
	
	public ArrayList getClubArray(String server_club_id, String club_name, Context context) {
		
		jsonHelper = new HelperOnlineJSONParser();
		fctHelper=new HelperFunction();
		
		mPreferences = context.getSharedPreferences("CurrentUser", context.MODE_PRIVATE);
		if(server_club_id!=null){
			url=context.getString(R.string.url)+"clubs.json"+"?auth_token=" + mPreferences.getString("AuthToken", "")+"&club_id="+server_club_id;
		}
		if(club_name!=null){
			url=context.getString(R.string.url)+"clubs.json"+"?auth_token=" + mPreferences.getString("AuthToken", "")+"&club_name="+club_name;
		}
		
		// Leerzeichen in dem url-String bearbeiten
		url = fctHelper.revise(url);
Log.v("HelperOnlineGetJSON url 1", String.valueOf(url));
		// getting JSON string from URL
		JSONObject json = jsonHelper.getJSONFromUrl(url);
	 
		try {

	            clubs = json.getJSONArray(TAG_CLUBS);
	            	JSONObject c = clubs.getJSONObject(0);
	            	server_club_id = c.getString(TAG_ID);
	            	club_name = c.getString(TAG_CLUB_NAME);
	            	club_name_short = c.getString(TAG_CLUB_SHORT);
	            	
	            	listClubData.add(server_club_id);
	            	listClubData.add(club_name);
	            	listClubData.add(club_name_short);
Log.v("HelperOnlineGetJSON listClubData", String.valueOf(listClubData));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return(listClubData);
		
	}
	
/*
 * 
 * Mannschaftsdaten anhand der Team-ID und des Mannschaftstyps vom Server laden
 *
 */
	
	public ArrayList getTeamArray(String server_club_id, String team_type, Context context) {
		
		jsonHelper = new HelperOnlineJSONParser();
		fctHelper=new HelperFunction();
		
		mPreferences = context.getSharedPreferences("CurrentUser", context.MODE_PRIVATE);
		url=context.getString(R.string.url)+"teams.json"+"?auth_token=" + mPreferences.getString("AuthToken", "")+"&club_id="+server_club_id+"&team_type="+team_type;
		
		// Leerzeichen in dem url-String bearbeiten
		url = fctHelper.revise(url);

		// getting JSON string from URL
		JSONObject json = jsonHelper.getJSONFromUrl(url);
	 
		try {

	            teams = json.getJSONArray(TAG_TEAMS);
	            	JSONObject c = teams.getJSONObject(0);
	            	team_id = c.getString(TAG_ID);
	            	team_club_id = c.getString(TAG_TEAM_CLUB_ID);
	            	team_type_id = c.getString(TAG_TEAM_TYPE_ID);
	            	team_club_name = c.getString(TAG_TEAM_CLUB_NAME);
	            	listTeamData.add(team_id);
	            	listTeamData.add(team_club_id);
	            	listTeamData.add(team_type_id);
	            	listTeamData.add(team_club_name);
	            		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return(listTeamData);
		
	}
	
/*
 * 
 * Spielerdaten vom Server laden und als lokale Spieler anlegen bzw. lokale Spieler aktualisieren
 *
 */

	public void loadPlayerFromServer(String team_id, String server_team_id, Context context) {
		
		jsonHelper = new HelperOnlineJSONParser();
		sqlHelper=new HelperSQL(context);
		fctHelper=new HelperFunction();
		JSONArray players = null;
		url=context.getString(R.string.url)+"players.json"+"?team_id="+server_team_id;
		
		// Leerzeichen in dem url-String bearbeiten
		url = fctHelper.revise(url);
		
		Log.v("HelperOnlineGetJSON url 2", url);
		JSONObject json = jsonHelper.getJSONFromUrl(url);
		
		try {
			
			// Getting Array of Contacts
			players = json.getJSONArray(TAG_PLAYERS);
      	             
			// looping through All Contacts
			for(int i = 0; i < players.length(); i++){
				
				JSONObject cPLayer = players.getJSONObject(i);
      	                 
				// Storing each json item in variable
				server_player_id = cPLayer.getString(TAG_ID);
				server_player_forename = cPLayer.getString(TAG_PLAYER_FORENAME);
				server_player_surename = cPLayer.getString(TAG_PLAYER_SURENAME);
				server_player_number = cPLayer.getString(TAG_PLAYER_NUMBER);
				player_position_first = cPLayer.getString(TAG_PLAYER_POSITION_FIRST);
				player_position_second = cPLayer.getString(TAG_PLAYER_POSITION_SECOND);
				player_position_third = cPLayer.getString(TAG_PLAYER_POSITION_THIRD);
				player_id = sqlHelper.getPlayerIDByPlayerNameAndTeamID(server_player_forename, server_player_surename, team_id);
							
				//Abfrage, ob der Spieler bereits lokal existiert
				if(player_id!=null){	
					
					// Falls Spieler bereits existiert übertrage lediglich die Online IDs
					sqlHelper.updatePlayerOnline(player_id, server_player_id, server_team_id);
					
				} else {
					
					// Falls Spieler noch nicht vorhanden ist => Neuen Spieler einrichten
					sqlHelper.insertPlayer(server_player_id, team_id, server_team_id, server_player_number, server_player_forename, server_player_surename, player_position_first, player_position_second, player_position_third);
					
				}
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
/*
 * 
 * Daten des Vereins anhand der Vereins-ID oder des Vereinsnamens vom Server laden
 *
 */

	public ArrayList getPlayerArray(String server_team_id, String player_forename, String player_surename, Context context) {
		
		jsonHelper = new HelperOnlineJSONParser();
		fctHelper=new HelperFunction();
		mPreferences = context.getSharedPreferences("CurrentUser", context.MODE_PRIVATE);
		
		if(server_team_id!=null){
			if(player_forename!=null  || player_surename!=null){
				if (player_forename==null) {player_forename="";}
				if (player_surename==null) {player_surename="";}
				url=context.getString(R.string.url)+"players.json"+"?auth_token=" + mPreferences.getString("AuthToken", "")+"&team_id="+server_team_id+"&player_forename="+player_forename+"&player_surename="+player_surename;
			} else {
				url=context.getString(R.string.url)+"players.json"+"?auth_token=" + mPreferences.getString("AuthToken", "")+"&team_id="+server_team_id;
			}
		}
		
		Log.v("HelperOnlineGetJSON url 3", url);
		
		// Leerzeichen in dem url-String bearbeiten
		url = fctHelper.revise(url);

		// getting JSON string from URL
		JSONObject json = jsonHelper.getJSONFromUrl(url);
	 
		try {

	            players = json.getJSONArray(TAG_PLAYERS);
	            	JSONObject c = players.getJSONObject(0);
	            player_id = c.getString(TAG_ID);
	            team_id = c.getString(TAG_PLAYER_TEAM_ID);
	            player_number = c.getString(TAG_PLAYER_NUMBER);
	            player_forename = c.getString(TAG_PLAYER_FORENAME);
	            player_surename = c.getString(TAG_PLAYER_SURENAME);
	            player_position_first = c.getString(TAG_PLAYER_POSITION_FIRST);
	            player_position_second = c.getString(TAG_PLAYER_POSITION_SECOND);
	            player_position_third = c.getString(TAG_PLAYER_POSITION_THIRD);
	            	listPlayerData.add(player_id);
	            	listPlayerData.add(team_id);
	            	listPlayerData.add(player_number);
	            	listPlayerData.add(player_forename);
	            	listPlayerData.add(player_surename);
	            	listPlayerData.add(player_position_first);
	            	listPlayerData.add(player_position_first);
	            	listPlayerData.add(player_position_third);
	            		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return(listPlayerData);
		
	}
}
