package com.actiknow.triburg.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.model.ProductDetail;
import com.actiknow.triburg.utils.Utils;
import com.actiknow.triburg.utils.VerticalViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName ();
    private VerticalViewPager mVerticalViewPager;

    int product_id;
    String product_name = "";
    String product_description = "";
    String product_category = "";

    Toolbar toolbar;

    LinearLayout llSize;
    TextView tvSize;
    TextView tvInfo;

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener () {
        @Override
        public void onPageSelected (int position) {
        }

        @Override
        public void onPageScrolled (int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged (int arg0) {
        }
    };

    ProductDetail productDetail = new ProductDetail ();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_product_detail);
        getExtras ();
        initView ();
        initData ();
        initListener ();
        setUpNavigationDrawer ();
    }

    private void initView () {
        llSize = (LinearLayout) findViewById (R.id.llSize);
        tvSize = (TextView) findViewById (R.id.tvSize);
        tvInfo = (TextView) findViewById (R.id.tvInfo);
    }

    private void initData(){
        Utils.setTypefaceToAllViews (this, tvSize);
        productDetail.setId (product_id);
        productDetail.setName (product_name);
        ArrayList<String> productImages = new ArrayList<> ();

        switch (product_category) {
            case "Western":
                productImages.add ("https://assets.abfrlcdn.com/img/app/product/1/174749-446475.jpg");
                productImages.add ("http://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=55469079");
                productImages.add ("http://images.express.com/is/image/expressfashion/0027_06113498_0432_f65?cache=on&wid=361&fmt=jpeg&qlt=75,1&resmode=sharp2&op_usm=1,1,5,0");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/e5/b7/c6/e5b7c68425ac55bda88e52d52b8b7ce0.jpg");
                productImages.add ("http://www.rahulsarees.com/wp-content/uploads/2015/12/menindowestern3-1.jpeg");
                productImages.add ("http://www.wetellyouhow.com/wp-content/uploads/2013/01/indo-western-bridal-lehenga1.jpg");
                productImages.add ("http://images.asos-media.com/inv/media/3/4/3/0/6770343/brown/image1xl.jpg");
                productImages.add ("http://stylishmods.com/wp-content/uploads/2015/03/amazing-short-dress-forr-ladies.jpg");
                break;
            case "Formal":
                productImages.add ("http://images.menswearhouse.com/is/image/TMW/MW40_368F_17_VERA_WANG_CHARCOAL_FORMAL_MAIN?01AD=3lQ_VdLBlmS4r0yV8ptiXnC-pdCJwlKdklEPctEUHpULOppmP0ri_LA&01RI=AF221E8AD36276E&01NA=&$40Zoom$");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/564x/f9/dc/7d/f9dc7d1f20fd08e95443819acc05af32.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/564x/81/68/75/8168755496cde72a0ff56326965aa21b.jpg");
                productImages.add ("https://ae01.alicdn.com/kf/HTB1naL_LXXXXXcQXpXXq6xXFXXX5/Formal-Female-Pantsuits-Women-Suits-with-2-Piece-Pant-and-Top-Sets-Ladies-Office-Uniform-Blouses.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/a9/03/fc/a903fc285ef181c62565e15c3950a92d.jpg");
                productImages.add ("http://g02.a.alicdn.com/kf/UT8UdiFXlVaXXagOFbX1/202476885/UT8UdiFXlVaXXagOFbX1.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/236x/bc/4f/c3/bc4fc383fab1473f30f44ceca9eb09f1.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/236x/ab/28/02/ab2802c924eee4d2f4add8aa248bf2c2.jpg");
                break;
            case "Casual":
                productImages.add ("https://s-media-cache-ak0.pinimg.com/236x/6f/de/a6/6fdea6102652822fa2d831e789bdb2c0.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/63/0f/8a/630f8a50d1f58fe91a4e09870bad11bf.jpg");
                productImages.add ("http://menfash.us/wp-content/uploads/2014/01/Linen-shirts-with-sweaters.jpg");
                productImages.add ("https://fashion360.pk/wp-content/uploads/2015/08/Bonanza-Garments-Women-Midsummer-Satrangi-Wear-Collection-2015-1-533x800.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/73/64/16/73641637d215ec6ba656bb8873f02893.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/45/4e/ce/454ece86cc04c7cf9cf66edeb5e5d343.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/d4/7f/d1/d47fd1d1af76c3bdf68ec631e995c812.jpg");
                productImages.add ("http://luxfashiontrends.com/wp-content/uploads/2012/07/casual-fashion-women-2012-ideas.jpg");
                break;
            case "Kurtis":
                productImages.add ("https://www.shreedesignersaree.com/image/large/magical-multi-color-net-kurtis.jpg");
                productImages.add ("http://sc01.alicdn.com/kf/HTB1pUKuLXXXXXXwXXXXq6xXFXXXJ/Black-Latest-kurti-designs-for-girls-for.jpg");
                productImages.add ("http://www.bigfashionworld.com/wp-content/uploads/2014/06/Utsav-Fashion-Modern-Western-Collection-of-Kurtis-2014-1.jpg");
                productImages.add ("http://img1.exportersindia.com/product_images/bc-full/dir_51/1514556/girls-long-designer-kurtis-904250.jpg");
                productImages.add ("http://fashionnama.com/wp-content/uploads/2015/06/Long-Kurtis-for-Women-Cotton-Design.jpg");
                productImages.add ("http://pakistaniladies.com/wp-content/uploads/2015/11/Manish-Malhotra-Dresses-Designs-2016-Long-Salwar-Kameez-Suits-Winter-Collection.jpg");
                productImages.add ("https://fashionnama.files.wordpress.com/2015/06/long-kurtis-for-women.jpg");
                productImages.add ("http://www.styleglow.com/wp-content/uploads/2015/04/women-Kurta-designs-2015-3.jpg");
                break;
            case "Ethnic":
                productImages.add ("http://www.high5store.com/500308-621090-prodlist2/xvandv-black-pink-fabulous-heavy-designer-anarkali-suits.jpg.pagespeed.ic.gltId78bPo.jpg");
                productImages.add ("http://designersoutfits.com/wp-content/uploads/2016/10/7-captivating-pink-and-orange-lehnga-for-bridals-1.jpeg");
                productImages.add ("https://appleblossommy.files.wordpress.com/2015/01/0002449_dia-mirza-red-anarkali-with-silk-jacket.jpeg");
                productImages.add ("http://assets1.mirraw.com/images/508952/bnd-003_zoom.jpg?1415794382");
                productImages.add ("https://static4.cilory.com/128417-large_default/heenari-series-coral-semi-stitched-plazzo-style-suit.jpg");
                productImages.add ("http://static.shopdost.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/0/0/0035.jpg.jpg");
                productImages.add ("http://www.high5store.com/500308-621090-prodlist2/xvandv-black-pink-fabulous-heavy-designer-anarkali-suits.jpg.pagespeed.ic.gltId78bPo.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/bc/57/f8/bc57f8e1d72283836270b3ff70473fbc.jpg");
                break;
            case "Collection":
                productImages.add ("http://www.sulbha.com/102//prod1483787970.jpg");
                productImages.add ("https://images.cbazaar.com/images/maroon-umbrella-long-choli-lehenga-ghscc22002-pl.jpg");
                productImages.add ("http://www.jackets.co.in/wp-content/uploads/2015/10/Womens-Plus-Size-Winter-Jackets.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/originals/44/cc/8e/44cc8e455f00dae2ea33241395d5f829.jpg");
                productImages.add ("https://cdn.shopify.com/s/files/1/0803/3561/products/grey_susa_2179_large.jpg?v=1483652753");
                productImages.add ("https://assets.vanheusenindia.com/img/app/product/8/85787-254108-large.jpg");
                productImages.add ("http://assets.myntassets.com/h_240,q_95,w_180/v1/images/style/properties/Ira-Soleil-Women-Black-Printed-Kurti_c86a54f87d73cf9b6516ea35a2f0c98c_images_mini.jpg");
                productImages.add ("http://stat.homeshop18.com/homeshop18/images/productImages/540/shonaya-womens-georgette-semi-stitched-kurta-purple-240X336-5X7-e7cb4b02d5944225ba7107817100b927.jpg");
                break;
            case "Kids":
                productImages.add ("http://cdn1-www.momtastic.com/assets/uploads/2015/03/Nelly-Stella-dress-with-cut-out-sleeves.jpg");
                productImages.add ("http://4.bp.blogspot.com/-YoWZ1zwNAhU/UBogs9ywWKI/AAAAAAAAD5w/y7tfexnn8PM/s1600/fashion-for-kids-Junior-Gaultier-cool-dude-suit-for-boys-summer-2012-+childrens+fashion+dresses,+Kids+Fashion+Designer,+Kids+fashion+dresses,+trendy+children's+fashion+dresses+-emoo-fashion.blogspot.com+-16.jpg");
                productImages.add ("https://ae01.alicdn.com/kf/HTB13He9IVXXXXXPXVXXq6xXFXXX6/2015-Boy-s-Clothing-Children-s-Clothes-Big-Kids-Wear-Tide-Cotton-Cardigan-Coat-Autumn-Clothes.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/564x/bd/47/19/bd4719c4264e911966815ef729f88e59.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/5c/4f/ea/5c4feadf53268ea2b1a7398440140a15.jpg");
                productImages.add ("https://s-media-cache-ak0.pinimg.com/736x/0d/9e/74/0d9e7472f0bc55480e4ca1df764bd557.jpg");
                productImages.add ("https://ae01.alicdn.com/kf/HTB1VgOJJVXXXXcVXpXXq6xXFXXXz/2016-Summer-Fashion-Hot-Sale-Baby-Kids-Boys-font-b-Gir-b-font-Letter-Printedl-Tops.jpg");
                productImages.add ("https://www.solidrop.net/photo-10/children-s-sports-suit-boy-casual-tracksuit-kids-hip-hop-dancewear-boys-summer-clothes-cool-fashion-black-white-brand-2016-new.jpg");
                break;
            case "Accessories":
                productImages.add ("http://www.wholesalevoguedresses.com/images/jewelry/necklaces/jn148.jpg");
                productImages.add ("http://modernfashionblog.com/wp-content/uploads/2014/07/12-Modern-Head-Chain-Pieces-For-Girls-Women-2014-Hair-Accessories-8.jpg");
                productImages.add ("http://umekobeautyfashion.com/wp-content/uploads/2014/11/Fall-2014-Winter-2015-Handbag-Trends-20.jpg");
                productImages.add ("http://www.zquotes.net/wp-content/uploads/2014/10/Celine-Tote-Handbags-2015.jpg");
                productImages.add ("https://dtpmhvbsmffsz.cloudfront.net/posts/2015/08/08/55c67a924973b6422800697b/s_55c67a924973b6422800697c.jpg");
                productImages.add ("https://guideimg.alibaba.com/images/shop/79/09/24/9/fashion-za-vintage-crystal-necklace-for-women-2014-necklaces-pendants-statement-necklace-gold-chain-accessories-jewelry_1636999.jpg");
                productImages.add ("http://picture-cdn.wheretoget.it/r9c6d7-i.jpg");
                productImages.add ("http://www.stylesgap.com/wp-content/uploads/2016/02/Stylo-Shoes-Fancy-Wedding-Footwear-Collection-2016-2017-35.jpg");
                break;
        }
        productDetail.setImages (productImages);

        mVerticalViewPager = (VerticalViewPager) findViewById (R.id.view_pager_vertical);
        mVerticalViewPager.setPageTransformer (true, new VerticalPageTransformer ());
        mVerticalViewPager.setAdapter (new VerticalPageAdapter (this));
        mVerticalViewPager.addOnPageChangeListener (viewPagerPageChangeListener);

        PageIndicatorView pageIndicatorView = (PageIndicatorView) findViewById (R.id.pageIndicatorView);
        pageIndicatorView.setViewPager (mVerticalViewPager);
        pageIndicatorView.setInteractiveAnimation (true);

        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation (this, R.anim.myanim);
        ranim.setFillAfter (true); //For the textview to remain at the same place after the rotation
        pageIndicatorView.setAnimation (ranim);

//        float marginTop = 200 / - 1.0f;
//        pageIndicatorView.setTranslationY (marginTop);
    }

    private void initListener(){
        tvSize.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                if (llSize.getVisibility () == View.VISIBLE) {
                    llSize.setVisibility (View.GONE);
                } else {
                    llSize.setVisibility (View.VISIBLE);
                }
            }
        });

        tvInfo.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (ProductDetailActivity.this, ProductDetailInfoActivity.class);
                intent.putExtra ("product_id", product_id);
                intent.putExtra ("product_name", product_name);
                intent.putExtra ("product_description", "REF 83923453-RAT12");
                startActivity (intent);
                overridePendingTransition (R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage (View page, float position) {
            page.setTranslationX (page.getWidth () * - position);
            page.setTranslationY (page.getHeight () * position);
        }
    }

    private class VerticalPageAdapter extends PagerAdapter {
        Context context;

        public VerticalPageAdapter (Context context) {
            this.context = context;
        }

        @Override
        public int getCount () {
            return productDetail.getImages ().size ();
        }

        @Override
        public boolean isViewFromObject (View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem (ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from (context);
            View view = inflater.inflate (R.layout.viewpager_content, container, false);
            ImageView imageview = (ImageView) view.findViewById (R.id.page_position);
            final ProgressBar progressBar = (ProgressBar) view.findViewById (R.id.progressBar);
            Glide.with (ProductDetailActivity.this)
                    .load (productDetail.getImages ().get (position))
                    .listener (new RequestListener<String, GlideDrawable> () {
                        @Override
                        public boolean onException (Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility (View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady (GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility (View.GONE);
                            return false;
                        }
                    })
                    .into (imageview);
            container.addView (view);
            return view;
        }

        @Override
        public void destroyItem (ViewGroup container, int position, Object object) {
            container.removeView ((View) object);
        }
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        setSupportActionBar (toolbar);
        TextView tvProductName = (TextView) toolbar.findViewById (R.id.tvProductName);
        TextView tvProductDescription = (TextView) toolbar.findViewById (R.id.tvProductDescription);
        ImageView ivShoppingBag = (ImageView) toolbar.findViewById (R.id.ivShoppingBag);
        ImageView ivBack = (ImageView) toolbar.findViewById (R.id.ivBack);
        TextView tvShoppingBagNumber = (TextView) findViewById (R.id.tvShoppingBagNumber);

        tvProductName.setText (product_name);
        if (product_description.length ()>0){
            tvProductDescription.setText (product_description);
            tvProductDescription.setVisibility (View.VISIBLE);
        } else{
            tvProductDescription.setVisibility (View.GONE);
        }

        tvShoppingBagNumber.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (ProductDetailActivity.this, ShoppingBagActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_up, R.anim.stay);
            }
        });
        ivShoppingBag.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (ProductDetailActivity.this, ShoppingBagActivity.class);
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

    private void getExtras () {
        Intent intent = getIntent ();
        product_id = intent.getIntExtra ("product_id", 0);
        product_name = intent.getStringExtra ("product_name");
        product_category = intent.getStringExtra ("product_category");
        product_description = intent.getStringExtra ("product_description");
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
