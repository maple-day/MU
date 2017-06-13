package com.maplechen.mu.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.graphics.Palette;
import android.view.Window;

/**
 * Created by Administrator on 2017/4/11.
 */
public class PaletteUtils {

    public static void getWelcomeActivityBgColor(final Activity context, int iamges){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), iamges);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch darkMutedSwatch = palette.getLightMutedSwatch();
                Window window = context.getWindow();
                if(Build.VERSION.SDK_INT >= 21){
                    window.setStatusBarColor(darkMutedSwatch.getRgb());
                }
            }
        });
    }

}
