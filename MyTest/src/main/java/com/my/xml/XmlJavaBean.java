package com.my.xml;

import com.thoughtworks.xstream.annotations.*;

import java.io.Serializable;
import java.util.List;

@XStreamAlias("feature")
public class XmlJavaBean implements Serializable {
    @XStreamAlias("name")
    @XStreamAsAttribute
    private String name;

    @XStreamImplicit(itemFieldName = "attret")
    private List<Child> attrets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getAttrets() {
        return attrets;
    }

    public void setAttrets(List<Child> attrets) {
        this.attrets = attrets;
    }

    public static class Child implements Serializable{
        @XStreamAlias("name")
        @XStreamAsAttribute
        private String name;
        @XStreamInclude()
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
