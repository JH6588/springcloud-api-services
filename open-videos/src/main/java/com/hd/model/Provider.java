package com.hd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Provider {
    private String provider;
    private List<Map<String, String>> episodes;


}
