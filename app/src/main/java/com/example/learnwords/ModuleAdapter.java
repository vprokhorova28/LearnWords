package com.example.learnwords;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> modules;

    ModuleAdapter(Context context, ArrayList<String> modules){
        this.context = context;
        this.modules = modules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.module_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        DatabaseHelper db = new DatabaseHelper(context);

        holder.moduleTitle.setText((modules.get(position)));
        ///holder.termNumber.setText(db.countModuleData(modules.get(position)).getCount());
        holder.moduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LearnActivity.class);
                intent.putExtra("moduleName", modules.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView moduleTitle, termNumber;
        ConstraintLayout moduleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleTitle = itemView.findViewById(R.id.moduleTitle);
            termNumber = itemView.findViewById(R.id.termNumber);
            moduleView = itemView.findViewById(R.id.module);
        }
    }

}
