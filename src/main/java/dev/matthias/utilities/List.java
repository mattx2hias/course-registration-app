package dev.matthias.utilities;

public interface List<T> {

    public Object[] getList();

    void add(int i, T element);

    void append(T element);

    T get(int i);

    int size();
}
