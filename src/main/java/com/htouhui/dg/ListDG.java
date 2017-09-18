/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with haitouhui.com.
 */
package com.htouhui.dg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 邻接表 有向图
 *
 * @author WangNan, nan.wang@htouhui.com
 * @version 1.0
 * @date 2017年09月18日
 */
@SuppressWarnings("all")
public class ListDG {
    // 邻接表中表对应的链表的顶点
    private class ENode {
        int ivex;       // 该边所指向的顶点的位置
        int weight;     // 该边的权
        ENode nextEdge; // 指向下一条弧的指针
    }

    // 邻接表中表的顶点
    private class VNode {
        char data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧
    }

    private VNode[] mVexs;  // 顶点数组
    private int mEdgNum;    // 边的数量

    /*
     * 创建图(用已提供的矩阵)
     *
     * 参数说明：
     *     vexs  -- 顶点数组
     *     edges -- 边数组
     */
    public ListDG(char[] vexs, char[][] edges) {

        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;
        int elen = edges.length;

        // 初始化"顶点"
        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode();
            mVexs[i].data = vexs[i];
            mVexs[i].firstEdge = null;
        }

        // 初始化"边"
        for (int i = 0; i < elen; i++) {
            // 读取边的起始顶点和结束顶点
            char c1 = edges[i][0];
            char c2 = edges[i][1];
            // 读取边的起始顶点和结束顶点的位置
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);

            // 初始化node1
            ENode node1 = new ENode();
            node1.ivex = p2;
            // 将node1链接到"p1所在链表的末尾"
            if (mVexs[p1].firstEdge == null)
                mVexs[p1].firstEdge = node1;
            else
                linkLast(mVexs[p1].firstEdge, node1);
        }
    }

    /*
     * 将node节点链接到list的最后
     */
    private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while (p.nextEdge != null)
            p = p.nextEdge;
        p.nextEdge = node;
    }

    /*
     * 返回ch位置
     */
    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++)
            if (mVexs[i].data == ch)
                return i;
        return -1;
    }

    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("List Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            System.out.printf("%d(%c): ", i, mVexs[i].data);
            ENode node = mVexs[i].firstEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.ivex, mVexs[node.ivex].data);
                node = node.nextEdge;
            }
            System.out.printf("\n");
        }
    }

    /*
     * 拓扑排序
     *
     * 返回值：
     *     -1 -- 失败(由于内存不足等原因导致)
     *      0 -- 成功排序，并输入结果
     *      1 -- 失败(该有向图是有环的)
     */
    public int topologicalSort() {
        int index = 0;
        int num = mVexs.length;
        int[] ins;               // 入度数组
        char[] tops;             // 拓扑排序结果数组，记录每个节点的排序后的序号。
        Queue<Integer> queue;    // 辅组队列

        ins = new int[num];
        tops = new char[num];
        queue = new LinkedList<Integer>();

        // 统计每个顶点的入度数
        for (int i = 0; i < num; i++) {
            ENode node = mVexs[i].firstEdge;
            while (node != null) {
                ins[node.ivex]++;
                node = node.nextEdge;
            }
        }

        // 将所有入度为0的顶点入队列
        for (int i = 0; i < num; i++)
            if (ins[i] == 0)
                queue.offer(i);                 // 入队列

        while (!queue.isEmpty()) {              // 队列非空
            int j = queue.poll().intValue();    // 出队列。j是顶点的序号
            tops[index++] = mVexs[j].data;      // 将该顶点添加到tops中，tops是排序结果
            ENode node = mVexs[j].firstEdge;    // 获取以该顶点为起点的出边队列

            // 将与"node"关联的节点的入度减1；
            // 若减1之后，该节点的入度为0；则将该节点添加到队列中。
            while (node != null) {
                // 将节点(序号为node.ivex)的入度减1。
                ins[node.ivex]--;
                // 若节点的入度为0，则将其"入队列"
                if (ins[node.ivex] == 0)
                    queue.offer(node.ivex);    // 入队列

                node = node.nextEdge;
            }
        }

        if (index != num) {
            System.out.printf("Graph has a cycle\n");
            return 1;
        }

        // 打印拓扑排序结果
        System.out.printf("== TopSort: ");
        for (int i = 0; i < num; i++)
            System.out.printf("%c ", tops[i]);
        System.out.printf("\n");

        return 0;
    }

    /**
     * 克鲁斯卡尔（Kruskal)最小生成树
     */
    public void kruskal() {
        int index = 0;                      // rets数组的索引
        int[] vends = new int[mEdgNum];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
        EData[] rets = new EData[mEdgNum];  // 结果数组，保存kruskal最小生成树的边
        EData[] edges;                      // 图对应的所有边

        // 获取"图中所有的边"
        edges = getEdges();
        // 将边按照"权"的大小进行排序(从小到大)
        sortEdges(edges, mEdgNum);

        for (int i = 0; i < mEdgNum; i++) {
            int p1 = getPosition(edges[i].start);       // 获取第i条边的"起点"的序号
            int p2 = getPosition(edges[i].end);         // 获取第i条边的"终点"的序号

            int m = getEnd(vends, p1);                  // 获取p1在"已有的最小生成树"中的终点
            int n = getEnd(vends, p2);                  // 获取p2在"已有的最小生成树"中的终点
            // 如果m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
            if (m != n) {
                vends[m] = n;                           // 设置m在"已有的最小生成树"中的终点为n
                rets[index++] = edges[i];               // 保存结果
            }
        }

        // 统计并打印"kruskal最小生成树"的信息
        int length = 0;
        for (int i = 0; i < index; i++)
            length += rets[i].weight;
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++)
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        System.out.printf("\n");
    }

    /*
     * 获取图中的边
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges;

        edges = new EData[mEdgNum];
        for (int i = 0; i < mVexs.length; i++) {

            ENode node = mVexs[i].firstEdge;
            while (node != null) {
                if (node.ivex > i) {
                    edges[index++] = new EData(mVexs[i].data, mVexs[node.ivex].data, node.weight);
                }
                node = node.nextEdge;
            }
        }

        return edges;
    }

    /*
     * 对边按照权值大小进行排序(由小到大)
     */
    private void sortEdges(EData[] edges, int elen) {

        for (int i = 0; i < elen; i++) {
            for (int j = i + 1; j < elen; j++) {

                if (edges[i].weight > edges[j].weight) {
                    // 交换"边i"和"边j"
                    EData tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    /*
     * 获取i的终点
     */
    private int getEnd(int[] vends, int i) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }

    // 边的结构体
    private static class EData {
        char start; // 边的起点
        char end;   // 边的终点
        int weight; // 边的权重

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    ;

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'B'},
                {'B', 'C'},
                {'B', 'E'},
                {'B', 'F'},
                {'C', 'E'},
                {'D', 'C'},
                {'E', 'B'},
                {'E', 'D'},
                {'F', 'G'}};
        ListDG pG;

        // 自定义"图"(输入矩阵队列)
        // pG = new ListDG();
        // 采用已有的"图"
        pG = new ListDG(vexs, edges);

        pG.print();   // 打印图
    }
}
