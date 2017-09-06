package com.joe.phonebook.entity;

import android.provider.ContactsContract;

import com.joe.phonebook.R;
import com.joe.phonebook.api.PhoneInit;
import com.joe.phonebook.myenum.ChooseWayEnum;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Think on 2017/1/18.
 */

public class TreeHelper {

  public static int number = 0;

    /**
     * @param datas
     * @param defaultExpandLevel
     * @return 传入我们普通的bean, 转化为排序后的Node
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List<Node> getSortedNodes(List<SYS_Department> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        List<Node> result = new ArrayList<Node>();
        /**将用户数据转换为List<Node>*/
        List<Node> nodes = convetData2Node(datas);
        /**拿到根节点*/
        List<Node> rootNodes = getRootNodes(nodes);
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;

    }

    /**
     * 过滤出所有可见的Node
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNode(List<Node> nodes){
        List<Node> result = new ArrayList<Node>();
        for(Node node :nodes){
            /**如果为根节点或者上层目录为展开状态*/
            if(node.isRoot() || node.isParentExpand()){
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 过滤出要展示的Node,部门在前，人员在后
     * @param nodes
     * @return
     */
    public static List<Node> filterVisiableNodeShow(List<Node> nodes){
        List<Node> result = new ArrayList<Node>();
        for(Node node :nodes){
            if(node .isShow){
                setNodeIcon(node);
                result.add(node);
            }

        }
        List<Node> result_paixu = new ArrayList<Node>();
        /**对Node进行排序，开始展示部门，后面展示人员*/
        for(Node node:result){
            if(!node.isPeople){
                result_paixu.add(node);
            }

        }
        for(Node node :result){
            if(node.isPeople){
                result_paixu.add(node);
            }
        }
        return result_paixu;
    }


    /**
     * 获取到所有的人员
     * @param datas
     * @param users
     * @param nodes
     * @throws IllegalAccessException
     */
    public static void getAllUser(List<SYS_Department>datas,List<SYS_User> users,List<Node> nodes)throws IllegalAccessException{
        Node node = null;
        for(SYS_Department t : datas){
            String id = null;
            String pId = null;
            String label = null;
            Class<? extends Object> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for(Field f : declaredFields){
                if(f.getAnnotation(TreeNodeId.class)!= null){
                    f.setAccessible(true);
                    id = (String) f.get(t);

                }
                if(f.getAnnotation(TreeNodePid.class)!= null){
                    f.setAccessible(true);
                    pId = (String) f.get(t);
                }
                if(f.getAnnotation(TreeNodeLabel.class)!= null){
                    f.setAccessible(true);
                    label = (String)f.get(t);
                }
                if(id != null || pId != null || label != null){
                    break;
                }

            }
            number = 0;
            getAllUserNumber(t);
            t.setNumber(number);
            node = new Node(id,pId,label,t,false);
            nodes.add(node);

        }
        ChooseWayEnum mChooseWayEnum = PhoneInit.getInstance().getChooseWayEnum();
        switch (mChooseWayEnum){
            case DEPARTMENTCHOOSE:
                break;
            case FREECHOOSE:
            case PEOPLECHOOSE:
                for(SYS_User t : users){
                    String id = null;
                    String pId = null;
                    String label = null;
                    Class<? extends Object> clazz = t.getClass();
                    Field[] declaredFields = clazz.getDeclaredFields();
                    for(Field f: declaredFields){
                        if(f.getAnnotation(TreeNodeId.class)!= null){
                            f.setAccessible(true);
                            id = (String) f.get(t);
                        }
                        if(f.getAnnotation(TreeNodePid.class)!= null){
                            f.setAccessible(true);
                            pId = (String) f.get(t);
                        }
                        if(f.getAnnotation(TreeNodeLabel.class)!= null){
                            f.setAccessible(true);
                            label = (String) f.get(t);
                        }
                        if (id != null && pId != null && label != null)
                        {
                            break;
                        }

                    }
                    node = new Node(id,pId,label,t,true);
                    nodes.add(node);

                }
                break;

        }
        Iterator in = datas.iterator();
        while (in.hasNext()){
            SYS_Department supSYS_Department = (SYS_Department) in.next();
            getAllUser(supSYS_Department.getSYS_DepartmentList(),supSYS_Department.getSYS_User(),nodes);
        }

    }

    /**
     * 查询该集团目录下的所有User
     * @param currentSYS_Department
     */
    private static void getAllUserNumber(SYS_Department currentSYS_Department) {
        number += currentSYS_Department.getSYS_User().size();
        Iterator in = currentSYS_Department.getSYS_DepartmentList().iterator();
        while (in.hasNext()){
            SYS_Department supSYS_Department = (SYS_Department) in.next();
            getAllUserNumber(supSYS_Department);
        }

    }

    /**
     * @param datas
     * @return 将我们的数据转化成树的节点
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static List<Node> convetData2Node(List<SYS_Department> datas) throws IllegalArgumentException, IllegalAccessException {
        List<Node> nodes = new ArrayList<Node>();
        getAllUser(datas, new ArrayList<SYS_User>(), nodes);

        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getpId().equals(n.getId())) {
                    n.getChildren().add(m);
                    m.setParent(n);
                } else if (m.getId().equals(n.getId())) {
                    m.getChildren().add(n);
                    n.setParent(m);

                }
            }
        }
        /**设置图片*/
        for(Node n : nodes){
            setNodeIcon(n);
        }
        return nodes;
    }

    /**
     * 获取根节点的Node
     * @param nodes
     * @return
     */
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<Node>();
        for(Node node : nodes){
            if(node.isRoot()){
                root.add(node);
            }
        }

        return root;
    }

    /**
     * 把上一节点的所有内容都挂上去
     * @param nodes
     * @param node
     * @param defaultExpandLevel
     * @param currentLevel
     */
    private static void addNode(List<Node> nodes, Node node, int defaultExpandLevel, int currentLevel) {

        nodes.add(node);
        if(defaultExpandLevel >= currentLevel){
            node.setExpand(false);
        }
        if(node.isLeaf()){
            return;
        }
        for(int i = 0;i<node.getChildren().size();i++){
            addNode(nodes,node.getChildren().get(i),defaultExpandLevel,currentLevel+1);
        }

    }

    /**
     * 设置节点的图标
     * @param nodeIcon
     */
    public static void setNodeIcon(Node nodeIcon) {
        if( nodeIcon.getChildren().size() > 0&& nodeIcon.isExpand()){
            nodeIcon.setIcon(R.drawable.icon_minus);
        }else if(nodeIcon.getChildren().size() >0 && nodeIcon.isExpand()){
            nodeIcon.setIcon(R.drawable.icon_plus);
        }else {
            nodeIcon.setIcon(-1);
        }
    }

}
