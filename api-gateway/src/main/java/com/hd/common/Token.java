package com.hd.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {
    private String appName;
    private String appKey;
    private String accessToken;
    private Integer maxAccessTime;
    private Long createTimestamp;
    private boolean  expired;

}