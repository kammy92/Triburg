package com.actiknow.triburg.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.actiknow.triburg.R;
import com.actiknow.triburg.model.UploadImage;

import java.util.ArrayList;
import java.util.List;


public class UploadImageAdapter extends RecyclerView.Adapter<UploadImageAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private List<UploadImage> uploadImageList = new ArrayList<UploadImage> ();

    public UploadImageAdapter (Activity activity, List<UploadImage> uploadImageList) {
        this.activity = activity;
        this.uploadImageList = uploadImageList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.list_item_upload_image, parent, false);
        sView.setMinimumWidth (parent.getMeasuredWidth ());
        return new ViewHolder (sView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        final UploadImage uploadImage = uploadImageList.get (position);
        final ViewHolder tempholder = holder;

        holder.ivImage.setImageBitmap (uploadImage.getImage ());

//        Glide.with (activity)
//                .load (product.getImage ())
//                .listener (new RequestListener<String, GlideDrawable> () {
//                    @Override
//                    public boolean onException (Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        tempholder.progressBar.setVisibility (View.GONE);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady (GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        tempholder.progressBar.setVisibility (View.GONE);
//                        return false;
//                    }
//                })
//                .into (holder.ivImage);

    }


    @Override
    public int getItemCount () {
        return uploadImageList.size ();
    }

    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick (View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivImage;
        ProgressBar progressBar;

        public ViewHolder (View view) {
            super (view);
            ivImage = (ImageView) view.findViewById (R.id.ivImage);
            progressBar = (ProgressBar) view.findViewById (R.id.progressBar);
            view.setOnClickListener (this);
        }

        @Override
        public void onClick (View v) {
        }
    }
}