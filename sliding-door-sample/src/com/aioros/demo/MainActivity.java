package com.aioros.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.aioros.slidingdoor.view.SlidingDoor;
import com.aioros.slidingdoor.view.SlidingDoor.OnSlidingDoorChangeListener;

import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * @包名：com.aioros.demo
 * @类名：MainActivity
 * @日期：2015-2-26
 * @作者：Aioros (http://www.github.com/AiorosXu)
 */
@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity implements OnClickListener, OnSlidingDoorChangeListener
{
	private SlidingDoor doorView;
	private Button left_btn, right_btn, top_btn, bottom_btn, url_btn, save_btn;
	private static final String imageUrl = "http://b.zol-img.com.cn/sjbizhi/images/6/320x510/1381822607338.jpg";
	private final String Dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SlidingDoor";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setListener();
	}

	private void initView()
	{
		doorView = (SlidingDoor) findViewById(R.id.door);
		doorView.setTextViewContent(getResources().getString(R.string.bottom));
		doorView.startTextViewShimmer();
		doorView.setTextViewSize(16);
		doorView.setOrientation(SlidingDoor.BOTTOM);
		doorView.setImageViewResource(R.drawable.slidingdoor_bg);
		left_btn = (Button) findViewById(R.id.left_btn);
		right_btn = (Button) findViewById(R.id.right_btn);
		top_btn = (Button) findViewById(R.id.top_btn);
		bottom_btn = (Button) findViewById(R.id.bottom_btn);
		url_btn = (Button) findViewById(R.id.url_btn);
		save_btn = (Button) findViewById(R.id.save_btn);
	}

	private void setListener()
	{
		left_btn.setOnClickListener(this);
		right_btn.setOnClickListener(this);
		top_btn.setOnClickListener(this);
		bottom_btn.setOnClickListener(this);
		url_btn.setOnClickListener(this);
		save_btn.setOnClickListener(this);
		doorView.setOnSlidingDoorChangeListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.left_btn:
				doorView.setTextViewContent(getResources().getString(R.string.left));
				doorView.setOrientation(SlidingDoor.LEFT);
				doorView.setVisibleFlag(true);
				doorView.setVisibility(View.VISIBLE);
				doorView.showSlidingDoor();
				break;
			case R.id.right_btn:
				doorView.setTextViewContent(getResources().getString(R.string.right));
				doorView.setOrientation(SlidingDoor.RIGHT);
				doorView.setVisibleFlag(true);
				doorView.setVisibility(View.VISIBLE);
				doorView.showSlidingDoor();
				break;
			case R.id.top_btn:
				doorView.setTextViewContent(getResources().getString(R.string.top));
				doorView.setOrientation(SlidingDoor.TOP);
				doorView.setVisibleFlag(true);
				doorView.setVisibility(View.VISIBLE);
				doorView.showSlidingDoor();
				break;
			case R.id.bottom_btn:
				doorView.setTextViewContent(getResources().getString(R.string.bottom));
				doorView.setOrientation(SlidingDoor.BOTTOM);
				doorView.setVisibleFlag(true);
				doorView.setVisibility(View.VISIBLE);
				doorView.showSlidingDoor();
				break;
			case R.id.url_btn:
				doorView.setTextViewContent(getResources().getString(R.string.bottom));
				doorView.setOrientation(SlidingDoor.BOTTOM);
				doorView.setVisibleFlag(true);
				doorView.setVisibility(View.VISIBLE);
				doorView.setImageViewResourceFromUrl(imageUrl, UilOptions.displayImageOptions);
				doorView.showSlidingDoor();
				break;
			case R.id.save_btn:
				File file = new File(Dir);
				if (!file.exists())
				{
					file.mkdirs();
				}
				BitmapDrawable bd = (BitmapDrawable) doorView.getImageViewDrawable();
				Toast.makeText(getApplicationContext(), savePhoto(bd.getBitmap(), 100), Toast.LENGTH_SHORT).show();
				break;

		}
	}

	private String savePhoto(Bitmap bitmap, int quality)
	{
		// 判断是否有sd卡
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			// 保存图像
			StringBuilder sb = new StringBuilder();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String tempDate = sdf.format(Calendar.getInstance().getTime());
			sb.append(Dir + "/");
			tempDate = tempDate + ".jpg";
			sb.append(tempDate);
			String path = sb.toString();
			boolean isSaveSuccess = false;
			isSaveSuccess = saveBitmap(bitmap, quality, sb.toString());
			if (isSaveSuccess)
			{
				return "保存成功！存储位置:" + path;
			}
			Toast.makeText(this, "保存失败！", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
		}
		return null;
	}

	public static boolean saveBitmap(Bitmap bitmap, int quality, String target)
	{
		if (bitmap == null)
		{
			return false;
		}
		File file = new File(target);
		try
		{
			if (bitmap != null)
			{
				FileOutputStream fos = new FileOutputStream(file);
				boolean istrue = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);
				fos.flush();
				fos.close();
				return istrue;
			}
		}
		catch (IOException e)
		{
		}
		return false;
	}

	@Override
	public void onSlidingDoorClose()
	{
		Toast.makeText(getApplicationContext(), "close", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSlidingDoorOpen()
	{
		Toast.makeText(getApplicationContext(), "open", Toast.LENGTH_SHORT).show();
	}
}
