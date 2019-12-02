package com.example.footballapi.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.footballapi.R;

import java.util.Objects;

@SuppressLint("Registered")
public class CreditsFragment extends Fragment {

    public View v;

    public CreditsFragment() {}
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.credits, container, false);
        Objects.requireNonNull(this.getActivity()).setTitle("A propos");
        return v;
    }
}
