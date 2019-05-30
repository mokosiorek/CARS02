package kosiorek.michal.menu;

import kosiorek.michal.services.CarService;

public class App 
{
    public static void main( String[] args )
    {
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json", "cars02car4.json");
        MenuService menuService = new MenuService(carService);
        menuService.mainMenu();

    }
}
