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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TabFragTeamEdit extends Fragment {
	
	HelperLayout lytHelper = null;
	Bundle args;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.team_edit, container, false);
		
/* Helper generieren */
        
		lytHelper = new HelperLayout();

/* Daten aus Activity laden */ 
        
		args = getArguments();
		
/* Layout festlegen */
		
		FragmentManager fragmentManager = getFragmentManager();
		lytHelper.lytTeamEdit(view, fragmentManager, args);

		return view;
	}
}