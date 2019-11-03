package com.example.timetabletest.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timetabletest.Model.MemoData;
import com.example.timetabletest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MemoAdapter extends BaseAdapter{
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MemoData> sample = null;
    private List<MemoData> listitem = null;
    private List<MemoData> displaylistitem = null;
    private ArrayList<MemoData> arrayList;


    public MemoAdapter(Context context, ArrayList<MemoData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MemoData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(final int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.memo_list, null);

        TextView textmemo = (TextView)view.findViewById(R.id.textmemo);
        ImageButton trash_btn = (ImageButton)view.findViewById(R.id.trash);

        textmemo.setText(sample.get(position).getTitle());
        final String text = sample.get(position).getTitle();
        trash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new Task_memo_delete().execute(sample.get(position).getLecture_code()).get();
                } catch (InterruptedException e){
                    e.printStackTrace();
                } catch (ExecutionException e){
                    e.printStackTrace();
                }
                Toast.makeText(mContext, text + "삭제 완료",Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}