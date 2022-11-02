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

    @Test
    void shouldBeValidFinalStates() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertTrue(turingMachine.isValidFinalState());
    }

    @Test
    void shouldNotBeValidFinalStates() {
        TuringMachine turingMachine = newTuringMachine();
        turingMachine.getFinalStates().add(State.builder().name("q5").build());
        Assertions.assertFalse(turingMachine.isValidFinalState());
    }

    @Test
    void shouldBeValidOriginState() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertTrue(turingMachine.isValidOriginState(Transition.builder().originState("q0").build()));
    }

    @Test
    void shouldNotBeValidOriginState() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertFalse(turingMachine.isValidOriginState(Transition.builder().originState("q5").build()));
    }

    @Test
    void shouldBeValidDestinyState() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertTrue(turingMachine.isValidDestinyState(Transition.builder().destinyState("q0").build()));
    }

    @Test
    void shouldNotBeValidDestinyState() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertFalse(turingMachine.isValidDestinyState(Transition.builder().destinyState("q5").build()));
    }

    @Test
    void shouldBeValidReadSymbol() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertTrue(turingMachine.isValidSymbolRead(Transition.builder().symbolRead("0").build()));
    }

    @Test
    void shouldNotBeValidReadSymbol() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertFalse(turingMachine.isValidSymbolRead(Transition.builder().symbolRead("P").build()));
    }

    @Test
    void shouldBeValidWriteSymbol() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertTrue(turingMachine.isValidWriteSymbol(Transition.builder().writeSymbol("X").build()));
    }

    @Test
    void shouldBeValidWriteSymbolWhenStartMaker() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertTrue(turingMachine.isValidWriteSymbol(turingMachine.getTransitions().get(0)));
    }

    @Test
    void shouldBeValidWriteSymbolWhenStartMakerNotPresentAndHasNoTransactionWithStartMakerSymbol() {
        TuringMachine turingMachine = newTuringMachine();
        turingMachine.setStartMaker(null);
        turingMachine.getTransitions().remove(0);
        Assertions.assertTrue(turingMachine.isValidWriteSymbol(turingMachine.getTransitions().get(0)));
    }

    @Test
    void shouldNotBeValidWriteSymbol() {
        TuringMachine turingMachine = newTuringMachine();
        Assertions.assertFalse(turingMachine.isValidWriteSymbol(Transition.builder().writeSymbol("Z").build()));
    }

    @Test
    void shouldBeValidTransitions() {
        TuringMachine turingMachine = newTuringMachine();
        turingMachine.setStartMaker("*");
        Assertions.assertTrue(turingMachine.validateTransitions());
    }

    @Test
    void shouldNotBeValidTransitions() {
        TuringMachine turingMachine = newTuringMachine();
        turingMachine.setStartMaker(null);
        turingMachine.getTransitions().get(0).setSymbolRead("P");
        Assertions.assertFalse(turingMachine.validateTransitions());
    }

}