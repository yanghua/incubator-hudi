package org.apache.hudi.common.rdd;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.spark.api.java.JavaRDD;

public class HoodieWriteRDDOutput<T extends HoodieRecordPayload> implements HoodieWriteOutput<JavaRDD<WriteStatus>> {

  private JavaRDD<WriteStatus> output;

  public HoodieWriteRDDOutput() {

  }

  public HoodieWriteRDDOutput(JavaRDD<WriteStatus> output) {
    this.output = output;
  }

  @Override
  public JavaRDD<WriteStatus> getOutput() {
    return output;
  }
}
