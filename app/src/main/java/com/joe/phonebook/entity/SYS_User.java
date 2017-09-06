package com.joe.phonebook.entity;

import android.graphics.Bitmap;
import android.view.View;

import com.joe.phonebook.weight.MyCheckBox;

import java.io.Serializable;

/**
 * 人员
 * Created by Think on 2017/1/18.
 */

public class SYS_User implements Serializable{

    @TreeNodeId
    public String UserId;
    public String Password;
    @TreeNodeLabel
    public String FullName = "";
    public int Gender;
    public String ISDN;
    public String email;
    public short Status;
    public String Telephone = "";
    public String Fax;
    public String Office;
    public int UserType;

    public String Mobile;
    public String Position;
    public String Photosurl;
    public Short IsEMPUser;
    /**
     * 用户类型,
     * 0-虚拟用户，
     * 1-非平台用户，
     * 2-EMM平台用户，
     * 3-EMP移动端用户，
     * 4-EMI消息用户
     * */
    public Short IsEMIUser = 0;
    /**
     * EMI类型,
     * 0-未开通，
     * 1-开通
     * */
    public Short emiType;
    /**
     * 是否管理员,
     * 0-非管理员，
     * 1-业务管理员，
     * 2-单位管理员，
     * 3-超级管理员
     * */
    public Short adminType;
    /**
     * 用户昵称
     * */
    public String userNickname;
    /**
     * 头像类型,
     * 0-自己上传头像图片，
     * 1-默认图标，
     * 2-显示人名，
     * 3-显示姓，
     * 4-显示昵称
     * */
    public Short headType;
    /**
     * 头像图片ID，
     * 0-表示没有，
     * 不为0则对应图片表的图片ID
     * */
    public Short head_picture_id;
    /**
     * 显示顺序，用于根据所属部门排序
     * */
    public int display_order;
    /**
     * 职务
     * */
    public String title;
    /**
     * 生日
     * */
    public String birthday;
    /**
     * 家庭电话
     * */
    public String home_phone;
    /**
     * 办公地点
     * */
    public String office_address ;
    /**
     * 邮编
     * */
    public String postal_code;
    /**
     * 备注
     * */
    public String remark;
    public String orgName;
    public String corpName;
    public String pic_path;
    public String efs1 ;
    private  String efs2 ;
    private  String efs3 ;
    private   String efs4 ;
    private  String efs5  ;
    private   String efs6  ;
    private  String efs7  ;
    private   String efs8  ;
    private   String efs9  ;
    private   String efs10 ;
    private   String efi1  ;
    private   String efi2  ;
    private   String efi3  ;
    private   String efi4  ;
    private   String efi5 ;
    private   String efd1 ;
    private  String efd2 = "";
    private  String efn1 = "";
    private  String efn2 = "";
    private  String efn3 = "";

    @TreeNodePid
    public String DepartmentCode ;
    public String header = "";
    public String suoxie = "";
    public View checkView = null;
    public boolean isCheck = false;
    public MyCheckBox mCheckBox = null;
    public int color = 0;
    public String nameJan;

    public Node node;

    private SYS_Department mSYS_Department;
    public TD_User mTD_User;
    public TD_UserFieldSecret mTD_UserFieldSecret;
    public Bitmap bitmap;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getISDN() {
        return ISDN;
    }

