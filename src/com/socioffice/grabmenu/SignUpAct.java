package com.socioffice.grabmenu;

import java.util.ArrayList;

import com.socioffice.grabmenu.model.DBAdapter;
import com.socioffice.grabmenu.model.UserInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpAct extends Activity {

	private int intZipcode = -1;
	private boolean blnNews = false;
	private boolean blnPromo = false;

	private String strEmail;
	private String strPassword;
	private String strConfirm;
	private String strZipcode;
	private String strGender;

	private String success;

	private ArrayList<String> errors;
	private ArrayList<String> values;

	private EditText txtEmail;
	private EditText txtPassword;
	private EditText txtConfirm;
	private EditText txtZipcode;

	private TextView lblGender;
	private RadioButton radMale;
	private RadioButton radFemale;

	private CheckBox chkNews;
	private CheckBox chkPromo;

	private Button btnCancel;
	private Button btnSignUp;
	private Button btnTerms;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);

		Intent intent = getIntent();
		success = intent.getStringExtra("success");
		strEmail = intent.getStringExtra("email");
		strZipcode = intent.getStringExtra("zipcode");
		errors = intent.getStringArrayListExtra("errors");
		values = intent.getStringArrayListExtra("values");

		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		txtConfirm = (EditText) findViewById(R.id.txtConfirmPass);
		txtZipcode = (EditText) findViewById(R.id.txtZipcode);

		lblGender = (TextView) findViewById(R.id.lblGender);
		radMale = (RadioButton) findViewById(R.id.radMale);
		radFemale = (RadioButton) findViewById(R.id.radFemale);

		chkNews = (CheckBox) findViewById(R.id.chkNews);
		chkPromo = (CheckBox) findViewById(R.id.chkPromo);

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnSignUp = (Button) findViewById(R.id.btnSignUP);
		btnTerms = (Button) findViewById(R.id.btnTerms);

		btnCancel.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnCancelOnClick(view);
			}
		});
		
		btnSignUp.setOnClickListener(new OnClickListener() {			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnSignUpOnClick(view);
			}
		});
		
		btnTerms.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnTermsOnClick(view);
			}			
		});

		if (errors != null) {
			showError();
			if (values != null) {
				repopulate();
			}
		}

		if (success != null) {
			Toast.makeText(this,
					"You Have Registered Successfully To GrabMenu",
					Toast.LENGTH_LONG).show();
		}

	}

	public void btnCancelOnClick(View view) {
		// TODO Auto-generated method stub		
		finish();			
	}
	
	public void btnSignUpOnClick(View view) {
		// TODO Auto-generated method stub		
		validate();			
	}
	
	public void btnTermsOnClick(View view) {
		// TODO Auto-generated method stub		
		Intent intent = new Intent().setClass(this, TermsServingAct.class);
		startActivity(intent);
	}

	public void validate() {

		strEmail = txtEmail.getText().toString();
		strPassword = txtPassword.getText().toString();
		strConfirm = txtConfirm.getText().toString();
		strZipcode = txtZipcode.getText().toString();

		values = new ArrayList<String>();
		errors = new ArrayList<String>();

		if (!(strEmail.contains("@") && (strEmail.contains(".com") || strEmail
				.contains(".co.in")))) {
			errors.add("Email");
		}

		if (strPassword.length() < 8) {
			errors.add("Password");
		} else if (!strPassword.equals(strConfirm)) {
			errors.add("Confirm");
		}

		if (strZipcode.length() == 6) {
			try {
				intZipcode = Integer.parseInt(strZipcode);
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		} else {
			errors.add("Zipcode");
		}

		values.add("Email");
		values.add("Zipcode");

		if (radMale.isChecked()) {
			strGender = "M";
			values.add("Male");
		}

		if (radFemale.isChecked()) {
			strGender = "F";
			values.add("Female");
		}

		if (strGender == null) {
			errors.add("Gender");
		}

		if (chkNews.isChecked()) {
			blnNews = true;
			values.add("News");
		}

		if (chkPromo.isChecked()) {
			blnPromo = true;
			values.add("Promo");
		}

		if (!errors.isEmpty()) {
			// showError(errList);
			Intent intent = new Intent().setClass(this, this.getClass());
			intent.putExtra("email", strEmail);
			intent.putExtra("zipcode", strZipcode);
			intent.putStringArrayListExtra("errors", errors);
			intent.putStringArrayListExtra("values", values);
			startActivity(intent);
			finish();
			return;
		}

		DBAdapter db = new DBAdapter(this);
		UserInfo user = new UserInfo(strEmail, strPassword);
		user.setZipCode(intZipcode);
		user.setGender(strGender);
		user.setNews(blnNews);
		user.setPromo(blnPromo);
		db.insertUser(user);
		Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show();
		finish();

	}

	public void repopulate() {

		for (String value : values) {

			if (value.equals("Email")) {
				txtEmail.setText(strEmail);
			}

			if (value.equals("Zipcode")) {
				txtZipcode.setText(strZipcode);
			}

			if (value.equals("Male")) {
				radMale.setChecked(true);
			}

			if (value.equals("Female")) {
				radFemale.setChecked(true);
			}

			if (value.equals("News")) {
				chkNews.setChecked(true);
			}

			if (value.equals("Promo")) {
				chkPromo.setChecked(true);
			}
		}
	}

	public void showError() {

		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		// Set the message to display
		alertbox.setMessage("Please Enter Valide Information Into Marked Field");
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

			if (error.equals("Confirm")) {
				txtConfirm.setCompoundDrawables(null, null, x, null);
			}

			if (error.equals("Zipcode")) {
				txtZipcode.setCompoundDrawables(null, null, x, null);
			}

			if (error.equals("Gender")) {
				lblGender.setCompoundDrawables(null, null, x, null);
			}
		}
	}
}
