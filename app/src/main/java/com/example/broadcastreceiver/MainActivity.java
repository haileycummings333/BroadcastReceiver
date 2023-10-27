package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;
    TickerListViewModel tickerListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String message = intent.getStringExtra("SMS");
        //initializes the tl view model
        tickerListViewModel = new ViewModelProvider(this).get(TickerListViewModel.class);
        fg = getSupportFragmentManager();

        //if there's no saved state, replaces fragments within the current activitys layout
        if(savedInstanceState==null){
            fg.beginTransaction().replace(R.id.listFragment, new TickerListFragment()).commit();
            fg.beginTransaction().replace(R.id.webFragment, new InfoWebFragment()).commit();
        }

        //check for permissions, will request if not granted yet
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){
            String[] permissions = new String[]{android.Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this,permissions, 101);
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //retrieves the message from the intent
        String message = intent.getStringExtra("sms");

        //checks format and validity of the ticker from sms message
        if(!message.contains("Ticker:<<") || !message.contains(">>")){ //invalid format
            recreate();
            Toast.makeText(this, "Not a valid entry", Toast.LENGTH_SHORT).show();
        } else {
            int tickerBegin = message.lastIndexOf('<');
            int tickerEnd = message.indexOf('>');
            String ticker = message.substring(tickerBegin + 1, tickerEnd).toUpperCase();
            if (isValidTicker(ticker) == false){ //invalid ticker
                recreate();
                Toast.makeText(this, "The ticker was invalid", Toast.LENGTH_SHORT).show();
            } else { //the ticker is valid and in the correct format
                recreate();
                tickerListViewModel.addTickers(ticker);
                tickerListViewModel.setSelectedTicker(ticker);
            }
        }
    }
    //checks if ticker is valid
    public boolean isValidTicker(String ticker){
        for (int i = 0; i < ticker.length(); i++){
            if((!Character.isLetter(ticker.charAt(i))))
                return false;
        }
        return true;
    }
}
