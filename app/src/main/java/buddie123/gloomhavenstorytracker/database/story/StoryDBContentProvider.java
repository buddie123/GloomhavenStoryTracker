package buddie123.gloomhavenstorytracker.database.story;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import buddie123.gloomhavenstorytracker.database.DatabaseDescription;

public class StoryDBContentProvider extends ContentProvider {
    // used to access the database
    private StoryDBHelper dbHelper;

    // UriMatcher helps content provider determine operation to perform
    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    // constants used with UriMatcher to determine operation to perform
    private static final int ONE_CHARACTER_CLASS = 1;
    private static final int CHARACTER_CLASSES = 2;

    private static final int ONE_LOCATION = 3;
    private static final int LOCATIONS = 4;

    private static final int ONE_GLOBAL_ACHIEVEMENT = 5;
    private static final int GLOBAL_ACHIEVEMENTS = 6;

    private static final int ONE_GLOBAL_ACHIEVEMENT_TO_BE_AWARDED = 7;
    private static final int GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED = 8;

    private static final int ONE_GLOBAL_ACHIEVEMENT_TO_BE_REVOKED = 9;
    private static final int GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED = 10;

    private static final int ONE_PARTY_ACHIEVEMENT = 11;
    private static final int PARTY_ACHIEVEMENTS = 12;

    private static final int ONE_PARTY_ACHIEVEMENT_TO_BE_AWARDED = 13;
    private static final int PARTY_ACHIEVEMENTS_TO_BE_AWARDED = 14;

    private static final int ONE_PARTY_ACHIEVEMENT_TO_BE_REVOKED = 15;
    private static final int PARTY_ACHIEVEMENTS_TO_BE_REVOKED = 16;

    private static final int ONE_LOCATIONS_TO_BE_UNLOCKED = 17;
    private static final int LOCATIONS_TO_BE_UNLOCKED = 18;

    private static final int ONE_LOCATIONS_TO_BE_BLOCKED = 19;
    private static final int LOCATIONS_TO_BE_BLOCKED = 20;

    private static final int ONE_ADD_REWARD_APPLICATION_TYPE = 21;
    private static final int ADD_REWARD_APPLICATION_TYPES = 22;

    private static final int ONE_ADD_REWARD_TYPE = 23;
    private static final int ADD_REWARD_TYPES = 24;

    private static final int ONE_ADD_REWARD = 25;
    private static final int ADD_REWARDS = 26;

    private static final int ONE_ADD_PENALTY = 27;
    private static final int ADD_PENALTIES = 28;


