package org.pdm.ib.home.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
        Drawable.ConstantState viewState = view.getDrawable().getConstantState();
        Drawable.ConstantState arrowDownState = context.getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_white_18px).getConstantState();

        return (viewState != null && arrowDownState != null && viewState.equals(arrowDownState)) ||
                getBitmap(view.getDrawable()).sameAs(getBitmap(context.getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_white_18px)));
    }

    private static Bitmap getBitmap(Drawable drawable) {
        Bitmap result;

        if (drawable instanceof BitmapDrawable) {
            result = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            if (width <= 0) {
                width = 1;
            }
            if (height <= 0) {
                height = 1;
            }

            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return result;
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
