package com.example.learnwords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private Context context;
    private ArrayList id, term, desc;

    TermAdapter(Context context, ArrayList id, ArrayList term, ArrayList desc){
        this.context = context;
        this.id = id;
        this.term = term;
        this.desc = desc;
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.add_term_view, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        EditText term = holder.term;
        EditText desc = holder.desc;
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class TermViewHolder extends RecyclerView.ViewHolder {

        EditText term, desc;

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            term = itemView.findViewById(R.id.term_input);
            desc = itemView.findViewById(R.id.desc_input);
        }
    }
}
