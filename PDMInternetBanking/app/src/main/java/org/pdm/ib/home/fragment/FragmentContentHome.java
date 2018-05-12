package org.pdm.ib.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pdm.ib.R;
import org.pdm.ib.context.AccountContextHolder;
import org.pdm.ib.service.AccountService;
import org.pdm.ib.service.impl.AccountServiceImpl;

public class FragmentContentHome extends Fragment {

    private final AccountService accountService = new AccountServiceImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //AccountContextHolder.setCurrentAccount(accountService.getCurrentAccount());
    }
}
