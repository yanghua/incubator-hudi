package org.apache.hudi.common.rdd;

import com.codahale.metrics.Timer;
import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.HoodieWriteClientV2;
import org.apache.hudi.common.HoodieWriteInput;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.index.v2.HoodieIndexV2;
import org.apache.hudi.index.v2.HoodieIndexV2Factory;
import org.apache.hudi.metrics.HoodieMetrics;
import org.apache.hudi.table.v2.HoodieTableV2;

public class HoodieWriteRDDClient<T extends HoodieRecordPayload> implements
    HoodieWriteClientV2<HoodieWriteRDDInput<HoodieRecord<T>>, HoodieWriteRDDOutput> {


  private final transient HoodieMetrics metrics;
  private final transient HoodieIndexV2<HoodieWriteInput, HoodieWriteRDDInput> index;
  protected final HoodieWriteConfig config;
  private final transient HoodieEngineRDDContext context;
  private static final String LOOKUP_STR = "lookup";

  public HoodieWriteRDDClient(HoodieEngineRDDContext context, HoodieWriteConfig config) {
    this.context = context;
    this.config = config;
    this.metrics = new HoodieMetrics(config, config.getTableName());
    this.index = HoodieIndexV2Factory.createHoodieIndex(config, context);
  }

  @Override
  public HoodieWriteRDDOutput upsert(HoodieWriteRDDInput<HoodieRecord<T>> hoodieRecords,
      String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteRDDOutput upsertPreppedRecords(
      HoodieWriteRDDInput<HoodieRecord<T>> preppedRecords, String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteRDDInput<HoodieRecord<T>> filterExists(HoodieWriteRDDInput<HoodieRecord<T>> hoodieRecords) {
    // Create a Hoodie table which encapsulated the commits and files visible
    HoodieTableV2<T> table = HoodieTableV2.create(config, context);
    Timer.Context indexTimer = metrics.getIndexCtx();
    HoodieWriteRDDInput recordsWithLocation = getIndex().tagLocation(hoodieRecords, context, table);
    metrics.updateIndexMetrics(LOOKUP_STR, metrics.getDurationInMs(indexTimer == null ? 0L : indexTimer.stop()));

    return new HoodieWriteRDDInput<>(context.filterUnknownLocations(recordsWithLocation).getInputs());

  }

  @Override
  public HoodieWriteRDDOutput insert(HoodieWriteRDDInput<HoodieRecord<T>> records,
      String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteRDDOutput insertPreppedRecords(
      HoodieWriteRDDInput<HoodieRecord<T>> preppedRecords, String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteRDDOutput bulkInsert(HoodieWriteRDDInput<HoodieRecord<T>> records,
      String instantTime) {
    return null;
  }

  @Override
  public HoodieWriteRDDOutput delete(HoodieWriteRDDInput<HoodieRecord<T>> keys,
      String instantTime) {
    return null;
  }

  @Override
  public HoodieEngineContext<HoodieWriteRDDInput<HoodieRecord<T>>, HoodieWriteRDDOutput> getEngineContext() {
    return context;
  }

  public HoodieIndexV2<HoodieWriteInput, HoodieWriteRDDInput> getIndex() {
    return index;
  }
}
