package com.example.vroch.hubuece.fragments;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.example.vroch.hubuece.R;
import com.example.vroch.hubuece.data.dao.MapLocationDao;
import com.example.vroch.hubuece.data.model.MapLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Map;

/**
 * Created by vroch on 14/12/2015.
 */
public class MapFragment extends Fragment {

    private GoogleMap googleMap;
    private MapView mapView;

    public static MapFragment newInstance() {

        Bundle args = new Bundle();

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_map_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //setUpMapIfNeeded();
        View v =  inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) v.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mapView.getMap();


//
//        // Changing marker icon
//        marker.icon(BitmapDescriptorFactory
//                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
//
//        // adding marker
//        googleMap.addMarker(marker);
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(-33.86997, 151.2089)).zoom(12).build();
//        googleMap.animateCamera(CameraUpdateFactory
//                .newCameraPosition(cameraPosition));


        MapLocationDao dao = new MapLocationDao(getActivity());
        List<MapLocation> mapLocations = dao.getAll();

        for (int i = 0;i<mapLocations.size();i++){

            MapLocation location  = mapLocations.get(i);

            // create marker
            MarkerOptions marker = new MarkerOptions().position(
                new LatLng(location.getLatitude(), location.getLongitude())).title(location.getNome());

            googleMap.addMarker(marker);
        }


        double defaultLatitude = -3.785603;
        double defaultLongitude = -38.552485;

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(defaultLatitude, defaultLongitude), 16.0f));

        // Perform any camera updates here
        return v;


    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }



}
