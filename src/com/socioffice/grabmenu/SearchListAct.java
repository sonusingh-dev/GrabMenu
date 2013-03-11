package com.socioffice.grabmenu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SearchListAct extends ListActivity {
				
	private ListView list;
	private TextView lblItem;
	private Button btnCancel;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_list);
		
		Intent intent = getIntent();		
		InitializeGrabMenu app = (InitializeGrabMenu) getApplicationContext();
		
		list = getListView();
		lblItem = (TextView) findViewById(R.id.lblTitle);
		btnCancel = (Button) findViewById(R.id.btnCancel);		
								
		list.setOnItemClickListener(new OnItemClickListener() {
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
		
		String strCity = app.getCity();
		String strItem = intent.getStringExtra("item");
				
		lblItem.setText(strItem);
		btnCancel.setText(strCity);
		
		String[] strCuisines = getResources().getStringArray(R.array.cuisines_array);
		String[] strMeals = getResources().getStringArray(R.array.meals_array);
		String[] strFeatures = getResources().getStringArray(R.array.features_array);

		if(strItem.equals("Cuisines")) {
			setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row, strCuisines));
		}
		
		if( strItem.equals("Meals") ) {
			setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row, strMeals));		
		}
		
		if( strItem.equals("Features") ) {
			setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row, strFeatures));
		}
						
	}

	public void listOnItemClick(AdapterView<?> parent, View view, int pos, long id) {
		// TODO Auto-generated method stub					
		Intent data = new Intent().setClass(this, SearchAct.class);
		String item = ((TextView) view).getText().toString();		
		data.putExtra("item", item);
		setResult(RESULT_OK, data);
		finish();
	}

	public void btnCancelOnClick(View view) {
		// TODO Auto-generated method stub		
		finish();
	}
}
