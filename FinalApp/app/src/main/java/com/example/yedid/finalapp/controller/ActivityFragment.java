package com.example.yedid.finalapp.controller;

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

import com.example.yedid.finalapp.R;
import com.example.yedid.finalapp.model.backend.FactoryDataSource;
import com.example.yedid.finalapp.model.entities.Activity;
import com.example.yedid.finalapp.controller.ExpandableListAdapter;

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

    /**
     * This method is set off when creating the fragment.
     * It's in charge of setting it up
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * This method is set off when creating the view.
     * It inflates the view which is stored in the xml file
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);
        //getActivity().setTitle("yedidya");
    }

    /**
     * This method is called post to the creation of the view.
     * It is in charge of setting up all the listviews and other graphic parts
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activitiesListView = (ExpandableListView) view.findViewById(R.id.TripExpandbleListView);
        activitiesListView.setAdapter(new ExpandableListAdapter(FactoryDataSource.getDataBase().getActivities(),getActivity()));
        activitiesListView.setGroupIndicator(null);

        final ExpandableListAdapter adapter;
        //final MenuItem item = ((ActionMenuItemView) getActivity().findViewById(R.id.action_search)).getItemData();
        //SearchView searchView = (SearchView)item.getActionView();//in menu/main

        if (activitiesListView.getExpandableListAdapter() instanceof ExpandableListAdapter) {
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

    /**
     * This method is called when a button is pressed - automatically created by the compiler
     * @param activity
     */
    public void onButtonPressed(Activity activity) {
        if (mListener != null) {
            mListener.onFragmentInteraction(activity);
        }
    }

    /**
     * This is in charge of attaching the fragment to the activity
     * @param context
     */
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

    /**
     * This is in charge of detaching the fragment finally from the activity
     */
    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    /**
     *
     * This is in charge of setting the search view
     * @param sv
     */
    public void setSearchView(SearchView sv)
    {
        searchView = sv;
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
