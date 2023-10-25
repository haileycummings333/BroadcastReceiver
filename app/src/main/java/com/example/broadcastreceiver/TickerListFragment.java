package com.example.broadcastreceiver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TickerListFragment extends Fragment {

    ListView listview;
    ArrayAdapter<String> adapter;
    TickerListViewModel tickerListViewModel;

    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String ticker = (String) adapterView.getItemAtPosition(i);
            tickerListViewModel.setSelectedTicker(ticker); // sets the selected ticker
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tickerListViewModel = new ViewModelProvider(getActivity()).get(TickerListViewModel.class);
        //observes changes in the listview
        Observer<LinkedList<String>> observer = new Observer<LinkedList<String>>() {
            @Override
            public void onChanged(LinkedList<String> strings) {
                LinkedList<String> tickers = tickerListViewModel.getTickers().getValue();
                adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tickers);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(clickListener);
                adapter.notifyDataSetChanged();
            }
        };

        tickerListViewModel.getTickers().observe(getViewLifecycleOwner(),observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticker_list, container, false);
        listview = view.findViewById(R.id.listview_id);
        return view;
    }


}