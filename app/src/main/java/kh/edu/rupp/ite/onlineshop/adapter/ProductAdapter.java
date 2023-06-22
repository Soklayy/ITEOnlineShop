package kh.edu.rupp.ite.onlineshop.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.Product;
import kh.edu.rupp.ite.onlineshop.databinding.ProductListItemBinding;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder>
{

    public ProductAdapter() {
        super(new DiffUtil.ItemCallback<Product>() {
            @Override
            public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return newItem.getId() == oldItem.getId();
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProductListItemBinding binding = ProductListItemBinding.inflate(inflater,parent,false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            Product productItem = getItem(position);
            holder.bind(productItem);
    }

    // view holder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private ProductListItemBinding binding;
        public ProductViewHolder(ProductListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(Product product) {

            Picasso.get().load(product.getImageUrl()).into(binding.productImage);
            binding.productName.setText(product.getName());
            binding.productPrice.setText("$"+Integer.toString(product.getPrice()));
            binding.productRate.setText(Float.toString(product.getRating()));
        }
    }
}
