package com.actiknow.triburg.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actiknow.triburg.R;
import com.actiknow.triburg.activity.ProductListActivity;
import com.actiknow.triburg.model.Category;
import com.actiknow.triburg.utils.SetTypeFace;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<Category> categoryList = new ArrayList<Category> ();

    public AllCategoryAdapter (Activity activity, List<Category> categoryList) {
        this.activity = activity;
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.list_item_category, parent, false);
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {//        runEnterAnimation (holder.itemView);
        final Category category = categoryList.get (position);
        holder.tvName.setTypeface (SetTypeFace.getTypeface (activity));
        holder.tvName.setText (category.getName ());

        final ViewHolder tempholder = holder;

        Glide.with (activity)
                .load (category.getImage ())
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
        return categoryList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        ImageView ivImage;
        ProgressBar progressBar;

        public ViewHolder (View view) {
            super (view);
            tvName = (TextView) view.findViewById (R.id.tvName);
            ivImage = (ImageView) view.findViewById (R.id.ivImage);
            progressBar = (ProgressBar) view.findViewById (R.id.progressBar);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
            Category category = categoryList.get (getLayoutPosition ());
            Intent intent = new Intent (activity, ProductListActivity.class);
            intent.putExtra ("category_id", category.getId ());
            intent.putExtra ("category_name", category.getName ());
            activity.startActivity (intent);
            activity.overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}