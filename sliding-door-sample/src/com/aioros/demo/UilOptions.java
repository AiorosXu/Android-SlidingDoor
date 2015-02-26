package com.aioros.demo;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 *	@包名：com.aioros.demo
 *	@类名：UilOptions
 *	@日期：2015-2-26
 *	@作者：Aioros(http://www.github.com/AiorosXu)
 */
public class UilOptions
{
	public static final DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
			.resetViewBeforeLoading(false).delayBeforeLoading(1000).cacheInMemory(false).cacheOnDisk(false)
			.considerExifParams(false).bitmapConfig(Bitmap.Config.ARGB_8888).displayer(new SimpleBitmapDisplayer())
			.build();
}
