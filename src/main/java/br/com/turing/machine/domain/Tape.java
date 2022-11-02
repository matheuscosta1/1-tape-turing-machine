package br.com.turing.machine.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tape {

  private Symbol symbol;
  private Integer xAxis;
  private Integer yAxis;
  private Integer height;
  private Integer width;
  private Integer drawStringYAxis;

}
