package com.example.walmartapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductsAdapter extends ArrayAdapter<Products> {

    private Context context;
    private ArrayList<Products> data;

    public ProductsAdapter(@NonNull Context context, int resource,
                           @NonNull ArrayList<Products> data)
    {
        super(context, resource , data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
       String TAG = "Adapter";
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout,parent,false);
            viewHolder.productName = convertView.findViewById(R.id.product_name);
            viewHolder.productPrice = convertView.findViewById(R.id.product_price);
            viewHolder.productImage = convertView.findViewById(R.id.product_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.productName.setText(data.get(position).getProductName());
        viewHolder.productPrice.setText(data.get(position).getPrice());

        String main_url = "https://mobile-tha-server.firebaseapp.com";
        Picasso.with(context)
                .load(main_url+data.get(position).getProductImage())
                .fit()
                .into(viewHolder.productImage);
        return convertView;
    }

    static class ViewHolder {
        TextView productName;
        TextView productPrice;
        CircleImageView productImage;
    }
}
