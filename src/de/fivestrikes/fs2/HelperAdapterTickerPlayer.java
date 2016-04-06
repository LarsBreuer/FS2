package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HelperAdapterTickerPlayer extends CursorAdapter {
	
	HelperSQL sqlHelper=null;
	HelperOnlineGetJSON getJsonHelper=null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	SmartPlayerList playerHelper = null;
	float screenDensity;
	int picSize=0;
	String team_id=null;
	String player_id=null;
	String server_player_id=null;
	String server_team_id=null;
	String player_number=null;
	String player_forename=null;
	String player_surename=null;
	String player_position_first=null;
	String player_position_second=null;
	String player_position_third=null;
	String game_id = null;
	Context ctxt = null;
	
	public HelperAdapterTickerPlayer(Context context, Cursor c, String id) {
		
		super(context, c);
		game_id = id;
		sqlHelper=new HelperSQL(context);
		getJsonHelper = new HelperOnlineGetJSON();
		fctHelper=new HelperFunction();
		screenDensity=fctHelper.getScreenDensity(context);
		lytHelper=new HelperLayout();
		playerHelper= new SmartPlayerList();
		
	}

	@Override
	public void bindView(View row, Context ctxt, Cursor c) {
		
		TickerPlayerHolder holder=(TickerPlayerHolder)row.getTag();
		holder.populateFrom(c, sqlHelper, game_id);
		
	}
	
	@Override
	public View newView(Context ctxt, Cursor c, ViewGroup parent) {
		
		LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.row_player, parent, false);
		TickerPlayerHolder holder=new TickerPlayerHolder(row, ctxt);
		row.setTag(holder);
		return(row);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);  
		ctxt = parent.getContext();
		
		return view;
		
	}
}

class TickerPlayerHolder {

	private TextView tv_player_number = null;
	private TextView tv_player_name = null;
	private TextView tv_player_position = null;
	private ImageView img_player_active = null;
	private ImageButton img_penalty = null;
	Integer yellow_card_id,  two_minutes_id, twoplustwo_id, red_card_id;
	Context ctxt=null;
	Resources res;
    
	TickerPlayerHolder(View row, Context context) {
      
		tv_player_number = (TextView) row.findViewById(R.id.player_number);
		tv_player_name = (TextView) row.findViewById(R.id.player_name);
		tv_player_position = (TextView) row.findViewById(R.id.player_position);
		img_player_active = (ImageView) row.findViewById(R.id.logo_synch);
		img_penalty=(ImageButton) row.findViewById(R.id.btn_synch);
		ctxt = context;
		res = ctxt.getResources(); 
    	
	}
    
	void populateFrom(Cursor c, HelperSQL helper, String game_id) {
		
		String player_id = helper.getPlayerID(c);
		String player_number=helper.getPlayerNumberByID(player_id);
		String player_name=helper.getPlayerForenameByID(player_id)+" "+helper.getPlayerSurenameByID(player_id);
		String player_position_id=helper.getPlayerPositionFirstByID(player_id);
		
		yellow_card_id = res.getInteger(R.integer.yellow_card_id);
		two_minutes_id = res.getInteger(R.integer.two_minutes_id);
		twoplustwo_id = res.getInteger(R.integer.twoplustwo_id);
		red_card_id = res.getInteger(R.integer.red_card_id);
		
		// Ist der Spieler aktuell eingesetzt worden?
/** TODO -4- => Anzeigen, ob der Spieler eingesetzt ist oder nicht */
		// img_player_active.setImageResource(R.drawable.cloud_green);
		
		// Welche Strafen hat der Spieler
		if (helper.count_ticker_activity(game_id, null, player_id, red_card_id, null, null) > 0) {				// Rote Karte eintragen
			
			img_penalty.setImageResource(R.drawable.red_card_mini);
			
	    	} else {
	    		
	    		if (helper.count_ticker_activity(game_id, null, player_id, yellow_card_id, null, null) > 0) {			// Gelbe Karte eintragen
	    			
	    			img_penalty.setImageResource(R.drawable.yellow_card_mini);
	    			
	    			if (helper.count_ticker_activity(game_id, null, player_id, two_minutes_id, null, null) == 1) {			// Gelbe Karte und zwei Minuten eintragen
	    				
	    				img_penalty.setImageResource(R.drawable.yellow_plus_two);
	    				
	    			}
	    			
	    			if (helper.count_ticker_activity(game_id, null, player_id, two_minutes_id, null, null) > 1 || 
	    					helper.count_ticker_activity(game_id, null, player_id, twoplustwo_id, null, null)> 0) {			// Gelbe Karte und zwei Mal zwei Minuten eintragen
	    				
	    				img_penalty.setImageResource(R.drawable.yellow_two_plus_two);
	    				
	    			}
	    			
	    		} else {
	    			
	    			if (helper.count_ticker_activity(game_id, null, player_id, two_minutes_id, null, null) == 1) {
	    				
	    				img_penalty.setImageResource(R.drawable.two_minutes_mini);
	    				
	    			}
	    			
	    			if (helper.count_ticker_activity(game_id, null, player_id, two_minutes_id, null, null) == 2 || 
	    					helper.count_ticker_activity(game_id, null, player_id, twoplustwo_id, null, null) > 0) {			// Gelbe Karte und zwei Mal zwei Minuten eintragen
	    				
	    				img_penalty.setImageResource(R.drawable.two_plus_two_minutes);
	    				
	    			}
	    			
	    		}
	    	}
		
		// Texte setzen
		tv_player_number.setText(player_number);
		tv_player_name.setText(player_name);
		tv_player_position.setText(helper.getPlayerPositionNameByID(player_position_id, ctxt));
  
	}
	
}


