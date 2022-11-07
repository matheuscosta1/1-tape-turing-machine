package br.com.turing.machine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class State implements Serializable {

  @JsonProperty("nome")
  private String name;

}
