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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@SuppressLint("Registered")
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;

    private static final String ADDRESS = "teamAddress";
    private static final String TEAMVENUE = "teamVenue";
    private String address = "";
    private String teamVenue= "";
    private LatLng latLng;
    private boolean created;

    public static MapFragment newInstance(String teamVenue, String address) {
        MapFragment frag = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ADDRESS, address);
        args.putString(TEAMVENUE, teamVenue);
        frag.setArguments(args);
        return(frag);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_map, container, false);

        if(getArguments() != null){
            this.address = getArguments().getString(ADDRESS, "");
            this.teamVenue = getArguments().getString(TEAMVENUE, "");
        }

        this.mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);

        String location = this.address;
        List<Address> addressList = null;

        Geocoder geocoder = new Geocoder(this.getContext());
        try{
            addressList = geocoder.getFromLocationName(location, 1);
        } catch (IOException e){
            e.printStackTrace();
        }
        assert addressList != null;
        Address address = addressList.get(0);
        this.latLng = new LatLng(address.getLatitude(), address.getLongitude());
        created = false;

        return v;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (!created) {
            created = true;
            Marker marker = googleMap.addMarker(new MarkerOptions().position(this.latLng).title(teamVenue));
            marker.showInfoWindow();
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.latLng,8));
            mapFragment.getMapAsync(this);
        }
    }
}
