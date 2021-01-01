package com.example.a07_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ImageView img = findViewById(R.id.imageView);
        TextView text = findViewById(R.id.textView);

        Intent intenR = getIntent();
        if(intenR != null) {
            int nImg = intenR.getIntExtra("img",0);
            String sText = intenR.getStringExtra("text");
            img.setImageResource(nImg);
            text.setText(sText);
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}