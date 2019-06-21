package com.bdca.visionscene.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdca.visionscene.Adapter.PostureListAdapter;
import com.bdca.visionscene.BaseFragment;
import com.bdca.visionscene.R;
import com.bdca.visionscene.bean.PostureAdapterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentPostureList extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.left_image)
    ImageView leftImage;
    @BindView(R.id.middle_text)
    TextView middleText;
    @BindView(R.id.right_image)
    ImageView rightImage;
    private View view;

    private List<PostureAdapterBean> mList = new ArrayList<>();

    public static FragmentPostureList newInstance() {
        return new FragmentPostureList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_posture_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        mList.clear();
        mList.add(new PostureAdapterBean("五秒下蹲",R.mipmap.img_3));
//        mList.add(new PostureAdapterBean("站姿十字夹背",R.mipmap.img_1));
//        mList.add(new PostureAdapterBean("碗形静力",R.mipmap.img_2));
//        mList.add(new PostureAdapterBean("跳箱",R.mipmap.img_4));
        leftImage.setVisibility(View.VISIBLE);
        middleText.setText("动作列表");
        PostureListAdapter sportAdapter = new PostureListAdapter(mActivity, mList, new PostureListAdapter.MyOrderAdapterFunc() {
            @Override
            public void itemClick(int position) {
                replaceFragment(FragmentPostureDetail.newInstance(), R.id.fragmentLayout_container);
            }
        });
        LinearLayoutManager layoutManage = new LinearLayoutManager(mActivity);
        layoutManage.setOrientation(LinearLayoutManager.VERTICAL);
        //添加Android自带的分割线
        DividerItemDecoration divider = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(mActivity, R.drawable.custom_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(layoutManage);
        recyclerView.setAdapter(sportAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.left_image)
    public void onViewClicked() {
        popFragment();
    }
}
