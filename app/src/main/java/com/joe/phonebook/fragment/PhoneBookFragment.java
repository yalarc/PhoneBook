package com.joe.phonebook.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joe.phonebook.R;
import com.joe.phonebook.adapter.ContactAdapter;
import com.joe.phonebook.application.BaseApplication;
import com.joe.phonebook.base.BaseFragment;
import com.joe.phonebook.dao.SYS_UserDAO;
import com.joe.phonebook.entity.SYS_User;
import com.joe.phonebook.entity.T_UserRelationship;
import com.joe.phonebook.listener.CallBackUserCount;
import com.joe.phonebook.utils.CharacterParser;
import com.joe.phonebook.weight.Sidebar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2017/2/21.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class PhoneBookFragment extends BaseFragment implements Sidebar.OnTouchingLetterChangedListener,TextWatcher,View.OnClickListener,CallBackUserCount{


    private ContactAdapter adapter;
    private List<T_UserRelationship> contactList;
    private ListView listView;
    private boolean hidden;
    private Sidebar sidebar;
    private TextView tv_total;
    private LayoutInflater infalter;
    private TextView mDialog;
    private CharacterParser characterParser;// 汉字转拼音
    private ArrayList<SYS_User> mSYSUser = null;
    private EditText mSearchInput;
    private ListView search_list; // 搜索的ListView
    private FrameLayout fragment_bottom;
    private int sizeAll ;
    private RelativeLayout re_function, re_unit_contacts, re_discussion_groups;
    private ProgressBar progress;
    private Activity activity = null;
    private ImageView btn_daiban_person;
    private ImageButton title_right_new_function;
    private ImageView bit_back;
    private FrameLayout frame_search;
    private ProgressBar progress_search;
    public SYS_UserDAO mSYS_UserDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contactlist,container,false);
    }

    /**
     * 判断宿主activity是否实现了接口MyListener
     * @param context
     */
    @Override
    public void onAttach(Activity context) {
        this.activity = context;
        super.onAttach(context);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {
        btn_daiban_person = (ImageView)getView().findViewById(R.id.btn_daiban_person);
        title_right_new_function = (ImageButton)getView().findViewById(R.id.title_right_new_function);
        progress = (ProgressBar) getActivity().findViewById(R.id.progress_);
        listView = (ListView) getView().findViewById(R.id.list);
        frame_search = (FrameLayout)getView().findViewById(R.id.frame_search);
        progress_search = (ProgressBar)getView().findViewById(R.id.progress_search);
        bit_back =(ImageView) getView().findViewById(R.id.bit_back);
        infalter = LayoutInflater.from(getActivity());
        mSearchInput = (EditText) getActivity().findViewById(
                R.id.school_friend_member_search_input);

        View headView = infalter.inflate(R.layout.item_contact_list_header,
                null);
        listView.addHeaderView(headView);

        re_function = (RelativeLayout) headView.findViewById(R.id.re_function);// 功能号

        re_unit_contacts = (RelativeLayout) headView
                .findViewById(R.id.re_unit_contacts);// 单位通讯录

        re_discussion_groups = (RelativeLayout) headView
                .findViewById(R.id.re_discussion_groups);// 讨论组

        View footerView = infalter.inflate(R.layout.item_contact_list_footer,
                null);
        listView.addFooterView(footerView);

        sidebar = (Sidebar) getActivity().findViewById(R.id.sidebar);
        mDialog = (TextView) getView().findViewById(R.id.floating_header);
        search_list = (ListView) getView().findViewById(R.id.search_list);
        fragment_bottom = (FrameLayout) getView().findViewById(
                R.id.fragment_bottom);
        tv_total = (TextView) footerView.findViewById(R.id.tv_total);


    }


    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

        mSYS_UserDAO = new SYS_UserDAO(getActivity(), BaseApplication.getApplication(getActivity()));
        contactList = new ArrayList<T_UserRelationship>();
        sidebar.setTextView(mDialog);
        sidebar.setOnTouchingLetterChangedListener(this);
        adapter = new ContactAdapter(getActivity(), R.layout.item_contact_list,
                contactList,this);
        listView.setSelected(true);
        listView.setAdapter(adapter);
        tv_total.setText(String.valueOf(contactList.size()) + "位联系人");
        btn_daiban_person.setOnClickListener(this);
        title_right_new_function.setOnClickListener(this);
        re_function.setOnClickListener(this);
        re_unit_contacts.setOnClickListener(this);
        bit_back.setOnClickListener(this);
        re_discussion_groups.setOnClickListener(this);
        mSearchInput.addTextChangedListener(this);

    }

    @Override
    public void onTouchingLetterChanged(String s) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void callUserCount(int count) {

    }
}
