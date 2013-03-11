package com.socioffice.grabmenu.model;

import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {

	private DBHelper dbHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context context) {
		// TODO Auto-generated constructor stub
		dbHelper = new DBHelper(context);
		try {
			dbHelper.createDataBase();
			dbHelper.openDataBase();
			dbHelper.close();
			db = dbHelper.getWritableDatabase();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	/*
	 * public void insertUser(UserInfo user) { ContentValues iniValues = new
	 * ContentValues(); db.insert(DATABASE_TABLE, null, iniValues); }
	 */

	// Retrieves all the titles
	public String[] getCities() {

		final String KEY_ROWID = "_id";
		final String KEY_CITY = "City_Name";
		final String DATABASE_TABLE = "Cities";

		Cursor cursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_CITY }, null, null, null, null, KEY_CITY);
		String[] cities = new String[cursor.getCount()];
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToNext();
			cities[i] = cursor.getString(1);
		}

		return cities;
	}

	public String[] getCuisines() {

		final String KEY_ROWID = "_id";
		final String KEY_CUISINE = "Cuisine_Name";
		final String DATABASE_TABLE = "Cuisines";

		Cursor cursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_CUISINE }, null, null, null, null, KEY_CUISINE);
		String[] cuisines = new String[cursor.getCount()];
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToNext();
			cuisines[i] = cursor.getString(1);
		}

		return cuisines;

	}

	public Cursor getRestaurants() {

		final String KEY_ROWID = "_id";
		final String KEY_REST = "Name";
		final String KEY_CUISINES = "Cuisines";
		final String KEY_LOCATION = "Location";
		final String KEY_PRICE = "Price";
		final String KEY_REVIEWS = "Reviews";
		final String KEY_RATINGS = "Ratings";
		final String DATABASE_TABLE = "Restaurants";

		return db.query(DATABASE_TABLE,
				new String[] { KEY_ROWID, KEY_REST, KEY_CUISINES, KEY_LOCATION,
						KEY_PRICE, KEY_REVIEWS, KEY_RATINGS }, null, null,
				null, null, KEY_ROWID);

	}
	
	private static final String KEY_USERID = "_id";
	private static final String KEY_EMAIL = "Email";
	private static final String KEY_PASSWORD = "Password";
	private static final String KEY_ZIPCODE = "Zipcode";
	private static final String KEY_GENDER = "Gender";
	private static final String KEY_NEWS = "News";
	private static final String KEY_PROMO = "Promo";
	private static final String TABLE_USER = "User";
	
	public Cursor getUser(String strEmail) {
		
		return db.query(true, TABLE_USER,
				new String[] { KEY_USERID, KEY_EMAIL, KEY_PASSWORD, KEY_ZIPCODE,
						KEY_GENDER, KEY_NEWS, KEY_PROMO }, KEY_EMAIL + "=" + strEmail, null, null,
				null, null, null);
	}
	
	// Insert a title into the database
	public long insertUser(UserInfo user) {
		
		ContentValues iniValues = new ContentValues();
		iniValues.put(KEY_EMAIL, user.getEmail());
		iniValues.put(KEY_PASSWORD, user.getPassword());
		iniValues.put(KEY_ZIPCODE, user.getZipCode());
		iniValues.put(KEY_GENDER, user.getGender());
		iniValues.put(KEY_NEWS, user.isNews());
		iniValues.put(KEY_PROMO, user.isPromo());
		return db.insert(TABLE_USER, null, iniValues);
	}
	
	public void close() {
		db.close();
	}
}
