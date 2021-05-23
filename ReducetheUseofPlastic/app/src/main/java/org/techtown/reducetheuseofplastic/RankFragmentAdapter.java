package org.techtown.reducetheuseofplastic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RankFragmentAdapter extends BaseAdapter {
    private Context context;//액티비티 화면을 가져옴
    private ArrayList<UserIdPoint> data=new ArrayList<UserIdPoint>();
    private LayoutInflater layoutInflater;
    private int layout;

    public RankFragmentAdapter(Context context,int layout,ArrayList<UserIdPoint> data){
        this.context=context;
        this.data=data;
        this.layout=layout;
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=layoutInflater.inflate(layout,viewGroup,false);
        }
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView1);
        TextView textView1=(TextView)view.findViewById(R.id.textView1);
        TextView textView2=(TextView)view.findViewById(R.id.textView2);
        Button button=(Button)view.findViewById(R.id.button1);

        imageView.setImageResource(data.get(i).getPicture());
        textView1.setText(data.get(i).getUserId());
        textView2.setText(data.get(i).getUserPoint());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),data.get(i).getUserId()+"님은 아직 프로필 준비중",Toast.LENGTH_SHORT).show();;

            }
        });
        return view;
    }
}
