package com.poke.api;

import com.poke.api.dto.PokemonDTO;
import com.poke.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyRunner implements CommandLineRunner {

//    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private PokemonService pokemonService;

    @Override
    public void run(String... args) throws Exception {
//        RestTemplate restTemplate = new RestTemplate();
//
//        PokemonDTO quote = restTemplate.getForObject(
//                "https://pokeapi.co/api/v2/pokemon/1/", PokemonDTO.class);
////            System.out.println(quote.getName());
        pokemonService.fetchPokemon();
    }
}