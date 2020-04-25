package org.apache.hudi.common.java;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HoodieEngineNativeContext<T extends HoodieRecordPayload> implements HoodieEngineContext<HoodieWriteNativeInput<HoodieRecord<T>>, HoodieWriteNativeOutput> {

    @Override
    public HoodieWriteNativeOutput<T> filterUnknownLocations(HoodieWriteNativeInput<HoodieRecord<T>> taggedRecords) {
        HoodieWriteNativeOutput<T> output = new HoodieWriteNativeOutput<>();
        Iterable<HoodieRecord<T>> filterd = StreamSupport.stream(taggedRecords.getInputs().spliterator(), false)
                        .filter(v -> !v.isCurrentLocationKnown()).collect(Collectors.toList());
        output.setRecords(filterd);
        return output;
    }
}
