
package com.example.kashish.posts.photoref;



import com.example.kashish.posts.photoref.Northeast;
import com.example.kashish.posts.photoref.Southwest;
import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Viewport {

    @Expose
    private Northeast northeast;
    @Expose
    private Southwest southwest;

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

}
