package com.poke.api.service;

import com.poke.api.dto.MoveElemDTO;
import com.poke.api.dto.PokemonDTO;
import com.poke.api.dto.TypeElemDTO;
import com.poke.api.handler.RestTemplateResponseErrorHandler;
import com.poke.api.model.Pokemon;
import com.poke.api.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    private RestTemplate restTemplate;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, RestTemplateBuilder restTemplateBuilder) {
        this.pokemonRepository = pokemonRepository;
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Value("${poke.api.url}")
    private String pokeApiUrl;

    @Value("${poke.api.size}")
    private Integer listSize;

    public void fetchPokemon() {

        LOGGER.debug("Fetching Pokemon - START");

        Pokemon pokemon;
        PokemonDTO pokemonDTO;
        for (int i = 1; i <= listSize; i++) {

            pokemonDTO = restTemplate.getForObject(pokeApiUrl + i, PokemonDTO.class);

            pokemon = new Pokemon();
            pokemon.setName(StringUtils.capitalize(pokemonDTO.getName()));
            pokemon.setWeight(getRoundedDecimal(pokemonDTO.getWeight(), 3));
            pokemon.setHeight(getRoundedDecimal(pokemonDTO.getHeight(), 2));
            pokemon.setMoves(getMoves(pokemonDTO).toString());
            pokemon.setTypes(getTypes(pokemonDTO).toString());

            try {
                pokemonRepository.save(pokemon);
                LOGGER.debug("Added pokemon - {}", pokemon);

            } catch (Exception e) {
                LOGGER.error("There was an error storing on DB the pokemon:{}. Message:{}", pokemon, e.getMessage());
            }
        }

        LOGGER.debug("Fetching Pokemon - FINISHED");
    }

    private List<String> getTypes(PokemonDTO pokemonDTO) {
        List<String> types = new ArrayList<>();

        for (TypeElemDTO typeElemDTO : pokemonDTO.getTypes()) {
            types.add(typeElemDTO.getType().getName());
        }
        return types;
    }

    private List<String> getMoves(PokemonDTO pokemonDTO) {
        List<String> moves = new ArrayList<>();

        int moveCounter = 0;
        for (MoveElemDTO moveElemDTO : pokemonDTO.getMoves()) {
            if (moveCounter < 4) {
                moves.add(moveElemDTO.getMove().getName());
                moveCounter++;
            } else {
                break;
            }
        }
        return moves;
    }

    private Double getRoundedDecimal(Double value, Integer places) {
        return new BigDecimal(value)
                .setScale(places, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public List<Pokemon> getPokemons() {
        return pokemonRepository.findAll();
    }

    public List<Pokemon> getPokemonsPage(Integer page, Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Pokemon> pagedResult = pokemonRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        return new ArrayList<>();
    }

    public Pokemon getPokemon(Integer id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        if (pokemon.isPresent()) {
            return pokemon.get();
        }
        return null;
    }
}
