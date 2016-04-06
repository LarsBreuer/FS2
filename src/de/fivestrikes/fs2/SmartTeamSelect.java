package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartTeamSelect extends ListActivity {

	HelperSQL sqlHelper=null;
	Cursor model=null;
	HelperAdapterTeam adapter=null;
	String game_id=null;
	String team_id=null;
	String home_id = null;
	String away_id = null;
	Integer home_or_away = null;
	Bundle args;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
/* Helper generieren */
		
		sqlHelper = new HelperSQL(this);
		
/* Daten aus Activity laden */ 
		
		args = getIntent().getExtras();
		home_or_away = args.getInt("HomeOrAway");
		game_id = args.getString("GameID");
		home_id = args.getString("HomeID");
		away_id = args.getString("AwayID");
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Teamliste einrichten */
		
		model=sqlHelper.getAllTeamCursor();
		startManagingCursor(model);
		adapter=new HelperAdapterTeam(SmartTeamSelect.this, model, null);
		setListAdapter(adapter);		
		
	}
	
	
/* Teamauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		// Mannschaftsdaten ermitteln
		String team_id=String.valueOf(id);
				
		// Daten übermitteln
		Intent i=new Intent();
		args.putString("TeamID", team_id);
		
		if (home_or_away != null) {
			
			// Abfragen, ob ausgewählte Heim- und Auswärtsmannschaft identisch sind
			Boolean team_equal = false;
			
			if (home_or_away.equals(1)) {
				if (team_id.equals(away_id) && away_id != null) {
					
					team_equal = true;
					
				}
			}
			
			if (home_or_away.equals(0)) {
				if (team_id.equals(home_id) && home_id != null) {
				
					team_equal = true;
					
				}
			}
			
			// Mannschaft abspeichern, wenn Heim- nicht gleich Auswärtsmannschaft, ansonsten Nachricht, dass nicht gespeichert wird
			if (team_equal == true) {
				
				// DialogBox einrichten
				final Dialog dialog = new Dialog(this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.custom_dialog);

				// Texte setzen
				TextView title = (TextView) dialog.findViewById(R.id.title);
				TextView text = (TextView) dialog.findViewById(R.id.text);
				title.setText(R.string.text_selection_not_possible);
				text.setText(R.string.text_opponent_selection);
				
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
				
				i.putExtras(args);
				setResult(RESULT_OK, i);
				finish();
				
			}
			
		} else {
			
			setResult(RESULT_OK, i);
			finish();
			
		}       
	}
	
/* Action Bar einrichten */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
		
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
        
        case android.R.id.home: 
      	  	onBackPressed();
            break;
            
        case 0:
		// Füge neue Mannschaft ein
      	  	Intent i = new Intent(SmartTeamSelect.this, SmartListWithText.class);
      	  	Bundle args = new Bundle();
  	  	args.putString("InputString", "TeamAdd");
		i.putExtras(args);
		startActivity(i);

        default:
      	  	return super.onOptionsItemSelected(item);
      	  	
        }
        
        return true;
        
    }
	
	private void CreateMenu(Menu menu) {
	
		MenuItem mnu1 = menu.add(0, 0, 0, "Item 1"); {

			mnu1.setAlphabeticShortcut('a');
			mnu1.setIcon(R.drawable.add);
			mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
		}
		
	}
	    
}