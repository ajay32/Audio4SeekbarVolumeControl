package com.hackingbuzz.audio4seekbarvolumecontrol;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    Button play, pause;
    MediaPlayer musicPlayer;
    SeekBar volumeControlling;

    AudioManager volumeManager; // Manager are those who control the hardware thing or hardware build in thing
    // volume is a part of phone hardware so we need audio manager to manage ,access or use it.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play  = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);

        musicPlayer = MediaPlayer.create(this,R.raw.enkore );


        volumeControlling = (SeekBar) findViewById(R.id.seekBar);

        volumeManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); // the way we initilize the managers..


        // get the max volume of our phone at this particular moment ..suppose the max volume is 10 of ur phone ..n u set the
        //max value of ur phone hardware 5 (controlling by side buttons) ..so seekbar max volume index is 5...
        //so all its depend on your if your hardware voume is at top ..than seekbar voume is gonna be same..if it is 5
        // then seekbar max volume is 5

        int maxVolume = volumeManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // STREAM_MUSIC is like hardware as GPS // we are getting the maximum volume of
        // our phone with the help of  audio manager...we don't need min volume because its zero as we know...
        // we taking it so we can make our seekbar has the maximum voulme as our phone does.

        int currentVolume = volumeManager.getStreamVolume(AudioManager.STREAM_MUSIC); // current volume we need because when we
        // move seekbar it progress n that is our current volume of music we set...so we r gonna set this current volume as progess for seekbar

      volumeControlling.setMax(maxVolume);
        volumeControlling.setProgress(currentVolume);



        volumeControlling.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // the change that is happening here will set to seekbar coz tis method is fire up to change seekbar changes ofcourse
                volumeManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0); // audio manage manages the volume
                // so here also it will set the volume . third paramether flag..we set it zero we dont want additional info
                //means this will update the seekbar  // progrss depends on maxVolume we set (index)
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void play(View view) {

        musicPlayer.start();
    }
    public void pause(View view) {

        musicPlayer.pause();
    }
}
