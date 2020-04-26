package org.apache.hudi.common;

public interface HoodieEngineContext<INPUT extends HoodieWriteInput, OUTPUT extends HoodieWriteOutput> {

  INPUT filterUnknownLocations(INPUT taggedRecords);

}
