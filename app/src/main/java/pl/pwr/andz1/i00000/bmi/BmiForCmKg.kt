package pl.pwr.andz1.i00000.bmi

class BmiForCmKg (
        private val mass: Double,
        private val height: Double
) : Bmi{

    override fun count(): Double =
            mass / (height * height / 10000)
}