package com.aptitude.application_test;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aptitude.application_test.adapters.CustomAdapter;
import com.aptitude.application_test.models.DataModel;
import com.aptitude.application_test.myinterfaces.GetDataCommits;
import com.aptitude.application_test.utils.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alert;
    GetDataCommits service;
    List<DataModel> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataCommits.class);
        recyclerView = findViewById(R.id.recycler);
        myList = new ArrayList<>();
        adapter = new CustomAdapter(this,myList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getData();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getData(){
        if (!isConnected(MainActivity.this)){
            noInternet();
            return;
        }
        progressDialogbox();
        Call<List<DataModel>> call = service.getAllCommits();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error getting Data, please try again", Toast.LENGTH_LONG).show();
                alert.dismiss();
            }
        });
    }

    private void generateDataList(List<DataModel> dataList) {
        myList.clear();
        myList.addAll(dataList);
        adapter.notifyDataSetChanged();
        alert.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            getData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void progressDialogbox(){
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View rootView = inflater.inflate(R.layout.box_dialog, null, false);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(rootView);
        alert = alertDialogBuilder.create();
        alert.show();
    }
    public void noInternet(){
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        alertDialogBuilder.setCancelable(false);
        alert = alertDialogBuilder.create();
        alert.setTitle("No Internet Connection");
        alert.setMessage("Kindly turn on your internet connection and refresh the page");
        alert.setButton(Dialog.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getData();
            }
        });
        alert.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing
            }
        });
        alert.show();
    }
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if ((activeNetwork != null && activeNetwork.isConnected())) {
            return true;
        } else {
            return false;
        }
    }
}
