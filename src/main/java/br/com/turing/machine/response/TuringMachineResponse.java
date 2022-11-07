package br.com.turing.machine.response;

import br.com.turing.machine.domain.TuringMachine;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuringMachineResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private TuringMachine maquinaTuring;
  private List<TuringMachineProcessingResponse> processamento = new ArrayList<>();
  private String status;


}
