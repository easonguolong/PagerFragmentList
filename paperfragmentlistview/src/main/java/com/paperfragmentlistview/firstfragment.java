package com.paperfragmentlistview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paperfragmentlistview.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/04/07.
 */

public class firstfragment extends BaseFragment {
    private List<String> textList;
    private View view ;
    private Myadapter adapter;

    @Bind(R.id.mlistview)
    RefreshListeView mlistview;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.firstfragment_layout, container, false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new Myadapter();
        textList = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            textList.add("这是一条ListView的数据" + i);
        }
        mlistview.setAdapter(adapter);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position="+position);
                Context context = getActivity();
                Intent intent = new Intent(context,secondActivity.class);
                context.startActivity(intent);
            }
        });
        mlistview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                new AsyncTask<Void,Void,Void>(){

                    @Override
                    protected Void doInBackground(Void... params) {
                        SystemClock.sleep(1000);
                        for(int i =0;i<2;i++)
                        {
                            textList.add(0,"下拉数据Eason"+i);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        mlistview.hideHeaderView();
                    }
                }.execute();
            }

            @Override
            public void onLoadingMore() {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        SystemClock.sleep(1000);
                        textList.add("Eason-1");
                        textList.add("Eason-2");
                        textList.add("Eason-3");
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        mlistview.hideFooterView();
                    }
                }.execute();
            }
        });
        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    private class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return textList.size();
        }

        @Override
        public Object getItem(int position) {
            return textList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,parent,false);  //找到adapter布局
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            tv.setText(textList.get(position));
            tv.setTextColor(Color.RED);
            return convertView;
        }
    }

}
