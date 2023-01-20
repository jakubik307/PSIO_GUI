package Test;

import StrategiaPensja.Pensja;
import StrategiaPensja.PensjaLekarz;
import StrategiaPensja.PensjaPielegniarka;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PensjeTest {

    @Test
    void pensjaLekarzTest() {
        Pensja pensja = new PensjaLekarz();
        Assertions.assertAll(() -> assertEquals(6000, pensja.liczeniePensji(20)),
                () -> assertEquals(300, pensja.liczeniePensji(1)),
                () -> assertEquals(0, pensja.liczeniePensji(0)),
                () -> assertEquals(3000, pensja.liczeniePensji(10)),
                () -> assertEquals(30000, pensja.liczeniePensji(100)));
    }

    @Test
    void pensjaPielegniarkaTest() {
        Pensja pensja = new PensjaPielegniarka();
        Assertions.assertAll(() -> assertEquals(4000, pensja.liczeniePensji(20)),
                () -> assertEquals(200, pensja.liczeniePensji(1)),
                () -> assertEquals(0, pensja.liczeniePensji(0)),
                () -> assertEquals(2000, pensja.liczeniePensji(10)),
                () -> assertEquals(20000, pensja.liczeniePensji(100)));
    }

}