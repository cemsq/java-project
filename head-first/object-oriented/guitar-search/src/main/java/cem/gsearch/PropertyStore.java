package cem.gsearch;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.Map;

public class PropertyStore {

    private Map<String, Object> map = Maps.newHashMap();

    public PropertyStore() {
    }

    public PropertyStore set(String key, Object value) {
        map.put(key, value);

        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        Preconditions.checkNotNull(clazz);

        T value = null;
        Object obj = get(key);
        if (obj != null) {
            if (clazz.isAssignableFrom(obj.getClass())) {
                value = (T) obj;
            } else {
                String msg = String.format("Cannot cast object to %s, found %s", clazz.getName(), obj.getClass().getName());
                throw new IllegalArgumentException(msg);
            }
        }

        return value;
    }

    public Object get(String key) {
        return map.get(key);
    }
}
