package com.shippingiseverything.droidcamera;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;

public class BrushedMetallicDrawable extends ShapeDrawable {

	public BrushedMetallicDrawable() {
		super();
		
		RectShape s = new RectShape();
		setShape(s);
	}
	
	@Override
	protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
//		super.onDraw(shape, canvas, paint);
		
		paint.setColor(Color.DKGRAY);
		shape.draw(canvas, paint);
	}
}
