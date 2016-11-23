package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.Activity;
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
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartTeamList extends ListActivity {

	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	HelperSQL sqlHelper=null;
	double screenInch = 0;
	float screenDensity;
	Cursor model=null;
	HelperAdapterTeam adapter=null;
	String team_id=null;
	Bundle args;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
/* Helper generieren */
		
		fctHelper=new HelperFunction();
		lytHelper=new HelperLayout();
		sqlHelper=new HelperSQL(this);
		
/* Bildschirmgröße ermitteln */
		
		// Auflösung
		screenDensity=fctHelper.getScreenDensity(SmartTeamList.this);
		
		// Inch
		screenInch=fctHelper.getScreenInch(SmartTeamList.this);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Teamliste einrichten */
		
		refreshContent();		
		
	}
	
/* Inhalt neu laden, wenn Activity ernuet aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent();
	    	
	}
	
	public void refreshContent() {
		
		model=sqlHelper.getAllTeamCursor();
		startManagingCursor(model);
		adapter=new HelperAdapterTeam(SmartTeamList.this, model, null, null);
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);
		        
	}
	
/* Teamauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		team_id = String.valueOf(id);
		args = new Bundle();
		args.putString("TeamID", team_id);
		
		// Werte übermitteln
		if (sqlHelper.getTeamServerTeamID(team_id)!=null) {
			
			Intent i=new Intent(SmartTeamList.this, SmartPlayerList.class);
			i.putExtras(args);
			startActivity(i); 
			
		} else {
			
			Intent i=new Intent(SmartTeamList.this, SmartTeamEdit.class);
			i.putExtras(args);
			startActivity(i);
			
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
      	  	Intent i = new Intent(SmartTeamList.this, SmartListWithText.class);
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