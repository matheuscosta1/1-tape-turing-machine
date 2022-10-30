package br.com.turing.machine.service;

import br.com.turing.machine.domain.TuringMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class TuringMachineServiceTest {

    @Autowired
    ReadTuringMachineTransitions turingMachineService;

    @Test
    void shouldReadFile() throws IOException {
        TuringMachine turingMachineRequest = turingMachineService.readFile();
        Assertions.assertNotNull(turingMachineRequest);
    }
}