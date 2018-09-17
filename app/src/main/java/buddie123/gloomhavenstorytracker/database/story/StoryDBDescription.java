package buddie123.gloomhavenstorytracker.database.story;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class StoryDBDescription {
    // ContentProvider's name
    public static final String AUTHORITY =
            "buddie123.gloomhavenstorytracker.database.story";

    // base URI used to interact with the ContentProvider
    private static final Uri BASE_CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    // characterClasses
    public static final class CharacterClasses implements BaseColumns {
        public static final String TABLE_NAME = "character_classes";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_CLASS = "class";

        public static Uri buildCharacterClassUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


    // the Locations table records basic information about each location in the
    // game, including the teaser, summary, and conclusion texts.
    public static final class Locations implements BaseColumns {
        public static final String TABLE_NAME = "locations"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TEASER = "teaser";
        public static final String COLUMN_SUMMARY = "summary";
        public static final String COLUMN_CONCLUSION = "conclusion";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the GlobalAchievements table records information about each Global
    // Achievement in the game
    public static final class GlobalAchievements implements BaseColumns {
        public static final String TABLE_NAME = "global_achievements"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_GROUP = "group";
        public static final String COLUMN_MAX_COUNT = "max_count";

        public static Uri buildGlobalAchievementUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        // the GlobalAchievementsToBeAwarded table tracks which Global Achievements can be awarded
        // by each Location that awards a global achievement when completed
        public static final class GlobalAchievementsToBeAwarded implements BaseColumns {
            public static final String TABLE_NAME = "global_achievements_to_be_awarded"; // table's name

            public static final Uri CONTENT_URI =
                    BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

            public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
            public static final String COLUMN_GLOBAL_ACHIEVEMENT_ID = "global_achievement_id";

            public static Uri buildGlobalAchievementToBeAwardedUri(long id) {
                return ContentUris.withAppendedId(CONTENT_URI, id);
            }
        }
    }


    // the GlobalAchievementsToBeRevoked table tracks which Global Achievements will be
    // lost by each Location that revokes/replaces a global achievement when completed
    public static final class GlobalAchievementsToBeRevoked implements BaseColumns {
        public static final String TABLE_NAME = "global_achievements_to_be_revoked"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_GLOBAL_ACHIEVEMENT_ID = "global_achievement_id";

        public static Uri buildGlobalAchievementToBeRevokedUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the PartyAchievements table records all and entry for each Party Achievement in the game
    public static final class PartyAchievements implements BaseColumns {
        public static final String TABLE_NAME = "party_achievements"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_NAME = "name";

        public static Uri buildPartyAchievementUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


    // the PartyAchievementsToBeAwarded table tracks which Party Achievements can be awarded
    // by each Location that awards a party achievement when completed
    public static final class PartyAchievementsToBeAwarded implements BaseColumns {
        public static final String TABLE_NAME = "party_achievements_to_be_awarded"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_PARTY_ACHIEVEMENT_ID = "party_achievement_id";

        public static Uri buildPartyAchievementToBeAwardedUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the PartyAchievementsToBeRevoked table tracks which Party Achievements can be revoked
    // by each Location that revokes a party achievement when completed
    public static final class PartyAchievementsToBeRevoked implements BaseColumns {
        public static final String TABLE_NAME = "party_achievements_to_be_revoked"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_PARTY_ACHIEVEMENT_ID = "party_achievement_id";

        public static Uri buildPartyAchievementToBeRevokedUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


    // the LocationsToBeUnlocked table records which locations will be unlocked by each
    // location that unlocks one or more locations
    public static final class LocationsToBeUnlocked implements BaseColumns {
        public static final String TABLE_NAME = "locations_to_be_unlocked"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // column names for contacts table's columns
        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_UNLOCKED_LOCATION_ID= "unlocked_location_id";

        public static Uri buildLocationToBeUnlockedUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the LocationsToBeBlocked table records which locations will be blocked by each
    // location that blocks one or more locations
    public static final class LocationsToBeBlocked implements BaseColumns {
        public static final String TABLE_NAME = "locations_to_be_blocked"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_BLOCKED_LOCATION_ID = "blocked_location_id";

        public static Uri buildLocationToBeBlockedUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // table AddRewardApplicationTypes lists the how rewards can be applied:
    // either individually("each") or collectively
    public static final class AddRewardApplicationTypes implements BaseColumns {
        public static final String TABLE_NAME = "add_reward_application_types";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_APPLICATION_TYPE = "application_type";

        public static Uri buildAddRewardApplicationTypeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // table AddRewardTypes lists the types of additional rewards that can be
    // gained/lost by a party
    public static final class AddRewardTypes implements BaseColumns {
        public static final String TABLE_NAME = "add_reward_types";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // column names
        public static final String COLUMN_REWARD_TYPE = "reward_type";

        public static Uri buildAddRewardTypeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the AddRewards table list the additional rewards that can be gained by
    // a party when a given location is completed
    public static final class AddRewards implements BaseColumns {
        public static final String TABLE_NAME = "add_rewards"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_REWARD_TYPE_ID = "reward_type_id";
        public static final String COLUMN_REWARD_VALUE = "reward_value";
        public static final String COLUMN_REWARD_APPLICATION_TYPE_ID = "reward_application_type";

        public static Uri buildAddRewardUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the AddPenalties table list the additional rewards that will be lost by
    // a party when a given location is completed
    public static final class AddPenalties implements BaseColumns {
        public static final String TABLE_NAME = "add_penalties"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed";
        public static final String COLUMN_PENALTY_TYPE_ID = "penalty_type_id";
        public static final String COLUMN_PENALTY_VALUE = "penalty_value";
        public static final String COLUMN_PENALTY_APPLICATION_TYPE_ID = "penalty_application_type_id";

        public static Uri buildAddPenaltyUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
