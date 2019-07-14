package com.example.chien.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Post extends AsyncTask<String, Long, String> {

    String result;
    String userName, pass;

    public Post(String result) {
        this.result = result;
    }

    public Post(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
