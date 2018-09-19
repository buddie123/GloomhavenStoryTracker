package buddie123.gloomhavenstorytracker;

import android.app.Activity;
import android.database.Cursor;
import android.provider.Settings;

import junit.framework.TestSuite;

import org.junit.Assert;
import org.junit.Test;

import buddie123.gloomhavenstorytracker.database.story.StoryDBDescription;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class StoryDatabaseStructureTests extends TestSuite {
    public StoryDatabaseStructureTests () {
        super(MainActivity.class);
    }

    @Test
    public void load_achievement_types() {
        Activity activity = new MainActivity();
        Cursor cursor= activity.getContentResolver().query(
                StoryDBDescription.AchievementTypes.CONTENT_URI,
                null,null,null,null);
        System.out.println("AchievementTypes: " + cursor.toString());
        int typeIndex = cursor.getColumnIndexOrThrow(StoryDBDescription.AchievementTypes.COLUMN_TYPE);
        while(cursor.moveToFirst()) {
            System.out.println(cursor.getString(typeIndex));
        }
    }
}