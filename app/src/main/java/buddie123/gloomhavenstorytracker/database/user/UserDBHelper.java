package buddie123.gloomhavenstorytracker.database.user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.db";
    private static final int DATABASE_VERSION= 1;

    //constructor
    UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creates all the tables when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create PARTIES table
        final String CREATE_PARTIES_TABLE =
                "CREATE TABLE " + UserDBDescription.Parties.TABLE_NAME + "(" +
                        UserDBDescription.Parties._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.Parties.COLUMN_NAME + " TEXT UNIQUE);";
        db.execSQL(CREATE_PARTIES_TABLE);

        // create Characters table
        final String CREATE_CHARACTERS_TABLE =
                "CREATE TABLE " + UserDBDescription.Characters.TABLE_NAME + "(" +
                        UserDBDescription.Characters._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.Characters.COLUMN_NAME + " TEXT, " +
                        UserDBDescription.Characters.COLUMN_CLASS_ID + " INTEGER, " +
                        UserDBDescription.Characters.COLUMN_PARTY_ID + " INTEGER, " +
                        "UNIQUE (" + UserDBDescription.Characters.COLUMN_NAME +
                        ", " + UserDBDescription.Characters.COLUMN_PARTY_ID +")," +
                        "UNIQUE (" + UserDBDescription.Characters.COLUMN_PARTY_ID +
                        ", " + UserDBDescription.Characters.COLUMN_CLASS_ID +"));";
        db.execSQL(CREATE_CHARACTERS_TABLE);

        // create Attempt Table
        final String CREATE_ATTEMPTS_TABLE =
                "CREATE TABLE " + UserDBDescription.Attempts.TABLE_NAME + "(" +
                        UserDBDescription.Attempts._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.Attempts.COLUMN_NUMBER + " INTEGER, " +
                        UserDBDescription.Attempts.COLUMN_PARTY_ID + " INTEGER, " +
                        UserDBDescription.Attempts.COLUMN_LOCATION_ID + " INTEGER, " +
                        UserDBDescription.Attempts.COLUMN_SUCCESSFUL_ID + " INTEGER, " +
                        UserDBDescription.Attempts.COLUMN_DATE_ID + " INTEGER, " +
                        "UNIQUE (" + UserDBDescription.Attempts.COLUMN_NUMBER +
                        ", " + UserDBDescription.Attempts.COLUMN_PARTY_ID +
                        ", " + UserDBDescription.Attempts.COLUMN_LOCATION_ID + "));";
        db.execSQL(CREATE_ATTEMPTS_TABLE);

        // create AttemptParticipants table
        final String CREATE_ATTEMPT_PARTICIPANTS_TABLE =
                "CREATE TABLE " + UserDBDescription.AttemptParticipants.TABLE_NAME + "(" +
                        UserDBDescription.AttemptParticipants._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.AttemptParticipants.COLUMN_ATTEMPT_ID + " INTEGER, " +
                        UserDBDescription.AttemptParticipants.COLUMN_CHARACTER_ID + " INTEGER, " +
                        "UNIQUE (" + UserDBDescription.AttemptParticipants.COLUMN_ATTEMPT_ID +
                        ", " + UserDBDescription.AttemptParticipants.COLUMN_CHARACTER_ID +"));";
        db.execSQL(CREATE_ATTEMPT_PARTICIPANTS_TABLE);


        // create AttemptParticipants table
        final String CREATE_ATTEMPT_NON_PARTICIPANTS_TABLE =
                "CREATE TABLE " + UserDBDescription.AttemptNonParticipants.TABLE_NAME + "(" +
                        UserDBDescription.AttemptNonParticipants._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.AttemptNonParticipants.COLUMN_ATTEMPT_ID + " INTEGER, " +
                        UserDBDescription.AttemptNonParticipants.COLUMN_CHARACTER_ID + " INTEGER, " +
                        "UNIQUE (" + UserDBDescription.AttemptNonParticipants.COLUMN_ATTEMPT_ID +
                        ", " + UserDBDescription.AttemptNonParticipants.COLUMN_CHARACTER_ID +"));";
        db.execSQL(CREATE_ATTEMPT_NON_PARTICIPANTS_TABLE);

        // create UnlockedLocations table
        final String CREATE_UNLOCKED_LOCATIONS_TABLE =
                "CREATE TABLE " + UserDBDescription.UnlockedLocations.TABLE_NAME + "(" +
                        UserDBDescription.UnlockedLocations._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.UnlockedLocations.COLUMN_PARTY_ID + " INTEGER, " +
                        UserDBDescription.UnlockedLocations.COLUMN_UNLOCKED_LOCATION_ID + " INTEGER, " +
                        UserDBDescription.UnlockedLocations.COLUMN_UNLOCKING_LOCATION_ID + " INTEGER, " +
                        "UNIQUE (" + UserDBDescription.UnlockedLocations.COLUMN_UNLOCKED_LOCATION_ID +
                        ", " + UserDBDescription.UnlockedLocations.COLUMN_PARTY_ID +"));";
        db.execSQL(CREATE_UNLOCKED_LOCATIONS_TABLE);

        // create BlockedLocations table
        final String CREATE_BLOCKED_LOCATIONS_TABLE =
                "CREATE TABLE " + UserDBDescription.BlockedLocations.TABLE_NAME + "(" +
                        UserDBDescription.BlockedLocations._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.BlockedLocations.COLUMN_PARTY_ID + " INTEGER, " +
                        UserDBDescription.BlockedLocations.COLUMN_BLOCKED_LOCATION_ID + " INTEGER, " +
                        UserDBDescription.BlockedLocations.COLUMN_BLOCKING_LOCATION_ID + " INTEGER, " +
                        "UNIQUE (" + UserDBDescription.BlockedLocations.COLUMN_BLOCKED_LOCATION_ID +
                        ", " + UserDBDescription.BlockedLocations.COLUMN_PARTY_ID +"));";
        db.execSQL(CREATE_BLOCKED_LOCATIONS_TABLE);

        // create CompletedLocations table
        final String CREATE_COMPLETED_LOCATIONS_TABLE =
                "CREATE TABLE " + UserDBDescription.CompletedLocations.TABLE_NAME + "(" +
                        UserDBDescription.CompletedLocations._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.CompletedLocations.COLUMN_PARTY_ID + " INTEGER, " +
                        UserDBDescription.CompletedLocations.COLUMN_COMPLETED_LOCATION_ID+ " INTEGER, " +
                        UserDBDescription.CompletedLocations.COLUMN_COMPLETED_DATE_ID + " INTEGER, " +
                        "UNIQUE (" + UserDBDescription.CompletedLocations.COLUMN_PARTY_ID +
                        ", " + UserDBDescription.CompletedLocations.COLUMN_COMPLETED_LOCATION_ID +"));";
        db.execSQL(CREATE_COMPLETED_LOCATIONS_TABLE);

        // create LockedLocations table
        final String CREATE_LOCKED_LOCATIONS_TABLE =
                "CREATE TABLE " + UserDBDescription.LockedLocations.TABLE_NAME + "(" +
                        UserDBDescription.LockedLocations._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.LockedLocations.COLUMN_PARTY_ID + " INTEGER, " +
                        UserDBDescription.LockedLocations.COLUMN_LOCATION_ID + " INTEGER, " +
                        "UNIQUE (" + UserDBDescription.LockedLocations.COLUMN_PARTY_ID +
                        ", " + UserDBDescription.LockedLocations.COLUMN_LOCATION_ID +"));";
        db.execSQL(CREATE_LOCKED_LOCATIONS_TABLE);

        // create AttemptNotes table
        final String CREATE_ATTEMPT_NOTES_TABLE =
                "CREATE TABLE " + UserDBDescription.AttemptNotes.TABLE_NAME + "(" +
                        UserDBDescription.AttemptNotes._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.AttemptNotes.COLUMN_ATTEMPT_ID + " INTEGER UNIQUE, " +
                        UserDBDescription.AttemptNotes.COLUMN_NOTE + " TEXT);";
        db.execSQL(CREATE_ATTEMPT_NOTES_TABLE);

        // create UnlockedCharacterClasses table
        final String CREATE_UNLOCKED_CHARACTER_CLASSES_TABLE =
                "CREATE TABLE " + UserDBDescription.UnlockedCharacterClasses.TABLE_NAME + "(" +
                        UserDBDescription.UnlockedCharacterClasses._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.UnlockedCharacterClasses.COLUMN_PARTY_ID + " INTEGER, " +
                        UserDBDescription.UnlockedCharacterClasses.COLUMN_CLASS_ID+ " INTEGER, " +
                        "Unique (" + UserDBDescription.UnlockedCharacterClasses.COLUMN_PARTY_ID +
                        ", " + UserDBDescription.UnlockedCharacterClasses.COLUMN_CLASS_ID +"));";
        db.execSQL(CREATE_UNLOCKED_CHARACTER_CLASSES_TABLE);

        // create ActiveGlobalAchievements table
        final String CREATE_ACTIVE_GLOBAL_ACHIEVEMENTS_TABLE =
                "CREATE TABLE " + UserDBDescription.ActiveGlobalAchievements.TABLE_NAME + "(" +
                        UserDBDescription.ActiveGlobalAchievements._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.ActiveGlobalAchievements.COLUMN_PARTY_ID + " INTEGER, " +
                        UserDBDescription.ActiveGlobalAchievements.COLUMN_GLOBAL_ACHIEVEMENT_ID + " INTEGER, " +
                        "Unique (" + UserDBDescription.ActiveGlobalAchievements.COLUMN_PARTY_ID +
                        ", " + UserDBDescription.ActiveGlobalAchievements.COLUMN_GLOBAL_ACHIEVEMENT_ID +"));";
        db.execSQL(CREATE_ACTIVE_GLOBAL_ACHIEVEMENTS_TABLE);

        // create ActivePartyAchievements table
        final String CREATE_ACTIVE_PARTY_ACHIEVEMENTS_TABLE =
                "CREATE TABLE " + UserDBDescription.ActivePartyAchievements.TABLE_NAME + "(" +
                        UserDBDescription.ActivePartyAchievements._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.ActivePartyAchievements.COLUMN_PARTY_ID + " INTEGER, " +
                        UserDBDescription.ActivePartyAchievements.COLUMN_PARTY_ACHIEVEMENT_ID + " INTEGER, " +
                        "Unique (" + UserDBDescription.ActivePartyAchievements.COLUMN_PARTY_ID +
                        ", " + UserDBDescription.ActivePartyAchievements.COLUMN_PARTY_ACHIEVEMENT_ID +"));";
        db.execSQL(CREATE_ACTIVE_PARTY_ACHIEVEMENTS_TABLE);

        // create AttemptNotes table
        final String CREATE_DATES_TABLE =
                "CREATE TABLE " + UserDBDescription.Dates.TABLE_NAME + "(" +
                        UserDBDescription.Dates._ID + " INTEGER primary key AUTOINCREMENT, " +
                        UserDBDescription.Dates.COLUMN_YEAR + " INTEGER, " + // no unique constraint
                        UserDBDescription.Dates.COLUMN_MONTH + " INTEGER, " +
                        UserDBDescription.Dates.COLUMN_DAY + " INTEGER);";
        db.execSQL(CREATE_DATES_TABLE);
    }

    // normally defines how to upgrade the database when the schema changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO Research how to implement this method
    }

}
