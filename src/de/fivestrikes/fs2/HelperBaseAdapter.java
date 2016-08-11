package de.fivestrikes.fs2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class HelperBaseAdapter extends CursorAdapter {
	private ProgressDialog progressDialog;
	private Context ctxt = null;
	
	public HelperBaseAdapter(Context context, Cursor c) {
		super(context, c);
		setCtxt(context);
	}

	protected Handler loginNotPossibleHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			dismissProgressDialog();

			// Nachrichtenbox einrichten
			final Dialog dialog = new Dialog(ctxt);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_dialog);

			// Texte setzen
			TextView title = (TextView) dialog.findViewById(R.id.title);
			TextView text = (TextView) dialog.findViewById(R.id.text);
			title.setText(R.string.synchro);
			text.setText(R.string.text_login_not_possible);
			
			// Button definieren
			LinearLayout lyt_button2 = (LinearLayout) dialog.findViewById(R.id.lyt_button2);
			lyt_button2.removeAllViews();
			LinearLayout lyt_button3 = (LinearLayout) dialog.findViewById(R.id.lyt_button3);
			lyt_button3.removeAllViews();
			
			Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);
			dialogButton1.setText(R.string.okay);
			
			dialogButton1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			dialog.show();
			// Ende Nachrichtenbox
		}
	};
	
	public void dismissProgressDialog() {
		if (getProgressDialog() != null && getProgressDialog().isShowing()) {
			getProgressDialog().dismiss();
		}
	}
	
	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}

	public Context getCtxt() {
		return ctxt;
	}

	public void setCtxt(Context ctxt) {
		this.ctxt = ctxt;
	}
}
