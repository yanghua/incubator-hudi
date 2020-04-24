package org.apache.hudi.index.v2;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.table.HoodieTable;

public interface HoodieIndexV2<IN, OUT> {
    OUT fetchRecordLocation(IN inputs);

    OUT tagLocation(IN inputs, HoodieEngineContext context, HoodieTable table);

    OUT updateLocation(IN inputs);
}
