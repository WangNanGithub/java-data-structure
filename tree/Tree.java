/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with haitouhui.com.
 */
package com.htouhui;

import java.util.*;

/**
 * @author WangNan, nan.wang@htouhui.com
 * @version 1.0
 */
public class Tree {

    /**
     * 节点ID
     */
    private Long nodeId;
    /**
     * 节点值
     */
    private Object value;
    /**
     * 父节点ID
     */
    private Long parentId;
    /**
     * 父节点
     */
    private Tree parent;
    /**
     * 子节点
     */
    private List<Tree> subNodes;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public List<Tree> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<Tree> subNodes) {
        this.subNodes = subNodes;
    }

    public Tree() {
    }

    public Tree(Object value) {
        this.value = value;
    }

    public Tree(Long nodeId, Object value, Long parentId, Tree parent, List<Tree> subNodes) {
        this.nodeId = nodeId;
        this.value = value;
        this.parentId = parentId;
        this.parent = parent;
        this.subNodes = subNodes;
    }

    /**
     * 深度优先遍历
     */
    public void deepOrder() {
        iterator(this, null);
    }

    public void iterator(Tree tree, Stack<Tree> stack) {
        if (stack == null) {
            stack = new Stack<Tree>();
        }
        if (tree != null) {
            stack.push(tree);
            if (!stack.isEmpty()) {
                Tree pop = stack.pop();
                System.out.println(pop.getValue());
                List<Tree> subNodes = pop.getSubNodes();
                if (subNodes != null && subNodes.size() > 0) {
                    for (Tree subNode : subNodes) {
                        iterator(subNode, stack);
                    }
                }
            }
        }
    }

    /**
     * 广度优先遍历
     */
//    public void broadOrder() {
//        broadIterator(this, null);
//    }

//    public void broadIterator(Tree tree, Deque<Tree> deque) {
//        if (deque == null) {
//            deque = new ArrayDeque<>();
//        }
//        if (tree != null) {
//            deque.push(tree);
//            if (!deque.isEmpty()) {
//                Tree pop = deque.pop();
//                System.out.println(pop.getValue());
//                List<Tree> subNodes = pop.getSubNodes();
//                if (subNodes != null && subNodes.size() > 0) {
//                    for (Tree subNode : subNodes) {
//                        broadIterator(subNode, deque);
//                    }
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        Tree root = new Tree("root");
        Tree A = new Tree("A");
        Tree B = new Tree("B");
        Tree C = new Tree("C");
        Tree A1 = new Tree("A1");
        Tree A2 = new Tree("A2");
        Tree A3 = new Tree("A3");
        Tree A1_A = new Tree("A1_A");
        Tree A1_B = new Tree("A1_B");
        Tree B1 = new Tree("B1");
        Tree B2 = new Tree("B2");
        Tree B1_A = new Tree("B1_A");
        Tree C1 = new Tree("C1");
        Tree C2 = new Tree("C2");

        List<Tree> A1Child = new ArrayList<Tree>();
        A1Child.add(A1_A);
        A1Child.add(A1_B);

        List<Tree> AChild = new ArrayList<Tree>();
        AChild.add(A1);
        AChild.add(A2);
        AChild.add(A3);

        List<Tree> B1Child = new ArrayList<Tree>();
        B1Child.add(B1_A);

        List<Tree> BChild = new ArrayList<Tree>();
        BChild.add(B1);
        BChild.add(B2);

        List<Tree> CChild = new ArrayList<Tree>();
        CChild.add(C1);
        CChild.add(C2);

        List<Tree> rootChild = new ArrayList<Tree>();
        rootChild.add(A);
        rootChild.add(B);
        rootChild.add(C);

        B1.setSubNodes(B1Child);
        A1.setSubNodes(A1Child);

        A.setSubNodes(AChild);
        B.setSubNodes(BChild);
        C.setSubNodes(CChild);

        root.setSubNodes(rootChild);

        root.deepOrder();
    }

}
