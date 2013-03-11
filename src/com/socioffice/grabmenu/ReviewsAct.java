package com.socioffice.grabmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReviewsAct extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.reviews);
    	
    	Button btnAddUrReview = (Button) findViewById(R.id.btnAddUrReview);
    	btnAddUrReview.setOnClickListener(this);
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub				
		Intent intent = new Intent().setClass(this, ReviewAct.class);
		startActivity(intent);
	}
}
