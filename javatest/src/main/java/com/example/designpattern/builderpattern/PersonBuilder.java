package com.example.designpattern.builderpattern;

/**
 * Created by wuhai on 2017/11/13 14:02.
 * 描述：角色Builder
 */

public interface PersonBuilder {
    PersonBuilder buildHead();
    PersonBuilder buildBody();
    PersonBuilder buildFoot();
    Person buildPerson();
}
