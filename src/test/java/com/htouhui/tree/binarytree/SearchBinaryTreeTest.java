package com.htouhui.tree.binarytree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author WangNan, nan.wang@htouhui.com
 * @version 1.0
 */
public class SearchBinaryTreeTest {

    private static final int arr[] = {7, 4, 9, 2, 6, 8, 10, 1, 3, 5};
    private SearchBinaryTree<Integer> tree;

    @Before
    public void before() {
        int i, iLen;
        tree = new SearchBinaryTree<Integer>();

        System.out.println("== 依次添加: ");
        iLen = arr.length;
        for (i = 0; i < iLen; i++) {
            System.out.print(arr[i] * 2 + " ");
            tree.insert(arr[i] * 2);
        }
        System.out.println();
    }

    @Test
    public void testMaxDeath() {
        System.out.println("二叉树最大深度: ");
        int maxDeath = tree.maxDeath();
        System.out.println(maxDeath);
    }

    @Test
    public void testMinDeath() {
        System.out.println("二叉树最小深度: ");
        int minDeath = tree.minDeath();
        System.out.println(minDeath);
    }

    @Test
    public void testNumOfTreeNode() {
        System.out.println("二叉树节点个数: ");
        int numOfTreeNode = tree.numOfTreeNode();
        System.out.println(numOfTreeNode);
    }

    @Test
    public void testNumOfNoChildNode() {
        int numOfNoChildNode = tree.numOfNoChildNode();
        System.out.printf("二叉树叶子节点个数 : %d \n", numOfNoChildNode);
    }

    @Test
    public void numOfkLevelTreeNode() {
        int death = tree.maxDeath();
        for (int i = 1; i <= death; i++) {
            int numOfkLevelTreeNode = tree.numOfkLevelTreeNode(i);
            System.out.printf("二叉树第 %d 层节点个数 : %d \n", i, numOfkLevelTreeNode);
        }
    }

    @Test
    public void isBalanced() {
        System.out.printf("二叉树是否是平衡二叉树 : %s", tree.isBalanced());
    }

    @Test
    public void testPreOrder() throws Exception {
        System.out.println("\n== 前序遍历: ");
        tree.preOrder();
    }

    @Test
    public void testInOrder() throws Exception {
        System.out.println("\n== 中序遍历: ");
        tree.inOrder();
    }

    @Test
    public void testPostOrder() throws Exception {
        System.out.println("\n== 后序遍历: ");
        tree.postOrder();
    }

    @Test
    public void testLevelOrder() throws Exception {
        System.out.println("\n== 层次遍历: ");
        ArrayList<ArrayList<Integer>> arrayLists = tree.levelOrder();
        for (ArrayList<Integer> list : arrayLists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    public void testSearch() throws Exception {
        System.out.println("查找结点。。。");
        SearchBinaryTree<Integer>.BSTNode<Integer> node = tree.iterativeSearch(6);
        System.out.println(node.getKey());
    }

    public void testIterativeSearch() throws Exception {
    }

    @Test
    public void testMinimum() throws Exception {
        System.out.println("查找最小值。。。");
        Integer minimum = tree.minimum();
        System.out.println(minimum);
    }

    @Test
    public void testMaximum() throws Exception {
        System.out.println("查找最大值。。。");
        Integer maximum = tree.maximum();
        System.out.println(maximum);
    }

    @Test
    public void testSuccessor() throws Exception {
        System.out.println("查找后继结点。。。");
        SearchBinaryTree<Integer>.BSTNode<Integer> node = tree.iterativeSearch(6);
        SearchBinaryTree<Integer>.BSTNode<Integer> successor = tree.successor(node);
        System.out.println(successor.getKey());
    }

    @Test
    public void testPredecessor() throws Exception {
        System.out.println("查找前驱结点。。。");
        SearchBinaryTree<Integer>.BSTNode<Integer> node = tree.search(6);
        SearchBinaryTree<Integer>.BSTNode<Integer> successor = tree.predecessor(node);
        System.out.println(successor.getKey());
    }

    @Test
    public void testInsert() throws Exception {
        System.out.println("before insert");
        tree.print();

        // 二叉树失衡
        for (int i = 0; i < 100; i++) {
            SearchBinaryTree<Integer>.BSTNode<Integer> node = tree.search(i);
            if (node == null) {
                tree.insert(i);
            }
        }

        System.out.println("after insert");
        tree.print();

    }

    @Test
    public void testRemove() throws Exception {
        System.out.println("删除结点。。。");
        System.out.println("删除前...");
        tree.print();
        tree.remove(9);
        System.out.println("删除后...");
        tree.print();
    }

    public void testClear() throws Exception {
    }

    @Test
    public void testPrint() throws Exception {
        tree.insert(13);
        tree.print();

        tree.remove(12);
        tree.print();
    }

}