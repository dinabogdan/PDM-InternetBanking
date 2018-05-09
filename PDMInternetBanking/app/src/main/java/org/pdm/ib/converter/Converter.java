package org.pdm.ib.converter;

public interface Converter<C, E> {

    E convertToEntity(C c);

    C convertToCommand(E e);
}
