package com.example.wumpusgamemadkhaled.NonActivityClasses;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout
{
	public MyRelativeLayout(Context context)
	{
		super(context);
	}

	public MyRelativeLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// This is the key that will make the height equivalent to its width
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
}