package com.example.footballapi.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.footballapi.R;

@SuppressLint("Registered")
public class TeamWebFragment extends Fragment {

    private static final String TEAM_URL = "TEAM_URL";
    private String teamURL = "";

    public static TeamWebFragment newInstance(String teamURL) {
        TeamWebFragment frag = new TeamWebFragment();
        Bundle args = new Bundle();
        args.putString(TEAM_URL, teamURL);
        frag.setArguments(args);
        return(frag);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_website, container, false);

        if(getArguments() != null){
            this.teamURL = getArguments().getString(TEAM_URL, "");
        }

        WebView webview = v.findViewById(R.id.wvTeamWebsite);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(this.teamURL);

        return v;
    }
}
