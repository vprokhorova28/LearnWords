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
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        holder.termTextWatcher.updatePosition(holder.getAbsoluteAdapterPosition());
        holder.descTextWatcher.updatePosition(holder.getAbsoluteAdapterPosition());

        holder.term.setText(terms.get(holder.getAbsoluteAdapterPosition()));
        holder.desc.setText(descs.get(holder.getAbsoluteAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public class TermViewHolder extends RecyclerView.ViewHolder {

        EditText term, desc;
        TermTextWatcher termTextWatcher;
        DescTextWatcher descTextWatcher;

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            this.term = itemView.findViewById(R.id.term_input);
            this.desc = itemView.findViewById(R.id.desc_input);

            termTextWatcher = new TermTextWatcher();
            term.addTextChangedListener(termTextWatcher);

            descTextWatcher = new DescTextWatcher();
            desc.addTextChangedListener(descTextWatcher);
        }
    }

    private class TermTextWatcher implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            terms.set(position, charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }


    private class DescTextWatcher implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            descs.set(position, charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }
}


