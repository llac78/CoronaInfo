package projeto.coronainfo

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_numbers.*


class NumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.titleNumbers)
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        pbNumbers.visibility = View.INVISIBLE


        wvNumbers.loadUrl("https://covid19.who.int/")
        wvNumbers.settings.javaScriptEnabled = true

        wvNumbers.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(
                view: WebView,
                url: String,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)
                pbNumbers.visibility = View.VISIBLE
            }
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                pbNumbers.visibility = View.INVISIBLE
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
