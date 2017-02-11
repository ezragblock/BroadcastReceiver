package com.example.yedid.finalapp.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.example.yedid.finalapp.R;
import com.example.yedid.finalapp.model.entities.Business;

import java.util.ArrayList;
import java.util.List;


public class MyBusinessRecyclerViewAdapter extends RecyclerView.Adapter<MyBusinessRecyclerViewAdapter.ViewHolder> implements Filterable {

    private List<Business> mValues;
    private final List<Business> dValues;
    private final BusinessFragment.OnListFragmentInteractionListener mListener;

    /**
     * cibstructor form the business recycler view adapter
     * @param items
     * @param listener
     */
    public MyBusinessRecyclerViewAdapter(List<Business> items, BusinessFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        dValues = (List<Business>) ((ArrayList<Business>)items).clone();
    }

    /**
     * returns the readied view for the fragment
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_business, parent, false);
        return new ViewHolder(view);
    }

    /**
     * in charge of setting the textviews accordingly
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mAddressView.setText(mValues.get(position).getAddress().toString());
        holder.mBusinessId.setText(String.valueOf(mValues.get(position).getId()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    /**
     * retuns the item count from the list
     * @return
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     *
     * @return the filtered list
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults results = new FilterResults();
                ArrayList<Business> FilteredArray = new ArrayList<Business>();

                // perform your search here using the searchConstraint String.

                if (constraint == null || constraint.length() == 0) {
                    results.count = dValues.size();
                    results.values = dValues;
                }
                else {
                    String cs = constraint.toString().toLowerCase();
                    for (int i = 0; i < dValues.size(); i++) {
                        Business tmp = dValues.get(i);
                        if (tmp.getName().toLowerCase().startsWith(cs))  {
                            FilteredArray.add(tmp);
                        }
                    }

                    results.count = FilteredArray.size();
                    results.values = FilteredArray;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                mValues = (List<Business>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mAddressView;
        public final TextView mBusinessId;
        public Business mItem;

        /**
         * sets up the textviews
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.BusinessNameTextView);
            mAddressView = (TextView) view.findViewById(R.id.AddressTextView);
            mBusinessId = (TextView) view.findViewById(R.id.BusinessIdTextView);
        }

        /**
         * Ok... Well...
         * Ummm.... I don't know what I'm supposed to comment on this...
         * @return
         */
        @Override
        public String toString() {
            return "stupid";
        }
    }
}
