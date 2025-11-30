package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable{

    private final int timeToLive;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }


    @Override
    public void packageItem(){
        System.out.println("Посылка <<" + description + ">> обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public int getBaseCost() {
        return ParcelType.FRAGILE.getBaseCost();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка <<" + getDescription() + ">> изменила местоположение на " + newLocation);
    }

    @Override
    public String getAdditionalInfo() {
        return ", время жизни: " + timeToLive;
    }
}
