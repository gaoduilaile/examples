package com.bdca.visionscene.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anyu.armmodule.weight.SpaceItemDecoration;
import com.bdca.visionscene.Adapter.PostureHomeAdapter;
import com.bdca.visionscene.BaseFragment;
import com.bdca.visionscene.R;
import com.bdca.visionscene.presenter.FragmentPosturePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentPosture extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.left_image)
    ImageView leftImage;
    @BindView(R.id.middle_text)
    TextView middleText;
    @BindView(R.id.right_image)
    ImageView rightImage;
    @BindView(R.id.title_bar_id)
    RelativeLayout titleBarId;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private String mParam1;
    private String mParam2;

    FragmentPosturePresenter presenter;

    private List<Integer> mList = new ArrayList<>();

    public static FragmentPosture newInstance() {
        return new FragmentPosture();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posture, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mList.clear();
        mList.add(R.mipmap.posture_home1_bg);

        middleText.setText("智能检测&识别");
        PostureHomeAdapter sportAdapter = new PostureHomeAdapter(mActivity, mList, new PostureHomeAdapter.MyOrderAdapterFunc() {
            @Override
            public void itemClick(int position) {
                replaceFragment(FragmentPostureList.newInstance(), R.id.fragmentLayout_container);
            }
        });
        LinearLayoutManager layoutManage = new LinearLayoutManager(mActivity);
        layoutManage.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new SpaceItemDecoration(20));
        recyclerView.setLayoutManager(layoutManage);
        recyclerView.setAdapter(sportAdapter);

//        middleText.setText();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
