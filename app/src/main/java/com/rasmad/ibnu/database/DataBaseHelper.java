package com.rasmad.ibnu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DataBaseHelper extends SQLiteOpenHelper {
   public static final String col_id = "id";
   public static final String col_username = "username";
   public static final String dbname = "user";
   public static final String tbname = "username";

   public DataBaseHelper(Context context) {
      super(context, dbname, null, 1);
   }

   public Cursor getUsername() {
      SQLiteDatabase db = getWritableDatabase();
      Cursor res = db.rawQuery("Select * from " + tbname, null);
	  return res;
   }

   public boolean insertUsername(String username) {
      SQLiteDatabase db = getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(col_username, username);
      long result = db.insert(tbname, null, contentValues);
      db.close();
      if (result == -1) {
		  return false;
	  } else {
		  return true;
	  }
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE " + tbname + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT)");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + tbname);
   }

   public boolean updateUsername(String id, String username) {
      SQLiteDatabase db = getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(col_username, username);
      int result = db.update(tbname, contentValues, "ID =?", new String[]{id});
	  if(result > 0) {
		  return false;
	  } else {
		  return true;
	  }
   }
}
