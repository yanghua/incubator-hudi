package org.apache.hudi.common;

import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.table.HoodieTable;
import org.apache.spark.api.java.JavaSparkContext;

public class ActionContext {

  private final transient JavaSparkContext jsc;
  private final HoodieWriteConfig config;
  private final HoodieTable<?> table;
  private final String instantTime;

  public ActionContext(JavaSparkContext jsc, HoodieWriteConfig config,
      HoodieTable<?> table, String instantTime) {
    this.jsc = jsc;
    this.config = config;
    this.table = table;
    this.instantTime = instantTime;
  }

  public JavaSparkContext getJsc() {
    return jsc;
  }

  public HoodieWriteConfig getConfig() {
    return config;
  }

  public HoodieTable<?> getTable() {
    return table;
  }

  public String getInstantTime() {
    return instantTime;
  }
}
