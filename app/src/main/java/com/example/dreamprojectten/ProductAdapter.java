package com.example.dreamprojectten;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    List<Product> productList;




    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.priceTV.setText("৳"+product.getPrice());
        holder.nameTV.setText(product.getName());
        Picasso.get().load(product.getImage()).into(holder.imageView);



        holder.addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase.getInstance().getReference().child("CartInfo").child(product.getId()).setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(v.getContext(), "Product Added into Card", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase.getInstance().getReference().child("FavoriteInfo").child(product.getId()).setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(v.getContext(), "Product Added into Favorite", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });






    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTV,priceTV;
        ImageButton addCard,addFavorite;
        Map<String,Object> answer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            answer = new HashMap<>();
            imageView = itemView.findViewById(R.id.product2Image);
            nameTV = itemView.findViewById(R.id.produc2tName);
            priceTV = itemView.findViewById(R.id.productPrice);
            addCard = itemView.findViewById(R.id.productbutton);
            addFavorite = itemView.findViewById(R.id.favorite_productbutton);
        }
    }
}
