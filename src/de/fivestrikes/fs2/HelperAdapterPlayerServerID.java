package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Configuration;
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

public class HelperAdapterPlayerServerID extends CursorAdapter {
	
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
	ArrayList<String> listPlayerData = new ArrayList<String>();
	Context ctxt = null;
	
	public HelperAdapterPlayerServerID(Context context, Cursor c) {
		
		super(context, c);
		sqlHelper=new HelperSQL(context);
		getJsonHelper = new HelperOnlineGetJSON();
		fctHelper=new HelperFunction();
		screenDensity=fctHelper.getScreenDensity(context);
		lytHelper=new HelperLayout();
		playerHelper= new SmartPlayerList();
		
	}

	@Override
	public void bindView(View row, Context ctxt, Cursor c) {
		
		PlayerHolder holder=(PlayerHolder)row.getTag();
		holder.populateFrom(c, sqlHelper);
		
	}
	
	@Override
	public View newView(Context ctxt, Cursor c, ViewGroup parent) {
		
		LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.row_player_server_id, parent, false);
		PlayerHolder holder=new PlayerHolder(row, ctxt);
		row.setTag(holder);
		return(row);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);  
		ctxt = parent.getContext();
		if (position % 2 == 1) {
			view.setBackgroundColor(ctxt.getResources().getColor(R.color.rowdark));
		} else {
			view.setBackgroundColor(ctxt.getResources().getColor(R.color.rowlight));
		}
		
		return view;
		
	}
}

class PlayerHolder {

	private TextView tv_player_number=null;
	private TextView tv_player_name=null;
	private TextView tv_player_position=null;
	private TextView tv_player_team=null;
	Context ctxt=null;
    
	PlayerHolder(View row, Context context) {
      
		tv_player_number = (TextView)row.findViewById(R.id.player_number);
		tv_player_name = (TextView)row.findViewById(R.id.player_name);
		tv_player_position = (TextView)row.findViewById(R.id.player_position);
		tv_player_team = (TextView)row.findViewById(R.id.player_team);
		ctxt = context;
    	
	}
    
	void populateFrom(Cursor c, HelperSQL helper) {
		
		String player_id = helper.getPlayerID(c);
		String player_number=helper.getPlayerNumberByID(player_id);
		String player_name=helper.getPlayerForenameByID(player_id)+" "+helper.getPlayerSurenameByID(player_id);
		String player_position_id=helper.getPlayerPositionFirstByID(player_id);
		String player_team=helper.getPlayerTeamIDByID(player_id);
		
		// Texte setzen
		tv_player_number.setText(player_number);
		tv_player_name.setText(player_name);
		tv_player_position.setText(helper.getPlayerPositionNameByID(player_position_id, ctxt));
		tv_player_team.setText(player_team);
  
	}
	
}


