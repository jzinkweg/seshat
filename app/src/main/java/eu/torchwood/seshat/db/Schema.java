package eu.torchwood.seshat.db;

import android.provider.BaseColumns;

/**
 * Created by jop on 5/6/16.
 */
public final class Schema {

    public Schema(){}

    public static abstract class Tournament implements BaseColumns {
        public static final String TABLE_NAME = "tournament";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TIMESTAMP = "created";
    }

    public static abstract class Event implements BaseColumns {
        public static final String TABLE_NAME = "event";
        public static final String COLUMN_NAME_PARENT_TOURNAMENT = "tournamentid";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_FORMAT = "format";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_NUMBER_OF_ROUNDS = "rounds";
    }

}
