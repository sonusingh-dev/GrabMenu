package com.socioffice.grabmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GMapAct extends MapActivity {

	private static int count = -1;
	
	private Button btnRefineMap;
	private ImageButton btnNext;
	private ImageButton btnBack;

	private ArrayList<String> addrList;	
	private MapView mapView;
	private List<Overlay> mapOverlays;
	private Drawable drawable1;
	private Drawable drawable2;
	private MyItemizedOverlay itemizedOverlay1;
	private MyItemizedOverlay itemizedOverlay2;
	private MapController mapController;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gmap);

		mapView = (MapView) findViewById(R.id.mapview);		
		addrList = new ArrayList<String>();

		btnRefineMap = (Button) findViewById(R.id.btnRefineMap);
		btnNext = (ImageButton) findViewById(R.id.btnNext);
		btnBack = (ImageButton) findViewById(R.id.btnBack);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		mapController = mapView.getController();
		mapController.setZoom(14);

		mapOverlays = mapView.getOverlays();
		drawable1 = getResources().getDrawable(R.drawable.gpin2);
		itemizedOverlay1 = new MyItemizedOverlay(drawable1, mapView);
		addAddr("Andheri Railway, Mumbai", itemizedOverlay1);
		addAddr("215 mahavir industrial estate, mahakali caves road, andheri east, mumbai",
				itemizedOverlay1);
		addAddr("Vile Parle Railway, Mumbai", itemizedOverlay1);
		addAddr("King Circle Railway, Mumbai", itemizedOverlay1);
		addAddr("Khar Railway, Mumbai", itemizedOverlay1);
		addAddr("Wadala Railway, Mumbai", itemizedOverlay1);
		addAddr("Bandra Railway, Mumbai", itemizedOverlay1);
		addAddr("Santacruz Railway, Mumbai", itemizedOverlay1);
		mapOverlays.add(itemizedOverlay1);

		drawable2 = getResources().getDrawable(R.drawable.gpin1);
		itemizedOverlay2 = new MyItemizedOverlay(drawable2, mapView);
		addAddr("Mahim Railway, Mumbai", itemizedOverlay2);
		mapOverlays.add(itemizedOverlay2);		
		mapView.invalidate();

		btnRefineMap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnRefineMapOnClick(view);
			}
		});

		btnNext.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnNextOnClick(view);
			}
		});

		btnBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				btnBackOnClick(view);
			}
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void addAddr(String addr, MyItemizedOverlay itemizedOverlay) {
				
		addrList.add(addr);
		count = count + 1;
		getPoint(addr, itemizedOverlay);
	}
	
	public GeoPoint getPoint(String addr, MyItemizedOverlay itemizedOverlay) {

		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		try {
			Address address = geocoder.getFromLocationName(addr, 5).get(0);
			GeoPoint point = new GeoPoint((int) (address.getLatitude() * 1E6),
					(int) (address.getLongitude() * 1E6));			
			OverlayItem overlayitem = new OverlayItem(point, "Socioffice Software Pvt. Ltd.", addr);
			itemizedOverlay.addOverlay(overlayitem);
			return point;			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void btnRefineMapOnClick(View view) {
		Intent intent = new Intent().setClass(this, RefineAct.class);
		startActivity(intent);
	}

	public void btnNextOnClick(View view) {		
		count++;
		if (count >= addrList.size()) {
			count = 0;
		}
		GeoPoint point = getPoint(addrList.get(count), itemizedOverlay2);
		if (point != null) {
			mapController.animateTo(point);
		}
		
	}

	public void btnBackOnClick(View view) {		
		count--;
		if (count <= -1) {
			count = addrList.size() - 1;
		}
		GeoPoint point = getPoint(addrList.get(count), itemizedOverlay2);
		if (point != null) {
			mapController.animateTo(point);
		}
	}

}
