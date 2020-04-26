package org.apache.hudi.common.rdd;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class HoodieEngineRDDContext<T extends HoodieRecordPayload> implements HoodieEngineContext<HoodieWriteInput<JavaRDD<HoodieRecord<T>>>, HoodieWriteOutput<JavaRDD<WriteStatus>>> {

  private JavaSparkContext rddContext;


  public JavaSparkContext getRddContext() {
    return rddContext;
  }

  public void setRddContext(JavaSparkContext rddContext) {
    this.rddContext = rddContext;
  }

  @Override
  public HoodieWriteInput<JavaRDD<HoodieRecord<T>>> filterUnknownLocations(
      HoodieWriteInput<JavaRDD<HoodieRecord<T>>> taggedRecords) {
    JavaRDD<HoodieRecord<T>> inputs = taggedRecords.getInputs().filter(v1 -> !v1.isCurrentLocationKnown());

    return new HoodieWriteInput<>(inputs);
  }
}
