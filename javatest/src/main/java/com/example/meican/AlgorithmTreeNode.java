package com.example.meican;

import com.example.datastructure.TreeNodeTest;

/**
 * Created by wuhai on 2018/4/25.
 */

public class AlgorithmTreeNode {

    public static void main(String[] args){
        TreeNodeTest.TreeNode node5 = new TreeNodeTest.TreeNode(5);
        TreeNodeTest.TreeNode node2 = new TreeNodeTest.TreeNode(2);
        TreeNodeTest.TreeNode node7 = new TreeNodeTest.TreeNode(7);
        TreeNodeTest.TreeNode node1 = new TreeNodeTest.TreeNode(1);
        TreeNodeTest.TreeNode node3 = new TreeNodeTest.TreeNode(8);// 3:true;8:false
        TreeNodeTest.TreeNode node11 = new TreeNodeTest.TreeNode(11);
        node5.setLeft(node2);
        node5.setRight(node7);
        node2.setLeft(node1);
        node2.setRight(node3);
        node7.setRight(node11);

        try {
            judgeIsTree(node5,Integer.MIN_VALUE,Integer.MAX_VALUE);
            System.out.println("true");
        }catch (Exception e){
            System.out.println("false");
        }

    }

    static class StopMsgException extends RuntimeException {
    }

    public static void judgeIsTree(TreeNodeTest.TreeNode node, int min, int max){

        if(node.getVal() <= min || node.getVal() >= max){
            // 跳出
            throw new StopMsgException();
        }
        if(node.getLeft() != null){
            judgeIsTree(node.getLeft(),min,node.getVal());//左节点
        }

        if(node.getRight() != null){
            judgeIsTree(node.getRight(),node.getVal(),max);//右节点
        }

    }

}
