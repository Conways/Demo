package com.conways.demo.home.firstmenu.sqlite;

/**
 * Created by Conways on 2016/6/19.
 */
public class User extends BaseModel {
    private int id;
    private String name;
    private long birthday;
    private int sex;
    private String introduce;
    private String head;

    public User(int id, String name, long birthday, int sex, String introduce, String head) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.introduce = introduce;
        this.head = head;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", introduce='" + introduce + '\'' +
                ", head='" + head + '\'' +
                '}';
    }
}
