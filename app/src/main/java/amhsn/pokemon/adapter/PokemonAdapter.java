package amhsn.pokemon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import amhsn.pokemon.R;
import amhsn.pokemon.databinding.PokemonItemBinding;
import amhsn.pokemon.model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private List<Pokemon> mList = new ArrayList<>();
    private Context mContext;

    public PokemonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pokemon_item, parent, false);
        return new PokemonViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.itemBinding.pokemonNameTextView.setText(mList.get(position).getName());

        Glide.with(mContext).load(mList.get(position).getUrl())
                .into(holder.itemBinding.pokemonImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<Pokemon> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private PokemonItemBinding itemBinding;

        public PokemonViewHolder(@NonNull PokemonItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}