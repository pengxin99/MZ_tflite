package com.example.android.tflitecamerademo;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Volume {


    private static SoundPool mSoundPool;
    private static final int DEFAULT_INVALID_SOUND_ID = -Integer.MAX_VALUE;
    private static final int DEFAULT_INVALID_STREAM_ID = -Integer.MAX_VALUE;

    private static int mSoundId = DEFAULT_INVALID_SOUND_ID;
    private static int mStreamID = DEFAULT_INVALID_STREAM_ID;


    private AudioManager mAudioManager;


    /**
     * 创建SoundPool
     */
    public static void createSoundPoolIfNeeded() {
        if (mSoundPool == null) {
            // 5.0 及 之后
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = null;
                audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();

                mSoundPool = new SoundPool.Builder()
                        .setMaxStreams(16)
                        .setAudioAttributes(audioAttributes)
                        .build();
            } else { // 5.0 以前
                mSoundPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);  // 创建SoundPool
            }

            mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                    Toast.makeText("","加特技准备完毕~",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void paly(String volume_path){
        createSoundPoolIfNeeded();
        mSoundId = mSoundPool.load(volume_path, 1);
        mSoundPool.play(mSoundId, 1, 1, 0, 0, 1);
    }

}
