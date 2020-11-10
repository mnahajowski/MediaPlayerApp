package pl.pwr.andz1.i00000.bmi

class BmiForInchLb(
        private val mass: Double,
        private val height: Double
) : Bmi{

    override fun count(): Double =
            703 * mass / (height * height)
}