package com.yandex.mobilization.ymapp.activity;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yandex.mobilization.ymapp.R;

public class ArtistDetailActivity extends AppCompatActivity {

    private String coverintent;
    private String albumsintent;
    private String trakcsintent;
    private String descrintent;
    private String genresintent;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String titleintent;
    private ActionBar actionBar;
    private FloatingActionButton fab;
    private String linkintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artistdetail);

        final ImageView detailcover;
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
        linkintent=intent.getStringExtra(MainActivity.EXTRA_LINK);
        titleintent=intent.getStringExtra(MainActivity.EXTRA_TITLE);
        coverintent = intent.getStringExtra(MainActivity.EXTRA_BCURL);
        albumsintent = intent.getStringExtra(MainActivity.EXTRA_ALBUMS);
        trakcsintent = intent.getStringExtra(MainActivity.EXTRA_TRACKS);
        descrintent = intent.getStringExtra(MainActivity.EXTRA_DESCR);
        genresintent = intent.getStringExtra(MainActivity.EXTRA_GENRES);

        detailalbums.setText("альбомов: " + albumsintent);
        detailtracks.setText("песен: " + trakcsintent);
        detaildescription.setText(descrintent);
        detailgenre.setText(genresintent.substring(1,genresintent.length()-1));


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

        /*Picasso.with(this)
                .load(coverintent)
                .into(detailcover);*/

        Picasso.with(this)
                .load(coverintent)
                .resize(1050,1050)
                .centerInside()
                .into(detailcover, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable)detailcover.getDrawable()).getBitmap();
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                applyPalette(palette);
                            }
                        });
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void applyPalette(Palette palette) {
        int primaryDark=getResources().getColor(R.color.colorPrimary);
        int primary = getResources().getColor(R.color.colorPrimary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        updateBackground((FloatingActionButton) findViewById(R.id.fab), palette);
        supportStartPostponedEnterTransition();
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.colorYellow));

        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkintent));
                    startActivity(browserIntent);
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Link is not correct, opening Google",  Toast.LENGTH_LONG).show();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.ru#newwindow=1&q="+titleintent));
                    startActivity(browserIntent);
                    e.printStackTrace();
                }

            }
        });
    }
}
