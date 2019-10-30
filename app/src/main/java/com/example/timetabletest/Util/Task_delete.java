package com.example.timetabletest.Util;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Task_delete extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = null;
            url = new URL("https://k03c8j1o5a.execute-api.ap-northeast-2.amazonaws.com/v1/programmers/timetable");

            JSONObject json = new JSONObject();
            json.put("user_key","0035d7d225158bfb9a5b3b4437961b9e");
            json.put("code",params[0]);
            String body2 = json.toString();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setDoOutput(true);
            conn.setRequestProperty("x-api-key", "QJuHAX8evMY24jvpHfHQ4pHGetlk5vn8FJbk70O6");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(body2.getBytes());
            os.flush();

            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {       // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                builder.append(str + "\n");                     // View에 표시하기 위해 라인 구분자 추가
            }

            Log.d("POST", "POST COMPLETE");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}