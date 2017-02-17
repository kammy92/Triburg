package com.actiknow.triburg.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.adapter.UploadImageAdapter;
import com.actiknow.triburg.model.UploadImage;
import com.actiknow.triburg.utils.Constants;
import com.actiknow.triburg.utils.TypefaceSpan;
import com.actiknow.triburg.utils.Utils;
import com.rd.PageIndicatorView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.actiknow.triburg.R.id.ivImage;


/**
 * Created by actiknow on 2/16/17.
 */

public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextInputLayout input_layout_cloth, input_layout_type, input_layout_price, input_layout_comment;
    EditText etPrice, etComment;
    TextView tvSizeS, tvSizeM, tvSizeL, tvSizeXL;
    AutoCompleteTextView etClothType, etCategory;
    private ArrayAdapter<String> ClothTypeadapter;
    private ArrayAdapter<String> Categoryadapter;
    String ClothType[] = {"Silk", "Cotton", "Denim", "Linen", "Georgette", "Chiffon", "Velvet", "Satin", "Wool", "Polyester", "Rayon", "Jute", "Terrycloth", "Brocade", "Mesh", "Gabardine"};
    String Category[] = {"Western", "Formal", "Casual", "Kurtis", "Ethnic", "Collection", "Kids", "Accessories"};


    Toolbar toolbar;
    LinearLayout llImages;

    ArrayList<UploadImage> uploadImageList = new ArrayList<> ();
    UploadImageAdapter adapter;

    RecyclerView rvImages;
    FloatingActionButton fabAddImage;


    private ViewPager viewPager;
    ImagesPagerAdapter pagerAdapter;
    PageIndicatorView pageIndicatorView;

    public static int PERMISSION_REQUEST_CODE = 11;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_product);
        initView ();
        initData ();
        initListener ();
        initAdapter ();
        checkPermissions ();
        setUpNavigationDrawer ();
    }

    private void initAdapter () {
        ClothTypeadapter = new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line, ClothType);
        etClothType.setThreshold (1);
        etClothType.setAdapter (ClothTypeadapter);
        etClothType.setOnItemSelectedListener (this);

        Categoryadapter = new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line, Category);
        etCategory.setThreshold (1);
        etCategory.setAdapter (Categoryadapter);
        etCategory.setOnItemSelectedListener (this);

        adapter = new UploadImageAdapter (this, uploadImageList);
        rvImages.setAdapter (adapter);
        rvImages.setHasFixedSize (true);
        rvImages.setLayoutManager (new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL, false));

