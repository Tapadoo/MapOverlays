package com.tapadoo.android.mapOverlays;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class LineOverlay  extends Overlay {
	
	private List<GeoPoint> points ;
	private Point[] pixelPoints ;
	
	private Paint mPaint;
	private int pointsSize;
	
	public LineOverlay( List<GeoPoint> points )
	{
		super();

		this.points = points;
		this.pointsSize = points.size() ;
		
		pixelPoints = new Point[pointsSize];
		
		mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(8);	
        
        for( int i = 0 ; i < pointsSize ; i++ )
	    {
        	pixelPoints[i] = new Point();
	    }
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
		super.draw(canvas,mapView,shadow);
		
		if( shadow)
		{
			return ;
		}
	
	    Projection projection = mapView.getProjection();
	    Path path = new Path();
	    boolean isFirst = true ;
	    
	    for( int i = 0 ; i < pointsSize ; i++ )
	    {
	    	GeoPoint gPoint = points.get(i);
	    	Point pixelPoint = pixelPoints[i];
	    	
	    	projection.toPixels(gPoint, pixelPoint);
	    	
	    	if( isFirst )
	    	{
	    		path.moveTo(pixelPoint.x, pixelPoint.y);
	    		isFirst = false ;
	    	}
	    	else
	    	{
	    		path.lineTo(pixelPoint.x, pixelPoint.y);
	    	}
	    }
	    

        canvas.drawPath(path, mPaint);
	}
	
	

}
