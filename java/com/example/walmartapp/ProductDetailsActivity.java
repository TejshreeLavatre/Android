package com.example.walmartapp;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private Context mContext;
    private static final String TAG = "ProductDetailsActivity";


    ArrayList<Products> productData;

    ArrayList<Products> productsD;
    LayoutInflater inflater;

    ViewPager viewpager;

    int currentProdPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = ProductDetailsActivity.this;
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("Bundle");
        ArrayList<Products> products = (ArrayList<Products>)args.
                getSerializable("ProductsArrayList");

        Log.d("ProductsArrayList", products.toString());

        currentProdPosition = intent.getIntExtra("productPosition", 0);
        Log.d("currentProdPosition", String.valueOf(currentProdPosition));

        productsD = products;
        productData = new ArrayList<Products>(productsD.subList
                (currentProdPosition, productsD.size()));


        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        viewpager = (ViewPager)findViewById(R.id.viewPager);

        viewpager.setAdapter(new MyPageAdapter());

    }

    class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            //Return total pages, here one for each data item
            return productData.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            //See if object from instantiateItem is related to the given view
            //required by API
            return arg0==(View)arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
            object=null;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View page = inflater.inflate(R.layout.content_product_details, null);
            //position = currentProdPosition;

            TextView productTitle = page.findViewById(R.id.productTitle);
            TextView productIdValue = page.findViewById(R.id.productIdValue);
            TextView productPriceValue = page.findViewById(R.id.productPriceValue);
            TextView productShortDescValue = page.findViewById(R.id.productShortDescValue);
            TextView productLongDescValue = page.findViewById(R.id.productLongDescValue);
            TextView productReviewRatingValue = page.findViewById(R.id.productReviewRatingValue);
            TextView productReviewCountValue = page.findViewById(R.id.productReviewCountValue);
            TextView productInStockValue = page.findViewById(R.id.productInStockValue);
            ImageView imageView = page.findViewById(R.id.imageView);


            String productName = productData.get(position).getProductName();
            String productId = productData.get(position).getProductId();
            String shortDescription = productData.get(position).getShortDescription();
            String longDescription = productData.get(position).getLongDescription();
            String price = productData.get(position).getPrice();
            String productImage = productData.get(position).getProductImage();
            Double reviewRating =  productData.get(position).getReviewRating();
            int reviewCount =  productData.get(position).getReviewCount();
            Boolean inStock = productData.get(position).getInStock();

            String shortDesc = Html.fromHtml(shortDescription).toString();
            String longDesc = Html.fromHtml(longDescription).toString();

            productTitle.setText(productName);
            productIdValue.setText(productId);
            productPriceValue.setText(price);
            productShortDescValue.setText(shortDesc);
            productLongDescValue.setText(longDesc);

            productReviewCountValue.setText(Integer.toString(reviewCount));
            productReviewRatingValue.setText(Double.toString(reviewRating));

            productInStockValue.setText(inStock.toString());

            //Download image
            String main_url = "https://mobile-tha-server.firebaseapp.com";
            Log.d(TAG,main_url+productImage);
            Picasso.with(mContext)
                    .load(main_url+productImage)
                    .resize(800, 800)
                    .into(imageView);

            Log.d("currentProdPosition >>", String.valueOf(currentProdPosition));
            //Add the page to the front of the queue
            ((ViewPager) container).addView(page, 0);
            return page;
        }
    }

}