//        pagerAdapter = new ImagesPagerAdapter (this, uploadImageList);
//        viewPager.setAdapter (pagerAdapter);
//        pageIndicatorView.setViewPager (viewPager);
//        pageIndicatorView.setInteractiveAnimation (true);
    }

    private void initView () {
        input_layout_cloth = (TextInputLayout) findViewById (R.id.input_layout_fabric);
        input_layout_type = (TextInputLayout) findViewById (R.id.input_layout_category);
        input_layout_price = (TextInputLayout) findViewById (R.id.input_layout_price);
        input_layout_comment = (TextInputLayout) findViewById (R.id.input_layout_description);

        etClothType = (AutoCompleteTextView) findViewById (R.id.etFabric);
        etCategory = (AutoCompleteTextView) findViewById (R.id.etCategory);
        etPrice = (EditText) findViewById (R.id.etPrice);
        etComment = (EditText) findViewById (R.id.etDescription);
        tvSizeS = (TextView) findViewById (R.id.tvSizeS);
        tvSizeM = (TextView) findViewById (R.id.tvSizeM);
        tvSizeL = (TextView) findViewById (R.id.tvSizeL);
        tvSizeXL = (TextView) findViewById (R.id.tvSizeXL);

        rvImages = (RecyclerView) findViewById (R.id.rvImages);

        viewPager = (ViewPager) findViewById (R.id.vpImages);
        pageIndicatorView = (PageIndicatorView) findViewById (R.id.pageIndicatorView);

        fabAddImage = (FloatingActionButton) findViewById (R.id.fabAddImage);
        Utils.hideSoftKeyboard (this);
    }

    private void initData () {
        Utils.setTypefaceToAllViews (this, tvSizeL);
        SpannableString s = new SpannableString ("FABRIC  Cotton, Silk, ...");
        s.setSpan (new TypefaceSpan (AddProductActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        input_layout_cloth.setHint (s);
        SpannableString s2 = new SpannableString ("CATEGORY  Kurtis, Ethnic, ...");
        s2.setSpan (new TypefaceSpan (AddProductActivity.this, Constants.font_name), 0, s2.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        input_layout_type.setHint (s2);
        SpannableString s3 = new SpannableString ("MIN QTY");
        s3.setSpan (new TypefaceSpan (AddProductActivity.this, Constants.font_name), 0, s3.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        input_layout_price.setHint (s3);
        SpannableString s4 = new SpannableString ("DESCRIPTION");
        s4.setSpan (new TypefaceSpan (AddProductActivity.this, Constants.font_name), 0, s4.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        input_layout_comment.setHint (s4);
    }

    private void initListener () {
        tvSizeS.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                tvSizeS.setBackgroundResource (R.drawable.circle_black);
                tvSizeS.setTextColor (getResources ().getColor (R.color.text_color_white));
                tvSizeM.setBackgroundResource (R.drawable.circle);
                tvSizeM.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeL.setBackgroundResource (R.drawable.circle);
                tvSizeL.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeXL.setBackgroundResource (R.drawable.circle);
                tvSizeXL.setTextColor (getResources ().getColor (R.color.text_color_black));
            }
        });
        tvSizeM.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                tvSizeS.setBackgroundResource (R.drawable.circle);
                tvSizeS.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeM.setBackgroundResource (R.drawable.circle_black);
                tvSizeM.setTextColor (getResources ().getColor (R.color.text_color_white));
                tvSizeL.setBackgroundResource (R.drawable.circle);
                tvSizeL.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeXL.setBackgroundResource (R.drawable.circle);
                tvSizeXL.setTextColor (getResources ().getColor (R.color.text_color_black));
            }
        });
        tvSizeL.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                tvSizeS.setBackgroundResource (R.drawable.circle);
                tvSizeS.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeM.setBackgroundResource (R.drawable.circle);
                tvSizeM.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeL.setBackgroundResource (R.drawable.circle_black);
                tvSizeL.setTextColor (getResources ().getColor (R.color.text_color_white));
                tvSizeXL.setBackgroundResource (R.drawable.circle);
                tvSizeXL.setTextColor (getResources ().getColor (R.color.text_color_black));
            }
        });
        tvSizeXL.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                tvSizeS.setBackgroundResource (R.drawable.circle);
                tvSizeS.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeM.setBackgroundResource (R.drawable.circle);
                tvSizeM.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeL.setBackgroundResource (R.drawable.circle);
                tvSizeL.setTextColor (getResources ().getColor (R.color.text_color_black));
                tvSizeXL.setBackgroundResource (R.drawable.circle_black);
                tvSizeXL.setTextColor (getResources ().getColor (R.color.text_color_white));
            }
        });

        fabAddImage.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                selectImage ();
            }
        });
    }

    @Override
    public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected (AdapterView<?> adapterView) {
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.stay, R.anim.slide_out_down);
    }

    private void selectImage () {
        final CharSequence[] options = {"From Camera", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder (AddProductActivity.this);
        builder.setItems (options, new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int item) {
                if (options[item].equals ("From Camera")) {
                    Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File (android.os.Environment.getExternalStorageDirectory (), "temp.jpg");
                    intent.putExtra (MediaStore.EXTRA_OUTPUT, Uri.fromFile (f));
                    startActivityForResult (intent, 1);
                } else if (options[item].equals ("From Gallery")) {
                    Intent intent = new Intent (Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult (intent, 2);

                }
            }
        });
        builder.show ();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File (Environment.getExternalStorageDirectory ().toString ());
                for (File temp : f.listFiles ()) {
                    if (temp.getName ().equals ("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options ();
                    bitmap = BitmapFactory.decodeFile (f.getAbsolutePath (), bitmapOptions);

//                    uploadImageList.add (new UploadImage (Utils.compressBitmap (bitmap, this)));
                    uploadImageList.add (new UploadImage (bitmap));
                    adapter.notifyDataSetChanged ();

//                    pagerAdapter = new ImagesPagerAdapter (this, uploadImageList);
//                    viewPager.setAdapter (pagerAdapter);
//                    pageIndicatorView.setViewPager (viewPager);
//                    pageIndicatorView.setInteractiveAnimation (true);


//                    ImageView image = new ImageView (this);
//                    LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
//                    image.setLayoutParams (vp);
//                    image.setScaleType (ImageView.ScaleType.CENTER_CROP);
//                    image.setMaxHeight (50);
//                    image.setMaxWidth (50);
//                    image.setImageBitmap (bitmap);//Utils.compressBitmap (bitmap, this));
//                    llImages.addView (image);

                    String path = android.os.Environment
                            .getExternalStorageDirectory ()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete ();
                    OutputStream outFile = null;
                    File file = new File (path, String.valueOf (System.currentTimeMillis ()) + ".jpg");
                    try {
                        outFile = new FileOutputStream (file);
                        bitmap.compress (Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush ();
                        outFile.close ();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace ();
                    } catch (IOException e) {
                        e.printStackTrace ();
                    } catch (Exception e) {
                        e.printStackTrace ();
                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData ();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver ().query (selectedImage, filePath, null, null, null);
                c.moveToFirst ();
                int columnIndex = c.getColumnIndex (filePath[0]);
                String picturePath = c.getString (columnIndex);
                c.close ();
                Bitmap thumbnail = (BitmapFactory.decodeFile (picturePath));
                Log.w ("gallery path", picturePath + "");

//                uploadImageList.add (new UploadImage (Utils.compressBitmap (thumbnail, this)));
                uploadImageList.add (new UploadImage (thumbnail));
                adapter.notifyDataSetChanged ();

//                pagerAdapter = new ImagesPagerAdapter (this, uploadImageList);
//                viewPager.setAdapter (pagerAdapter);
//                pageIndicatorView.setViewPager (viewPager);
//                pageIndicatorView.setInteractiveAnimation (true);


//                ImageView image = new ImageView (this);
//                LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
//                image.setLayoutParams (vp);
//                image.setScaleType (ImageView.ScaleType.FIT_CENTER);
//                image.setMaxHeight (50);
//                image.setMaxWidth (50);
//                image.setImageBitmap (thumbnail);//Utils.compressBitmap (thumbnail, this));
//                llImages.addView (image);
            }
        }
    }

    public void checkPermissions () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission (Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || checkSelfPermission (WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions (new String[] {Manifest.permission.CAMERA, Manifest.permission.INTERNET, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    @TargetApi(23)
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = shouldShowRequestPermissionRationale (permission);
                    if (! showRationale) {
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder (AddProductActivity.this);
                        builder.setMessage ("Permission are required please enable them on the App Setting page")
                                .setCancelable (false)
                                .setPositiveButton ("OK", new DialogInterface.OnClickListener () {
                                    public void onClick (DialogInterface dialog, int id) {
                                        dialog.dismiss ();
                                        Intent intent = new Intent (Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts ("package", getPackageName (), null));
                                        startActivity (intent);
                                    }
                                });
                        android.support.v7.app.AlertDialog alert = builder.create ();
                        alert.show ();
                        // user denied flagging NEVER ASK AGAIN
                        // you can either enable some fall back,
                        // disable features of your app
                        // or open another dialog explaining
                        // again the permission and directing to
                        // the app setting
                    } else if (Manifest.permission.CAMERA.equals (permission)) {
//                        Utils.showToast (this, "Camera Permission is required");
//                        showRationale (permission, R.string.permission_denied_contacts);
                        // user denied WITHOUT never ask again
                        // this is a good place to explain the user
                        // why you need the permission and ask if he want
                        // to accept it (the rationale)
                    } else if (WRITE_EXTERNAL_STORAGE.equals (permission)) {
//                        Utils.showToast (this, "Write Permission is required");
//                        showRationale (permission, R.string.permission_denied_contacts);
                        // user denied WITHOUT never ask again
                        // this is a good place to explain the user
                        // why you need the permission and ask if he want
                        // to accept it (the rationale)
                    }
                }
            }
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    private void setUpNavigationDrawer () {
        toolbar = (Toolbar) findViewById (R.id.toolbar1);
        setSupportActionBar (toolbar);
        ImageView ivBack = (ImageView) toolbar.findViewById (R.id.ivBack);
        TextView tvSaveDesign = (TextView) findViewById (R.id.tvSaveDesign);
        ivBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                finish ();
                overridePendingTransition (R.anim.stay, R.anim.slide_out_down);
            }
        });

        tvSaveDesign.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Utils.showToast (AddProductActivity.this, "New design added successfully", true);
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

    private class ImagesPagerAdapter extends PagerAdapter {
        Context context;
        private List<UploadImage> uploadImageList = new ArrayList<UploadImage> ();

        public ImagesPagerAdapter (Context context, List<UploadImage> uploadImageList) {
            this.context = context;
            this.uploadImageList = uploadImageList;
        }

        @Override
        public int getCount () {
            return uploadImageList.size ();
        }

        @Override
        public boolean isViewFromObject (View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem (ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from (context);
            View view = inflater.inflate (R.layout.list_item_upload_image, container, false);
            ImageView imageview = (ImageView) view.findViewById (ivImage);
            final ProgressBar progressBar = (ProgressBar) view.findViewById (R.id.progressBar);

            UploadImage uploadImage = uploadImageList.get (position);
            imageview.setImageBitmap (uploadImage.getImage ());


//            Glide.with (ProductDetailActivity.this)
//                    .load (productDetail.getImages ().get (position))
//                    .listener (new RequestListener<String, GlideDrawable> () {
//                        @Override
//                        public boolean onException (Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                           progressBar.setVisibility (View.GONE);
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady (GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                            progressBar.setVisibility (View.GONE);
//                            return false;
//                        }
//                    })
//                    .into (imageview);
            container.addView (view);
            return view;
        }

        @Override
        public void destroyItem (ViewGroup container, int position, Object object) {
            container.removeView ((View) object);
        }

    }
}
