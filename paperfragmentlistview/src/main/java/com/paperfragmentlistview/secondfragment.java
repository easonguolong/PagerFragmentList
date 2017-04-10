package com.paperfragmentlistview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paperfragmentlistview.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/04/07.
 */

public class secondfragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main,container,false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void lazyLoad() {

    }
}
