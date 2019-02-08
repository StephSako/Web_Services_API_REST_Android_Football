package com.example.footballapi.recyclerview.squad;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footballapi.R;

import java.util.List;

public class AdapterRV_Squad extends RecyclerView.Adapter<AdapterRV_Squad.ViewHolder> {

    private List<SquadModel> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvPlayerName;
        public TextView tvShirtNumber;
        public TextView tvNationality;
        public TextView tvPosition;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            tvPlayerName = v.findViewById(R.id.tvPlayerName);
            tvShirtNumber = v.findViewById(R.id.tvShirtNumber);
            tvNationality = v.findViewById(R.id.tvNationality);
            tvPosition = v.findViewById(R.id.tvPosition);
        }
    }

    public void add(int position, SquadModel item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRV_Squad(List<SquadModel> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterRV_Squad.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_squad, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvPosition.setText(values.get(position).getPlayerPosition());
        holder.tvPlayerName.setText(values.get(position).getPlayerName());
        holder.tvNationality.setText(values.get(position).getPlayerNationality());
        holder.tvShirtNumber.setText(values.get(position).getPlayerShirtNumber());

        holder.tvPlayerName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), PlayerActivity.class);
//                intent.putExtra(CLE_DONNEES_ID_PLAYER, (int) id);
//                intent.putExtra(CLE_DONNEES_NOM_CLUB, ((TeamActivity) Objects.requireNonNull(getActivity())).getnomClub());
//                intent.putExtra(CLE_DONNEES_CRUST_URL, ((TeamActivity) Objects.requireNonNull(getActivity())).getcrestURLPlayer());
//                startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}