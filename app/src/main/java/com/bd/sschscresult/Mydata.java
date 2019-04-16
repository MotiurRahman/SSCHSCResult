package com.bd.sschscresult;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Mydata{

    private Context context;

    public Mydata(Context context){
        this.context=context;
    }


    public interface VolleyCallback {
        void onSuccess(HashMap<String,String> result);
    }


    private static final String URL_DATA = "https://5a4868926939da2c7c03186d1eb6d3c3531ff4c8.cloudapp-enterprise.appcelerator.com/api/resultlink";

    public void getUrlData(final VolleyCallback callback) {

      //  final ArrayList<String> urlData = new ArrayList<String>();
        final HashMap<String, String> url_data = new HashMap<String, String>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                if (!response.equals(null)) {


                    try {

                        JSONArray jsonArray = response.getJSONArray("resultlinks");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String url = jsonObject.getString("url");


                            //  Log.e("Site URL",url);
                            url_data.put(name,url);

                            //urlData.add(url + name);

                        }
                        callback.onSuccess(url_data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    Log.e("Your Array Response", "Data Null");
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }


            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String credentials = "b0ze5kVq2UjHdLIudqSi0UdHDOjJ7R6u:";
                String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic " + base64EncodedCredentials);
                return headers;
            }

        };
        RequestQueue queue = MySingleton.getInstance(context).getRequestQueue();
        queue.add(request);


    }


}

