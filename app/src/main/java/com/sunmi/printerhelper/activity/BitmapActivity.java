package com.sunmi.printerhelper.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sunmi.printerhelper.R;
import com.sunmi.printerhelper.utils.AidlUtil;


public class BitmapActivity extends BaseActivity {
    ImageView mImageView;
    Bitmap bitmap;

    int mytype;   // Bitmap printing

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        //setMyTitle(R.string.bitmap_title);
        //setBack();

        View inflatedFrame = getLayoutInflater().inflate(R.layout.category_content2, null);
        FrameLayout frameLayout = (FrameLayout) inflatedFrame.findViewById(R.id.frame_category) ;
        frameLayout.setDrawingCacheEnabled(true);
        frameLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        frameLayout.layout(0, 0, frameLayout.getMeasuredWidth(), frameLayout.getMeasuredHeight());
        frameLayout.buildDrawingCache(true);
        final Bitmap bm = frameLayout.getDrawingCache();

        bitmap = bm;

        initView();

        AidlUtil.getInstance().initPrinter();


    }

    private void initView() {

        mImageView = (ImageView) findViewById(R.id.bitmap_imageview);
        if (baseApp.isAidl()) {
            mImageView.setImageDrawable(new BitmapDrawable(bitmap));
        }

    }

    public void onClick(View view) {
        if (baseApp.isAidl()) {
            AidlUtil.getInstance().printBitmap(bitmap);
            AidlUtil.getInstance().print3Line();
        }
    }
}
