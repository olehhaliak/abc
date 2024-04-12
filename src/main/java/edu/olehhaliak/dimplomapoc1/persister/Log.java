package edu.olehhaliak.dimplomapoc1.persister;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Log {
    Integer partition;
    Integer offset;
    Long unixTimestamp;
    String key;
    String value;
}
