package amhsn.pokemon.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import amhsn.pokemon.model.Pokemon;
import amhsn.pokemon.model.PokemonResponse;
import amhsn.pokemon.repository.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private static final String TAG = "PokemonViewModel";
    private Repository repository;
    private MutableLiveData<List<Pokemon>> pokemonList = new MutableLiveData<>();

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    @SuppressLint("CheckResult")
    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, List<Pokemon>>() {
                    @Override
                    public List<Pokemon> apply(PokemonResponse pokemonResponse) throws Throwable {
                        List<Pokemon> responseList = pokemonResponse.getResultPokemonList();
                        for (Pokemon pokemon : responseList) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return responseList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result), error -> Log.e(TAG, "error: " + error.getMessage()));
    }
}
