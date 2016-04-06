package de.fivestrikes.fs2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SmartTickerMenu extends Activity {

	HelperLayout lytHelper = null;
	View view = null;
	String game_id = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticker_menu);
		view = findViewById(android.R.id.content);
		SmartTickerActivity activity = (SmartTickerActivity) this.getParent();
		
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		game_id=getIntent().getStringExtra("GameID");
		
/* Layout festlegen */
		
		lytHelper.lytTickerMenu(game_id, view, null, null, activity);		

	}	    
}