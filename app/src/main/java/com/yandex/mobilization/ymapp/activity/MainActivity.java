package com.yandex.mobilization.ymapp.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.yandex.mobilization.ymapp.recycler.ArtistList;
import com.yandex.mobilization.ymapp.db.DatabaseHelper;
import com.yandex.mobilization.ymapp.R;
import com.yandex.mobilization.ymapp.recycler.RecyclerItemClickListener;

import java.io.File;


import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_TRACKS = "trakcs";
    public static final String EXTRA_ALBUMS = "albums";
    public static final String EXTRA_LINK = "link";
    public static final String EXTRA_GENRES = "genres";
    public static final String EXTRA_DESCR = "descr";
    public static final String EXTRA_SCURL = "scurl";
    public static final String EXTRA_BCURL = "bcurl";
    private DatabaseHelper dbhelp;
    private SQLiteDatabase mdb;
    private Cursor mcursor;
    private RecyclerView recyclerview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        deleteDatabase(DatabaseHelper.DB_NAME);





        recyclerview1 = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerview1.setLayoutManager(new LinearLayoutManager(this));



        dbhelp = new DatabaseHelper(this);
        mdb = dbhelp.getReadableDatabase();
        mcursor = mdb.query(DatabaseHelper.DB_TABLE, null, null, null, null, null, null);


        recyclerview1.setAdapter(new SlideInRightAnimationAdapter(new ArtistList(this,mcursor)));

        recyclerview1.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mcursor.moveToPosition(position);
                String item_title = mcursor.getString(mcursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE));
                String item_tracks = mcursor.getString(mcursor.getColumnIndex(DatabaseHelper.COLUMN_TRACKS));
                String item_albums = mcursor.getString(mcursor.getColumnIndex(DatabaseHelper.COLUMN_ALBUMS));
                String item_link = mcursor.getString(mcursor.getColumnIndex(DatabaseHelper.COLUMN_LINK));
                String item_genres = mcursor.getString(mcursor.getColumnIndex(DatabaseHelper.COLUMN_GENRES));
                String item_descr = mcursor.getString(mcursor.getColumnIndex(DatabaseHelper.COLUMN_DESCR));
                String item_Scurl = mcursor.getString(mcursor.getColumnIndex(DatabaseHelper.COLUMN_SMALL_COVER_URL));
                String item_Bcurl = mcursor.getString(mcursor.getColumnIndex(DatabaseHelper.COLUMN_BIG_COVER_URL));

                Intent intent=new Intent(MainActivity.this,ArtistDetailActivity.class);
                intent.putExtra(EXTRA_TITLE,item_title);
                intent.putExtra(EXTRA_TRACKS,item_tracks);
                intent.putExtra(EXTRA_ALBUMS,item_albums);
                intent.putExtra(EXTRA_LINK,item_link);
                intent.putExtra(EXTRA_GENRES,item_genres);
                intent.putExtra(EXTRA_DESCR,item_descr);
                intent.putExtra(EXTRA_SCURL,item_Scurl);
                intent.putExtra(EXTRA_BCURL,item_Bcurl);
                startActivity(intent);
               // overridePendingTransition(R.anim.rotate,R.anim.rotate);


            }
        }
        ));

    }


    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        }
        else if(dir!= null && dir.isFile())
            return dir.delete();
        else {
            return false;
        }
    }


    @Override
    protected void onDestroy() {
        deleteCache(this);
        super.onDestroy();
    }
}


