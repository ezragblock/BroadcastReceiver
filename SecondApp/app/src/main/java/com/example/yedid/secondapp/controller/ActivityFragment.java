package com.example.yedid.secondapp.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.yedid.secondapp.R;
import com.example.yedid.secondapp.model.backend.FactoryDataSource;
import com.example.yedid.secondapp.model.entities.Activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityFragment extends Fragment {/////////////////////////i much rather take te expandable list fro  the other class or make an independable class somehow
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ExpandableListView activitiesListView;
    private SearchView searchView;

    public ActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);
        //getActivity().setTitle("yedidya");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activitiesListView = (ExpandableListView) view.findViewById(R.id.TripExpandbleListView);
        activitiesListView.setAdapter(new ActivityFragment.ExpandableListAdapter(FactoryDataSource.getDataBase().getActivities()));
        activitiesListView.setGroupIndicator(null);

        final ExpandableListAdapter adapter;
        //final MenuItem item = ((ActionMenuItemView) getActivity().findViewById(R.id.action_search)).getItemData();
        //SearchView searchView = (SearchView)item.getActionView();//in menu/main

        if (activitiesListView.getExpandableListAdapter() instanceof ActivityFragment.ExpandableListAdapter) {
            adapter = (ExpandableListAdapter) activitiesListView.getExpandableListAdapter();

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    adapter.getFilter().filter(null);
                    return false;
                }
            });
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Activity activity) {
        if (mListener != null) {
            mListener.onFragmentInteraction(activity);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setSearchView(SearchView sv)
    {
        searchView = sv;
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter implements Filterable {

        private final LayoutInflater inf;
        private List<Activity>[] children;
        private List<com.example.yedid.secondapp.model.entities.Activity>[] original;

        public ExpandableListAdapter(List<com.example.yedid.secondapp.model.entities.Activity> activities) {

            this.original = getSortedByState(activities);
            this.children = this.original;
            inf = LayoutInflater.from(getActivity());
        }

        private List<com.example.yedid.secondapp.model.entities.Activity>[] getSortedByState(List<com.example.yedid.secondapp.model.entities.Activity> activities)
        {
            ArrayList<String> group = new ArrayList<>();
            for(com.example.yedid.secondapp.model.entities.Activity state:activities)
            {
                if(!group.contains(state.getState()))
                    group.add(state.getState());
            }//counting how many groups do we need

            ArrayList<com.example.yedid.secondapp.model.entities.Activity>[] result = new ArrayList[group.size()];
            for(int i = 0;i < group.size();i++)
            {
                result[i] = new ArrayList<>();
            }//initilze the group

            for(com.example.yedid.secondapp.model.entities.Activity activity:activities)
            {
                for(int i = 0;i < group.size();i++)
                {
                    if(group.get(i) == activity.getState())
                        result[i].add(activity);
                }
            }//add every activity to the appropriate position in the group
            return result;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    ArrayList<ArrayList<com.example.yedid.secondapp.model.entities.Activity>> FilteredArray = new ArrayList<>();

                    // perform your search here using the searchConstraint String.

                    if (constraint == null || constraint.length() == 0) {
                        results.count = original.length;
                        results.values = original;
                    } else {
                        String cs = constraint.toString().toLowerCase();
                        for (int i = 0; i < original.length; i++) {
                            String tmp = original[i].get(0).getState();
                            if (tmp.toLowerCase().startsWith(cs))  {
                                FilteredArray.add((ArrayList<com.example.yedid.secondapp.model.entities.Activity>)original[i]);
                            }
                        }

                        results.count = FilteredArray.size();
                        results.values = FilteredArray;
                    }

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if(constraint.length() == 0)
                        children = original;
                    else {
                        ArrayList<ArrayList<Activity>> FilteredArray = ((ArrayList<ArrayList<Activity>>) results.values);
                        children = new ArrayList[results.count];
                        //convert the FilteredArray to children
                        for (int i = 0; i < results.count; i++) {
                            children[i] = FilteredArray.get(i);
                        }
                    }
                    notifyDataSetChanged();
                }
            };
        }

        @Override
        public int getGroupCount() {
            return children.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return children[groupPosition].get(0).getState();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition].get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.state_item, parent, false);

                holder = new GroupViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (GroupViewHolder) convertView.getTag();
            }

            holder.setText(groupPosition);

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ActivityViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.activity_item, parent, false);
                holder = new ActivityViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ActivityViewHolder) convertView.getTag();
            }

            holder.setText(groupPosition,childPosition);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        private class GroupViewHolder{

            public GroupViewHolder(View convertView)
            {
                stateText = (TextView) convertView.findViewById(R.id.SteteTextView);
            }

            public void setText(int group)
            {
                stateText.setText(children[group].get(0).getState());
            }

            private TextView stateText;
        }

        private class ActivityViewHolder {

            public ActivityViewHolder(View convertView)
            {
                endDateTextView = (TextView) convertView.findViewById(R.id.EndDateTextView);
                beginingDAtaText = (TextView) convertView.findViewById(R.id.BeginingDateTextView);
            }

            public void setText(int group,int child)
            {
                beginingDAtaText.setText(children[group].get(child).getBeginningDate().get(Calendar.DAY_OF_MONTH) + "/" +
                                         children[group].get(child).getBeginningDate().get(Calendar.MONTH) + "/" +
                                         children[group].get(child).getBeginningDate().get(Calendar.YEAR));
                endDateTextView.setText(children[group].get(child).getFinishDate().get(Calendar.DAY_OF_MONTH) + "/" +
                                         children[group].get(child).getFinishDate().get(Calendar.MONTH) + "/" +
                                         children[group].get(child).getFinishDate().get(Calendar.YEAR));
            }

            private TextView endDateTextView;
            private TextView beginingDAtaText;
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Activity activity);
    }
}
