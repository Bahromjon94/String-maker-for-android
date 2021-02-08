package uz.technickpro.stringmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private Context context;

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_VERB = "verb";
    private static final String VERB_ID = "id";
    private static final String VERB_WORD = "word";
    private static final String VERB_V1 = "verb1";
    private static final String VERB_V2 = "verb2";
    private static final String VERB_V3 = "verb3";
    private static final String VERB_DESCRIPTION = "description";
    private static final String VERB_PRON_V1 = "pronounceV1";
    private static final String VERB_PRON_V2 = "pronounceV2";
    private static final String VERB_PRON_V3 = "pronounceV3";
    private static final String VERB_IS_FAVOURITE = "bookmarks";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query_Table = " CREATE TABLE " + TABLE_VERB + "("
                + VERB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VERB_WORD + " TEXT, " + VERB_V1 + " TEXT, "
                + VERB_V2 + " TEXT, " + VERB_V3 + " TEXT, "
                + VERB_DESCRIPTION + " TEXT, " + VERB_PRON_V1 + " TEXT, "
                + VERB_PRON_V2 + " TEXT, " + VERB_PRON_V3 + " TEXT);";

        db.execSQL(Query_Table);
    }

    public long insertString(String str) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VERB_WORD, str);
        long insertLong = db.insert(TABLE_VERB, null, values);
        db.close();
        return insertLong;

    }

    public long insertVerb(Verb verb) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VERB_WORD, verb.getWord());
        values.put(VERB_V1, verb.getV1());
        values.put(VERB_V2, verb.getV2());
        values.put(VERB_V3, verb.getV3());
        values.put(VERB_DESCRIPTION, verb.getDescription());
        values.put(VERB_PRON_V1, verb.getPronV1());
        values.put(VERB_PRON_V2, verb.getPronV2());
        values.put(VERB_PRON_V3, verb.getPronV3());

        long insertVerb = db.insert(TABLE_VERB, null, values);
        db.close();

        return insertVerb;
    }

    public List<String> strings() {
        List<String> strings = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String s = "select * from " + TABLE_VERB;
        Cursor cursor = db.rawQuery(s, null);

        if (cursor.moveToFirst()) {
            do {
                strings.add(cursor.getString(cursor.getColumnIndex(VERB_WORD)));
            }
            while (cursor.moveToNext());
        }

        db.close();
        return strings;
    }

    public String getData() {
        SQLiteDatabase db = getReadableDatabase();
        String s = "select * from " + TABLE_VERB;
        Cursor cursor = db.rawQuery(s, null);
        String result = "";

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = result + cursor.getString(cursor.getColumnIndex(VERB_WORD));
        }
        db.close();
        return result;
    }




    public String getArray() {

        SQLiteDatabase db = getReadableDatabase();
        String s = "select * from " + TABLE_VERB;
        Cursor cursor = db.rawQuery(s, null);

        String result = "";

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = result + cursor.getString(cursor.getColumnIndex(VERB_WORD));
        }
        return result;
    }

    public void updateVerb(String v1, String v2, String v3) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VERB_V1, v1);
        values.put(VERB_V2, v2);
        values.put(VERB_V3, v3);

        db.close();
    }

    public List<Verb> verbs() {

        List<Verb> verbs = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String s = "select * from " + TABLE_VERB;
        Cursor cursor = db.rawQuery(s, null);

        if (cursor.moveToFirst()) {
            do {
                verbs.add(new Verb(cursor.getInt(cursor.getColumnIndex(VERB_ID)),
                        cursor.getString(cursor.getColumnIndex(VERB_WORD)),
                        cursor.getString(cursor.getColumnIndex(VERB_V1)),
                        cursor.getString(cursor.getColumnIndex(VERB_V2)),
                        cursor.getString(cursor.getColumnIndex(VERB_V3))));
            }
            while (cursor.moveToNext());
        }
        db.close();
        return verbs;
    }


    public void updateVerb(long l, String word, String v1, String v2, String v3) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VERB_WORD, word);
        values.put(VERB_V1, v1);
        values.put(VERB_V2, v2);
        values.put(VERB_V3, v3);

        db.update(TABLE_VERB, values, VERB_ID + "=" + l, null);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
