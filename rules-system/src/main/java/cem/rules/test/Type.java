package cem.rules.test;

import com.google.common.collect.Lists;

import java.util.List;

public enum Type {
    System(Characteristic.System),

    Provider(Characteristic.Provider),
    Group(Characteristic.ProviderGroup),
    Clinic(Characteristic.ProviderShipToGroup),
    Address(Characteristic.ProviderShipTo),

    Empty()
    ;

    private List<Characteristic> characteristics;

    Type(Characteristic... chr) {
        characteristics = Lists.newArrayList(chr);
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }
}
