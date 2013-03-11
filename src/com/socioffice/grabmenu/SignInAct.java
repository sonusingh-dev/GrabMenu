package com.socioffice.grabmenu;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInAct extends Activity {

	private String strEmail;
	private String strPassword;
	private String success;

	private ArrayList<String> errors;

	private EditText txtEmail;
	private EditText txtPassword;

	private Button btnCancel;
	private Button btnSignIn;
	private Button btnForgot;
	private Button btnSignUp;
	private Button btnWhy;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_in);

		Intent intent = getIntent();
		success = intent.getStringExtra("success");
		strEmail = intent.getStringExtra("email");
		errors = intent.getStringArrayListExtra("errors");

		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtPassword = (EditText) findViewById(R.id.txtPassword);

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnSignIn = (Button) findViewById(R.id.btnSignIn);
		btnForgot = (Button) findViewById(R.id.btnForgot);
		btnSignUp = (Button) findViewById(R.id.btnSignUp);
		btnWhy = (Button) findViewById(R.id.btnWhy);

		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnCancelOnClick(view);
			}
		});

		btnSignIn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnSignInOnClick(view);
			}
		});
		
		btnForgot.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnForgotOnClick(view);
			}
		});

		btnSignUp.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnSignUpOnClick(view);
			}
		});

		btnWhy.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnWhyOnClick(view);
			}
		});
		
		if (errors != null) {
			showError();
			if (strEmail != null) {
				repopulate();
			}
		}

		if (success != null) {
			Toast.makeText(this, "You Have Signed In Successfully To GrabMenu",
					Toast.LENGTH_LONG).show();
		}
	}

	public void btnCancelOnClick(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent().setClass(this, GrabMenuAct.class);
		startActivity(intent);
	}

	public void btnSignInOnClick(View view) {
		// TODO Auto-generated method stub
		validate();
	}

	public void btnForgotOnClick(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent().setClass(this, ForgotPasswordAct.class);
		startActivity(intent);
	}
	
	public void btnSignUpOnClick(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent().setClass(this, SignUpAct.class);
		startActivity(intent);
	}
	
	public void btnWhyOnClick(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent().setClass(this, WhySignUpAct.class);
		startActivity(intent);
	}

	public void validate() {

		strEmail = txtEmail.getText().toString();
		strPassword = txtPassword.getText().toString();

		errors = new ArrayList<String>();

		if (!(strEmail.contains("@") && (strEmail.contains(".com") || strEmail
				.contains(".co.in")))) {
			errors.add("Email");
		}

		if (strPassword.length() < 8) {
			errors.add("Password");
		}

		if (!errors.isEmpty()) {
			// showError(errList);
			Intent intent = new Intent().setClass(this, this.getClass());
			intent.putStringArrayListExtra("errors", errors);
			intent.putExtra("email", strEmail);
			startActivity(intent);
			finish();
			return;
		}

		Intent intent = new Intent().setClass(this, this.getClass());
		intent.putExtra("success", "success");
		startActivity(intent);
		finish();

	}

	public void repopulate() {
		txtEmail.setText(strEmail);
	}

	public void showError() {

		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		// Set the message to display
		alertbox.setMessage("The email or password you entered is incorrect.");
		// Set a neutral/OK button and create a listener
		alertbox.setNeutralButton("OK", null);
		// display box
		alertbox.show();

		Drawable x = getResources().getDrawable(R.drawable.error);
		x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());

		for (String error : errors) {

			if (error.equals("Email")) {
				txtEmail.setCompoundDrawables(null, null, x, null);
			}

			if (error.equals("Password")) {
				txtPassword.setCompoundDrawables(null, null, x, null);
			}

		}
	}
}
