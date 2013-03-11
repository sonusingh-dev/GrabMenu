package com.socioffice.grabmenu;

import android.app.ListActivity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.socioffice.grabmenu.model.DBAdapter;

public class CityListAct extends ListActivity {

	private String[] cities;
	private ListView cityList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_list);

		DBAdapter dbAdapter = new DBAdapter(this);
		cities = dbAdapter.getCities();
		dbAdapter.close();

		cityList = getListView();
		cityList.setDivider(new ColorDrawable(0xffE6E6E6));
		cityList.setDividerHeight(1);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row, cities));

		cityList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				// TODO Auto-generated method stub
				cityListOnItemClick(parent, view, pos, id);
			}
		});

		// getCities();
	}

	public void cityListOnItemClick(AdapterView<?> parent, View v, int pos,
			long id) {
		InitializeGrabMenu app = (InitializeGrabMenu) getApplicationContext();
		String city = ((TextView) v).getText().toString();
		app.setCity(city);
		finish();
	}
	
}
