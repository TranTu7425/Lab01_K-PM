package vn.phenikaa.ktpm;

/**
 * Lab 01 - Kiem dinh phan mem.
 * <p>
 * Chuong trinh phan loai hoc luc cua sinh vien dua tren mot mang diem thanh phan.
 * Phuong thuc {@link #classify(double[])} chua:
 *   - Mot cau lenh re nhanh (if/else if/else) de kiem tra dau vao va xep loai.
 *   - Mot vong lap (for-each) de duyet qua cac diem va cong don tong.
 */
public class GradeClassifier {

    /** Diem khong hop le (nam ngoai [0, 10] hoac mang rong/null). */
    public static final String INVALID = "INVALID";
    /** Loai Gioi: trung binh >= 8.0. */
    public static final String GIOI = "GIOI";
    /** Loai Kha: 6.5 <= trung binh < 8.0. */
    public static final String KHA = "KHA";
    /** Loai Trung binh: 5.0 <= trung binh < 6.5. */
    public static final String TRUNG_BINH = "TRUNG_BINH";
    /** Loai Yeu: trung binh < 5.0. */
    public static final String YEU = "YEU";

    /**
     * Phan loai hoc luc dua tren mang diem.
     *
     * @param scores mang diem thanh phan, moi diem trong khoang [0, 10].
     * @return chuoi phan loai: INVALID / GIOI / KHA / TRUNG_BINH / YEU.
     */
    public static String classify(double[] scores) {
        if (scores == null || scores.length == 0) {
            return INVALID;
        }

        double sum = 0.0;
        for (double s : scores) {
            if (s < 0.0 || s > 10.0) {
                return INVALID;
            }
            sum += s;
        }

        double avg = sum / scores.length;
        if (avg >= 8.0) {
            return GIOI;
        } else if (avg >= 6.5) {
            return KHA;
        } else if (avg >= 5.0) {
            return TRUNG_BINH;
        } else {
            return YEU;
        }
    }

    public static void main(String[] args) {
        double[][] samples = {
                {9.0, 8.5, 8.0},
                {7.0, 6.5, 7.5},
                {5.5, 5.0, 6.0},
                {4.0, 3.5, 4.5},
                {9.0, 11.0, 7.0},
                {}
        };

        for (double[] sample : samples) {
            System.out.println("Diem = " + java.util.Arrays.toString(sample)
                    + " -> " + classify(sample));
        }
    }
}
