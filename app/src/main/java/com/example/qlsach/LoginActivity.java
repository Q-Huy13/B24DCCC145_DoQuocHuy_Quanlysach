package com.example.qlsach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

public class LoginActivity extends Activity {
    private EditText edtTenDangNhap;
    private EditText edtMatKhau;
    private TextView tvThongBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        tvThongBao = findViewById(R.id.tvThongBao);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);

        btnDangNhap.setOnClickListener(v -> xuLyDangNhap());
    }

    private void xuLyDangNhap() {
        String tenDangNhap = edtTenDangNhap.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString();
        tvThongBao.setVisibility(View.GONE);

        if (tenDangNhap.isEmpty()) {
            edtTenDangNhap.setError("Vui lòng nhập tên đăng nhập");
            edtTenDangNhap.requestFocus();
            return;
        }
        if (matKhau.trim().isEmpty()) {
            edtMatKhau.setError("Vui lòng nhập mật khẩu");
            edtMatKhau.requestFocus();
            return;
        }

        if (tenDangNhap.equals("admin") && matKhau.equals("123456")) {
            BookRepository.khoiTaoDuLieuMau();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            tvThongBao.setText("Sai tên đăng nhập hoặc mật khẩu");
            tvThongBao.setVisibility(View.VISIBLE);
        }
    }
}
