package com.bdca.visionscene;

import android.os.Bundle;

import com.bdca.visionscene.fragment.FragmentPosture;


public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第一个fragment
        addFragment(FragmentPosture.newInstance(), R.id.fragmentLayout_container);
    }

}
