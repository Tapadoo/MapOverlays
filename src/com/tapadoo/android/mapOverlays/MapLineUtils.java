package com.tapadoo.android.mapOverlays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.google.android.maps.GeoPoint;


public class MapLineUtils {

	private static final String TAG = null;

	private MapLineUtils()
	{
		
	}
	
	/**
	 * Convert data form input stream to geo points. 
	 * Assumes input is the form of lat,long
	 * @param inputStream
	 */
	public static List<GeoPoint> gpsCoordsToGeoPoints( InputStream inputStream )
	{
		
		ArrayList<GeoPoint> data = new ArrayList<GeoPoint>();
		
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8") , 8192);
	    
			String line = null;
 
	 	   while ((line = reader.readLine()) != null) {
	 		   
	 		   String[] dataPoint = line.split(",");
	 		   
	 		   double latDbl = Double.parseDouble(dataPoint[0]);
	 		   double longDbl = Double.parseDouble(dataPoint[1]);
	 		   
	 		   GeoPoint newGP = new GeoPoint(  (int) (latDbl *1E6 ) , (int) (longDbl *1E6 ) );
	 		   data.add(newGP);
	 	   }
	 	   
		} catch (IOException e) {
		    Log.e( TAG, "Error reading points", e );
		
		} finally {
		    try {
		    	inputStream.close();
		    } catch (IOException e) {
			    Log.e( TAG, "Error reading points", e );
		    }
		}

		return data ;
	}
	
}
