package org.example.enums;

//import com.fasterxml.jackson.annotation.JsonCreator;
import org.example.Encoder;

public enum TestEnum1 {

    U("U"), V("V");

    private final String name;

    TestEnum1(String name) {
        this.name = name;
    }

    @Encoder
    public String encode() {
        return this.name;
    }
}
