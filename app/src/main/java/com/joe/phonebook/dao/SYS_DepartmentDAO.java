package com.joe.phonebook.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.joe.phonebook.application.BaseApplication;
import com.joe.phonebook.db.DBManager;
import com.joe.phonebook.entity.SYS_Department;

/**
 * Created by Think on 2017/1/18.
 */

public class SYS_DepartmentDAO {
    /**数据库表名*/
    private static final String TABLE_NAME = "org_org_tree";
    /**组织ID*/
    private static final String DEPARTMENTCODE = "org_id";
    /**组织类别 1-部门，2-集团，3-单位,4-虚拟机构 */
    private static final String ORGANISETYPE = "org_type";
    /**组织机构编码 */
    private static final String ORG_CODE = "org_code";
    /**组织机构简称*/
    private static final String SHORTNAME = "org_shortname";
    /**组织结构名称*/
    private static final String FULLNAME = "org_name";
    /**组织机构拼音*/
    private static final String PINYIN = "org_pinyin";
    /**组织机构显示顺序*/
    private static final String DISORDER = "display_order";
    /**上级组织ID*/
    private static final String PARENTDEPARTMENT = "parent_org_id";
    /**组织机构状态：-1: 删除，0-禁用，1-正常*/
    private static final String ORGSTATUS = "status_flag";
    /***/
    private static final String TREECODE = "tree_code";
    /**组织机构的电话*/
    private static final String TELEPHONE = "org_phone";
    /**组织机构的传真*/
    private static final String FAX = "org_fax";
    /**组织机构的地址（部门所在楼层、门牌号等）*/
    private static final String ADDRESS = "org_address";
    /**创建人*/
    private static final String CREATEDBY = "create_by";
    /**创建时间*/
    private static final String CREATEDDATE = "create_time";
    /**修改人*/
    private static final String MODIFIEDBY = "update_by";
    /**修改时间*/
    private static final String MODIFIEDDATE = "update_time";
    /**第三方组织机构*/
    private static final String THIRDDEPARTMENTID = "third_dept_id";

    private SQLiteDatabase db;
    private BaseApplication myApp;
    private Context context;
    private SYS_OrgUserDAO mSYS_OrgUserDAO;
    private int index =0;

    public SYS_DepartmentDAO(Context context) {
        this.context = context;
        mSYS_OrgUserDAO = new SYS_OrgUserDAO(context);
        db = DBManager.getInstance(context);
    }

    public SYS_DepartmentDAO(SQLiteDatabase db, BaseApplication myApp) {
        this.db = db;
        this.myApp = myApp;
    }

    public SYS_DepartmentDAO(Context context, BaseApplication myApp) {
        this.context = context;
        this.myApp = myApp;
        db = DBManager.getInstance(context);
        mSYS_OrgUserDAO = new SYS_OrgUserDAO(context);
    }

