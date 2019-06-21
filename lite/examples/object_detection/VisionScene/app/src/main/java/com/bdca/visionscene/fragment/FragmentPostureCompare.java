package com.bdca.visionscene.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anyu.armmodule.mvp.IPresenter;
import com.bdca.visionscene.BaseFragment;
import com.bdca.visionscene.R;

public class FragmentPostureCompare extends BaseFragment {


    private View view;

    public static FragmentPostureCompare newInstance(){
        return new FragmentPostureCompare();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_posture_compare, container, false);

        return view;
    }

}
