package br.com.turing.machine.service;

import br.com.turing.machine.domain.TuringMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

class ReadTuringMachineServiceTest {

    @Test
    void shouldReadFile() throws Exception {
        ReadTuringMachineTransitions turingMachineService = new ReadTuringMachineTransitions();

        String inputFilePath = "classpath:entrada/maquina-1.json";
        TuringMachine turingMachineRequest = turingMachineService.readFile(inputFilePath);
        Assertions.assertNotNull(turingMachineRequest);
    }
}