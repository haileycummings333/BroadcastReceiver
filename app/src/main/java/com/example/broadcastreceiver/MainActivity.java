package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;
    TickerListViewModel tickerListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String message = intent.getStringExtra("SMS");

        gf = getSupportFragmentManager();
        if(savedInstanceState==null){
            fg.beginTransaction().replace(R.id.topTicker, new TickerList()).commit();
            fg.beginTransaction().replace(R.id.bottomTicker, new WebViewFragment()).commit();
        }

        tickerListViewModel = new ViewModelProvider(this).get(TickerListViewModel.class);

        // Check if the layout is in landscape mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load both fragments side by side
            loadTickerListFragment();
            loadInfoWebFragment("https://seekingalpha.com");
        }
    }

    private void loadTickerListFragment() {
        // Add TickerListFragment to the container view
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new TickerListFragment())
                .commit();
    }

    private void loadInfoWebFragment(String url) {
        // Add InfoWebFragment to the container view and pass the URL
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        InfoWebFragment infoWebFragment = new InfoWebFragment();
        infoWebFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, infoWebFragment)
                .commit();
    }
}
