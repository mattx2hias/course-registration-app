package dev.matthias.utilities;

import java.util.Arrays;

public class ArrayList<T> implements List<T>{

    private Object [] e;
    private int i;

    public ArrayList() {
        this.e = new Object[10];
        this.i = 0;
    }

    @Override
    public void add(int i, Object element) {

    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    private void resize() {
        this.e = Arrays.copyOf(this.e, this.e.length + 10);
    }
}
