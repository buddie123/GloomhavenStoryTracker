package buddie123.gloomhavenstorytracker.database.user;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class UserDBContentProvider extends ContentProvider {
    // used to access the database
    private UserDBHelper dbHelper;

    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    // constants used with UriMatcher to determine operation to perform
    // IMPORTANT: For each table, the single row access constant should be odd
    // and one less than the table access constant. The logic in the CRUD
    // operations depend on this arraignment.
    public static final int ONE_PARTY = 1;
    public static final int PARTIES = 2;

    public static final int ONE_CHARACTER = 3;
    public static final int CHARACTERS = 4;

    public static final int ONE_ATTEMPT = 5;
    public static final int ATTEMPTS = 6;

    public static final int ONE_ATTEMPT_PARTICIPANT = 7;
    public static final int ATTEMPT_PARTICIPANTS = 8;

    public static final int ONE_ATTEMPT_NON_PARTICIPANT = 9;
    public static final int ATTEMPT_NON_PARTICIPANTS = 10;

    public static final int ONE_UNLOCKED_LOCATION = 11;
    public static final int UNLOCKED_LOCATIONS = 12;

    public static final int ONE_BLOCKED_LOCATION = 13;
    public static final int BLOCKED_LOCATIONS = 14;

    public static final int ONE_COMPLETED_LOCATION = 15;
    public static final int COMPLETED_LOCATIONS = 16;

    public static final int ONE_LOCKED_LOCATION = 17;
    public static final int LOCKED_LOCATIONS = 18;

    public static final int ONE_ATTEMPT_NOTE = 19;
    public static final int ATTEMPT_NOTES = 20;

    public static final int ONE_UNLOCKED_CHARACTER_CLASS = 21;
    public static final int UNLOCKED_CHARACTER_CLASSES = 22;

    public static final int ONE_ACTIVE_GLOBAL_ACHIEVEMENT = 23;
    public static final int ACTIVE_GLOBAL_ACHIEVEMENTS = 24;

    public static final int ONE_ACTIVE_PARTY_ACHIEVEMENT = 25;
    public static final int ACTIVE_PARTY_ACHIEVEMENTS = 26;

    public static final int ONE_DATE = 27;
    public static final int DATES = 28;
    // IMPORTANT: For each table added to this database, the single row
    // access constant should be odd and one less than the table access
    // constant. The logic in the CRUD operations depends on this arraignment.


    // static block to configure this ContentProvider's UriMatcher
    // The StoryDBDescription.XXXXXXX.TABLE_NAME + "/#" appends a numerical
    // wildcard to the end of the Uri. This is so that Uri's of this form
    // can have the row _ID appended to the end and be easily parsed.
    static {
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.Parties.TABLE_NAME + "/#", ONE_PARTY);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.Parties.TABLE_NAME, PARTIES);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.Characters.TABLE_NAME + "/#", ONE_CHARACTER);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.Characters.TABLE_NAME, CHARACTERS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.Attempts.TABLE_NAME + "/#", ONE_ATTEMPT);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.Attempts.TABLE_NAME, ATTEMPTS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.AttemptParticipants.TABLE_NAME + "/#", ONE_ATTEMPT_PARTICIPANT);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.AttemptParticipants.TABLE_NAME, ATTEMPT_PARTICIPANTS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.AttemptNonParticipants.TABLE_NAME + "/#", ONE_ATTEMPT_NON_PARTICIPANT);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.AttemptNonParticipants.TABLE_NAME, ATTEMPT_NON_PARTICIPANTS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.UnlockedLocations.TABLE_NAME + "/#", ONE_UNLOCKED_LOCATION);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.UnlockedLocations.TABLE_NAME, UNLOCKED_LOCATIONS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.BlockedLocations.TABLE_NAME + "/#", ONE_BLOCKED_LOCATION);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.BlockedLocations.TABLE_NAME, BLOCKED_LOCATIONS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.CompletedLocations.TABLE_NAME + "/#", ONE_COMPLETED_LOCATION);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.CompletedLocations.TABLE_NAME, COMPLETED_LOCATIONS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.LockedLocations.TABLE_NAME + "/#", LOCKED_LOCATIONS);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.LockedLocations.TABLE_NAME, ONE_LOCKED_LOCATION);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.AttemptNotes.TABLE_NAME + "/#", ONE_ATTEMPT_NOTE);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.AttemptNotes.TABLE_NAME, ATTEMPT_NOTES);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.UnlockedCharacterClasses.TABLE_NAME + "/#", ONE_UNLOCKED_CHARACTER_CLASS);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.UnlockedCharacterClasses.TABLE_NAME, UNLOCKED_CHARACTER_CLASSES);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.ActiveGlobalAchievements.TABLE_NAME + "/#", ONE_ACTIVE_GLOBAL_ACHIEVEMENT);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.ActiveGlobalAchievements.TABLE_NAME, ACTIVE_GLOBAL_ACHIEVEMENTS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.ActivePartyAchievements.TABLE_NAME + "/#", ONE_ACTIVE_PARTY_ACHIEVEMENT);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.ActivePartyAchievements.TABLE_NAME, ACTIVE_PARTY_ACHIEVEMENTS);

        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.Dates.TABLE_NAME + "/#", ONE_DATE);
        uriMatcher.addURI(UserDBDescription.AUTHORITY,
                UserDBDescription.Dates.TABLE_NAME, DATES);

    }

    @Override
    public boolean onCreate() {
        dbHelper = new UserDBHelper(getContext());
        return true;
        // TODO verify that this return value computation is correct
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // initialize the queryBuilder object used to get the cursor with the query results
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // set the appropriate table to query based on the given uri
        queryBuilder.setTables(getTableName(uri));

        // if querying a single element, get the match on the _ID
        if (uriMatcher.match(uri) % 2 == 1) {
            queryBuilder.appendWhere(BaseColumns._ID + "=" + uri.getLastPathSegment());
        }

        // execute the query
        Cursor cursor = queryBuilder.query(dbHelper.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);

        // set observer so that changes to the database will reflect in the cursor
        if(getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
        // TODO Research how this method should be implemented/used
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri newElementUri = null;

        // insert into database. ignore insert if the constraints aren't met
        long rowID = dbHelper.getWritableDatabase().insertWithOnConflict(
                getTableName(uri), null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);

        // get the uri for inserted element
        if(rowID > 0) {
            switch (uriMatcher.match(uri)) {
                case PARTIES:
                    newElementUri = UserDBDescription.Parties.buildPartyUri(rowID);
                    break;
                case CHARACTERS:
                    newElementUri = UserDBDescription.Characters.buildCharacterUri(rowID);
                    break;
                case ATTEMPTS:
                    newElementUri = UserDBDescription.Attempts.buildAttemptUri(rowID);
                    break;
                case ATTEMPT_PARTICIPANTS:
                    newElementUri = UserDBDescription.AttemptParticipants.buildAttemptParticipantUri(rowID);
                    break;
                case ATTEMPT_NON_PARTICIPANTS:
                    newElementUri = UserDBDescription.AttemptNonParticipants.buildAttemptNonParticipantUri(rowID);
                    break;
                case UNLOCKED_LOCATIONS:
                    newElementUri = UserDBDescription.UnlockedLocations.buildUnlockedLocationUri(rowID);
                    break;
                case BLOCKED_LOCATIONS:
                    newElementUri = UserDBDescription.BlockedLocations.buildBlockedLocationUri(rowID);
                    break;
                case COMPLETED_LOCATIONS:
                    newElementUri = UserDBDescription.CompletedLocations.buildCompletedLocationUri(rowID);
                    break;
                case LOCKED_LOCATIONS:
                    newElementUri = UserDBDescription.LockedLocations.buildLockedLocationUri(rowID);
                    break;
                case ATTEMPT_NOTES:
                    newElementUri = UserDBDescription.AttemptNotes.buildAttemptNoteUri(rowID);
                    break;
                case UNLOCKED_CHARACTER_CLASSES:
                    newElementUri = UserDBDescription.UnlockedCharacterClasses.buildUnlockedCharacterClassUri(rowID);
                    break;
                case ACTIVE_GLOBAL_ACHIEVEMENTS:
                    newElementUri = UserDBDescription.ActiveGlobalAchievements.buildActiveGlobalAchievementUri(rowID);
                    break;
                case ACTIVE_PARTY_ACHIEVEMENTS:
                    newElementUri = UserDBDescription.ActivePartyAchievements.buildActivePartyAchievementUri(rowID);
                    break;
                case DATES:
                    newElementUri = UserDBDescription.Dates.buildDateUri(rowID);
                    break;
            }
            // update the content resolver that the specific table has been changed
            if(getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }
        return newElementUri;
    }

    // delete the row in the given table with the given ID
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // get the _ID field of the row to delete
        String id = uri.getLastPathSegment();

        // delete the rows in the appropriate table, if able
        int numberOfRowsDeleted = dbHelper.getWritableDatabase().delete(
                getTableName(uri), BaseColumns._ID + "=" + id, selectionArgs);

        // notify the contentResolver if a table has been changed
        if (numberOfRowsDeleted != 0) {
            if(getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }

        // return how many rows were deleted
        return numberOfRowsDeleted;
    }

    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        String id = uri.getLastPathSegment();

        // update the rows in the appropriate table, if able
        int numberOfRowsUpdated = dbHelper.getWritableDatabase().update(
                getTableName(uri), contentValues, BaseColumns._ID + "=" + id, selectionArgs);

        // notify the contentResolver if a table has been changed
        if (numberOfRowsUpdated != 0) {
            if(getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }

        // return how many rows were updated
        return numberOfRowsUpdated;
    }

    // return a table name based on a given Uri, or throw an error if invalid
    // note: the switch logic expects that whole table lookups will be even and that
    // single row lookups will odd and use a constant one number less than the whole
    // table lookup constant
    private String getTableName(Uri uri) {
        String tableName;
        switch (uriMatcher.match(uri) % 2 == 0 ? uriMatcher.match(uri) : uriMatcher.match(uri) + 1) {
            case PARTIES:
                tableName = UserDBDescription.Parties.TABLE_NAME;
                break;
            case CHARACTERS:
                tableName = UserDBDescription.Characters.TABLE_NAME;
                break;
            case ATTEMPTS:
                tableName = UserDBDescription.Attempts.TABLE_NAME;
                break;
            case ATTEMPT_PARTICIPANTS:
                tableName = UserDBDescription.AttemptParticipants.TABLE_NAME;
                break;
            case ATTEMPT_NON_PARTICIPANTS:
                tableName = UserDBDescription.AttemptNonParticipants.TABLE_NAME;
                break;
            case UNLOCKED_LOCATIONS:
                tableName = UserDBDescription.UnlockedLocations.TABLE_NAME;
                break;
            case BLOCKED_LOCATIONS:
                tableName = UserDBDescription.BlockedLocations.TABLE_NAME;
                break;
            case COMPLETED_LOCATIONS:
                tableName = UserDBDescription.CompletedLocations.TABLE_NAME;
                break;
            case LOCKED_LOCATIONS:
                tableName = UserDBDescription.LockedLocations.TABLE_NAME;
                break;
            case ATTEMPT_NOTES:
                tableName = UserDBDescription.AttemptNotes.TABLE_NAME;
                break;
            case UNLOCKED_CHARACTER_CLASSES:
                tableName = UserDBDescription.UnlockedCharacterClasses.TABLE_NAME;
                break;
            case ACTIVE_GLOBAL_ACHIEVEMENTS:
                tableName = UserDBDescription.ActiveGlobalAchievements.TABLE_NAME;
                break;
            case ACTIVE_PARTY_ACHIEVEMENTS:
                tableName = UserDBDescription.ActivePartyAchievements.TABLE_NAME;
                break;
            case DATES:
                tableName = UserDBDescription.Dates.TABLE_NAME;
                break;
            default:
                throw new SQLException(getContext() != null ?  "Invalid Query Uri: " + uri : null);
        }
        return tableName;
    }
}