package com.example.internshiptask;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private String[]data;
    private static final String TAG = "ListAdapter";
    public ListAdapter(String[] data)
    {
        this.data=data;
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {
        final String title = data[position];
        holder.name.setText(title);

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"position is "+holder.getAdapterPosition());

                Intent intent = new Intent(view.getContext(),activity_details.class);
                intent.putExtra("value1",holder.name.getText().toString());

                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder
    {
     TextView name;
        public ListViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
        }

    }


}
