package com.example.qlsach;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class BookDetailActivity extends Activity {
    private TextView tvTenSach, tvTacGiaTheLoai, tvTinhTrang, tvGhiChu, tvChiTietSoSao;
    private RatingBar rbChiTietDanhGia;
    private Button btnDanhDauDaDoc;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        tvTenSach = findViewById(R.id.tvTenSach);
        tvTacGiaTheLoai = findViewById(R.id.tvTacGiaTheLoai);
        tvTinhTrang = findViewById(R.id.tvTinhTrang);
        tvGhiChu = findViewById(R.id.tvGhiChu);
        tvChiTietSoSao = findViewById(R.id.tvChiTietSoSao);
        rbChiTietDanhGia = findViewById(R.id.rbChiTietDanhGia);
        btnDanhDauDaDoc = findViewById(R.id.btnDanhDauDaDoc);
        Button btnQuayLai = findViewById(R.id.btnQuayLai);
        Button btnXoaSach = findViewById(R.id.btnXoaSach);

        int viTriSach = getIntent().getIntExtra("viTriSach", -1);
        if (viTriSach < 0 || viTriSach >= BookRepository.danhSachSach.size()) {
            Toast.makeText(this, "Không tìm thấy sách", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        book = BookRepository.danhSachSach.get(viTriSach);
        hienThiThongTin();

        rbChiTietDanhGia.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            if (book != null && fromUser) {
                book.setDanhGia(rating);
                tvChiTietSoSao.setText(Math.round(rating) + "/5 sao");
            }
        });

        btnDanhDauDaDoc.setOnClickListener(v -> {
            if (book != null) {
                book.setTinhTrang("Đã đọc");
                capNhatTinhTrang();
                Toast.makeText(this, "Đã cập nhật tình trạng đọc",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnQuayLai.setOnClickListener(v -> finish());
        btnXoaSach.setOnClickListener(v -> xoaSach());
    }

    private void hienThiThongTin() {
        tvTenSach.setText(book.getTenSach());
        tvTacGiaTheLoai.setText("Tác giả: " + book.getTacGia()
                + "\nThể loại: " + book.getTheLoai());
        tvGhiChu.setText(book.getGhiChu().isEmpty()
                ? "Chưa có ghi chú" : book.getGhiChu());
        rbChiTietDanhGia.setRating(book.getDanhGia());
        tvChiTietSoSao.setText(Math.round(book.getDanhGia()) + "/5 sao");
        capNhatTinhTrang();
    }

    private void capNhatTinhTrang() {
        tvTinhTrang.setText("Tình trạng đọc: " + book.getTinhTrang());
        boolean daDoc = "Đã đọc".equals(book.getTinhTrang());
        btnDanhDauDaDoc.setEnabled(!daDoc);
        btnDanhDauDaDoc.setText(daDoc ? "Sách đã đọc" : "Đánh dấu đã đọc");
    }

    private void xoaSach() {
        if (book != null) {
            BookRepository.danhSachSach.remove(book);
            Toast.makeText(this, "Đã xóa sách", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
