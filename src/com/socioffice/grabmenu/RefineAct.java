package com.socioffice.grabmenu;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class RefineAct extends ListActivity {

	private String focus;
	private String[] strWhat;
	private String[] strWhere;

	private ListView list;
	private EditText txtWhat;
	private EditText txtWhere;
	private Button btnCancel;
	private Button btnApply;
	
	private Drawable drawable;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.refine_list);

		list = getListView();

		txtWhat = (EditText) findViewById(R.id.txtWhat);
		txtWhere = (EditText) findViewById(R.id.txtWhere);

		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnApply = (Button) findViewById(R.id.btnApply);
		
		drawable = getResources().getDrawable(R.drawable.clear);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
	
		txtWhat.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				txtWhatOnTouch(view, event);
				return false;
			}
		});

		txtWhere.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				txtWhereOnTouch(view, event);
				return false;
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnCancelOnClick(view);
			}
		});

		btnApply.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnApplyOnClick(view);
			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				// TODO Auto-generated method stub
				listOnItemClick(parent, view, pos, id);
			}
		});
		
		strWhat = getResources().getStringArray(R.array.what_array);
		strWhere = getResources().getStringArray(R.array.where_array);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			if (focus.equals("What")) {
				txtWhat.setText(data.getStringExtra("item"));
			}
			if (focus.equals("Where")) {
				txtWhere.setText(data.getStringExtra("item"));
			}
		}
	}

	public void btnCancelOnClick(View v) {
		finish();
	}

	public void btnApplyOnClick(View v) {
		finish();
	}
	
	public boolean txtWhatOnTouch(View view, MotionEvent event) {
		focus = "What";
		setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row,
				strWhat));
		if (txtWhat.getCompoundDrawables() == null) {
			// cross is not being shown so no need to handle
			return false;
		}
		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			// only respond to the down type
			return false;
		}
		if (event.getX() > txtWhat.getMeasuredWidth()
				- txtWhat.getPaddingRight() - drawable.getIntrinsicWidth()) {
			txtWhat.setText("");
			return true;
		} else {
			return false;
		}
	}
	
	public boolean txtWhereOnTouch(View view, MotionEvent event) {
		focus = "Where";
		setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row,
				strWhere));
		setListAdapter(new ArrayAdapter<String>(this, R.layout.item_row,
				strWhere));

		if (txtWhere.getCompoundDrawables() == null) {
			// cross is not being shown so no need to handle
			return false;
		}
		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			// only respond to the down type
			return false;
		}
		if (event.getX() > txtWhere.getMeasuredWidth()
				- txtWhere.getPaddingRight() - drawable.getIntrinsicWidth()) {
			txtWhere.setText("");
			return true;
		} else {
			return false;
		}
	}
	
	public void listOnItemClick(AdapterView<?> parent, View view, int pos, long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent().setClass(this, ItemListAct.class);
		String strItem = ((TextView) view).getText().toString();
		if (strItem.equals("Current Location")) {
			txtWhere.setText(strItem);
			return;
		}
		intent.putExtra("item", strItem);
		startActivityForResult(intent, 0);
	}
}
