package org.apache.hudi.common.java;

import org.apache.hudi.common.HoodieWriteInput;

public class HoodieWriteNativeInput<T> implements HoodieWriteInput<Iterable<T>> {
    private Iterable<T> inputs;

    public HoodieWriteNativeInput() {
        this(null);
    }

    public HoodieWriteNativeInput(Iterable<T> inputs) {
        this.inputs = inputs;
    }

    @Override
    public Iterable<T> getInputs() {
        return inputs;
    }

}

