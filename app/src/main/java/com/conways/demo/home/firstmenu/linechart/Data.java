package com.conways.demo.home.firstmenu.linechart;

/**
 * Created by user on 2016/6/30.
 */
public class Data implements Comparable {

    private int value;
    private int position;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    @Override
    public int compareTo(Object another) {
        Data data = (Data) another;
        if (data.getPosition() > this.getPosition()) {
            return -1;
        } else if (data.getPosition() < this.getPosition()) {
            return 1;
        }
        return 0;
    }
}
