package org.apache.hudi.common;

public interface HoodieWriteInput<INPUT> {

    INPUT getInputs();
}
