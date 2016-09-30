package com.example;

import java.io.Serializable;

/**
 * Created by wuhai on 2016/9/29.
 * 参考文章http://zhidao.baidu.com/link?url=4m8n8is6u7TpVzs28NSj_oO4C5bNhwYEhdcoGkm9sLrDvgZiaaHNCCF-kfAX25dQfI50v8kScIVRI-Y4bed7ypcCpRRx_9tkq4jOUTpK8Mi
 */
public class Entry12 implements Serializable{
//    private String name;
//    private String email;
//    private String age;
////    private static final long serialVersionUID = 12L;
//
////    public Entry12(String name, String email) {
////        this.name = name;
////        this.email = email;
////    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
////    public String getEmail() {
////        return email;
////    }
////
////    public void setEmail(String email) {
////        this.email = email;
////    }
//
//    @Override
//    public String toString() {
//        return "Entry12{" +
//                "name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", age='" + age + '\'' +
//                '}';
//    }


    /**
     * 结构变了要进行以下操作 才能正常取到值
     */
    private static final long serialVersionUID = 12L;
    static class EntryInternal implements Serializable{
        private static final long serialVersionUID = 13L;
        private String name;
        private String email;
        private String age;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public EntryInternal() {
        }

        public EntryInternal(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "EntryInternal{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    private EntryInternal pair ;

    public Entry12(EntryInternal pair) {
        this.pair = pair;
    }

    public EntryInternal getPair() {
        return pair;
    }

    public void setPair(EntryInternal pair) {
        this.pair = pair;
    }

    @Override
    public String toString() {
        return "Entry12{" +
                "pair=" + pair +
                '}';
    }

//    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] {
//            new ObjectStreamField("name" , String.class),//
//            new ObjectStreamField("email" , String.class),//
//    };
//    /* 我们在这里不是直接写出字段，而把要写出的字段包装起来，
//       我们按需交换字段，而不是直接读写pair 这个字段.
//    */
//    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
//        ObjectInputStream.GetField getFields = input.readFields();
//
//    /* 请注意：使用 Serializable 进行交换时不使用构造方法，所以这时 pair 还未初始化. */
//        pair = new EntryInternal();
//
//        pair.name = (String) getFields.get("name", null);
//        pair.email = (String) getFields.get("email", null);
//    }

}
