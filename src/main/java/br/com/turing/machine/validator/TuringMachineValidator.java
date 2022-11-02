package br.com.turing.machine.validator;

import br.com.turing.machine.domain.TuringMachine;

public class TuringMachineValidator {

    TuringMachine turingMachine;

    public TuringMachineValidator(TuringMachine turingMachine) {
        this.turingMachine = turingMachine;
    }

    public boolean isValid() {
        return turingMachine.isValidInitialState() && turingMachine.isValidWhiteSymbol() && turingMachine.isValidFinalState();
    }
}
