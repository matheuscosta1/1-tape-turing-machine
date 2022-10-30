package br.com.turing.machine.service;

import br.com.turing.machine.domain.TuringMachine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ReadTuringMachineTransitions {

    @Value("${turing-machine.resource.input}")
    private String fileResource;
    @Autowired
    ResourceLoader resourceLoader;

    public ReadTuringMachineTransitions(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public TuringMachine readFile() throws IOException {

        Resource resource = resourceLoader.getResource(fileResource);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(resource.getURI().getPath()), TuringMachine.class);
    }
}
