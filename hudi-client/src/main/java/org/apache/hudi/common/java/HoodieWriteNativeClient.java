package org.apache.hudi.common.java;

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

public class HoodieWriteNativeClient<T extends HoodieRecordPayload> implements
        HoodieWriteClientV2<
            HoodieWriteInput<Iterable<HoodieRecord<T>>>,
            HoodieWriteKey<Iterable<HoodieKey>>,
            HoodieWriteOutput<Iterable<WriteStatus>>> {

    private final transient HoodieMetrics metrics;
    private final transient HoodieIndexV2<HoodieWriteInput, HoodieWriteInput<Iterable<HoodieRecord<T>>>> index;
    protected final HoodieWriteConfig config;
    private final transient HoodieEngineNativeContext context;
    private static final String LOOKUP_STR = "lookup";

    public HoodieWriteNativeClient(HoodieEngineNativeContext context, HoodieWriteConfig config) {
        this.context = context;
        this.config = config;
        this.metrics = new HoodieMetrics(config, config.getTableName());
        this.index = HoodieIndexV2Factory.createHoodieIndex(config, context);
    }

    @Override
    public HoodieWriteOutput<Iterable<WriteStatus>> upsert(
        HoodieWriteInput<Iterable<HoodieRecord<T>>> hoodieRecords, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteOutput<Iterable<WriteStatus>> upsertPreppedRecords(
        HoodieWriteInput<Iterable<HoodieRecord<T>>> preppedRecords, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteInput<Iterable<HoodieRecord<T>>> filterExists(
        HoodieWriteInput<Iterable<HoodieRecord<T>>> hoodieRecords) {
        // Create a Hoodie table which encapsulated the commits and files visible
        HoodieTableV2<T> table = HoodieTableV2.create(config, context);
        Timer.Context indexTimer = metrics.getIndexCtx();
        HoodieWriteInput<Iterable<HoodieRecord<T>>> recordsWithLocation = getIndex().tagLocation(hoodieRecords, context, table);
        metrics.updateIndexMetrics(LOOKUP_STR, metrics.getDurationInMs(indexTimer == null ? 0L : indexTimer.stop()));

        return context.filterUnknownLocations(recordsWithLocation);
    }

    @Override
    public HoodieWriteOutput<Iterable<WriteStatus>> insert(
        HoodieWriteInput<Iterable<HoodieRecord<T>>> records, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteOutput<Iterable<WriteStatus>> insertPreppedRecords(
        HoodieWriteInput<Iterable<HoodieRecord<T>>> preppedRecords, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteOutput<Iterable<WriteStatus>> bulkInsert(
        HoodieWriteInput<Iterable<HoodieRecord<T>>> records, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteOutput<Iterable<WriteStatus>> delete(
        HoodieWriteKey<Iterable<HoodieKey>> keys, String instantTime) {
        return null;
    }

    @Override
    public HoodieEngineContext<HoodieWriteInput<Iterable<HoodieRecord<T>>>, HoodieWriteOutput<Iterable<WriteStatus>>> getEngineContext() {
        return null;
    }

    public HoodieIndexV2<HoodieWriteInput, HoodieWriteInput<Iterable<HoodieRecord<T>>>> getIndex() {
        return index;
    }
}
