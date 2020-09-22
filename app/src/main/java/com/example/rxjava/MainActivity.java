package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtHi)
    TextView txtHi;
    @BindView(R.id.myRecyclerView)
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RVCustomAdapter rvCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this);
        rvCustomAdapter = new RVCustomAdapter();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvCustomAdapter);

        Observable.just("How are you?").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                txtHi.setText(s);
            }
        });
//        Observable.just("Utkarsh", "Pranshu", "Deepak", "Swarnika", "Akanksha", "SnehLata", "Dhirendra" ).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                rvCustomAdapter.addStringToList(s);
//            }
//        });

        Entry entry1 = new Entry("PS4", BigDecimal.valueOf(1500), new Date());  //new date gives current date...
        Entry entry2 = new Entry("Xbox One", BigDecimal.valueOf(2000), new Date());
        Entry entry3 = new Entry("Xbox One S", BigDecimal.valueOf(2500), new Date());
        Entry entry4 = new Entry("Xbox One X", BigDecimal.valueOf(3000), new Date());

        Observable.just(entry1, entry2, entry3, entry4).subscribe(new Consumer<Entry>() {
            @Override
            public void accept(Entry entry) throws Throwable {
                rvCustomAdapter.addEntry(entry);
            }
        });
    }
}