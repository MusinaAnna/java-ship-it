package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final List<T> parcels;
    private final int maxWeight;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.parcels = new ArrayList<>();
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
            currentWeight += parcel.getWeight();
            System.out.println("Посылка '" + parcel.getDescription() + "' добавлена в коробку.");
        } else {
            System.out.println("Предупреждение: Нельзя добавить посылку '" + parcel.getDescription() +
                    "'. Превышен максимальный вес коробки.");
        }
    }

    public List<T> getAllParcels() {
        return parcels;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getRemainingCapacity() {
        return maxWeight - currentWeight;
    }
}
