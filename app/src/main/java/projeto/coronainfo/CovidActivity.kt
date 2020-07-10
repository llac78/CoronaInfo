package projeto.coronainfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CovidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.titleCovid)
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
