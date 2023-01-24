package com.digitox.authapplicationv3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digitox.authapplicationv3.recyclerView.CustomAdapter2;
import com.digitox.authapplicationv3.recyclerView.CustomItemClickListener;
import com.digitox.authapplicationv3.recyclerView.RetrofitClient;
import com.digitox.authapplicationv3.recyclerView.RetrofitInterface;
import com.digitox.authapplicationv3.recyclerView.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerDataPassing extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private CustomAdapter2 customAdapter2;
    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_data_passing);

        recyclerView = findViewById(R.id.recycler_view2);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getUserListFromRestApi();

    }

    private void getUserListFromRestApi() {

        progressDialog = createProgressDialog(RecyclerDataPassing.this);

        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);


        Call<List<User>> call = retrofitInterface.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                progressDialog.dismiss();
                userList = new ArrayList<>(response.body());
                customAdapter2 = new CustomAdapter2(getApplicationContext(), userList, new CustomItemClickListener() {
                    @Override
                    public void onItemClick(User user, int position) {

                        Toast.makeText(getApplicationContext(),""+user.getId(), Toast.LENGTH_SHORT).show();
                        ArrayList<String> list = new ArrayList<>();
                        list.add(user.getId());
                        list.add(user.getUserId());
                        list.add(user.getTitle());
                        list.add(user.getBody());
                        Intent intent = new Intent(getApplicationContext(), DataCatch.class);
                        intent.putStringArrayListExtra("user", list);
                        startActivity(intent);

                    }
                });
                recyclerView.setAdapter(customAdapter2);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                progressDialog.dismiss();
//                DialogHelper.getAlertWithMessage("Error",t.getMessage(),RecyclerViewActivity.this);

            }
        });

    }


    public ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dialog.setContentView(R.layout.dialog_layout);
        return dialog;
    }



//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_item, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.menu_search);
//
//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                customAdapter.getFilter().filter(newText);
//                return true;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
}