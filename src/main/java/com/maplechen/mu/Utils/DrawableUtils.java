package com.maplechen.mu.Utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtils {

	public static GradientDrawable getShape(int color,int radius){
		
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setShape(GradientDrawable.RECTANGLE);
		gradientDrawable.setColor(color);
		gradientDrawable.setCornerRadius(radius);
		
		return gradientDrawable;
	}
	
	public static StateListDrawable  getSelectorDrawable(Drawable drawable,Drawable normal){
		
		StateListDrawable  stateListDrawable  = new StateListDrawable();
		stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, drawable);//安卓下自带的状态
		stateListDrawable.addState(new int[]{},normal);
		
		return stateListDrawable;
		
	}
	
	public static StateListDrawable setDrawable(int color,int radius,int presscolor){
		GradientDrawable press_drawbler = getShape(presscolor, radius);
		GradientDrawable drawable = getShape(color, radius);
		StateListDrawable selectorDrawable = getSelectorDrawable(press_drawbler, drawable);
		
		return selectorDrawable;
		
	}
}
