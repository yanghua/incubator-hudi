package org.apache.hudi.common.java;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecordPayload;

public class HoodieWriteNativeOutput<T extends HoodieRecordPayload> implements HoodieWriteOutput<Iterable<WriteStatus>> {
    private Iterable<WriteStatus> output;

    public HoodieWriteNativeOutput() {}

    public HoodieWriteNativeOutput(Iterable<WriteStatus> output) {
        this.output = output;
    }

    @Override
    public Iterable<WriteStatus> getOutput() {
        return output;
    }

    public void setOutput(Iterable<WriteStatus> output) {
        this.output = output;
    }
}