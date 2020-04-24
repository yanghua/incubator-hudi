package org.apache.hudi.common.rdd;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.spark.api.java.JavaRDD;

public class HoodieWriteRDDOutput implements HoodieWriteOutput {

  private JavaRDD<WriteStatus> output;

  public JavaRDD<WriteStatus> getOutput() {
    return output;
  }

  public void setOutput(JavaRDD<WriteStatus> output) {
    this.output = output;
  }
}
