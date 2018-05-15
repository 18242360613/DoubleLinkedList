package xin.shaozb;

/**
 * 简单的用Java实现双向链表
 *
 * @Author Shaozb
 */
public class LinkList {

    private int linkSize = 0; //链表的初始长度
    private Node head; //链表的头指针,指向链表头部
    private Node last; //链表的尾指针,指向链表尾部

    /**
     * 向链表尾部添加一个节点,如果当前链表为空链表则当前添加的节点作为头结点
     *
     * @param nodeData 节点数据
     */
    public void add(Object nodeData) {
        Node newNode = new Node(nodeData); //将节点数据实例化为一个节点对象
        if (linkSize == 0) { //当前链表为空链表
            head = newNode;
            last = newNode;
        } else {
            newNode.preNode = last;
            last.nextNode = newNode;
            last = newNode;
        }
        linkSize++;
    }

    /**
     * 向链表中插入一个节点
     *
     * @param index    插入节点的位置,如果index和linkSize均为0,则相当于向空链表中添加头结点
     *                 如果linkSize>0,index=linkSize,则代表向链表中插入新的节点
     *                 如果linkSize>0,index!=0&&index!=linkSize,则代表是向链表中插入新的节点
     * @param nodeData 节点数据
     */
    public void insert(int index, Object nodeData) {
        if (linkSize == 0 && index == 0) { //空链表,添加头结点
            add(nodeData);
            return;
        }
        if (index == linkSize) { //向链表尾部添加节点
            add(nodeData);
            return;
        }
        Node newNode = new Node(nodeData);
        Node node = getNode(index);

        newNode.nextNode = node;
        newNode.preNode = node.preNode;

        if (index == 0) { //链表不为空,向链表中插入头结点
            head = newNode;
            linkSize++;
            return;
        }

        node.preNode.nextNode = newNode;
        node.preNode = newNode;

        linkSize++;
    }

    /**
     * 通过链表下标删除节点
     *
     * @param index 删除节点的下标,如果index=0则代表删除头结点
     *              index=linkSize-1则代表删除尾节点
     */
    public void remove(int index) {
        Node node = getNode(index);
        if (index == 0) { //删除头结点
            head = node.nextNode;
            node.nextNode.preNode = null;
            node.nextNode = null;
        } else if (index == linkSize - 1) { //删除尾节点
            last = node.preNode;
            node.preNode.nextNode = null;
            node.preNode = null;
        } else {
            node.nextNode.preNode = node.preNode;
            node.preNode.nextNode = node.nextNode;
        }
        node = null; //将删除的节点置为null,以便GC(Garbage Controller)可以顺利回收,节省内存空间
        linkSize--;
    }

    /**
     * 通过节点删除节点
     *
     * @param node 删除的节点
     */
    public void remove(Node node) {
        int tempSize = linkSize;
        while (tempSize > 0) {
            Node tmp = getNode((linkSize - tempSize));
            if (tmp.nodeData.equals(node.nodeData)) {
                remove((linkSize - tempSize));
                return;
            }
            tempSize--;
        }

    }

    /**
     * 打印输出整个链表
     */
    public void show() {
        if (linkSize > 0) {
            Node node = head;
            int tempSize = linkSize;
            while (tempSize > 0) {
                String str = "[" + (linkSize - tempSize) + "]:" + node.nodeData;
                node = node.nextNode;
                tempSize--;
                System.out.println(str);
            }
        } else {
            System.out.println("[]");
        }
    }

    /**
     * 获取链表的长度
     *
     * @return 链表的长度
     */
    public int size() {
        return linkSize;
    }

    /**
     * 判断当前链表是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return (linkSize == 0);
    }

    /**
     * 获取链表中指定下标的节点
     *
     * @param index 节点下标
     * @return 节点
     * @throws NullPointerException           空指针异常
     * @throws ArrayIndexOutOfBoundsException 数组下标越界异常
     */
    public Node getNode(int index) throws NullPointerException, ArrayIndexOutOfBoundsException {
        if (linkSize == 0) { //空链表无法删除节点
            throw new NullPointerException();
        }
        if (linkSize - 1 < index) { //下标超出链表的最大长度
            throw new ArrayIndexOutOfBoundsException();
        }
        if (linkSize - 1 == index) { //尾节点
            return last;
        }
        if (index == 0) { //头结点
            return head;
        }
        Node node = head;
        int tempSize = linkSize;
        while (tempSize > 0) {
            if ((linkSize - tempSize) == index) {
                return node;
            }
            node = node.nextNode;
            tempSize--;
        }
        return null;
    }

    /**
     * 获取节点的下标
     *
     * @param node 节点
     * @return 下标
     */
    public int indexOf(Node node) {
        int tempSize = linkSize;
        while (tempSize > 0) {
            Node tmp = getNode((linkSize - tempSize));
            if (tmp.nodeData.equals(node.nodeData)) {
                return (linkSize - tempSize);
            }
            tempSize--;
        }
        return 0;
    }


}

