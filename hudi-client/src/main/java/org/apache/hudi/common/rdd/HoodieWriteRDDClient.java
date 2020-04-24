package org.apache.hudi.common.rdd;

import com.codahale.metrics.Timer;
import org.apache.hudi.common.HoodieEngineContext;
import org.apache.hudi.common.HoodieWriteClientV2;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.index.HoodieIndex;
import org.apache.hudi.metrics.HoodieMetrics;
import org.apache.hudi.table.HoodieTable;

public class HoodieWriteRDDClient<T extends HoodieRecordPayload> implements
    HoodieWriteClientV2<HoodieWriteRDDInput<HoodieRecord<T>>, HoodieWriteRDDOutput> {


  private final transient HoodieMetrics metrics;
  private final transient HoodieIndex<T> index;
  protected final HoodieWriteConfig config;
  private final transient HoodieEngineRDDContext context;
  private static final String LOOKUP_STR = "lookup";

  public HoodieWriteRDDClient(HoodieEngineRDDContext context, HoodieWriteConfig config) {
    this.context = context;
    this.config = config;
    this.metrics = new HoodieMetrics(config, config.getTableName());
    this.index = HoodieIndex.createIndex(config, context.getRddContext());
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
    HoodieTable<T> table = HoodieTable.create(config, context.getRddContext());
    Timer.Context indexTimer = metrics.getIndexCtx();
    HoodieWriteRDDInput<HoodieRecord<T>> recordsWithLocation = getIndex().tagLocation(hoodieRecords, context.getRddContext(), table);
    metrics.updateIndexMetrics(LOOKUP_STR, metrics.getDurationInMs(indexTimer == null ? 0L : indexTimer.stop()));
    return context.filterUnknownLocations(recordsWithLocation);
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

  public HoodieIndex<T> getIndex() {
    return index;
  }


}
