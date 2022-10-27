package br.com.turing.machine.controller.request;

import br.com.turing.machine.domain.Alphabeth;
import br.com.turing.machine.domain.Simbol;
import br.com.turing.machine.domain.State;
import br.com.turing.machine.domain.Transition;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuringMachineRequest {

  private List<Transition> transicoes;
  private List<Simbol> simbolos;
  private List<Alphabeth> alfabeto;
  private String simboloBranco;
  private String estadoInicial;
  private List<State> estadosFinais;
  private List<State> estados;

}
