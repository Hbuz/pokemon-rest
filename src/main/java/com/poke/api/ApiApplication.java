package com.poke.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

//    @Autowired
//    @Lazy
//    private PokemonService pokemonService;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);

        System.out.println("ciao");

    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
//
//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        return args -> {
//            PokemonDTO quote = restTemplate.getForObject(
//                    "https://pokeapi.co/api/v2/pokemon/1/", PokemonDTO.class);
////            System.out.println(quote.getName());
//            pokemonService.put(quote.getName());
//        };
//    }

}
