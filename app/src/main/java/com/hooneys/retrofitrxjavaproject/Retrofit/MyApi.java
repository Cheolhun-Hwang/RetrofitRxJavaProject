package com.hooneys.retrofitrxjavaproject.Retrofit;

import com.hooneys.retrofitrxjavaproject.DO.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyApi {
    @GET("posts")                       //https://jsonplaceholder.typicode.com/posts
    Observable<List<Post>> getPosts();
}