    /**
     *
     * 更新部门数据
     * @param DepartmentCode
     * @param departmentList
     * @return
     * @throws ParseException
     */
    public SYS_Department getUpdateDepartment(String DepartmentCode,SYS_Department departmentList)throws ParseException{

        departmentList.getSYS_DepartmentList().clear();
        if(db.isOpen()){
            Cursor cursor = null;

            try {
                cursor = db.query(TABLE_NAME,null,PARENTDEPARTMENT +"=? and status_flag = 1",new String[]{DepartmentCode},null,null,null);
                if(cursor == null|| cursor.getCount() <=0){
                    return null;
                }
                while (cursor.moveToNext()){
                    SYS_Department sysDepartment = new SYS_Department();
                    String departmentCode = cursor.getString(cursor.getColumnIndex(DEPARTMENTCODE));
                    sysDepartment.setDepartmentCode(departmentCode);

                    String ShortName = cursor.getString(cursor.getColumnIndex(SHORTNAME));
                    sysDepartment.setShortName(ShortName);

                    String fullName = cursor.getString(cursor
                            .getColumnIndex(FULLNAME));
                    sysDepartment.setFullName(fullName);

                    String OrganiseType = cursor.getString(cursor
                            .getColumnIndex(ORGANISETYPE));
                    sysDepartment.setOrganiseType(OrganiseType);

                    String ParentDepartment = cursor.getString(cursor
                            .getColumnIndex(PARENTDEPARTMENT));
                    sysDepartment.setParentDepartment(ParentDepartment);

                    Short orgStatus = cursor.getShort(cursor
                            .getColumnIndex(ORGSTATUS));
                    sysDepartment.setOrgStatus(orgStatus);

                    String Telephone = cursor.getString(cursor
                            .getColumnIndex(TELEPHONE));
                    sysDepartment.setTelephone(Telephone);

                    String orgCode = cursor.getString(cursor.getColumnIndex(ORG_CODE));
                    sysDepartment.setOrgCode(orgCode);

                    String treeCode = cursor.getString(cursor.getColumnIndex(TREECODE));
                    sysDepartment.setTreeCode(treeCode);

                    String Fax = cursor.getString(cursor.getColumnIndex(FAX));
                    sysDepartment.setFax(Fax);

                    String Address = cursor.getString(cursor
                            .getColumnIndex(ADDRESS));
                    sysDepartment.setAddress(Address);

                    String CreatedBy = cursor.getString(cursor
                            .getColumnIndex(CREATEDBY));
                    sysDepartment.setCreatedBy(CreatedBy);

                    String CreatedDate = cursor.getString(cursor
                            .getColumnIndex(CREATEDDATE));
                    sysDepartment.setCreatedDate(CreatedDate);

                    String ModifiedBy = cursor.getString(cursor
                            .getColumnIndex(MODIFIEDBY));
                    sysDepartment.setModifiedBy(ModifiedBy);

                    String ModifiedDate = cursor.getString(cursor
                            .getColumnIndex(MODIFIEDDATE));
                    sysDepartment.setModifiedDate(ModifiedDate);

                    String Pinyin = cursor.getString(cursor.getColumnIndex(PINYIN));
                    try{
                        sysDepartment.setPinyin(Pinyin);
                        sysDepartment.setSuoxie(Pinyin);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    String ThirdDepartmentId = cursor.getString(cursor
                            .getColumnIndex(THIRDDEPARTMENTID));
                    sysDepartment.setThirdDepartmentId(ThirdDepartmentId);

                    int DisOrder = cursor.getInt(cursor.getColumnIndex(DISORDER));
                    sysDepartment.setDisOrder(DisOrder);

                    departmentList.getSYS_DepartmentList().add(sysDepartment);


                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

        }

        return departmentList;
    }

    /**
     * 获取部门数据
     * @param DepartmentCode
     * @param departmentList
     * @return
     * @throws java.text.ParseException
     */
    public SYS_Department getDepartments(String DepartmentCode, SYS_Department departmentList) throws java.text.ParseException {

        if(db.isOpen()){
            Cursor cursor = null;
            cursor = db.query(TABLE_NAME,null,PARENTDEPARTMENT+"=? and status_flag = 1",new String[]{DepartmentCode},null,null,null);

            if(cursor == null || cursor.getCount() <=0){
                return null;
            }
            while (cursor.moveToNext()) {

                SYS_Department mSYS_Department = new SYS_Department();

                String departmentCode = cursor.getString(cursor
                        .getColumnIndex(DEPARTMENTCODE));
                mSYS_Department.setDepartmentCode(departmentCode);

                String ShortName = cursor.getString(cursor
                        .getColumnIndex(SHORTNAME));
                mSYS_Department.setShortName(ShortName);

                String fullName = cursor.getString(cursor
                        .getColumnIndex(FULLNAME));
                mSYS_Department.setFullName(fullName);

                Short orgStatus = cursor.getShort(cursor
                        .getColumnIndex(ORGSTATUS));
                mSYS_Department.setOrgStatus(orgStatus);

                String orgCode = cursor.getString(cursor.getColumnIndex(ORG_CODE));
                mSYS_Department.setOrgCode(orgCode);

                String treeCode = cursor.getString(cursor.getColumnIndex(TREECODE));
                mSYS_Department.setTreeCode(treeCode);

                String OrganiseType = cursor.getString(cursor
                        .getColumnIndex(ORGANISETYPE));
                mSYS_Department.setOrganiseType(OrganiseType);

                String ParentDepartment = cursor.getString(cursor
                        .getColumnIndex(PARENTDEPARTMENT));
                mSYS_Department.setParentDepartment(ParentDepartment);

                String Telephone = cursor.getString(cursor
                        .getColumnIndex(TELEPHONE));
                mSYS_Department.setTelephone(Telephone);

                String Fax = cursor.getString(cursor.getColumnIndex(FAX));
                mSYS_Department.setFax(Fax);

                String Address = cursor.getString(cursor
                        .getColumnIndex(ADDRESS));
                mSYS_Department.setAddress(Address);

                String CreatedBy = cursor.getString(cursor
                        .getColumnIndex(CREATEDBY));
                mSYS_Department.setCreatedBy(CreatedBy);

                String CreatedDate = cursor.getString(cursor
                        .getColumnIndex(CREATEDDATE));
                mSYS_Department.setCreatedDate(CreatedDate);

                String ModifiedBy = cursor.getString(cursor
                        .getColumnIndex(MODIFIEDBY));
                mSYS_Department.setModifiedBy(ModifiedBy);

                String ModifiedDate = cursor.getString(cursor
                        .getColumnIndex(MODIFIEDDATE));
                mSYS_Department.setModifiedDate(ModifiedDate);

                String Pinyin = cursor.getString(cursor.getColumnIndex(PINYIN));
                try{
                    mSYS_Department.setPinyin(Pinyin);
                    mSYS_Department.setSuoxie(Pinyin);

                }catch (Exception e){
                    e.printStackTrace();
                }

                String ThirdDepartmentId = cursor.getString(cursor
                        .getColumnIndex(THIRDDEPARTMENTID));
                mSYS_Department.setThirdDepartmentId(ThirdDepartmentId);

                int DisOrder = cursor.getInt(cursor.getColumnIndex(DISORDER));
                mSYS_Department.setDisOrder(DisOrder);

                SYS_UserDAO mSYS_UserDAO = new SYS_UserDAO(context);

            }

        }


        return null;
    }


    /**
     * 选择单位
     * @param DepartmentCode
     * @param mSYS_Department
     */
    public void selectDepartment(String DepartmentCode,
                                 SYS_Department mSYS_Department) {
        if (db.isOpen()) {
            Cursor cursor = null;
            try{
                cursor = db.query(TABLE_NAME, null, DEPARTMENTCODE + "=? and status_flag = 1 ",
                        new String[]{DepartmentCode}, null, null, null);
                if (cursor.moveToLast()) {
                    String departmentCode = cursor.getString(cursor
                            .getColumnIndex(DEPARTMENTCODE));
                    mSYS_Department.setDepartmentCode(departmentCode);
                    String ShortName = cursor.getString(cursor
                            .getColumnIndex(SHORTNAME));
                    mSYS_Department.setShortName(ShortName);
                    String fullName = cursor.getString(cursor
                            .getColumnIndex(FULLNAME));
                    mSYS_Department.setFullName(fullName);
                    String OrganiseType = cursor.getString(cursor
                            .getColumnIndex(ORGANISETYPE));
                    String orgCode = cursor.getString(cursor.getColumnIndex(ORG_CODE));
                    mSYS_Department.setOrgCode(orgCode);
                    String treeCode = cursor.getString(cursor.getColumnIndex(TREECODE));
                    mSYS_Department.setTreeCode(treeCode);
                    Short orgStatus = cursor.getShort(cursor
                            .getColumnIndex(ORGSTATUS));
                    mSYS_Department.setOrgStatus(orgStatus);
                    mSYS_Department.setOrganiseType(OrganiseType);
                    String ParentDepartment = cursor.getString(cursor
                            .getColumnIndex(PARENTDEPARTMENT));
                    mSYS_Department.setParentDepartment(ParentDepartment);
                    String Telephone = cursor.getString(cursor
                            .getColumnIndex(TELEPHONE));
                    mSYS_Department.setTelephone(Telephone);
                    String Fax = cursor.getString(cursor.getColumnIndex(FAX));
                    mSYS_Department.setFax(Fax);
                    String Address = cursor.getString(cursor
                            .getColumnIndex(ADDRESS));
                    mSYS_Department.setAddress(Address);
                    String CreatedBy = cursor.getString(cursor
                            .getColumnIndex(CREATEDBY));
                    mSYS_Department.setCreatedBy(CreatedBy);
                    String CreatedDate = cursor.getString(cursor
                            .getColumnIndex(CREATEDDATE));
                    mSYS_Department.setCreatedDate(CreatedDate);
                    String ModifiedBy = cursor.getString(cursor
                            .getColumnIndex(MODIFIEDBY));
                    mSYS_Department.setModifiedBy(ModifiedBy);
                    String ModifiedDate = cursor.getString(cursor
                            .getColumnIndex(MODIFIEDDATE));
                    mSYS_Department.setModifiedDate(ModifiedDate);
                    String Pinyin = cursor.getString(cursor.getColumnIndex(PINYIN));
                    try{

                        mSYS_Department.setPinyin(Pinyin);
                        mSYS_Department.setSuoxie(Pinyin);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    String ThirdDepartmentId = cursor.getString(cursor
                            .getColumnIndex(THIRDDEPARTMENTID));
                    mSYS_Department.setThirdDepartmentId(ThirdDepartmentId);
                    int DisOrder = cursor.getInt(cursor.getColumnIndex(DISORDER));
                    mSYS_Department.setDisOrder(DisOrder);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(cursor != null ){
                    cursor.close();
                }
            }

        }
    }

    /**
     *
     * 查询单位
     * @param mSYS_DepartmentupAll
     * @param mSYS_Departmentup
     * @return
     */
    public SYS_Department searchDepartmentIsContains(SYS_Department mSYS_DepartmentupAll,
                                                     SYS_Department mSYS_Departmentup) {
        for (SYS_Department sSYS_Department : mSYS_DepartmentupAll
                .getSYS_DepartmentList()) {
            if (mSYS_DepartmentupAll.getDepartmentCode().equals(
                    mSYS_Departmentup.getParentDepartment())) {
                mSYS_DepartmentupAll.getSYS_DepartmentList().add(mSYS_Departmentup);
                return mSYS_Departmentup;
            }else{
                return searchDepartmentIsContains(sSYS_Department, mSYS_Departmentup);
            }
        }

        return null;
    }

}
