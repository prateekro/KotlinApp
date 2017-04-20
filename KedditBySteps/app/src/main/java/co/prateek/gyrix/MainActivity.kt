package co.prateek.gyrix

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.widget.Toast
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.KeyEvent.KEYCODE_BACK




class MainActivity : AppCompatActivity() {

    //var mywebview : WebView = null  //as WebView
    var mywebview : WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()

        mywebview = findViewById(R.id.webViewGyrix) as WebView

        (mywebview as WebView).setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url);
                return true
            }
        })

        (mywebview as WebView).loadUrl("http://www.gyrix.com.au/")
        (mywebview as WebView).settings.displayZoomControls.and(true)
        Toast.makeText(this, "Loading Completed", Toast.LENGTH_SHORT).show()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && (mywebview as WebView).canGoBack()) {
            (mywebview as WebView).goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "Called for background", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "Back into Foreground", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Activity Closed on Destruction", Toast.LENGTH_SHORT).show()
    }

}
