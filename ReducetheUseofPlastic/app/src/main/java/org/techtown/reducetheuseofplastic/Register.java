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
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Register extends AppCompatActivity {
EditText edt_id,edt_pw;
Button register_btn;
private FirebaseAuth firebaseAuth;
private DatabaseReference databaseReference;
private FirebaseUser user;

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
        actionBar.setTitle("소영Create Account");
        
        actionBar.setDisplayHomeAsUpEnabled(true);//뒤로가기버튼
        actionBar.setDisplayShowHomeEnabled(true);//홈 아이콘
        
        //파이어베이스 접근 설정

        firebaseAuth=FirebaseAuth.getInstance();//firebaseAuth객체의 공유 인스턴스 가져옴
        user=firebaseAuth.getCurrentUser();

        edt_id=(EditText)findViewById(R.id.edt_id);
        edt_pw=(EditText)findViewById(R.id.edt_pw);
        register_btn=(Button)findViewById(R.id.reg_btn);
        
        //파이어베이스 user로 접근
        //가입 클릭 리스너-->firebase에 데이터를 저장한다.
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //가입정보 가져오기
                String id=edt_id.getText().toString().trim();
                String pw=edt_pw.getText().toString().trim();
                
                if(!id.equals("")&&!pw.equals("")){
                   // Log.d(Register,"등록버튼"+id+","+pw);
                    final ProgressDialog progressDialog=new ProgressDialog(Register.this);
                    progressDialog.setMessage("가입중입니다..");
                    progressDialog.show();

                    //writeNewUser(id,pw);


                    //파이어베이스에 신규계정 등록하기
                    firebaseAuth.createUserWithEmailAndPassword(id,pw).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //가입성공시
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                //FirebaseUser user=firebaseAuth.getCurrentUser();
                                String Id=user.getUid();
                                //해쉬맵 테이블을 파이어 베이스 데이터 베이스에 저장
                                HashMap<Object,String> hashMap=new HashMap<>();
                                hashMap.put("uid",id);
                                hashMap.put("upw",pw);

                                FirebaseDatabase database=FirebaseDatabase.getInstance();
                                DatabaseReference reference=database.getReference("users");
                                reference.child(id).setValue(hashMap);
                                
                                //가입이 이루어졌을시 가입화면을 빠져나감
                                Intent intent=new Intent(Register.this,Login.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(),"회원강비에 성공하셨습니다.",Toast.LENGTH_SHORT).show();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(Register.this,"이미 존재하는 아이디 입니다.",Toast.LENGTH_SHORT).show();
                                return;//해당메소드 진행을 멈추고 빠져나감.
                            }
                        }
                    });

                }
                
            }
        });
        
    }
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
}
