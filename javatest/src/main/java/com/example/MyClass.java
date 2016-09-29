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

        //序列化测试
//        Entry12 entry12 = new Entry12("lufei","maiin");
//        SerializeTools.cacheObj("F:\\entry12.txt", entry12);
        Entry12.EntryInternal entryB = new Entry12.EntryInternal("lufei","maiin");
        SerializeTools.cacheObj("F:\\entryB.txt", new Entry12(entryB));
//        Entry12 newEntry = SerializeTools.getObj("F:\\entry12.txt",Entry12.class);
//        String name = newEntry.getPair().getName();
//        String mail = newEntry.getPair().getEmail();
//        System.out.println("name=" + name +",mail=" + mail);
//        System.out.println("newEntry="+newEntry.toString());

    }

}
