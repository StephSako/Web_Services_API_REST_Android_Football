package com.example.footballapi.view.fragments;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.footballapi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

@SuppressLint("Registered")
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final String ADDRESS = "teamAddress";
    private static final String TEAMNAME = "teamName";
    private String address = "";
    private String teamName = "";

    public static MapFragment newInstance(String teamName, String address) {
        MapFragment frag = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ADDRESS, address);
        args.putString(TEAMNAME, teamName);
        frag.setArguments(args);
        return(frag);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_map, container, false);

        if(getArguments() != null){
            this.address = getArguments().getString(ADDRESS, "");
            this.teamName = getArguments().getString(TEAMNAME, "");
        }

        assert getFragmentManager() != null;
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        String location = address;
        List<Address> addressList = null;

        Geocoder geocoder = new Geocoder(this.getContext());
        try{
            addressList = geocoder.getFromLocationName(location, 1);
        } catch (IOException e){
            e.printStackTrace();
        }
        assert addressList != null;
        Address address = addressList.get(0);
        /*LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(teamName));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        mapFragment.getMapAsync(this);*/

        return v;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(latLng).title(teamName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
