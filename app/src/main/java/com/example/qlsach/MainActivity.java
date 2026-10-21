package com.example.qlsach;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private TextView tvThongKe;
    private ArrayAdapter<String> adapter;

    private final ArrayList<String> noiDungHienThi = new ArrayList<>();

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvThongKe = findViewById(R.id.tvThongKe);

        ListView lvDanhSachSach = findViewById(R.id.lvDanhSachSach);
        Button btnThemSach = findViewById(R.id.btnThemSach);

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, noiDungHienThi) {

            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {

                LinearLayout card = new LinearLayout(MainActivity.this);
                card.setOrientation(LinearLayout.VERTICAL);
                card.setGravity(Gravity.CENTER_VERTICAL);
                card.setPadding(dp(16), dp(14), dp(16), dp(14));

                GradientDrawable nen = new GradientDrawable();
                nen.setColor(Color.WHITE);
                nen.setCornerRadius(dp(18));
                nen.setStroke(dp(1), Color.rgb(227, 232, 242));
                card.setBackground(nen);
                card.setElevation(dp(2));

                Book book = BookRepository.danhSachSach.get(position);

                TextView tvTen = new TextView(MainActivity.this);
                tvTen.setText(book.getTenSach());
                tvTen.setTextSize(17);
                tvTen.setTypeface(null, Typeface.BOLD);
                tvTen.setTextColor(Color.rgb(23, 33, 58));
                card.addView(tvTen);

                TextView tvTacGia = new TextView(MainActivity.this);
                tvTacGia.setText("Tác giả: " + book.getTacGia());
                tvTacGia.setTextSize(14);
                tvTacGia.setTextColor(Color.rgb(105, 115, 139));
                LinearLayout.LayoutParams tacGiaParams =
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                tacGiaParams.topMargin = dp(7);
                card.addView(tvTacGia, tacGiaParams);

                TextView tvTheLoai = new TextView(MainActivity.this);
                tvTheLoai.setText("Thể loại: " + book.getTheLoai());
                tvTheLoai.setTextSize(14);
                tvTheLoai.setTextColor(Color.rgb(105, 115, 139));
                LinearLayout.LayoutParams theLoaiParams =
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                theLoaiParams.topMargin = dp(3);
                card.addView(tvTheLoai, theLoaiParams);

                TextView tvTrangThai = new TextView(MainActivity.this);
                tvTrangThai.setText(book.getTinhTrang());
                tvTrangThai.setTextSize(12);
                tvTrangThai.setTypeface(null, Typeface.BOLD);
                tvTrangThai.setPadding(dp(10), dp(5), dp(10), dp(5));

                GradientDrawable nenTrangThai = new GradientDrawable();
                nenTrangThai.setCornerRadius(dp(20));

                if ("Đã đọc".equals(book.getTinhTrang())) {
                    nenTrangThai.setColor(Color.rgb(227, 246, 238));
                    tvTrangThai.setTextColor(Color.rgb(22, 134, 106));
                } else if ("Đang đọc".equals(book.getTinhTrang())) {
                    nenTrangThai.setColor(Color.rgb(255, 243, 218));
                    tvTrangThai.setTextColor(Color.rgb(156, 101, 8));
                } else {
                    nenTrangThai.setColor(Color.rgb(232, 238, 255));
                    tvTrangThai.setTextColor(Color.rgb(36, 71, 168));
                }

                tvTrangThai.setBackground(nenTrangThai);

                LinearLayout.LayoutParams trangThaiParams =
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                trangThaiParams.topMargin = dp(10);
                card.addView(tvTrangThai, trangThaiParams);

                TextView tvSao = new TextView(MainActivity.this);
                tvSao.setText(hienThiSao(book.getDanhGia()));
                tvSao.setTextSize(16);
                tvSao.setTextColor(Color.rgb(230, 163, 35));

                LinearLayout.LayoutParams saoParams =
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                saoParams.topMargin = dp(7);
                card.addView(tvSao, saoParams);

                return card;
            }
        };

        lvDanhSachSach.setAdapter(adapter);

        btnThemSach.setOnClickListener(v ->
                startActivity(new Intent(this, AddBookActivity.class)));

        lvDanhSachSach.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra("viTriSach", position);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        capNhatDanhSach();
    }

    private void capNhatDanhSach() {
        noiDungHienThi.clear();

        int tongSoSach = BookRepository.danhSachSach.size();
        int soSachDaDoc = 0;
        int soSachChuaDocXong = 0;

        for (Book book : BookRepository.danhSachSach) {
            if ("Đã đọc".equals(book.getTinhTrang())) {
                soSachDaDoc++;
            } else {
                soSachChuaDocXong++;
            }

            noiDungHienThi.add(book.getTenSach());
        }

        tvThongKe.setText(
                "📚   Tổng số sách: " + tongSoSach
                        + "\n✓   Số sách đã đọc: " + soSachDaDoc
                        + "\n◷   Số sách chưa đọc xong: "
                        + soSachChuaDocXong
        );

        adapter.notifyDataSetChanged();
    }

    private String hienThiSao(float danhGia) {
        int soSao = Math.round(danhGia);
        StringBuilder ketQua = new StringBuilder();

        for (int i = 0; i < soSao; i++) {
            ketQua.append("★");
        }

        for (int i = soSao; i < 5; i++) {
            ketQua.append("☆");
        }

        return ketQua.toString();
    }
}