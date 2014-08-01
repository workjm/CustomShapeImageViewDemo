package com.meg7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.meg7.samples.R;
import com.svgandroid.SVG;
import com.svgandroid.SVGParser;

/**
 * Created by Mostafa Gazar on 11/2/13.
 */
public class SvgImageView extends BaseImageView {

    private int mSvgRawResourceId;

    public SvgImageView(Context context) {
        super(context);
    }

    public SvgImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context, attrs);
    }

    public SvgImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sharedConstructor(context, attrs);
    }

    private void sharedConstructor(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeImageView);
        mSvgRawResourceId = a.getResourceId(R.styleable.CustomShapeImageView_svg_raw_resource, 0);
        a.recycle();
    }
    
/*******************************************************************************
 * USAGE:
    // Load and parse a SVG
	SVG svg = new SVGBuilder()
            .readFromResource(getResources(), R.raw.someSvgResource) // if svg in res/raw
            .readFromAsset(getAssets(), "somePicture.svg")           // if svg in assets
            // .setWhiteMode(true) // draw fills in white, doesn't draw strokes
            // .setColorSwap(0xFF008800, 0xFF33AAFF) // swap a single colour
            // .setColorFilter(filter) // run through a colour filter
            // .set[Stroke|Fill]ColorFilter(filter) // apply a colour filter to only the stroke or fill
            .build();

			// Draw onto a canvas
			canvas.drawPicture(svg.getPicture());

			// Turn into a drawable
			Drawable drawable = svg.createDrawable();
			// drawable.draw(canvas);
			// imageView.setImageDrawable(drawable);
 ********************************************************************************/
    public static Bitmap getBitmap(Context context, int width, int height, int svgRawResourceId) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if (svgRawResourceId > 0) {
            SVG svg = SVGParser.getSVGFromInputStream(
                    context.getResources().openRawResource(svgRawResourceId), width, height);
            canvas.drawPicture(svg.getPicture());
        } else {
            canvas.drawRect(new RectF(0.0f, 0.0f, width, height), paint);
        }

        return bitmap;
    }

    @Override
    public Bitmap getBitmap() {
        return getBitmap(mContext, getWidth(), getHeight(), mSvgRawResourceId);
    }
}
