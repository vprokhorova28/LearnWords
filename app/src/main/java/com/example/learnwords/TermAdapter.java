package com.example.learnwords;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private Context context;
    private ArrayList<String> term, desc;
    private int itemCount = 2;


    TermAdapter(Context context){
        this.context = context;
        this.term = new ArrayList<>();
        this.desc = new ArrayList<>();
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.add_term_view, parent, false);
        return new TermViewHolder(view);
    }

    public ArrayList<String> getTerm() {
        return term;
    }

    public ArrayList<String> getDesc() {
        return desc;
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, final int position) {
        holder.term.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                term.add(position, s.toString());
            }
        });
        holder.desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                desc.add(position, s.toString());
            }
        });
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public int getItemCount() {
        return itemCount;
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
