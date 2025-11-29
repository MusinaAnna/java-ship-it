package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private final int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return (getSendDay() + timeToLive) < currentDay;
    }

    @Override
    public int getBaseCost() {
        return ParcelType.PERISHABLE.getBaseCost();
    }

    @Override
    public String getAdditionalInfo() {
        return ", время жизни: " + timeToLive;
    }
}
