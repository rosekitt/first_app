package com.example.a07_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TodayActivity extends AppCompatActivity {

    public void onClickGood(View V) {
        Intent intentT = new Intent();
        intentT.putExtra("result","굿!");
        setResult(RESULT_OK,intentT);
        finish();
    }

    public  void onClickNozam (View V) {
        Intent intentT = new Intent();
        intentT.putExtra("result","노잼");
        setResult(RESULT_OK,intentT);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        TextView textInterpark, textFacebook, textYoutube, textNaver;
        textInterpark = findViewById(R.id.textViewInterpark);
        textFacebook = findViewById(R.id.textViewFacebook );
        textYoutube = findViewById(R.id.textViewYoutube);
        textNaver = findViewById(R.id.textViewNaver);

        Intent intentM = getIntent();
        if(intentM != null) {
            int nInterpark = intentM.getIntExtra("interpark",0);
            int nYoutube = intentM.getIntExtra("youtube",0);
            int nFacebook = intentM.getIntExtra("facebook",0);
            int nNaver = intentM.getIntExtra("naver",0);

            textInterpark.setText(nInterpark + "회 방문"); //1회 방문
            textYoutube.setText(nYoutube + "회 방문");
            textFacebook.setText(nFacebook + "회 방문");
            textNaver.setText(nNaver + "회 방문");
        }
    }
}