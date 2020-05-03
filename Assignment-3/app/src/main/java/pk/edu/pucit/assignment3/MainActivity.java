package pk.edu.pucit.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> level = new ArrayList<>();
    ArrayList<String> info = new ArrayList<>();
    ArrayList<String> imagid= new ArrayList<>();
    ArrayList<String> bookUrl= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv;
        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray userArray = obj.getJSONArray("books");


            for (int i = 0; i < userArray.length(); i++) {

                JSONObject Detail = userArray.getJSONObject(i);

                title.add(Detail.getString("title"));

                level.add(Detail.getString("level"));
                info.add(Detail.getString("info"));
                imagid.add(Detail.getString("cover"));
                bookUrl.add(Detail.getString("url"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RVAdapter adapter = new RVAdapter(this, title, level,info, imagid,bookUrl);

        rv.setAdapter(adapter);
    }
    public String loadJSONFromAsset() {
        String json = "";
        try {

            InputStream is = getResources().openRawResource(R.raw.data);
            byte[] data = new byte[is.available()];


            while (is.read(data)!=-1){

            }
            json = new String(data);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}
