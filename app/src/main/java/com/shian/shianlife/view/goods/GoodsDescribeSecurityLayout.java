package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;

/**
 * Created by zm.
 */

public class GoodsDescribeSecurityLayout extends BaseLayout {
    ImageView ivContent;

    public GoodsDescribeSecurityLayout(Context context) {
        this(context, null);
    }

    public GoodsDescribeSecurityLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_describle_security, attrs);
    }

    @Override
    protected void initView() {
        ivContent = (ImageView) view.findViewById(R.id.iv_content);
        String imageUri = "drawable://" + R.drawable.zhy_icon_security;
        ImageLoader.getInstance().displayImage(imageUri, ivContent);
    }

    @Override
    protected void initData() {

    }
}
