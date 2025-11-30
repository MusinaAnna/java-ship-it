package ru.yandex.practicum.delivery;

public abstract class Parcel {

    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;


    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public String getDescription() {
        return description;
    }


    public int getWeight() {
        return weight;
    }

    public String getAdditionalInfo() {
        return "";
    }


    public int getSendDay() {
        return sendDay;
    }


    public void packageItem(){
        System.out.println("Посылка <<" + description + ">> упакована");
    }

    public void deliver(){
        System.out.println("Посылка <<" + description + ">> доставлена по адресу "  + deliveryAddress);
    }

    public abstract int getBaseCost();


    public void calculateDeliveryCost(){
        int shippingCost = weight * getBaseCost();
        System.out.println("Стоимость доставки: " + shippingCost);

    }




}
