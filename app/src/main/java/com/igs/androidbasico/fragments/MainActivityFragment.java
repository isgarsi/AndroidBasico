package com.igs.androidbasico.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igs.androidbasico.DividerItemDecoration;
import com.igs.androidbasico.R;
import com.igs.androidbasico.adapters.RViewAdapter;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ArrayList<String> mItems;
    private RecyclerView recView;
    private RViewAdapter adapter;
    private OnMainFragmentInteractionListener mListener;

    public MainActivityFragment() {
    }

    public interface OnMainFragmentInteractionListener {
        public void onItemSelected(int option);
    }

    public static MainActivityFragment newInstance(int idUser) {
        MainActivityFragment fragment = new MainActivityFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMainFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMainFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fillItems();
        adapter = new RViewAdapter(getActivity(), mItems);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recView.getChildAdapterPosition(v);
                mListener.onItemSelected(position);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recView = (RecyclerView) view.findViewById(R.id.rv_main);
        recView.setHasFixedSize(true);
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        return view;
    }

    private void fillItems(){
        mItems = new ArrayList<String>();
        mItems.add(getString(R.string.item_animations));
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
