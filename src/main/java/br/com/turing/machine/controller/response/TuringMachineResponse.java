package br.com.turing.machine.controller.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuringMachineResponse {
  private String estado;
  private String fita;
}
