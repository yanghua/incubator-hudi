package org.apache.hudi.index.v2;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.table.v2.HoodieTableV2;

public class HoodieBloomIndexV2<IN> implements HoodieIndexV2<HoodieWriteInput<IN>, HoodieWriteInput<IN>> {

    @Override
    public HoodieWriteInput<IN> fetchRecordLocation(HoodieWriteInput<IN> inputs) {
        return null;
    }

    @Override
    public HoodieWriteInput<IN> tagLocation(HoodieWriteInput<IN> inputs, HoodieEngineContext context, HoodieTableV2 table) {
        return null;
    }

    @Override
    public HoodieWriteInput<IN> updateLocation(HoodieWriteInput<IN> inputs) {
        return null;
    }
}
