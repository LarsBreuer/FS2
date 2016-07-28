package de.fivestrikes.fs2;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class TermsActivity extends Activity {

	HelperText txtHelper = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.terms);
		
/* Helper generieren */
		
		txtHelper=new HelperText();
		
/* Textfelder konfigurieren */
		
		TextView title = (TextView) findViewById(R.id.title);
		TextView text = (TextView) findViewById(R.id.text);
		
		title.setText(R.string.terms);
		text.setText(txtHelper.terms());

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

	}
	
/* Action Bar einrichten */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
        
			case android.R.id.home: 
				onBackPressed();
				break;

			default:
				return super.onOptionsItemSelected(item);
      	  	
		}
        
		return true;
        
	}
	
	private void CreateMenu(Menu menu) {
		
	}
	    
}