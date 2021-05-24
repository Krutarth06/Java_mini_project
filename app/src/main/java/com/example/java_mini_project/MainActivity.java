package com.example.java_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button seeStat, cowinLink, callHelpline, emailHelpline;
    ImageView Symptoms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seeStat = findViewById(R.id.See_stat_btn_id);
        cowinLink = findViewById(R.id.Cowin_portal_btn_id);
        Symptoms = findViewById(R.id.symptom_banner_id);
        callHelpline = findViewById(R.id.call_btn);
        emailHelpline = findViewById(R.id.email_btn);

        callHelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        emailHelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ncov2019@gov.in"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "I am having Covid-19 Emergency.");
                intent.putExtra(Intent.EXTRA_TEXT, "Name: \nPhone number: \nAddress: \n I am having following symptoms:");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        Symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), symptom_checker.class));
            }
        });

        seeStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Statistics.class));
            }
        });
        cowinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.cowin.gov.in/home");
            }
        });

    }
    private void call() {
        String number = "1075";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}