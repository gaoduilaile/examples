package com.bdca.visionscene.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bdca.visionscene.BaseFragment;
import com.bdca.visionscene.ExoplayerrUtils;
import com.bdca.visionscene.R;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentPostureDetail extends BaseFragment {

    @BindView(R.id.simpleExoPlayerView)
    SimpleExoPlayerView simpleExoPlayerView;
    Unbinder unbinder;
    @BindView(R.id.left_image)
    ImageView leftImage;
    @BindView(R.id.middle_text)
    TextView middleText;
    @BindView(R.id.right_image)
    ImageView rightImage;
    @BindView(R.id.title_bar_id)
    RelativeLayout titleBarId;
    private View view;

    public static FragmentPostureDetail newInstance() {
        return new FragmentPostureDetail();
    }

    private String url = "http://101.68.81.66:12121/oss/vision-sense/action-detect/bodybuilding/video/coach/29.mp4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_posture_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        leftImage.setVisibility(View.VISIBLE);
        middleText.setText("运动指导");
        ExoplayerrUtils.init(url, mActivity, simpleExoPlayerView);
    }

    @OnClick(R.id.left_image)
    public void onViewClicked() {
        popFragment();
    }
}
