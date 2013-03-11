package com.socioffice.grabmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ForgotPasswordAct extends Activity {
	
	private Button btnCancel;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.forgot_password);
    
    	btnCancel = (Button) findViewById(R.id.btnCancel);
    	btnCancel.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnCancelOnClick(view);
			}
		});
    }

    public void btnCancelOnClick(View view) {
		// TODO Auto-generated method stub
		finish();
	}

}
