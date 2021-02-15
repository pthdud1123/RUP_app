package org.techtown.reducetheuseofplastic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText edt_id,edt_pw;
    Button btn_login;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_id = (EditText) findViewById(R.id.edt_id);
        edt_pw = (EditText) findViewById(R.id.edt_pw);
        btn_login = (Button) findViewById(R.id.btn_login);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        readUser();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUserId = edt_id.getText().toString();
                String getUerPw = edt_pw.getText().toString();

                HashMap result = new HashMap<>();
                result.put("Id", getUserId);
                result.put("Pw", getUerPw);

                writeNewUser( getUserId, getUerPw);
            }
        });
    }
    private void writeNewUser(String getUserId,String getUserPw) {

        User user=new User(getUserId,getUserPw);
        mDatabase.child("users").child(getUserId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this,"저장을 완료 했습니다.",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"저장을 실패했습니다.",Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void readUser(){
        mDatabase.child("users").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(User.class)!=null){
                    User post=snapshot.getValue(User.class);
                    Log.w("FireBaseData","getData"+post.toString());
                }else{
                    Toast.makeText(MainActivity.this,"데이터 없음",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("FireBaseData","loadPost:onCancelled",error.toException());

            }
        });
    }
}