package org.pdm.ib.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pdm.ib.R;
import org.pdm.ib.context.AccountContextHolder;
import org.pdm.ib.home.fragment.FragmentContentHome;
import org.pdm.ib.map.FindATMActivity;
import org.pdm.ib.model.UserProfile;
import org.pdm.ib.payments.FragmentPayments;
import org.pdm.ib.service.AccountService;
import org.pdm.ib.service.UserProfileService;
import org.pdm.ib.service.impl.AccountServiceImpl;
import org.pdm.ib.service.impl.UserProfileServiceImpl;
import org.pdm.ib.widget.AccountBalanceWidget;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final AccountService accountService = new AccountServiceImpl();
    private final UserProfileService userService = new UserProfileServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(AccountBalanceWidget.NEW_PAYMENT_KEY)) {
            replaceFragment(FragmentPayments.class);
            navigationView.getMenu().findItem(R.id.nav_payments).setChecked(true);
        } else {
            replaceFragment(FragmentContentHome.class);
        }

        View header = navigationView.getHeaderView(0);

        TextView textViewUserDetails = header.findViewById(R.id.textViewUserDetails);
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserProfile profile = userService.getProfile();
                HomeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewUserDetails.setText(getResources().getString(R.string.user_details_placeholder, profile.getLastName(), profile.getFirstName()));
                    }
                });
            }
        }).start();

        LinearLayout linearLayoutChooseAccount = header.findViewById(R.id.linearLayoutChooseAccount);
        linearLayoutChooseAccount.setOnClickListener(new NavigationAccountChangeClickListener(getApplicationContext(), navigationView));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        Class fragmentClass = null;

        switch (item.getItemId()) {
            //regular navigation menu items
            case R.id.nav_home:
                fragmentClass = FragmentContentHome.class;
                break;
            case R.id.nav_find_atm:
                intent = new Intent(getApplicationContext(), FindATMActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_payments:
                fragmentClass = FragmentPayments.class;
                break;
            //choose account navigation menu items
            case R.id.nav_menu_item_current_account:
                AccountContextHolder.setCurrentAccount(accountService.getCurrentAccount());
                break;
            case R.id.nav_menu_item_savings_account:
                AccountContextHolder.setCurrentAccount(accountService.getSavingsAccount());
                break;
            case R.id.nav_menu_item_credit_account:
                AccountContextHolder.setCurrentAccount(accountService.getCreditAccount());
                break;
        }

        if (fragmentClass != null) {
            replaceFragment(fragmentClass);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private <T extends Fragment> void replaceFragment(Class<T> fragmentClass) {
        try {
            Fragment fragment = fragmentClass.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.flContent, fragment)
                    .commit();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
