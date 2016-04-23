package com.example.nyadmin.jsontest2;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nyadmin on 10.04.2016.
 */
public class JsonHepler {

    List<Artist> artists;

    public List<Artist> takeDatafromJson(Context context){
        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();
        artists =new ArrayList<Artist>();
        artists = Arrays.asList(gson.fromJson(loadJsonfromAssets(context),Artist[].class));
        return artists;
    }

    public String loadJsonfromAssets(Context context){
        String json=null;
        try {
            InputStream is=context.getAssets().open("artists.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}


