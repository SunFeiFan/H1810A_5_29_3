package com.example.dell.h1810a_5_29_3;

import android.content.Context;
import android.media.tv.TvContentRating;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends PagerAdapter{

    private Context mContext;
    private List<String> mList;

    public VpAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_image, null);
        String s = mList.get(position);
        ImageView mImage = inflate.findViewById(R.id.img);
        TextView mTv = inflate.findViewById(R.id.tv_count);
        Glide.with(mContext).load(s).into(mImage);
        mTv.setText("第"+(position+1)+"页/共"+mList.size()+"页");
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
