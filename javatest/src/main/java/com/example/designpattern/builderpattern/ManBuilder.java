package com.example.designpattern.builderpattern;

/**
 * Created by wuhai on 2017/11/13 14:34.
 * 描述：角色ConcreteBuilder
 */

public class ManBuilder implements PersonBuilder {

    Person person;

    public ManBuilder() {
        person = new Man();
    }

    @Override
    public PersonBuilder buildHead() {
        person.setHead("建造男人的头");
        return this;
    }

    @Override
    public PersonBuilder buildBody() {
        person.setBody("建造男人的身体");
        return this;
    }

    @Override
    public PersonBuilder buildFoot() {
        person.setFoot("建造男人的脚");
        return this;
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
