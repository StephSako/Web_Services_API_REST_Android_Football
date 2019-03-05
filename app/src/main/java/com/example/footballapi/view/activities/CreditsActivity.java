package com.example.footballapi.view.activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.footballapi.R;

import java.util.Objects;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

@SuppressLint("Registered")
public class CreditsActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

        this.setTitle("A propos");

        TextView contents = findViewById(R.id.tvCredits);
        contents.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
    }
}
