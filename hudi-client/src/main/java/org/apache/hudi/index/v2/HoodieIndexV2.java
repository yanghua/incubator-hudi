package org.apache.hudi.index.v2;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.table.v2.HoodieTableV2;

public interface HoodieIndexV2<IN, OUT> {
    OUT fetchRecordLocation(IN inputs);

    OUT tagLocation(IN inputs, HoodieEngineContext context, HoodieTableV2 table);

    OUT updateLocation(IN inputs);
}
