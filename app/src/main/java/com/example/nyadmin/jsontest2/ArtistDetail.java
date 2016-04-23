package com.example.nyadmin.jsontest2;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ArtistDetail extends AppCompatActivity {

    private String coverintent;
    private String albumsintent;
    private String trakcsintent;
    private String descrintent;
    private String genresintent;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String titleintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artistdetail);

        ImageView detailcover;
        TextView detailalbums;
        TextView detailtracks;
        TextView detailgenre;
        TextView detaildescription;

        detailcover = (ImageView) findViewById(R.id.image);
        detailalbums = (TextView) findViewById(R.id.detailalbums);
        detailtracks = (TextView) findViewById(R.id.detailtracks);
        detailgenre = (TextView) findViewById(R.id.detailgenre);
        detaildescription = (TextView) findViewById(R.id.detaildescription);

        Intent intent = getIntent();
        titleintent=intent.getStringExtra(MainActivity.EXTRA_TITLE);
        coverintent = intent.getStringExtra(MainActivity.EXTRA_BCURL);
        albumsintent = intent.getStringExtra(MainActivity.EXTRA_ALBUMS);
        trakcsintent = intent.getStringExtra(MainActivity.EXTRA_TRACKS);
        descrintent = intent.getStringExtra(MainActivity.EXTRA_DESCR);
        genresintent = intent.getStringExtra(MainActivity.EXTRA_GENRES);

        detailalbums.setText("альбомов: " + albumsintent);
        detailtracks.setText("песен: " + trakcsintent);
        detaildescription.setText(descrintent);
        detailgenre.setText(genresintent);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(titleintent);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));

        Picasso.with(this)
                .load(coverintent)
                .resize(1500,1500)
                .centerInside()
                .into(detailcover);
    }
}
