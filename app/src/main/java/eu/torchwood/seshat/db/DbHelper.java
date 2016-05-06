package eu.torchwood.seshat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static eu.torchwood.seshat.db.Schema.*;

/**
 * Created by jop on 5/6/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Seshat.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    protected String[] toArray(String... args){
        return args;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        while(oldVersion < newVersion){
            oldVersion++;
            if(oldVersion == 1){
                createV1(db);
            }
        }
    }

    private void createV1(SQLiteDatabase db){
        String createTournament = "CREATE TABLE " + Tournament.TABLE_NAME + "( "
                + Tournament._ID + " INTEGER PRIMARY KEY, "
                + Tournament.COLUMN_NAME_DESCRIPTION + " TEXT, "
                + Tournament.COLUMN_NAME_TIMESTAMP + " INTEGER"
                + ")";
        String createEvent = "CREATE TABLE " + Event.TABLE_NAME + "( "
                + Event._ID + " INTEGER PRIMARY KEY, "
                + Event.COLUMN_NAME_PARENT_TOURNAMENT + " INTEGER, "
                + Event.COLUMN_NAME_DESCRIPTION + " TEXT, "
                + Event.COLUMN_NAME_FORMAT + " TEXT, "
                + Event.COLUMN_NAME_NUMBER_OF_ROUNDS + " INTEGER, "
                + Event.COLUMN_NAME_TIMESTAMP + " INTEGER"
                + ")";
        db.execSQL(createTournament);
        db.execSQL(createEvent);
    }
}
