package com.example.nyadmin.jsontest2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ArtistList extends CursorRecyclerViewAdapter<ArtistList.MyCardHolder> {

    private final Context context;

    public ArtistList(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }


    @Override
    public void onBindViewHolder(MyCardHolder viewHolder, Cursor cursor) {


        String artistpicdb = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SMALL_COVER_URL));


        Picasso
                .with(context)
                .load(artistpicdb)
                .resize(320,320)
                .centerInside()
                .into(viewHolder.artistpic);




        viewHolder.titledb.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE)));

        String albumsdb = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ALBUMS));
        String tracksdb = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TRACKS));

        viewHolder.albumsandtracksdb.setText("альбомов: "+albumsdb+" песен: "+tracksdb);

        String genresdb=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENRES));
        viewHolder.genresdb.setText(genresdb);
    }

    @Override
    public MyCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.artistlist,
                parent,
                false
        );


        return new MyCardHolder(view);
    }


    public static class MyCardHolder extends RecyclerView.ViewHolder {

        TextView genresdb;
        TextView albumsandtracksdb;
        ImageView artistpic;
        TextView titledb;


        public MyCardHolder(View view) {
            super(view);


            artistpic = (ImageView) view.findViewById(R.id.artistiv);
            titledb = (TextView) view.findViewById(R.id.artistnametv);
            albumsandtracksdb = (TextView)view.findViewById(R.id.artistalbumstrackstv);
            genresdb=(TextView)view.findViewById(R.id.artistgenrestv);

        }


    }

}
