package amhsn.pokemon.repository;

import javax.inject.Inject;

import amhsn.pokemon.model.PokemonResponse;
import amhsn.pokemon.network.PokemonApiService;
import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private PokemonApiService pokemonApiService;

    @Inject
    public Repository(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    public Observable<PokemonResponse> getPokemons(){
        return pokemonApiService.getPokemonList();
    }
}
