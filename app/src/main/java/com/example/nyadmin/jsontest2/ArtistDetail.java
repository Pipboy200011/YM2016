package com.example.nyadmin.jsontest2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ArtistDetail extends AppCompatActivity {

    private String coverintent;
    private String albumsintent;
    private String trakcsintent;
    private String descrintent;
    private String genresintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artistdetail);

        ImageView detailcover;
        TextView detailalbums;
        TextView detailtracks;
        TextView detailgenre;
        TextView detaildescription;

        detailcover = (ImageView) findViewById(R.id.detailcover);
        detailalbums = (TextView) findViewById(R.id.detailalbums);
        detailtracks = (TextView) findViewById(R.id.detailtracks);
        detailgenre = (TextView) findViewById(R.id.detailgenre);
        detaildescription = (TextView) findViewById(R.id.detaildescription);

        Intent intent = getIntent();
        coverintent = intent.getStringExtra(MainActivity.EXTRA_BCURL);
        albumsintent = intent.getStringExtra(MainActivity.EXTRA_ALBUMS);
        trakcsintent = intent.getStringExtra(MainActivity.EXTRA_TRACKS);
        descrintent = intent.getStringExtra(MainActivity.EXTRA_DESCR);
        genresintent=intent.getStringExtra(MainActivity.EXTRA_GENRES);

        detailalbums.setText("альбомов: "+albumsintent);
        detailtracks.setText("песен: "+trakcsintent);
        detaildescription.setText(descrintent);
        detailgenre.setText(genresintent);

    }
}
