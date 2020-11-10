package pl.pwr.andz1.i00000.bmi

import android.icu.text.DecimalFormat
import java.io.Serializable
import java.util.*

class BmiResultObject (
        private val result : Double,
        private val height : Int,
        private val weight : Int,
        private val unit : String,
        private val date: Date
) : Serializable{
    override fun toString(): String {
        return java.lang.String.format("%3.2f  %3d        %3d      %7s \t \t \t %8s\n", result, height, weight, unit, date.toString())
    }
}