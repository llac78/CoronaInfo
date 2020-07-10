package projeto.coronainfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SymptomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.titleSymptoms)
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
