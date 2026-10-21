package com.example.qlsach;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class AddBookActivity extends Activity {
    private EditText edtTenSach, edtTacGia, edtGhiChu;
    private Spinner spTheLoai;
    private RadioGroup rgTinhTrang;
    private RatingBar rbDanhGia;
    private TextView tvSoSao;

    private final String[] cacTheLoai = {
            "Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtTenSach = findViewById(R.id.edtTenSach);
        edtTacGia = findViewById(R.id.edtTacGia);
        edtGhiChu = findViewById(R.id.edtGhiChu);
        spTheLoai = findViewById(R.id.spTheLoai);
        rgTinhTrang = findViewById(R.id.rgTinhTrang);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        tvSoSao = findViewById(R.id.tvSoSao);
        Button btnLuuSach = findViewById(R.id.btnLuuSach);

        ArrayAdapter<String> theLoaiAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, cacTheLoai);
        theLoaiAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spTheLoai.setAdapter(theLoaiAdapter);

        rbDanhGia.setOnRatingBarChangeListener((bar, rating, fromUser) ->
                tvSoSao.setText(Math.round(rating) + "/5 sao"));

        btnLuuSach.setOnClickListener(v -> luuSach());
    }

    private void luuSach() {
        String tenSach = edtTenSach.getText().toString().trim();
        String tacGia = edtTacGia.getText().toString().trim();
        String ghiChu = edtGhiChu.getText().toString().trim();

        if (tenSach.isEmpty()) {
            edtTenSach.setError("Vui lòng nhập tên sách");
            edtTenSach.requestFocus();
            return;
        }
        if (tacGia.isEmpty()) {
            edtTacGia.setError("Vui lòng nhập tác giả");
            edtTacGia.requestFocus();
            return;
        }

        String tinhTrang;
        int id = rgTinhTrang.getCheckedRadioButtonId();
        if (id == R.id.rbDangDoc) tinhTrang = "Đang đọc";
        else if (id == R.id.rbDaDoc) tinhTrang = "Đã đọc";
        else tinhTrang = "Chưa đọc";

        Book book = new Book(
                tenSach, tacGia, spTheLoai.getSelectedItem().toString(),
                tinhTrang, rbDanhGia.getRating(), ghiChu);
        BookRepository.danhSachSach.add(book);

        Toast.makeText(this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
        finish();
    }
}
