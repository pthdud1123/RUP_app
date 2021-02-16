package org.techtown.reducetheuseofplastic;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button btn_createQR,btn_scanQR;
    private ImageView qrcode;
    private String text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_createQR=(Button)findViewById(R.id.btn_createQR);
        btn_scanQR=(Button)findViewById(R.id.btn_scanQR);
        qrcode=(ImageView)findViewById(R.id.qrcode);

        btn_createQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text="https://park-duck.tistory.com";
                MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix=multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,100,100);
                    BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                    Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                    qrcode.setImageBitmap(bitmap);
                }catch (Exception e){

                }

            }
        });


    }
}