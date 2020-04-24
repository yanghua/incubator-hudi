package org.apache.hudi.index.v2;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.table.HoodieTable;

public class HoodieBloomIndexV2 implements HoodieIndexV2<HoodieWriteInput, HoodieWriteInput> {
    @Override
    public HoodieWriteInput fetchRecordLocation(HoodieWriteInput inputs) {
        return null;
    }

    @Override
    public HoodieWriteInput tagLocation(HoodieWriteInput inputs, HoodieEngineContext context, HoodieTable table) {
        return null;
    }

    @Override
    public HoodieWriteInput updateLocation(HoodieWriteInput inputs) {
        return null;
    }
}
