package de.fivestrikes.fs2;

import de.fivestrikes.fs2.inappbilling.util.IabHelper;
import de.fivestrikes.fs2.inappbilling.util.IabResult;
import de.fivestrikes.fs2.inappbilling.util.Inventory;
import de.fivestrikes.fs2.inappbilling.util.Purchase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


public class TabGameActivity extends FragmentActivity {

	HelperSQL sqlHelper = null;
	TabFragGameList fragGameList;
	String game_id = null;
	Bundle args = null;
	IabHelper mHelper;
	public boolean mIsPremium = false;
	public final static String SKU_UNLIMITED_VERSION = "unlimited_version";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab_game);
		
/* Datenbank laden */
        
		sqlHelper = new HelperSQL(this);

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
	
/* Fragments einrichten */

		if (findViewById(R.id.frag_game_list) != null) {

			if (savedInstanceState != null) {return;}
			TabFragGameList firstFragment = new TabFragGameList();
			getSupportFragmentManager().beginTransaction()
				.add(R.id.frag_game_list, firstFragment).commit();
			
		}
        
		if (findViewById(R.id.frag_game_edit) != null) {

			Bundle args = new Bundle();
			args.putString("Activity", "Game");
			
			if (savedInstanceState != null) {return;}
			TabFragEmpty secondFragment = new TabFragEmpty();
			secondFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
            			.add(R.id.frag_game_edit, secondFragment).commit();
			
		}
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
					
					// Füge neues Spiel ein
					sqlHelper.insertGame();
					game_id = sqlHelper.getLastGameID();
					args = new Bundle();
					args.putString("GameID", game_id);
					
					// Aktualisiere die Spieliste
	      	  			fragGameList = (TabFragGameList) getSupportFragmentManager().findFragmentById(R.id.frag_game_list);
	      	  			fragGameList.refreshContent(game_id);
	      	  		
					// Erneuere Fragments
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();				
					TabFragGameEdit fragment = new TabFragGameEdit();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_game_edit, fragment);
					fragmentTransaction.commit();
					
				}
				
				return true;

			default:
				return super.onOptionsItemSelected(item);
				
		}
		return true;
	}
	
	private void CreateMenu(Menu menu) {
	
		MenuItem mnu1 = menu.add(0, 0, 0, "Item 1"); {

			mnu1.setAlphabeticShortcut('a');
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
					
					// Füge neues Spiel ein
					sqlHelper.insertGame();
					game_id = sqlHelper.getLastGameID();
					args = new Bundle();
					args.putString("GameID", game_id);
					
					// Aktualisiere die Spieliste
	      	  			fragGameList = (TabFragGameList) getSupportFragmentManager().findFragmentById(R.id.frag_game_list);
	      	  			fragGameList.refreshContent(game_id);
	      	  		
					// Erneuere Fragments
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();				
					TabFragGameEdit fragment = new TabFragGameEdit();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_game_edit, fragment);
					fragmentTransaction.commit();
					
				} else {
					
					// DialogBox einrichten
					final Dialog dialog = new Dialog(TabGameActivity.this);
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
							mHelper.launchPurchaseFlow(TabGameActivity.this, SKU_UNLIMITED_VERSION, 10001,
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
					
					// Füge neues Spiel ein
					sqlHelper.insertGame();
					game_id = sqlHelper.getLastGameID();
					args = new Bundle();
					args.putString("GameID", game_id);
					
					// Aktualisiere die Spieliste
	      	  			fragGameList = (TabFragGameList) getSupportFragmentManager().findFragmentById(R.id.frag_game_list);
	      	  			fragGameList.refreshContent(game_id);
	      	  		
					// Erneuere Fragments
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();				
					TabFragGameEdit fragment = new TabFragGameEdit();
					fragment.setArguments(args);
					fragmentTransaction.replace(R.id.frag_game_edit, fragment);
					fragmentTransaction.commit();
					
				}
			}
		}
	};
}
		