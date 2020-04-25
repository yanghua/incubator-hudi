package org.apache.hudi.common.rdd;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.spark.api.java.JavaRDD;

public class HoodieWriteRDDOutput<T extends HoodieRecordPayload> implements HoodieWriteOutput<JavaRDD<WriteStatus>, JavaRDD<HoodieRecord<T>>> {

  private JavaRDD<WriteStatus> output;

  private JavaRDD<HoodieRecord<T>> records;

  @Override
  public void setOutput(JavaRDD<WriteStatus> output) {
    this.output = output;
  }

  @Override
  public void setRecords(JavaRDD<HoodieRecord<T>> records) {
    this.records = records;
  }
  
  @Override
  public JavaRDD<WriteStatus> getOutput() {
    return output;
  }

  @Override
  public JavaRDD<HoodieRecord<T>> getRecords() {
    return records;
  }
}
