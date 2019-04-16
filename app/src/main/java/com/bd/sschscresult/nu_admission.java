package com.bd.sschscresult;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.HashMap;

public class nu_admission extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    HashMap<String,String> URLLink;
    MainActivity myMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nu_admission);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Bar color
        ActionBar webActivity = getSupportActionBar();
        webActivity.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        if (webActivity != null) {
            webActivity.setDisplayHomeAsUpEnabled(true);
            webActivity.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }


        // Load an ad into the AdMob banner view.
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


        // Interestitial Ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        //End Interestitial Ad

        myMainActivity = new MainActivity();

        URLLink = myMainActivity.URLLink;

    }


    //For internet connection

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    //End internet connection

    // For API data
    public void getAPIData() {

        if (isNetworkConnected()) {
            Mydata mydata = new Mydata(this);

            mydata.getUrlData(new Mydata.VolleyCallback() {
                @Override
                public void onSuccess(HashMap<String,String> result) {
                  //  Log.e("Got the ALl the URL", result.get(3));
                    URLLink = result;
                }
            });

            Toast.makeText(getApplicationContext(), "Click Again", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();

        }
    }


    public void nu_honours_AdmissionResult(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        String URL = "";
        if (isNetworkConnected()) {

            if (URLLink != null) {
                for (String i : URLLink.keySet()) {
                    if (i.equals("nu_honours_AdmissionResult")) {
                        URL = URLLink.get(i);
                        Log.d("Loading Site URL:", URL);
                        break;

                    }

                }

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {

                getAPIData();

            }


        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }


    }

    public void degree_admission(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        String URL = "";
        if (isNetworkConnected()) {

            if (URLLink != null) {
                for (String i : URLLink.keySet()) {
                    if (i.equals("degree_admission")) {
                        URL = URLLink.get(i);
                        Log.d("Loading Site URL:", URL);
                        break;

                    }

                }

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {

                getAPIData();

            }


        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }


    }

    public void masters_preliminary(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        String URL = "";
        if (isNetworkConnected()) {

            if (URLLink != null) {
                for (String i : URLLink.keySet()) {
                    if (i.contains("masters_preliminary")) {
                        URL = URLLink.get(i);
                        Log.d("Loading Site URL:", URL);
                        break;

                    }

                }

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {

                getAPIData();

            }


        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }

    public void masters_professional(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        String URL = "";
        if (isNetworkConnected()) {

            if (URLLink != null) {
                for (String i : URLLink.keySet()) {
                    if (i.contains("masters_professional")) {
                        URL = URLLink.get(i);
                        Log.d("Loading Site URL:", URL);
                        break;

                    }

                }

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {

                getAPIData();

            }


        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }


    }

    public void masters_Regular(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        String URL = "";
        if (isNetworkConnected()) {

            if (URLLink != null) {
                for (String i : URLLink.keySet()) {
                    if (i.contains("masters_Regular")) {
                        URL = URLLink.get(i);
                        Log.d("Loading Site URL:", URL);
                        break;

                    }

                }

                Intent intent = new Intent(getApplicationContext(), BdResultActivity.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            } else {

                getAPIData();

            }


        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.govtresult, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_close) {
            finish();

            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            //  overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        }


        return super.onOptionsItemSelected(item);
    }

}
