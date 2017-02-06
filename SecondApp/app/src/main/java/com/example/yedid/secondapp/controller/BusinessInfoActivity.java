package com.example.yedid.secondapp.controller;

import android.content.Context;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yedid.secondapp.R;
import com.example.yedid.secondapp.model.backend.FactoryDataSource;
import com.example.yedid.secondapp.model.entities.Activity;
import com.example.yedid.secondapp.model.entities.Business;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/////////////////////////////I would much rather import the ExpandableListAdapter view from the activity fragment
public class BusinessInfoActivity extends AppCompatActivity {

    private ExpandableListView activitiesListView;
    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_info);

        setBusinessArg();
        setBusinessData();




        ArrayList<Activity> activities = new ArrayList<>();//taking only the trip for this business
        for(Activity activity: FactoryDataSource.getDataBase().getActivities())
        {
            if(activity.getBusinessId() == business.getId())
                activities.add(activity);
        }

        activitiesListView = (ExpandableListView) findViewById(R.id.activitiesExpandableView);
        activitiesListView.setAdapter(new ExpandableListAdapter(activities,this));
        activitiesListView.setGroupIndicator(null);

        final ExpandableListAdapter adapter;

        setTitle(business.getName());
    }



    /*public class ExpandableListAdapter extends BaseExpandableListAdapter implements Filterable {

        private final LayoutInflater inf;
        private List<Activity>[] children;
        private List<Activity>[] original;
        private Context context;

        public ExpandableListAdapter(List<com.example.yedid.secondapp.model.entities.Activity> activities, Context context) {

            this.original = getSortedByState(activities);
            this.children = this.original;
            this.context = context;
            inf = LayoutInflater.from(this.context);
        }

        private List<Activity>[] getSortedByState(List<Activity> activities)
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
                    children = (ArrayList<com.example.yedid.secondapp.model.entities.Activity>[]) results.values;
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
            ExpandableListAdapter.GroupViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.state_item, parent, false);

                holder = new ExpandableListAdapter.GroupViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ExpandableListAdapter.GroupViewHolder) convertView.getTag();
            }

            holder.setText(groupPosition);

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ExpandableListAdapter.ActivityViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.activity_item, parent, false);
                holder = new ExpandableListAdapter.ActivityViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ExpandableListAdapter.ActivityViewHolder) convertView.getTag();
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
                stateText = (TextView) convertView.findViewById(R.id.stateTextView);
            }

            public void setText(int group)
            {
                stateText.setText(children[group].get(0).getState());
            }

            TextView stateText;
        }

        private class ActivityViewHolder {

            public ActivityViewHolder(View convertView)
            {
                stateTextView = (TextView) convertView.findViewById(R.id.stateTextView);
                descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);
                endDateTextView = (TextView) convertView.findViewById(R.id.finishDateTextView);
                beginingDAtaText = (TextView) convertView.findViewById(R.id.beginingDateTextView);
            }

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
    }*/



    protected void Dial(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + business.getTelephoneNumber()));
        try {
            startActivity(intent);
        }
        catch (SecurityException e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT);
        }
    }

    protected void SentEmail(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{business.getEmail()});
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    protected void OpenMap(View view)
    {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=1600 " + business.getAddress().state + ", " + business.getAddress().city + ", " + business.getAddress().state);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    protected void OpenWebSite(View view)
    {
        Intent intent = new Intent(this, BusinessWebView.class);
        intent.putExtra("address",business.getWebsiteAddress());
        startActivity(intent);
    }

    //set the business argument for thisfragment so we can take the buisness data and print them
    private void setBusinessArg()
    {
        Intent intent = getIntent();
        business = (Business)intent.getSerializableExtra("business");
    }

    //take the data from the business paramter and print it
    private void setBusinessData()
    {
        //set the btns text
        ((TextView)findViewById(R.id.businessNameText)).setText(business.getName());
        ((Button)findViewById(R.id.phoneBtn)).setText(business.getTelephoneNumber());
        ((Button)findViewById(R.id.webBtn)).setText(business.getWebsiteAddress());
        ((Button)findViewById(R.id.mapBtn)).setText(business.getAddress().toString());
        ((Button)findViewById(R.id.emailBtn)).setText(business.getEmail());
    }
}
