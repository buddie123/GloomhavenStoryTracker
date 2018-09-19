package buddie123.gloomhavenstorytracker.database.story;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class StoryDBDescription {
    // ContentProvider's name
    public static final String AUTHORITY =
            "buddie123.gloomhavenstorytracker.database.story";

    // base URI used to interact with the ContentProvider
    public static final Uri BASE_CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    //AchievementTypes
    public static final class AchievementTypes implements BaseColumns {
        public static final String TABLE_NAME = "achievement_types";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_TYPE = "type";

        public static Uri buildAchievementTypeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the character class keeps track of each of the 17 playable character
    // classes available in the game
    public static final class CharacterClasses implements BaseColumns {
        public static final String TABLE_NAME = "character_classes";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_NAME = "name";

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
        public static final String COLUMN_GROUP_NAME = "group_name";
        public static final String COLUMN_MAX_COUNT = "max_count";

        public static Uri buildGlobalAchievementUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the GlobalAchievementsToBeAwarded table tracks which Global Achievements can be awarded
    // by each Location that awards a global achievement when completed
    public static final class GlobalAchievementsToBeAwarded implements BaseColumns {
        public static final String TABLE_NAME = "global_achievements_to_be_awarded"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_GLOBAL_ACHIEVEMENT_ID = "global_achievement_id";
        public static final String COLUMN_CONDITION = "condition";

        public static Uri buildGlobalAchievementToBeAwardedUri(long id) {
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
        public static final String COLUMN_LOCATION_TO_BE_UNLOCKED_ID= "location_to_be_unlocked_id";
        public static final String COLUMN_CONDITION_ID = "condition_id";

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

        public static final String COLUMN_LOCATION_TO_BE_BLOCKED_ID = "location_to_be_blocked_id";
        public static final String COLUMN_INCOMPLETE_ACHIEVEMENT_ID = "incomplete_achievement_id";
        public static final String COLUMN_INCOMPLETE_ACHIEVEMENT_TYPE_ID = "incomplete_achievement_type_id";

        public static Uri buildLocationToBeBlockedUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // table AddRewardApplicationTypes lists the how rewards can be applied:
    // either individually("each") or collectively
    public static final class ApplicationTypes implements BaseColumns {
        public static final String TABLE_NAME = "application_types";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_APPLICATION_TYPE = "application_type";

        public static Uri buildApplicationTypeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // table AddRewardTypes lists the types of additional rewards that can be
    // gained/lost by a party
    public static final class AdditionalRewardTypes implements BaseColumns {
        public static final String TABLE_NAME = "additional_reward_types";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // column names
        public static final String COLUMN_REWARD_TYPE = "reward_type";

        public static Uri buildAddRewardTypeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

   // a table to hold the values yes and no....
    public static final class YesNoResponses implements BaseColumns {
        public static final String TABLE_NAME = "yes_no_responses";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // column names
        public static final String COLUMN_RESPONSE = "response";

        public static Uri buildYesNoResponseUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the AddRewards table list the additional rewards that can be gained by
    // a party when a given location is completed
    public static final class AdditionalRewards implements BaseColumns {
        public static final String TABLE_NAME = "additional_rewards"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_REWARD_TYPE_ID = "reward_type_id";
        public static final String COLUMN_REWARD_VALUE = "reward_value";
        public static final String COLUMN_REWARD_APPLICATION_TYPE_ID = "reward_application_type_id";

        public static Uri buildAddRewardUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // the AddPenalties table list the additional rewards that will be lost by
    // a party when a given location is completed
    public static final class AdditionalPenalties implements BaseColumns {
        public static final String TABLE_NAME = "additional_penalties"; // table's name

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String COLUMN_LOCATION_TO_BE_COMPLETED_ID = "location_to_be_completed_id";
        public static final String COLUMN_PENALTY_TYPE_ID = "penalty_type_id";
        public static final String COLUMN_PENALTY_VALUE = "penalty_value";
        public static final String COLUMN_PENALTY_APPLICATION_TYPE_ID = "penalty_application_type_id";

        public static Uri buildAdditionalPenaltyUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
