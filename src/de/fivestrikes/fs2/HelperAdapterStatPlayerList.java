package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.res.Resources;
import android.database.Cursor;

public class HelperAdapterStatPlayerList extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> playerList ;
	HelperSQL sqlHelper = null;
	String player_selected_id=null;
	
	public HelperAdapterStatPlayerList(Context context, ArrayList<String> player, String id) {
		super(context, R.layout.row_player_raw, player);
		this.context = context;
		playerList = player;
		player_selected_id = id;
		sqlHelper=new HelperSQL(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// Layoutfelder definieren
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_player_raw, parent, false);
		
		TextView txt_player_number = (TextView) rowView.findViewById(R.id.player_number);
		TextView txt_player_name = (TextView) rowView.findViewById(R.id.player_name);
		
		String player_id = playerList.get(position);
		String player_number = sqlHelper.getPlayerNumberByID(player_id);
		String player_name = sqlHelper.getPlayerForenameByID(player_id) + " " + sqlHelper.getPlayerSurenameByID(player_id);
		
		txt_player_number.setText(player_number);
		txt_player_name.setText(player_name);
		
		Context ctxt = parent.getContext();
		
		if (player_id != null) {
			if (player_id.equals(player_selected_id)) {
				rowView.setBackgroundColor(ctxt.getResources().getColor(R.color.green));
			} 
		}

		return rowView;
	}
}