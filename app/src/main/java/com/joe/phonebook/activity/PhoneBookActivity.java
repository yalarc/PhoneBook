package com.joe.phonebook.activity;

import android.app.DownloadManager;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.joe.phonebook.R;
import com.joe.phonebook.api.PhoneInit;
import com.joe.phonebook.base.BaseFragment;
import com.joe.phonebook.fragment.PhoneBookFragment;
import com.joe.phonebook.listener.AddressListener;
import com.joe.phonebook.listener.BackHandledInterface;
import com.joe.phonebook.task.DownLoadTask;
import com.joe.phonebook.utils.Contant;

import org.json.JSONObject;

/**
 * 通讯录入口
 */
public class PhoneBookActivity extends FragmentActivity implements AddressListener,BackHandledInterface {

    public BaseFragment baseFragment;
    public PhoneBookFragment phoneBookFragment;
    private Handler handler = new Handler();
    private DownLoadTask downLoadTask;
    private JSONObject jsonObject;
    private String phoneBookFragmentType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);

        phoneBookFragmentType = getIntent().getStringExtra("phoneBookFragmentType");
        Contant.START_TYPE = phoneBookFragmentType;
        initContent();

    }

    /**
     * 初始化显示的内容
     */
    private void initContent() {
        PhoneInit.getInstance().setPhoneType(phoneBookFragmentType);
        if(phoneBookFragmentType.equals(Contant.LOING_INIT)){
            phoneBookFragment = (PhoneBookFragment)PhoneInit.getInstance().getMyMap().get(phoneBookFragmentType);
        }else {
            phoneBookFragment = new PhoneBookFragment();
            PhoneInit.getInstance().getMyMap().put(phoneBookFragmentType,phoneBookFragment);

        }

        PhoneInit.getInstance().setPhoneBookFragment(phoneBookFragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content,phoneBookFragment);
        transaction.commitAllowingStateLoss();

    }

    @Override
    public void onClickChild(BaseFragment f) {
        switchContent(f);
    }

    private void switchContent(BaseFragment f) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onBackPressed() {
        PhoneInit.getInstance().setPhoneType(Contant.LOING_INIT);
        this.finish();
        super.onBackPressed();
    }

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        baseFragment = selectedFragment;
    }
}
