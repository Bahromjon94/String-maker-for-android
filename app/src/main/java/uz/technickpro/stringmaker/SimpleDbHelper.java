package uz.technickpro.stringmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SimpleDbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private Context context;

    private static final String DATABASE_NAME = "simple_db.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STRING = "strings";
    private static final String TABLE_ARRAY_CODE = "array_code";

    private static final String KEY_ID = "id";
    private static final String KEY_STRING = "string";
    private static final String VERB_ARRAY = "verb_array";


    public SimpleDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query = "CREATE TABLE " + TABLE_STRING + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_STRING + " TEXT, " + VERB_ARRAY + " TEXT);";
        db.execSQL(Query);

        String Name = "CREATE TABLE " + TABLE_ARRAY_CODE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VERB_ARRAY + " TEXT);";
        db.execSQL(Name);

    }

    public long insertString(String str) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_STRING, str);

        long insertLong = db.insert(TABLE_STRING, null, values);
        db.close();
        return insertLong;
    }



    public String getData() {
        SQLiteDatabase db = getReadableDatabase();
        String s = "select * from " + TABLE_STRING;
        Cursor cursor = db.rawQuery(s, null);
        String result = "";

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = result + cursor.getString(cursor.getColumnIndex(KEY_STRING));
        }
        db.close();
        return result;
    }


    public long insertArray(String str) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VERB_ARRAY, str);

        long insertLong = db.insert(TABLE_ARRAY_CODE, null, values);
        db.close();
        return insertLong;
    }

    public String getArray() {

        SQLiteDatabase db = getReadableDatabase();
        String s = "select * from " + TABLE_ARRAY_CODE;
        Cursor cursor = db.rawQuery(s, null);
        String result = "";

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = result + cursor.getString(cursor.getColumnIndex(VERB_ARRAY));
        }
        db.close();
        return result;

    }

    public void updateArr(long l, String str){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VERB_ARRAY, str);
        db.update(TABLE_ARRAY_CODE, values, l + "=" + KEY_ID, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
