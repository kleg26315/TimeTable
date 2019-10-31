package com.example.timetabletest.Util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.example.timetabletest.DetailActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class    Task extends AsyncTask<String, Void, String> {

    private String str, receiveMsg;

    @Override
    protected String doInBackground(String... params) {
        URL url = null;

        try {
            url = new URL("https://k03c8j1o5a.execute-api.ap-northeast-2.amazonaws.com/v1/programmers" + params[0]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("x-api-key", "QJuHAX8evMY24jvpHfHQ4pHGetlk5vn8FJbk70O6");
            conn.setRequestProperty("Content-Type", "application/json");

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }


}