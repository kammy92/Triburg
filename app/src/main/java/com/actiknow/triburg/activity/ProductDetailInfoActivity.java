package com.actiknow.triburg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.utils.Utils;

/**
 * Created by l on 16/02/2017.
 */

public class ProductDetailInfoActivity extends AppCompatActivity {

    int product_id;
    String product_name = "";
    String product_description = "";

    TextView tvShare;
    LinearLayout llShare;

    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_product_detail_info);
        getExtras ();
        initView ();
        initData();
        initListener ();
        setUpNavigationDrawer ();
    }

    private void initView() {
        tvShare = (TextView) findViewById (R.id.tvShare);
        llShare =  (LinearLayout) findViewById (R.id.llShare);
    }

    private void initData(){
        Utils.setTypefaceToAllViews (this, tvShare);
    }

    private void initListener(){
        tvShare.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                if (llShare.getVisibility () == View.VISIBLE){
                    llShare.setVisibility (View.GONE);
                } else {
                    llShare.setVisibility (View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.fade_in, R.anim.fade_out);
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        setSupportActionBar (toolbar);
        TextView tvProductName = (TextView) toolbar.findViewById (R.id.tvProductName);
        TextView tvProductDescription = (TextView) toolbar.findViewById (R.id.tvProductDescription);
        ImageView ivBack = (ImageView) toolbar.findViewById (R.id.ivBack);

        tvProductName.setText (product_name);
        if (product_description.length () > 0) {
            tvProductDescription.setText (product_description);
            tvProductDescription.setVisibility (View.VISIBLE);
        } else {
            tvProductDescription.setVisibility (View.GONE);
        }

        ivBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                finish ();
                overridePendingTransition (R.anim.fade_in, R.anim.fade_out);
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

    private void getExtras () {
        Intent intent = getIntent ();
        product_id = intent.getIntExtra ("product_id", 0);
        product_name = intent.getStringExtra ("product_name");
        product_description = intent.getStringExtra ("product_description");
    }
}
