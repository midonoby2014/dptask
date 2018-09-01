package com.noby.dptask.gui.maproute;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.noby.dptask.Data.beans.Employee;
import com.noby.dptask.R;
import com.noby.dptask.baseClasses.BaseActivity;
import com.noby.dptask.baseClasses.BaseModels;
import com.noby.dptask.baseClasses.BaseView;

import static com.noby.dptask.utils.StaticMethods.REQUEST_LOCATION;
import static com.noby.dptask.utils.StaticMethods.checkLocationPermission;

public class MapRouteActivity extends BaseActivity implements
        MapRouteView,OnMapReadyCallback
       {

    MapRouteModelView modelView  ;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    GoogleApiClient mGoogleApiClient;
    Employee employee ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        inilizeVaribles();
    }

    private void inilizeVaribles() {
        modelView =  new MapRouteModelView();
        modelView.attachView(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission(this,this);
        }
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
        Intent intent = getIntent();
        // employee = (Employee) intent.getSerializableExtra("empdetails");
        employee = (Employee) intent.getExtras().getSerializable("empdetails");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapFrag.onResume();
     //   setCurrentLOcattion() ;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        modelView.detachView();
    }
    @Override
    public BaseModels<? extends BaseView> getModel() {
        return null;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
               // .addConnectionCallbacks(this)
               // .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        setCurrentLOcattion();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
               // setCurrentLOcattion();
            }
        } else {
            buildGoogleApiClient();
        }
    }


    private void setCurrentLOcattion() {
            LatLng latLng = new LatLng(Double.parseDouble(employee.getCoords().getLat()), Double.parseDouble(employee.getCoords().getLng()));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            //SetInfoCustomuze(mGoogleMap,null);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            //  markerOptions.draggable(true);
            Marker marker1 = mGoogleMap.addMarker(markerOptions);
            // markerId = marker1.getId() ;
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

       // }
    }
}
