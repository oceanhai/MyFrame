package com.example.designpattern.builderpattern;


/**
 * Created by wuhai on 2017/11/13 14:39.
 * 描述：
 */

public class BuilderPatternTest {

    public static void main(String[] args){
//        PersonBuilder builder = new ManBuilder();
//        Person person =  builder.buildHead().buildBody().buildFoot().buildPerson();
//        System.out.println(person.toString());

        PersonDirector director = new PersonDirector();
        Person man = director.constructPerson(new ManBuilder());
        Person woman = director.constructPerson(new WomanBuilder());
        System.out.println(man.toString());
        System.out.println(woman.toString());
    }

}
