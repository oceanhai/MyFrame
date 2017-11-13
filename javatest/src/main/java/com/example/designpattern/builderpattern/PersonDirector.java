package com.example.designpattern.builderpattern;

/**
 * Created by wuhai on 2017/11/13 16:00.
 * 描述：角色Director
 */

public class PersonDirector {

    public Person constructPerson(PersonBuilder personBuilder){
        return personBuilder.buildHead().buildBody().buildFoot().buildPerson();
    }

}
