package com.my.BeanUtils;

public class Class1 {
    private String name;
    private String sex;
    private Teature teature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Teature getTeature() {
        return teature;
    }

    public void setTeature(Teature teature) {
        this.teature = teature;
    }


    public static class Teature{
        private String t1;
        private String t2;

        public String getT1() {
            return t1;
        }

        public void setT1(String t1) {
            this.t1 = t1;
        }

        public String getT2() {
            return t2;
        }

        public void setT2(String t2) {
            this.t2 = t2;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
