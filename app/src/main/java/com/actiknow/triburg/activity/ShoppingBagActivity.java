package com.actiknow.triburg.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.adapter.ShoppingBagProductAdapter;
import com.actiknow.triburg.model.ShoppingBagProduct;
import com.actiknow.triburg.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBagActivity extends AppCompatActivity {
    RecyclerView rvCartItemList;
    List<ShoppingBagProduct> shoppingBagProductList = new ArrayList<> ();
    ShoppingBagProductAdapter adapter;
    TextView tvTotalPrice;

    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_shopping_bag);
        initView ();
        initData ();
        initAdapter ();
        setUpNavigationDrawer ();
    }

    private void initView () {
        tvTotalPrice = (TextView) findViewById (R.id.tvTotalAmount);
        rvCartItemList = (RecyclerView) findViewById (R.id.rvCartItemList);

    }

    private void initData () {
        Utils.setTypefaceToAllViews (this, tvTotalPrice);
        shoppingBagProductList.add (new ShoppingBagProduct (1,
                "http://g01.a.alicdn.com/kf/HTB18WcyIVXXXXcPaXXXq6xXFXXXy/Leisure-Styles-Spring-V-Neck-Casual-T-shirts-Men-Bodybuilding-TShirts-Slim-Fit-Undershirt-Solid-Cotton.jpg",
                "V Neck Casual Shirt",
                "REF 83080290 - NORIEGA7",
                "M",
                "29",
                "#000000"));
    }

    private void initAdapter () {
        adapter= new ShoppingBagProductAdapter (this, shoppingBagProductList);
        rvCartItemList.setAdapter (adapter);
        rvCartItemList.setHasFixedSize (true);
        rvCartItemList.setLayoutManager (new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false));
        rvCartItemList.setItemAnimator (new DefaultItemAnimator ());
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.stay, R.anim.slide_out_down);
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        setSupportActionBar (toolbar);
        ImageView ivBack = (ImageView) toolbar.findViewById (R.id.ivBack);
        TextView tvEditShoppingBag = (TextView) findViewById (R.id.tvEditShoppingBag);

        ivBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                finish ();
                overridePendingTransition (R.anim.stay, R.anim.slide_out_down);
            }
        });

        ActionBar actionBar = getSupportActionBar ();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (false);
            actionBar.setHomeButtonEnabled (false);
            actionBar.setDisplayShowTitleEnabled (false);
        } catch (Exception ignored) {
        }
    }

}


