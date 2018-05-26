package com.example.syedfaizan.jsontest;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Syed Faizan on 5/27/2018.
 */

public class Request {
    public String makeRequest(){
        String urln = "http://jsonplaceholder.typicode.com/posts/1";

        String response = null;
        try {
            URL url = new URL(urln);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e("R: ", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("R: ",  "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("R: ", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("R: ", "Exception: " + e.getMessage());
        }

        Log.e("R: ", "Response from url: " + response);
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}