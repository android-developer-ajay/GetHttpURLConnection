package com.ajay.developer.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // new POSTjsonRequest().execute();
    }

    public class GETJsonRequest extends AsyncTask<String, String, String> {
        StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL("http://developer-ajay.16mb.com/pesona/test.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String Str;
                while ((Str = bufferedReader.readLine()) != null) {
                    stringBuilder.append(Str);
                    publishProgress(Str);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("AJU", "" + s);
        }
    }


    private class PostJsonRequest extends AsyncTask<String, String, String> {
        StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            int id = 21;
            String query = "id=" + id;


            try {
                URL url = new URL("http://developer-ajay.16mb.com/pesona/test.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                outputStream.writeBytes(query);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                int responseCode = httpURLConnection.getResponseCode();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String data;
                while ((data = reader.readLine()) != null) {
                    stringBuilder.append(data);
                    publishProgress(data);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("JOSHI", "" + s);
        }
    }


    public class JsonPutRequest extends AsyncTask<String, String, String> {

        StringBuilder stringBuilder = new StringBuilder();
        JSONObject user;

        @Override
        protected void onPreExecute() {


            user = new JSONObject();
            try {
                user.put("user_id", "33");
                user.put("version", "2");
            } catch (JSONException e) {
                e.printStackTrace();
                //      Toast.makeText(CreateRoom.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL("http://clients.vfactor.in/puttdemo/getsuggessionfriendlist");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("Authorization", "vk2DjNMIoaggQ221");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(user.toString());
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                int responseCode = httpURLConnection.getResponseCode();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String data;
                while ((data = bufferedReader.readLine()) != null) {
                    stringBuilder.append(data);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}