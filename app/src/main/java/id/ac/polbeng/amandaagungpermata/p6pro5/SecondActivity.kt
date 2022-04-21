package id.ac.polbeng.amandaagungpermata.p6pro5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bundle = intent.getBundleExtra("main_activity_data")

        val weight = bundle?.getFloat("weight")
        val height = bundle?.getFloat("height")

        tvBmi1.text = weight.toString()
        tvBmi2.text = height.toString()

        btnCalculate.setOnClickListener {
            val mIntent = Intent()
            val mBmi = 703 * (weight!! / (height!! * height!!))
            mIntent.putExtra("second_activity_data", mBmi)
            setResult(Activity.RESULT_OK, mIntent)
            finish()
        }
    }
}