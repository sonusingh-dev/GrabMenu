package com.socioffice.grabmenu;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantAct extends TabActivity {

	private Button btnList;
	private ImageButton btnUp;
	private ImageButton btnDown;
	
	private Intent intent;	
	private TabHost tabHost;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_detail);

		tabHost = getTabHost(); // The activity TabHost
		
		btnList = (Button) findViewById(R.id.btnList);
		btnUp = (ImageButton) findViewById(R.id.btnUp);
		btnDown = (ImageButton) findViewById(R.id.btnDown);
		
		btnList.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnListOnClick(view);
			}
		});
		
		btnUp.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnUpOnClick(view);
			}
		});
		
		btnDown.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnDownOnClick(view);
			}
		});
			
		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, ProfileAct.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		setupTab(tabHost, "Profile", intent);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, ReviewsAct.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		setupTab(tabHost, "Reviews", intent);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, MenusAct.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		setupTab(tabHost, "Menu", intent);

		// Hide gray line
		tabHost.getTabWidget().setStripEnabled(false);		
	}

	public void btnListOnClick(View view) {
		// TODO Auto-generated method stub
		finish();
	}
	
	public void btnUpOnClick(View view) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Up is pressed", Toast.LENGTH_SHORT).show();
	}
	
	public void btnDownOnClick(View view) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Down is pressed", Toast.LENGTH_SHORT).show();
	}

	public void setupTab(TabHost tabHost, String tag, Intent intent) {
		View view = createTabView(tabHost.getContext(), tag);
		TabHost.TabSpec tab = tabHost.newTabSpec(tag).setIndicator(view)
				.setContent(intent);
		tabHost.addTab(tab);
	}

	public static View createTabView(Context context, String text) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.tabs_rest_bg, null);
		TextView textView = (TextView) view.findViewById(R.id.lblTabTag);
		textView.setText(text);
		return view;
	}

}
