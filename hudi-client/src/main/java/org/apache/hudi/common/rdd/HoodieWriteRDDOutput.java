package org.apache.hudi.common.rdd;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.spark.api.java.JavaRDD;

public class HoodieWriteRDDOutput<T extends HoodieRecordPayload> implements HoodieWriteOutput {

  private JavaRDD<WriteStatus> output;

  private JavaRDD<HoodieRecord<T>> recordJavaRDD;

  public JavaRDD<WriteStatus> getOutput() {
    return output;
  }

  public void setOutput(JavaRDD<WriteStatus> output) {
    this.output = output;
  }

  public JavaRDD<HoodieRecord<T>> getRecordJavaRDD() {
    return recordJavaRDD;
  }

  public void setRecordJavaRDD(JavaRDD<HoodieRecord<T>> recordJavaRDD) {
    this.recordJavaRDD = recordJavaRDD;
  }
}
