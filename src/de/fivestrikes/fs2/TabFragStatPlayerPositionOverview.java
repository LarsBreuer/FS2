package de.fivestrikes.fs2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TabFragStatPlayerPositionOverview extends Fragment {
	
	HelperLayout lytHelper = null;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.stat_position_overview, container, false);
		
/* Helper generieren */
        
		lytHelper = new HelperLayout();

/* Daten aus Activity laden */ 
        
		Bundle args = getArguments();
		
/* Layout festlegen */
		
		FragmentManager fragmentManager = getFragmentManager();
		lytHelper.lytStatPlayerPositionOverview(args, view, fragmentManager);

		return view;
	}
}