package com.example.appbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.appbanhang.Adapter.LoaiSpAdapter;

import com.example.appbanhang.Adapter.SanPhamAdapter;
import com.example.appbanhang.Adapter.SanPhamMoiAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.model.LoaiSp;
import com.example.appbanhang.model.NetworkChangeListener;
import com.example.appbanhang.model.SanPham;
import com.example.appbanhang.model.SanPhamMoi;
import com.example.appbanhang.retrofit.ApiBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    GridView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    ImageView info;
    TextView tvhi;

    List<SanPhamMoi> mangSpMoi;
    SanPhamMoiAdapter spAdapter;

    List<SanPham> mangSp;
    SanPhamAdapter sanphamAdapter;



    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imgsearch;
    //check_connected
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    FloatingActionButton floatingActionButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        Anhxa();
        if (isConnected(this)){

            getLoaiSanPham();
            getSpMoi();
            getEventClick();
        }else{
            Toast.makeText(getApplicationContext(),"Không có internet, vui lòng kết nối", Toast.LENGTH_SHORT).show();
        }


    }

    private void getEventClick() {
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent beef = new Intent(getApplicationContext(), Combo.class);
                        beef.putExtra("loai", 2);
                        startActivity(beef);
                        break;
                    case 1:
                        Intent chicken = new Intent(getApplicationContext(), Food.class);
                        chicken.putExtra("loai", 1);
                        startActivity(chicken);
                        break;
                    case 2:
                        Intent dessert = new Intent(getApplicationContext(), Dessert.class);
                        dessert.putExtra("loai", 3);
                        startActivity(dessert);
                        break;
                    case 3:
                        Intent lamb = new Intent(getApplicationContext(), Drinks.class);
                        lamb.putExtra("loai", 4);
                        startActivity(lamb);
                        break;
                    case 4:
                        Intent quanli = new Intent(getApplicationContext(), QuanLiActivity.class);
                        startActivity(quanli);
                        break;



                }
            }
        });
    }



    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                mangSpMoi = sanPhamMoiModel.getResult();
                                spAdapter = new SanPhamMoiAdapter(getApplicationContext(), mangSpMoi);
                                recyclerViewManHinhChinh.setAdapter(spAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được sever"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                ));
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    loaiSpModel -> {
                        if (loaiSpModel.isSuccess()){
                            mangloaisp = loaiSpModel.getResult();
                            loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(),mangloaisp);
                            listViewManHinhChinh.setAdapter(loaiSpAdapter);

                        }
                    }
                ));

    }


    private void Anhxa() {
        imgsearch = findViewById(R.id.imgsearch);
        info = findViewById(R.id.info);

        recyclerViewManHinhChinh = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewManHinhChinh.setLayoutManager(layoutManager);
        recyclerViewManHinhChinh.setHasFixedSize(true);

        listViewManHinhChinh = findViewById(R.id.listviewmanhinhchinh);
        tvhi = findViewById(R.id.tvhi);


        drawerLayout = findViewById(R.id.drawerlayout);
        badge = findViewById(R.id.menu_sl);
        frameLayout =findViewById(R.id.framegiohang);
        mangloaisp = new ArrayList<>();
        mangSpMoi = new ArrayList<>();
        floatingActionButton = findViewById(R.id.float_giohang_home);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });

        if (Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }else{
            int totalItem = 0;
            for (int i =0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem+Utils.manggiohang.get(i).getSpluong();
            }
//            badge.setText(String.valueOf(totalItem));
        }



        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), XemDonActivity.class);
                startActivity(intent);
            }
        });

    }

        @Override
        protected void onResume() {
            super.onResume();
            int totalItem = 0;
            for (int i =0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem+Utils.manggiohang.get(i).getSpluong();
            }
//            badge.setText(String.valueOf(totalItem));
        }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null &&  wifi.isConnected()) ||(mobile != null && mobile.isConnected())){
            return true;
        }else{
            return  false;
        }

    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }


}