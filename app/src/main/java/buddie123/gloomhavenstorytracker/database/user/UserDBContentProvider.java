package buddie123.gloomhavenstorytracker.database.user;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
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
    public static final int COMPLETED_LOCCATIONS = 16;

    public static final int ONE_LOCKED_LOCATION = 17;
    public static final int LOCKED_LOCATIONS = 18;

    public static final int ONE_ATTEMPT_NOTE = 19;
    public static final int ATTEMPT_NOTES = 20;

    public static final int ONE_UNLOCKED_CHARACTER_CLASS = 21;
    public static final int UNLOCKED_CHARACTER_CLASSES = 22;

    public static final int ONE_ACTIVE_GLOBAL_ACHIEVEMENT = 23;
    public static final int ACTIVE_GLOBAL_ACHIEVEMENTS = 24;

    public static final int ONE_ACTIVE_PARTY_ACHIEVMENT = 25;
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
                UserDBDescription.CompletedLocations.TABLE_NAME, COMPLETED_LOCCATIONS);

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
                UserDBDescription.ActivePartyAchievements.TABLE_NAME + "/#", ONE_ACTIVE_PARTY_ACHIEVMENT);
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
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
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
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
