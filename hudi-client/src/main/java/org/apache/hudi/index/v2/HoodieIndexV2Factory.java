package org.apache.hudi.index.v2;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.config.HoodieWriteConfig;

public class HoodieIndexV2Factory {
    public static <IN> HoodieIndexV2 createHoodieIndex(HoodieWriteConfig config, HoodieEngineContext context) {
        return new HoodieBloomIndexV2<IN>();
    }
}
