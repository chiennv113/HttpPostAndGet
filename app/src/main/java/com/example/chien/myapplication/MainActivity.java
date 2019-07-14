package com.example.chien.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private EditText mEdtUserName;
    private EditText mEdtPass;
    private EditText mEdtName;
    private Button mButton;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mEdtUserName.getText().toString();
                String pass = mEdtPass.getText().toString();

                if (userName.equals("admin") && pass.equals("123456")) {
                    String name = mEdtName.getText().toString();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("key", name);
                    startActivity(intent);
                    AsyncTask asyncTask = new AsyncTask();
                    asyncTask.execute("http://dotplays.com/android/login.php");
                } else {
                    Toast.makeText(MainActivity.this, "Sai tai khoan hoac mat khau", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public class AsyncTask extends android.os.AsyncTask<String, Long, String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }


        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");


                // khoi tao param
                StringBuilder params = new StringBuilder();

                params.append(mEdtUserName.getText().toString());
                params.append(mEdtPass.getText().toString());


                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter
                        (new OutputStreamWriter(os, "UTF-8"));

                // dua param vao body cua request
                writer.append(params);

                // giai phong bo nho
                writer.flush();
                // ket thuc truyen du lieu vao output
                writer.close();
                os.close();


                // lay du lieu tra ve
                StringBuilder response = new StringBuilder();

                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                }


                return response.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    private void initView() {
        mImageView = findViewById(R.id.imageView);
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtPass = findViewById(R.id.edtPass);
        mEdtName = findViewById(R.id.edtName);
        mButton = findViewById(R.id.button);
    }


}
