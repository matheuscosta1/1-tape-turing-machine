package br.com.turing.machine.validator;

import br.com.turing.machine.domain.TuringMachine;

import java.util.Arrays;

public class TuringMachineValidator {

    TuringMachine turingMachine;

    public TuringMachineValidator(TuringMachine turingMachine) {
        this.turingMachine = turingMachine;
    }

    public boolean isValidMachine() {
        return turingMachine.isValidInitialState()
                && turingMachine.isValidWhiteSymbol()
                && turingMachine.isValidFinalState()
                && turingMachine.validateTransitions();
    }

    public boolean isValidWord(String word) {

        if(word.equals("")) {
            return true;
        }

        return Arrays.stream(word.split("")).allMatch(character -> turingMachine.getAlphabet().stream().anyMatch(alphabet -> alphabet.getCharacter().equals(character)));
    }
}
