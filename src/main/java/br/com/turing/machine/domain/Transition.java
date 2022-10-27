package br.com.turing.machine.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transition {

  private String simbolo;
  private String escreve;
  private String direcao;
  private String estadoOrigem;
  private String estadoDestino;

}
