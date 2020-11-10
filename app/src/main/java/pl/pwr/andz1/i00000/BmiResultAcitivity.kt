package pl.pwr.andz1.i00000

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import pl.pwr.andz1.i00000.databinding.ActivityMain3Binding
import pl.pwr.andz1.i00000.databinding.ActivityMainBinding

class BmiResultAcitivity : AppCompatActivity() {

    lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val tv = findViewById<TextView>(R.id.myTextView)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        tv.text = intent.getDoubleExtra("bmiValue", 0.00).toString()
        HelloTextSetter()
    }


    fun HelloTextSetter(){
        val helloTV = findViewById<TextView>(R.id.helloTV)
        val tv = findViewById<TextView>(R.id.myTextView)

        when {
            tv.text.toString().toDouble() < getString(R.string.BMI_VERY_SEVERELY_UNDERWEIGHT).toDouble() -> {
                helloTV.text = getString(R.string.very_severely_underweight)
                findViewById<ImageView>(R.id.thumbImg).setImageResource(R.drawable.sad)
            }
            tv.text.toString().toDouble() < getString(R.string.BMI_SEVERELY_UNDERWEIGHT).toDouble() -> {
                helloTV.text = getString(R.string.serverely_underweight)
                findViewById<ImageView>(R.id.thumbImg).setImageResource(R.drawable.medium)
            }
            tv.text.toString().toDouble() < getString(R.string.BMI_UNDERWEIGHT).toDouble() -> {
                helloTV.text = getString(R.string.underweight)
                findViewById<ImageView>(R.id.thumbImg).setImageResource(R.drawable.medium)
            }
            tv.text.toString().toDouble() < getString(R.string.BMI_NORMAL).toDouble() -> {
                helloTV.text = getString(R.string.normal_weight)
                findViewById<ImageView>(R.id.thumbImg).setImageResource(R.drawable.thumb)
            }
            tv.text.toString().toDouble() < getString(R.string.BMI_OVERWEIGHT).toDouble() -> {
                helloTV.text = getString(R.string.overweight)
                findViewById<ImageView>(R.id.thumbImg).setImageResource(R.drawable.medium)
            }
            tv.text.toString().toDouble() < getString(R.string.BMI_MODERATELY_OBESE).toDouble() -> {
                helloTV.text = getString(R.string.moderately_obese)
                findViewById<ImageView>(R.id.thumbImg).setImageResource(R.drawable.medium)
            }
            tv.text.toString().toDouble() < getString(R.string.BMI_SEVERELY_OBESE).toDouble() -> {
                helloTV.text = getString(R.string.severely_obese)
                findViewById<ImageView>(R.id.thumbImg).setImageResource(R.drawable.medium)
            }
            else -> {
                helloTV.text = getString(R.string.very_severely_obese)
                findViewById<ImageView>(R.id.thumbImg).setImageResource(R.drawable.sad)
            }
        }
    }
}