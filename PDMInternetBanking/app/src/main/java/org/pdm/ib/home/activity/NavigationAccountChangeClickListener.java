package org.pdm.ib.home.activity;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import org.pdm.ib.R;
import org.pdm.ib.context.AccountContextHolder;
import org.pdm.ib.model.Account;

public class NavigationAccountChangeClickListener implements View.OnClickListener {

    private Context context;
    private NavigationView navigationView;

    private int regularMenuActiveItemIdx = 0;

    public NavigationAccountChangeClickListener(Context context, NavigationView navigationView) {
        this.context = context;
        this.navigationView = navigationView;
    }

    @Override
    public void onClick(View v) {
        ImageView imageView = v.findViewById(R.id.img_choose_account);

        if (isArrowDown(imageView)) {
            regularMenuActiveItemIdx = getCheckedItem();

            imageView.setImageResource(R.drawable.ic_keyboard_arrow_up_white_18px);

            showAccountsMenu();
        } else {
            imageView.setImageResource(R.drawable.ic_keyboard_arrow_down_white_18px);

            showRegularMenu();
        }
    }

    private boolean isArrowDown(ImageView view) {
        return view.getDrawable().getConstantState()
                .equals(context.getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_white_18px).getConstantState());
    }

    private void showAccountsMenu() {
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.activity_home_accounts);

        Account account = AccountContextHolder.getCurrentAccount();

        switch (account.getType()) {
            case CURRENT:
                navigationView.getMenu().findItem(R.id.nav_menu_item_current_account).setChecked(true);
                break;
            case SAVINGS:
                navigationView.getMenu().findItem(R.id.nav_menu_item_savings_account).setChecked(true);
                break;
            case CREDIT:
                navigationView.getMenu().findItem(R.id.nav_menu_item_credit_account).setChecked(true);
                break;
        }
    }

    private void showRegularMenu() {
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.activity_home_drawer);

        navigationView.getMenu().getItem(regularMenuActiveItemIdx).setChecked(true);
    }

    private int getCheckedItem() {
        Menu menu = navigationView.getMenu();

        for (int idx = 0; idx < menu.size(); idx++) {
            if (menu.getItem(idx).isChecked()) {
                return idx;
            }
        }

        return -1;
    }
}
