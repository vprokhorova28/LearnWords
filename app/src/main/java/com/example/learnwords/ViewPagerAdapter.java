package com.example.learnwords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>{

    private List<String> terms;

    ViewPagerAdapter(List<String> terms){
        this.terms = terms;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.term_view, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        holder.termTextView.setText(terms.get(position));
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    static class ViewPagerViewHolder extends RecyclerView.ViewHolder{

        private TextView termTextView;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            termTextView = itemView.findViewById(R.id.term_textView);
        }
    }
}
