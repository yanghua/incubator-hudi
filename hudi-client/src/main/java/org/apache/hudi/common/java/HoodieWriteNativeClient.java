package org.apache.hudi.common.java;

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

public class HoodieWriteNativeClient<T extends HoodieRecordPayload> implements
        HoodieWriteClientV2<HoodieWriteNativeInput<HoodieRecord<T>>, HoodieWriteNativeOutput> {

    private final transient HoodieMetrics metrics;
    private final transient HoodieIndexV2<HoodieWriteInput, HoodieWriteNativeInput> index;
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
    public HoodieWriteNativeOutput upsert(HoodieWriteNativeInput<HoodieRecord<T>> hoodieRecords, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteNativeOutput upsertPreppedRecords(HoodieWriteNativeInput<HoodieRecord<T>> preppedRecords, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteNativeInput<HoodieRecord<T>> filterExists(HoodieWriteNativeInput<HoodieRecord<T>> hoodieRecords) {
        // Create a Hoodie table which encapsulated the commits and files visible
        HoodieTableV2<T> table = HoodieTableV2.create(config, context);
        Timer.Context indexTimer = metrics.getIndexCtx();
        HoodieWriteNativeInput recordsWithLocation = getIndex().tagLocation(hoodieRecords, context, table);
        metrics.updateIndexMetrics(LOOKUP_STR, metrics.getDurationInMs(indexTimer == null ? 0L : indexTimer.stop()));

        return new HoodieWriteNativeInput<>(context.filterUnknownLocations(recordsWithLocation).getInputs());
    }

    @Override
    public HoodieWriteNativeOutput insert(HoodieWriteNativeInput<HoodieRecord<T>> records, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteNativeOutput insertPreppedRecords(HoodieWriteNativeInput<HoodieRecord<T>> preppedRecords, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteNativeOutput bulkInsert(HoodieWriteNativeInput<HoodieRecord<T>> records, String instantTime) {
        return null;
    }

    @Override
    public HoodieWriteNativeOutput delete(HoodieWriteNativeInput<HoodieRecord<T>> keys, String instantTime) {
        return null;
    }

    @Override
    public HoodieEngineContext<HoodieWriteNativeInput<HoodieRecord<T>>, HoodieWriteNativeOutput> getEngineContext() {
        return context;
    }

    public HoodieIndexV2<HoodieWriteInput, HoodieWriteNativeInput> getIndex() {
        return index;
    }
}
