package com.my.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;
import java.util.Set;

public class PersionBean {
    @XStreamAlias("name")
    private String name;
    @XStreamAlias("sex")
    private String sex;
    @XStreamAlias("age")
    private int age;
    @XStreamAlias("friend")
    private Friend friend;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public static class Friend{
        @XStreamImplicit(itemFieldName = "books")
        private List<String> books;
        @XStreamImplicit(itemFieldName = "lovers")
        private Set<String> lovers;

        public List<String> getBooks() {
            return books;
        }

        public void setBooks(List<String> books) {
            this.books = books;
        }

        public Set<String> getLovers() {
            return lovers;
        }

        public void setLovers(Set<String> lovers) {
            this.lovers = lovers;
        }
    }
}
