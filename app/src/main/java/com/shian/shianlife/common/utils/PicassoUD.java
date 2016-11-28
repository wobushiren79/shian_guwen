package com.shian.shianlife.common.utils;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class PicassoUD
{

    public static void loadByImageLoader(Context ctx, String url,
            ImageView imageView, int repalaceId)
    {
    	if(url==null) return;
        Options bitmapOptions = new Options();
        bitmapOptions.inPreferredConfig = Config.ALPHA_8;
        bitmapOptions.inSampleSize = 20;
        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inDither = false;
//        bitmapOptions.outHeight = imageView.getMinimumHeight();
//        bitmapOptions.outWidth = imageView.getMinimumWidth();
        BitmapDisplayer displayer = new RoundedBitmapDisplayer(20);
        DisplayImageOptions options = null;
        if (repalaceId == 0)
        {
            options = new DisplayImageOptions.Builder()
                    .bitmapConfig(Config.RGB_565)
                    .cacheOnDisk(true)
                    // .showImageOnLoading(repalaceId)
                    // .showImageForEmptyUri(repalaceId)
                    // .showImageOnFail(R.drawable.ic_launcher)
                    .decodingOptions(bitmapOptions).displayer(displayer)
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
        } else
        {
            options = new DisplayImageOptions.Builder()
                    .bitmapConfig(Config.RGB_565).cacheOnDisk(true)
                    .showImageOnLoading(repalaceId)
                    .showImageForEmptyUri(repalaceId)
                    // .showImageOnFail(R.drawable.ic_launcher)
                    .decodingOptions(bitmapOptions).displayer(displayer)
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
        }
        ImageLoader.getInstance().displayImage(url, imageView, options);
        // if (repalaceId == 0) {
        // repalaceId = R.drawable.loading_big;
        // }
        // Transformation mTransformation = new RoundedTransformationBuilder()
        // .cornerRadius(20).borderColor(Color.BLACK).oval(false).build();
        //
        // Picasso.with(ctx).load(url).placeholder(repalaceId)
        // .error(R.drawable.ic_launcher)
        // .config(Config.RGB_565).fit()
        // // .transform(mTransformation)
        // .into(imageView);
    }

    public static void loadImage(Context ctx, String url, int replaceID,
            ImageView v)
    {
        // Picasso.with(ctx)
        // .load(url)
        // .placeholder(replaceID)
        // .config(Config.ALPHA_8)
        // .fit()
        // .into(v);
        loadByImageLoader(ctx, url, v, replaceID);
    }

    public static void loadImage(Context ctx, String url, int replaceID,
            int cornerRadius, ImageView v)
    {
        // Picasso.with(ctx)
        // .load(url)
        // .placeholder(replaceID)
        // .fit()
        // .config(Config.ALPHA_8)
        // .into(v);
        loadByImageLoader(ctx, url, v, replaceID);
        ;
    }

    public static void loadImage(Context ctx, String url, ImageView v)
    {
        // Picasso.with(ctx)
        // .load(url)
        // .fit()
        // .config(Config.ALPHA_8)
        // .into(v);
        loadByImageLoader(ctx, url, v, 0);

    }

    /**
     * added by LiuXu
     * 加载一张四周圆角的图片到ImageView 中
     * 
     * @param context
     * @param url
     * @param imageview
     * @param cornerRadius  四周圆角半径以px为单位
     */
//    public static void loadRoundCornerImageByPicasso(Context context,
//            String url, ImageView imageview, int cornerRadius, int replaceID)
//    {
//        Transformation mTransformation = new RoundedTransformationBuilder()
//                .cornerRadius(cornerRadius).borderColor(Color.BLACK)
//                .oval(false).build();
//        Picasso.with(context).load(url).placeholder(replaceID).fit()
//                .transform(mTransformation).into(imageview);
//    }

    /**
     * added by LiuXu 加载一张四周圆角的图片到ImageView 中
     * 
     * @param context
     * @param url
     * @param imageview
     * @param cornerRadius
     *            四周圆角半径以px为单位
     */
    public static void loadRoundCornerImage(Context context, String url,
            ImageView imageview, int cornerRadius, int replaceID)
    {
        // Picasso.with(context)
        // .load(url)
        // .placeholder(replaceID)
        // .fit()
        // .config(Config.ALPHA_8)
        // .into(imageview);
        loadByImageLoader(context, url, imageview, replaceID);
    }

    public static void loadRoundCornerImage(Context context, String url,
            ImageView imageview, int cornerRadius)
    {
        // Picasso.with(context)
        // .load(url)
        // .fit()
        // .config(Config.ALPHA_8)
        // .into(imageview);
        loadByImageLoader(context, url, imageview, 0);

    }

}
