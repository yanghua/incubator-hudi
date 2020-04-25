package org.apache.hudi.common;

public interface HoodieWriteInput<INPUT> {

    void setInputs(INPUT inputs);

    INPUT getInputs();
}
