package org.example.controller;

import org.example.enums.TestEnum;
import org.example.enums.TestEnum1;
import org.example.enums.TestEnum2;

public class TestParam {

    private TestEnum t1;
    private TestEnum1 t2;
    private TestEnum2 t3;
    private String content;

    public TestEnum getT1() {
        return t1;
    }

    public void setT1(TestEnum t1) {
        this.t1 = t1;
    }

    public TestEnum1 getT2() {
        return t2;
    }

    public void setT2(TestEnum1 t2) {
        this.t2 = t2;
    }

    public TestEnum2 getT3() {
        return t3;
    }

    public void setT3(TestEnum2 t3) {
        this.t3 = t3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
