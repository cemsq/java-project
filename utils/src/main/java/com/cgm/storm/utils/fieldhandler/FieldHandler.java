package com.cgm.storm.utils.fieldhandler;

/**
 *
 */
public interface FieldHandler {

    Object get(Object obj);

    void set(Object obj, Object value);
}
