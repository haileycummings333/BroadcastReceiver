package com.example.broadcastreceiver;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class TickerListViewModel extends ViewModel {

    MutableLiveData<LinkedList<String>> tickers = new MutableLiveData<>();
    //stores the tickers
    LinkedList<String> tickerList = new LinkedList<>();
    //holds selected ticker
    MutableLiveData<String> selectedTicker = new MutableLiveData<>();
    int ci = 0; //current index, used for round robin implementation


    //sets the selected ticker so the observer gets notified
    public void setSelectedTicker(String ticker){
        selectedTicker.setValue(ticker);
    }

    //returns the selected ticker
    public LiveData<String> getSelectedTicker(){
        return selectedTicker;
    }

    //returns the list of tickers, if there are none it sets the default tickers
    public LiveData<LinkedList<String>> getTickers(){
        if (tickerList.size() == 0)
            setTickers();
        return tickers;
    }

    //initializes the ticker list
    public void setTickers(){
        if(tickerList.size() == 0){
            tickerList.add("BAC");
            tickerList.add("AAPL");
            tickerList.add("DIS");
            tickers.setValue(tickerList);
        }
    }

    //adds new ticker to list, making sure it doesn't hold more than 6 tickers at once
//    public void addTickers(String ticker){
//        if(tickerList.size() >= 6 && !tickerList.contains(ticker)){
//            tickerList.set(5, ticker);
//        } else if(tickerList.size() < 6 && !tickerList.contains(ticker)) {
//            tickerList.add(ticker);
//            tickers.setValue(tickerList);
//        }
//    }

    //round robin implementation
    public void addTickers(String ticker){
        if(tickerList.size() >= 6 && !tickerList.contains(ticker)){
            tickerList.set(ci, ticker);
            ci = (ci + 1) % 6; //update the current index which should loop back to 0
                                //when it exceeds 5 (stays in range 0-5)
        } else if(tickerList.size() < 6 && !tickerList.contains(ticker)) {
            tickerList.add(ticker);
            tickers.setValue(tickerList);
        }
    }


}
