package org.pdm.ib.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pdm.ib.R;
import org.pdm.ib.model.Account;
import org.pdm.ib.service.AccountService;
import org.pdm.ib.service.impl.AccountServiceImpl;

public class FragmentTotalAmount extends Fragment {

    private AccountService accountService = new AccountServiceImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_total_amount, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView textViewTotalAmount = view.findViewById(R.id.text_view_total_amount);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Double balance = 0.0;
                for (Account account : accountService.getAccounts(1L)) {
                    if (account.getBalance() != null) {
                        balance += account.getBalance().getAmount();
                    }
                }

                final Double balanceResult = balance;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewTotalAmount.setText(getResources().getString(R.string.balance_placeholder, balanceResult));
                    }
                });
            }
        }).start();
    }
}
