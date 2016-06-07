package cem.gsearch;

public class Instrument {

    PropertyStore properties;

    public Instrument() {
        this(new PropertyStore());
    }

    public Instrument(PropertyStore properties) {
        this.properties = properties;
    }

    public PropertyStore setProperty(String key, Object value) {
        return properties.set(key, value);
    }
}
