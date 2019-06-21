package com.bdca.visionscene;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class ExoplayerrUtils {

    public static SimpleExoPlayer player;

    public static SimpleExoPlayer init(String url, Context context, SimpleExoPlayerView simpleExoPlayerView) {

        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        // 1.创建一个默认TrackSelector
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        // 2.创建一个默认的LoadControl
        LoadControl loadControl = new DefaultLoadControl();

        // 3.创建播放器
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

//        4、创建一个显示视频的控件View
//        SimpleExoPlayerView simpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.simpleExoPlayerView);
//         5、将player关联到View上
        simpleExoPlayerView.setPlayer(player);

        // 5、创建一个DataSource 资源
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "yourApplicationName"), bandwidthMeter);
        //6、创建一个 Extractor 资源
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        //6、创建一个 MediaSource 资源
//        String url = "http://visioncircle.file.alimmdn.com/video/file_8e577efaa0ae4eae8e53938dfb279634?t=1522652671328";
        ExtractorMediaSource videoSource = new ExtractorMediaSource(Uri.parse(url),
                dataSourceFactory, extractorsFactory, null, null);


        // 播放
        player.prepare(videoSource);

//        player.setPlayWhenReady(true);

        //6、讲一个视频资源和字母资源 整合到一起
//        MediaSource subtitleSource = new SingleSampleMediaSource(Uri.parse(url), dataSourceFactory, );
//        MergingMediaSource mergedSource = new MergingMediaSource(videoSource, subtitleSource);

        return player;


    }
}
