package br.com.turing.machine.domain;

import br.com.turing.machine.BaseUtilsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class TuringMachineTest extends BaseUtilsTest {

    @Test
    void shouldBeValidInitialState(){
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertTrue(turingMachine.isValidInitialState());
    }

    @Test
    void shouldNotBeValidInitialState(){
        TuringMachine turingMachine = newTuringMachine();
        turingMachine.setInitialState("q5");
        Assertions.assertFalse(turingMachine.isValidInitialState());
    }

    @Test
    void shouldBeValidWhiteSymbol(){
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertTrue(turingMachine.isValidWhiteSymbol());
    }

    @Test
    void shouldNotBeValidWhiteSymbol(){
        TuringMachine turingMachine = newTuringMachine();
        turingMachine.setWhiteSymbol("C");
        Assertions.assertFalse(turingMachine.isValidWhiteSymbol());
    }

    @Test
    void shouldFindByActualStateAndReadSymbol() {
        TuringMachine turingMachine = newTuringMachine();
        Optional<Transition> transition = turingMachine.findTransitionByActualStateAndReadSymbol("q0", "0");
        transition.ifPresent(value -> Assertions.assertEquals("q1", value.getDestinyState()));
    }

}