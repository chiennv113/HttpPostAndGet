package com.example.chien.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mTextView2;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        final Intent intent = getIntent();
        String name = intent.getStringExtra("key");
        mTextView.setText("Xin chào nhân viên " + name);


        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("http://dotplays.com/android/bai1.php?food=today\n");


        mButton2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent1 = new Intent(HomeActivity.this,MainActivity.class);
                                            startActivity(intent1);
                                        }
                                    }
        );
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        mTextView2 = findViewById(R.id.textView2);
        mButton2 = findViewById(R.id.button2);
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
            Log.e("data", s);
            mTextView2.setText(s.replace("\\n", Objects.requireNonNull(System.getProperty("line.separator"))));

        }
    }
}

