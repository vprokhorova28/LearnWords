package com.example.learnwords;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>{

    private List<String> terms, descs;

    ViewPagerAdapter(List<String> terms,  List<String> descs){
        this.terms = terms;
        this.descs = descs;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.term_view, parent, false);
        return new ViewPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        holder.termTextView.setText(terms.get(position));
        holder.descTextView.setText(descs.get(position));
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    static class ViewPagerViewHolder extends RecyclerView.ViewHolder{

        private TextView termTextView, descTextView;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            termTextView = itemView.findViewById(R.id.term_textView);
            descTextView = itemView.findViewById(R.id.desc_textView);
        }
    }
}
