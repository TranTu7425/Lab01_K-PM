package vn.phenikaa.ktpm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static vn.phenikaa.ktpm.GradeClassifier.*;

/**
 * Path Coverage Test cho GradeClassifier.classify().
 *
 * Muc tieu: moi duong di (path) tu dau den cuoi phuong thuc classify()
 * duoc thuc thi it nhat mot lan.
 *
 * Cac duong di (path) da xac dinh:
 *   P1:  scores == null                             -> INVALID
 *   P2:  scores.length == 0                         -> INVALID
 *   P3:  loop -> phan tu dau s < 0                  -> INVALID
 *   P4:  loop -> phan tu dau s > 10                 -> INVALID
 *   P5:  loop -> phan tu dau hop le, sau s < 0      -> INVALID
 *   P6:  loop -> phan tu dau hop le, sau s > 10     -> INVALID
 *   P7:  loop -> tat ca hop le -> avg >= 8.0        -> GIOI
 *   P8:  loop -> tat ca hop le -> 6.5 <= avg < 8.0  -> KHA
 *   P9:  loop -> tat ca hop le -> 5.0 <= avg < 6.5  -> TRUNG_BINH
 *   P10: loop -> tat ca hop le -> avg < 5.0         -> YEU
 */
class GradeClassifierPathTest {

    // ===== P1: scores == null -> INVALID =====
    @Test
    void path1_null() {
        assertEquals(INVALID, classify(null));
    }

    // ===== P2: scores.length == 0 -> INVALID =====
    @Test
    void path2_emptyArray() {
        assertEquals(INVALID, classify(new double[]{}));
    }

    // ===== P3: loop -> phan tu dau s < 0 -> INVALID =====
    @Test
    void path3_firstElementNegative() {
        assertEquals(INVALID, classify(new double[]{-1.0, 5.0, 6.0}));
    }

    // ===== P4: loop -> phan tu dau s > 10 -> INVALID =====
    @Test
    void path4_firstElementAbove10() {
        assertEquals(INVALID, classify(new double[]{11.0, 5.0, 6.0}));
    }

    // ===== P5: loop -> phan tu dau hop le, phan tu sau s < 0 -> INVALID =====
    @Test
    void path5_laterElementNegative() {
        assertEquals(INVALID, classify(new double[]{8.0, -2.0, 7.0}));
    }

    // ===== P6: loop -> phan tu dau hop le, phan tu sau s > 10 -> INVALID =====
    @Test
    void path6_laterElementAbove10() {
        assertEquals(INVALID, classify(new double[]{8.0, 12.0, 7.0}));
    }

    // ===== P7: loop -> tat ca hop le -> avg >= 8.0 -> GIOI =====
    @Test
    void path7_allValid_gioi() {
        assertEquals(GIOI, classify(new double[]{9.0, 8.5, 8.5}));
    }

    // ===== P8: loop -> tat ca hop le -> 6.5 <= avg < 8.0 -> KHA =====
    @Test
    void path8_allValid_kha() {
        assertEquals(KHA, classify(new double[]{7.0, 7.0, 7.0}));
    }

    // ===== P9: loop -> tat ca hop le -> 5.0 <= avg < 6.5 -> TRUNG_BINH =====
    @Test
    void path9_allValid_trungBinh() {
        assertEquals(TRUNG_BINH, classify(new double[]{6.0, 5.5, 5.5}));
    }

    // ===== P10: loop -> tat ca hop le -> avg < 5.0 -> YEU =====
    @Test
    void path10_allValid_yeu() {
        assertEquals(YEU, classify(new double[]{3.0, 4.0, 2.0}));
    }

    // =========================================================
    // Parameterized test: kiem tra tat ca 10 duong di cung luc
    // =========================================================
    static Stream<Arguments> allPathsProvider() {
        return Stream.of(
                // P1: null
                Arguments.of(null, INVALID),
                // P2: empty
                Arguments.of(new double[]{}, INVALID),
                // P3: phan tu dau < 0
                Arguments.of(new double[]{-1.0, 5.0, 6.0}, INVALID),
                // P4: phan tu dau > 10
                Arguments.of(new double[]{11.0, 5.0, 6.0}, INVALID),
                // P5: phan tu sau < 0
                Arguments.of(new double[]{8.0, -2.0, 7.0}, INVALID),
                // P6: phan tu sau > 10
                Arguments.of(new double[]{8.0, 12.0, 7.0}, INVALID),
                // P7: GIOI
                Arguments.of(new double[]{9.0, 8.5, 8.5}, GIOI),
                // P8: KHA
                Arguments.of(new double[]{7.0, 7.0, 7.0}, KHA),
                // P9: TRUNG_BINH
                Arguments.of(new double[]{6.0, 5.5, 5.5}, TRUNG_BINH),
                // P10: YEU
                Arguments.of(new double[]{3.0, 4.0, 2.0}, YEU)
        );
    }

    @ParameterizedTest(name = "Path: scores={0} -> {1}")
    @MethodSource("allPathsProvider")
    void allPaths(double[] scores, String expected) {
        assertEquals(expected, classify(scores));
    }
}