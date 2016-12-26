package com.example.meitu;

/**
 * 参考
 * http://www.cnblogs.com/lwbqqyumidi/p/3837629.html
 */
public class C {

    public static void main(String[] args){
        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(712);
        Box<Number> number = new Box<Number>(314);

        getData(name);
        getData(age);
        getData(number);

//        getUpperNumberData(name); // 1 报错 非Number子类
        getUpperNumberData(age);    // 2
        getUpperNumberData(number); // 3
    }

    public static void getData(Box<?> data) {
        System.out.println("data :" + data.getData());
    }

    public static void getUpperNumberData(Box<? extends Number> data){
        System.out.println("data :" + data.getData());
    }

    static class Box<T> {
        private T data;
        public Box() {
        }
        public Box(T data) {
            setData(data);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }
}
