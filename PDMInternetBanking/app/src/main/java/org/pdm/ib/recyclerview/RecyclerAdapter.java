package org.pdm.ib.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pdm.ib.R;
import org.pdm.ib.model.Transaction;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Transaction> transactions;

    public RecyclerAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.tvTransactionName.setText(transactions.get(position).getReceiverName());
        holder.tvTransactionAmount.setText(String.valueOf(transactions.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return (transactions != null ? transactions.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTransactionName;
        TextView tvTransactionAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTransactionName = itemView.findViewById(R.id.tv_transaction_receiver_name);
            tvTransactionAmount = itemView.findViewById(R.id.tv_transaction_amount);
        }
    }
}
