package com.socioffice.grabmenu;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ResourceCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.socioffice.grabmenu.model.DBAdapter;

public class RestaurantListAct extends ListActivity {

	private ListView restList;
	private Spinner spinner;
	private Button btnRefineList;

	private DBAdapter dbAdapter;
	private Cursor cursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_list);

		dbAdapter = new DBAdapter(this);
		cursor = dbAdapter.getRestaurants();
				
		restList = getListView();		
		restList.setDivider(new ColorDrawable(0xffE6E6E6));
		restList.setDividerHeight(1);
		
		spinner = (Spinner) findViewById(R.id.spnSortBy);
		btnRefineList = (Button) findViewById(R.id.btnRefineList);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.sort_by_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinner.setAdapter(adapter);

		RestListAdapter restListAdapter = new RestListAdapter(this, cursor);
		setListAdapter(restListAdapter);

		restList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				// TODO Auto-generated method stub
				listOnItemClick(parent, view, pos, id);
			}
		});

		btnRefineList.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnRefineOnClick(view);
			}
		});

	}

	private class RestListAdapter extends ResourceCursorAdapter {

		public RestListAdapter(Context context, Cursor cursor) {
			// TODO Auto-generated constructor stub
			super(context, R.layout.restaurant_row, cursor);
		}

		@Override
		public View newView(Context context, Cursor cur, ViewGroup parent) {
			LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return li.inflate(R.layout.restaurant_row, parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView lblCity = (TextView) view.findViewById(R.id.lblCity);
			lblCity.setVisibility(View.GONE);
			TextView lblRest = (TextView) view.findViewById(R.id.lblRestaurant);
			TextView lblCuisines = (TextView) view
					.findViewById(R.id.lblCuisines);
			TextView lblLocation = (TextView) view
					.findViewById(R.id.lblLocation);
			TextView lblPrice = (TextView) view.findViewById(R.id.lblPrice);
			TextView lblReviews = (TextView) view.findViewById(R.id.lblReview);
			// cursor.move(position);
			RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

			lblRest.setText(cursor.getString(1));
			lblCuisines.setText(cursor.getString(2));
			lblLocation.setText(cursor.getString(3));
			lblPrice.setText(cursor.getString(4));
			lblReviews.setText(cursor.getString(5));
			float rating = 1.0f;
			try {
				rating = Float.parseFloat(cursor.getString(6));
				ratingBar.setRating(rating);
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}

		}

	}

	public void listOnItemClick(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent().setClass(this, RestaurantAct.class);
		startActivity(intent);
	}

	public void btnRefineOnClick(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent().setClass(this, RefineAct.class);
		startActivity(intent);
	}

}
