package com.socioffice.grabmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class ReviewAct extends Activity implements OnRatingBarChangeListener {
	
	private Button btnCancel;
	private Button btnAddReview;
	
	private RatingBar ratFood;
	private RatingBar ratService;
	private RatingBar ratValue;
	private RatingBar ratAtmosphere;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.review);
    	
    	btnCancel = (Button) findViewById(R.id.btnCancel);
    	btnAddReview = (Button) findViewById(R.id.btnAddReview);
    	
    	ratFood = (RatingBar) findViewById(R.id.ratFood);
    	ratService = (RatingBar) findViewById(R.id.ratService);
    	ratValue = (RatingBar) findViewById(R.id.ratValue);
    	ratAtmosphere = (RatingBar) findViewById(R.id.ratAtmosphere);
    	
    	ratFood.setOnRatingBarChangeListener(this);
    	ratService.setOnRatingBarChangeListener(this);
    	ratValue.setOnRatingBarChangeListener(this);
    	ratAtmosphere.setOnRatingBarChangeListener(this);
    	
    	btnCancel.setOnClickListener(new OnClickListener() {
    		public void onClick(View view) {
    			btnCancelOnClick(view);
    		}
    	});
    	
    	btnAddReview.setOnClickListener(new OnClickListener() {
    		public void onClick(View view) {
    			btnAddReviewOnClick(view);
    		}
    	});
    }

	public void btnCancelOnClick(View view) {
		// TODO Auto-generated method stub
		finish();
	}

	public void btnAddReviewOnClick(View view) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Review Added", Toast.LENGTH_SHORT).show();
	}
	
	public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Rating", Toast.LENGTH_SHORT).show();
	}
}
