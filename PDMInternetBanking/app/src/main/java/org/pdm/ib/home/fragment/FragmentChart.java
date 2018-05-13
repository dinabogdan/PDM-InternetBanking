package org.pdm.ib.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManagerNonConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.pdm.ib.R;
import org.pdm.ib.context.AccountContextHolder;
import org.pdm.ib.event.AccountChangedEvent;
import org.pdm.ib.listener.OnAccountChangedEventListener;
import org.pdm.ib.model.AccountBalance;
import org.pdm.ib.service.AccountBalanceService;
import org.pdm.ib.service.impl.AccountBalanceServiceImpl;
import org.pdm.ib.util.AccountTitle;

import java.util.ArrayList;
import java.util.List;

public class FragmentChart extends Fragment {

    private AccountBalanceService accountBalanceService = new AccountBalanceServiceImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LineChart accountEvolutionChart = view.findViewById(R.id.chart_account_evolution);
        TextView textView = view.findViewById(R.id.account_chart_label);

        //new Thread(new Runnable() {
          //  @Override
            //public void run() {
              //  getActivity().runOnUiThread(new Runnable() {
               //     @Override
                 //   public void run() {
                        AccountContextHolder.addOnAccountChangedListener(new OnAccountChanged(accountEvolutionChart, textView, accountBalanceService));
                   // }
                //});
  //          }
//        }).start();

    }

    private class OnAccountChanged implements OnAccountChangedEventListener {

        private LineChart chart;
        private TextView accountLabel;
        private AccountBalanceService accountBalanceService;

        OnAccountChanged(LineChart chart, TextView accountLabel, AccountBalanceService accountBalanceService) {
            this.chart = chart;
            this.accountLabel = accountLabel;
            this.accountBalanceService = accountBalanceService;
        }

        @Override
        public void handle(AccountChangedEvent event) {
            accountLabel.setText(AccountTitle.getTitleByAccountType(
                    chart.getContext(),
                    event.getAccount().getType(),
                    R.string.account_chart_title_placeholder));
            //new Thread(new Runnable() {
           //     @Override
             //   public void run() {
             //       FragmentChart.this.getActivity().runOnUiThread(new Runnable() {
             //           @Override
               //         public void run() {
                            List<AccountBalance> data = accountBalanceService.getLastYearAccountBalance(event.getAccount());
                            addChartData(data);
                 //       }
                   // });
               // }
            //}).start();


        }

        private void addChartData(List<AccountBalance> data) {
            List<Entry> entries = new ArrayList<>();

            for (AccountBalance balance : data) {
                entries.add(new Entry(1 + balance.getMonth().getMonth(), balance.getAmount().floatValue()));
            }

            LineData lineData = new LineData(new LineDataSet(entries, "Account balance evolution"));

            chart.setData(lineData);
            chart.invalidate();
        }
    }
}
