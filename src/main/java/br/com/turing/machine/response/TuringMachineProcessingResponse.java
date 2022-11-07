package br.com.turing.machine.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuringMachineProcessingResponse implements Serializable {

  private String estadoAtual;
  private String leSimbolo;
  private String escreveSimbolo;
  private String direcao;
  private String estadoDestino;

}
