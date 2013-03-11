package com.socioffice.grabmenu;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.socioffice.grabmenu.model.DBAdapter;

public class ItemListAct extends ListActivity {
	
	//private String strCity;
	private String strItem;
	private String[] strCuisines;
	private String[] strMeals;
	private String[] strFeatures;
	
	private ListView itemList;
	private Button btnCancel;
	
	private Intent intent;
	//private InitializeMenuPages app;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_list);

		DBAdapter dbAdapter = new DBAdapter(this);		
				
		intent = getIntent();		
		//app = (InitializeMenuPages) getApplicationContext();

		itemList = getListView();
		itemList.setDivider(new ColorDrawable(0xffE6E6E6));
		itemList.setDividerHeight(1);
		
		TextView item = (TextView) findViewById(R.id.lblTitle);
		btnCancel = (Button) findViewById(R.id.btnCancel);
						
		//strCity = app.getCity();
		strItem = intent.getStringExtra("item");		
		strMeals = getResources().getStringArray(R.array.meals_array);
		strFeatures = getResources().getStringArray(R.array.features_array);
		
		itemList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				// TODO Auto-generated method stub
				listOnItemClick(parent, view, pos, id);
			}			
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnCancelOnClick(view);
			}			
		});
				
		item.setText(strItem);
		//btnCancel.setText(strCity);		
		
		if (strItem.equals("Cuisines")) {			
			strCuisines = dbAdapter.getCuisines();
			setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row,
					strCuisines));
		}

		if (strItem.equals("Meals")) {			
			setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row,
					strMeals));
		}

		if (strItem.equals("Features")) {			
			setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row,
					strFeatures));
		}

	}
	
	public void listOnItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent data = new Intent();
		String strItem = ((TextView) view).getText().toString();
		data.putExtra("item", strItem);
		setResult(RESULT_OK, data);
		finish();
	}
	
	public void btnCancelOnClick(View v) {
		// TODO Auto-generated method stub
		Intent data = new Intent();
		setResult(RESULT_OK, data);
		finish();
	}
}
