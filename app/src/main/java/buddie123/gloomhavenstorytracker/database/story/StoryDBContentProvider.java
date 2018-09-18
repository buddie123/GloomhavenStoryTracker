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
    // IMPORTANT: For each table, the single row access constant should be odd
    // and one less than the table access constant. The logic in the CRUD
    // operations depend on this arraignment.
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

    private static final int ONE_YES_NO_RESPONSE = 29;
    private static final int YES_NO_RESPONSES = 30;
    // IMPORTANT: For each table added to this database, the single row
    // access constant should be odd and one less than the table access
    // constant. The logic in the CRUD operations depend on this arraignment.


    // static block to configure this ContentProvider's UriMatcher
    // The StoryDBDescription.XXXXXXX.TABLE_NAME + "/#" appends a numerical
    // wildcard to the end of the Uri. This is so that Uri's of this form
    // can have the row _ID appended to the end and be easily parsed.
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

        // Yes/No Responses
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.YesNoResponses.TABLE_NAME + "/#", ONE_YES_NO_RESPONSE);
        uriMatcher.addURI(StoryDBDescription.AUTHORITY,
                StoryDBDescription.YesNoResponses.TABLE_NAME, YES_NO_RESPONSES);
    }

    // simply initializes the StoryDBHelper
    @Override
    public boolean onCreate() {
        dbHelper = new StoryDBHelper(getContext());
        return true;
        // TODO verify that this return value is correct
    }

    // not sure what this method is suppose to do
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
        // TODO Research how this method should be implemented
    }

    // query's the database and returns the Cursor
    // This method depends on the constants defined above having a certain
    // structure. In particular, single row grabs should be odd, and
    // multi-row grabs should be even.
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

    // WARNING: SHOULD NEVER BE CALLED BY THE PROGRAM
    // insert the given elements into the given table ( should not give a row id, except for location)
    @Nullable
    @Override   // TODO this method should throw an exception and not perform the insert
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri newElementUri = null;

        // insert into database. ignore insert if the constraints aren't met
        long rowID = dbHelper.getWritableDatabase().insertWithOnConflict(
                getTableName(uri), null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);

        // get the uri for inserted element
        if(rowID > 0) {
            switch (uriMatcher.match(uri)) {
                case LOCATIONS:
                    newElementUri = StoryDBDescription.Locations.buildLocationUri(rowID);
                    break;
                case GLOBAL_ACHIEVEMENTS:
                    newElementUri = StoryDBDescription.GlobalAchievements.buildGlobalAchievementUri(rowID);
                    break;
                case PARTY_ACHIEVEMENTS:
                    newElementUri = StoryDBDescription.PartyAchievements.buildPartyAchievementUri(rowID);
                    break;
                case GLOBAL_ACHIEVEMENTS_TO_BE_AWARDED:
                    newElementUri = StoryDBDescription.GlobalAchievementsToBeAwarded.buildGlobalAchievementToBeAwardedUri(rowID);
                    break;
                case GLOBAL_ACHIEVEMENTS_TO_BE_REVOKED:
                    newElementUri = StoryDBDescription.GlobalAchievementsToBeRevoked.buildGlobalAchievementToBeRevokedUri(rowID);
                    break;
                case PARTY_ACHIEVEMENTS_TO_BE_AWARDED:
                    newElementUri = StoryDBDescription.PartyAchievementsToBeAwarded.buildPartyAchievementToBeAwardedUri(rowID);
                    break;
                case PARTY_ACHIEVEMENTS_TO_BE_REVOKED:
                    newElementUri = StoryDBDescription.PartyAchievementsToBeRevoked.buildPartyAchievementToBeRevokedUri(rowID);
                    break;
                case LOCATIONS_TO_BE_UNLOCKED:
                    newElementUri = StoryDBDescription.LocationsToBeUnlocked.buildLocationToBeUnlockedUri(rowID);
                    break;
                case LOCATIONS_TO_BE_BLOCKED:
                    newElementUri = StoryDBDescription.LocationsToBeBlocked.buildLocationToBeBlockedUri(rowID);
                    break;
                case ADD_REWARD_APPLICATION_TYPES:
                    newElementUri = StoryDBDescription.AddRewardApplicationTypes.buildAddRewardApplicationTypeUri(rowID);
                    break;
                case ADD_REWARD_TYPES:
                    newElementUri = StoryDBDescription.AddRewardTypes.buildAddRewardTypeUri(rowID);
                    break;
                case ADD_REWARDS:
                    newElementUri = StoryDBDescription.AddRewards.buildAddRewardUri(rowID);
                    break;
                case ADD_PENALTIES:
                    newElementUri = StoryDBDescription.AddPenalties.buildAddPenaltyUri(rowID);
                    break;
                case CHARACTER_CLASSES:
                    newElementUri = StoryDBDescription.CharacterClasses.buildCharacterClassUri(rowID);
                    break;
                case YES_NO_RESPONSES:
                    newElementUri = StoryDBDescription.YesNoResponses.buildYesNoResponseUri(rowID);
            }

            // update the content resolver that the specific table has been changed
            if(getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }
        return newElementUri;
    }

    // delete the row in the given table with the given ID
    // WARNING: SHOULD NEVER BE CALLED BY THE PROGRAM
    @Override // TODO this method should throw exception and not delete anything from the database
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

    // update the row in the given table with the given ID
    // WARNING: SHOULD NEVER BE CALLED BY THE PROGRAM
    @Override // TODO this method should throw exception and not update the database
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
            case YES_NO_RESPONSES:
                tableName = StoryDBDescription.YesNoResponses.TABLE_NAME;
                break;
            default:
                throw new SQLException(getContext() != null ?  "Invalid Query Uri: " + uri : null);
        }
        return tableName;
    }
}
