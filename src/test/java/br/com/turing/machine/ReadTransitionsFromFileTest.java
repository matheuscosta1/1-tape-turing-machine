package br.com.turing.machine;

import br.com.turing.machine.domain.Transition;
import br.com.turing.machine.domain.TuringMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadTransitionsFromFileTest {
    @Test
    void shouldReadFile() throws Exception {
        ReadTransitionsFromFile transitions = new ReadTransitionsFromFile();

        String inputFilePath = "classpath:transicoes/transicoes-numero-igual-0s-1s";
        List<Transition> transitions1 = transitions.readFile(inputFilePath);
        assertNotNull(transitions1);
    }
}