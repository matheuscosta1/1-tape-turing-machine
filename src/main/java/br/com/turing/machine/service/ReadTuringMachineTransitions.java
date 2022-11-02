package br.com.turing.machine.service;

import br.com.turing.machine.domain.TuringMachine;
import br.com.turing.machine.exception.TuringMachineException;
import br.com.turing.machine.validator.TuringMachineValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ReadTuringMachineTransitions {

    ResourceLoader resourceLoader = new DefaultResourceLoader();

    public TuringMachine readFile(String inputFilePath) throws Exception {

        Resource resource = resourceLoader.getResource(inputFilePath);
        ObjectMapper objectMapper = new ObjectMapper();
        TuringMachine turingMachine = objectMapper.readValue(new File(resource.getURI().getPath()), TuringMachine.class);

        TuringMachineValidator turingMachineValidator = new TuringMachineValidator(turingMachine);

        if(turingMachineValidator.isValid()) {
            return turingMachine;
        }

        throw new TuringMachineException("Turing machine is not valid.");
    }
}
