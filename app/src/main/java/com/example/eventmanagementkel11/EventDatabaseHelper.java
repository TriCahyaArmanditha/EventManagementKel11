package com.example.eventmanagementkel11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_EVENT_DATE = "event_date";
    public static final String COLUMN_IMAGE_NAME = "image_name";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_EVENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EVENT_NAME + " TEXT, " +
                    COLUMN_EVENT_DATE + " TEXT, " +
                    COLUMN_IMAGE_NAME + " TEXT" +
                    ");";

    public EventDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }
}
