package com.poke.api.service;

import com.poke.api.dto.MoveElem;
import com.poke.api.dto.PokemonDTO;
import com.poke.api.dto.TypeElem;
import com.poke.api.model.Pokemon;
import com.poke.api.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PokemonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonService.class);

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public void fetchPokemon() {

        LOGGER.info("Fetching Pokemon - START");

        RestTemplate restTemplate = new RestTemplate();

        Pokemon pokemon;
        PokemonDTO pokemonDTO;
        for (int i = 1; i <= 2; i++) {
            pokemonDTO = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/"+i, PokemonDTO.class);

            pokemon = new Pokemon();
            pokemon.setName(pokemonDTO.getName());
            pokemon.setWeight(getRoundedDecimal(pokemonDTO.getWeight(), 3));
            pokemon.setHeight(getRoundedDecimal(pokemonDTO.getHeight(), 2));
            pokemon.setMoves(getMoves(pokemonDTO).toString());
            pokemon.setTypes(getTypes(pokemonDTO).toString());

            pokemonRepository.save(pokemon);

            LOGGER.info("Added pokemon - {}", pokemon);
        }

        LOGGER.info("Fetching Pokemon - FINISHED");
    }

    private List<String> getTypes(PokemonDTO pokemonDTO) {
        List<String> types = new ArrayList<>();
        for (TypeElem typeElem: pokemonDTO.getTypes()) {
            types.add(typeElem.getType().getName());
        }
        return types;
    }

    private List<String> getMoves(PokemonDTO pokemonDTO) {
        List<String> moves = new ArrayList<>();
        int moveCounter = 0;
        for (MoveElem moveElem: pokemonDTO.getMoves()) {
            if (moveCounter < 4) {
                moves.add(moveElem.getMove().getName());
                moveCounter++;
            } else {
                break;
            }
        }
        return moves;
    }

    private Double getRoundedDecimal(Double value, Integer places) {
        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    public List<Pokemon> getPokemons() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons;
    }

    public Pokemon getPokemon(Integer id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        return pokemon.get();
    }
}
