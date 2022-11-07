package br.com.turing.machine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alphabet implements Serializable {

  @JsonProperty("caracter")
  private String character;
}
