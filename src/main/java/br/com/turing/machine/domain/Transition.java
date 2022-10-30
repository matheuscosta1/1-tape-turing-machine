package br.com.turing.machine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transition {

  @JsonProperty("simbolo")
  private String symbol;

  @JsonProperty("escreve")
  private String write;

  @JsonProperty("direcao")
  private String direction;

  @JsonProperty("estadoOrigem")
  private String originState;

  @JsonProperty("estadoDestino")
  private String destinyState;

}
