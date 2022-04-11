package dev.matthias.utilities;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private Object [] list;
    private int i;

    public ArrayList() {
        this.list = new Object[1];
        this.i = 0;
    }
    @Override
    public Object[] getList() {
        return list;
    }

    @Override
    public void add(int i, Object element) {
        if(this.list.length <= this.i )
            resize();
        Object[] newList = new Object[this.list.length];
        for(int j = 0; j < newList.length; j++)
            newList[j] = (j != i) ? this.list[j] : element ;
        this.list = newList;
        this.i++;
    }

    @Override
    public void append(T element) {
        if(this.list.length <= this.i)
            resize();
        this.list[this.i] = element;
        this.i++;
    }

    @Override
    public T get(int i) {
        return (T) this.list[i];
    }

    @Override
    public int size() {
        return this.i;
    }

    private void resize() {
        this.list = Arrays.copyOf(this.list, this.list.length + 10);
    }
}
