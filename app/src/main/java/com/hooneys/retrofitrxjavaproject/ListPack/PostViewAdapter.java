package com.hooneys.retrofitrxjavaproject.ListPack;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hooneys.retrofitrxjavaproject.DO.Post;
import com.hooneys.retrofitrxjavaproject.R;

import java.util.ArrayList;
import java.util.List;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.PostViewHolder> {
    private List<Post> list;

    public PostViewAdapter() {
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_layout, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int i) {
        Post item = list.get(i);
        Log.i("Post List", item.getTitle()+" / "+item.getUserId()+" / "+item.getBody());
        holder.txt_title.setText(item.getTitle());
        holder.txt_content.setText(item.getBody());
        holder.txt_author.setText(item.getUserId()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Post> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView txt_title, txt_content, txt_author;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_content = itemView.findViewById(R.id.txt_content);
            txt_author = itemView.findViewById(R.id.txt_author);
        }
    }
}
