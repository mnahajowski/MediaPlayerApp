package pl.pwr.andz1.MediaPlayerPackage

interface IPlayable {
    fun onTrackPrevious() : Unit
    fun onTrackPlay() : Unit
    fun onTrackPause() : Unit
    fun onTrackNext() : Unit
}