# Android-SlidingDoor
Android-SlidingDoor 是一个简单的滑动门控件，可以作为应用的首页展示。

Demo
============
![](https://github.com/AiorosXu/Android-SlidingDoor/blob/master/screenshot/SlidingDoor-screenshot.gif)

Usage
============
Eclipse
* **[sliding-door-lib.jar](https://github.com/AiorosXu/Android-SlidingDoor/releases/download/v1.0.0/sliding-door-lib.jar)** 

SlidingDoor的使用
```xml
    <com.aioros.slidingdoor.view.SlidingDoor
        android:id="@+id/door"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
    </com.aioros.slidingdoor.view.SlidingDoor>
```

SlidingDoor状态监听接口
``` Java
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
```

设置SlidingDoor的滑动方向
``` Java
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
```
	
Thanks
============
Romain Piel   [Shimmer-android](https://github.com/RomainPiel/Shimmer-android)

Sergey Tarasevich   [Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)

Developed By
============

* Aioros Xu - <Aioros1220@gmail.com>

License
=======

    Copyright 2015 Aioros Xu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
