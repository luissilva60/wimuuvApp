package com.example.wimuuvapplication.Login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.FeedDetails;
import com.example.wimuuvapplication.UI.FeedFragment;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<FeedDetails> {


    public ListAdapter(@NonNull Context context, ArrayList<FeedDetails> feedDetails) {
        super(context, R.layout.activity_feed_details,feedDetails);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        FeedDetails feedDetails = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_feed_details,parent,false);
        }

        TextView name = convertView.findViewById(R.id.textViewEventName);
        TextView description = convertView.findViewById(R.id.textViewEventDescription);
        TextView spot = convertView.findViewById(R.id.textViewEventSpot);
        TextView org = convertView.findViewById(R.id.textViewEventOrg);
        TextView starttime = convertView.findViewById(R.id.textViewEventStarttime);
        TextView endtime = convertView.findViewById(R.id.textViewEventEndtime);
        TextView date = convertView.findViewById(R.id.textViewEventDate);
        TextView state = convertView.findViewById(R.id.textViewEventState);
        TextView type = convertView.findViewById(R.id.textViewEventType);

        return super.getView(position, convertView, parent);
    }
}
