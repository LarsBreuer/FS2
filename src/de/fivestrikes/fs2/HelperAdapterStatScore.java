package de.fivestrikes.fs2;

import java.util.ArrayList;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.res.Resources;
import android.graphics.Color;

public class HelperAdapterStatScore extends BaseAdapter {
	private final Context context;
	private final ArrayList mData;
	String game_id, player_id, player_name, team_name, strInput, player_position;
	Resources res;
	HelperSQL sqlHelper = null;
	
	public HelperAdapterStatScore(Context context, Map<String, Integer> map, String id, String str) {
		
		this.context = context;
		mData = new ArrayList();
		mData.addAll(map.entrySet());
		game_id = id;
		strInput = str;
		sqlHelper = new HelperSQL(context);
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}
	
	@Override
	public Map.Entry<String, String> getItem(int position) {
		return (Map.Entry) mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		res = context.getResources();
		
		// Layout der Reihe einrichten
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int xml_type = R.layout.row_stat_score;
		if (position == 0) xml_type = R.layout.row_stat_score_head;
		View rowView = inflater.inflate(xml_type, parent, false);
		
		// Teamname bzw. Spielerposition nicht eintragen, falls die erste Reihe eingerichtet werden soll
		if (position != 0) {
			
			Map.Entry<String, String> item = getItem(position);
			player_id = item.getKey();
			player_name = sqlHelper.getPlayerForenameByID(player_id) + " " + sqlHelper.getPlayerSurenameByID(player_id);
			player_position = sqlHelper.getPlayerPositionNameByID(sqlHelper.getPlayerPositionFirstByID(player_id), context);
			team_name = sqlHelper.getTeamClubNameByID(sqlHelper.getPlayerTeamIDByID(player_id)); 
			Integer goal_id = res.getInteger(R.integer.goal_id);
			Integer goal_7m_id = res.getInteger(R.integer.goal_7m_id);
			Integer goal_fb_id = res.getInteger(R.integer.goal_fb_id);
			Integer miss_id = res.getInteger(R.integer.miss_id);
			Integer miss_7m_id = res.getInteger(R.integer.miss_7m_id);
			Integer miss_fb_id = res.getInteger(R.integer.miss_fb_id);
			int goals = 0;
			int goals_7m = 0;
			int goals_fb = 0;
			int goals_total = 0;
			int miss = 0;
			int miss_7m = 0;
			int miss_fb = 0;
			int miss_total = 0;
			int attempts = 0;
			int effective = 0;
			
			goals = sqlHelper.count_ticker_activity(game_id, null, player_id, goal_id, null, null);
			goals_7m = sqlHelper.count_ticker_activity(game_id, null, player_id, goal_7m_id, null, null);
			goals_fb = sqlHelper.count_ticker_activity(game_id, null, player_id, goal_fb_id, null, null);
			miss = sqlHelper.count_ticker_activity(game_id, null, player_id, miss_id, null, null);
			miss_7m = sqlHelper.count_ticker_activity(game_id, null, player_id, miss_7m_id, null, null);
			miss_fb = sqlHelper.count_ticker_activity(game_id, null, player_id, miss_fb_id, null, null);
			goals_total = goals + goals_7m + goals_fb;
			miss_total = miss + miss_7m + miss_fb;
			attempts = goals_total + miss_total;
			if (attempts != 0) effective = (goals_total * 100) / attempts;
			
			TextView nameTextView = (TextView) rowView.findViewById(R.id.goal_name);
			TextView goalsTextView = (TextView) rowView.findViewById(R.id.goal_count);
			TextView attemptsTextView = (TextView) rowView.findViewById(R.id.goal_attempt);
			TextView effectTextView = (TextView) rowView.findViewById(R.id.goal_effective);
			nameTextView.setText(player_name);
			TextView teamNameTextView = (TextView) rowView.findViewById(R.id.goal_team_name);
			teamNameTextView.setText(player_position);
			if (strInput.equals("ScoreTotal")) teamNameTextView.setText(team_name);
			if (goals_7m > 0) {
				goalsTextView.setText(String.valueOf(goals_total) + " / " + String.valueOf(goals_7m));
			} else {
				goalsTextView.setText(String.valueOf(goals_total));
			}
			attemptsTextView.setText(String.valueOf(attempts));
			effectTextView.setText(String.valueOf(effective) + "%");
						
		}

		return rowView;
	}
}