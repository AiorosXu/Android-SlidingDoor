package com.aioros.slidingdoor.view;

import com.aioros.slidingdoor.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * @描述：SlidingDoor
 * @包名：com.aioros.slidingdoor.view
 * @类名：SlidingDoor
 * @日期：2015-2-6
 * @作者：Aioros(http://www.github.com/AiorosXu)
 */
public class SlidingDoor extends RelativeLayout
{
	private static final String TAG = "SlidingDoor";
	private Context context;
	private Scroller mScroller;
	private Interpolator interpolator;
	private ShimmerTextView shimmerTextView;
	private Shimmer shimmer;
	private ImageView imageView;
	private int windowWidth = 0;
	private int windowHeigh = 0;
	private float firstX = 0;
	private float firstY = 0;
	private float lastX = 0;
	private float lastY = 0;
	private float differenceX = 0;
	private float differenceY = 0;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int TOP = 2;
	public static final int BOTTOM = 3;
	private int orientation = BOTTOM;
	private int defaultHeight = 100;
	private int defaultDuration = 800;
	private boolean visibleFlag = true;
	private boolean slidingdoorState = true;
	private OnSlidingDoorChangeListener onSlidingDoorChangeListener;

	public SlidingDoor(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		this.context = context;
		initView();
	}

	private void initView()
	{
		interpolator = new BounceInterpolator();
		mScroller = new Scroller(context, interpolator);
		shimmer = new Shimmer();
		imageView = new ImageView(context);
		shimmerTextView = new ShimmerTextView(context);
		windowWidth = Utils.getWindowWidth(context);
		windowHeigh = Utils.getWindowHeight(context);
		imageView
				.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		shimmerTextView.setTextColor(Color.DKGRAY);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 0, 0, 80);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		addView(imageView);
		addView(shimmerTextView, params);
	}

	/**
	 * 设置SlidingDoor的监听
	 */
	public void setOnSlidingDoorChangeListener(OnSlidingDoorChangeListener onSlidingDoorChangeListener)
	{
		this.onSlidingDoorChangeListener = onSlidingDoorChangeListener;
	}

	/**
	 * 设置SlidingDoor的方向
	 * 
	 * @param orientation
	 */
	public void setOrientation(int orientation)
	{
		if (orientation >= LEFT && orientation <= BOTTOM)
		{
			this.orientation = orientation;
		}
		else
		{
			this.orientation = BOTTOM;
		}
	}

	/**
	 * 显示SlidingDoor
	 */
	public void showSlidingDoor()
	{
		switch (orientation)
		{
			case LEFT:
				smoothScroll(-windowWidth, 0, windowWidth, 0, defaultDuration);
				break;
			case RIGHT:
				smoothScroll(windowWidth, 0, -windowWidth, 0, defaultDuration);
				break;
			case TOP:
				smoothScroll(0, -windowHeigh, 0, windowHeigh, defaultDuration);
				break;
			case BOTTOM:
				smoothScroll(0, windowHeigh, 0, -windowHeigh, defaultDuration);
				break;
		}
		onSlidingDoorChangeListener.onSlidingDoorClose();
	}

	/**
	 * 设置SlidingDoor的可见状态
	 * 
	 * @param visibleFlag
	 */
	public void setVisibleFlag(boolean visibleFlag)
	{
		this.visibleFlag = visibleFlag;
	}

	/**
	 * 设置最小的弹起高度
	 * 
	 * @param height
	 */
	public void setMinHeight(int height)
	{
		defaultHeight = height;
	}

	/**
	 * 设置SlidingDoor滑动的速度
	 * 
	 * @param duration
	 */
	public void setSligingDuration(int duration)
	{
		defaultDuration = duration;
	}

	/**
	 * 设置网络图片
	 * 
	 * @param imageUrl
	 * @param displayImageOptions
	 */
	public void setImageViewResourceFromUrl(String imageUrl, DisplayImageOptions displayImageOptions)
	{
		ImageLoader.getInstance().displayImage(imageUrl, imageView, displayImageOptions);
	}

	/**
	 * 设置图片背景
	 * 
	 * @param drawable
	 */
	public void setImageViewBackground(Drawable drawable)
	{
		imageView.setImageDrawable(drawable);
	}

	/**
	 * 设置图片资源
	 * 
	 * @param resId
	 */
	public void setImageViewResource(int resId)
	{
		imageView.setImageResource(resId);
	}

	/**
	 * 获取ImageView的Drawable
	 * @return
	 */
	public Drawable getImageViewDrawable()
	{
		return imageView.getDrawable();
	}

	/**
	 * 设置文字内容
	 * 
	 * @param charSequence
	 */
	public void setTextViewContent(CharSequence charSequence)
	{
		shimmerTextView.setText(charSequence);
	}

	/**
	 * 设置文字颜色
	 * 
	 * @param color
	 */
	public void setTextViewColor(int color)
	{
		shimmerTextView.setTextColor(color);
	}

	/**
	 * 设置文字大小
	 * 
	 * @param size
	 */
	public void setTextViewSize(int size)
	{
		shimmerTextView.setTextSize(size);
	}

	/**
	 * 设置文字闪烁
	 */
	public void startTextViewShimmer()
	{
		shimmer.start(shimmerTextView);
	}

	/**
	 * 停止文字闪烁
	 */
	public void stopTextViewShimmer()
	{
		shimmer.cancel();
	}

	/**
	 * 滑动Scroll
	 * 
	 * @param fx
	 * @param fy
	 * @param dx
	 * @param dy
	 * @param duration
	 */
	public void smoothScroll(int fx, int fy, int dx, int dy, int duration)
	{
		mScroller.startScroll(fx, fy, dx, dy, duration);
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getAction();
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
				firstX = event.getX();
				firstY = event.getY();
				Log.i(TAG, "ACTION_DOWN---------->firstX=" + firstX + "          firstY=" + firstY);
				return true;
			case MotionEvent.ACTION_MOVE:
				lastX = event.getX();
				lastY = event.getY();
				switch (orientation)
				{
					case LEFT:
						differenceX = firstX - lastX;
						if (differenceX < 0)
							scrollTo((int) differenceX, 0);
						break;
					case RIGHT:
						differenceX = firstX - lastX;
						if (differenceX > 0)
							scrollTo((int) differenceX, 0);
						break;
					case TOP:
						differenceY = firstY - lastY;
						if (differenceY < 0)
							scrollTo(0, (int) differenceY);
						break;
					case BOTTOM:
						differenceY = firstY - lastY;
						if (differenceY > 0)
							scrollTo(0, (int) differenceY);
						break;
				}
				Log.i(TAG, "ACTION_MOVE---------->lastX=" + lastX + "          lastY=" + lastY
						+ "           differenceX=" + differenceX + "          differenceY=" + differenceY);
				break;
			case MotionEvent.ACTION_UP:
				lastX = event.getX();
				lastY = event.getY();
				switch (orientation)
				{
					case LEFT:
						differenceX = firstX - lastX;
						if (differenceX == 0)
						{
							scrollTo(-defaultHeight, 0);
							smoothScroll(SlidingDoor.this.getScrollX(), 0, -SlidingDoor.this.getScrollX(), 0,
									defaultDuration);
							slidingdoorState = true;
						}
						else if (Math.abs(differenceX) > windowWidth / 4)
						{
							smoothScroll(SlidingDoor.this.getScrollX(), 0, -windowWidth, 0, defaultDuration);
							visibleFlag = false;
							slidingdoorState = false;
						}
						else
						{
							smoothScroll(SlidingDoor.this.getScrollX(), 0, -SlidingDoor.this.getScrollX(), 0,
									defaultDuration);
							slidingdoorState = true;
						}
						break;
					case RIGHT:
						differenceX = firstX - lastX;
						if (differenceX == 0)
						{
							scrollTo(defaultHeight, 0);
							smoothScroll(SlidingDoor.this.getScrollX(), 0, -SlidingDoor.this.getScrollX(), 0,
									defaultDuration);
							slidingdoorState = true;
						}
						else if (Math.abs(differenceX) > windowWidth / 4)
						{
							smoothScroll(SlidingDoor.this.getScrollX(), 0, windowWidth, 0, defaultDuration);
							visibleFlag = false;
							slidingdoorState = false;
						}
						else
						{
							smoothScroll(SlidingDoor.this.getScrollX(), 0, -SlidingDoor.this.getScrollX(), 0,
									defaultDuration);
							slidingdoorState = true;
						}
						break;
					case TOP:
						differenceY = firstY - lastY;
						if (differenceY == 0)
						{
							scrollTo(0, -defaultHeight);
							smoothScroll(0, SlidingDoor.this.getScrollY(), 0, -SlidingDoor.this.getScrollY(),
									defaultDuration);
							slidingdoorState = true;
						}
						else if (Math.abs(differenceY) > windowHeigh / 4)
						{
							smoothScroll(0, SlidingDoor.this.getScrollY(), 0, -windowHeigh, defaultDuration);
							visibleFlag = false;
							slidingdoorState = false;
						}
						else
						{
							smoothScroll(0, SlidingDoor.this.getScrollY(), 0, -SlidingDoor.this.getScrollY(),
									defaultDuration);
							slidingdoorState = true;
						}
						break;
					case BOTTOM:
						differenceY = firstY - lastY;
						if (differenceY == 0)
						{
							scrollTo(0, defaultHeight);
							smoothScroll(0, SlidingDoor.this.getScrollY(), 0, -SlidingDoor.this.getScrollY(),
									defaultDuration);
							slidingdoorState = true;
							break;
						}
						else if (Math.abs(differenceY) > windowHeigh / 4)
						{
							smoothScroll(0, SlidingDoor.this.getScrollY(), 0, windowHeigh, defaultDuration);
							visibleFlag = false;
							slidingdoorState = false;
						}
						else
						{
							smoothScroll(0, SlidingDoor.this.getScrollY(), 0, -SlidingDoor.this.getScrollY(),
									defaultDuration);
							slidingdoorState = true;
						}
						Log.i(TAG, "ACTION_UP---------->lastX=" + lastX + "          lastY=" + lastY
								+ "           differenceX=" + differenceX + "          differenceY=" + differenceY);
						break;
				}
				if (slidingdoorState)
				{
					onSlidingDoorChangeListener.onSlidingDoorClose();
				}
				else
				{
					onSlidingDoorChangeListener.onSlidingDoorOpen();
				}
				break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void computeScroll()
	{
		if (mScroller.computeScrollOffset())
		{
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
		else
		{
			if (!visibleFlag)
			{
				this.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 监听SlidingDoor状态的接口
	 * 
	 */
	public interface OnSlidingDoorChangeListener
	{
		/**
		 * SlidingDoor滑闭
		 */
		public void onSlidingDoorClose();

		/**
		 * SlidingDoor滑开
		 */
		public void onSlidingDoorOpen();
	}
}
