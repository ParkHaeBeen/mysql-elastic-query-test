package com.example.test.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkerInfoRequest {

  @Builder.Default
  private String name="";
  private Double lnt;

  private Double lat;
}