    public void setISDN(String ISDN) {
        this.ISDN = ISDN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getStatus() {
        return Status;
    }

    public void setStatus(short status) {
        Status = status;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getOffice() {
        return Office;
    }

    public void setOffice(String office) {
        Office = office;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPhotosurl() {
        return Photosurl;
    }

    public void setPhotosurl(String photosurl) {
        Photosurl = photosurl;
    }

    public Short getIsEMPUser() {
        return IsEMPUser;
    }

    public void setIsEMPUser(Short isEMPUser) {
        IsEMPUser = isEMPUser;
    }

    public Short getIsEMIUser() {
        return IsEMIUser;
    }

    public void setIsEMIUser(Short isEMIUser) {
        IsEMIUser = isEMIUser;
    }

    public Short getEmiType() {
        return emiType;
    }

    public void setEmiType(Short emiType) {
        this.emiType = emiType;
    }

    public Short getAdminType() {
        return adminType;
    }

    public void setAdminType(Short adminType) {
        this.adminType = adminType;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Short getHeadType() {
        return headType;
    }

    public void setHeadType(Short headType) {
        this.headType = headType;
    }

    public Short getHead_picture_id() {
        return head_picture_id;
    }

    public void setHead_picture_id(Short head_picture_id) {
        this.head_picture_id = head_picture_id;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public String getEfs1() {
        return efs1;
    }

    public void setEfs1(String efs1) {
        this.efs1 = efs1;
    }

    public String getEfs2() {
        return efs2;
    }

    public void setEfs2(String efs2) {
        this.efs2 = efs2;
    }

    public String getEfs3() {
        return efs3;
    }

    public void setEfs3(String efs3) {
        this.efs3 = efs3;
    }

    public String getEfs4() {
        return efs4;
    }

    public void setEfs4(String efs4) {
        this.efs4 = efs4;
    }

    public String getEfs5() {
        return efs5;
    }

    public void setEfs5(String efs5) {
        this.efs5 = efs5;
    }

    public String getEfs6() {
        return efs6;
    }

    public void setEfs6(String efs6) {
        this.efs6 = efs6;
    }

    public String getEfs7() {
        return efs7;
    }

    public void setEfs7(String efs7) {
        this.efs7 = efs7;
    }

    public String getEfs8() {
        return efs8;
    }

    public void setEfs8(String efs8) {
        this.efs8 = efs8;
    }

    public String getEfs9() {
        return efs9;
    }

    public void setEfs9(String efs9) {
        this.efs9 = efs9;
    }

    public String getEfs10() {
        return efs10;
    }

    public void setEfs10(String efs10) {
        this.efs10 = efs10;
    }

    public String getEfi1() {
        return efi1;
    }

    public void setEfi1(String efi1) {
        this.efi1 = efi1;
    }

    public String getEfi2() {
        return efi2;
    }

    public void setEfi2(String efi2) {
        this.efi2 = efi2;
    }

    public String getEfi3() {
        return efi3;
    }

    public void setEfi3(String efi3) {
        this.efi3 = efi3;
    }

    public String getEfi4() {
        return efi4;
    }

    public void setEfi4(String efi4) {
        this.efi4 = efi4;
    }

    public String getEfi5() {
        return efi5;
    }

    public void setEfi5(String efi5) {
        this.efi5 = efi5;
    }

    public String getEfd1() {
        return efd1;
    }

    public void setEfd1(String efd1) {
        this.efd1 = efd1;
    }

    public String getEfd2() {
        return efd2;
    }

    public void setEfd2(String efd2) {
        this.efd2 = efd2;
    }

    public String getEfn1() {
        return efn1;
    }

    public void setEfn1(String efn1) {
        this.efn1 = efn1;
    }

    public String getEfn2() {
        return efn2;
    }

    public void setEfn2(String efn2) {
        this.efn2 = efn2;
    }

    public String getEfn3() {
        return efn3;
    }

    public void setEfn3(String efn3) {
        this.efn3 = efn3;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        DepartmentCode = departmentCode;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSuoxie() {
        return suoxie;
    }

    public void setSuoxie(String suoxie) {
        this.suoxie = suoxie;
    }

    public View getCheckView() {
        return checkView;
    }

    public void setCheckView(View checkView) {
        this.checkView = checkView;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public MyCheckBox getmCheckBox() {
        return mCheckBox;
    }

    public void setmCheckBox(MyCheckBox mCheckBox) {
        this.mCheckBox = mCheckBox;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNameJan() {
        return nameJan;
    }

    public void setNameJan(String nameJan) {
        this.nameJan = nameJan;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public SYS_Department getmSYS_Department() {
        return mSYS_Department;
    }

    public void setmSYS_Department(SYS_Department mSYS_Department) {
        this.mSYS_Department = mSYS_Department;
        setDepartmentCode(mSYS_Department.getTreeCode());
    }

    public TD_User getmTD_User() {
        return mTD_User;
    }

    public void setmTD_User(TD_User mTD_User) {
        this.mTD_User = mTD_User;
    }

    public TD_UserFieldSecret getmTD_UserFieldSecret() {
        return mTD_UserFieldSecret;
    }

    public void setmTD_UserFieldSecret(TD_UserFieldSecret mTD_UserFieldSecret) {
        this.mTD_UserFieldSecret = mTD_UserFieldSecret;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj ==null || !(obj instanceof SYS_User))
            return false;
        SYS_User mSYS_User = (SYS_User)obj;
        return mSYS_User.UserId.equals(UserId);
    }

}
