package org.apache.hudi.common;

public interface HoodieWriteOutput<O> {
    void setOutput(O outputs);

    O getOutput();
}
