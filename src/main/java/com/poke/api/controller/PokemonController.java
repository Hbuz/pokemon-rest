package com.poke.api.controller;

import com.poke.api.model.Pokemon;
import com.poke.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pokemon>> getPokemons() {

        List<Pokemon> pokemons = pokemonService.getPokemons();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> getPokemonsPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        List<Pokemon> pokemons = pokemonService.getPokemonsPage(page, size);
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable("id") Integer id) {

        Pokemon pokemon = pokemonService.getPokemon(id);
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }
}
