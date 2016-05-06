package eu.torchwood.seshat.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.torchwood.seshat.entity.*;
import eu.torchwood.seshat.entity.Tournament;

import static eu.torchwood.seshat.db.Schema.Tournament.*;

/**
 * Created by jop on 5/6/16.
 */
public class TournamentManager extends DbHelper {
    public TournamentManager(Context context) {
        super(context);
    }

    public List<Tournament> getTournaments(){
        List<Tournament> tournaments = new ArrayList<>();

        String[] projection = {
                _ID,
                COLUMN_NAME_DESCRIPTION,
                COLUMN_NAME_TIMESTAMP
        };
        String sortOrder = COLUMN_NAME_TIMESTAMP + " DESC";

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(
                TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if(c.moveToFirst()){
            do {
                Long id = c.getLong(c.getColumnIndexOrThrow(_ID));
                Date timestamp = new Date(c.getLong(c.getColumnIndexOrThrow(COLUMN_NAME_TIMESTAMP)));
                String description = c.getString(c.getColumnIndexOrThrow(COLUMN_NAME_DESCRIPTION));
                Tournament t = new Tournament(id, description, timestamp);
                tournaments.add(t);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return tournaments;
    }

    public void createTournament(Tournament t){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_DESCRIPTION, t.getDescription());
        values.put(COLUMN_NAME_TIMESTAMP, t.getCreated().getTime());
        Long tournamentId = db.insert(TABLE_NAME, null, values);
        t.setId(tournamentId);
        db.close();
    }

    public void deleteTournament(Long id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, _ID + " = ?", toArray(id.toString()));
    }
}
