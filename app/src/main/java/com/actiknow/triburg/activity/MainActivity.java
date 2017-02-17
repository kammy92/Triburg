package com.actiknow.triburg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.adapter.AllCategoryAdapter;
import com.actiknow.triburg.model.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvHomeList;
    List<Category> categoryList = new ArrayList<> ();
    AllCategoryAdapter adapter;

    Toolbar toolbar;

    CoordinatorLayout clMain;

    FloatingActionButton fabAddProduct;

//    ListView Listview;
//    static SlidingMenuLayout slidingmenu_layout;
//    String[] nearby_values = {"WOMEN", "MEN", "KIDS", "CURVE SIZES", "BARCODE", "SCAN", "STORES", "LOGIN", "WISHLIST", "COUNTRY AND LANGUAGE", "HELP"};


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
//        slidingmenu_layout = (SlidingMenuLayout) this.getLayoutInflater ().inflate (R.layout.activity_main, null);
//        setContentView (slidingmenu_layout);
        setContentView (R.layout.activity_main);
        initView ();
        initData ();
        initListener ();
        setUpNavigationDrawer ();
        initHomeItemList ();
    }

    private void initView () {
//        Listview = (ListView) findViewById (R.id.list);
        rvHomeList = (RecyclerView) findViewById (R.id.rvHomeList);
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
        fabAddProduct = (FloatingActionButton) findViewById (R.id.fabAddDesign);
    }

    private void initData () {
        categoryList.add (new Category (1, "WESTERN", "https://ae01.alicdn.com/kf/HTB1pM5YOpXXXXXRXpXXq6xXFXXXo/3-Piece-Suits-Men-British-font-b-Latest-b-font-font-b-Coat-b-font-font.jpg"));
        categoryList.add (new Category (2, "FORMAL", "http://tailormenow.com/wp-content/uploads/2016/06/11.jpg"));
        categoryList.add (new Category (3, "CASUAL", "http://g03.a.alicdn.com/kf/HTB1b5jZHVXXXXahXFXXq6xXFXXXG/New-Summer-Men-Collar-Polo-Shirt-Men-Clothing-Solid-Mens-Polo-Shirts-Business-Casual-Cotton-Sportswear.jpg"));
        categoryList.add (new Category (4, "KURTIS", "https://sc01.alicdn.com/kf/UT8.PyFXzpXXXagOFbXV/100800004/UT8.PyFXzpXXXagOFbXV.jpg"));
        categoryList.add (new Category (5, "ETHNIC", "https://media.licdn.com/mpr/mpr/shrinknp_800_800/AAEAAQAAAAAAAAKBAAAAJDZhM2IzZTZiLTkyMmEtNDgwNi1hZDBkLWY5ODBlNTRiMmRmYg.jpg"));
        categoryList.add (new Category (6, "COLLECTION", "http://mediaresources.idiva.com/media/content/2013/Apr/paul_shark_india1.jpg"));
        categoryList.add (new Category (7, "KIDS", "https://static.pexels.com/photos/35188/child-childrens-baby-children-s-medium.jpg"));
        categoryList.add (new Category (8, "ACCESSORIES", "http://picture-cdn.wheretoget.it/r9c6d7-i.jpg"));
//        Listview.setDividerHeight (2);
//        Listview.setClickable (true);
//        ListAdapter adapter = new ListAdapter (this, nearby_values);
//        Listview.setAdapter (adapter);

//        rvHomeList.setOnTouchListener (new View.OnTouchListener () {
//            @Override
//            public boolean onTouch (View view, MotionEvent motionEvent) {
//              if (slidingmenu_layout.isMenuVisible ()){
//                  toggleMenu ();
//              }
//                return false;
//            }
//        });
    }

    private void initListener(){
        fabAddProduct.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (MainActivity.this, AddProductActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_up, R.anim.stay);
            }
        });
    }
    private void initHomeItemList () {
        adapter = new AllCategoryAdapter (this, categoryList);
        rvHomeList.setAdapter (adapter);
        rvHomeList.setHasFixedSize (true);
        rvHomeList.setLayoutManager (new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false));
//        rvHomeList.addItemDecoration (new SimpleDividerItemDecoration (this));
    }

//    public static void toggleMenu () {
//        slidingmenu_layout.toggleMenu ();
//    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        ImageView ivNavigation = (ImageView) findViewById (R.id.ivNavigation);
        ImageView ivShoppingBag = (ImageView) findViewById (R.id.ivShoppingBag);
        TextView tvShoppingBagNumber = (TextView) findViewById (R.id.tvShoppingBagNumber);


        ivNavigation.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
//                Listview.smoothScrollToPosition (0);
//                toggleMenu ();
            }
        });

        tvShoppingBagNumber.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (MainActivity.this, ShoppingBagActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_up, R.anim.stay);
            }
        });
        ivShoppingBag.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (MainActivity.this, ShoppingBagActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_up, R.anim.stay);
            }
        });

        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (false);
            actionBar.setHomeButtonEnabled (false);
            actionBar.setDisplayShowTitleEnabled (false);
        } catch (Exception ignored) {
        }
    }
}
