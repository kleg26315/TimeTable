package com.example.timetabletest.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.timetabletest.Model.LectureData;
import com.example.timetabletest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends BaseAdapter{

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<LectureData> sample = null;
    private List<LectureData> listitem = null;
    private List<LectureData> displaylistitem = null;
    private ArrayList<LectureData> arrayList;


    public SearchAdapter(Context context, ArrayList<LectureData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<LectureData>();
        this.arrayList.addAll(sample);
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
    public LectureData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.lecture_list, null);

        TextView lecture = (TextView)view.findViewById(R.id.textLecture);
        TextView strTime = (TextView)view.findViewById(R.id.textStrtime);
        TextView endTime = (TextView)view.findViewById(R.id.textEndtime);
        TextView dayofweek = (TextView)view.findViewById(R.id.textDayofweek);
        TextView code = (TextView)view.findViewById(R.id.textCode);
        TextView professor = (TextView)view.findViewById(R.id.textProfessor);
        TextView location = (TextView)view.findViewById(R.id.textLocation);

        lecture.setText(sample.get(position).getLecture());
        strTime.setText(sample.get(position).getStrTime());
        endTime.setText(sample.get(position).getEndTime());
        dayofweek.setText(sample.get(position).getDayofweek());
        code.setText(sample.get(position).getCode());
        professor.setText(sample.get(position).getProfessor());
        location.setText(sample.get(position).getLocation());

        return view;
    }

    public void filter(String searchText){
        sample.clear();;
        if(searchText.length() == 0)
        {
            sample.addAll(arrayList);
        }
        else {
            for (LectureData lectureData : arrayList)
            {
                if (lectureData.getLecture().toLowerCase(Locale.getDefault()).contains(searchText))
                {
                    sample.add(lectureData);
                }
            }
        }
        notifyDataSetChanged();

    }
}