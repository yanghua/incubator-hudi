package org.apache.hudi.common.rdd;

import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.spark.api.java.JavaSparkContext;

public class HoodieEngineRDDContext<T extends HoodieRecordPayload> implements HoodieEngineContext<HoodieWriteRDDInput<HoodieRecord<T>>, HoodieWriteRDDOutput> {

  private JavaSparkContext rddContext;


  public JavaSparkContext getRddContext() {
    return rddContext;
  }

  public void setRddContext(JavaSparkContext rddContext) {
    this.rddContext = rddContext;
  }

  @Override
  public HoodieWriteRDDInput filterUnknownLocations(
          HoodieWriteRDDInput<HoodieRecord<T>> taggedRecords) {
    HoodieWriteRDDInput<HoodieRecord<T>> input = new HoodieWriteRDDInput();
    input.setInputs(taggedRecords.getInputs().filter(v1 -> !v1.isCurrentLocationKnown()));
    return input;
  }
}
