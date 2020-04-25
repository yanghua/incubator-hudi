package org.apache.hudi.common;

public interface HoodieWriteOutput<O, R> {
    void setOutput(O outputs);

    void setRecords(R records);

    O getOutput();

    R getRecords();

}
