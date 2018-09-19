package buddie123.gloomhavenstorytracker.database.story;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class StoryDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Story.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    // constructor
    public StoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;


    }


    // TODO revisit this code
    // creates each table in the Story.db database when the database
    // is created
    @Override
    public void onCreate(SQLiteDatabase db) {


        InputStream myInput = null;
        OutputStream myOutput = null;

        try {
            myInput = context.getAssets().open(DATABASE_NAME);
            String outFileName = db.getPath();
            Log.e("StoryDBHelper", outFileName);
            myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
        }
        catch(Exception e) {
            // TODO should alert the user that the program can't load
            Log.e ( "StoryDBHelper", "Something bad happened!", e );
        }
        finally{
            try {
                if(myOutput!=null) {
                    myOutput.flush();
                    myOutput.close();
                }
                if(myInput!=null) {
                    myInput.close();
                }
            }
            catch (Exception e) {
                // TODO not should what should happen here, if anything
                Log.e("StoryDBHelper", "trouble closing files", e);
            }
        }

    }

    // defines how to upgrade the database when the schema changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO Research how to implement this method
    }
}