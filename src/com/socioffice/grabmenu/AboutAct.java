package com.socioffice.grabmenu;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutAct extends Activity {

	private TextView lblAboutMsg;
	private Button btnDone;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		lblAboutMsg = (TextView) findViewById(R.id.lblAboutMsg);
		btnDone = (Button) findViewById(R.id.btnDone);
						
		btnDone.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnDoneOnClick(view);
			}			
		});		
		
		aboutUs();
	}

	public void btnDoneOnClick(View view) {
		finish();
	}
	
	public void aboutUs() {
		lblAboutMsg.setText(Html.fromHtml("<big>" + "<b>GrabMenu Android App</b>"
				+ "<br/>Send questions and feeback to feedback@grabmenu.com." + "</big>"
				+ "<br/><br/>" + "<small>" + "DateAdded" + "</small>"));
	}
	
}