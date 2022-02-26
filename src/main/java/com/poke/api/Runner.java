package com.poke.api;

import com.poke.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final PokemonService pokemonService;

    @Autowired
    public Runner(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Override
    public void run(String... args) {
        pokemonService.fetchPokemon();
    }
}