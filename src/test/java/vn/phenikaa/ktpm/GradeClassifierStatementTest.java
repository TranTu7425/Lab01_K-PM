package vn.phenikaa.ktpm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static vn.phenikaa.ktpm.GradeClassifier.*;

/**
 * Statement Coverage Test cho GradeClassifier.classify().
 *
 * Muc tieu: moi lenh (statement) trong phuong thuc classify()
 * duoc thuc thi it nhat mot lan.
 *
 * Cac lenh can bao phu:
 *   S1: if (scores == null || scores.length == 0) -> return INVALID
 *   S2: double sum = 0.0;
 *   S3: for (double s : scores)
 *   S4:   if (s < 0.0 || s > 10.0) -> return INVALID
 *   S5:   sum += s;
 *   S6: double avg = sum / scores.length;
 *   S7: if (avg >= 8.0) -> return GIOI
 *   S8: else if (avg >= 6.5) -> return KHA
 *   S9: else if (avg >= 5.0) -> return TRUNG_BINH
 *   S10: else -> return YEU
 */
class GradeClassifierStatementTest {

    // --- Test bao phu S1: scores == null ---
    @Test
    void testNull_coversS1() {
        assertEquals(INVALID, classify(null));
    }

    // --- Test bao phu S1: scores.length == 0 ---
    @Test
    void testEmpty_coversS1() {
        assertEquals(INVALID, classify(new double[]{}));
    }

    // --- Test bao phu S2, S3, S4: diem khong hop le (> 10) ---
    @Test
    void testScoreAbove10_coversS2_S3_S4() {
        assertEquals(INVALID, classify(new double[]{9.0, 10.5}));
    }

    // --- Test bao phu S2, S3, S4: diem khong hop le (< 0) ---
    @Test
    void testNegativeScore_coversS2_S3_S4() {
        assertEquals(INVALID, classify(new double[]{-1.0, 5.0}));
    }

    // --- Test bao phu S2, S3, S5, S6, S7: avg >= 8.0 -> GIOI ---
    @Test
    void testGioi_coversS2_S3_S5_S6_S7() {
        assertEquals(GIOI, classify(new double[]{9.0, 8.5, 8.5}));
    }

    // --- Test bao phu S8: avg >= 6.5 -> KHA ---
    @Test
    void testKha_coversS8() {
        assertEquals(KHA, classify(new double[]{7.0, 7.0, 7.0}));
    }

    // --- Test bao phu S9: avg >= 5.0 -> TRUNG_BINH ---
    @Test
    void testTrungBinh_coversS9() {
        assertEquals(TRUNG_BINH, classify(new double[]{5.5, 6.0, 5.5}));
    }

    // --- Test bao phu S10: avg < 5.0 -> YEU ---
    @Test
    void testYeu_coversS10() {
        assertEquals(YEU, classify(new double[]{3.0, 4.0, 2.0}));
    }
}