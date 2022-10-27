package br.com.turing.machine.controller;

import br.com.turing.machine.controller.request.TuringMachineRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.*;

@RestController
@Slf4j
@Validated
public class TuringMachineController {

  @Value("${turing-machine.resource}")
  private String fileResource;
  @Autowired ResourceLoader resourceLoader;

  @PostMapping("processor")
  public ResponseEntity<Void> registerCreditCard(
      @Valid @RequestBody TuringMachineRequest request) throws IOException, ClassNotFoundException {

    Resource resource = resourceLoader.getResource(fileResource);
    ObjectMapper objectMapper = new ObjectMapper();
    TuringMachineRequest example = objectMapper.readValue(new File(resource.getURI().getPath()), TuringMachineRequest.class);

    return ResponseEntity.ok().build();
  }

}
