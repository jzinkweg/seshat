package eu.torchwood.seshat.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.torchwood.seshat.entity.Event;

/**
 * Created by jop on 5/6/16.
 */
public class EventManager extends DbHelper {
    public EventManager(Context context) {
        super(context);
    }

    public List<Event> getEvents(Long tournamentId){
        List<Event> events = new ArrayList<>();

        String[] projection = {
                Schema.Event._ID,
                Schema.Event.COLUMN_NAME_DESCRIPTION,
                Schema.Event.COLUMN_NAME_FORMAT,
                Schema.Event.COLUMN_NAME_NUMBER_OF_ROUNDS,
                Schema.Event.COLUMN_NAME_TIMESTAMP
        };
        String sortOrder = Schema.Event.COLUMN_NAME_TIMESTAMP + " DESC";
        String where = Schema.Event.COLUMN_NAME_PARENT_TOURNAMENT + " = ?";

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(
                Schema.Event.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                where,                                // The columns for the WHERE clause
                toArray(tournamentId.toString()),                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if(c.moveToFirst()){
            do {
                Long id = c.getLong(c.getColumnIndexOrThrow(Schema.Event._ID));
                Date timestamp = new Date(c.getLong(c.getColumnIndexOrThrow(Schema.Event.COLUMN_NAME_TIMESTAMP)));
                String description = c.getString(c.getColumnIndexOrThrow(Schema.Event.COLUMN_NAME_DESCRIPTION));
                String format = c.getString((c.getColumnIndexOrThrow(Schema.Event.COLUMN_NAME_FORMAT)));
                Integer rounds = c.getInt(c.getColumnIndexOrThrow(Schema.Event.COLUMN_NAME_NUMBER_OF_ROUNDS));
                Event e = new Event(id, tournamentId, description, format, rounds, timestamp);
                events.add(e);
            } while(c.moveToNext());
        }
        return events;
    }
}
