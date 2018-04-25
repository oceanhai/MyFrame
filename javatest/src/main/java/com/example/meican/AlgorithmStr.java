package com.example.meican;

/**
 * Created by wuhai on 2018/4/25.
 */

public class AlgorithmStr {


    public static void main(String[] args){
//        method01();

//        String str1 = "ABCDEGCBBA";
//        String str2 = "CG";
//        System.out.println(minWindow(str1,str2));
//
//        String str3 = "ABCDEAGBCBBA";
//        String str4 = "ACG";
//        System.out.println(minWindow(str3,str4));

        String str3 = "ACBCDEFFFFFF";
        String str4 = "ABCDE";
//        System.out.println(minWindow(str3,str4));
        System.out.println(minWindow2(str3,str4));

    }

    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
    public static String minWindow(String source, String target) {
        // write your code
        if(source.length()<target.length()){
            return "";
        }
        for(int i=target.length();i<=source.length();i++){
            for(int j=0;j+i<=source.length();j++){
                String s=source.substring(j, j+i);
                StringBuffer sb=new StringBuffer(s);
                int count=0;
                System.out.println("sb="+sb.toString());//查询的子串
                for(int k=0;k<target.length();k++){
                    if(sb.indexOf(target.charAt(k)+"")!=-1){
                        System.out.println("delete-"+k+":"+target.charAt(k)+",删的下标:"+sb.indexOf(target.charAt(k)+""));//删掉的字母
                        sb.deleteCharAt(sb.indexOf(target.charAt(k)+""));
                        count++;
                        System.out.println("count="+count);
                    }else{
                        break;
                    }
                }
                if(count==target.length()){
                    return s;
                }
            }
        }
        return "";
    }

    /**
     * 改进下 StringBuffer 只判断是否含有
     * @param source
     * @param target
     * @return
     */
    public static String minWindow2(String source, String target) {
        // write your code
        if(source.length()<target.length()){
            return "";
        }
        for(int i=target.length();i<=source.length();i++){
            for(int j=0;j+i<=source.length();j++){
                String s=source.substring(j, j+i);
                int count=0;
                System.out.println("sb="+s);//查询的子串
                for(int k=0;k<target.length();k++){
                    if(s.contains(target.charAt(k)+"")){
                        count++;
                        System.out.println("count="+count);//看是否都包含
                    }else{
                        break;
                    }
                }
                if(count==target.length()){
                    return s;
                }
            }
        }
        return "";
    }

    /**
     * 面试给的解决方案是 用两个指针，指针1开始指向0位置，指针2后移继续找，找到看是否匹配
     * 但指针2搜索字符等于指针1的字符时候，指针1后移开始、
     * 上述就是面试给出的解决方案，但这样其实是不对的，就是我main中的第三种情况就会有问题
     * @param str1
     * @param str2
     * @return
     */
    public static int subCount(String str1, String str2){
        char[] arr = str1.toCharArray();
        arr.hashCode();
        int minLength = 0;
        int pointerX = 0;
        int pointerY = 1;
        while (pointerX<arr.length && pointerY<arr.length){
          if(str2.contains(""+arr[pointerX])){
              for(;pointerY<arr.length;pointerY++){
                  if (!str2.contains(""+arr[pointerX])){
                      if(arr[pointerX] == arr[pointerY]){

                      }
                      //TODO 判断pointerX 到pointerY 之间是否有完整str2,有记录minLength
                      //pointerY 继续后移
                  }
              }

          }else{
              pointerX++;
          }
        }

        return minLength;
    }

    public static void method01(){
        char[] arr1 = {'c','g'};
        char[] arr2 = {'g','c'};
        System.out.println("arr1.hashcode="+arr1.hashCode()+",arr1.hashcode="+arr2.hashCode());
    }
}
