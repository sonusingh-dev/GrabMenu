package com.socioffice.grabmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackAct extends Activity {

	private EditText txtFrom;
	private EditText txtTo;
	private EditText txtSubject;
	private EditText txtBody;
	
	private Button btnCancel;
	private Button btnSend;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);

		txtFrom = (EditText) findViewById(R.id.txtName);
		txtTo = (EditText) findViewById(R.id.txtEmail);
		txtSubject = (EditText) findViewById(R.id.txtSubject);
		txtBody = (EditText) findViewById(R.id.txtMessage);
		
		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnSend = (Button) findViewById(R.id.btnSend);

		btnCancel.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnCencelOnClick(view);
			}
		});
		
		
		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnSendOnClick(view);
			}			
		});
	}

	public void btnCencelOnClick(View view) {
		finish();
	}
	
	public void btnSendOnClick(View view) {
		// GMailSender sender = new GMailSender("sonu7066@gmail.com", "sd2k2112855");
		try {                   
            /*sender.sendMail(txtSubject.getText().toString(),   
                    txtBody.getText().toString(),   
                    txtFrom.getText().toString(),   
                    txtTo.getText().toString());*/
            Toast.makeText(this, "Mail Send", Toast.LENGTH_LONG).show();
        } catch (Exception e) {   
            Log.e("SendMail", e.getMessage(), e);   
        } 
		sendMail();		
	}

	public void sendMail() {
		// Create a new Intent to send messages
		Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);		
		// Add attributes to the intent
		sendIntent.setType("text/plain"); // use this line for testing in the
											// emulator
		// sendIntent.setType("message/rfc822"); //use this line for testing on
		// the real phone
		sendIntent.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "sonu7066@gmail.com" });
		sendIntent.putExtra(Intent.EXTRA_SUBJECT, " Password");
		sendIntent.putExtra(Intent.EXTRA_TEXT, "You're password is: ");
		startActivity(Intent.createChooser(sendIntent,
				"Title:"));
		    
	}

}
