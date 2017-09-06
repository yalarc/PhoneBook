package com.joe.phonebook.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.joe.phonebook.listener.BackHandledInterface;


public abstract class BaseFragment extends Fragment {
	public abstract boolean onBackPressed();

	protected BackHandledInterface mBackHandledInterface;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!(getActivity() instanceof BackHandledInterface)) {
			throw new ClassCastException(
					"Hosting Activity must implement BackHandledInterface");

		} else {
			this.mBackHandledInterface = (BackHandledInterface) getActivity();
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		try{
			initView();
			initData();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void onStart() {
		super.onStart();
		// 告诉FragmentActivity，当前Fragment在栈顶
		mBackHandledInterface.setSelectedFragment(this);
	}

	protected abstract void initView();

	protected abstract void initData();

}
