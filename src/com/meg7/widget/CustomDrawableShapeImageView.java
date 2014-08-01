package com.meg7.widget;

import com.meg7.samples.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Create by workjm on 2014/8/1
 * Email:workjm2020@163.com
 */
public class CustomDrawableShapeImageView extends BaseImageView{

	private int mShapeDrawable;
	private Context mContext;

	
	public CustomDrawableShapeImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init(context, attrs);
	}
	
    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeImageView);
        mShapeDrawable = a.getResourceId(R.styleable.CustomShapeImageView_drawable_resource, 0);
        a.recycle();
    }

	private Bitmap getBitmap(Context context, int width, int height, int shapeID) {
		Bitmap shape = BitmapFactory.decodeResource(getResources(), shapeID);
		
		Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
		int left = width/2 - shape.getWidth()/2;
		int top = height/2 - shape.getHeight()/2;
		canvas.drawBitmap(shape, left, top, paint);
        
        return bitmap;
        
	}
	
	public void setShapeDrawable(int drawableId) {
		mShapeDrawable = drawableId;
	}
	
	
	public Bitmap getBitmap() {
		 return getBitmap(mContext, getWidth(), getHeight(), mShapeDrawable);
	}

}
