package br.com.turing.machine;

import br.com.turing.machine.domain.*;

import java.util.ArrayList;
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

    public ArrayList<Alphabet> newAlphabet() {
        ArrayList<Alphabet> arrayList = new ArrayList<>();
        arrayList.add(Alphabet.builder().character("0").build());
        return arrayList;
    }

    public ArrayList<State> newFinalStates() {
        ArrayList<State> arrayList = new ArrayList<>();
        arrayList.add(State.builder().name("q4").build());
        return arrayList;
    }

    public ArrayList<State> newStates() {
        ArrayList<State> arrayList = new ArrayList<>();
        arrayList.add(State.builder().name("q0").build());
        arrayList.add(State.builder().name("q1").build());
        arrayList.add(State.builder().name("q2").build());
        arrayList.add(State.builder().name("q3").build());
        arrayList.add(State.builder().name("q4").build());
        return arrayList;
    }


    public ArrayList<Symbol> newSymbols() {
        ArrayList<Symbol> arrayList = new ArrayList<>();
        arrayList.add(Symbol.builder().character("0").build());
        arrayList.add(Symbol.builder().character("1").build());
        arrayList.add(Symbol.builder().character("X").build());
        arrayList.add(Symbol.builder().character("Y").build());
        arrayList.add(Symbol.builder().character("B").build());
        return arrayList;
    }

    public List<Transition> newTransitions() {
        ArrayList<Transition> arrayList = new ArrayList<>();
        arrayList.add(Transition.builder().readSymbol("*").writeSymbol("*").direction(Direction.RIGHT).originState("q0").destinyState("q0").build());
        arrayList.add(Transition.builder().readSymbol("0").writeSymbol("X").direction(Direction.RIGHT).originState("q0").destinyState("q1").build());
        return arrayList;
    }
}
