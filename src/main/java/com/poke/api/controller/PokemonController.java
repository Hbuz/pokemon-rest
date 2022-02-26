package com.poke.api.controller;

import com.poke.api.model.Pokemon;
import com.poke.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("")
    public ResponseEntity<List<Pokemon>> getPokemons() {

        List<Pokemon> pokemons = pokemonService.getPokemons();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable("id") Integer id) {

        Pokemon pokemon = pokemonService.getPokemon(id);
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }
}
