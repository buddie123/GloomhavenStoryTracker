package buddie123.gloomhavenstorytracker.database.user;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


/*
 * Author: Robert Reed
 * Contains an inner class for each user data table in the read-write user
 * database. Each inner class contains a series of public constants:
 * TABLE_NAME, CONTENT_URI, and one for each non _ID column in the table.
 * There is also a method in each class to create a URI for a specific
 * element using it's id.
 */
public class UserDBDescription {
    // ContentProvider's name
    public static final String AUTHORITY =
            "buddie123.gloomhavenstorytracker.database.user";

    // base URI used to interact with the ContentProvider
    private static final Uri BASE_CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    /*
     * the inner classes that define the constants used to refer to
     * each table in the database. There is one inner class per each
     * table in the database
     */


    // the Attempts table records an entry for each game, or attempt, that
    // a party makes for each location that a party has attempted/completed.
    public static final class Attempts implements BaseColumns {
        public static final String TABLE_NAME = "attempts"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID = "party_id";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_SUCCESSFUL_ID = "successful";
        public static final String COLUMN_DATE_ID = "date_id";

        public static Uri buildAttemptUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the AttemptParticipants table records which characters in a given party participated
    // in a given attempt.
    public static final class AttemptParticipants implements BaseColumns {
        public static final String TABLE_NAME = "attempt_participants"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_ATTEMPT_ID = "attempt_id";
        public static final String COLUMN_CHARACTER_ID = "character_id";

        public static Uri buildAttemptParticipantUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the AttemptNonParticipants table records which characters in a given party did not
    // participate in a given attempt.
    public static final class AttemptNonParticipants implements BaseColumns {
        public static final String TABLE_NAME = "attempt_non_participants"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_ATTEMPT_ID = "attempt_id";
        public static final String COLUMN_CHARACTER_ID = "character_id";

        public static Uri buildAttemptNonParticipantUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


    // Notes for each
    public static final class AttemptNotes implements BaseColumns {
        public static final String TABLE_NAME = "attempt_notes";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_ATTEMPT_ID = "attempt_id";
        public static final String COLUMN_NOTE = "note";

        public static Uri buildAttemptNoteUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the Parties table records the name of each party that has been created
    public static final class Parties implements BaseColumns {
        public static final String TABLE_NAME = "parties"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_NAME = "name";

        public static Uri buildPartyUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the Characters table records the characters that are in each party
    public static final class Characters implements BaseColumns {
        public static final String TABLE_NAME = "characters"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID = "party_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CLASS_ID = "class_id";

        public static Uri buildCharacterUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class UnlockedCharacterClasses implements BaseColumns {
        public static final String TABLE_NAME = "character_classes_unlocked";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID= "party_id";
        public static final String COLUMN_CLASS_ID = "class_id";

        public static Uri buildUnlockedCharacterClassUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the UnlockedLocations table tracks which locations have been unlocked by the party
    // along with the completed location that unlocked the given location
    public static final class UnlockedLocations implements BaseColumns {
        public static final String TABLE_NAME = "unlocked_locations"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID = "party_id";
        public static final String COLUMN_UNLOCKED_LOCATION_ID = "unlocked_location_id";
        public static final String COLUMN_UNLOCKING_LOCATION_ID = "unlocking_location_id";
        public static Uri buildUnlockedLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the BlockedLocations table tracks which locations have been blocked to the party
    // along with the completed location that blocked the given location
    public static final class BlockedLocations implements BaseColumns {
        public static final String TABLE_NAME = "blocked_locations"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID = "party_id"; // primary key / foreign key
        public static final String COLUMN_BLOCKED_LOCATION_ID = "blocked_location_ID";
        public static final String COLUMN_BLOCKING_LOCATION_ID = "blocking_location_ID"; // TODO is this field necessary?

        public static Uri buildBlockedLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the CompletedLocations table records which locations have been completed by each
    // party along with the timestamp of the Successful Attempt SS
    public static final class CompletedLocations implements BaseColumns {
        public static final String TABLE_NAME = "completed_locations"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID = "party_id";
        public static final String COLUMN_COMPLETED_LOCATION_ID = "completed_location_id";
        public static final String COLUMN_COMPLETED_DATE_ID = "completed_date_id";

        public static Uri buildCompletedLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the LockedLocations table records which locations have not been completed by
    // each party
    public static final class LockedLocations implements BaseColumns {
        public static final String TABLE_NAME = "locked_locations"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID = "party_id";
        public static final String COLUMN_LOCATION_ID = "location_id";

        public static Uri buildLockedLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the GlobalAchievementsActive table records which locations have been completed
    // by each party
    public static final class ActiveGlobalAchievements implements BaseColumns {
        public static final String TABLE_NAME = "active_global_achievement"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID = "party_id";
        public static final String COLUMN_GLOBAL_ACHIEVEMENT_ID = "global_achievement_id";

        public static Uri buildActiveGlobalAchievementUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the GlobalAchievementsActive table records which locations have been completed
    // by each party
    public static final class ActivePartyAchievements implements BaseColumns {
        public static final String TABLE_NAME = "active_party_achievements"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_PARTY_ID = "party_id";
        public static final String COLUMN_PARTY_ACHIEVEMENT_ID = "party_achievement_id";

        public static Uri buildActivePartyAchievementUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class Dates implements BaseColumns {
        public static final String TABLE_NAME = "dates";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_YEAR = "year";
        public static final String COLUMN_MONTH = "month";
        public static final String COLUMN_DAY = "day";

        public static Uri buildDateUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
