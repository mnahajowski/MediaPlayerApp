package pl.pwr.andz1.i00000

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pl.pwr.andz1.i00000.bmi.BmiForCmKg
import pl.pwr.andz1.i00000.bmi.BmiForInchLb
import pl.pwr.andz1.i00000.bmi.BmiHistory
import pl.pwr.andz1.i00000.bmi.BmiResultObject
import pl.pwr.andz1.i00000.databinding.ActivityMainBinding

const val RESULTS_SHARED_KEY = "result_history"
const val SHARED_PREFERENCES_STRING = "shared preferences"
const val BMI_SAVED_VALUE = "bmi_value"
const val LAST_10_HISTORY_LIST_SERIALIZABLE_KEY = "mytext"
const val LAST_10_HISTORY_LIST_INTENT_KEY = "Bundle"
const val BMI_VALUE_INTENT_KEY = "bmiValue"

//246711_Marcin_Nahajowski
//Pixel_3a_API_30_x86

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var last10history = BmiHistory()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_STRING, MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(RESULTS_SHARED_KEY, null)

        val myType = object : TypeToken<ArrayList<BmiResultObject>>() {}.type
        val savedHistory = gson.fromJson<ArrayList<BmiResultObject>>(json, myType)

        if(savedHistory == null) {
            last10history = BmiHistory()
        }
        else {
            last10history.set(savedHistory)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(BMI_SAVED_VALUE, binding.bmiTV.text.toString().toDouble())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.bmiTV.text = savedInstanceState.getDouble(BMI_SAVED_VALUE).toString()
        showIfCorrect()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.unit_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.polish_units){
            binding.heightTV.text = resources.getString(R.string.polish_height_cm)
            binding.massTV.text = resources.getString(R.string.polish_mass_kg)
        }  else if (item.itemId == R.id.english_units){
            binding.heightTV.text = resources.getString(R.string.english_height_inch)
            binding.massTV.text = resources.getString(R.string.english_mass_pd)
        } else {
            ShowHistory(null)
        }
        binding.heightET.text.clear()
        binding.massET.text.clear()
        binding.bmiTV.visibility = View.INVISIBLE
        return super.onOptionsItemSelected(item)
    }

    fun showIfCorrect() {
        binding.apply {
            if(massET.error == null && heightET.error == null) {
                BmiTextColor()
                bmiTV.visibility = View.VISIBLE
                var unit =""
                if(massTV.text == resources.getString(R.string.polish_mass_kg))
                    unit = getString(R.string.polish_units_string)
                else
                    unit = getString(R.string.english_units_string)
                last10history.add(BmiResultObject(bmiTV.text.toString().toDouble(), heightET.text.toString().toInt(),
                        massET.text.toString().toInt(), unit, Calendar.getInstance().time))
            } else {
                bmiTV.visibility = View.INVISIBLE
            }
        }
    }

    fun checkPolishValues() {
        binding.apply {
            if (massET.text.toString().toDouble() < 20 || massET.text.toString().toDouble() > 300) {
                massET.error = getString(R.string.weight_is_invalid)
            }
            if (heightET.text.toString().toDouble() < 100 || heightET.text.toString().toDouble() > 250) {
                heightET.error = getString(R.string.height_is_invalid)
            }
            bmiTV.text = String.format("%.2f", BmiForCmKg(massET.text.toString().toDouble(), heightET.text.toString().toDouble()).count())
        }
    }

    fun checkEnglishValues() {
        binding.apply {
            if (massET.text.toString().toDouble() < 44 || heightET.text.toString().toDouble() > 660) {
                massET.error = getString(R.string.weight_is_invalid)
            } else if (heightET.text.toString().toDouble() < 40 || heightET.text.toString().toDouble() > 98) {
                heightET.error = getString(R.string.height_is_invalid)
            }
            bmiTV.text = String.format("%.2f", BmiForInchLb(massET.text.toString().toDouble(), heightET.text.toString().toDouble()).count())
        }
    }


    fun count(view: View) {
        binding.apply {
            if(massET.text.isBlank() || heightET.text.isBlank()) {
                if(massET.text.isBlank())
                    massET.error = getString(R.string.height_is_empty)
                if(heightET.text.isBlank())
                    heightET.error = getString(R.string.height_is_empty)
            }
            else {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

                if(massTV.text == resources.getString(R.string.polish_mass_kg))
                    checkPolishValues()
                else
                    checkEnglishValues()

                showIfCorrect()
            }
        }
    }

    @SuppressLint("ResourceType")
    fun BmiTextColor() {
        val value = binding.bmiTV.text.toString().toDouble()
        when {
            value < 16 -> binding.bmiTV.setTextColor(Color.parseColor(resources.getString(R.color.deep_blue)))
            value < 17 -> binding.bmiTV.setTextColor(Color.parseColor(resources.getString(R.color.light_blue)))
            value < 18.5 -> binding.bmiTV.setTextColor(Color.parseColor(resources.getString(R.color.light_green)))
            value < 24 -> binding.bmiTV.setTextColor(Color.parseColor(resources.getString(R.color.green)))
            value < 30 -> binding.bmiTV.setTextColor(Color.parseColor(resources.getString(R.color.green_yellow)))
            value < 35 -> binding.bmiTV.setTextColor(Color.parseColor(resources.getString(R.color.yellow)))
            value < 40 -> binding.bmiTV.setTextColor(Color.parseColor(resources.getString(R.color.orange)))
            else -> binding.bmiTV.setTextColor(Color.parseColor(resources.getString(R.color.dark_red)))
        }

    }
    fun PassBmiValueToNextScreen(v: View?) {

        val intent = Intent(this, BmiResultAcitivity::class.java)
        intent.putExtra(BMI_VALUE_INTENT_KEY, binding.bmiTV.text.toString().toDouble())
        startActivityForResult(intent, 1)
    }

    fun ShowHistory(v: View?) {
        val intent = Intent(this, HistoryActivity::class.java)
        val args = Bundle()
        args.putSerializable(LAST_10_HISTORY_LIST_SERIALIZABLE_KEY, last10history)
        intent.putExtra(LAST_10_HISTORY_LIST_INTENT_KEY, args)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_STRING, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(last10history.get())
        editor.putString(RESULTS_SHARED_KEY, json)
        editor.apply()
    }



}