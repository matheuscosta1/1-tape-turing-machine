package br.com.turing.machine.service;

import br.com.turing.machine.service.request.TuringMachineRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class TuringMachineService {

  @Value("${turing-machine.resource}")
  private String fileResource;
  @Autowired ResourceLoader resourceLoader;

  public TuringMachineService(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  public TuringMachineRequest readFile() throws IOException {

    Resource resource = resourceLoader.getResource(fileResource);
    ObjectMapper objectMapper = new ObjectMapper();
    TuringMachineRequest turingMachineRequest = objectMapper.readValue(new File(resource.getURI().getPath()), TuringMachineRequest.class);

    return turingMachineRequest;
  }

}
