package com.example.designpattern.builderpattern;

/**
 * Created by wuhai on 2017/11/13 15:54.
 * 描述：角色ConcreteBuilder
 */

public class WomanBuilder implements PersonBuilder {

    private Person person;

    public WomanBuilder() {
        this.person = new Woman();
    }

    @Override
    public PersonBuilder buildHead() {
        person.setHead("建造女人的头");
        return this;
    }

    @Override
    public PersonBuilder buildBody() {
        person.setBody("建造女人的身体");
        return this;
    }

    @Override
    public PersonBuilder buildFoot() {
        person.setFoot("建造女人的脚");
        return this;
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
