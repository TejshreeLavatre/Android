package com.example.walmartapp;

import java.io.Serializable;

public class Products implements Serializable {

    /*
    * SAMPLE JSON

    *  "products":
    [
       {"productId":"003e3e6a-3f84-43ac-8ef3-a5ae2db0f80e",
        "productName":"Ellerton TV Console",
        "shortDescription":"<p><span style=\"color:#FF0000;\"><b>White Glove Delivery Included</b></span></p>\n\n<ul>\n\t<li>Excellent for the gamer, movie enthusiest, or interior decorator in your home</li>\n\t<li>Built-in power strip for electronics</li>\n\t<li>Hardwood solids and cherry veneers</li>\n</ul>\n",
        "longDescription":"<p>The Ellerton media console is well-suited for today&rsquo;s casual lifestyle. Its elegant style and expert construction will make it a centerpiece in any home. Soundly constructed, the Ellerton uses hardwood solids &amp; cherry veneers elegantly finished in a rich dark cherry finish. &nbsp;With ample storage for electronics &amp; media, it also cleverly allows for customization with three choices of interchangeable door panels.</p>\n",
        "price":"$949.00",
        "productImage":"/images/image2.jpeg",
        "reviewRating":2,
        "reviewCount":1,
        "inStock":true
       }
    ]
     */

    private String productId;
    private String productName;
    private String shortDescription;
    private String longDescription;
    private String price;
    private String productImage;
    private Double reviewRating;
    private Integer reviewCount;
    private Boolean inStock;

    public Products(String productId, String productName, String shortDescription,
                    String longDescription, String price, String productImage,
                    Double reviewRating, Integer reviewCount, Boolean inStock)
    {
        this.productId = productId;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        this.productImage = productImage;
        this.reviewRating = reviewRating;
        this.reviewCount = reviewCount;
        this.inStock = inStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getPrice() {
        return price;
    }

    public String getProductImage() {
        return productImage;
    }

    public Double getReviewRating() {
        return reviewRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public Boolean getInStock() {
        return inStock;
    }
}
