package pl.pwr.andz1.i00000

interface IPlayable {
    fun onTrackPrevious() : Unit
    fun onTrackPlay() : Unit
    fun onTrackPause() : Unit
    fun onTrackNext() : Unit
}