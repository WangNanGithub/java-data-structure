package com.htouhui.tree.binarytree;

import org.junit.Before;
import org.junit.Test;

/**
 * @author WangNan, nan.wang@htouhui.com
 * @version 1.0
 */
public class AVLTreeTest {
    private static int arr[] = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
    private AVLTree<Integer> tree;

    @Before
    public void before() {
        tree = new AVLTree<>();

        System.out.printf("== 依次添加: ");
        for (int i : arr) {
            System.out.printf("%d ", i * 2);
            tree.insert(i * 2);
        }
    }

    @Test
    public void testPreOrder() {
        System.out.printf("\n== 前序遍历: ");
        tree.preOrder();
    }

    @Test
    public void testInOrder() {
        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();
    }

    @Test
    public void testPostOrder() {
        System.out.printf("\n== 后序遍历: ");
        tree.postOrder();
    }

    @Test
    public void testInsert() {
        System.out.println();
        System.out.println("Height : " + tree.height());
        tree.print();

        System.out.printf("== 依次添加: ");
        for (int i : arr) {
            AVLTree<Integer>.AVLTreeNode<Integer> node = tree.search(i);
            if (node == null) {
                System.out.printf("%d ", i);
                tree.insert(i);
            }
        }

        System.out.println();
        System.out.println("Height : " + tree.height());
        tree.print();

        for (int i = 0; i < 100; i++) {
            AVLTree<Integer>.AVLTreeNode<Integer> node = tree.search(i);
            if (node == null) {
                System.out.printf("%d ", i);
                tree.insert(i);
            }
        }


        System.out.println();
        System.out.println("Height : " + tree.height());
        tree.print();
    }


    @Test
    public void printInfo() {
        System.out.printf("== 高度: %d\n", tree.height());
        System.out.printf("== 最小值: %d\n", tree.minimum());
        System.out.printf("== 最大值: %d\n", tree.maximum());
        System.out.printf("== 树的详细信息: \n");
        tree.print();
    }

    @Test
    public void test() {
        int i = 8;
        System.out.printf("\n== 删除根节点: %d", i);
        tree.remove(i);

        System.out.printf("\n== 高度: %d", tree.height());
        System.out.printf("\n== 中序遍历: ");
        tree.inOrder();
        System.out.printf("\n== 树的详细信息: \n");
        tree.print();

        // 销毁二叉树
        tree.destroy();
    }
}