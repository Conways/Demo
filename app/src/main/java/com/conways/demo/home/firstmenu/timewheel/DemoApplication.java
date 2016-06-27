package com.conways.demo.home.firstmenu.timewheel;

import android.app.Application;

import com.conways.demo.utils.LogUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by user on 2016/5/6.
 */
public class DemoApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initLog();
        initUmeng();
        initImageLoader();
    }

    private void initLog() {
        LogUtils.isDebugModel(true);
    }

    private void initUmeng() {
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }


    public void initImageLoader() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();
        ImageLoader.getInstance().init(config.build());
    }
}
