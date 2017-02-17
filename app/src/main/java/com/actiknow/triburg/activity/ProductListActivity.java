package com.actiknow.triburg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.adapter.AllProductAdapter;
import com.actiknow.triburg.model.Product;
import com.actiknow.triburg.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 15-02-2017.
 */

public class ProductListActivity extends AppCompatActivity {
    CoordinatorLayout clMain;
    RecyclerView rvProductList;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Product> productList = new ArrayList<> ();
    AllProductAdapter adapter;
    Toolbar toolbar;

    int category_id;
    String category_name;

    RelativeLayout rlInit;
    TextView tvNoResult;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_product_list);
        getExtras ();
        initView ();
        initData ();
        initListener ();
        setUpNavigationDrawer ();
        getAllProducts (category_id);
    }

    private void initData () {
        Utils.setTypefaceToAllViews (this, tvNoResult);
        swipeRefreshLayout.setRefreshing (true);
        productList.clear ();
        adapter = new AllProductAdapter (this, productList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager (2, StaggeredGridLayoutManager.VERTICAL);
        rvProductList.setAdapter (adapter);
        rvProductList.setHasFixedSize (true);
        rvProductList.setLayoutManager (layoutManager);

    }

    private void getExtras () {
        Intent intent = getIntent ();
        category_id = intent.getIntExtra ("category_id", 0);
        category_name = intent.getStringExtra ("category_name");
    }

    private void initView () {
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
        rvProductList = (RecyclerView) findViewById (R.id.rvProductList);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById (R.id.swipe_refresh_layout);
        tvNoResult = (TextView) findViewById (R.id.tvNoResult);
        rlInit = (RelativeLayout) findViewById (R.id.rlInit);
    }

    private void initListener () {
        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh () {
                getAllProducts (category_id);
            }
        });
    }

    private void getAllProducts (int category_id) {
        productList.clear ();
        switch (category_id) {
            case 1:

                //western
                productList.add (new Product (1, 29, 0, false, false, "Cotton quilted jacket", "https://assets.abfrlcdn.com/img/app/product/1/174749-446475.jpg", "Western"));
                productList.add (new Product (2, 39, 0, false, false, "Girls wear", "http://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=55469079", "Western"));
                productList.add (new Product (3, 48, 0, false, false, "Nylon cotton trench", "http://images.express.com/is/image/expressfashion/0027_06113498_0432_f65?cache=on&wid=361&fmt=jpeg&qlt=75,1&resmode=sharp2&op_usm=1,1,5,0", "Western"));
                productList.add (new Product (4, 20, 10, true, true, "Adguy Dress", "https://s-media-cache-ak0.pinimg.com/736x/e5/b7/c6/e5b7c68425ac55bda88e52d52b8b7ce0.jpg", "Western"));
                productList.add (new Product (5, 50, 40, true, false, "Military style wool coat", "http://www.rahulsarees.com/wp-content/uploads/2015/12/menindowestern3-1.jpeg", "Western"));
                productList.add (new Product (6, 40, 39, true, false, "Simrella Gown", "http://www.wetellyouhow.com/wp-content/uploads/2013/01/indo-western-bridal-lehenga1.jpg", "Western"));
                productList.add (new Product (7, 50, 45, true, false, "Reversible trench coat", "http://images.asos-media.com/inv/media/3/4/3/0/6770343/brown/image1xl.jpg", "Western"));
                productList.add (new Product (8, 39, 0, false, false, "Flecked trench", "http://stylishmods.com/wp-content/uploads/2015/03/amazing-short-dress-forr-ladies.jpg", "Western"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
                break;
            case 2:

                //Formal
                productList.add (new Product (9, 29, 0, false, false, "Meeting Clothes", "http://images.menswearhouse.com/is/image/TMW/MW40_368F_17_VERA_WANG_CHARCOAL_FORMAL_MAIN?01AD=3lQ_VdLBlmS4r0yV8ptiXnC-pdCJwlKdklEPctEUHpULOppmP0ri_LA&01RI=AF221E8AD36276E&01NA=&$40Zoom$", "Formal"));
                productList.add (new Product (10, 19, 0, false, false, "Girls Formal Wear", "https://s-media-cache-ak0.pinimg.com/564x/f9/dc/7d/f9dc7d1f20fd08e95443819acc05af32.jpg", "Formal"));
                productList.add (new Product (11, 49, 39, true, false, "Formal Shirts", "https://s-media-cache-ak0.pinimg.com/564x/81/68/75/8168755496cde72a0ff56326965aa21b.jpg", "Formal"));
                productList.add (new Product (12, 40, 30, true, true, "Ladies Office Uniform", "https://ae01.alicdn.com/kf/HTB1naL_LXXXXXcQXpXXq6xXFXXX5/Formal-Female-Pantsuits-Women-Suits-with-2-Piece-Pant-and-Top-Sets-Ladies-Office-Uniform-Blouses.jpg", "Formal"));
                productList.add (new Product (13, 40, 30, true, false, "Highlander Formal", "https://s-media-cache-ak0.pinimg.com/736x/a9/03/fc/a903fc285ef181c62565e15c3950a92d.jpg", "Formal"));
                productList.add (new Product (14, 20, 10, true, false, "United Colors of Benetton", "http://g02.a.alicdn.com/kf/UT8UdiFXlVaXXagOFbX1/202476885/UT8UdiFXlVaXXagOFbX1.jpg", "Formal"));
                productList.add (new Product (15, 50, 40, true, false, "Formal shirt", "https://s-media-cache-ak0.pinimg.com/236x/bc/4f/c3/bc4fc383fab1473f30f44ceca9eb09f1.jpg", "Formal"));
                productList.add (new Product (16, 40, 0, false, false, "Flecked Formal clothes", "https://s-media-cache-ak0.pinimg.com/236x/ab/28/02/ab2802c924eee4d2f4add8aa248bf2c2.jpg", "Formal"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
                break;
            case 3:
                // Casual wear
                productList.add (new Product (25, 40, 0, false, false, "Lakshya Casual clothes", "https://s-media-cache-ak0.pinimg.com/236x/6f/de/a6/6fdea6102652822fa2d831e789bdb2c0.jpg", "Casual"));
                productList.add (new Product (26, 50, 0, false, false, "Tissot Watche", "https://s-media-cache-ak0.pinimg.com/736x/63/0f/8a/630f8a50d1f58fe91a4e09870bad11bf.jpg", "Casual"));
                productList.add (new Product (27, 40, 0, false, true, "Sunoite Sweaters", "http://menfash.us/wp-content/uploads/2014/01/Linen-shirts-with-sweaters.jpg", "Casual"));
                productList.add (new Product (28, 50, 39, true, false, "Lakshya Kurtis", "https://fashion360.pk/wp-content/uploads/2015/08/Bonanza-Garments-Women-Midsummer-Satrangi-Wear-Collection-2015-1-533x800.jpg", "Casual"));
                productList.add (new Product (29, 50, 40, true, false, "Military Capry", "https://s-media-cache-ak0.pinimg.com/736x/73/64/16/73641637d215ec6ba656bb8873f02893.jpg", "Casual"));
                productList.add (new Product (30, 40, 30, true, false, "Tweed Stylish Dress", "https://s-media-cache-ak0.pinimg.com/736x/45/4e/ce/454ece86cc04c7cf9cf66edeb5e5d343.jpg", "Casual"));
                productList.add (new Product (31, 50, 30, true, false, "Actiknow Pant", "https://s-media-cache-ak0.pinimg.com/736x/d4/7f/d1/d47fd1d1af76c3bdf68ec631e995c812.jpg", "Casual"));
                productList.add (new Product (32, 30, 0, false, false, "Casual Dress", "http://luxfashiontrends.com/wp-content/uploads/2012/07/casual-fashion-women-2012-ideas.jpg", "Casual"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
                break;
            // Kurtis
            case 4:
                productList.add (new Product (17, 50, 0, false, false, "Lakshya Simple Kurtis", "https://www.shreedesignersaree.com/image/large/magical-multi-color-net-kurtis.jpg", "Kurtis"));
                productList.add (new Product (18, 40, 0, false, false, "Simple Kurtis", "http://sc01.alicdn.com/kf/HTB1pUKuLXXXXXXwXXXXq6xXFXXXJ/Black-Latest-kurti-designs-for-girls-for.jpg", "Kurtis"));
                productList.add (new Product (19, 40, 0, false, false, "Simple Kurtis", "http://www.bigfashionworld.com/wp-content/uploads/2014/06/Utsav-Fashion-Modern-Western-Collection-of-Kurtis-2014-1.jpg", "Kurtis"));
                productList.add (new Product (20, 50, 30, true, false, "Simple Kurtis", "http://img1.exportersindia.com/product_images/bc-full/dir_51/1514556/girls-long-designer-kurtis-904250.jpg", "Kurtis"));
                productList.add (new Product (21, 40, 30, true, false, "Simple Kurtis", "http://fashionnama.com/wp-content/uploads/2015/06/Long-Kurtis-for-Women-Cotton-Design.jpg", "Kurtis"));
                productList.add (new Product (22, 40, 20, true, false, "Simple Kurtis", "http://pakistaniladies.com/wp-content/uploads/2015/11/Manish-Malhotra-Dresses-Designs-2016-Long-Salwar-Kameez-Suits-Winter-Collection.jpg", "Kurtis"));
                productList.add (new Product (23, 20, 20, true, true, "Simple Kurtis", "https://fashionnama.files.wordpress.com/2015/06/long-kurtis-for-women.jpg", "Kurtis"));
                productList.add (new Product (24, 50, 0, false, false, "Simple Kurtis", "http://www.styleglow.com/wp-content/uploads/2015/04/women-Kurta-designs-2015-3.jpg", "Kurtis"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
                break;
            //Ethnic

            case 5:
                productList.add (new Product (33, 50, 0, false, true, "Dulhan Dress", "http://www.high5store.com/500308-621090-prodlist2/xvandv-black-pink-fabulous-heavy-designer-anarkali-suits.jpg.pagespeed.ic.gltId78bPo.jpg", "Ethnic"));
                productList.add (new Product (34, 40, 0, false, false, "Reversible trench", "http://designersoutfits.com/wp-content/uploads/2016/10/7-captivating-pink-and-orange-lehnga-for-bridals-1.jpeg", "Ethnic"));
                productList.add (new Product (35, 50, 0, false, false, "Nylon trench", "https://appleblossommy.files.wordpress.com/2015/01/0002449_dia-mirza-red-anarkali-with-silk-jacket.jpeg", "Ethnic"));
                productList.add (new Product (36, 40, 30, true, false, "Wedding Dress", "http://assets1.mirraw.com/images/508952/bnd-003_zoom.jpg?1415794382", "Ethnic"));
                productList.add (new Product (37, 50, 40, true, true, "Wedding Dress", "https://static4.cilory.com/128417-large_default/heenari-series-coral-semi-stitched-plazzo-style-suit.jpg", "Ethnic"));
                productList.add (new Product (38, 50, 40, true, false, "Wedding Dress", "http://static.shopdost.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/0/0/0035.jpg.jpg", "Ethnic"));
                productList.add (new Product (39, 50, 40, true, false, "Wedding Dress", "http://www.high5store.com/500308-621090-prodlist2/xvandv-black-pink-fabulous-heavy-designer-anarkali-suits.jpg.pagespeed.ic.gltId78bPo.jpg", "Ethnic"));
                productList.add (new Product (40, 30, 0, false, false, "Wedding Dress", "https://s-media-cache-ak0.pinimg.com/736x/bc/57/f8/bc57f8e1d72283836270b3ff70473fbc.jpg", "Ethnic"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
                break;

            //Collection
            case 6:
                productList.add (new Product (33, 50, 0, false, false, "Cotton quilted jacket", "http://www.sulbha.com/102//prod1483787970.jpg", "Collection"));
                productList.add (new Product (34, 40, 0, false, false, "Handmade wool coat", "https://images.cbazaar.com/images/maroon-umbrella-long-choli-lehenga-ghscc22002-pl.jpg", "Collection"));
                productList.add (new Product (35, 30, 0, false, false, "Nylon trench", "http://www.jackets.co.in/wp-content/uploads/2015/10/Womens-Plus-Size-Winter-Jackets.jpg", "Collection"));
                productList.add (new Product (36, 50, 30, true, false, "Hooded wool coat", "https://s-media-cache-ak0.pinimg.com/originals/44/cc/8e/44cc8e455f00dae2ea33241395d5f829.jpg", "Collection"));
                productList.add (new Product (37, 40, 20, true, true, "Formal coat", "https://cdn.shopify.com/s/files/1/0803/3561/products/grey_susa_2179_large.jpg?v=1483652753", "Collection"));
                productList.add (new Product (38, 30, 20, true, false, "Van Huesen India", "https://assets.vanheusenindia.com/img/app/product/8/85787-254108-large.jpg", "Collection"));
                productList.add (new Product (39, 20, 10, true, false, "Reversible trench kurtis", "http://assets.myntassets.com/h_240,q_95,w_180/v1/images/style/properties/Ira-Soleil-Women-Black-Printed-Kurti_c86a54f87d73cf9b6516ea35a2f0c98c_images_mini.jpg", "Collection"));
                productList.add (new Product (40, 40, 0, false, false, "Flecked Kurtis", "http://stat.homeshop18.com/homeshop18/images/productImages/540/shonaya-womens-georgette-semi-stitched-kurta-purple-240X336-5X7-e7cb4b02d5944225ba7107817100b927.jpg", "Collection"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
                break;

            //Kids
            case 7:
                productList.add (new Product (41, 50, 0, false, false, "Nelly Stella dress", "http://cdn1-www.momtastic.com/assets/uploads/2015/03/Nelly-Stella-dress-with-cut-out-sleeves.jpg", "Kids"));
                productList.add (new Product (42, 40, 0, false, false, "Handmade Nelly  dress ", "http://4.bp.blogspot.com/-YoWZ1zwNAhU/UBogs9ywWKI/AAAAAAAAD5w/y7tfexnn8PM/s1600/fashion-for-kids-Junior-Gaultier-cool-dude-suit-for-boys-summer-2012-+childrens+fashion+dresses,+Kids+Fashion+Designer,+Kids+fashion+dresses,+trendy+children's+fashion+dresses+-emoo-fashion.blogspot.com+-16.jpg", "Kids"));
                productList.add (new Product (43, 40, 0, false, false, "Fashion summer", "https://ae01.alicdn.com/kf/HTB13He9IVXXXXXPXVXXq6xXFXXX6/2015-Boy-s-Clothing-Children-s-Clothes-Big-Kids-Wear-Tide-Cotton-Cardigan-Coat-Autumn-Clothes.jpg", "Kids"));
                productList.add (new Product (44, 20, 10, true, false, "Kids Style", "https://s-media-cache-ak0.pinimg.com/564x/bd/47/19/bd4719c4264e911966815ef729f88e59.jpg", "Kids"));
                productList.add (new Product (45, 35, 30, true, false, "Kids pompy look", "https://s-media-cache-ak0.pinimg.com/736x/5c/4f/ea/5c4feadf53268ea2b1a7398440140a15.jpg", "Kids"));
                productList.add (new Product (46, 40, 10, true, false, "Fashion look", "https://s-media-cache-ak0.pinimg.com/736x/0d/9e/74/0d9e7472f0bc55480e4ca1df764bd557.jpg", "Kids"));
                productList.add (new Product (47, 45, 150, true, false, "Reversible jeans", "https://ae01.alicdn.com/kf/HTB1VgOJJVXXXXcVXpXXq6xXFXXXz/2016-Summer-Fashion-Hot-Sale-Baby-Kids-Boys-font-b-Gir-b-font-Letter-Printedl-Tops.jpg", "Kids"));
                productList.add (new Product (48, 30, 0, false, false, "tracksuit kids hip hop", "https://www.solidrop.net/photo-10/children-s-sports-suit-boy-casual-tracksuit-kids-hip-hop-dancewear-boys-summer-clothes-cool-fashion-black-white-brand-2016-new.jpg", "Kids"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
                break;

            //Accessories
            case 8:
                productList.add (new Product (41, 40, 0, false, false, "Nelly Stella Necklaces", "http://www.wholesalevoguedresses.com/images/jewelry/necklaces/jn148.jpg", "Accessories"));
                productList.add (new Product (42, 50, 0, false, false, "Handmade Nelly ", "http://modernfashionblog.com/wp-content/uploads/2014/07/12-Modern-Head-Chain-Pieces-For-Girls-Women-2014-Hair-Accessories-8.jpg", "Accessories"));
                productList.add (new Product (43, 50, 0, false, false, "Fashion Handbag", "http://umekobeautyfashion.com/wp-content/uploads/2014/11/Fall-2014-Winter-2015-Handbag-Trends-20.jpg", "Accessories"));
                productList.add (new Product (44, 40, 30, true, false, "Style Handbag", "http://www.zquotes.net/wp-content/uploads/2014/10/Celine-Tote-Handbags-2015.jpg", "Accessories"));
                productList.add (new Product (45, 40, 30, true, false, "Pompy Belt", "https://dtpmhvbsmffsz.cloudfront.net/posts/2015/08/08/55c67a924973b6422800697b/s_55c67a924973b6422800697c.jpg", "Accessories"));
                productList.add (new Product (46, 50, 40, true, false, "Fashion look", "https://guideimg.alibaba.com/images/shop/79/09/24/9/fashion-za-vintage-crystal-necklace-for-women-2014-necklaces-pendants-statement-necklace-gold-chain-accessories-jewelry_1636999.jpg", "Accessories"));
                productList.add (new Product (47, 50, 40, true, false, "Reversible Rings", "http://picture-cdn.wheretoget.it/r9c6d7-i.jpg", "Accessories"));
                productList.add (new Product (48, 40, 0, false, false, "Footwear", "http://www.stylesgap.com/wp-content/uploads/2016/02/Stylo-Shoes-Fancy-Wedding-Footwear-Collection-2016-2017-35.jpg", "Accessories"));
                adapter.notifyDataSetChanged ();
                swipeRefreshLayout.setRefreshing (false);
                break;
        }
    }


    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        setSupportActionBar (toolbar);
        TextView tvCategoryName = (TextView) toolbar.findViewById (R.id.tvCategoryName);
        ImageView ivShoppingBag = (ImageView) toolbar.findViewById (R.id.ivShoppingBag);
        ImageView ivBack = (ImageView) toolbar.findViewById (R.id.ivBack);
        TextView tvShoppingBagNumber = (TextView) findViewById (R.id.tvShoppingBagNumber);

        tvCategoryName.setText (category_name);

        tvShoppingBagNumber.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (ProductListActivity.this, ShoppingBagActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_up, R.anim.stay);
            }
        });
        ivShoppingBag.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (ProductListActivity.this, ShoppingBagActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_up, R.anim.stay);
            }
        });

        ivBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
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

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
