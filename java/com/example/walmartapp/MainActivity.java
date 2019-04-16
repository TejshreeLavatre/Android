package com.example.walmartapp;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    private ListView productsListView;
    String url="https://mobile-tha-server.firebaseapp.com/walmartproducts/1/30";
    private ArrayList<Products> productsArrayList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        //Initialize Widgets
        productsListView = findViewById(R.id.listView);
        RequestQueue requestQueue = Volley.newRequestQueue(mActivity);
        //Download and parse the JSON
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            productsArrayList = new ArrayList<>();
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("products");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product = array.getJSONObject(i);
                                //Log.d(TAG, "Product is -> " +product);
                                String productId = product.getString("productId");
                                String productName = product.getString("productName");
                                String shortDescription = product.has("shortDescription")
                                        ? product.getString("shortDescription") : "-";
                                String longDescription = product.has("longDescription")
                                        ? product.getString("longDescription") : "-";
                                String price = product.getString("price");
                                String productImage = product.getString("productImage");
                                Double reviewRating = product.getDouble("reviewRating");
                                Integer reviewCount = product.getInt("reviewCount");
                                Boolean inStock = product.getBoolean("inStock");

                                productsArrayList.add(new Products(productId, productName,
                                        shortDescription, longDescription, price, productImage,
                                        reviewRating, reviewCount, inStock));
                                ProductsAdapter productsAdapter = new ProductsAdapter
                                        (MainActivity.this, R.layout.custom_layout,
                                                productsArrayList);
                                productsListView.setAdapter(productsAdapter);
                                productsListView.setOnItemClickListener
                                        (new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent,
                                                            View view, int position, long id) {

                                        Intent intent = new Intent(MainActivity.this,
                                                ProductDetailsActivity.class);
                                        //intent.putExtra("listItem", position);
                                        intent.putExtra("productId",
                                                productsArrayList.get(position).getProductId());
                                        intent.putExtra("productName",
                                                productsArrayList.get(position).getProductName());
                                        intent.putExtra("shortDescription",
                                                productsArrayList.get(position).getShortDescription
                                                        ());
                                        intent.putExtra("longDescription",
                                                productsArrayList.get(position).
                                                        getLongDescription());
                                        intent.putExtra("price", productsArrayList.
                                                get(position).getPrice());
                                        intent.putExtra("productImage", productsArrayList.
                                                get(position).getProductImage());
                                        intent.putExtra("reviewRating", productsArrayList.
                                                get(position).getReviewRating());
                                        intent.putExtra("reviewCount", productsArrayList.
                                                get(position).getReviewCount());
                                        intent.putExtra("inStock", productsArrayList.
                                                get(position).getInStock());
                                        //Log.d("productPosition", String.valueOf(position));
                                        intent.putExtra("productPosition", position);

                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("ProductsArrayList",
                                                (Serializable)productsArrayList);
                                        intent.putExtra("Bundle", bundle);
                                        //intent.putExtra("productsArray",productsArrayList);
                                        startActivity(intent);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
