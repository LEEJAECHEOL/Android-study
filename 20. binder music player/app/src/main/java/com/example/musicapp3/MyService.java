package com.example.musicapp3;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private MediaPlayer mp;
    private final IBinder mBinder = new LocalBinder();

    class LocalBinder extends Binder{
        MyService getMyService(){
            return MyService.this;
        }
    }

    public MediaPlayer getMp(){
        return mp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sample1);
    }

    public MyService() { }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}