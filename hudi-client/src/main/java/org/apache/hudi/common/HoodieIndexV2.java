package org.apache.hudi.common;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.table.v2.HoodieTableV2;

public interface HoodieIndexV2<IN, OUT> {
    IN fetchRecordLocation(IN inputs);

    IN tagLocation(IN inputs, HoodieEngineContext context, HoodieTableV2 table);

    OUT updateLocation(IN inputs);
}
