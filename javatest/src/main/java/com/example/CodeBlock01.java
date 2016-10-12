package com.example;

/**
 * Created by wuhai on 2016/10/11.
 */
//普通代码块：在方法或语句中出现的{}就称为普通代码块。
// 普通代码块和一般的语句执行顺序由他们在代码中出现的次序决定--“先出现先执行”
 public class CodeBlock01{
       public static void main(String[] args){

             {
               int x=3;
               System.out.println("1,普通代码块内的变量x="+x);
             }

             int x=1;
             System.out.println("主方法内的变量x="+x);

             {
                int y=7;
                System.out.println("2,普通代码块内的变量y="+y);
             }
           }
     }

     /*
     运行结果：
     1,普通代码块内的变量x=3
          主方法内的变量x=1
          2,普通代码块内的变量y=7
     */

