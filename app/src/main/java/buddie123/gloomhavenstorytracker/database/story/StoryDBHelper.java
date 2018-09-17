package buddie123.gloomhavenstorytracker.database.story;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoryDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Story.db";
    private static final int DATABASE_VERSION = 1;

    // constructor
    public StoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creates each table in the Story.db database when the database
    // is created
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the Locations Table
        final String CREATE_LOCATIONS_TABLE =
                "CREATE TABLE " + StoryDBDescription.Locations.TABLE_NAME + "(" +
                        StoryDBDescription.Locations._ID + " INTEGER primary key, " + // not autoincrement
                        StoryDBDescription.Locations.COLUMN_NAME + " TEXT, " +
                        StoryDBDescription.Locations.COLUMN_TEASER + " TEXT, " +
                        StoryDBDescription.Locations.COLUMN_SUMMARY + " TEXT, " +
                        StoryDBDescription.Locations.COLUMN_CONCLUSION + " TEXT);";
        db.execSQL(CREATE_LOCATIONS_TABLE);

        // create the GlobalAchievements table
        final String CREATE_GLOBAL_ACHIEVEMENTS_TABLE =
                "CREATE TABLE " + StoryDBDescription.GlobalAchievements.TABLE_NAME + "(" +
                        StoryDBDescription.GlobalAchievements._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.GlobalAchievements.COLUMN_NAME + " TEXT UNIQUE, " +
                        StoryDBDescription.GlobalAchievements.COLUMN_GROUP_NAME + " TEXT, " +
                        StoryDBDescription.GlobalAchievements.COLUMN_MAX_COUNT + " INTEGER DEFAULT 1);";
        db.execSQL(CREATE_GLOBAL_ACHIEVEMENTS_TABLE);


        // create the PartyAchievements table
        final String CREATE_PARTY_ACHIEVEMENTS_TABLE =
                "CREATE TABLE " + StoryDBDescription.PartyAchievements.TABLE_NAME + "(" +
                        StoryDBDescription.PartyAchievements._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.PartyAchievements.COLUMN_NAME + " TEXT UNIQUE);";
        db.execSQL(CREATE_PARTY_ACHIEVEMENTS_TABLE);


        // create the GlobalAchievementsToBeAwarded table
        final String CREATE_GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED_TABLE =
                "CREATE TABLE " + StoryDBDescription.GlobalAchievementsToBeAwarded.TABLE_NAME + "(" +
                        StoryDBDescription.GlobalAchievementsToBeAwarded._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.GlobalAchievementsToBeAwarded.COLUMN_LOCATION_TO_BE_COMPLETED_ID + " INTEGER, " +
                        StoryDBDescription.GlobalAchievementsToBeAwarded.COLUMN_GLOBAL_ACHIEVEMENT_ID + " TEXT, " +
                        "UNIQUE (" + StoryDBDescription.GlobalAchievementsToBeAwarded.COLUMN_LOCATION_TO_BE_COMPLETED_ID +
                        ", " + StoryDBDescription.GlobalAchievementsToBeAwarded.COLUMN_GLOBAL_ACHIEVEMENT_ID + "));";
        db.execSQL(CREATE_GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED_TABLE);

        // create the GlobalAchievementsToBeRevoked table
        final String CREATE_GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED_TABLE =
                "CREATE TABLE " + StoryDBDescription.GlobalAchievementsToBeRevoked.TABLE_NAME + "(" +
                        StoryDBDescription.GlobalAchievementsToBeRevoked._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.GlobalAchievementsToBeRevoked.COLUMN_LOCATION_TO_BE_COMPLETED_ID + " INTEGER, " +
                        StoryDBDescription.GlobalAchievementsToBeRevoked.COLUMN_GLOBAL_ACHIEVEMENT_ID + " TEXT, " +
                        "UNIQUE (" + StoryDBDescription.GlobalAchievementsToBeRevoked.COLUMN_LOCATION_TO_BE_COMPLETED_ID +
                        ", " + StoryDBDescription.GlobalAchievementsToBeRevoked.COLUMN_GLOBAL_ACHIEVEMENT_ID + "));";
        db.execSQL(CREATE_GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED_TABLE);

        // create the PartyAchievementsToBeAwarded table
        final String CREATE_PARTY_ACHIEVEMENTS_TO_BE_AWARDED_TABLE =
                "CREATE TABLE " + StoryDBDescription.PartyAchievementsToBeAwarded.TABLE_NAME + "(" +
                        StoryDBDescription.PartyAchievementsToBeAwarded._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.PartyAchievementsToBeAwarded.COLUMN_LOCATION_TO_BE_COMPLETED_ID + " INTEGER, " +
                        StoryDBDescription.PartyAchievementsToBeAwarded.COLUMN_PARTY_ACHIEVEMENT_ID + " TEXT, " +
                        "UNIQUE (" + StoryDBDescription.PartyAchievementsToBeAwarded.COLUMN_LOCATION_TO_BE_COMPLETED_ID +
                        ", " + StoryDBDescription.PartyAchievementsToBeAwarded.COLUMN_PARTY_ACHIEVEMENT_ID + "));";
        db.execSQL(CREATE_PARTY_ACHIEVEMENTS_TO_BE_AWARDED_TABLE);

        // create the PartyAchievementsToBeRevoked table
        final String CREATE_PARTY_ACHIEVEMENTS_TO_BE_REVOKED_TABLE =
                "CREATE TABLE " + StoryDBDescription.PartyAchievementsToBeRevoked.TABLE_NAME + "(" +
                        StoryDBDescription.PartyAchievementsToBeRevoked._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.PartyAchievementsToBeRevoked.COLUMN_LOCATION_TO_BE_COMPLETED_ID + " INTEGER, " +
                        StoryDBDescription.PartyAchievementsToBeRevoked.COLUMN_PARTY_ACHIEVEMENT_ID + " TEXT, " +
                        "UNIQUE (" + StoryDBDescription.PartyAchievementsToBeRevoked.COLUMN_LOCATION_TO_BE_COMPLETED_ID +
                        ", " + StoryDBDescription.PartyAchievementsToBeRevoked.COLUMN_PARTY_ACHIEVEMENT_ID + "));";
        db.execSQL(CREATE_PARTY_ACHIEVEMENTS_TO_BE_REVOKED_TABLE);

        // create the LocationsToBeUnlocked table
        final String CREATE_LOCATIONS_TO_BE_UNLOCKED_TABLE =
                "CREATE TABLE " + StoryDBDescription.LocationsToBeUnlocked.TABLE_NAME + "(" +
                        StoryDBDescription.LocationsToBeUnlocked._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.LocationsToBeUnlocked.COLUMN_LOCATION_TO_BE_COMPLETED_ID + " INTEGER, " +
                        StoryDBDescription.LocationsToBeUnlocked.COLUMN_UNLOCKED_LOCATION_ID + " INTEGER, " +
                        "UNIQUE (" + StoryDBDescription.LocationsToBeUnlocked.COLUMN_LOCATION_TO_BE_COMPLETED_ID +
                        ", " + StoryDBDescription.LocationsToBeUnlocked.COLUMN_UNLOCKED_LOCATION_ID + "));";
        db.execSQL(CREATE_LOCATIONS_TO_BE_UNLOCKED_TABLE);


        // create the LocationsToBeBlocked table
        final String CREATE_LOCATIONS_TO_BE_BLOCKED_TABLE =
                "CREATE TABLE " + StoryDBDescription.LocationsToBeBlocked.TABLE_NAME + "(" +
                        StoryDBDescription.LocationsToBeBlocked._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.LocationsToBeBlocked.COLUMN_LOCATION_TO_BE_COMPLETED_ID + " INTEGER, " +
                        StoryDBDescription.LocationsToBeBlocked.COLUMN_BLOCKED_LOCATION_ID + " INTEGER, " +
                        "UNIQUE (" + StoryDBDescription.LocationsToBeBlocked.COLUMN_LOCATION_TO_BE_COMPLETED_ID +
                        ", " + StoryDBDescription.LocationsToBeBlocked.COLUMN_BLOCKED_LOCATION_ID + "));";
        db.execSQL(CREATE_LOCATIONS_TO_BE_BLOCKED_TABLE);


        // create AddRewardApplicationTypes table
        final String CREATE_ADD_REWARD_APPLICATION_TYPES_TABLE =
                "CREATE TABLE " + StoryDBDescription.AddRewardApplicationTypes.TABLE_NAME + "(" +
                        StoryDBDescription.AddRewardApplicationTypes._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.AddRewardApplicationTypes.COLUMN_APPLICATION_TYPE + " TEXT UNIQUE);";
        db.execSQL(CREATE_ADD_REWARD_APPLICATION_TYPES_TABLE);

        // create AddRewardTypes table
        final String CREATE_ADD_REWARD_TYPES_TABLE =
                "CREATE TABLE " + StoryDBDescription.AddRewardTypes.TABLE_NAME + "(" +
                        StoryDBDescription.AddRewardTypes._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.AddRewardTypes.COLUMN_REWARD_TYPE + " TEXT UNIQUE);";
        db.execSQL(CREATE_ADD_REWARD_TYPES_TABLE);

        // create the AddRewards table
        final String CREATE_ADD_REWARDS_TABLE =
                "CREATE TABLE " + StoryDBDescription.AddRewards.TABLE_NAME + "(" +
                        StoryDBDescription.AddRewards._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.AddRewards.COLUMN_LOCATION_TO_BE_COMPLETED_ID + " INTEGER, " +
                        StoryDBDescription.AddRewards.COLUMN_REWARD_TYPE_ID + " TEXT, " +
                        StoryDBDescription.AddRewards.COLUMN_REWARD_VALUE + " INTEGER, " +
                        StoryDBDescription.AddRewards.COLUMN_REWARD_APPLICATION_TYPE_ID + " TEXT, " +
                        "UNIQUE (" + StoryDBDescription.AddRewards.COLUMN_LOCATION_TO_BE_COMPLETED_ID +
                        ", " + StoryDBDescription.AddRewards.COLUMN_REWARD_TYPE_ID + "));";
        db.execSQL(CREATE_ADD_REWARDS_TABLE);

        // create the AddPenalties table
        final String CREATE_ADD_PENALTIES_TABLE =
                "CREATE TABLE " + StoryDBDescription.AddPenalties.TABLE_NAME + "(" +
                        StoryDBDescription.AddPenalties._ID + " INTEGER primary key AUTOINCREMENT, " +
                        StoryDBDescription.AddPenalties.COLUMN_LOCATION_TO_BE_COMPLETED_ID + " INTEGER, " +
                        StoryDBDescription.AddPenalties.COLUMN_PENALTY_TYPE_ID + " TEXT, " +
                        StoryDBDescription.AddPenalties.COLUMN_PENALTY_VALUE + " INTEGER, " +
                        StoryDBDescription.AddPenalties.COLUMN_PENALTY_APPLICATION_TYPE_ID + " TEXT, " +
                        "UNIQUE (" + StoryDBDescription.AddPenalties.COLUMN_LOCATION_TO_BE_COMPLETED_ID +
                        ", " + StoryDBDescription.AddPenalties.COLUMN_PENALTY_TYPE_ID + "));";
        db.execSQL(CREATE_ADD_PENALTIES_TABLE);

        // create the CharacterClasses table =
        final String CREATE_CHARACTER_CLASSES =
                "CREATE TABLE " + StoryDBDescription.CharacterClasses.TABLE_NAME + "(" +
                        StoryDBDescription.CharacterClasses.COLUMN_CLASS + " TEXT UNIQUE);";
        db.execSQL(CREATE_CHARACTER_CLASSES);
    }

    // defines how to upgrade the database when the schema changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO Research how to implement this method
    }
}
