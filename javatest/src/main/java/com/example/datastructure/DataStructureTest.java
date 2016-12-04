package com.example.datastructure;

import com.example.Student;

import java.util.LinkedList;

/**
 * 数据结构 测试类
 */
public class DataStructureTest {

    public static void main(String[] args){
        method02();
    }

    /**
     * 单链表 反转算法 递归
     */
    public static void method02(){
        Node head1 = new Node(1);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        Node head4 = new Node(4);
        Node head5 = new Node(5);
        head1.setNext(head2);
        head2.setNext(head3);
        head3.setNext(head4);
        head4.setNext(head5);

        Node node = head1;
        System.out.print("反转前：");
        while (node!=null){
            System.out.print(node.getData() + ",");
            node = node.getNext();
        }

        System.out.println();

        /**
         * 方法1
         */
        node = DataStructureUtils.reverse1(head1);
        System.out.print("反转后：");
        while (node!=null){
            System.out.print(node.getData() + ",");
            node = node.getNext();
        }

        System.out.println();

        /**
         * 方法二  我们再用另一种方法把它反转回去
         */
        node = DataStructureUtils.reverse2(head5);
        System.out.print("反转后：");
        while (node!=null){
            System.out.print(node.getData() + ",");
            node = node.getNext();
        }
    }

    /**
     * 链表 单列表 其实Node只用到了next
     */
    public static void method01(){
        LinkedList<Student> list = new LinkedList<Student>();
        for(int x=0;x<5;x++){
            Student student = new Student("student"+x, 18);
            list.offer(student);
        }

        for(int y=0;y<list.size();y++){
            System.out.print(list.get(y).toString() + ",");
        }
        System.out.println();

        LinkedList reverseList = DataStructureUtils.reverse(list);
        for(int y=0;y<reverseList.size();y++){
            System.out.print(reverseList.get(y).toString() + ",");
        }

    }
}
