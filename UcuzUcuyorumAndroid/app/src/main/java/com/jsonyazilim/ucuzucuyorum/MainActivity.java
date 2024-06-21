package com.jsonyazilim.ucuzucuyorum;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webview=(WebView)findViewById(R.id.webview);
        webview.setWebViewClient(new MyWebViewClient()
        );
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.getCacheMode();
        webview.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        webview.loadUrl("https://ucuzucuyorum.com/");

    }

    class MyWebViewClient extends WebViewClient
    {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String  url)
        {

            if( URLUtil.isNetworkUrl(url) )
            {
                return false;
            }
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }catch(ActivityNotFoundException e)
            {
                Log.e("AndroiRide",e.toString());
                Toast.makeText(MainActivity.this,"Uygulama Bulunamadı",Toast.LENGTH_LONG).show();
            }

            return true;
        }

        @RequiresApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
        {
            String url=request.getUrl().toString();
            if( URLUtil.isNetworkUrl(url) )
            {
                return false;
            }
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }catch(ActivityNotFoundException e)
            {
                Log.e("AndroiRide",e.toString());
                Toast.makeText(MainActivity.this,"Uygulama Bulunamadı",Toast.LENGTH_LONG).show();
            }

            return true;
        }
    }
    @Override
    public void	 onBackPressed() {
        if (webview.canGoBack()){
            webview.goBack();
        }else{
            super.onBackPressed();
        }

    }
}
