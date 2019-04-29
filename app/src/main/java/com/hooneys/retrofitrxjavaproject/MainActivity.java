package com.hooneys.retrofitrxjavaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hooneys.retrofitrxjavaproject.DO.Post;
import com.hooneys.retrofitrxjavaproject.ListPack.PostViewAdapter;
import com.hooneys.retrofitrxjavaproject.Retrofit.MyApi;
import com.hooneys.retrofitrxjavaproject.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private MyApi myApi;
    private RecyclerView recyclerView;
    private PostViewAdapter adapter;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void init() {
        compositeDisposable = new CompositeDisposable();

        Retrofit retrofit = RetrofitClient.getInstance();
        myApi = retrofit.create(MyApi.class);

        recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        adapter = new PostViewAdapter();
        recyclerView.setAdapter(adapter);

        fetchData();
    }

    private void fetchData() {
        compositeDisposable.add(myApi.getPosts().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Post>>() {
                @Override
                public void accept(List<Post> posts) throws Exception {
                    displayData(posts);
                }})
        );
    }

    private void displayData(List<Post> posts) {
        adapter.setList(posts);
    }
}
