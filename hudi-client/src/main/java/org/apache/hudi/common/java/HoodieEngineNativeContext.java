package org.apache.hudi.common.java;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;

public class HoodieEngineNativeContext<T extends HoodieRecordPayload> implements HoodieEngineContext<HoodieWriteNativeInput<HoodieRecord<T>>, HoodieWriteNativeOutput> {

    @Override
    public HoodieWriteNativeOutput<T> filterUnknownLocations(HoodieWriteNativeInput<HoodieRecord<T>> taggedRecords) {
        HoodieWriteNativeOutput<T> output = new HoodieWriteNativeOutput<>();
        output.setRecords(taggedRecords.getInputs());
        return output;
    }
}
