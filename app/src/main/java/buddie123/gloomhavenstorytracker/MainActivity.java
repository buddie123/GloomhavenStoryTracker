package buddie123.gloomhavenstorytracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import buddie123.gloomhavenstorytracker.database.story.StoryDBDescription;
import buddie123.gloomhavenstorytracker.database.story.StoryDBHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StoryDBHelper dbHelper = new StoryDBHelper(this);
        dbHelper.getReadableDatabase();

        // AchievementTypes
        Cursor cursor;
        cursor = getContentResolver().query(
                StoryDBDescription.AchievementTypes.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.AchievementTypes.TABLE_NAME + ": " + cursor.toString());
        assert cursor.getColumnCount() == 2;
        cursor.getColumnIndexOrThrow(StoryDBDescription.AchievementTypes._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AchievementTypes.COLUMN_TYPE);
        cursor.close();

        // CharacterClasses
        cursor = getContentResolver().query(
                StoryDBDescription.CharacterClasses.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.CharacterClasses.TABLE_NAME + ": " + cursor.toString());
        assert cursor.getColumnCount() == 2;
        cursor.getColumnIndexOrThrow(StoryDBDescription.CharacterClasses._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.CharacterClasses.COLUMN_NAME);
        cursor.close();


        cursor = getContentResolver().query(
                StoryDBDescription.Locations.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.Locations.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 5;
        cursor.getColumnIndexOrThrow(StoryDBDescription.Locations._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.Locations.COLUMN_NAME);
        cursor.getColumnIndexOrThrow(StoryDBDescription.Locations.COLUMN_TEASER);
        cursor.getColumnIndexOrThrow(StoryDBDescription.Locations.COLUMN_SUMMARY);
        cursor.getColumnIndexOrThrow(StoryDBDescription.Locations.COLUMN_CONCLUSION);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.GlobalAchievements.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.GlobalAchievements.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 4;
        cursor.getColumnIndexOrThrow(StoryDBDescription.GlobalAchievements._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.GlobalAchievements.COLUMN_NAME);
        cursor.getColumnIndexOrThrow(StoryDBDescription.GlobalAchievements.COLUMN_GROUP_NAME);
        cursor.getColumnIndexOrThrow(StoryDBDescription.GlobalAchievements.COLUMN_MAX_COUNT);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.GlobalAchievementsToBeAwarded.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.GlobalAchievementsToBeAwarded.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 4;
        cursor.getColumnIndexOrThrow(StoryDBDescription.GlobalAchievementsToBeAwarded._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.GlobalAchievementsToBeAwarded.COLUMN_LOCATION_TO_BE_COMPLETED_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.GlobalAchievementsToBeAwarded.COLUMN_GLOBAL_ACHIEVEMENT_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.GlobalAchievementsToBeAwarded.COLUMN_CONDITION);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.PartyAchievements.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.PartyAchievements.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 2;
        cursor.getColumnIndexOrThrow(StoryDBDescription.PartyAchievements._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.PartyAchievements.COLUMN_NAME);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.PartyAchievementsToBeAwarded.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.PartyAchievementsToBeAwarded.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 3;
        cursor.getColumnIndexOrThrow(StoryDBDescription.PartyAchievementsToBeAwarded._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.PartyAchievementsToBeAwarded.COLUMN_LOCATION_TO_BE_COMPLETED_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.PartyAchievementsToBeAwarded.COLUMN_PARTY_ACHIEVEMENT_ID);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.PartyAchievementsToBeRevoked.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.PartyAchievementsToBeRevoked.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 3;
        cursor.getColumnIndexOrThrow(StoryDBDescription.PartyAchievementsToBeRevoked._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.PartyAchievementsToBeRevoked.COLUMN_LOCATION_TO_BE_COMPLETED_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.PartyAchievementsToBeRevoked.COLUMN_PARTY_ACHIEVEMENT_ID);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.LocationsToBeUnlocked.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.LocationsToBeUnlocked.TABLE_NAME + ": " + cursor.toString());
        assert cursor.getColumnCount() == 4;
        cursor.getColumnIndexOrThrow(StoryDBDescription.LocationsToBeUnlocked._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.LocationsToBeUnlocked.COLUMN_LOCATION_TO_BE_COMPLETED_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.LocationsToBeUnlocked.COLUMN_LOCATION_TO_BE_UNLOCKED_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.LocationsToBeUnlocked.COLUMN_CONDITION_ID);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.LocationsToBeBlocked.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.LocationsToBeBlocked.TABLE_NAME + ": " + cursor.toString());
        assert cursor.getColumnCount() == 4;
        cursor.getColumnIndexOrThrow(StoryDBDescription.LocationsToBeBlocked._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.LocationsToBeBlocked.COLUMN_LOCATION_TO_BE_BLOCKED_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.LocationsToBeBlocked.COLUMN_INCOMPLETE_ACHIEVEMENT_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.LocationsToBeBlocked.COLUMN_INCOMPLETE_ACHIEVEMENT_TYPE_ID);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.ApplicationTypes.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.ApplicationTypes.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 2;
        cursor.getColumnIndexOrThrow(StoryDBDescription.ApplicationTypes._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.ApplicationTypes.COLUMN_APPLICATION_TYPE);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.AdditionalRewardTypes.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.AdditionalRewardTypes.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 2;
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalRewardTypes._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalRewardTypes.COLUMN_REWARD_TYPE);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.YesNoResponses.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.YesNoResponses.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 2;
        cursor.getColumnIndexOrThrow(StoryDBDescription.YesNoResponses._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.YesNoResponses.COLUMN_RESPONSE);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.AdditionalRewards.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.AdditionalRewards.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 5;
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalRewards._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalRewards.COLUMN_LOCATION_TO_BE_COMPLETED_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalRewards.COLUMN_REWARD_TYPE_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalRewards.COLUMN_REWARD_VALUE);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalRewards.COLUMN_REWARD_APPLICATION_TYPE_ID);
        cursor.close();

        cursor = getContentResolver().query(
                StoryDBDescription.AdditionalPenalties.CONTENT_URI,
                null,null,null,null);
        System.out.println(StoryDBDescription.AdditionalPenalties.TABLE_NAME + ": " +cursor.toString());
        assert cursor.getColumnCount() == 5;
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalPenalties._ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalPenalties.COLUMN_LOCATION_TO_BE_COMPLETED_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalPenalties.COLUMN_PENALTY_TYPE_ID);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalPenalties.COLUMN_PENALTY_VALUE);
        cursor.getColumnIndexOrThrow(StoryDBDescription.AdditionalPenalties.COLUMN_PENALTY_APPLICATION_TYPE_ID);
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
