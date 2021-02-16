package org.techtown.reducetheuseofplastic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register2 extends AppCompatActivity {
    private EditText edt_email,edt_pw;
    private Button btn_reg;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
        Log.d("sy","Register2");

        firebaseAuth=FirebaseAuth.getInstance();
        /*
        if(firebaseAuth.getCurrentUser()!=null){
            //이미 로그인이 되어 있다면 액티비티를 종료 하고 main액티비티를 연다.
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        */

        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_pw=(EditText)findViewById(R.id.edt_pw);
        btn_reg=(Button)findViewById(R.id.btn_reg);



        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=edt_email.getText().toString().trim();
                String password=edt_pw.getText().toString().trim();
                ProgressDialog progressDialog=new ProgressDialog(Register2.this);
                progressDialog.setMessage("등록중입니다 기다려주세요");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"등록에러",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });


    }


}