    // static block to configure this ContentProvider's UriMatcher
    static {

        //Locations
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.Locations.TABLE_NAME + "/#", ONE_LOCATION);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.Locations.TABLE_NAME, LOCATIONS);

        // Global Achievements
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.GlobalAchievements.TABLE_NAME + "/#", ONE_GLOBAL_ACHIEVEMENT);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.GlobalAchievements.TABLE_NAME, GLOBAL_ACHIEVEMENTS);

        // Party Achievements
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.PartyAchievements.TABLE_NAME + "/#", ONE_PARTY_ACHIEVEMENT);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.PartyAchievements.TABLE_NAME, PARTY_ACHIEVEMENTS);

        // Global Achievements to be awarded
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.GlobalAchievementsToBeAwarded.TABLE_NAME + "/#", ONE_GLOBAL_ACHIEVEMENT_TO_BE_AWARDED);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.GlobalAchievementsToBeAwarded.TABLE_NAME, GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED);

        // Global Achievements to be revoked
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.GlobalAchievementsToBeRevoked.TABLE_NAME + "/#", ONE_GLOBAL_ACHIEVEMENT_TO_BE_REVOKED);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.GlobalAchievementsToBeRevoked.TABLE_NAME, GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED);

        // Party Achievements to be awarded
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.PartyAchievementsToBeAwarded.TABLE_NAME + "/#", ONE_PARTY_ACHIEVEMENT_TO_BE_AWARDED);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.PartyAchievementsToBeAwarded.TABLE_NAME, PARTY_ACHIEVEMENTS_TO_BE_AWARDED);

        //Party Achievements to be revoked
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.PartyAchievementsToBeRevoked.TABLE_NAME + "/#", ONE_PARTY_ACHIEVEMENT_TO_BE_REVOKED);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.PartyAchievementsToBeRevoked.TABLE_NAME, PARTY_ACHIEVEMENTS_TO_BE_REVOKED);

        // Locations to be unlocked
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.LocationsToBeUnlocked.TABLE_NAME + "/#", ONE_LOCATIONS_TO_BE_UNLOCKED);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.LocationsToBeUnlocked.TABLE_NAME, LOCATIONS_TO_BE_UNLOCKED);

        // Locations to be blocked
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.LocationsToBeBlocked.TABLE_NAME + "/#", ONE_LOCATIONS_TO_BE_BLOCKED);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.LocationsToBeBlocked.TABLE_NAME, LOCATIONS_TO_BE_BLOCKED);

        // Add Reward Application Types
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.AddRewardApplicationTypes.TABLE_NAME + "/#", ONE_ADD_REWARD_APPLICATION_TYPE);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.AddRewardApplicationTypes.TABLE_NAME, ADD_REWARD_APPLICATION_TYPES);

        // Add Reward Types
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.AddRewardTypes.TABLE_NAME + "/#", ONE_ADD_REWARD_TYPE);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.AddRewardTypes.TABLE_NAME, ADD_REWARD_TYPES);

        // Add Rewards
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.AddRewards.TABLE_NAME + "/#", ONE_ADD_REWARD);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.AddRewards.TABLE_NAME, ADD_REWARDS);

        // Add Penalties
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.AddPenalties.TABLE_NAME + "/#", ONE_ADD_PENALTY);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.AddPenalties.TABLE_NAME, ADD_PENALTIES);

        // Character Classes
        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.CharacterClasses.TABLE_NAME + "/#", ONE_CHARACTER_CLASS);

        uriMatcher.addURI(DatabaseDescription.AUTHORITY,
                DatabaseDescription.CharacterClasses.TABLE_NAME, CHARACTER_CLASSES);
    }





    @Override
    public boolean onCreate() {
        dbHelper = new StoryDBHelper(getContext());
        return true;
        // TODO verify that this return value is correct
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
        // TODO Research how this method should be implemented
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // initialize the queryBuilder object used to get the cursor with the query results
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // set the appropriate table to query based on the given uri
        queryBuilder.setTables(getTableName(uri));

        // if querying a single element, get the match on the _ID
        if(uriMatcher.match(uri) % 2 == 1) {
            queryBuilder.appendWhere(BaseColumns._ID + "=" + uri.getLastPathSegment() );
        }

        // execute the query
        return queryBuilder.query(dbHelper.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
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

    // return a table name based on a given Uri, or throw an error if invalid
    private String getTableName(Uri uri) {
        String tableName;
        switch (uriMatcher.match(uri) % 2 == 0 ? uriMatcher.match(uri) : uriMatcher.match(uri) + 1) {
            case CHARACTER_CLASSES:
                tableName = DatabaseDescription.CharacterClasses.TABLE_NAME;
                break;
            case LOCATIONS:
                tableName = DatabaseDescription.Locations.TABLE_NAME;
                break;
            case GLOBAL_ACHIEVEMENTS:
                tableName = DatabaseDescription.GlobalAchievements.TABLE_NAME;
                break;
            case PARTY_ACHIEVEMENTS:
                tableName = DatabaseDescription.PartyAchievements.TABLE_NAME;
                break;
            case GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED:
                tableName = DatabaseDescription.GlobalAchievementsToBeAwarded.TABLE_NAME;
                break;
            case GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED:
                tableName = DatabaseDescription.GlobalAchievementsToBeRevoked.TABLE_NAME;
                break;
            case PARTY_ACHIEVEMENTS_TO_BE_AWARDED:
                tableName = DatabaseDescription.PartyAchievementsToBeAwarded.TABLE_NAME;
                break;
            case PARTY_ACHIEVEMENTS_TO_BE_REVOKED:
                tableName = DatabaseDescription.PartyAchievementsToBeRevoked.TABLE_NAME;
                break;
            case LOCATIONS_TO_BE_UNLOCKED:
                tableName = DatabaseDescription.LocationsToBeUnlocked.TABLE_NAME;
                break;
            case LOCATIONS_TO_BE_BLOCKED:
                tableName = DatabaseDescription.LocationsToBeBlocked.TABLE_NAME;
                break;
            case ADD_REWARD_APPLICATION_TYPES:
                tableName = DatabaseDescription.AddRewardApplicationTypes.TABLE_NAME;
                break;
            case ADD_REWARD_TYPES:
                tableName = DatabaseDescription.AddRewardTypes.TABLE_NAME;
                break;
            case ADD_REWARDS:
                tableName = DatabaseDescription.AddRewards.TABLE_NAME;
                break;
            case ADD_PENALTIES:
                tableName = DatabaseDescription.AddPenalties.TABLE_NAME;
                break;
            default:
                throw new SQLException(getContext() != null ?  "Invalid Query Uri: " + uri : null);
        }
        return tableName;
    }
}
