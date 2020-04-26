package org.apache.hudi.common;

public interface HoodieWriteOutput<O> {

    O getOutput();
}
