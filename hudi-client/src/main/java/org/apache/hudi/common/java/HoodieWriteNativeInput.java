package org.apache.hudi.common.java;

import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.common.model.HoodieKey;

public class HoodieWriteNativeInput<T> implements HoodieWriteInput {
    private Iterable<T> inputs;
    private Iterable<HoodieKey> keys;

    public void setInputs(Iterable<T> inputs) {
        this.inputs = inputs;
    }

    public void setKeys(Iterable<HoodieKey> keys) {
        this.keys = keys;
    }

    public Iterable<T> getInputs() {
        return inputs;
    }

    public Iterable<HoodieKey> getKeys() {
        return keys;
    }
}

