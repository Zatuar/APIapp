package com.example.apiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    private ClickItemListenner listener;
    private List<Tasks> mItems;
    private final Context context;
    private PGDnote pgd;

    MyAdapter(Context ctx, List<Tasks> l,PGDnote pgd) {
        this.mItems = l;
        this.context = ctx;
        this.pgd = pgd;
    }
    public void setItem(ArrayList<Tasks> notes, ClickItemListenner listener) {
        this.mItems.addAll(notes);
        this.listener = listener;
    }
    static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        SwitchCompat done;
        ImageButton delete;
        Holder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            done = itemView.findViewById(R.id.done);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_recyclerview, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Tasks key = mItems.get(position);
        holder.title.setText(key.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(key));
        holder.done.setChecked(key.isCompleted());
        holder.done.setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("completed",holder.done.isChecked());
                    pgd.update(key.getKey(), hashMap).addOnSuccessListener(suc ->
                            Toast.makeText(context,
                                    "Done",Toast.LENGTH_SHORT).show()
                    ).addOnFailureListener( er ->
                            Toast.makeText(context,
                                    ""+er.getMessage(),Toast.LENGTH_SHORT).show()
                    );
                }
            }
        );
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgd.delete(key.getKey()).addOnSuccessListener(suc ->
                        Toast.makeText(context,
                                "Note Deleted",Toast.LENGTH_SHORT).show()
                ).addOnFailureListener( er ->
                        Toast.makeText(context,
                                ""+er.getMessage(),Toast.LENGTH_SHORT).show()
                );
            }
        });
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

