package com.example.a07_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    int nInterpark, nfacebook, nYoutube, nNaver;
    final static int MOVE_TODAY_REQ = 100;
    final static int MOVE_RECENT_REQ = 101;

    public void onClickInterpark(View v){
 //       String str = getString(R.string.interpark_msg);
 //       Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        Intent intentM = new Intent(getApplicationContext(),RecentActivity.class);
        intentM.putExtra("web","interpark");
        //startActivity(intentM);
        startActivityForResult(intentM, MOVE_RECENT_REQ);

    }

    public void onClickImg(View v){
        int nId =v.getId();

        switch(nId) {
            case R.id.imageViewYoutube :
                Toast.makeText(getApplicationContext(),R.string.youtube_msg,Toast.LENGTH_LONG).show();
                break;
            case R.id.imageViewFacebook :
                Toast.makeText(getApplicationContext(),R.string.facebook_msg,Toast.LENGTH_LONG).show();
                break;
            case R.id.imageViewNaver :
                Toast.makeText(getApplicationContext(),R.string.naver_comic_msg,Toast.LENGTH_LONG).show();
            {
                Intent intentM = new Intent(this, RecentActivity.class);
                intentM.putExtra("web", "naver_comic");
                //startActivity(intentM);
                startActivityForResult(intentM, MOVE_RECENT_REQ);
            }break;
            }

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textInterpark = findViewById(R.id.textViewInterpark);
        TextView textYoutube = findViewById(R.id.textViewYoutube);
        TextView textFacebook = findViewById(R.id.textViewFacebook);
        TextView textNaver = findViewById(R.id.textViewNaver);
        TextView textTitle = findViewById(R.id.textViewTitle);


        textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentM = new Intent(getApplicationContext(),TodayActivity.class);
                intentM.putExtra("interpark",nInterpark);
                intentM.putExtra("facebook",nfacebook);
                intentM.putExtra("youtube",nYoutube);
                intentM.putExtra("naver",nNaver);
                startActivityForResult(intentM,MOVE_TODAY_REQ);
            }
        });
        textInterpark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.interpark.com"));
                nInterpark++; //nInterpark+=1;
                startActivity(intent);
            }
        });

        textFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
                 nfacebook++;
                 startActivity(intent);
            }
        });

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.textViewYoutube){
                        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com"));
                        nYoutube++;
                        startActivity(intent);
                } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.comic.naver.com"));
                            nNaver++;
                            startActivity(intent);
                    }
            }
        };

        textYoutube.setOnClickListener(ocl);
        textNaver.setOnClickListener(ocl);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == MOVE_TODAY_REQ){
            if(resultCode == RESULT_OK) {
                String strResult = data.getStringExtra("result");
                Toast.makeText(getApplicationContext(),"오늘" + strResult, Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(this,"취소되었습니다.",Toast.LENGTH_LONG).show();
        } else if(requestCode == MOVE_RECENT_REQ) {
            if (resultCode == RESULT_OK) {
                String strResult = data.getStringExtra("result");
                Toast.makeText(getApplicationContext(), strResult, Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(this,"취소되었습니다.",Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}