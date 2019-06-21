package com.anyu.armmodule.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by gaoqiong on 2019/4/4
 */

public class SoundPoolUtils {
    private static SoundPoolUtils instance = null;
    private SoundPool soundPool;
    private HashMap<Integer, Integer> sounddata;
    private boolean isLoaded;
    private Context mContext;

    public static SoundPoolUtils getInstance() {
        synchronized (SoundPoolUtils.class) {
            if (instance == null) {
                instance = new SoundPoolUtils();
            } else {
                return instance;
            }
        }
        return instance;
    }

    private SoundPoolUtils() {
    }

    //初始化声音
    public void InitSound(Context mContext) {
        this.mContext = mContext;
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sounddata = new HashMap<Integer, Integer>();
//        sounddata.put(1, soundPool.load(mContext, R.raw.sound_set_001, 1));
//        sounddata.put(2, soundPool.load(mContext, R.raw.sound_set_002, 1));
//        sounddata.put(3, soundPool.load(mContext, R.raw.sound_set_003, 1));
//        sounddata.put(4, soundPool.load(mContext, R.raw.sound_set_004_success, 1));
//        sounddata.put(5, soundPool.load(mContext, R.raw.sound_set_005_failure, 1));
//        sounddata.put(6, soundPool.load(mContext, R.raw.sound_card_006, 1));
//        sounddata.put(7, soundPool.load(mContext, R.raw.sound_set_weight_failure_007, 1));
//        sounddata.put(8, soundPool.load(mContext, R.raw.sound_upload_008, 1));
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool sound, int sampleId, int status) {
                isLoaded = true;
            }
        });
    }


    //播放声音
    public void playSound(int sound) {

        if (isLoaded) {
            AudioManager am = (AudioManager) mContext
                    .getSystemService(Context.AUDIO_SERVICE);
            float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float volumnCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
            float volumnRatio = volumnCurrent / audioMaxVolumn;

            if (soundPool != null) {
                soundPool.play(sounddata.get(sound),
                        volumnRatio,// 左声道音量
                        volumnRatio,// 右声道音量
                        1, // 优先级
                        0,// 循环播放次数
                        1);// 回放速度，该值在0.5-2.0之间 1为正常速度
            }
        }
    }
}
