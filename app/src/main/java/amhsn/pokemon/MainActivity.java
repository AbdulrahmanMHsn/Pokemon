package amhsn.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.List;

import amhsn.pokemon.adapter.PokemonAdapter;
import amhsn.pokemon.databinding.ActivityMainBinding;
import amhsn.pokemon.model.Pokemon;
import amhsn.pokemon.viewmodel.PokemonViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private PokemonViewModel pokemonViewModel;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        // create instance from PokemonAdapter
        pokemonAdapter = new PokemonAdapter(this);

        // recyclerView
        mainBinding.pokemonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.pokemonRecyclerView.setAdapter(pokemonAdapter);

        // create instance from PokemonViewModel
        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        pokemonViewModel.getPokemons();

        pokemonViewModel.getPokemonList().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
              pokemonAdapter.setList(pokemons);
            }
        });

    }
}