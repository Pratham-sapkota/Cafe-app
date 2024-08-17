
import java.util.List;

/**
 * The several classes that make up the beverage management system are intended to capture various facets of the drinks that are served in cafes.
 *  Individual beverages are represented by the Beverage class, which holds their names, distinctive identifiers
 * and comprehensive data that is contained in the UserDreamBeverage class.
 * UserDreamBeverage class  defines generic characteristics shared by all beverages
 * like cost, sugar level, varieties of milk available, additional components, and a brief description. 
 *  Subclasses such as CoffeeSpecific and TeaSpecific extend UserDreamBeverage and include specific information.
 * The constructors of each class set their initial values, and the getter methods access the data that has been saved. 
 */
  class UserDreamBeverage {
    private double priceOfBev;
    private boolean sugar;
    private List<String> milkType;
    private List<String> extras;
    private String description;

    public UserDreamBeverage(double priceOfBev, boolean sugar, List<String> milkType, List<String> extras, String description) {
        this.priceOfBev = priceOfBev;
        this.sugar = sugar;
        this.milkType = milkType;
        this.extras = extras;
        this.description = description;
    }

    public double getPriceOfBev() {
        return priceOfBev;
    }

    public boolean hasSugar() {
        return sugar;
    }

    public List<String> getMilkType() {
        return milkType;
    }

    public List<String> getExtras() {
        return extras;
    }

    public String getDescription() {
        return description;
    }
}

 class Beverage {
    private int beverageId;
    private String bevName;
    private UserDreamBeverage userDreamBeverageMenu;

    public Beverage(int beverageId, String bevName, UserDreamBeverage userDreamBeverageMenu) {
        this.beverageId = beverageId;
        this.bevName = bevName;
        this.userDreamBeverageMenu = userDreamBeverageMenu;
    }

    public int getBeverageId() {
        return beverageId;
    }

    public String getBevName() {
        return bevName;
    }

    public UserDreamBeverage getDreamBeverageMenu() {
        return userDreamBeverageMenu;
    }
}


class Coffee extends Beverage {
    public Coffee(int id, String name, CoffeeSpecific coffeeSpecificDetails) {
        super(id, name, coffeeSpecificDetails);
    }
}


/**
 * Since there are two classes which are encapsulated , new classes have been created .
 * CoffeeDetails class consists of specific feature for coffees like number of shots.
 * Same goes for TeaDetails, it consists specific properties like tea boiling temperature and steeping time.
 * Both of them are used for seperation of concerns , future extensibility and polymorphism.
 */

 class CoffeeSpecific extends UserDreamBeverage {
    private int shots;

    public CoffeeSpecific(double priceOfBev, boolean sugar, int shots, List<String> milkType, List<String> extras, String description) {
        super(priceOfBev, sugar, milkType, extras, description);
        this.shots = shots;
    }

    public int getNumberOfShots() {
        return shots;
    }
}


class Tea extends Beverage {
    public Tea(int id, String name, TeaSpecific teaSpecifcDetails) {
        super(id, name, teaSpecifcDetails);
    }
}


class TeaSpecific extends UserDreamBeverage {
    private int temperature;
    private int steepingTime;

    public TeaSpecific(double priceOfBev, boolean sugar, int temperature, int steepingTime, List<String> milkType, List<String> extras, String description) {
        super(priceOfBev, sugar, milkType, extras, description);
        this.temperature = temperature;
        this.steepingTime = steepingTime;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getTeaSteepingTime() {
        return steepingTime;
    }
}

