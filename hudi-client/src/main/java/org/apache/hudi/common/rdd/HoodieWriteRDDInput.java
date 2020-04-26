package org.apache.hudi.common.rdd;

import org.apache.hudi.common.HoodieWriteInput;
import org.apache.spark.api.java.JavaRDD;

public class HoodieWriteRDDInput<T> implements HoodieWriteInput<JavaRDD<T>> {

  private JavaRDD<T> inputs;

  public HoodieWriteRDDInput() {
    this(null);
  }

  public HoodieWriteRDDInput(JavaRDD<T> inputs) {
    this.inputs = inputs;
  }

  @Override
  public JavaRDD<T> getInputs() {
    return inputs;
  }
}
