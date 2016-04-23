package com.example.nyadmin.jsontest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Nyadmin on 10.04.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context mcontext;
    private JsonHepler jsonhelper;
    List<Artist> artists;


    final static String DB_NAME = "yandexartist.db";
    final static int DB_VERSION = 1;
    public final static String DB_TABLE = "Artist";
    public static final String COLUMN_ID = "Artist_id";
    public static final String COLUMN_TITLE = "Artist_title";
    public static final String COLUMN_TRACKS="Artist_tracks";
    public static final String COLUMN_ALBUMS="Artist_albums";
    public static final String COLUMN_GENRES = "Artist_genres";
    public static final String COLUMN_LINK="Artist_link";
    public static final String COLUMN_DESCR="Artist_descr";
    public static final String COLUMN_SMALL_COVER_URL = "Artist_small_cover_url";
    public static final String COLUMN_BIG_COVER_URL = "Artist_big_cover_url";

    public static final String[] COLUMNS = {COLUMN_ID, COLUMN_TITLE, COLUMN_TRACKS,COLUMN_ALBUMS,COLUMN_GENRES,
            COLUMN_LINK,COLUMN_DESCR,COLUMN_SMALL_COVER_URL,COLUMN_BIG_COVER_URL};



    private static String
            DB_CREATE = "CREATE TABLE " + DB_TABLE +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ID + " INTEGER, " + COLUMN_TITLE + " TEXT UNIQUE ON CONFLICT REPLACE NOT NULL ON CONFLICT ROLLBACK, " +
            COLUMN_TRACKS + " TEXT NOT NULL ON CONFLICT ROLLBACK, " +
            COLUMN_ALBUMS + " TEXT NOT NULL ON CONFLICT ROLLBACK, " +
            COLUMN_LINK + " TEXT, " +
            COLUMN_GENRES + " TEXT, " +
            COLUMN_DESCR + " TEXT, " +
            COLUMN_SMALL_COVER_URL + " TEXT, " +
            COLUMN_BIG_COVER_URL + " TEXT);";






    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        jsonhelper=new JsonHepler();
        artists =jsonhelper.takeDatafromJson(mcontext);
        for (Artist artist : artists){
            InsertData(db, artist.getId(), artist.getName(), artist.getTracks(),artist.getAlbums(),
                    artist.getLink(), artist.getGenres().toString(), artist.getDescription(),artist.getCoverSmall(),artist.getCoverBig());

        }
    }

    private void InsertData(SQLiteDatabase db, long id, String title, int trakcs, int albums, String link, String genres,
                            String descr, String Scurl, String Bcurl) {
        ContentValues values=new ContentValues(1);
        values.put(COLUMN_ID,id);
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_TRACKS,trakcs);
        values.put(COLUMN_ALBUMS,albums);
        values.put(COLUMN_LINK,link);
        values.put(COLUMN_GENRES,genres);
        values.put(COLUMN_DESCR,descr);
        values.put(COLUMN_SMALL_COVER_URL,Scurl);
        values.put(COLUMN_BIG_COVER_URL,Bcurl);
        db.insert(DB_TABLE,null,values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
