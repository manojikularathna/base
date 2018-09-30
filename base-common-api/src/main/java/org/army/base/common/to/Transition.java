package org.army.base.common.to;

public class Transition<T> {

    private T newVal;

    private T oldVal;

    public T getNewVal() {
        return newVal;
    }

    public void setNewVal(T newVal) {
        this.newVal = newVal;
    }

    public T getOldVal() {
        return oldVal;
    }

    public void setOldVal(T oldVal) {
        this.oldVal = oldVal;
    }
}