package org.apache.hudi.common.rdd;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieTable;
import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.common.HoodieWriteKey;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.common.table.HoodieTableMetaClient;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.spark.api.java.JavaRDD;

public abstract class HoodieRDDTable<T extends HoodieRecordPayload>
    extends HoodieTable<T, HoodieWriteInput<JavaRDD<HoodieRecord<T>>>, HoodieWriteKey<JavaRDD<HoodieKey>>, HoodieWriteOutput<JavaRDD<WriteStatus>>> {

  public HoodieRDDTable(HoodieWriteConfig config, HoodieTableMetaClient metaClient) {
    super(config, metaClient);
  }
}
