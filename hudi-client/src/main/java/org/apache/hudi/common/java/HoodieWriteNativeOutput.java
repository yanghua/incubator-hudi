package org.apache.hudi.common.java;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecordPayload;

public class HoodieWriteNativeOutput<T extends HoodieRecordPayload> implements HoodieWriteOutput<Iterable<WriteStatus>> {
    private Iterable<WriteStatus> output;

    @Override
    public Iterable<WriteStatus> getOutput() {
        return output;
    }

    @Override
    public void setOutput(Iterable<WriteStatus> output) {
        this.output = output;
    }
}