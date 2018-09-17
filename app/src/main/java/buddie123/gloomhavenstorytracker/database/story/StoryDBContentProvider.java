package buddie123.gloomhavenstorytracker.database.story;

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

public class StoryDBContentProvider extends ContentProvider{
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
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.Locations.TABLE_NAME + "/#", ONE_LOCATION);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.Locations.TABLE_NAME, LOCATIONS);

        // Global Achievements
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.GlobalAchievements.TABLE_NAME + "/#", ONE_GLOBAL_ACHIEVEMENT);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.GlobalAchievements.TABLE_NAME, GLOBAL_ACHIEVEMENTS);

        // Party Achievements
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.PartyAchievements.TABLE_NAME + "/#", ONE_PARTY_ACHIEVEMENT);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.PartyAchievements.TABLE_NAME, PARTY_ACHIEVEMENTS);

        // Global Achievements to be awarded
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.GlobalAchievementsToBeAwarded.TABLE_NAME + "/#", ONE_GLOBAL_ACHIEVEMENT_TO_BE_AWARDED);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.GlobalAchievementsToBeAwarded.TABLE_NAME, GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED);

        // Global Achievements to be revoked
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.GlobalAchievementsToBeRevoked.TABLE_NAME + "/#", ONE_GLOBAL_ACHIEVEMENT_TO_BE_REVOKED);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.GlobalAchievementsToBeRevoked.TABLE_NAME, GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED);

        // Party Achievements to be awarded
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.PartyAchievementsToBeAwarded.TABLE_NAME + "/#", ONE_PARTY_ACHIEVEMENT_TO_BE_AWARDED);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.PartyAchievementsToBeAwarded.TABLE_NAME, PARTY_ACHIEVEMENTS_TO_BE_AWARDED);

        //Party Achievements to be revoked
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.PartyAchievementsToBeRevoked.TABLE_NAME + "/#", ONE_PARTY_ACHIEVEMENT_TO_BE_REVOKED);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.PartyAchievementsToBeRevoked.TABLE_NAME, PARTY_ACHIEVEMENTS_TO_BE_REVOKED);

        // Locations to be unlocked
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.LocationsToBeUnlocked.TABLE_NAME + "/#", ONE_LOCATIONS_TO_BE_UNLOCKED);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.LocationsToBeUnlocked.TABLE_NAME, LOCATIONS_TO_BE_UNLOCKED);

        // Locations to be blocked
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.LocationsToBeBlocked.TABLE_NAME + "/#", ONE_LOCATIONS_TO_BE_BLOCKED);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.LocationsToBeBlocked.TABLE_NAME, LOCATIONS_TO_BE_BLOCKED);

        // Add Reward Application Types
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.AddRewardApplicationTypes.TABLE_NAME + "/#", ONE_ADD_REWARD_APPLICATION_TYPE);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.AddRewardApplicationTypes.TABLE_NAME, ADD_REWARD_APPLICATION_TYPES);

        // Add Reward Types
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.AddRewardTypes.TABLE_NAME + "/#", ONE_ADD_REWARD_TYPE);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.AddRewardTypes.TABLE_NAME, ADD_REWARD_TYPES);

        // Add Rewards
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.AddRewards.TABLE_NAME + "/#", ONE_ADD_REWARD);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.AddRewards.TABLE_NAME, ADD_REWARDS);

        // Add Penalties
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.AddPenalties.TABLE_NAME + "/#", ONE_ADD_PENALTY);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.AddPenalties.TABLE_NAME, ADD_PENALTIES);

        // Character Classes
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.CharacterClasses.TABLE_NAME + "/#", ONE_CHARACTER_CLASS);

        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.CharacterClasses.TABLE_NAME, CHARACTER_CLASSES);
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
    @Override   // TODO this method should throw an exception and not perform the insert
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        // insert into database. ignore insert if the constraints aren't met
        long rowID = dbHelper.getWritableDatabase().insertWithOnConflict(
                getTableName(uri), null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);

        // get the uri for inserted element
        if(rowID > 0) {
            switch (uriMatcher.match(uri)) {
                case LOCATIONS:
                    return StoryDBDescription.Locations.buildLocationUri(rowID);
                case GLOBAL_ACHIEVEMENTS:
                    return StoryDBDescription.GlobalAchievements.buildGlobalAchievementUri(rowID);
                case PARTY_ACHIEVEMENTS:
                    return StoryDBDescription.PartyAchievements.buildPartyAchievementUri(rowID);
                case GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED:
                    return StoryDBDescription.GlobalAchievementsToBeAwarded.buildGlobalAchievementToBeAwardedUri(rowID);
                case GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED:
                    return StoryDBDescription.GlobalAchievementsToBeRevoked.buildGlobalAchievementToBeRevokedUri(rowID);
                case PARTY_ACHIEVEMENTS_TO_BE_AWARDED:
                    return StoryDBDescription.PartyAchievementsToBeAwarded.buildPartyAchievementToBeAwardedUri(rowID);
                case PARTY_ACHIEVEMENTS_TO_BE_REVOKED:
                    return StoryDBDescription.PartyAchievementsToBeRevoked.buildPartyAchievementToBeRevokedUri(rowID);
                case LOCATIONS_TO_BE_UNLOCKED:
                    return StoryDBDescription.LocationsToBeUnlocked.buildLocationToBeUnlockedUri(rowID);
                case LOCATIONS_TO_BE_BLOCKED:
                    return StoryDBDescription.LocationsToBeBlocked.buildLocationToBeBlockedUri(rowID);
                case ADD_REWARD_APPLICATION_TYPES:
                    return StoryDBDescription.AddRewardApplicationTypes.buildAddRewardApplicationTypeUri(rowID);
                case ADD_REWARD_TYPES:
                    return StoryDBDescription.AddRewardTypes.buildAddRewardTypeUri(rowID);
                case ADD_REWARDS:
                    return StoryDBDescription.AddRewards.buildAddRewardUri(rowID);
                case ADD_PENALTIES:
                    return StoryDBDescription.AddPenalties.buildAddPenaltyUri(rowID);
                case CHARACTER_CLASSES:
                    return StoryDBDescription.CharacterClasses.buildCharacterClassUri(rowID);
            }
        }
        return null;
    }

    @Override // TODO this method should throw exception and not delete anything from the database
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override // TODO this method should throw exception and not update the database
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    // return a table name based on a given Uri, or throw an error if invalid
    private String getTableName(Uri uri) {
        String tableName;
        switch (uriMatcher.match(uri) % 2 == 0 ? uriMatcher.match(uri) : uriMatcher.match(uri) + 1) {
            case CHARACTER_CLASSES:
                tableName = StoryDBDescription.CharacterClasses.TABLE_NAME;
                break;
            case LOCATIONS:
                tableName = StoryDBDescription.Locations.TABLE_NAME;
                break;
            case GLOBAL_ACHIEVEMENTS:
                tableName = StoryDBDescription.GlobalAchievements.TABLE_NAME;
                break;
            case PARTY_ACHIEVEMENTS:
                tableName = StoryDBDescription.PartyAchievements.TABLE_NAME;
                break;
            case GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED:
                tableName = StoryDBDescription.GlobalAchievementsToBeAwarded.TABLE_NAME;
                break;
            case GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED:
                tableName = StoryDBDescription.GlobalAchievementsToBeRevoked.TABLE_NAME;
                break;
            case PARTY_ACHIEVEMENTS_TO_BE_AWARDED:
                tableName = StoryDBDescription.PartyAchievementsToBeAwarded.TABLE_NAME;
                break;
            case PARTY_ACHIEVEMENTS_TO_BE_REVOKED:
                tableName = StoryDBDescription.PartyAchievementsToBeRevoked.TABLE_NAME;
                break;
            case LOCATIONS_TO_BE_UNLOCKED:
                tableName = StoryDBDescription.LocationsToBeUnlocked.TABLE_NAME;
                break;
            case LOCATIONS_TO_BE_BLOCKED:
                tableName = StoryDBDescription.LocationsToBeBlocked.TABLE_NAME;
                break;
            case ADD_REWARD_APPLICATION_TYPES:
                tableName = StoryDBDescription.AddRewardApplicationTypes.TABLE_NAME;
                break;
            case ADD_REWARD_TYPES:
                tableName = StoryDBDescription.AddRewardTypes.TABLE_NAME;
                break;
            case ADD_REWARDS:
                tableName = StoryDBDescription.AddRewards.TABLE_NAME;
                break;
            case ADD_PENALTIES:
                tableName = StoryDBDescription.AddPenalties.TABLE_NAME;
                break;
            default:
                throw new SQLException(getContext() != null ?  "Invalid Query Uri: " + uri : null);
        }
        return tableName;
    }
}
