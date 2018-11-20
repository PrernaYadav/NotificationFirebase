package com.vmmor.femina;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    WebView webview;
    String url = "http://feminastudio.com/";
    boolean loadingFinished = true;
    boolean redirect = false;
    final Handler handler = new Handler();
    ProgressDialog p;
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    web();
                    webview.scrollTo(0, 0);
                    return true;
                case R.id.navigation_mail:
//                    mTextMessage.setText(R.string.title_home);
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "helpdesk@feminastudio.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Thank You");
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);

                    CustomDialogClass cdd = new CustomDialogClass(MainActivity.this);
                    cdd.show();

                    navigation.setSelectedItemId(R.id.navigation_home);


//                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                    startActivityForResult(intent, 1);
                    return true;
                case R.id.navigation_share:

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareSubText = "Be Beautiful with Femina";
                    String shareBodyText = "https://play.google.com/store/apps/details?id=com.vmmor.femina";
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubText);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                    startActivity(Intent.createChooser(shareIntent, "Share With"));


                    return true;
                case R.id.navigation_notifications:
                    Intent i = new Intent();
                    String url = null;
                    try {
                        url = "https://api.whatsapp.com/send?phone=" + "918750420053" + "&text=" + URLEncoder.encode("I want to knw about Femina !!", "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    startActivity(i);

                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.webview);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);


        p = new ProgressDialog(MainActivity.this);
        p.setMessage("loading");
        p.show();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                p.dismiss();
                handler.postDelayed(this, 2000);
            }
        };

        handler.postDelayed(runnable, 2000);

        web();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webview.canGoBack()) {
                webview.goBack();


            } else {
                super.onBackPressed();
                handler.removeCallbacksAndMessages(null);
            }
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_POWER)) {

            Log.d("power", "power key pressed");

        }
        return super.onKeyDown(keyCode, event);
    }

    public void web() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(true);
        }
        webSettings.setAllowContentAccess(true);
        webview.reload();
        webview.setAccessibilityDelegate(new View.AccessibilityDelegate());
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webview.setWebViewClient(webViewClient);
        webview.loadUrl(url);
    }

    public class WebViewClientImpl extends WebViewClient {

        private Activity activity = null;

        public WebViewClientImpl(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.indexOf("http://feminastudio.com/") > -1)
                return false;

            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
            return true;
        }

    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (1):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // TODO Fetch other Contact details as you want to use

                    }
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_home);
    }


}
