package com.example.qlsach;

import java.util.ArrayList;

public class BookRepository {
    public static final ArrayList<Book> danhSachSach = new ArrayList<>();
    private static boolean daKhoiTao = false;

    public static void khoiTaoDuLieuMau() {
        if (daKhoiTao) return;

        danhSachSach.add(new Book(
                "Lập trình Android cơ bản", "Nguyễn Văn A",
                "Công nghệ", "Đang đọc", 4, "Sách học lập trình Android"
        ));
        danhSachSach.add(new Book(
                "Đắc nhân tâm", "Dale Carnegie",
                "Kỹ năng sống", "Đã đọc", 5, "Sách về kỹ năng giao tiếp"
        ));
        danhSachSach.add(new Book(
                "Dế Mèn phiêu lưu ký", "Tô Hoài",
                "Văn học", "Chưa đọc", 0, ""
        ));

        daKhoiTao = true;
    }
}
