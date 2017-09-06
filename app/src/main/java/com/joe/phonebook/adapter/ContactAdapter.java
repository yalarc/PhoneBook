package com.joe.phonebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.joe.phonebook.R;
import com.joe.phonebook.api.PhoneInit;
import com.joe.phonebook.entity.SYS_User;
import com.joe.phonebook.entity.T_UserRelationship;
import com.joe.phonebook.listener.CallBackUserCount;
import com.joe.phonebook.myenum.PeopleHeadEnum;
import com.joe.phonebook.utils.Contant;
import com.joe.phonebook.utils.LoadUserAvatar;
import com.joe.phonebook.weight.CircleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2017/2/21.
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {

    private LayoutInflater inflater;
    private Context context;
    private int res;
    private List<T_UserRelationship> list;
    private LoadUserAvatar avatarLoader;
    private CallBackUserCount mCallBackUserCount;
    private PeopleHeadEnum mPeopleHeadEnum;


    public ContactAdapter(Context context, int resource,
                          List<T_UserRelationship> objects, CallBackUserCount mCallBackUserCount) {
        this.context = context;
        this.mCallBackUserCount = mCallBackUserCount;
        if (list == null) {
            list = new ArrayList<T_UserRelationship>();
        } else {
            this.list = objects;
        }
        this.res = resource;
        res = resource;
        avatarLoader = new LoadUserAvatar(context, Contant.SDCARD_PATH);
        mPeopleHeadEnum = PhoneInit.getInstance().getPeopleHeadEnum();
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<T_UserRelationship> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final T_UserRelationship mT_UserRelationship = list.get(position);
        if (convertView == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(res, null);
            holder = new ViewHolder();

            holder.ivAvatar = (ImageView) convertView
                    .findViewById(R.id.iv_avatar);

            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvHeader = (TextView) convertView.findViewById(R.id.header);
            holder.tv_phone = (TextView) convertView
                    .findViewById(R.id.tv_phone);
            holder.iv_SMS = (ImageView) convertView.findViewById(R.id.iv_SMS);
            holder.iv_phone = (ImageView) convertView
                    .findViewById(R.id.iv_phone);
            holder.user_relative = (RelativeLayout) convertView
                    .findViewById(R.id.user_relative);
            holder.default_tv = (TextView) convertView.findViewById(R.id.default_tv);
            TextPaint tp = holder.tvHeader.getPaint();
            tp.setFakeBoldText(true);
            holder.viewTemp = (View) convertView.findViewById(R.id.view_temp);


            convertView.setTag(holder);
        } else if (((ViewHolder) convertView.getTag()).needInflate) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(res, null);
            holder = new ViewHolder();

            holder.ivAvatar = (ImageView) convertView
                    .findViewById(R.id.iv_avatar);

            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvHeader = (TextView) convertView.findViewById(R.id.header);
            holder.tv_phone = (TextView) convertView
                    .findViewById(R.id.tv_phone);
            holder.iv_SMS = (ImageView) convertView.findViewById(R.id.iv_SMS);
            holder.iv_phone = (ImageView) convertView
                    .findViewById(R.id.iv_phone);
            holder.user_relative = (RelativeLayout) convertView
                    .findViewById(R.id.user_relative);
            TextPaint tp = holder.tvHeader.getPaint();
            tp.setFakeBoldText(true);
            holder.viewTemp = (View) convertView.findViewById(R.id.view_temp);
            holder.default_tv = (CircleView) convertView.findViewById(R.id.default_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int index = position % Contant.colorList.length;
        if (mT_UserRelationship.getmSYS_User().getMobile() != null && mT_UserRelationship.getmSYS_User().getMobile().equals("")) {
            if (mT_UserRelationship.getmSYS_User().getTelephone().equals("")) {
                holder.iv_phone.setImageResource(R.drawable.btn_phone_disabled);
                holder.iv_phone.setEnabled(false);
            } else {
                holder.iv_phone.setImageResource(R.drawable.btn_phone_selected);
                holder.iv_phone.setEnabled(true);
            }

        } else {
            holder.iv_phone.setImageResource(R.drawable.btn_phone_selected);
            holder.iv_phone.setEnabled(true);
        }
        if(mT_UserRelationship.getmSYS_User().getIsEMIUser() != null) {
            //如果是EMI用户 就可以进行短信
            if (mT_UserRelationship.getmSYS_User().getIsEMIUser() == 1) {
                holder.iv_SMS.setVisibility(View.VISIBLE);
            } else {
                holder.iv_SMS.setVisibility(View.GONE);
            }
        }
        UserListListener mUserListListener = new UserListListener(mT_UserRelationship);
        holder.iv_SMS.setOnClickListener(mUserListListener);
        holder.iv_phone.setOnClickListener(mUserListListener);
        holder.user_relative.setOnClickListener(mUserListListener);

        return null;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }
    class UserListListener implements View.OnClickListener {
        private T_UserRelationship mT_UserRelationship;
        public UserListListener(T_UserRelationship mT_UserRelationship){
            this.mT_UserRelationship = mT_UserRelationship;
        }
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            if(arg0.getId() == R.id.iv_SMS){
                PhoneInit.getInstance().getmCallbackMX().callbackSendMessage(mT_UserRelationship.getCUserId());
            }else if(arg0.getId() == R.id.iv_phone){
                SYS_User mSYS_User = mT_UserRelationship.getmSYS_User();
                if(mSYS_User.getTelephone().equals("") ){
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mSYS_User.getMobile()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else if(mSYS_User.getMobile().equals("") && !mSYS_User.getTelephone().equals("")){
                    Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+mSYS_User.getTelephone()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else if(!mSYS_User.getMobile().equals("") && !mSYS_User.getTelephone().equals("")){
                    CallPhonePopupWindow menuWindow = new CallPhonePopupWindow(context, UserListListener.this,mSYS_User.getMobile(),mSYS_User.getTelephone());
                    //显示窗口
                    menuWindow.showAtLocation(arg0, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                }
//				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mT_UserRelationship.getmSYS_User().getMobile()));
//				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(intent);
            }else if(arg0.getId() == R.id.user_relative){
                BookInit.getInstance().setCurrentUser(mT_UserRelationship.getmSYS_User());
                Intent intent = new Intent(context, UserDetailsActivity.class);
                context.startActivity(intent);
            }else if(arg0.getId() == R.id.bt_call_phone || arg0.getId() == R.id.bt_call_phone_2){
                SYS_User mSYS_User = mT_UserRelationship.getmSYS_User();
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+arg0.getTag().toString()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }else if(arg0.getId() == R.id.bt_call_landline || arg0.getId() == R.id.bt_call_landline_2){//拨打座机号
                SYS_User mSYS_User = mT_UserRelationship.getmSYS_User();
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+arg0.getTag().toString()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }

    class ViewHolder {

        ImageView ivAvatar;
        TextView tvName;
        TextView tvHeader;
        View viewTemp;
        TextView tv_phone;
        ImageView iv_SMS;
        ImageView iv_phone;
        TextView default_tv;
        RelativeLayout user_relative;
        boolean needInflate = false;
    }
}
