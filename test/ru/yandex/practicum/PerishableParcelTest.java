package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.*;

public class PerishableParcelTest {

    @Test
    public void testNotExpiredWhenWithinTimeToLive() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "ул. Бульк", 1, 3);
        assertFalse(parcel.isExpired(3)); // день 1 + 3 = день 4, текущий день 3 - не испорчено
    }

    @Test
    public void testExpiredWhenExceededTimeToLive() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "ул. Бульк", 1, 3);
        assertTrue(parcel.isExpired(5)); // день 1 + 3 = день 4, текущий день 5 - испорчено
    }

    @Test
    public void testExpiredOnLastDay() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "ул. Бульк", 1, 3);
        assertFalse(parcel.isExpired(4)); // день 1 + 3 = день 4, текущий день 4 - последний день, не испорчено
    }

    @Test
    public void testExpiredWithZeroTimeToLive() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "ул. Бульк", 1, 0);
        assertTrue(parcel.isExpired(2)); // время жизни 0, на следующий день уже испорчено
    }
}
