package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelBoxTest {

    @Test
    public void testAddParcelWhenWeightNotExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel parcel = new StandardParcel("Книга", 3, "ул. Купол", 1);

        box.addParcel(parcel);

        assertEquals(1, box.getAllParcels().size());
        assertEquals(3, box.getCurrentWeight());
    }

    @Test
    public void testAddParcelWhenWeightExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(5);
        StandardParcel parcel1 = new StandardParcel("Книга", 3, "ул. Купол", 1);
        StandardParcel parcel2 = new StandardParcel("Одежда", 3, "ул. Юбкина", 1);

        box.addParcel(parcel1); // должно добавиться
        box.addParcel(parcel2); // не должно добавиться (3 + 3 = 6 > 5)

        assertEquals(1, box.getAllParcels().size());
        assertEquals(3, box.getCurrentWeight());
    }

    @Test
    public void testAddParcelWithExactWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(5);
        StandardParcel parcel = new StandardParcel("Книга", 5, "ул. Купол", 1);

        box.addParcel(parcel);

        assertEquals(1, box.getAllParcels().size());
        assertEquals(5, box.getCurrentWeight());
    }

    @Test
    public void testAddMultipleParcels() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel parcel1 = new StandardParcel("Книга", 2, "ул. Купол", 1);
        StandardParcel parcel2 = new StandardParcel("Одежда", 3, "ул. Юбкина", 1);
        StandardParcel parcel3 = new StandardParcel("Запчасти", 4, "ул. Отвертка", 1);

        box.addParcel(parcel1); // 2
        box.addParcel(parcel2); // 2 + 3 = 5
        box.addParcel(parcel3); // 5 + 4 = 9

        assertEquals(3, box.getAllParcels().size());
        assertEquals(9, box.getCurrentWeight());
    }

    @Test
    public void testEmptyBox() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);

        assertTrue(box.getAllParcels().isEmpty());
        assertEquals(0, box.getCurrentWeight());
    }
}

