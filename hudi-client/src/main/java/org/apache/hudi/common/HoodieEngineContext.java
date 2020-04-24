package org.apache.hudi.common;

public interface HoodieEngineContext<INPUT extends HoodieWriteInput, OUTPUT extends HoodieWriteOutput> {

  OUTPUT filterUnknownLocations(INPUT taggedRecords);

}
