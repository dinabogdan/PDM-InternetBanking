package org.pdm.ib.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.pdm.ib.R;
import org.pdm.ib.model.AccountBalance;
import org.pdm.ib.service.AccountBalanceEvolutionService;
import org.pdm.ib.service.impl.AccountBalanceEvolutionServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class FragmentChart extends Fragment {

    private AccountBalanceEvolutionService accountBalanceEvolutionService = new AccountBalanceEvolutionServiceImpl();

    private LineChart accountEvolutionChart;
    private List<AccountBalance> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        this.accountEvolutionChart = view.findViewById(R.id.chart_account_evolution);
        this.data = accountBalanceEvolutionService.getLastYearAccountBalance(null);

        addChartData();

        return view;
    }

    private void addChartData() {

        List<Entry> entries = new ArrayList<>();

        for (AccountBalance balance : this.data) {
            entries.add(new Entry(1 + balance.getMonth().getMonth(), balance.getAmount().floatValue()));
        }

        LineData lineData = new LineData(new LineDataSet(entries, "Account balance evolution"));

        accountEvolutionChart.setData(lineData);
        accountEvolutionChart.invalidate();
    }
}
