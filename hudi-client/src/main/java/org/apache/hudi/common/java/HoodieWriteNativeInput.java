package org.apache.hudi.common.java;

import org.apache.hudi.common.HoodieWriteInput;

public class HoodieWriteNativeInput<T> implements HoodieWriteInput<Iterable<T>> {
    private Iterable<T> inputs;

    public HoodieWriteNativeInput() {
    }

    public HoodieWriteNativeInput(Iterable<T> inputs) {
        this.inputs = inputs;
    }

    public void setInputs(Iterable<T> inputs) {
        this.inputs = inputs;
    }

    @Override
    public Iterable<T> getInputs() {
        return inputs;
    }

}

