package org.pdm.ib.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.pdm.ib.R;
import org.pdm.ib.model.Transaction;
import org.pdm.ib.recyclerview.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Transaction> transactions;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        transactions = new ArrayList<>();
        Transaction tx1 = new Transaction();
        tx1.setReceiverName("Dina Bogdan");
        tx1.setAmount(100.0);
        Transaction tx2 = new Transaction();
        tx2.setReceiverName("Burchi Sebi");
        tx2.setAmount(101.0);

        transactions.add(tx1);
        transactions.add(tx2);

        recyclerAdapter = new RecyclerAdapter(transactions);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}
