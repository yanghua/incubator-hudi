package org.apache.hudi.common.java;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HoodieEngineNativeContext<T extends HoodieRecordPayload> implements HoodieEngineContext<HoodieWriteInput<Iterable<HoodieRecord<T>>>, HoodieWriteOutput<Iterable<WriteStatus>>> {

    @Override
    public HoodieWriteInput<Iterable<HoodieRecord<T>>> filterUnknownLocations(HoodieWriteInput<Iterable<HoodieRecord<T>>> taggedRecords) {
        Iterable<HoodieRecord<T>> filterd = StreamSupport.stream(taggedRecords.getInputs().spliterator(), false)
                        .filter(v -> !v.isCurrentLocationKnown()).collect(Collectors.toList());
        return new HoodieWriteInput<>(filterd);
    }
}
