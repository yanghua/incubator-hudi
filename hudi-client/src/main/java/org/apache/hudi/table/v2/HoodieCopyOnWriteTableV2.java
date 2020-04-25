package org.apache.hudi.table.v2;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.config.HoodieWriteConfig;

public class HoodieCopyOnWriteTableV2 extends HoodieTableV2 {
    public HoodieCopyOnWriteTableV2(HoodieWriteConfig config, HoodieEngineContext context) {
        super(config, context);
    }
}
