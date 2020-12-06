package pl.pwr.andz1.i00000.bmi

import java.io.Serializable
import java.util.*

class BmiResultObject (
    val bmi_result : Double,
    val height : Int,
    val weight : Int,
    val unit : String,
    val date: Date
) : Serializable