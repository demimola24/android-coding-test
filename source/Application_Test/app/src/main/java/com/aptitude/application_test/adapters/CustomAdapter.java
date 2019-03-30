package com.aptitude.application_test.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aptitude.application_test.R;
import com.aptitude.application_test.models.DataModel;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<DataModel> dataList;
    private Context context;

    public CustomAdapter(Context context,List<DataModel> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView authorName;
        TextView commitLink;
        TextView commitMessage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            authorName = mView.findViewById(R.id.authorName);
            commitLink = mView.findViewById(R.id.commitLink);
            commitMessage = mView.findViewById(R.id.commitMessage);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_cell, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.authorName.setText(dataList.get(position).getCommmit().getAuthor().getName());
        holder.commitLink.setText(dataList.get(position).getCommmit().getCommitUrl());
        holder.commitMessage.setText(dataList.get(position).getCommmit().getCommitMessage());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}