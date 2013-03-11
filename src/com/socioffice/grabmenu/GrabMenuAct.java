package com.socioffice.grabmenu;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class GrabMenuAct extends TabActivity {

	private Intent intent;
	private Resources res;
	private Drawable drawable;
	private TabHost tabHost;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		intent = getIntent();  // Reusable Intent for each tab					
		res = getResources(); // Resource object to get Drawables		
		tabHost = getTabHost(); // The activity TabHost

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, HomeAct.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		drawable = res.getDrawable(R.drawable.search);
		setupTab(tabHost, "Search", drawable, intent);
		
		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, GMapAct.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		drawable = res.getDrawable(R.drawable.map);
		setupTab(tabHost, "Map", drawable, intent);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, RestaurantListAct.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		drawable = res.getDrawable(R.drawable.list);
		setupTab(tabHost, "List", drawable, intent);

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, FavoritesListAct.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		drawable = res.getDrawable(R.drawable.favorite);
		setupTab(tabHost, "Favorites", drawable, intent);
		
		tabHost.getTabWidget().setStripEnabled(false);		
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	
	public void setupTab(TabHost tabHost, String tag, Drawable drawable,
			Intent intent) {
		View view = createTabView(tabHost.getContext(), drawable, tag);
		TabHost.TabSpec tab = tabHost.newTabSpec(tag).setIndicator(view)
				.setContent(intent);
		tabHost.addTab(tab);
	}

	public static View createTabView(Context context, Drawable drawable, String text) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.tabs_main_bg, null);
		TextView textView = (TextView) view.findViewById(R.id.lblTabTag);
		ImageView imageView = (ImageView) view.findViewById(R.id.imgTabTag);
		imageView.setImageDrawable(drawable);
		textView.setText(text);
		return view;
	}
}