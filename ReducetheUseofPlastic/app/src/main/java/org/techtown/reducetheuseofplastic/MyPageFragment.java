package org.techtown.reducetheuseofplastic;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MyPageFragment extends Fragment {
    private ImageView iv_qr;
    private String userEmail;
    MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity=(MainActivity)getActivity();
        System.out.println("프래그먼트 홈");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity=null;
        System.out.println("프래그먼트 홈 떨어졌다");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.mypage, container, false);


        iv_qr=(ImageView)rootView.findViewById(R.id.imageview_qrcode);
        //개인 QR이 바로 응답할 수 있게 해야 함
        userEmail="pthdud1123";
        if(getArguments()!=null){
            userEmail=getArguments().getString("userEmail");
            System.out.println(userEmail);

        }


        System.out.println("야야야야야양" + userEmail);

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try{
            BitMatrix bitMatrix=multiFormatWriter.encode(userEmail, BarcodeFormat.QR_CODE,100,100);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            iv_qr.setImageBitmap(bitmap);
        }catch (Exception e){

        }
        return rootView;

    }
}