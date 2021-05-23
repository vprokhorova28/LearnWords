package com.example.learnwords;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private Context context;
    private ArrayList<String> terms, descs;


    TermAdapter(Context context){
        this.context = context;
        this.terms = new ArrayList<>();
        this.descs = new ArrayList<>();
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.add_term_view, parent, false);
        return new TermViewHolder(view);
    }

    public ArrayList<String> getTerms() {
        return terms;
    }

    public ArrayList<String> getDescs() {
        return descs;
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        holder.term.setText(terms.get(position));
        holder.desc.setText(descs.get(position));

        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public class TermViewHolder extends RecyclerView.ViewHolder {

        EditText term, desc;
        TextWatcher termTextWatcher;
        TextWatcher descTextWatcher;
        int pos = -1;

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            term = itemView.findViewById(R.id.term_input);
            desc = itemView.findViewById(R.id.desc_input);

            descTextWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (pos != -1){
                        terms.set(pos, s.toString());
                    }
                }
            };
            if (termTextWatcher != null){
                term.addTextChangedListener(termTextWatcher);
            }


            descTextWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (pos != -1){
                        descs.set(pos, s.toString());
                    }
                }
            };
            if (descTextWatcher != null){
                desc.addTextChangedListener(descTextWatcher);
            }
        }
    }
}
