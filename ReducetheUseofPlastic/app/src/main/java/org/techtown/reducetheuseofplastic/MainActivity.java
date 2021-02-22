package org.techtown.reducetheuseofplastic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_home, btn_rank, btn_mypage; //프레그먼트 전환 버튼
    public String userEmail;


    private long lastTimeBackPressed;
    MainFragment fragment_main;
    RankFragment fragment_rank;
    MyPageFragment fragment_mypage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent= getIntent();
        userEmail=intent.getStringExtra("userEmail");



        btn_home=(Button)findViewById(R.id.btn_home);
        btn_rank=(Button)findViewById(R.id.btn_rank);
        btn_mypage=(Button)findViewById(R.id.btn_mypage);

        btn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setFrag(0);
            }
        });


        btn_rank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setFrag(1);
            }
        });

        btn_mypage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MyPageFragment myPageFragment=new MyPageFragment();
                Bundle bundle=new Bundle();
                bundle.putString("userEmail",userEmail);
                myPageFragment.setArguments(bundle);
                setFrag(2);
            }
        });



        fragment_main=new MainFragment();
        fragment_rank=new RankFragment();
        fragment_mypage=new MyPageFragment();
        Bundle bundle=new Bundle();
        bundle.putString("userEmail",userEmail);
        fragment_main.setArguments(bundle);
        fragment_mypage.setArguments(bundle);
        setFrag(0);


    }
    public void setFrag(int n){
        switch (n){
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_main).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_rank).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_mypage).commit();
                break;
        }
    }
}