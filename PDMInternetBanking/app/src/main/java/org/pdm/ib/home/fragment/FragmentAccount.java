package org.pdm.ib.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pdm.ib.R;
import org.pdm.ib.context.AccountContextHolder;
import org.pdm.ib.event.AccountChangedEvent;
import org.pdm.ib.listener.OnAccountChangedEventListener;
import org.pdm.ib.model.Account;
import org.pdm.ib.util.AccountTitle;

public class FragmentAccount extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AccountContextHolder.addOnAccountChangedListener(new OnAccountChanged(view));
    }

    private static class OnAccountChanged implements OnAccountChangedEventListener {

        private View view;

        OnAccountChanged(View view) {
            this.view = view;
        }

        @Override
        public void handle(AccountChangedEvent event) {
            TextView textViewAccountLabel = view.findViewById(R.id.account_balance_label);
            Account account = event.getAccount();

            textViewAccountLabel.setText(AccountTitle.getTitleByAccountType(view.getContext(), account.getType()));

            TextView textViewAccount = view.findViewById(R.id.text_view_account_balance);
            textViewAccount.setText(view.getResources().getString(R.string.balance_placeholder, account.getBalance().getAmount()));
        }
    }
}
