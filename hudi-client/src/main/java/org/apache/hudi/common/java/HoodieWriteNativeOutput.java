package org.apache.hudi.common.java;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;

public class HoodieWriteNativeOutput<T extends HoodieRecordPayload> implements HoodieWriteOutput {
    private Iterable<WriteStatus> output;

    private Iterable<HoodieRecord<T>> records;

    public Iterable<WriteStatus> getOutput() {
        return output;
    }

    public void setOutput(Iterable<WriteStatus> output) {
        this.output = output;
    }

    public Iterable<HoodieRecord<T>> getRecords() {
        return records;
    }

    public void setRecords(Iterable<HoodieRecord<T>> records) {
        this.records = records;
    }
}