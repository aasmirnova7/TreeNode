package com.netcracker.edu.java.tasks;

import java.util.*;

public class TreeNodeImpl implements TreeNode{
    private TreeNode parent;
    private Object data = null;
    private boolean flag;
    private Set<TreeNode> leafs = new HashSet<TreeNode>();

    public TreeNodeImpl(){}

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }//

    @Override
    public TreeNode getRoot() { 
        TreeNode root = null;
        TreeNode node = this;
       while (node.getParent() != null){
           root = node.getParent();
           node = root;
        }
        return root;
    }

    @Override
    public boolean isLeaf() { 
        if(this.getChildCount() == 0){
            return true;
        }
        return false;
    }

    @Override
    public int getChildCount() { 
        return this.leafs.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return this.leafs.iterator();
    }

    @Override
    public void addChild(TreeNode child) { 
        leafs.add(child);
        child.setParent(this);
    }

    @Override
    public boolean removeChild(TreeNode child) {
        int n = 0;
        if(this.leafs.contains(child)){
            for(TreeNode node : this.leafs){
                if(node.equals(child)){
                    node.setParent(null);
                }
            }
            this.leafs.remove(child);
            return true;
        }
        return false;
    }

    @Override
    public boolean isExpanded() {//ok
        return this.flag;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.flag = expanded;
        for(TreeNode node : this.leafs){
                node.setExpanded(expanded);
        }
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getTreePath() {
        String s = "";
        TreeNode node = this;
        ArrayList<Object> arr = new ArrayList();
        while (this.getRoot() != node){
            if(this.getRoot() == null){
                return (String) this.getData();
            }
            if(node.getData() == null){
                arr.add("empty");
            } else {
                arr.add(node.getData().toString());
            }
            node = node.getParent();
        }
        if(this.getRoot().getData() == null){
            s = s + "empty";
        } else {
            s = s + this.getRoot().getData().toString();
        }
        for (int i = arr.size()-1; i >= 0; --i){
            s = s + "->" + arr.get(i) ;
        }
        return s;
    }

    @Override
    public TreeNode findParent(Object data) {
        TreeNode node = this;
        boolean flag = false;

        while ((node != this.getRoot()) && (flag != true) && (node.getRoot() != null)){
            if(node.getData() == null && data == null){
                flag = true;
                break;
            }
            if(node.getData() == null && data !=null){
                node = node.getParent();
                continue;
            }
            if (node.getData().equals(data)){
                flag = true;
                break;
            }
            node = node.getParent();
        }
        if(node.getData() != null && node.getData().equals(data)){
            flag = true;
        }
        if(node.getData() == null && data == null){
            flag = true;
        }
        if(flag == false){
            node = null;
        }
        return node;
    }

    @Override
    public TreeNode findChild(Object data){ 
        TreeNode res = null;
        if(data == null){
            for(TreeNode node : this.leafs){
               if(node.getData()== data){
                    return node;
               }
            }

        } else {
            for (TreeNode node : this.leafs){
                if(node.getData() == null){
                    break;
                }
                if(node.getData().equals(data)){
                    return node;
                }
            }

        }

        if(!this.isLeaf()){
            for(TreeNode node : this.leafs){
                if(node.isLeaf()){
                    continue;
                }
                res = node.findChild(data);
                break;
            }
        }
        return res;
    }


    public static void main(String[] args){
        TreeNode tr1 = new TreeNodeImpl();
        TreeNode tr2 = new TreeNodeImpl();
        TreeNode tr3 = new TreeNodeImpl();
        TreeNode tr4 = new TreeNodeImpl();
        TreeNode tr5 = new TreeNodeImpl();
        TreeNode tr6 = new TreeNodeImpl();
        TreeNode tr7 = new TreeNodeImpl();
        TreeNode tr8 = new TreeNodeImpl();

        tr1.setData("1");
        //tr2.setData("2");
        tr3.setData("3");
        tr4.setData("4");
        tr5.setData("5");
        tr6.setData("6");
        tr7.setData("7");
        tr8.setData("8");


        tr1.addChild(tr2);
        tr1.addChild(tr3);
        tr2.addChild(tr4);
        tr2.addChild(tr5);
        tr4.addChild(tr6);
        tr6.addChild(tr7);
        tr6.addChild(tr8);


        System.out.println("1 = " + tr1);
        System.out.println("7 = "+ tr7);
        System.out.println("_______________");
        System.out.println(tr6.findParent("1"));

}
}
