package br.com.turing.machine;

import br.com.turing.machine.domain.Direction;
import br.com.turing.machine.domain.Transition;
import br.com.turing.machine.domain.TuringMachine;
import br.com.turing.machine.exception.TuringMachineException;
import br.com.turing.machine.validator.TuringMachineValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ReadTransitionsFromFile {

    ResourceLoader resourceLoader = new DefaultResourceLoader();

    public List<Transition> readFile(String inputFilePath) throws Exception {
        List<Transition> transitions = new ArrayList<>();
        Resource resource = resourceLoader.getResource(inputFilePath);

        try {

            File myObj = new File(resource.getURI().getPath());
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String symbolReadAndOriginState = data.split("=")[0];
                String destinyStateWriteSymbolAndDirection = data.split("=")[1];

                transitions.add(Transition.builder()
                        .originState(symbolReadAndOriginState.split(",")[0])
                        .symbolRead(symbolReadAndOriginState.split(",")[1])
                        .destinyState(destinyStateWriteSymbolAndDirection.split(",")[0])
                        .writeSymbol(destinyStateWriteSymbolAndDirection.split(",")[1])
                        .direction(Direction.valueOf(destinyStateWriteSymbolAndDirection.split(",")[2]))
                        .build());
            }

            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transitions);

            System.out.println(json);

            myReader.close();
            return transitions;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }
}
