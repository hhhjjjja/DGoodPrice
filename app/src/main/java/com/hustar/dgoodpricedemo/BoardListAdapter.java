package com.hustar.dgoodpricedemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private ArrayList<Data> data = null;
    private int listCnt = 0;

    public BoardListAdapter(ArrayList<Data> data) {
        this.data = data;
        this.listCnt = this.data.size();
    }

    @Override
    public int getCount() {
        return listCnt;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            final Context context = viewGroup.getContext();
            if(inflater == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            view = inflater.inflate(R.layout.boardlist_item, viewGroup, false);
        }

        TextView titleTextview = (TextView) view.findViewById(R.id.titleTextview);
        TextView nameTextview = (TextView) view.findViewById(R.id.nameTextview);
        TextView dateTextview = (TextView) view.findViewById(R.id.dateTextview);

        titleTextview.setText(data.get(i).getTitle());
        nameTextview.setText(data.get(i).getName());
        dateTextview.setText(data.get(i).getDate());

        return view;
    }
}
