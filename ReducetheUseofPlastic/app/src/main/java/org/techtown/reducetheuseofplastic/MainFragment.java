package org.techtown.reducetheuseofplastic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    String userEmail;
    MainActivity mainActivity;
    Context context;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.main_fragment, container, false);


        if(getArguments()!=null){
            userEmail=getArguments().getString("userEmail");
            System.out.println(userEmail);

        }
        return rootView;
    }
}