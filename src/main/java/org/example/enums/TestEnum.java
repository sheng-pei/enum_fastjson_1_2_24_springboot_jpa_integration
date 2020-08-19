package org.example.enums;

import org.example.Encoder;
import org.example.EnumConverter;

import javax.persistence.Converter;

public enum TestEnum {

    A(1, "A"), B(2, "B");

    private int code;
    private String desc;

    TestEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Encoder
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
