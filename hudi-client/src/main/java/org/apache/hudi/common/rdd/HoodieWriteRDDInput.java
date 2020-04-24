package org.apache.hudi.common.rdd;

import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.spark.api.java.JavaRDD;

public class HoodieWriteRDDInput<T> implements HoodieWriteInput {

  private JavaRDD<T> inputs;
  private JavaRDD<HoodieKey> keys;

  public JavaRDD<T> getInputs() {
    return inputs;
  }

  public void setInputs(JavaRDD<T> inputs) {
    this.inputs = inputs;
  }

  public JavaRDD<HoodieKey> getKeys() {
    return keys;
  }

  public void setKeys(JavaRDD<HoodieKey> keys) {
    this.keys = keys;
  }
}
