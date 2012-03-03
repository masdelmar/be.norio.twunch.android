/**
 *	Copyright 2010-2012 Norio bvba
 *
 *	This program is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	This program is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package be.norio.twunch.android.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

public class Util {

	public static Float getDistanceToTwunch(Context context, double lat, double lon) {
		if (lat == 0 && lon == 0) {
			return null;
		}
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		String p = locationManager.getBestProvider(new Criteria(), true);
		if (p != null && p.length() > 0) {
			Location location = locationManager.getLastKnownLocation(p);
			if (location != null) {
				float[] distance = new float[1];
				Location.distanceBetween(location.getLatitude(), location.getLongitude(), lat, lon, distance);
				return (distance[0] / 1000);
			}
		}
		return null;
	}

	public static boolean isIceCreamSandwich() {
		// Can use static final constants like HONEYCOMB, declared in later
		// versions
		// of the OS since they are inlined at compile time. This is guaranteed
		// behavior.
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
	}

	public static String readTextFromResource(Context context, int resourceId) {
		InputStream raw = context.getResources().openRawResource(resourceId);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int i;
		try {
			i = raw.read();
			while (i != -1) {
				stream.write(i);
				i = raw.read();
			}
			raw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream.toString();
	}
}
