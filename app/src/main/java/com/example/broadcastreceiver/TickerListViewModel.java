package com.example.broadcastreceiver;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class TickerListViewModel extends ViewModel {
    MutableLiveData<LinkedList<String>> tickers = new MutableLiveData<>();
    LinkedList<String> tickerList = new LinkedList<>();
    MutableLiveData<String> selectedTicker = new MutableLiveData<>();


    //sets the ticker that is selected
    public void setSelectedTicker(String ticker){
        selectedTicker.setValue(ticker);
    }

    //returns the ticker that is selected
    public LiveData<String> getSelectedTicker(){
        return selectedTicker;
    }


    public LiveData<LinkedList<String>> getTickers(){
        if (tickerList.size() == 0) setTickers();

        return tickers;
    }

    public void setTickers(){
        if(tickerList.size() == 0){
            tickerList.add("TSLA");
            tickerList.add("SBUX");
            tickerList.add("AAPL");
            tickers.setValue(tickerList);
        }
    }

    public void addTickers(String ticker){
        if(tickerList.size() >= 6 && !tickerList.contains(ticker)){
            tickerList.set(5, ticker);
        } else if(tickerList.size() < 6 && !tickerList.contains(ticker)) {
            tickerList.add(ticker);
            tickers.setValue(tickerList);
        }
    }


}
