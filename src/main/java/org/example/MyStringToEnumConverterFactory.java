package org.example;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import ppl.common.utils.EnumUtils;
import ppl.common.utils.exception.UnknownEnumException;

import java.math.BigInteger;

@SuppressWarnings({"rawtypes", "unchecked"})
public final class MyStringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        boolean supported = EnumUtils.isEncodeSupport(targetType);
        if (supported) {
            return new StringToEnumConverter(targetType);
        }
        return null;
    }

    private static class StringToEnumConverter<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumClass;

        public StringToEnumConverter(Class<T> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        public T convert(String source) {
            if (source == null) {
                return null;
            }

            Class<?> keyType = EnumUtils.getKeyType(this.enumClass);
            if (keyType.equals(String.class)) {
                return (T) EnumUtils.enumOf(this.enumClass, source);
            } else if (keyType.equals(int.class) || keyType.equals(Integer.class)) {
                try {
                    return (T) EnumUtils.enumOf(this.enumClass, Integer.parseInt(source));
                } catch (NumberFormatException e) {
                    //ignore
                }
            } else if (keyType.equals(long.class) || keyType.equals(Long.class)) {
                try {
                    return (T) EnumUtils.enumOf(this.enumClass, Long.parseLong(source));
                } catch (NumberFormatException e) {
                    //ignore
                }
            } else if (keyType.equals(short.class) || keyType.equals(Short.class)) {
                try {
                    return (T) EnumUtils.enumOf(this.enumClass, Short.parseShort(source));
                } catch (NumberFormatException e) {
                    //ignore
                }
            } else if (keyType.equals(byte.class) || keyType.equals(Byte.class)) {
                try {
                    return (T) EnumUtils.enumOf(this.enumClass, Byte.parseByte(source));
                } catch (NumberFormatException e) {
                    //ignore
                }
            } else if (keyType.equals(char.class) || keyType.equals(Character.class)) {
                if (source.length() == 1) {
                    return (T) EnumUtils.enumOf(this.enumClass, source.charAt(0));
                }
            } else if (keyType.equals(boolean.class) || keyType.equals(Boolean.class)) {
                return (T) EnumUtils.enumOf(this.enumClass, Boolean.parseBoolean(source));
            } else if (keyType.equals(BigInteger.class)) {
                try {
                    return (T) EnumUtils.enumOf(this.enumClass, new BigInteger(source));
                } catch (NumberFormatException e) {
                    //ignore
                }
            }
            throw new UnknownEnumException((Class<? extends Enum<?>>) this.enumClass, source);
        }

    }

}
