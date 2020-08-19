package org.example.enums;

import org.example.Encoder;

import java.math.BigInteger;

public enum TestEnum2 {
    S(BigInteger.valueOf(1)), T(BigInteger.valueOf(2));
    private final BigInteger bigInteger;
    TestEnum2(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

    @Encoder
    public BigInteger encode() {
        return this.bigInteger;
    }
}
