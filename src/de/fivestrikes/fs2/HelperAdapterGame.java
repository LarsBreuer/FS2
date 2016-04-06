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

public class HelperAdapterGame extends CursorAdapter {
	
	HelperSQL sqlHelper=null;
	HelperFunction fctHelper = null;
	HelperLayout lytHelper = null;
	float screenDensity;
	int picSize=0;
	String game_id=null;
	Context ctxt = null;
	
	public HelperAdapterGame(Context context, Cursor c, String id) {
		
		super(context, c);
		game_id = id;
		sqlHelper=new HelperSQL(context);
		fctHelper=new HelperFunction();
		screenDensity=fctHelper.getScreenDensity(context);
		lytHelper=new HelperLayout();
		
	}

	@Override
	public void bindView(View row, Context ctxt,
			Cursor c) {
		
		GameHolder holder=(GameHolder)row.getTag();
		holder.populateFrom(c, sqlHelper);
		
	}
	
	@Override
	public View newView(Context ctxt, Cursor c, ViewGroup parent) {
		
		LayoutInflater inflater=(LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.row_game, parent, false);
		GameHolder holder=new GameHolder(row, ctxt);
		row.setTag(holder);
		return(row);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);  
		ctxt = parent.getContext();
		Cursor c = (Cursor) getItem(position);
		if(game_id != null){
			if(game_id.equals(sqlHelper.getGameID(c))){
				view.setBackgroundColor(ctxt.getResources().getColor(R.color.green));
			} 
		}
		
		return view;
		
	}
}

class GameHolder {

	private TextView txt_game_date=null;
	private TextView game_fixture=null;
	private ImageView img_strikes=null;
	Context ctxt=null;
	String game_id = null;
    
	GameHolder(View row, Context context) {
      
		txt_game_date = (TextView)row.findViewById(R.id.rowGameDate);
		game_fixture = (TextView)row.findViewById(R.id.rowGameFixture);
		img_strikes = (ImageView)row.findViewById(R.id.logo_strikes);
		ctxt = context;
    	
	}
    
	void populateFrom(Cursor c, HelperSQL helper) {
		
		String game_id = helper.getGameID(c);
		Integer int_strikes = helper.count_ticker_activity(game_id, null, null, null, null, null);
		String team_home = helper.getGameTeamHomeByID(game_id);
		String team_away = helper.getGameTeamAwayByID(game_id);
		
		// Datum einrichten
		int year = 0;
		int day = 0;
		int month = 0;
		Date game_date = null;
		String str_game_date = helper.getGameDateByID(game_id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			game_date = sdf.parse(str_game_date);
			day = game_date.getDate();    // getDate => Day of month; getDay => day of week
			month = game_date.getMonth();
			year = game_date.getYear()+1900;
		}
		catch (ParseException e) {}
		
		// Begegnung einrichten
		if (team_home == null) {team_home = ctxt.getResources().getString(R.string.home);}
		if (team_away == null) {team_away = ctxt.getResources().getString(R.string.away);}
		
		// Wertung einrichten
		if (int_strikes < 100) {
			img_strikes.setImageResource(R.drawable.stroke_one);
		} else {
			if (int_strikes < 200) {
				img_strikes.setImageResource(R.drawable.stroke_two);
			} else {
				if (int_strikes < 300) {
					img_strikes.setImageResource(R.drawable.stroke_three);
				} else {
					if (int_strikes < 400) {
						img_strikes.setImageResource(R.drawable.stroke_four);
					} else {
						img_strikes.setImageResource(R.drawable.stroke_five);
					}
				}
			}
		}
		
		// Felder beschriften
		txt_game_date.setText(new StringBuilder()
			.append(day).append(".")
			.append(month + 1).append(".")
			.append(year));
		game_fixture.setText(team_home + " - " + team_away);
  
	}
}


