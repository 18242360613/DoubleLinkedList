package xin.shaozb;

public class Node { //链表节点
    public Object nodeData = null; //节点数据
    public Node preNode = null; //前驱节点的引用
    public Node nextNode = null; //后继节点的引用

    public Node() {

    }

    public Node(Object nodeData) {
        this.nodeData = nodeData;
    }
}
