package com.example.broadcastreceiver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TickerAdapter extends RecyclerView.Adapter<TickerAdapter.TickerViewHolder> {
    private List<String> tickerList;
    private OnItemClickListener onItemClickListener;

    public TickerAdapter(List<String> tickerList) {
        this.tickerList = tickerList;
    }

    public interface OnItemClickListener {
        void onItemClick(String tickerSymbol);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public TickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ticker_list, parent, false);
        return new TickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TickerViewHolder holder, int position) {
        String tickerSymbol = tickerList.get(position);
        holder.bind(tickerSymbol);
    }

    @Override
    public int getItemCount() {
        return tickerList.size();
    }

    public class TickerViewHolder extends RecyclerView.ViewHolder {
        private TextView tickerTextView;

        public TickerViewHolder(@NonNull View itemView) {
            super(itemView);
            tickerTextView = itemView.findViewById(R.id.tickerTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(tickerList.get(position));
                        }
                    }
                }
            });
        }

        public void bind(String tickerSymbol) {
            tickerTextView.setText(tickerSymbol);
        }
    }
}
