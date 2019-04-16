package com.bd.sschscresult;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class BdResultActivity extends AppCompatActivity {
    WebView bdresult;
    ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd_result);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        //Bar color
        ActionBar webActivity = getSupportActionBar();
        webActivity.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        if (webActivity != null) {
            webActivity.setDisplayHomeAsUpEnabled(true);
            webActivity.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        //

        //WebView
        bdresult = (WebView) findViewById(R.id.web1);


        //Improve wevView performance


        bdresult.clearCache(true);
        WebSettings webSettings = bdresult.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(false);

        //Test
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //

        webSettings.setDisplayZoomControls(false);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setBuiltInZoomControls(true);


        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);


        webSettings.setEnableSmoothTransition(true);

        bdresult.setInitialScale(1);
        bdresult.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        bdresult.setHorizontalScrollBarEnabled(false);


        // Get URL

        String URL = getIntent().getStringExtra("URL");

        bdresult.setWebViewClient(new mywebClient());
        bdresult.loadUrl(URL);
        proBar = (ProgressBar) findViewById(R.id.progressBar1);


        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

//        bdresult.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading (WebView view, String url) {
//                if (url.endsWith(".pdf")) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                    // if want to download pdf manually create AsyncTask here
//                    // and download file
//                    return true;
//                }
//                return false;
//            }
//        });

        bdresult.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });


    }

    //For webview progress bar loading

    public class mywebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            proBar.setVisibility(View.GONE);
            setTitle(view.getTitle());


        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            proBar.setVisibility(View.VISIBLE);
            //setTitle("Loading.....");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }


    }


    //WebView back button

    @Override
    public void onBackPressed() {
        if (bdresult.canGoBack()) {
            bdresult.goBack();
        } else {
            super.onBackPressed();
        }

    }

    //end WebView back button

    // For Menu button

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bdresultmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            finish();

            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            //  overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        }


        return super.onOptionsItemSelected(item);
    }

}
