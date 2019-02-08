package com.example.footballapi.recyclerview.matches;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footballapi.R;

import java.util.List;

public class AdapterRV_Matches extends RecyclerView.Adapter<AdapterRV_Matches.ViewHolder> {

    private List<MatchesModel> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvMatchday;
        public TextView tvHomeTeam;
        public TextView tvScore;
        public TextView tvAwayTeam;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            tvMatchday = v.findViewById(R.id.tvMatchday);
            tvHomeTeam = v.findViewById(R.id.tvHomeTeam);
            tvScore = v.findViewById(R.id.tvScore);
            tvAwayTeam = v.findViewById(R.id.tvAwayTeam);
        }
    }

    public void add(int position, MatchesModel item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRV_Matches(List<MatchesModel> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterRV_Matches.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_matches, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvMatchday.setText(values.get(position).getMatchDay());
        holder.tvAwayTeam.setText(values.get(position).getAwayTeam());
        holder.tvScore.setText(values.get(position).getScore());
        holder.tvHomeTeam.setText(values.get(position).getHomeTeam());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}