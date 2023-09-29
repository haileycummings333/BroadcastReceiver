package com.example.broadcastreceiver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/*
    Fragment that displays the list of ticker symbols
    Starts with 3 default entries E.g., {BAC, AAPL, DIS}
    Clicking on an entry in the list, opens the SeekingAlpha information website for
        that selected ticker on the InfoWebFragment
    The List fragment will contain no more than to 6 entries
    Receiving any additional tickers when the list has 6 entries already should replace
        the sixth entry with the newly received ticker
 */

public class TickerListFragment extends Fragment {


    public TickerListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticker_list, container, false);
        return v;
    }
}