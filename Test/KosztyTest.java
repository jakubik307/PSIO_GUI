package Test;

import StrategiaKosztyLeczenia.Koszty;
import StrategiaKosztyLeczenia.KosztyDorosly;
import StrategiaKosztyLeczenia.KosztyDziecko;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KosztyTest {

    @Test
    void kosztyDzieckoTest() {
        Koszty koszty = new KosztyDziecko();
        Assertions.assertAll(() -> assertEquals(600, koszty.liczenieKosztowLeczenia(0)),
                () -> assertEquals(0, koszty.liczenieKosztowLeczenia(120)),
                () -> assertEquals(550, koszty.liczenieKosztowLeczenia(10)),
                () -> assertEquals(595, koszty.liczenieKosztowLeczenia(1)),
                () -> assertEquals(565, koszty.liczenieKosztowLeczenia(7)));
    }

    @Test
    void kosztyDoroslyTest() {
        Koszty koszty = new KosztyDorosly();
        Assertions.assertAll(() -> assertEquals(1200, koszty.liczenieKosztowLeczenia(0)),
                () -> assertEquals(1190, koszty.liczenieKosztowLeczenia(1)),
                () -> assertEquals(1100, koszty.liczenieKosztowLeczenia(10)),
                () -> assertEquals(400, koszty.liczenieKosztowLeczenia(80)),
                () -> assertEquals(750, koszty.liczenieKosztowLeczenia(45)),
                () -> assertEquals(100, koszty.liczenieKosztowLeczenia(110)),
                () -> assertEquals(0, koszty.liczenieKosztowLeczenia(120)),
                () -> assertEquals(0, koszty.liczenieKosztowLeczenia(2137)));
    }

}