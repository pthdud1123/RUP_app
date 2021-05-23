package org.techtown.reducetheuseofplastic;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView textViewId,textViewPoint;
    private TextView textViewLv;

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
        textViewId=(TextView)rootView.findViewById(R.id.mypage_id);
        textViewLv=(TextView)rootView.findViewById(R.id.mypage_rank);
        textViewPoint=(TextView)rootView.findViewById(R.id.mypage_point);
        if(getArguments()!=null){
            userEmail=getArguments().getString("userEmail");
            System.out.println(userEmail);
            textViewId.setText(userEmail);
            textViewLv.setText("Lv.5");//일단 정적으로 고정

        }


        System.out.println("야야야야야양" + userEmail);

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try{
            BitMatrix bitMatrix=multiFormatWriter.encode(userEmail, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            iv_qr.setImageBitmap(bitmap);
        }catch (Exception e){

        }
        return rootView;

    }
}