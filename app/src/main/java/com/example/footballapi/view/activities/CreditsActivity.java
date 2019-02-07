package com.example.footballapi.view.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.footballapi.R;

@SuppressLint("Registered")
public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

        TextView tvCredits = findViewById(R.id.tvCredits);
        tvCredits.setText(getString(R.string.DescriptionAPropos));
    }
}
