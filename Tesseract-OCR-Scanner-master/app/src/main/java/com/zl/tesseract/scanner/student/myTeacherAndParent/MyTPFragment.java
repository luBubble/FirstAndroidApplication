package com.zl.tesseract.scanner.student.myTeacherAndParent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Teacher;
import com.zl.tesseract.scanner.mainView.MyStudentDetailActivity;

import java.util.LinkedList;
import java.util.List;

public class MyTPFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView list_tp;
    private List<Teacher> mData;
    private TextView txt_search;
    private FragmentManager fManager;
    private TPAdapter mAdapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_and_list,container,false);
        list_tp=(ListView)view.findViewById(R.id.list);
        list_tp.setOnItemClickListener(this);//不能丢,监听点击item
        mData=new LinkedList<Teacher>();
        mData.add(new Teacher("20180330000","13277089625","刘苇苇","中南光明小学","三年二班的数学老师"));
        mAdapter=new TPAdapter(mData,getActivity());
        list_tp.setAdapter(mAdapter);

//        txt_search=(TextView)view.findViewById(R.id.txt_search);
//        txt_search.setOnClickListener(//点击搜索
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                }
//        );

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Bundle bd = new Bundle();
        Intent tpDetail = new Intent(getActivity(), MyStudentDetailActivity.class);
//        studentDetail.putExtras(bd);//传值给activity
        startActivity(tpDetail);//打开详情页
    }
}
