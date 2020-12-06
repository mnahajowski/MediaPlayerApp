package pl.pwr.andz1.i00000

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pl.pwr.andz1.i00000.databinding.ActivityMain3Binding

const val BMI_VERY_SEVERELY_UNDERWEIGHT = 16
const val BMI_SEVERELY_UNDERWEIGHT = 17
const val BMI_UNDERWEIGHT = 18.5
const val BMI_NORMAL = 25
const val BMI_OVERWEIGHT = 30
const val BMI_MODERATELY_OBESE = 35
const val BMI_SEVERELY_OBESE = 40

class BmiResultAcitivity : AppCompatActivity() {

    lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val tv = findViewById<TextView>(R.id.myTextView)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        tv.text = intent.getDoubleExtra(BMI_VALUE_INTENT_KEY, 0.00).toString()
        HelloTextSetter()
    }

    fun SetImage(newText: String, imageId: Int) {
        val helloTV = findViewById<TextView>(R.id.helloTV)
        helloTV.text = newText
        findViewById<ImageView>(R.id.thumbImg).setImageResource(imageId)
    }

    fun HelloTextSetter(){
        val tv = findViewById<TextView>(R.id.myTextView)

        when {
            tv.text.toString().toDouble() < BMI_VERY_SEVERELY_UNDERWEIGHT ->
                SetImage(getString(R.string.very_severely_underweight), (R.drawable.sad))

            tv.text.toString().toDouble() < BMI_SEVERELY_UNDERWEIGHT ->
                SetImage(getString(R.string.severely_underweight), (R.drawable.medium))

            tv.text.toString().toDouble() < BMI_UNDERWEIGHT ->
                SetImage(getString(R.string.underweight), (R.drawable.medium))

            tv.text.toString().toDouble() < BMI_NORMAL ->
                SetImage(getString(R.string.normal_weight), (R.drawable.thumb))

            tv.text.toString().toDouble() < BMI_OVERWEIGHT ->
                SetImage(getString(R.string.overweight), (R.drawable.medium))

            tv.text.toString().toDouble() < BMI_MODERATELY_OBESE ->
                SetImage(getString(R.string.moderately_obese), (R.drawable.medium))

            tv.text.toString().toDouble() < BMI_SEVERELY_OBESE ->
                SetImage(getString(R.string.severely_obese), (R.drawable.medium))
            else ->
                SetImage(getString(R.string.very_severely_obese), (R.drawable.sad))
        }
    }
}