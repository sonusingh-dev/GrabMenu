package com.socioffice.grabmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeAct extends Activity {

	private TextView txtCity;
	private EditText txtWhat;
	private EditText txtWhere;

	private Button btnCities;
	private Button btnFeedback;
	private Button btnAbout;

	private Intent intent;
	private InitializeGrabMenu app;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		app = (InitializeGrabMenu) getApplicationContext();

		txtCity = (TextView) findViewById(R.id.lblCity);
		txtWhat = (EditText) findViewById(R.id.txtWhat);
		txtWhere = (EditText) findViewById(R.id.txtWhere);

		btnCities = (Button) findViewById(R.id.btnCities);
		btnFeedback = (Button) findViewById(R.id.btnFeedback);
		btnAbout = (Button) findViewById(R.id.btnAbout);

		// Create the adView
	    // AdView adView = new AdView(this, AdSize.BANNER, "a14de7410312e4e");
	    // Lookup your LinearLayout assuming it’s been given
	    // the attribute android:id="@+id/mainLayout"
	    // LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);
	    // Add the adView to it
	    // layout.addView(adView);
	    // Initiate a generic request to load it with an ad
	    // adView.loadAd(new AdRequest());
	    
		txtWhat.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				txtWhatOnClick(view);
			}			
		});
		
		txtWhere.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				txtWhereOnClick(view);
			}			
		});
		
		btnCities.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnCitiesOnClick(view);
			}			
		});
		
		btnFeedback.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnFeedbackOnClick(view);
			}			
		});
		
		btnAbout.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnAboutOnClick(view);
			}			
		});
		
		txtCity.setText(app.getCity());		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			txtCity.setText(app.getCity());
			break;

		case 1:
			txtWhat.setText(data.getStringExtra("what"));
			txtWhere.setText(data.getStringExtra("where"));
			break;
		}			
	}
	
	public void txtWhatOnClick(View view) {
		intent = new Intent().setClass(this, SearchAct.class);
		intent.putExtra("focus", "What");
		startActivityForResult(intent, 1);		
	}

	public void txtWhereOnClick(View view) {
		intent = new Intent().setClass(this, SearchAct.class);
		intent.putExtra("focus", "Where");
		startActivityForResult(intent, 1);			
	}
	
	public void btnCitiesOnClick(View view) {
		intent = new Intent().setClass(this, CityListAct.class);
		startActivityForResult(intent, 0);		
	}
	
	public void btnFeedbackOnClick(View view) {
		intent = new Intent().setClass(this, FeedbackAct.class);
		startActivity(intent);		
	}
	
	public void btnAboutOnClick(View view) {
		intent = new Intent().setClass(this, AboutAct.class);
		startActivity(intent);		
	}
		
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Intent intent = new Intent().setClass(this, SearchAct.class);
		switch (v.getId()) {
		case R.id.txtWhat:
			intent.putExtra("focus", "What");
			break;
		case R.id.txtWhere:
			intent.putExtra("focus", "Where");
			break;
		}
		startActivityForResult(intent, 1);
		return true;
	}

}
