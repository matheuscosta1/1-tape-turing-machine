package br.com.turing.machine.service;

import br.com.turing.machine.ReadTuringMachineTransitions;
import br.com.turing.machine.domain.TuringMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReadTuringMachineServiceTest {

    @Test
    void shouldReadFile() throws Exception {
        ReadTuringMachineTransitions turingMachineService = new ReadTuringMachineTransitions();

        String inputFilePath = "classpath:entrada/maquina-1-0^n1^n.json";
        TuringMachine turingMachineRequest = turingMachineService.readFile(inputFilePath);
        Assertions.assertNotNull(turingMachineRequest);
    }
}