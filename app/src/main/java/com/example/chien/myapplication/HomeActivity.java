package com.example.chien.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mTextView2;
    private Button mButton2;
    private RecyclerView mRv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        final Intent intent = getIntent();
        String name = intent.getStringExtra("key");
        mTextView.setText("Xin chào nhân viên " + name);



        MyAsyncTask myAsyncTask = new MyAsyncTask();
//        myAsyncTask.execute("http://dotplays.com/android/bai1.php?food=today\n");
        myAsyncTask.execute("http://asian.dotplays.com/wp-json/wp/v2/posts?embed");


        mButton2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent1 = new Intent(HomeActivity.this, MainActivity.class);
                                            startActivity(intent1);
                                        }
                                    }
        );
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        mTextView2 = findViewById(R.id.textView2);
        mButton2 = findViewById(R.id.button2);
        mRv = findViewById(R.id.rv);
    }

    public class MyAsyncTask extends AsyncTask<String, Long, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                String data = "";

                while (scanner.hasNext()) {
                    data = data + scanner.nextLine();
                }

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //cach 1
            try {
                List<Model> modelList = new ArrayList<>();
                JSONArray root = new JSONArray(s);

                for (int i = 0; i < root.length(); i++) {
                    JSONObject post = root.getJSONObject(i);
                    int id = post.getInt("id");
                    String date = post.getString("date");

                    JSONObject title = post.getJSONObject("title");
                    String rendered = title.getString("rendered");

                    Log.e("id", String.valueOf(id));
                    Log.e("date", String.valueOf(date));
                    Log.e("date", rendered);

                    Model model = new Model(id,date,rendered);
                    modelList.add(model);


                    Log.e("ModelList", "" + modelList.size());

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //cach 2
//            JsonParser jsonParser = new JsonParser();
//            JsonElement jsonElement =jsonParser.parse(s);
//            JsonArray root = jsonElement.getAsJsonArray();
//
//            List<Model> modelList = new ArrayList<>();
//            for (int i=0;i<root.size();i++){
//                JsonObject post = root.get(i).getAsJsonObject();
//                int id = post.get("id").getAsInt();
//                String date = post.get("date").getAsString();
//                JsonObject title = post.get("title").getAsJsonObject();
//                String rendered = title.get("rendered").getAsString();
//
//                Model model = new Model();
//                model.title = rendered;
//                model.date = date;
//                model.id = id;
//                modelList.add(model);
//
//                Log.e("Model Size", "" + modelList.size());
//            }


        }
    }
}

