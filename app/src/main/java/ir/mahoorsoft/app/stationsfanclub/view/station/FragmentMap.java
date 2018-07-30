package ir.mahoorsoft.app.stationsfanclub.view.station;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.Pref;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.PrefKey;


/**
 * Created by M-gh on 07-Oct-17.
 */

public class FragmentMap extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {
    LatLng location;
    Marker selectedMarker = null;
    GoogleMap mMap;
    SupportMapFragment supportMapFragment;
    View view;
    boolean dataSaved = false;
    boolean mapReady = false;
    int selectedId = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        if (supportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            supportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map1, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_map, container, false);
            init();
        }

        return view;
    }

    private void init() {

        getDataFromServer();
    }

    private void getDataFromServer() {
/*        dialogProgres.showProgresBar();
        PresentTeacher presentTeacher = new PresentTeacher(this);
        presentTeacher.getAllTeacher();*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        location = new LatLng(29.4892550, 60.8612360);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                location).zoom(12).build();
        // mMap.setBuildingsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mapReady = true;
        setMarkers();
    }

    private void setMarkers() {
       // for (int i = 0; i < source.size(); i++) {
            LatLng location = new LatLng(29.4892550, 60.8612360);
            addMarker(location,"ok");
       // }

    }

    private void addMarker(LatLng location, String title) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        markerOptions.title("آموزشگاه : " + title + ", " + " برای مشاهده جزییات دوباره کلیک کنید.");
        Marker m = mMap.addMarker(new MarkerOptions()
                .position(location)
                .title("Melbourne")
                .snippet("Population: 4,137,400")
                .icon(BitmapDescriptorFactory.fromBitmap(G.getBitmapFromVectorDrawable(G.context, R.drawable.icon_gas_station))));
//        m.showInfoWindow();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                latLng).zoom(mMap.getCameraPosition().zoom).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Toast.makeText(G.context, marker.getSnippet(), Toast.LENGTH_SHORT).show();

        return true;
    }

    private void showDialog(String title, String message, String btntrue, String btnFalse) {
        AlertDialog.Builder builder = new AlertDialog.Builder(G.context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(btntrue, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                showCourseList();
                dialog.cancel();
            }
        });
        builder.setNegativeButton(btnFalse, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void showCourseList() {

    }

}

