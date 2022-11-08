package br.com.turing.machine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transition implements Serializable  {

  @JsonProperty("leSimbolo")
  private String symbolRead;

  @JsonProperty("escreve")
  private String writeSymbol;

  @JsonProperty("direcao")
  private Direction direction;

  @JsonProperty("estadoOrigem")
  private String originState;

  @JsonProperty("estadoDestino")
  private String destinyState;

}
