package com.poke.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonDTO {

    private String name;

    private Double weight;

    private Double height;

    private List<MoveElemDTO> moves;

    private List<TypeElemDTO> types;
}
