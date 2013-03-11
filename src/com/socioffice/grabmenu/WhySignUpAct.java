package com.socioffice.grabmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WhySignUpAct extends Activity {
	
	private Button btnDone;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.why_sign_up);
    
    	btnDone = (Button) findViewById(R.id.btnDone);
    	btnDone.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnDoneOnClick(view);
			}
		});
    }

    public void btnDoneOnClick(View view) {
		// TODO Auto-generated method stub
		finish();
	}
    
}
