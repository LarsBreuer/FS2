package de.fivestrikes.fs2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ListView;

public class TabFragTickerTime extends Fragment {
	
	HelperLayout lytHelper = null;
	View view;
	Bundle args;
	FragmentManager fragmentManager;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.ticker_set_watch, container, false);
		       
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		args = getArguments();
		
/* Layout einrichten */
		
		fragmentManager = getFragmentManager();
		lytHelper.lytTickerTime(args, view, fragmentManager);
		
		return view;
	
	}
}
