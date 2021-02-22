package org.techtown.reducetheuseofplastic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
EditText edt_id,edt_pw,edt_name,edt_pw2,edt_email,edt_recommended,edt_phone;
Button register_btn;
private FirebaseAuth firebaseAuth;
private FirebaseDatabase firebaseDatabase;
private DatabaseReference databaseReference;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();//뒤로가기 버튼을 눌렸을 시
        return super.onSupportNavigateUp();//뒤로가기 버튼
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //액션바 등록하기 
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("회원가입");
        
        actionBar.setDisplayHomeAsUpEnabled(true);//뒤로가기버튼
        actionBar.setDisplayShowHomeEnabled(true);//홈 아이콘



        //파이어베이스 접근 설정

        firebaseAuth=FirebaseAuth.getInstance();//firebaseAuth객체의 공유 인스턴스 가져옴
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        edt_id=(EditText)findViewById(R.id.edt_id);
        edt_pw=(EditText)findViewById(R.id.edt_pw);
        edt_pw2=(EditText)findViewById(R.id.edt_pw2);
        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_recommended=(EditText)findViewById(R.id.edt_recommended);
        edt_phone=(EditText)findViewById(R.id.edt_phone);
        register_btn=(Button)findViewById(R.id.btn_register);
        System.out.println("id"+edt_id.toString());
        System.out.println("email"+edt_email.toString());
        //파이어베이스 user로 접근
        //가입 클릭 리스너-->firebase에 데이터를 저장한다.
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //가입정보 가져오기
                String id=edt_id.getText().toString().trim();
                String pw=edt_pw.getText().toString().trim();
                String pw2=edt_pw2.getText().toString().trim();
                String name=edt_name.getText().toString().trim();
                String email=edt_email.getText().toString().trim();
                String recommended=edt_recommended.getText().toString().trim();
                String phone=edt_phone.getText().toString().trim();

                System.out.print("비밀번호"+pw.toString());

                if(pw.equals(pw2)){
                   // Log.d(Register,"등록버튼"+id+","+pw);
                    final ProgressDialog progressDialog=new ProgressDialog(RegisterActivity.this);
                    progressDialog.setMessage("가입중입니다..");
                    progressDialog.show();

                    //writeNewUser(id,pw);


                    //파이어베이스에 신규계정 등록하기
                    firebaseAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //가입성공시
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                FirebaseUser user=firebaseAuth.getCurrentUser();
                                HashMap<Object,String>hashMap =new HashMap<>();
                                hashMap.put("id",id);
                                hashMap.put("pw",pw);
                                hashMap.put("name",name);
                                hashMap.put("email",email);
                                hashMap.put("recommended",recommended);
                                hashMap.put("phone",phone);
                                hashMap.put("point","0");
                                databaseReference.child(id).setValue(hashMap);
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                finish();
                                Toast.makeText(getApplicationContext(),"회원가입에 성공하셨습니다.",Toast.LENGTH_SHORT).show();
                            }else{
                                progressDialog.dismiss();
                                System.out.println("이메일"+email.toString());
                                Toast.makeText(RegisterActivity.this,"이미 존재하는 아이디 입니다.",Toast.LENGTH_SHORT).show();
                                return;//해당메소드 진행을 멈추고 빠져나감.
                            }
                        }
                    });

                }else {
                    Toast.makeText(RegisterActivity.this,"비밀번호입력이 틀렸습니다. 다시 입력해 주세요.",Toast.LENGTH_SHORT).show();
                    edt_pw.setText("");
                    edt_pw2.setText("");
                    return;
                }
            }
        });
        
    }
    /*
    private void writeNewUser(String getUserId,String getUserPw){
        User user = new User(getUserId,getUserPw);
       databaseReference.child("users").child(getUserId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               Toast.makeText(getApplicationContext(),"저장을 완료 했습니다.",Toast.LENGTH_SHORT).show();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(getApplicationContext(),"저장을 완료 했습니다.",Toast.LENGTH_SHORT).show();
           }
       });

    }

     */
}
