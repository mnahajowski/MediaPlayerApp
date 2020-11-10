package pl.pwr.andz1.i00000

import org.junit.Test

import org.junit.Assert.*
import pl.pwr.andz1.i00000.bmi.BmiForCmKg
import pl.pwr.andz1.i00000.bmi.BmiForInchLb

class ExampleUnitTest {

    @Test
    fun bmiCmKgCount_isCorrect() {
        val bmi = BmiForCmKg(80.0, 180.0)
        assertEquals(24.69, bmi.count(), 0.01)
    }

    @Test
    fun bmiInchLbCount_isCorrect() {
        val bmi = BmiForInchLb(140.0, 70.0)
        assertEquals(20.09, bmi.count(), 0.01)
    }


}