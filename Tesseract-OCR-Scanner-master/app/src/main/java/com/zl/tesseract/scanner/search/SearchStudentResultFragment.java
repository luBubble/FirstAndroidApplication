package com.zl.tesseract.scanner.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;

import java.util.LinkedList;
import java.util.List;

public class SearchStudentResultFragment extends Fragment implements AdapterView.OnItemClickListener{
    private EditText edit_search;
    private TextView search_btn;
    private TextView btn_back;
    private ListView list_search_result;
    private StudentResultAdapter mAdapter;
    private List<Student> mData;
    private FragmentManager fManager;

    @SuppressLint("ValidFragment")
    public SearchStudentResultFragment( FragmentManager fManager) {
        this.fManager = fManager;
    }

    public SearchStudentResultFragment() { }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search,container,false);
        edit_search=(EditText) view.findViewById(R.id.edit_search);
        search_btn=(TextView) view.findViewById(R.id.search_btn);
        btn_back=(TextView) view.findViewById(R.id.btn_back);
        list_search_result=(ListView) view.findViewById(R.id.list_search_result);
        list_search_result.setOnItemClickListener(this);
        //点击搜索
        search_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String searchKey=edit_search.getText().toString();
                        Toast.makeText(getActivity(),searchKey,Toast.LENGTH_LONG).show();
                        mData=new LinkedList<Student>();
                        mData.add(new Student("邓萍萍","20180330422","123456","中南光明小学","三年二班","9","13277354776","女"));
                        mAdapter=new StudentResultAdapter((LinkedList<Student>)mData,getActivity());
                        list_search_result.setAdapter(mAdapter);
                    }
                }
        );
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        AddStudentDetailFragment detailFragment = new AddStudentDetailFragment();
        Bundle bd = new Bundle();
        bd.putString("name", mData.get(i).getName());
        bd.putString("id", mData.get(i).getId());
        bd.putString("school", mData.get(i).getSchool());
        bd.putString("studentClass", mData.get(i).getStudentClass());
        bd.putString("age", mData.get(i).getAge());
        bd.putString("sex", mData.get(i).getSex());
        bd.putString("phone", mData.get(i).getPhone());
        detailFragment.setArguments(bd);
        //获取Activity的控件
        TextView txt_toolbar = (TextView) getActivity().findViewById(R.id.txt_toolbar);
        txt_toolbar.setText("学生信息详情");
        //加上Fragment替换动画
        //fTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
        fTransaction.replace(R.id.no_menu_framelayout, detailFragment);
        //调用addToBackStack将Fragment添加到栈中
        fTransaction.addToBackStack(null);
        fTransaction.commit();
    }
}
