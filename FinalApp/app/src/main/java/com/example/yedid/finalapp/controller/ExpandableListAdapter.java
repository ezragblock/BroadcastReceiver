package com.example.yedid.finalapp.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.yedid.finalapp.R;
import com.example.yedid.finalapp.model.entities.Activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yedid on 2/6/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter implements Filterable {
    private final LayoutInflater inf;
    private List<Activity>[] children;
    private List<Activity>[] original;
    private Context context;

    /**
     * Constructor of the expendable list adapter
     * @param activities the list of activities being put in the expendable list
     * @param context
     */
    public ExpandableListAdapter(List<Activity> activities, Context context) {

        this.original = getSortedByState(activities);
        this.children = this.original;
        this.context = context;
        inf = LayoutInflater.from(this.context);
    }

    /**
     * returns the activities sorted by their state
     * @param activities
     * @return
     */
    private List<Activity>[] getSortedByState(List<Activity> activities)
    {
        ArrayList<String> group = new ArrayList<>();
        for(Activity state:activities)
        {
            if(!group.contains(state.getState()))
                group.add(state.getState());
        }//counting how many groups do we need

        ArrayList<Activity>[] result = new ArrayList[group.size()];
        for(int i = 0;i < group.size();i++)
        {
            result[i] = new ArrayList<>();
        }//initilze the group

        for(Activity activity:activities)
        {
            for(int i = 0;i < group.size();i++)
            {
                if(group.get(i) == activity.getState())
                    result[i].add(activity);
            }
        }//add every activity to the appropriate position in the group
        return result;
    }

    /**
     *
     * @return the filter of the activities
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<ArrayList<Activity>> FilteredArray = new ArrayList<>();

                // perform your search here using the searchConstraint String.

                if (constraint == null || constraint.length() == 0) {
                    results.count = original.length;
                    results.values = original;
                } else {
                    String cs = constraint.toString().toLowerCase();
                    for (int i = 0; i < original.length; i++) {
                        String tmp = original[i].get(0).getState();
                        if (tmp.toLowerCase().startsWith(cs))  {
                            FilteredArray.add((ArrayList<Activity>)original[i]);
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
                    children = new ArrayList[results.count];
                    ArrayList<ArrayList<Activity>> myList = (ArrayList<ArrayList<Activity>>) results.values;

                    //convert array list to array
                    for (int i = 0; i < results.count; i++) {
                        children[i] = myList.get(i);
                    }
                }
                notifyDataSetChanged();
            }
        };
    }

    /**
     *
     * @return returns the  froup count
     * */
    @Override
    public int getGroupCount() {
        return children.length;
    }

    /**
     *
     * @param groupPosition the group chosen
     * @return the count of the children inside a group
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return children[groupPosition].size();
    }

    /**
     * gets a specific chosen group
     * @param groupPosition the group chosen
     * @return the group that was chosen
     */
    @Override
    public Object getGroup(int groupPosition) {
        return children[groupPosition].get(0).getState();
    }

    /**
     * gets a specific child from a group
     * @param groupPosition the group chosen
     * @param childPosition the child chosen
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children[groupPosition].get(childPosition);
    }

    /**
     * return a group ID
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * returns the ID child of a group which were chosen
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * returns whether or not the IDs are stable
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * returns the group view
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
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

    /**
     * returns the child view
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
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

    /**
     * returns whether or not a child of a group is selected currently
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupViewHolder{

        /**
         * Constructor of the group view holder
         * @param convertView
         */
        public GroupViewHolder(View convertView)
        {
            stateText = (TextView) convertView.findViewById(R.id.stateTextView);
        }

        /**
         * puts in the state text into the state textview
         * @param group
         */
        public void setText(int group)
        {
            stateText.setText(children[group].get(0).getState());
        }

        TextView stateText;
    }

    private class ActivityViewHolder {

        /**
         * constructor - sets up all the textviews
         * @param convertView
         */
        public ActivityViewHolder(View convertView)
        {
            stateTextView = (TextView) convertView.findViewById(R.id.stateTextView);
            descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);
            endDateTextView = (TextView) convertView.findViewById(R.id.finishDateTextView);
            beginingDAtaText = (TextView) convertView.findViewById(R.id.beginingDateTextView);
        }

        /**
         * sets the text of the textviews according to the activity given
         * @param group
         * @param child
         */
        public void setText(int group,int child)
        {
            beginingDAtaText.setText(children[group].get(child).getBeginningDate().get(Calendar.DAY_OF_MONTH) + "/" +
                    children[group].get(child).getBeginningDate().get(Calendar.MONTH) + "/" +
                    children[group].get(child).getBeginningDate().get(Calendar.YEAR));
            endDateTextView.setText(children[group].get(child).getFinishDate().get(Calendar.DAY_OF_MONTH) + "/" +
                    children[group].get(child).getFinishDate().get(Calendar.MONTH) + "/" +
                    children[group].get(child).getFinishDate().get(Calendar.YEAR));
            stateTextView.setText("State: " + children[group].get(child).getState().toString());
            descriptionTextView.setText("Description: " + children[group].get(child).getDescription());
        }

        TextView endDateTextView;
        TextView stateTextView;
        TextView descriptionTextView;
        TextView beginingDAtaText;
    }
}
