package com.example.appbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.Adapter.SpinnerAdapter;
import com.example.appbanhang.R;
import com.example.appbanhang.model.ContryCodes;
import com.example.appbanhang.retrofit.ApiBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangkiActivity extends AppCompatActivity {
    EditText email, pass, mobile, username;
    ImageButton kich;
    Button button;
    private TextView errorphone, errorusername, errorpass, erroremail;
    private ImageButton btnback;
    private SpinnerAdapter spinnerAdapter;
    private Spinner listcontrycodes;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        initView();
        initControl();
        btnback = findViewById(R.id.btnBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(this,MainActivityLog.class);
//                startActivity(intent);
            }
        });

        spinnerAdapter = new SpinnerAdapter(this,R.layout.item_selected,getListCodes());
        listcontrycodes = findViewById(R.id.spnNumberRes);
        listcontrycodes.setAdapter(spinnerAdapter);
        listcontrycodes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ActivityRegister.this, spinnerAdapter.getItem(position).getNumber(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initControl() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangki();
                dang();
            }
        });


    }

    private void dang() {
        kich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void dangki() {
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_mobile = mobile.getText().toString().trim();
        String str_username = username.getText().toString().trim();
        boolean checkCorrect = true;
        TextView[] textViews = new TextView[]{
                errorpass, errorphone, errorusername, erroremail
        };
        for (TextView input : textViews) {
            if (input != null) {
                input.setText("");
            }
        }
        if (TextUtils.isEmpty(str_username)) {
            errorusername.setText("Vui lòng nhập tên người dùng");
            checkCorrect = false;
        }
        if (TextUtils.isEmpty(str_pass)) {
            errorpass.setText("Vui lòng nhập mật khẩu");
            checkCorrect = false;
        }
        if (TextUtils.isEmpty(str_mobile)) {
            errorphone.setText("Vui lòng nhập số điện thoại");
            checkCorrect = false;
        }
        if (TextUtils.isEmpty(str_email)) {
            erroremail.setText("Vui lòng nhập email");
            checkCorrect = false;
        }
        if(!str_email.matches("([a-zA-Z0-9]+)([\\.{1}])?([a-zA-Z0-9]+)\\@gmail([\\.])com")){
            erroremail.setText("Vui lòng nhập đúng email");
            checkCorrect = false;
        }
//        if (TextUtils.isEmpty(str_email)){
//            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
//        }
//        else if (TextUtils.isEmpty(str_pass)){
//            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Mật khẩu", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(str_repass)){
//            Toast.makeText(getApplicationContext(), "Bạn chưa nhập lại Mật khẩu", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(str_mobile)){
//            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điện thoại ", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(str_username)){
//            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Username", Toast.LENGTH_SHORT).show();
//        }else{
        // post data
        if (checkCorrect == true) {
            compositeDisposable.add(apiBanHang.dangKi(str_email, str_pass, str_username, str_mobile, "user")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            userModel -> {
                                if (userModel.isSuccess()) {
                                    Utils.user_current.setUsername(str_username);
                                    Utils.user_current.setPass(str_pass);
                                    Toast.makeText(getApplicationContext(), "Đăng kí thành công ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    ));
        }
    }

    private void initView() {
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        email = findViewById(R.id.email1);
        pass = findViewById(R.id.pass);
        mobile = findViewById(R.id.mobile);
        username = findViewById(R.id.username);
        button = findViewById(R.id.btndangki);
        errorphone = findViewById(R.id.errorPhone);
        errorusername = findViewById(R.id.errorUsername);
        errorpass = findViewById(R.id.errorPass);
        erroremail = findViewById(R.id.errorEmail);
        kich = findViewById(R.id.btnBack);
    }

    private List<ContryCodes> getListCodes(){
        List<ContryCodes> list = new ArrayList<>();
        list.add(new ContryCodes(R.drawable.ic_flag_of_vietnam,"+84"));
        list.add(new ContryCodes(R.drawable.ic_flag_of_the_united_states,"+44"));
        list.add(new ContryCodes(R.drawable.ic_flag_of_albania,"+355"));
        list.add(new ContryCodes(R.drawable.ic_flag_of_algeria,"+213"));
        list.add(new ContryCodes(R.drawable.ic_flag_of_argentina,"+54"));
        list.add(new ContryCodes(R.drawable.ic_flag_of_australia,"+61"));
        list.add(new ContryCodes(R.drawable.ic_flag_of_austria,"+43"));
        list.add(new ContryCodes(R.drawable.ic_flag_of_belgium,"+32"));
        //add flags and number contries codes here...

        return list;
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}