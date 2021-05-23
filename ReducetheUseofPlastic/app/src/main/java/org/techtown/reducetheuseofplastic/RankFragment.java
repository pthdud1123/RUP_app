package org.techtown.reducetheuseofplastic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RankFragment extends Fragment {
    
    MainActivity mainActivity;
    static final String[] ListMenu={"박소영","park so young","하우"};

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private TextView tempTextview;
    private ListView listView;
    private ArrayAdapter<String>adapter;
    List<Object> Array =new ArrayList<Object>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity=(MainActivity)getActivity();
        System.out.println("랭킹시스템 프래그먼트 붙었다");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity=null;
        System.out.println("랭킹시스템 떨어졌다");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.rank, container, false);
        View view=inflater.inflate(R.layout.rank,container,false);
        listView=(ListView)view.findViewById(R.id.listView_rank);
        tempTextview=(TextView)view.findViewById(R.id.TextView_rank);


        //initDatabase();

        mDatabaseReference=mFirebaseDatabase.getReference("users");
        System.out.println(mDatabaseReference.getKey());
        /*
      mDatabaseReference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              for(DataSnapshot messageData: snapshot.getChildren()){
                  String msg2=messageData.getValue().toString();
                  tempTextview.setText(msg2);
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

 */
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,ListMenu);
        listView.setAdapter(arrayAdapter);






        return view;
    }
    public void initDatabase(){



        mChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //항목 목록을 검색하거나 항목 목록에 대한 추가를 수신대기 합니다


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //목록의 항목에 대한 변경을 수신대기 합니다.

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //목록의 항목 삭제를 수신대기 합니다.

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //순서 있는 모고록의 항목 순서 변경을 수신대기 한다. 현재의 정렬 기준에 따라 항목의 순서 변경 원인이 된 onChildMoved()이벤트가 onChildChaged()이벤트를 항상 뒤따릅니다.



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabaseReference.removeEventListener(mChildEventListener);
    }
}