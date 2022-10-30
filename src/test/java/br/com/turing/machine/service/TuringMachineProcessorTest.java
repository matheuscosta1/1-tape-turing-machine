package br.com.turing.machine.service;

import br.com.turing.machine.BaseUtilsTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TuringMachineProcessorTest extends BaseUtilsTest {

    @Autowired TuringMachineProcessor turingMachineProcessor;

    @Test
    void shouldProcessorMachine() {
        turingMachineProcessor.run(newTuringMachine());
    }
}