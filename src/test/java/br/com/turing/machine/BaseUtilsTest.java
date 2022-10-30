package br.com.turing.machine;

import br.com.turing.machine.domain.*;

import java.util.List;

public class BaseUtilsTest {

    public TuringMachine newTuringMachine() {
        return TuringMachine
                .builder()
                .alphabet(newAlphabet())
                .initialState("q0")
                .whiteSymbol("B")
                .finalStates(newFinalStates())
                .states(newStates())
                .symbols(newSymbols())
                .transitions(newTransitions())
                .build();
    }

    public List<Alphabet> newAlphabet() {
        return List.of(Alphabet.builder().character("0").build());
    }

    public List<State> newFinalStates() {
        return List.of(State.builder().name("q4").build());
    }

    public List<State> newStates() {
        return List.of(
                State.builder().name("q0").build(),
                State.builder().name("q1").build(),
                State.builder().name("q2").build(),
                State.builder().name("q3").build(),
                State.builder().name("q4").build()
        );
    }


    public List<Symbol> newSymbols() {
        return List.of(
                Symbol.builder().character("0").build(),
                Symbol.builder().character("1").build(),
                Symbol.builder().character("X").build(),
                Symbol.builder().character("Y").build(),
                Symbol.builder().character("B").build()
        );
    }

    public List<Transition> newTransitions() {
        return List.of(
                Transition.builder().symbol("*").write("*").direction("RIGHT").originState("q0").destinyState("q0").build(),
                Transition.builder().symbol("0").write("X").direction("RIGHT").originState("q0").destinyState("q1").build()
        );
    }
}
