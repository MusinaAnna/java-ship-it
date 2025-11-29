package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    @Test
    public void testStandardParcelCost() {
        StandardParcel parcel = new StandardParcel("Ноут", 5, "ул. Кинга", 1);
        assertEquals(10, parcel.getWeight() * parcel.getBaseCost()); // 5 * 2 = 10
    }

    @Test
    public void testFragileParcelCost() {
        FragileParcel parcel = new FragileParcel("Мышь", 3, "ул. Конга", 1, 5);
        assertEquals(12, parcel.getWeight() * parcel.getBaseCost()); // 3 * 4 = 12
    }

    @Test
    public void testPerishableParcelCost() {
        PerishableParcel parcel = new PerishableParcel("Планшет", 2, "ул. Яблочная", 1, 3);
        assertEquals(6, parcel.getWeight() * parcel.getBaseCost()); // 2 * 3 = 6
    }

    @Test
    public void testZeroWeightParcelCost() {
        StandardParcel parcel = new StandardParcel("Стилус", 0, "ул. Хуавей", 1);
        assertEquals(0, parcel.getWeight() * parcel.getBaseCost()); // 0 * 2 = 0
    }
}