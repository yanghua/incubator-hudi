package org.apache.hudi.common.rdd;

import org.apache.hudi.common.HoodieWriteInput;
import org.apache.spark.api.java.JavaRDD;

public class HoodieWriteRDDInput<T> implements HoodieWriteInput<JavaRDD<T>> {

  private JavaRDD<T> inputs;

  @Override
  public JavaRDD<T> getInputs() {
    return inputs;
  }

  @Override
  public void setInputs(JavaRDD<T> inputs) {
    this.inputs = inputs;
  }
}
