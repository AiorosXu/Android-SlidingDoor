package com.aioros.slidingdoor.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @包名：com.aioros.slidingdoor.util
 * @类名：Utils
 * @日期：2015-2-26
 * @作者：Aioros(http://www.github.com/AiorosXu)
 */
public class Utils
{
	public static int getWindowWidth(Context context)
	{
		WindowManager windowManager = (WindowManager) (context.getSystemService(Context.WINDOW_SERVICE));
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.widthPixels;
	}

	public static int getWindowHeight(Context context)
	{
		WindowManager windowManager = (WindowManager) (context.getSystemService(Context.WINDOW_SERVICE));
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}

}
