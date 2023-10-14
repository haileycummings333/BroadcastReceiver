package com.example.broadcastreceiver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TickerListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TickerAdapter tickerAdapter;
    private List<String> tickerList;

    public TickerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tickerList = new ArrayList<>();
        // Initialize with default entries
        tickerList.add("BAC");
        tickerList.add("AAPL");
        tickerList.add("DIS");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticker_list, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tickerAdapter = new TickerAdapter(tickerList);
        recyclerView.setAdapter(tickerAdapter);

        // Handle item clicks in the RecyclerView
        tickerAdapter.setOnItemClickListener(new TickerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String tickerSymbol) {
                // Replace the InfoWebFragment with the selected ticker's URL
                ((MainActivity) requireActivity()).loadInfoWebFragment(generateSeekingAlphaURL(tickerSymbol));
            }
        });

        return v;
    }

    private String generateSeekingAlphaURL(String tickerSymbol) {

        return "https://seekingalpha.com/symbol/" + tickerSymbol;
    }
}
