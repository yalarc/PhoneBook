package com.joe.phonebook.entity;

import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Think on 2017/1/18.
 */

public class Node implements Serializable {
    /***/
    private String id;
    /**根节点的 pId 为 0 */
    private String pId;
    /***/
    private String name;
    /**当前的级别*/
    private int level;
    /**是否展开*/
    private boolean isExpand = false;
    /***/
    private int icon;
    /**下一节点的子Node*/
    private List<Node> children = new ArrayList<>();
    /**父Node*/
    private Node parent;
    /***/
    private SYS_User mSYS_User;
    /***/
    public boolean isPeople;
    /***/
    private SYS_Department mSYS_Department;
    /***/
    private TextView showNumber;
    /***/
    public boolean isShow = true;

    public Node() {
        super();
    }

    public Node(String id, String pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    /**
     * @param id
     * @param pId 人员id
     * @param name
     * @param mSYS_Department
     * @param isPeople
     */
    public Node(String id, String pId, String name, SYS_Department mSYS_Department, boolean isPeople) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.mSYS_Department = mSYS_Department;
        this.isPeople = isPeople;
    }

    public Node(String id, String pId, String name, SYS_User mSYS_User, boolean isPeople) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.mSYS_User = mSYS_User;
        this.isPeople = isPeople;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public SYS_User getmSYS_User() {
        return mSYS_User;
    }

    public void setmSYS_User(SYS_User mSYS_User) {
        this.mSYS_User = mSYS_User;
    }

    public boolean isPeople() {
        return isPeople;
    }

    public void setPeople(boolean people) {
        isPeople = people;
    }

    public SYS_Department getmSYS_Department() {
        return mSYS_Department;
    }

    public void setmSYS_Department(SYS_Department mSYS_Department) {
        this.mSYS_Department = mSYS_Department;
    }

    public TextView getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(TextView showNumber) {
        this.showNumber = showNumber;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }


    /**
     * 是否为根节点
     * @return boolean
     */
    public boolean isRoot(){
        return parent ==null;
    }

    /**
     * 判断父节点是否展开
     * @return boolean
     */
    public boolean isParentExpand(){
        if(parent == null){
            return false;
        }
        return parent.isExpand();
    }

    /**
     * 判断是否为叶子节点
     * @return boolean
     */
    public boolean isLeaf(){
        return children.size() == 0;
    }

    /**
     * 获取同级leval
     * @return int
     */
    public int getLevel(){
        return parent == null ? 0: parent.getLevel()+1;
    }

    /**
     * 设置所有选中人员所有父节点的展示个数
     */
    public void getCheckNumber(){
        if(parent != null ) {
            /**
             * 防止放入进去的TextView错位以及删除总数不变 设置Tag属性来进行判断
             *
             * 这个判断不能放入 parent一起 否则首次进入的时候底部没有被展开的情况下 showNumber为空 而第一层不为空
             * */
            if (parent.getShowNumber() != null){
                if (parent.getShowNumber().getTag().toString().equals(parent.getId())) {
                    parent.getShowNumber().setText("" + parent.getmSYS_Department().checkNumber + "/" + parent.getmSYS_Department().getNumber());
                }
            }
            parent.getCheckNumber();
        }
    }


    /**
     * 设置所有父节点的选中个数
     * @param number
     */
    public void setNumber(int number){
        if(parent!= null){
            parent.getmSYS_Department().setCheckNumber(number);
            parent.setNumber(number);
        }
    }

    /**
     * 设置展开
     * @param isExpand
     */
    public void setExpand(boolean isExpand) {

        this.isExpand = isExpand;
        if(!isExpand){
            for(Node node : children){
                node.setExpand(isExpand);
            }
        }

    }
}
