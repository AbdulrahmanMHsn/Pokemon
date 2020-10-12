package amhsn.pokemon.model;


import java.util.List;

public class PokemonResponse {
    private int count;
    private String next,previous;
    private List<Pokemon> results;

    public PokemonResponse(int count, String next, String previous, List<Pokemon> resultPokemonList) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = resultPokemonList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Pokemon> getResultPokemonList() {
        return results;
    }

    public void setResultPokemonList(List<Pokemon> resultPokemonList) {
        this.results = resultPokemonList;
    }
}
