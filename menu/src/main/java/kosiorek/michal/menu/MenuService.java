package kosiorek.michal.menu;

import kosiorek.michal.exceptions.MyException;
import kosiorek.michal.model.enums.CarBodyType;
import kosiorek.michal.model.enums.SortType;
import kosiorek.michal.model.enums.StatisticsType;
import kosiorek.michal.services.CarService;
import kosiorek.michal.services.UserDataService;

import java.math.BigDecimal;
import java.util.List;

public class MenuService {

    private CarService carService;
    private UserDataService userDataService = new UserDataService();

    public MenuService(CarService carService) {
        this.carService = carService;
    }

    public void mainMenu() {

        String menu;
        while (true) {
            try {
                do {
                    displayMenu();
                    menu = userDataService.getString("Enter number:", "");

                    switch (menu) {
                        case "1":
                            option1();
                            break;
                        case "2":
                            option2();
                            break;
                        case "3":
                            option3();
                            break;
                        case "4":
                            option4();
                            break;
                        case "5":
                            option5();
                            break;
                        case "6":
                            option6();
                            break;

                        case "7":
                            userDataService.close();
                            System.out.println("The End");
                            return;
                        default:
                            System.out.println("Invalid option in menu. Enter number again.");
                            break;
                    }
                } while (true);

            } catch (MyException e) {
                System.out.println(e.getExceptionInfo());
            }
        }

    }

    private void displayMenu() {
        System.out.println("Menu - enter the number:");
        System.out.println("1 - Sort cars.");
        System.out.println("2 - Get cars with car body type with price between a and b.");
        System.out.println("3 - Print statistics.");
        System.out.println("4 - Get car and mileage map.");
        System.out.println("5 - Get TireType and Car map.");
        System.out.println("6 - Get cars with components.");

        System.out.println("7 - Exit");
    }

    private void option1() {

        boolean descending = userDataService.getBoolean("Descending? ");
        SortType sortType = userDataService.getSortType();
        carService.sort(sortType, descending).forEach(System.out::println);

    }

    private void option2() {

        CarBodyType carBodyType = userDataService.getBodyType();
        BigDecimal a = userDataService.getBigDecimal("Enter price from:");
        BigDecimal b = userDataService.getBigDecimal("Enter price to:");
        carService.getCarsWithCarBodyTypeWithPriceBetween(carBodyType,a,b).forEach(System.out::println);

    }

    private void option3() {
        StatisticsType statisticsType = userDataService.getStatisticsType();
        carService.statistics(statisticsType);
    }

    private void option4() {
        carService.getCarAndMileageMap().forEach((k,v)->System.out.println(k+" "+v));
    }

    private void option5() {
        carService.getTireTypeAndCarMap().forEach((k,v)->System.out.println(k+" "+v));
    }

    private void option6() {

        List<String> components = userDataService.getComponents();
        carService.getCarsWithComponents(components).forEach(System.out::println);

    }
}
