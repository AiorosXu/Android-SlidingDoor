package com.aioros.demo;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.content.Context;

/**
 * 
 *	@包名：com.aioros.demo
 *	@类名：SlidingDoorApp
 *	@日期：2015-2-26
 *	@作者：Aioros(http://www.github.com/AiorosXu)
 */
public class SlidingDoorApp extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		initImageLoader(this);
	}
	
	public static void initImageLoader(Context context)
	{
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
		ImageLoader.getInstance().init(config);
	}
}
