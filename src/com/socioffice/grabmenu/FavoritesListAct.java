package com.socioffice.grabmenu;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FavoritesListAct extends ListActivity {	
		
	private ListView list;
	private Intent intent;
	private ListAdapter listAdapter;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);						
		setContentView(R.layout.favorites_list);
				
		list = getListView();
		listAdapter = new ListAdapter(this, R.layout.restaurant_row);		
		setListAdapter(listAdapter);
					
		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		// Set the message to display
		alertbox.setMessage("You have not logged In");
		
		// Set a positive/yes button and create a listener
		alertbox.setPositiveButton("Sign In", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				positiveOnClick(dialog, which);
			}			
		});
		
		// Set a negative/no button and create a listener
		alertbox.setNegativeButton("Cancel", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				negativeOnClick(dialog, which);
			}			
		});
		
		// display box
		alertbox.show();
		
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				// TODO Auto-generated method stub
				listOnItemClickListener(parent, view, pos, id);
			}			
		});
	}
	
	public void positiveOnClick(DialogInterface dialog, int which) {
		intent = new Intent(this, SignInAct.class);
		startActivity(intent);
	}
	
	public void negativeOnClick(DialogInterface dialog, int which) {
		// Nothing.
	}
	
	public void listOnItemClickListener(AdapterView<?> parent, View v, int pos, long id) {
		intent = new Intent().setClass(this, RestaurantAct.class);
		startActivity(intent);
	}
	
	private class ListAdapter extends ArrayAdapter<Object> {
			
		public ListAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 20;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = vi.inflate(R.layout.restaurant_row, null);
				
			}
			
			return view;
		}
		
	}
			
}
