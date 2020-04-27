package org.apache.hudi.common.rdd;

import com.codahale.metrics.Timer;
import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.HoodieWriteClientV2;
import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.common.HoodieWriteKey;
import org.apache.hudi.common.HoodieWriteOutput;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.common.HoodieIndexV2;
import org.apache.hudi.index.v2.HoodieIndexV2Factory;
import org.apache.hudi.metrics.HoodieMetrics;
import org.apache.hudi.table.v2.HoodieTableV2;
import org.apache.spark.api.java.JavaRDD;

public class HoodieWriteRDDClient<T extends HoodieRecordPayload> implements
    HoodieWriteClientV2<
        HoodieWriteInput<JavaRDD<HoodieRecord<T>>>,
        HoodieWriteKey<JavaRDD<HoodieKey>>,
        HoodieWriteOutput<JavaRDD<WriteStatus>>> {

  private final transient HoodieMetrics metrics;
  private final transient HoodieIndexV2<HoodieWriteInput, HoodieWriteInput<JavaRDD<HoodieRecord<T>>>> index;
  protected final HoodieWriteConfig config;
  private final transient HoodieRDDEngineContext context;
  private static final String LOOKUP_STR = "lookup";

  public HoodieWriteRDDClient(HoodieRDDEngineContext context, HoodieWriteConfig config) {
    this.context = context;
    this.config = config;
    this.metrics = new HoodieMetrics(config, config.getTableName());
    this.index = HoodieIndexV2Factory.createHoodieIndex(config, context);
  }

  @Override
  public HoodieWriteOutput<JavaRDD<WriteStatus>> upsert(
      HoodieWriteInput<JavaRDD<HoodieRecord<T>>> hoodieRecords, String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteOutput<JavaRDD<WriteStatus>> upsertPreppedRecords(
      HoodieWriteInput<JavaRDD<HoodieRecord<T>>> preppedRecords, String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteInput<JavaRDD<HoodieRecord<T>>> filterExists(
      HoodieWriteInput<JavaRDD<HoodieRecord<T>>> hoodieRecords) {
    // Create a Hoodie table which encapsulated the commits and files visible
    HoodieTableV2<T> table = HoodieTableV2.create(config, context);
    Timer.Context indexTimer = metrics.getIndexCtx();
    HoodieWriteInput<JavaRDD<HoodieRecord<T>>> recordsWithLocation = getIndex().tagLocation(hoodieRecords, context, table);
    metrics.updateIndexMetrics(LOOKUP_STR, metrics.getDurationInMs(indexTimer == null ? 0L : indexTimer.stop()));

    return context.filterUnknownLocations(recordsWithLocation);
  }

  @Override
  public HoodieWriteOutput<JavaRDD<WriteStatus>> insert(
      HoodieWriteInput<JavaRDD<HoodieRecord<T>>> records, String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteOutput<JavaRDD<WriteStatus>> insertPreppedRecords(
      HoodieWriteInput<JavaRDD<HoodieRecord<T>>> preppedRecords, String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteOutput<JavaRDD<WriteStatus>> bulkInsert(
      HoodieWriteInput<JavaRDD<HoodieRecord<T>>> records, String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteOutput<JavaRDD<WriteStatus>> delete(
      HoodieWriteKey<JavaRDD<HoodieKey>> keys, String instantTime) {
    return null;
  }

  @Override
  public HoodieEngineContext<HoodieWriteInput<JavaRDD<HoodieRecord<T>>>, HoodieWriteOutput<JavaRDD<WriteStatus>>> getEngineContext() {
    return null;
  }

  public HoodieIndexV2<HoodieWriteInput, HoodieWriteInput<JavaRDD<HoodieRecord<T>>>> getIndex() {
    return index;
  }
}
