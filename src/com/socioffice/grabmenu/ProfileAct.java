package com.socioffice.grabmenu;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ProfileAct extends Activity {
	
	private Button btnCall;
	private Button btnAddToFavorites;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		btnCall = (Button) findViewById(R.id.btnCall);
		btnAddToFavorites = (Button) findViewById(R.id.btnAddToFavorites);
		
		btnCall.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnCallOnClick(view);
			}
		});
		
		btnAddToFavorites.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnAddToFavorites(view);
			}
		});
		
	}
	
	public void btnAddToFavorites(View view) {
		Toast.makeText(this, "Added To Favorites", Toast.LENGTH_SHORT).show();
	}
	
	public void btnCallOnClick(View view) {
		try {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:+436641234567"));
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Log.e("helloandroid dialing example", "Call failed", e);
		}
	}
}
