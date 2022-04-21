package id.ac.polbeng.amandaagungpermata.p6pro5

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

val SECOND_ACTIVITY = 1000

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etWeight.hint = "Weight (LBS)"
        etHeight.hint = "Height (Inches)"
        btnCalculate.setOnClickListener {
            val mIntent = Intent(this@MainActivity, SecondActivity::class.java)
            val mBundle = Bundle()
            mBundle.putFloat("weight", etWeight.text.toString().toFloat())
            mBundle.putFloat("height", etHeight.text.toString().toFloat())
            mIntent.putExtra("main_activity_data", mBundle)
            startActivityForResult(mIntent, SECOND_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == SECOND_ACTIVITY) and (resultCode == Activity.RESULT_OK)) {
            val bmi = data!!.getFloatExtra("second_activity_data", 1.0F)
            val bmiString = "%.2f".format(bmi)
            etWeight.setText("")
            etHeight.setText("")
            tvBmi.text = "$bmiString ${getBMIDescription(bmi)}"
        }
    }

    private fun getBMIDescription(bmi: Float): String {
        return when (bmi) {
            in 1.0..18.5 -> "Underweight"
            in 18.6..24.9 -> "Normal Weight"
            in 25.0..29.9 -> "Overweight"
            else -> "Obese"
        }
    }

    override fun onResume() {
        super.onResume()
        clearInputs()
    }

    private fun clearInputs() {
        etWeight.setText("")
        etHeight.setText("")
    }
}