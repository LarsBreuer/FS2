package de.fivestrikes.fs2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HelperAdapterStatTeam extends ArrayAdapter<String> {
	private final Context context;
	private final String[] statHomeValues;
	private final String[] statTeamValues;
	private final String[] statAwayValues;
	private final int[] intHomeValues;
	private final int[] intAwayValues;
	String team_home;
	String team_away;
	Resources res;
	HelperSQL sqlHelper=null;
	
	public HelperAdapterStatTeam(Context context, String[] values1, String[] values2, String[] values3, int[] values4, int[] values5, String id) {
		super(context, R.layout.row_simple_text, values1);
		this.context = context;
		this.statHomeValues = values1;
		this.statTeamValues = values2;
		this.statAwayValues = values3;
		this.intHomeValues = values4;
		this.intAwayValues = values5;
		String game_id = id;
		sqlHelper=new HelperSQL(context);
		String team_home_id = sqlHelper.getGameTeamHomeIDByID(game_id);
		String team_away_id = sqlHelper.getGameTeamAwayIDByID(game_id);
		team_home = sqlHelper.getTeamClubShortByID(team_home_id);
		team_away = sqlHelper.getTeamClubShortByID(team_away_id);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		res = context.getResources();
		
		// Hintergrund setzen, je nach ob Überschrift oder nicht
		int xml_type = R.layout.row_stat_team;
		if (statTeamValues[position].equals(res.getString(R.string.game_statistic)) ||
				statTeamValues[position].equals(res.getString(R.string.goals)) ||
				statTeamValues[position].equals(res.getString(R.string.possession)) ||
				statTeamValues[position].equals(res.getString(R.string.game_history)) ||
				statTeamValues[position].equals(res.getString(R.string.penalties))) {
			xml_type = R.layout.row_stat_team_head;
		}
		final View rowView = inflater.inflate(xml_type, parent, false);
		
		// Layoutfelder definieren
		TextView homeTextView = (TextView) rowView.findViewById(R.id.txtStatTeamHome);
		TextView headTextView = (TextView) rowView.findViewById(R.id.txtStatHead);
		TextView awayTextView = (TextView) rowView.findViewById(R.id.txtStatTeamAway);
		homeTextView.setText(statHomeValues[position]);
		headTextView.setText(statTeamValues[position]);
		awayTextView.setText(statAwayValues[position]);
		
		// Bei Überschrift ändere Farbe der Flächen und der Schrift
		if (statTeamValues[position].equals(res.getString(R.string.game_statistic)) ||
				statTeamValues[position].equals(res.getString(R.string.goals)) ||
				statTeamValues[position].equals(res.getString(R.string.possession)) ||
				statTeamValues[position].equals(res.getString(R.string.game_history)) ||
				statTeamValues[position].equals(res.getString(R.string.penalties))) {
			
			// rowView.setBackgroundColor(res.getColor(R.color.standardfarbe));
			
			if (!statTeamValues[position].equals(res.getString(R.string.game_statistic))) {
				homeTextView.setText(team_home);
				awayTextView.setText(team_away);
			}
		
		// Falls keine Überschrift: Erstelle Balkendiagramm
		} else {
			
			rowView.post(new Runnable() {
				
				@Override
				public void run() {
					
					ImageView chartView = (ImageView) rowView.findViewById(R.id.view);
					
					// Ermittle Höhe und Breite
					int view_width = chartView.getWidth();
					int view_height = chartView.getHeight();
					
					// Ermittle das statistische Verhältnis
					if (intHomeValues[position] + intAwayValues[position] > 0) {
						
						int mark = intHomeValues[position] * view_width /
								(intHomeValues[position] + intAwayValues[position]);
						
												// Grafik einrichten
				  		Bitmap bitMap = Bitmap.createBitmap(view_width, view_height, Bitmap.Config.ARGB_8888);
				  		bitMap = bitMap.copy(bitMap.getConfig(), true);
				  		Canvas canvas = new Canvas(bitMap);
						// Layout definieren
	  			  		Paint paintHome = new Paint();
	  			  		Paint paintAway = new Paint();
	  			  		paintHome.setAntiAlias(true);
	  			  		paintAway.setAntiAlias(true);
	  			  		// Farbe der Mannschaft definieren
/** TODO -4- => Farbe an die Vereinsfarbe anpassen */
	  			  		paintHome.setColor(0xFF404895);
	  			  		paintAway.setColor(0xFFCB061D);
	  			  		// Balken Heimmannschaft zeichnen
	  			  		canvas.drawRect(0, 0, mark, view_height, paintHome);
	  			  		// Balken Auswärtsmannschaft zeichnen
	  			  		canvas.drawRect(mark, 0, view_width, view_height, paintAway);
	  			  		// Balkendiagramm setzen
	  			  		// Wurffelder setzen
	  			  		chartView.setImageBitmap(bitMap);
						
					}
				}
			});
		}

		return rowView;
	}
}