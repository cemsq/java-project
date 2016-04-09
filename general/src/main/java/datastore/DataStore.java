package datastore;

public interface DataStore<V> {

    DataStore<V> add(V value);

    V getRow(int index);

    void setRow(int index, V newValue);

    int size();
}
