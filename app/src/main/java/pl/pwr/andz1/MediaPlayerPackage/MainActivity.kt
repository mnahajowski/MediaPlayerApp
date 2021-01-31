package pl.pwr.andz1.MediaPlayerPackage

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.admin.DevicePolicyManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pl.pwr.andz1.MediaPlayerPackage.databinding.ActivityMainBinding


//246711_Marcin_Nahajowski
//Pixel_3a_API_30_x86

class MainActivity : AppCompatActivity(), IPlayable {

    lateinit var binding: ActivityMainBinding

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var notificationManager: NotificationManager
    private lateinit var tracks : ArrayList<Int>
    private lateinit var tracksNames : ArrayList<String>
    var position = 0
    var isPlaying = false


    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            var action = intent?.getStringExtra("actionname")

            when(action) {
                CreateNotification.ACTION_PREVIOUS -> {
                    goToPrevious()
                }
                CreateNotification.ACTION_PLAY -> {
                    if(isPlaying) {
                        mediaPlayer.pause()
                        onTrackPause()
                    } else {
                        onTrackPlay()
                        mediaPlayer.start()
                    }
                }
                CreateNotification.ACTION_NEXT -> {
                    goToNext()
                    startPlayMusic()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        populate_tracks()
        mediaPlayer = MediaPlayer.create(applicationContext,tracks[0])
        findViewById<TextView>(R.id.music_title).text = tracksNames[position]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
            registerReceiver(broadcastReceiver, IntentFilter("TRACKS_TRACKS"))
            startService(Intent(baseContext, DevicePolicyManager.OnClearApplicationUserDataListener::class.java))
        }


    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = NotificationChannel(CreateNotification.CHANNEL_ID, "Channel_name",
                NotificationManager.IMPORTANCE_LOW)
            notificationManager = getSystemService(NotificationManager::class.java)

            if(notificationManager != null) {
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun startPlayMusic() {
        if(isPlaying) {
            onTrackPause()
            mediaPlayer.pause()
        } else {
            onTrackPlay()
            mediaPlayer.start()
        }
    }
    fun playMusic(view: View) {
        startPlayMusic()
    }

    fun stopMusic(view: View) {
        mediaPlayer.seekTo(0);
        mediaPlayer.pause()
        onTrackPause()
    }

    fun goToNext() {
        if(mediaPlayer != null && isPlaying) {
            onTrackPause()
            mediaPlayer.pause()
            mediaPlayer.reset()
        }

        if(position < tracks.size - 1)
            position = position + 1;
        else
            position = 0

        mediaPlayer = MediaPlayer.create(applicationContext,tracks[position])
        onTrackNext()

    }

    fun goNext(view: View) {
        goToNext()
        playMusic(view)

        mediaPlayer.setOnCompletionListener {
            if(position < tracks.size - 1)
                position = position + 1;
            else
                position = 0
            if(mediaPlayer != null && isPlaying) {
                mediaPlayer.reset()
            }
            mediaPlayer = MediaPlayer.create(applicationContext,tracks[position])
            mediaPlayer.start()
            onTrackNext()
        }

    }

    fun goPrevious(view: View) {
        goToPrevious()
        playMusic(view)
    }

    fun goToPrevious() {
        if(mediaPlayer != null && isPlaying) {
            onTrackPause()
            mediaPlayer.pause()
            mediaPlayer.reset()
        }

        if(position > 0)
            position--
        else
            position = tracks.size - 1;

        mediaPlayer = MediaPlayer.create(applicationContext,tracks[position])

        onTrackPrevious()
    }

    fun fastForward(view: View) {
        val currentPosition: Int = mediaPlayer.currentPosition
        if (currentPosition + 10000 <= mediaPlayer.duration) {
            mediaPlayer.seekTo(currentPosition + 10000);
        } else {
            mediaPlayer.seekTo(mediaPlayer.duration);
        }
    }

    fun fastRewind(view : View) {
        val currentPosition: Int = mediaPlayer.currentPosition
        if (currentPosition - 10000 >= 0) {
            mediaPlayer.seekTo(currentPosition - 10000);
        } else {
            mediaPlayer.seekTo(0);
        }
    }

    fun populate_tracks() {
        tracks = arrayListOf<Int>(R.raw.song1, R.raw.song2,
            R.raw.song3, R.raw.song4, R.raw.song5)
        tracksNames = arrayListOf(getString(R.string.song1), getString(R.string.song2),
                 getString(R.string.song3), getString(R.string.song4), getString(R.string.song5))
    }



    override fun onTrackPrevious() {
        CreateNotification.create_notification(this, tracks[position], R.drawable.ic_baseline_pause_24,
            position, tracks.size - 1, tracksNames[position])
        findViewById<TextView>(R.id.music_title).text = tracksNames[position]
    }

    override fun onTrackPlay() {
        CreateNotification.create_notification(this, tracks[position], R.drawable.ic_baseline_pause_24,
            position, tracks.size - 1, tracksNames[position])
        findViewById<ImageButton>(R.id.playBtn).setImageResource(R.drawable.ic_baseline_pause_24)
        isPlaying = true
    }

    override fun onTrackPause() {
        CreateNotification.create_notification(this, tracks[position], R.drawable.ic_baseline_play_arrow_24,
            position, tracks.size - 1, tracksNames[position])
        findViewById<ImageButton>(R.id.playBtn).setImageResource(R.drawable.ic_baseline_play_arrow_24)
        isPlaying = false
    }

    override fun onTrackNext() {
        CreateNotification.create_notification(this, tracks[position], R.drawable.ic_baseline_pause_24,
            position, tracks.size - 1, tracksNames[position])
        findViewById<TextView>(R.id.music_title).text = tracksNames[position]
    }
}
