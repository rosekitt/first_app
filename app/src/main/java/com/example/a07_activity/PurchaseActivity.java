package com.example.a07_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PurchaseActivity extends AppCompatActivity {
    int nPrice, nTotalPrice;
    TextView textProduct, textPrice;
    EditText editNumber;
    RadioGroup rgPayment, rgSize;
    CheckBox cbPoint;
    int nProductNo;


    public void onClickInc(View v) {
        String sNum = editNumber.getText().toString();
        int nNum = Integer.parseInt(sNum);
        nNum++;
        nTotalPrice = nPrice * nNum;
        editNumber.setText(String.valueOf(nNum));
        textPrice.setText("가격 : "+ String.valueOf(nTotalPrice) + "원");
    }
     public void onClickDec (View v) {
         String sNum = editNumber.getText().toString();
         int nNum = Integer.parseInt(sNum);
         if ( nNum > 1) {
             nNum--;
         }
         nTotalPrice = nPrice * nNum;
         editNumber.setText(String.valueOf(nNum));
         textPrice.setText("가격 : "+ String.valueOf(nTotalPrice) + "원");
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        textProduct = findViewById(R.id.textViewProduct);
        textPrice = findViewById(R.id.textViewPrice);
        editNumber = findViewById(R.id.editTextNumber);
        rgPayment =findViewById(R.id.rgPayment);
        rgSize = findViewById(R.id.rgSize);
        cbPoint = findViewById(R.id.checkPoint);

        Intent intentR = getIntent();
        if( intentR != null) {
            String sProduct = "";
            String sPrice = "";
            nProductNo = intentR.getIntExtra("productNo", 1);
            switch (nProductNo) {
                case 1: //미마마스크
                    sProduct = "미마마스크";
                    nPrice = 990;
                    nTotalPrice = nPrice * 1;
                    sPrice = "가격 : " + nTotalPrice + "원";
                    break;
                case 2:
                    sProduct = "어린이용 마스크";
                    nPrice = 36000;
                    nTotalPrice = nPrice * 1;
                    sPrice = "가격 : " + nTotalPrice + "원"; //가격 : 36000원
                    break;
                case 3:
                    sProduct = "데일리 마스크";
                    nPrice = 1400;
                    nTotalPrice = nPrice * 1;
                    sPrice = "가격 : " + nTotalPrice + "원";
                    break;
            }
            textProduct.setText(sProduct);
            textPrice.setText(sPrice);
            editNumber.setText("1");

            rgPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    String sPayment = "";
                    switch (checkedId) {
                        case R.id.radioCash:
                            sPayment = "결제방식 : " + "현금";
                            break;
                        case R.id.radioCredit:
                            sPayment = "결제방식 : " + "신용카드";
                            break;
                        case R.id.radioMobile:
                            sPayment = "결제방식 : " + "모바일";
                            break;
                    }
                    Toast.makeText(getApplicationContext(), sPayment, Toast.LENGTH_LONG).show();
                }
            });

            rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    String sSize = "";
                    switch (checkedId) {
                        case R.id.radioLarge:
                            sSize = "대";
                            break;
                        case R.id.radioMid:
                            sSize = "중";
                            break;
                        case R.id.radioSmall:
                            sSize = "소";
                            break;
                    }
                    Toast.makeText(getApplicationContext(), sSize, Toast.LENGTH_LONG).show();
                }
            });
        }

        cbPoint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked); //isChecked == true
                    Toast.makeText(getApplicationContext(),"포인트 적용", Toast.LENGTH_LONG).show();
            }
        });

        final CheckBox cbPack = findViewById(R.id.checkPack);
        cbPack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked); //isChecked == true
                    Toast.makeText(getApplicationContext(),"선물용 포장", Toast.LENGTH_LONG).show();
            }
        });

        Button btnOk = findViewById(R.id.buttonOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sPrice = textPrice.getText().toString();
                int nPayment = rgPayment.getCheckedRadioButtonId();
                int nSize = rgSize.getCheckedRadioButtonId();
                String sResult = textProduct.getText().toString() + "\n";

                switch (nPayment) {
                    case R.id.radioCash :
                        sResult += "결제방식 : 현금 \n";
                        break;
                    case R.id.radioCredit :
                        sResult += "결제방식 : 카드 \n";
                        break;
                    case R.id.radioMobile :
                        sResult += "결제방식 : 모바일 \n";
                        break;
                }

                if(nSize == R.id.radioLarge)
                    sResult += "크기 : 대\n";
                else if(nSize == R.id.radioMid)
                    sResult += "크기 : 중\n";
                else if(nSize == R.id.radioSmall)
                    sResult += "크기 : 소\n";

                if(cbPoint.isChecked())
                    sResult += "포인트 적립";

                if(cbPack.isChecked())
                    sResult += "선물용 포장";

                sResult += sPrice;

                Intent intentP = new Intent();
                intentP.putExtra("result",sResult);
                setResult(RESULT_OK,intentP);
                finish();
            }
        });

        Button btnCancel = findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }

    });
    }//onCreate
} //class