package projeto.coronainfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PreventionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevention)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.titlePrevention)
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
