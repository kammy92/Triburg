package com.actiknow.triburg.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.model.ShoppingBagProduct;
import com.actiknow.triburg.utils.SetTypeFace;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


public class ShoppingBagProductAdapter extends RecyclerView.Adapter<ShoppingBagProductAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<ShoppingBagProduct> shoppingBagProductList = new ArrayList<> ();

    public ShoppingBagProductAdapter (Activity activity, List<ShoppingBagProduct> shoppingBagProductList) {
        this.activity = activity;
        this.shoppingBagProductList = shoppingBagProductList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.list_item_shopping_bag_product, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final ShoppingBagProduct shoppingBagProduct = shoppingBagProductList.get (position);

        holder.tvProductName.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvProductReference.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvProductColor.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvProductSize.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvProductPrice.setTypeface (SetTypeFace.getTypeface (activity));

        holder.tvProductName.setText (shoppingBagProduct.getName ());
        holder.tvProductReference.setText (shoppingBagProduct.getReference ());
        holder.tvProductColor.setTextColor (Color.parseColor (shoppingBagProduct.getColor ()));
        holder.tvProductSize.setText (shoppingBagProduct.getSize ());
        holder.tvProductPrice.setText (shoppingBagProduct.getPrice () + " Pcs.");

        final ViewHolder tempholder = holder;

        Glide.with (activity)
                .load (shoppingBagProduct.getImage ())
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
                .into (holder.ivProductImage);
    }

    @Override
    public int getItemCount () {
        return shoppingBagProductList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivProductImage;
        TextView tvProductName;
        TextView tvProductReference;
        TextView tvProductSize;
        TextView tvProductColor;
        TextView tvProductPrice;
        ProgressBar progressBar;


        public ViewHolder (View view) {
            super (view);
            ivProductImage = (ImageView) view.findViewById (R.id.ivProductImage);
            tvProductName = (TextView) view.findViewById (R.id.tvProductName);
            tvProductReference = (TextView) view.findViewById (R.id.tvProductReference);
            tvProductSize = (TextView) view.findViewById (R.id.tvProductSize);
            tvProductColor = (TextView) view.findViewById (R.id.tvProductColor);
            tvProductPrice = (TextView) view.findViewById (R.id.tvProductPrice);
            progressBar = (ProgressBar) view.findViewById (R.id.progressBar);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
          //  ShowDialog (badge);
        }

    }
}
