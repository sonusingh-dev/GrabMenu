package com.socioffice.grabmenu.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	// private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";
	private static final String DB_PATH = "/data/data/com.socioffice.grabmenu/databases/";
	// reference of database
	// private SQLiteDatabase grabmenu;
	// database name
	private static final String DB_NAME = "grabmenu.db";

	private SQLiteDatabase grabmenu;

	private final Context context;

	public DBHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DB_NAME, null, 1);
		this.context = context;
	}

	public void createDataBase() throws IOException {
		
		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}
		
		/*this.getReadableDatabase();

		try {

			copyDataBase();

		} catch (IOException e) {

			throw new Error("Error copying database");

		}*/

	}

	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException {
		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		grabmenu = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);

	}

	@Override
	public synchronized void close() {

		if (grabmenu != null)
			grabmenu.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
}
