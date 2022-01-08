package com.example.expensetracker;

import android.animation.Animator;
import android.content.res.Resources;
import android.os.Build;

import androidx.annotation.MenuRes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.view.MenuItem;

public class AddExpenseActivity extends AppCompatActivity {

    private View background;
    private ImageView currency_dropdown, category_dropdown, close_btn;
    private EditText amount,description;
    private TextView category, currency;
    private Button addExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.activity_add_expense);

        getSupportActionBar().hide();

        amount = findViewById(R.id.amount_et);
        description = findViewById(R.id.desc_et);
        currency_dropdown = findViewById(R.id.currency_drop_down);
        category_dropdown = findViewById(R.id.category_drop_down);
        category = findViewById(R.id.category_tv);
        close_btn = findViewById(R.id.close_bt);
        currency = findViewById(R.id.currency_tv);
        addExpense = findViewById(R.id.add_expense_btn);

        currency_dropdown.setOnClickListener(view -> {
            showMenu(view,R.menu.currency_menu_items, currency_dropdown);
        });

        category_dropdown.setOnClickListener(view ->{
            showMenu(view,R.menu.category_menu_items, category_dropdown);
        });

        addExpense.setOnClickListener(view -> {
            onBackPressed();
        });

        close_btn.setOnClickListener(view -> {
            onBackPressed();
        });

        background = findViewById(R.id.background);

        if (savedInstanceState == null) {
            background.setVisibility(View.INVISIBLE);

            final ViewTreeObserver viewTreeObserver = background.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                });
            }

        }

    }

    private void showMenu(View view, @MenuRes int menuRes, ImageView btn) {
        PopupMenu popupMenu = new PopupMenu(AddExpenseActivity.this, btn);
        popupMenu.getMenuInflater().inflate(menuRes, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.food:
                        category.setText("Food");
                        return true;
                    case R.id.mis:
                        category.setText("Miscellaneous");
                        return true;
                    case R.id.med:
                        category.setText("Medical");
                        return true;
                    case R.id.usd:
                        currency.setText("USD");
                        return true;
                    case R.id.inr:
                        currency.setText("INR");
                        return true;
                    case R.id.gbp:
                        currency.setText("GBP");
                        return true;
                }

                return false;
            }
        });
        popupMenu.show();
    }

    private void circularRevealActivity() {
        int cx = background.getRight() - getDips(44);
        int cy = background.getBottom() - getDips(44);

        float finalRadius = Math.max(background.getWidth(), background.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                background,
                cx,
                cy,
                0,
                finalRadius);

        circularReveal.setDuration(500);
        background.setVisibility(View.VISIBLE);
        circularReveal.start();

    }

    private int getDips(int dps) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                resources.getDisplayMetrics());
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = background.getWidth() - getDips(44);
            int cy = background.getBottom() - getDips(44);

            float finalRadius = Math.max(background.getWidth(), background.getHeight());
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(background, cx, cy, finalRadius, 0);

            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    background.setVisibility(View.INVISIBLE);
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            circularReveal.setDuration(500);
            circularReveal.start();
        }
        else {
            super.onBackPressed();
        }
    }
}