package ru.yandex.practicum.delivery;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();

    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(10);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(5);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(8);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackableParcels();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }


    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите описание:");
        String description = scanner.nextLine();

        System.out.println("Введите вес (целое число):");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес доставки:");
        String address = scanner.nextLine();

        System.out.println("Введите день отправки (целое число):");
        int sendDay = Integer.parseInt(scanner.nextLine());

        int timeToLive = 0;

        Parcel parcel = null;

        switch (type) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, address, sendDay);
                parcel = standardParcel;
                standardBox.addParcel(standardParcel);
                break;
            case 2:
                System.out.println("Введите время жизни (целое число):");
                timeToLive = Integer.parseInt(scanner.nextLine());
                FragileParcel fragileParcel = new FragileParcel(description, weight, address, sendDay, timeToLive);
                parcel = fragileParcel;
                fragileBox.addParcel(fragileParcel);
                trackableParcels.add(fragileParcel);
                break;
            case 3:
                System.out.println("Введите время жизни (целое число):");
                timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
                parcel = perishableParcel;
                perishableBox.addParcel(perishableParcel);
                break;
            default:
                System.out.println("Неверный тип посылки.");
                return;
        }

        allParcels.add(parcel);
        System.out.println("Посылка добавлена!");
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправки.");
            return;
        }

        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
        System.out.println("Все посылки отправлены!");
    }

    private static void calculateCosts() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для расчета стоимости.");
            return;
        }

        int totalCost = 0;
        System.out.println("Расчет стоимости доставки:");

        for (Parcel parcel : allParcels) {
            int cost = parcel.getWeight() * parcel.getBaseCost();
            System.out.println("Посылка '" + parcel.getDescription() + "': " + cost + " руб.");
            totalCost += cost;
        }

        System.out.println("Общая стоимость доставки: " + totalCost + " руб.");
    }

    private static void trackableParcels() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет посылок для отслеживания.");
            return;
        }

        System.out.println("Введите текущее местоположение посылок:");
        String location = scanner.nextLine();

        System.out.println("Отчет о местоположении:");
        for (Trackable trackable : trackableParcels) {
            trackable.reportStatus(location);
        }
    }
    private static void showBoxContents() {
        System.out.println("Выберите тип коробки для просмотра:");
        System.out.println("1 - Коробка со стандартными посылками");
        System.out.println("2 - Коробка с хрупкими посылками");
        System.out.println("3 - Коробка со скоропортящимися посылками");
        int boxType = Integer.parseInt(scanner.nextLine());

        switch (boxType) {
            case 1:
                showBoxContents(standardBox, "Коробка со стандартными посылками");
                break;
            case 2:
                showBoxContents(fragileBox, "Коробка с хрупкими посылками");
                break;
            case 3:
                showBoxContents(perishableBox, "Коробка со скоропортящимися посылками");
                break;
            default:
                System.out.println("Неверный тип коробки.");
        }
    }

    private static void showBoxContents(ParcelBox<? extends Parcel> box, String boxName) {
        List<? extends Parcel> parcels = box.getAllParcels();
        System.out.println("=== " + boxName + " ===");
        System.out.println("Текущий вес: " + box.getCurrentWeight() +
                "/" + box.getMaxWeight() +
                " (осталось: " + box.getRemainingCapacity() + ")");

        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.");
        } else {
            for (int i = 0; i < parcels.size(); i++) {
                Parcel parcel = parcels.get(i);
                System.out.println((i + 1) + ". " + parcel.getDescription() +
                        " (вес: " + parcel.getWeight() + parcel.getAdditionalInfo() + ")");
            }
        }
    }

}