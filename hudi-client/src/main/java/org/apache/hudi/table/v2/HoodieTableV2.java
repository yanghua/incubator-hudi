package org.apache.hudi.table.v2;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.index.v2.HoodieIndexV2;
import org.apache.hudi.index.v2.HoodieIndexV2Factory;

import java.io.Serializable;

public abstract class HoodieTableV2<T extends HoodieRecordPayload> implements Serializable {
    protected HoodieIndexV2 indexV2;

    public static HoodieTableV2 create(HoodieWriteConfig config, HoodieEngineContext context) {
        return new HoodieCopyOnWriteTableV2(config, context);
    }


    public HoodieTableV2(HoodieWriteConfig config, HoodieEngineContext context) {
      indexV2 = HoodieIndexV2Factory.createHoodieIndex(config, context);
    }

    public HoodieIndexV2 getIndexV2() {
        return indexV2;
    }
}
