package com.example.broadcastreceiver;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class TickerListViewModel extends ViewModel {
    MutableLiveData<LinkedList<String>> tickers = new MutableLiveData<>();
    LinkedList<String> tickerlist = new LinkedList<>();
    MutableLiveData<String> selectedTicker = new MutableLiveData<>();

    public void setSelectedTicker(String ticker){
        selectedTicker.setValue(ticker);
    }

    //returns the ticker that is selected
    public LiveData<String> getSelectedTicker(){
        return selectedTicker;
    }


    public LiveData<LinkedList<String>> getTickers(){
        if (tickerlist.size() == 0) setTickers();

        return tickers;
    }

    public void setTickers(){
        if(tickerlist.size() == 0){
            tickerlist.add("AAPL");
            tickerlist.add("TSLA");
            tickerlist.add("SBUX");
            tickers.setValue(tickerlist);
        }
    }

    public void addTickers(String ticker){
        if(tickerlist.size() >= 6 && !tickerlist.contains(ticker)){
            tickerlist.set(5, ticker);
        } else if(tickerlist.size() < 6 && !tickerlist.contains(ticker)) {
            tickerlist.add(ticker);
            tickers.setValue(tickerlist);
        }
    }


}
