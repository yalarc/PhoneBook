package com.joe.phonebook.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joe.phonebook.application.BaseApplication;
import com.joe.phonebook.db.DBManager;
import com.joe.phonebook.entity.SYS_User;
import com.joe.phonebook.entity.TD_User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 人员部门关系DAO，展示
 * Created by Think on 2017/1/18.
 */

public class ViewSYS_OrgUserDAO {

    private static final String TABLE_NAME = "org_user_org";
    /**
     * 用户ID
     * */
    private static final String USERID = "user_id";
    /**
     * 集团单位ID
     * */
    private static final String GROUPCORPID = "group_corp_id";
    /**
     * 用户编码
     * */
    private static final String USERCODE = "user_code";
    /**
     * 用户姓名
     * */
    private static final String FULLNAME = "user_name";
    /**
     * 用户类型,
     * 0-虚拟用户，
     * 1-非平台用户，
     * 2-EMM平台用户，
     * 3-EMP移动端用户，
     * 4-EMI消息用户
     * */
    private static final String USERTYPE = "user_type";
    /**
     * 是否管理员,
     * 0-非管理员，
     * 1-业务管理员，
     * 2-单位管理员，
     * 3-超级管理员
     * */
    private static final String ADMINTYPE = "admin_type";
    /**
     * EMI类型,
     * 0-未开通，
     * 1-开通
     * */
    private static final String EMITYPE = "emi_type";
    /**
     * 用户登录类型，
     * 0-无登录账号，
     * 1-平台账号密码登录，
     * */
    private static final String LOGINTYPE = "login_type";
    /**
     * 登录账号
     * */
    private static final String LOGINNAME = "login_name";
    /**
     * 登录密码
     * */
    private static final String PASSWORD = "login_password";
    /**
     * 用户昵称
     * */
    private static final String USER_NICKNAME = "user_nickname";
    /**
     * 用户拼音,同时包含全拼与简拼，用逗号分开。
     * */
    private static final String USER_PYNAME = "user_pyname";
    /**
     * 默认单位ID
     * */
    private static final String CORPID = "corp_id";
    /**
     * 默认部门Id
     * */
    private static final String ORGID = "org_id";
    /**
     * 显示顺序，用于根据所属部门排序。
     * */
    private static final String DISPLAY_ORDER = "display_order";
    /**
     * 头像类型,
     * 0-自己上传头像图片，
     * 1-默认图标，
     * 2-显示人名，
     * 3-显示姓，
     * 4-显示昵称。
     * */
    private static final String HEADTYPE = "head_type";
    /**
     * 头像图片ID，
     * 0-表示没有，
     * 不为0则对应图片表的图片ID
     * */
    private static final String HEADPICTUREID = "head_picture_id";
    /**
     * 手机号码
     * */
    private static final String MOBILE = "mobile_phone";
    /**
     * 办公电话
     * */
    private static final String OFFICE = "office_phone";
    /**
     * 用户状态，
     * -1 删除
     * 0-停用，
     * 1-正常
     * */
    private static final String STATUS = "status_flag";
    /***/
    private static final String PHOTOSURL = "pic_path";
    /**
     * 新增，职务
     */
    private static final String TITLE = "title";
    /**
     * 性别,
     * 0-未知，
     * 1-男，
     * 2-女
     * */
    private static final String GENDER = "gender";
    /**
     * 生日
     * */
    private static final String BIRTHDAY = "birthday";
    /**
     * 邮箱
     * */
    private static final String EMAIL = "email";
    /**
     * 家庭电话
     * */
    private static final String HOMEPHONE = "home_phone";
    /**
     * 办公地点
     * */
    private static final String OFFICEADDRESS = "office_address";
    /**
     * 邮编
     * */
    private static final String POSTALCODE = "postal_code";
    /**
     * 备注
     * */
    private static final String REMARK = "remark";
    /**
     * 单位名称
     * */
    private static final String CORP_NAME = "corp_name";
    /**
     * 组织名称
     * */
    private static final String ORG_NAME = "org_name";
    private static final String EFS1 = "efs1";
    private static final String EFS2 = "efs2";
    private static final String EFS3 = "efs3";
    private static final String EFS4 = "efs4";
    private static final String EFS5 = "efs5";
    private static final String EFS6 = "efs6";
    private static final String EFS7 = "efs7";
    private static final String EFS8 = "efs8";
    private static final String EFS9 = "efs9";
    private static final String EFS10 = "efs10";
    private static final String EFI1 = "efi1";
    private static final String EFI2 = "efi2";
    private static final String EFI3 = "efi3";
    private static final String EFI4 = "efi4";
    private static final String EFI5 = "efi5";
    private static final String EFD1 = "efd1";
    private static final String EFD2 = "efd2";
    private static final String EFN1 = "efn1";
    private static final String EFN2 = "efn2";
    private static final String EFN3 = "efn3";
    private SQLiteDatabase db;

