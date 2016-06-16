package com.conways.demo.home.firstmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.conways.demo.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by user on 2016/5/11.
 */
public class ViewPagerAdapter extends PagerAdapter{

    private Context context;
    private int[] imageId;
    private String[] urls;
    private ImageLoader imageLoader;

    public ViewPagerAdapter(Context context, int[] imageId, String[] urls) {
        this.context = context;
        this.imageId = imageId;
        this.urls = urls;
        imageLoader=ImageLoader.getInstance();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public int getCount() {
        return imageId.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(imageId[position]);
        imageLoader.displayImage(urls[position], imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
        container.addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
