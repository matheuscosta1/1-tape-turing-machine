package br.com.turing.machine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuringMachine {

  @JsonProperty("transicoes")
  private List<Transition> transitions;
  @JsonProperty("simbolos")

  private List<Symbol> symbols;

  @JsonProperty("alfabeto")
  private List<Alphabet> alphabet;

  @JsonProperty("simboloBranco")
  private String whiteSymbol;

  @JsonProperty("estadoInicial")
  private String initialState;

  @JsonProperty("estadosFinais")
  private List<State> finalStates;

  @JsonProperty("estados")
  private List<State> states;

  public boolean isValidWhiteSymbol() {
    return symbols.stream().anyMatch(symbol -> symbol.getCharacter().equals(whiteSymbol));
  }

  public boolean isValidInitialState() {
    return states.stream().anyMatch(state -> state.getName().equals(initialState));
  }

  public Optional<Transition> findTransitionByActualStateAndReadSymbol(String actualState, String readSymbol) {
    return transitions.stream().filter(transition -> transition.getOriginState().equals(actualState) && transition.getSymbol().equals(readSymbol)).findFirst();
  }

}
