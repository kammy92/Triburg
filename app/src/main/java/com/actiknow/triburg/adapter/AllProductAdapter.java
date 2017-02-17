package com.actiknow.triburg.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.activity.ProductDetailActivity;
import com.actiknow.triburg.model.Product;
import com.actiknow.triburg.utils.SetTypeFace;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<Product> productList = new ArrayList<Product> ();

    public AllProductAdapter (Activity activity, List<Product> productList) {
        this.activity = activity;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.list_item_product, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final Product product = productList.get (position);
        holder.tvName.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvPrice.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvOfferPrice.setTypeface (SetTypeFace.getTypeface (activity));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.ivImage.setClipToOutline (true);
        }

        holder.tvName.setText (product.getName ());
        holder.tvPrice.setText ("Min QTY : " + product.getPrice ());

//        if (product.isIn_wishlist ()){
//            holder.ivWishlist.setBackgroundResource (R.drawable.ic_blank_heart);
//            product.setIn_wishlist (false);
//            Utils.showToast (activity, "Product removed from Wishlist", true);
//        } else {
//            holder.ivWishlist.setBackgroundResource (R.drawable.ic_fill_heart);
//            product.setIn_wishlist (true);
//            Utils.showToast (activity,"Product added to Wishlist", true);
//        }


        if (product.is_offer ()){
            holder.tvOfferPrice.setVisibility (View.VISIBLE);
            holder.tvPrice.setPaintFlags (holder.tvPrice.getPaintFlags () | Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.tvPrice.setText (product.getPrice ());
            holder.tvOfferPrice.setText ("" + product.getOffer_price ());
        } else {
            holder.tvPrice.setPaintFlags (holder.tvPrice.getPaintFlags () & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tvOfferPrice.setVisibility (View.GONE);
        }

        final ViewHolder tempholder = holder;

        Glide.with (activity)
                .load (product.getImage ())
                .listener (new RequestListener<String, GlideDrawable> () {
                    @Override
                    public boolean onException (Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        tempholder.progressBar.setVisibility (View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady (GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        tempholder.progressBar.setVisibility (View.GONE);
                        return false;
                    }
                })
                .into (holder.ivImage);
    }

    @Override
    public int getItemCount () {
        return productList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvPrice;
        TextView tvOfferPrice;
        ImageView ivWishlist;
        ImageView ivImage;
        ProgressBar progressBar;


        public ViewHolder (View view) {
            super (view);
            tvName = (TextView) view.findViewById (R.id.tvName);
            tvPrice = (TextView) view.findViewById (R.id.tvPrice);
            tvOfferPrice = (TextView) view.findViewById (R.id.tvOfferPrice);
            ivWishlist= (ImageView) view.findViewById (R.id.ivWishlist);
            ivImage = (ImageView) view.findViewById (R.id.ivImage);
            progressBar = (ProgressBar) view.findViewById (R.id.progressBar);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
            Product product = productList.get (getLayoutPosition ());
            Intent intent = new Intent (activity, ProductDetailActivity.class);
            intent.putExtra ("product_id", product.getId ());
            intent.putExtra ("product_name", product.getName ());
            intent.putExtra ("product_category", product.getCategory ());
            if (product.is_offer ()){
                intent.putExtra ("product_description", "OFFER");
            } else {
                intent.putExtra ("product_description", "");
            }
            activity.startActivity (intent);
            activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}