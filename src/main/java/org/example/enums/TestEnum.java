package org.example.enums;

import ppl.common.utils.EnumConverter;
import ppl.common.utils.EnumEncoder;

import javax.persistence.Converter;

public enum TestEnum {

    A(1, "A"), B(2, "B");

    private int code;
    private String desc;

    TestEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumEncoder
    public int encode() {
        return this.code;
    }

    @Converter(autoApply = true)
    public static class TestConverter extends EnumConverter<TestEnum, Integer> {
        public TestConverter() {
            super(TestEnum.class, Integer.class);
        }
    }

}
