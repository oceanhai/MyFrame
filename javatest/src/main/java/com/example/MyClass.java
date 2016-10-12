package com.example;

public class MyClass {

    public static void main(String arg[]){
//        CommonUtils.printInt(76234);

//        String str;
//        TestUtils.test01(str);
//        System.out.println("test01:" + TestUtils.test01(null));

//        List<String> list1 = new ArrayList<String>();
//        List<String> list2 = new ArrayList<String>();
//        list2.add("tiaomu 1");
//        TestUtils.test02(list1);
//        TestUtils.test02(list2);

        //序列化测试 start
//        Entry12 entry12 = new Entry12("lufei","maiin");
//        SerializeTools.cacheObj("F:\\entry12.txt", entry12);

//        Entry12 newEntry = SerializeTools.getObj("F:\\entry12.txt",Entry12.class);
//        String name = newEntry.getPair().getName();
//        String mail = newEntry.getPair().getEmail();
//        System.out.println("name=" + name +",mail=" + mail);
//        System.out.println("newEntry="+newEntry.toString());

//        Entry12.EntryInternal entryB = new Entry12.EntryInternal("lufei","maiin");
//        SerializeTools.cacheObj("F:\\entryB.txt", new Entry12(entryB));

//        Entry12.EntryInternal entryC = new Entry12.EntryInternal("lufei","maiin");
//        SerializeTools.cacheObj("F:\\entryC.txt", new Entry12(entryC));

//        Entry12 newEntry = SerializeTools.getObj("F:\\entryC.txt",Entry12.class);
//        String name = newEntry.getPair().getName();
//        String mail = newEntry.getPair().getEmail();
//        String age = newEntry.getPair().getAge();
//        System.out.println("name=" + name +",mail=" + mail +",age=" + age);
//        System.out.println("newEntry="+newEntry.toString());
        //序列化测试 end

//        method01();
//        method02();
//        method03();
        method04();
    }

    public static void method04(){
        Outer outer = new Outer();
//        Outer.Inner inner  = outer.new Inner();//public Inner(){} 时候可以
        outer.method();

    }

    public static void method03(){
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");

        System.out.println( 1 == 1);
        System.out.println( str1 == str2);
        System.out.println( str1.equals(str2));
        System.out.println( str1 == str3);//比较地址
        System.out.println( str1.equals(str3));//String 重写了equals方法，比较的内容
    }

    public static void method02(){
//        Shape[] sh1 = {new Circle(2.0),new square,new Rectangle(3.0,4.0f)};
        String[] st1 = {"Joe", "Bob", "Bill", "Zeke"};
        Integer[] int1 = {99, 12, 8, 109, 5};
        System.out.println(CommonUtils.findMax(st1));
        System.out.println(CommonUtils.findMax(int1));
    }

    public static void method01(){
        MemoryCell cell = new MemoryCell();
        cell.setStoredValue(37);
        int str = (int) cell.getStoredValue();
        System.out.println("str = " + str);
    }
}
