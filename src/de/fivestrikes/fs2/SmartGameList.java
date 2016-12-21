package de.fivestrikes.fs2;

import de.fivestrikes.fs2.inappbilling.util.IabHelper;
import de.fivestrikes.fs2.inappbilling.util.IabResult;
import de.fivestrikes.fs2.inappbilling.util.Inventory;
import de.fivestrikes.fs2.inappbilling.util.Purchase;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartGameList extends ListActivity {

	HelperSQL sqlHelper=null;
	Cursor model=null;
	HelperAdapterGame adapter=null;
	String game_id=null;
	IabHelper mHelper;
	public boolean mIsPremium = false;
	public final static String SKU_UNLIMITED_VERSION = "unlimited_version";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Teamliste einrichten */
		
		refreshContent(game_id);		
		
	}
	
/* Inhalt neu laden, wenn Activity ernuet aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent(game_id);
	    	
	}
	
	public void refreshContent(String game_id) {
	
		sqlHelper=new HelperSQL(this);
		model=sqlHelper.getAllGameCursor();
		startManagingCursor(model);
		adapter=new HelperAdapterGame(SmartGameList.this, model, game_id);
		setListAdapter(adapter);
		        
	}
	
/* Spielauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
	
		game_id = String.valueOf(id);
		Intent i=new Intent(SmartGameList.this, SmartGameEdit.class);
		i.putExtra("GameID", game_id);
		startActivity(i);
			
	}
	
/* Action Bar einrichten */
/** TODO -3- => Menü-Button bei jedem Bildschirm mit Auswahl der anderen Hauptbereiche und Hilfe */
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
			
			// Füge ein neues Spiel ein
			case 0:

				// Abfrage, wie viele Spiele der Nutzer bereits angelegt hat
				Cursor c = sqlHelper.getAllGameCursor();			
				c.moveToFirst();
				Integer countGames = c.getCount();
				c.close();
				
				// Falls 3 oder mehr Spiele angelegt wurden, Abfrage, ob der Nutzer einen Premium-Account hat
				if (countGames > 2) {
					
					/** TODO -1- => PublicKey verstecken. */
					String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh99+dNG/SPzRB2WjrzjwiTtAc2l2tpbojMpz7vHmdxR7ahUY0zOfgtSaryxwMGRdL59Y8wNKDhntjOLj7qwj1oT8mUeBAXDrzDo+D2i1w1vzgCRxwffsunRoDidmftzIFce/I+DAAvf05QwGprtKDplmZl1o4rMkVUFHcgoIMstqTE6GdF330XpJyXsGsXAIDK0NXFLSFbFjaK+52PVVbQ6ViqPbvGaeApnTt5nl0GCwheV0oNP397KVqtAKKbGRIMDnyN9zVB0POWO7o8x+ph7ybQwCsGQKiypNgQJnmXgjZ4WJnlQDWjDVAf6/RA/4xhl30H55Z+ICtG+t6fO7KQIDAQAB";
					mHelper = new IabHelper(this, base64EncodedPublicKey);
					
					mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
						
						public void onIabSetupFinished(IabResult result) {
							
						      if (!result.isSuccess()) {

						      		// Oh no, there was a problem.
						      		Log.d("HelperLayout InputDepth", "Problem setting up In-app Billing: " + result);
						      		
						      } else {
						      	
							      // Prüfen, ob Nutzer den Premium-Account hat
						      		if (mHelper != null) mHelper.flagEndAsync();
								mHelper.queryInventoryAsync(mGotInventoryListener);
							      
						      }			
						}
					});
					
				} else {
					
					sqlHelper.insertGame();
					game_id = sqlHelper.getLastGameID();
					Intent i=new Intent(SmartGameList.this, SmartGameEdit.class);
					i.putExtra("GameID", game_id);
					startActivity(i);
					
				}
				
				return true;
		      	  	
		        default:
		      	  	return super.onOptionsItemSelected(item);
		
		}

		return true;
        
	}
	
	private void CreateMenu(Menu menu) {
	
		MenuItem mnu1 = menu.add(0, 0, 0, "Add"); {

			mnu1.setIcon(R.drawable.add);
			mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
		}
	}
	
	IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		
		public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
			
			if (result.isFailure()) {
				Log.v("HelperLayout QueryInventoryFinishedListener result", String.valueOf(result));
			} else {
				// does the user have the premium upgrade?
				mIsPremium = inventory.hasPurchase(SKU_UNLIMITED_VERSION);
				
				// Falls Nutzer keinen Premium-Account hat => Aufforderung, einen Premium-Account abzuschließen
				if (mIsPremium == true) {
					
					sqlHelper.insertGame();
					game_id = sqlHelper.getLastGameID();
					Intent i=new Intent(SmartGameList.this, SmartGameEdit.class);
					i.putExtra("GameID", game_id);
					startActivity(i);
					
				} else {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(SmartGameList.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.custom_dialog);

					// Texte setzen
					TextView title = (TextView) dialog.findViewById(R.id.title);
					TextView text = (TextView) dialog.findViewById(R.id.text);
					title.setText(R.string.pro_version);
					text.setText(R.string.text_pro_version);
						
					// Button definieren
					LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
					lyt_button3.removeAllViews();
					
					Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
					dialogButton1.setText(R.string.yes);
					
					dialogButton1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
	/** TODO -0- => Individuellen payload einbauen? */ 
							String payload = "abc";
							mHelper.launchPurchaseFlow(SmartGameList.this, SKU_UNLIMITED_VERSION, 10001,
									mPurchaseFinishedListener, payload);
							
							dialog.dismiss();
							
						}
					});
					
					Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
					dialogButton2.setText(R.string.no);
					
					dialogButton2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							
							dialog.dismiss();
							
						}
					});

					dialog.show();
					// Ende Dialogbox einrichten
					
				}
			}
		}
	};
	
	// Kontrollieren, ob Kauf erfolgreich war
	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
			  = new IabHelper.OnIabPurchaseFinishedListener() {
			
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
				
			if (result.isFailure()) {
				Log.d("Helper Layout InputDepth", "Error purchasing: " + result);
			} else {
				
				if (purchase.getSku().equals(SKU_UNLIMITED_VERSION)) {
					
					sqlHelper.insertGame();
					game_id = sqlHelper.getLastGameID();
					Intent i=new Intent(SmartGameList.this, SmartGameEdit.class);
					i.putExtra("GameID", game_id);
					startActivity(i);
					
				}
			}
		}
	};
}