    private Context context;

    private SimpleDateFormat format = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");;
    private BaseApplication myApp;

    public ViewSYS_OrgUserDAO(Context context, BaseApplication myApp) {
        db = DBManager.getInstance(context);
        this.context = context;
        this.myApp = myApp;
    }
    public ViewSYS_OrgUserDAO(Context context) {
        db = DBManager.getInstance(context);
        this.context = context;
    }

    /**
     * 搜索对应部门下的人员
     * @param DepartmentCode
     * @param keyword
     * @return
     */
    public synchronized ArrayList<SYS_User> findIdByUser(String DepartmentCode , String keyword){
        ArrayList<SYS_User> userList = new ArrayList<SYS_User>();
        if(db != null && db.isOpen()){
            Cursor cursor = null;
            try{
                String sql = "";
                if(DepartmentCode == null || "".equals(DepartmentCode)){
                    sql  = "select * from v_org_user_org ouo JOIN V_User u on u.user_id = ouo.user_id where (u.status_flag = 1  and ( u.user_name like '%"+keyword+"%' or u.user_pyname like '%"+keyword+"%' or u.office_phone like '%"+keyword+"%' or u.mobile_phone like '%"+keyword+"%'or u.title like '%"+keyword+"%' or ouo.org_name like '%"+keyword+"%')) order by u.display_order ";

                }else{
                    sql  = "select * from v_org_user_org ouo JOIN V_User u on u.user_id = ouo.user_id where (ouo.tree_code like '"+DepartmentCode+"%' and u.status_flag = 1  and ( u.user_name like '%"+keyword+"%' or u.user_pyname like '%"+keyword+"%' or u.office_phone like '%"+keyword+"%' or u.mobile_phone like '%"+keyword+"%'or u.title like '%"+keyword+"%' or ouo.org_name like '%"+keyword+"%')) order by u.display_order ASC";

                }

                cursor = db.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    SYS_User mSYS_User = new SYS_User();
                    String userId = cursor.getString(cursor.getColumnIndex(USERID));
                    mSYS_User.setUserId(userId);
                    String Password = cursor.getString(cursor
                            .getColumnIndex(PASSWORD));
                    String user_pin = cursor.getString(cursor
                            .getColumnIndex(USER_PYNAME));
                    mSYS_User.setPassword(Password);
                    String FullName = cursor.getString(cursor
                            .getColumnIndex(FULLNAME));
                    short head_type = cursor.getShort(cursor.getColumnIndex(HEADTYPE));
                    mSYS_User.setHeadType(head_type);
                    short headPictureId = cursor.getShort(cursor.getColumnIndex(HEADPICTUREID));
                    String officeAddress=cursor.getString(cursor.getColumnIndex(OFFICEADDRESS));
                    mSYS_User.setOffice_address(officeAddress);
                    mSYS_User.setHead_picture_id(headPictureId);
                    String user_nicName = cursor.getString(cursor.getColumnIndex(USER_NICKNAME));
                    mSYS_User.setUserNickname(user_nicName);
                    try{
                        mSYS_User.setHeader(user_pin.split(",")[1]);
                        mSYS_User.setSuoxie(user_pin);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    mSYS_User.setFullName(FullName);
                    short Status = cursor.getShort(cursor.getColumnIndex(STATUS));
                    mSYS_User.setStatus(Status);
                    String Office = cursor.getString(cursor.getColumnIndex(OFFICE));
                    mSYS_User.setOffice(Office);
                    String Mobile = cursor.getString(cursor.getColumnIndex(MOBILE));
                    mSYS_User.setMobile(Mobile);
//                    String Photosurl = cursor.getString(cursor
//                            .getColumnIndex(PHOTOSURL));
//                    mSYS_User.setPhotosurl(Photosurl);
                    short emi_type = cursor.getShort(cursor.getColumnIndex(EMITYPE));
                    mSYS_User.setEmiType(emi_type);
                    short isEMPUser = cursor.getShort(cursor
                            .getColumnIndex(USERTYPE));
                    mSYS_User.setIsEMPUser(isEMPUser);
                    int display_order = cursor.getInt(cursor.getColumnIndex(DISPLAY_ORDER));
                    mSYS_User.setDisplay_order(display_order);
                    short adminType = cursor.getShort(cursor.getColumnIndex(ADMINTYPE));
                    mSYS_User.setAdminType(adminType);
                    String title = cursor.getString(cursor.getColumnIndex(TITLE));
                    mSYS_User.setTitle(title);
                    int gender = cursor.getInt(cursor.getColumnIndex(GENDER));
                    mSYS_User.setGender(gender);
                    String birthday = cursor.getString(cursor.getColumnIndex(BIRTHDAY));
                    mSYS_User.setBirthday(birthday);
                    String email = cursor.getString(cursor.getColumnIndex(EMAIL));
                    mSYS_User.setEmail(email);
                    String home_phone = cursor.getString(cursor.getColumnIndex(HOMEPHONE));
                    mSYS_User.setHome_phone(home_phone);
                    String  postalCode = cursor.getString(cursor.getColumnIndex(POSTALCODE));
                    mSYS_User.setPostal_code(postalCode);
                    String remark = cursor.getString(cursor.getColumnIndex(REMARK));
                    mSYS_User.setRemark(remark);
                    String corpName = cursor.getString(cursor.getColumnIndex(CORP_NAME));
                    String orgName = cursor.getString(cursor.getColumnIndex(ORG_NAME));
                    mSYS_User.setCorpName(corpName);
                    mSYS_User.setOrgName(orgName);
//					SYS_OrgUserDAO mSYS_OrgUserDAO = new SYS_OrgUserDAO(db);
//					SYS_OrgUser mSYS_OrgUser = mSYS_OrgUserDAO
//							.getSYSOrgUser(mSYS_User);
//					SYS_DepartmentDAO mSYS_DepartmentDAO = new SYS_DepartmentDAO(context);
//					SYS_Department mSYS_Department = new SYS_Department();
//					mSYS_DepartmentDAO.selectDepartment(mSYS_OrgUser.getDepartmentCode(), mSYS_Department);
//					mSYS_User.setmSYS_Department(mSYS_Department);
                    String efs1 = cursor.getString(cursor.getColumnIndex(EFS1));
                    String efs2 = cursor.getString(cursor.getColumnIndex(EFS2));
                    String efs3 = cursor.getString(cursor.getColumnIndex(EFS3));
                    String efs4 = cursor.getString(cursor.getColumnIndex(EFS4));
                    String efs5 = cursor.getString(cursor.getColumnIndex(EFS5));
                    String efs6 = cursor.getString(cursor.getColumnIndex(EFS6));
                    String efs7 = cursor.getString(cursor.getColumnIndex(EFS7));
                    String efs8 = cursor.getString(cursor.getColumnIndex(EFS8));
                    String efs9 = cursor.getString(cursor.getColumnIndex(EFS9));
                    String efs10 = cursor.getString(cursor.getColumnIndex(EFS10));
                    String efi1 = cursor.getString(cursor.getColumnIndex(EFI1));
                    String efi2 = cursor.getString(cursor.getColumnIndex(EFI2));
                    String efi3 = cursor.getString(cursor.getColumnIndex(EFI3));
                    String efi4 = cursor.getString(cursor.getColumnIndex(EFI4));
                    String efi5 = cursor.getString(cursor.getColumnIndex(EFI5));
                    String efd1 = cursor.getString(cursor.getColumnIndex(EFD1));
                    String efd2 = cursor.getString(cursor.getColumnIndex(EFD2));
                    String efn1 = cursor.getString(cursor.getColumnIndex(EFN1));
                    String efn2 = cursor.getString(cursor.getColumnIndex(EFN2));
                    String efn3 = cursor.getString(cursor.getColumnIndex(EFN3));
                    mSYS_User.setEfs1(efs1);
                    mSYS_User.setEfs2(efs2);
                    mSYS_User.setEfs3(efs3);
                    mSYS_User.setEfs4(efs4);
                    mSYS_User.setEfs5(efs5);
                    mSYS_User.setEfs6(efs6);
                    mSYS_User.setEfs7(efs7);
                    mSYS_User.setEfs8(efs8);
                    mSYS_User.setEfs9(efs9);
                    mSYS_User.setEfs10(efs10);
                    mSYS_User.setEfi1(efi1);
                    mSYS_User.setEfi2(efi2);
                    mSYS_User.setEfi3(efi3);
                    mSYS_User.setEfi4(efi4);
                    mSYS_User.setEfi5(efi5);
                    mSYS_User.setEfd1(efd1);
                    mSYS_User.setEfd2(efd2);
                    mSYS_User.setEfn1(efn1);
                    mSYS_User.setEfn2(efn2);
                    mSYS_User.setEfn3(efn3);
                    TD_UserDAO mTD_UserDAO = new TD_UserDAO(context);
                    TD_User mTD_User = mTD_UserDAO.getTD_User(FullName);
                    mSYS_User.setmTD_User(mTD_User);
                    userList.add(mSYS_User);
                }
                if(myApp != null){
                    myApp.setUserList(userList);
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(cursor != null){
                    cursor.close();
                }
            }

        }
        return userList;
    }
}
