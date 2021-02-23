package com.example.musicapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private ImageView ivButton;
    private TextView tvTime;
    private SeekBar sbBar;

    private MyService myService;
    private MediaPlayer mp;

    // 서로 다른 쓰레드간의 통신을 위한 장치로 쓰임
    private Thread uiThred;
    Handler handler = new Handler();
    private int isPlaying = -1;
    private boolean threadStatus = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.LocalBinder)service).getMyService();
            mp = myService.getMp();
            seekBarInit();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mp.stop();
            mp.release();
        }
    };

    public void seekBarInit(){
//        Log.d(TAG, "seekBarInit: " + mp.getDuration());
        sbBar.setMax(mp.getDuration());
        sbBar.setProgress(0);
    }

    public void musicStart(){
        mp.start();
        ivButton.setImageResource(R.drawable.ic_pause);
    }
    public void musicPause(){
        mp.pause();
        ivButton.setImageResource(R.drawable.ic_play);

    }
    public void musicStop(){
        mp.seekTo(0);
        mp.pause();
        sbBar.setProgress(0);
        ivButton.setImageResource(R.drawable.ic_play);
        isPlaying = -1;
        threadStatus = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Intent musicIntent = new Intent(MainActivity.this, MyService.class);
        bindService(musicIntent, connection, BIND_AUTO_CREATE);

        ivButton.setOnClickListener(v -> {
            isPlaying *= -1;
            if(isPlaying == 1){
                musicStart();
            }else{
                musicPause();
            }
            uiThred = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (isPlaying == 1){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sbBar.setProgress(mp.getCurrentPosition());

                                if (mp.getCurrentPosition() >= mp.getDuration() - 8) {
                                    musicStop();
                                }
                            }
                        });
                        try {
                            Thread.sleep(1000);
                            if(threadStatus){
                                uiThred.interrupt(); //  잠깐쉬고있어야지 인터럽트가 걸림 : 멈추지않으면 안걸림.
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        // post()Runnable 객체를 전달.
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                sbBar.setProgress(mp.getCurrentPosition());
//                                Log.d(TAG, "run: getCurrentPosition : " + mp.getCurrentPosition());
//                                Log.d(TAG, "run: getDuration : " + mp.getDuration());
//                                if (mp.getCurrentPosition() >= mp.getDuration() - 8) {
////                                    Log.d(TAG, "run: 2");
//                                    musicStop();
//                                }
//                            }
//                        });
//                        try {
//                            Thread.sleep(1000);
//                            if(threadStatus){
////                                Log.d(TAG, "run: Thread interrupt");
//                                uiThred.interrupt();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            });
            uiThred.start();
        });



        sbBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    seekBar.setProgress(progress);
                    mp.seekTo(progress);
                }
                int min = progress / 60000;
                int sec = (progress % 60000) / 1000;

                String strTime = String.format("%02d:%02d", min, sec);
                tvTime.setText(strTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }

    private void init(){
        ivButton = findViewById(R.id.btn_play_stop);
        tvTime = findViewById(R.id.tv_time);
        sbBar = findViewById(R.id.seekBar);
    }
}