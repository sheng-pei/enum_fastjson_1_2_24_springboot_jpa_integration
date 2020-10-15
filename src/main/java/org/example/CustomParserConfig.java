package org.example;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import ppl.common.utils.EnumUtils;
import ppl.common.utils.StringUtils;

import java.lang.reflect.Type;
import java.math.BigInteger;

@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomParserConfig extends ParserConfig {

    @Override
    public ObjectDeserializer getDeserializer(Class<?> clazz, Type type) {
        if (Enum.class.isAssignableFrom(clazz)) {
            if (EnumUtils.isEncodeSupport((Class<? extends Enum>)clazz)) {
                if (type == null) {
                    type = clazz;
                }

                if (this.getDerializers().get(type) == null) {
                    this.putDeserializer(type, new CustomEnumDeserializer((Class<? extends Enum>) type));
                }
            }
        }
        return super.getDeserializer(clazz, type);
    }

    private static class CustomEnumDeserializer implements ObjectDeserializer {

        private final Class<? extends Enum> enumClass;
        private final Class<?> keyClass;

        public CustomEnumDeserializer(Class<? extends Enum> clazz) {
            this.enumClass = clazz;
            this.keyClass = EnumUtils.getKeyType(clazz);
        }

        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object o) {

            final JSONLexer lexer = parser.lexer;
            final int token = lexer.token();
            if (token == JSONToken.LITERAL_INT) {

                BigInteger bigIntegerValue = lexer.decimalValue().toBigInteger();
                lexer.nextToken(JSONToken.COMMA);

                if (isLong(keyClass)) {
                    checkInRange(bigIntegerValue, Long.MIN_VALUE, Long.MAX_VALUE);
                    return enumOf(bigIntegerValue.longValue());
                } else if (isInteger(keyClass)) {
                    checkInRange(bigIntegerValue, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    return enumOf(bigIntegerValue.intValue());
                } else if (isShort(keyClass)) {
                    checkInRange(bigIntegerValue, Short.MIN_VALUE, Short.MAX_VALUE);
                    return enumOf(bigIntegerValue.shortValue());
                } else if (isByte(keyClass)) {
                    checkInRange(bigIntegerValue, Byte.MIN_VALUE, Byte.MAX_VALUE);
                    return enumOf(bigIntegerValue.byteValue());
                } else if (isBigInteger(keyClass)) {
                    return enumOf(bigIntegerValue);
                }

                throw new JSONException(StringUtils.format(
                        "Parse enum({}) error, the specified value must be {} but int literal value",
                        enumClass.getCanonicalName(),
                        keyClass.getCanonicalName()));

            } else if (token == JSONToken.LITERAL_STRING) {
                String name = lexer.stringVal();
                lexer.nextToken(JSONToken.COMMA);

                if (keyClass.equals(String.class)) {
                    return enumOf(name);
                }

                throw new JSONException(StringUtils.format(
                        "Parse enum({}) error, the specified value must be {} but string literal value",
                        enumClass.getCanonicalName(),
                        keyClass.getCanonicalName()));
            } else if (token == JSONToken.NULL) {
                lexer.nextToken(JSONToken.COMMA);
                return null;
            } else {
                throw new JSONException(StringUtils.format(
                        "Parse enum({}) error, unsupported data type {}",
                        parser.parse().getClass().getCanonicalName()));
            }
        }

        private <T> T enumOf(Object value) {
            return (T) EnumUtils.enumOf(enumClass, value);
        }

        private boolean isByte(Class<?> clazz) {
            return keyClass.equals(Byte.class) || keyClass.equals(byte.class);
        }

        private boolean isShort(Class<?> clazz) {
            return keyClass.equals(Short.class) || keyClass.equals(short.class);
        }

        private boolean isInteger(Class<?> clazz) {
            return keyClass.equals(Integer.class) || keyClass.equals(int.class);
        }

        private boolean isLong(Class<?> clazz) {
            return keyClass.equals(Long.class) || keyClass.equals(long.class);
        }

        private boolean isBigInteger(Class<?> clazz) {
            return keyClass.equals(BigInteger.class);
        }

        private void checkInRange(BigInteger value, long min, long max) {
            if (!isInRange(value, min, max)) {
                throw new JSONException(StringUtils.format(
                        "Parse enum({}) error, value: {} not in the range of {}",
                        enumClass.getCanonicalName(),
                        value.toString(),
                        keyClass.getCanonicalName()));
            }
        }

        private boolean isInRange(BigInteger value, long min, long max) {
            return value.compareTo(BigInteger.valueOf(min)) >= 0 && value.compareTo(BigInteger.valueOf(max)) <= 0;
        }

        @Override
        public int getFastMatchToken() {
            return 0;
        }

    }

}
