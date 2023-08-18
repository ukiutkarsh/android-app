package com.example.newtabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerview_adapter_studyplan extends RecyclerView.Adapter<recyclerview_adapter_studyplan.ViewHolder>{

    private ArrayList<String> meventnames = new ArrayList<>();
    private ArrayList<String> mdate = new ArrayList<>();
    private ArrayList<String> mtime = new ArrayList<>();

    private Context mContext;

    public recyclerview_adapter_studyplan(ArrayList<String> meventnames, ArrayList<String> mdate, ArrayList<String> mtime, Context mContext) {
        this.meventnames = meventnames;
        this.mdate = mdate;
        this.mtime = mtime;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studyplan_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.event_name.setText(meventnames.get(position));
        holder.date.setText("Date : " + mdate.get(position));
        holder.time.setText("Time : " + mtime.get(position));
    }

    @Override
    public int getItemCount() {
        return meventnames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView event_name;
        TextView date;
        TextView time;
        RelativeLayout ParentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            event_name = itemView.findViewById(R.id.studyplan_id);
            date = itemView.findViewById(R.id.date_id);
            time = itemView.findViewById(R.id.time_id);
            ParentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
