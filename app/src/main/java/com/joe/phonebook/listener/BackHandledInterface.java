package com.joe.phonebook.listener;


import com.joe.phonebook.base.BaseFragment;

/**
 * 返回栈顶的Fragment
 * @author Tony
 *
 */
public interface BackHandledInterface {

    public abstract void setSelectedFragment(BaseFragment selectedFragment);
}