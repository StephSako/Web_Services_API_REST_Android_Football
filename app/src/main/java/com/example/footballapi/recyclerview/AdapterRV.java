package com.example.footballapi.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.recyclerview.model.TeamModel;
import com.example.footballapi.view.activities.TeamActivity;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.ViewHolder> {

    private List<TeamModel> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvPosition;
        public TextView tvClubname;
        public TextView tvDiff;
        public TextView tvPoints;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            tvPosition = v.findViewById(R.id.tvPosition);
            tvClubname = v.findViewById(R.id.tvClubname);
            tvDiff = v.findViewById(R.id.tvDiff);
            tvPoints = v.findViewById(R.id.tvPoints);
        }
    }

    public void add(int position, TeamModel item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRV(List<TeamModel> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterRV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_classement, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvPosition.setText(values.get(position).getPosition());
        holder.tvClubname.setText(values.get(position).getName());
        holder.tvDiff.setText(values.get(position).getDiff());
        holder.tvPoints.setText(values.get(position).getPoints());

        holder.tvClubname.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), TeamActivity.class);
//                intent.putExtra(CLE_DONNEES_ID_TEAM, (int) id);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}