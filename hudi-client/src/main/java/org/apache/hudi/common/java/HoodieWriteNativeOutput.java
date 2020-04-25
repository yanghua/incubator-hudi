package org.apache.hudi.common.java;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;

public class HoodieWriteNativeOutput<T extends HoodieRecordPayload> implements HoodieWriteOutput<Iterable<WriteStatus>, Iterable<HoodieRecord<T>>> {
    private Iterable<WriteStatus> output;

    private Iterable<HoodieRecord<T>> records;

    @Override
    public Iterable<WriteStatus> getOutput() {
        return output;
    }

    @Override
    public void setOutput(Iterable<WriteStatus> output) {
        this.output = output;
    }

    @Override
    public Iterable<HoodieRecord<T>> getRecords() {
        return records;
    }

    @Override
    public void setRecords(Iterable<HoodieRecord<T>> records) {
        this.records = records;
    }
}