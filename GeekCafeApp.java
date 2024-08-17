import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JFrame is used to create GUI which works as the container for our application.
 * To construct a GUI application for a coffee and tea ordering system, the GeekCafeApp class extends JFrame.
 *  It manages various panels for searching for beverages, showing matching results, and obtaining user information using a CardLayout.
 * The search panel appears when the application first launches.
 * JPanel  helps to organize and group containers within a window.
 */

public class GeekCafeApp extends JFrame {
    // ArrayList<> helps to create dynamic array for expansion and deduction of array as user wish.
    private static final List<Beverage> menu = new ArrayList<>();
    private JPanel mainPanelOfFrame;
    // CardLayout is a layout manager that defines the layout of app here.
    private  CardLayout cardTypeLayout;
    // JComboBox creates dropdown menu to select from various options
    private JComboBox<String> comboBoxForMilkType, comboBoxForMatchedOrder, comboBoxForExtras, comboBoxForSugar,comboBoxForShots, comboBoxForSteepingTime, comboBoxForTemperature;
    // JTextArea is area where text appears as  result or information
    private JTextArea textAreaForResult;
    // JTextField allows place to write/ input for users
    private JTextField minimumPriceRangeField, maximumPriceRangeField, extrasInputTextField,phoneNumberField, usersNameField,  particularOrderField;
    // JRadioButton allows user to select one from various options
    private JRadioButton coffeeSelectionButton, teaSelectionButton;

    public GeekCafeApp() {
        setTitle("Caffeniated Geek App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 500);
        setLocationRelativeTo(null);
        cardTypeLayout = new CardLayout();
        mainPanelOfFrame = new JPanel(cardTypeLayout);

        /* Creating various panels for different purpose. */
        JPanel searchBeveragePanel = searchBeveragesMenu();
        JPanel matchedBeveragePanel = showMatchedBeveragesResult();
        JPanel userDetailPanel = gatherUserInfo();
        
        mainPanelOfFrame.add(searchBeveragePanel, "Search");
        mainPanelOfFrame.add(matchedBeveragePanel, "Results");
        mainPanelOfFrame.add(userDetailPanel, "Input");

        add(mainPanelOfFrame);
        cardTypeLayout.show(mainPanelOfFrame, "Search");

        readMenuFromFile("menu.txt");
    }

    /**
     * searchBeveragesMenu is a method which searches for menu from menu.txt file based on user input
     * User will input various information as they wish
     * Action listeners are used such that when certain action takes place certain thing happens.
     * @return
     */
    private JPanel searchBeveragesMenu() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBackground(Color.MAGENTA);
        panel.setFont(new Font("Serif",Font.BOLD,16));

        ButtonGroup beverageTypeGroup = new ButtonGroup();
        coffeeSelectionButton = new JRadioButton("Coffee");
        teaSelectionButton = new JRadioButton("Tea");
        beverageTypeGroup.add(coffeeSelectionButton);
        beverageTypeGroup.add(teaSelectionButton);

        coffeeSelectionButton.addActionListener(e -> coffeeOptions());
        teaSelectionButton.addActionListener(e -> teaOptions());

        panel.add(new JLabel("Select Beverage:"));
        panel.add(coffeeSelectionButton);
        panel.add(new JLabel(""));
        panel.add(teaSelectionButton);

         // Coffee specific options
         comboBoxForShots = new JComboBox<>(new String[]{"1", "2", "3","Skip"});
         panel.add(new JLabel("Number of Shots For Coffee:"));
         panel.add(comboBoxForShots);
         comboBoxForShots.setVisible(false);
 
         // Tea specific options
         comboBoxForSteepingTime = new JComboBox<>(new String[]{
             Enums.TeaSteepingTime.ONE.getValue(),
             Enums.TeaSteepingTime.TWO.getValue(),
             Enums.TeaSteepingTime.THREE.getValue(),
             Enums.TeaSteepingTime.FOUR.getValue(),
             Enums.TeaSteepingTime.FIVE.getValue(),
             Enums.TeaSteepingTime.SIX.getValue(),
             Enums.TeaSteepingTime.SEVEN.getValue(),
             Enums.TeaSteepingTime.EIGHT.getValue(),
             Enums.TeaSteepingTime.SKIP.getValue(),
             
         });
         comboBoxForTemperature = new JComboBox<>(new String[]{
             Enums.BoilingTemperature.EIGHTY.getValue(),
             Enums.BoilingTemperature.EIGHTY_FIVE.getValue(),
             Enums.BoilingTemperature.NINETY.getValue(),
             Enums.BoilingTemperature.NINETY_FIVE.getValue(),
             Enums.BoilingTemperature.HUNDRED.getValue(),
             Enums.BoilingTemperature.SKIP.getValue()
         });
         panel.add(new JLabel("Tea Steeping Time:"));
         panel.add(comboBoxForSteepingTime);
         panel.add(new JLabel("Temperature at which tea boils:"));
         panel.add(comboBoxForTemperature);
         comboBoxForSteepingTime.setVisible(false);
         comboBoxForTemperature.setVisible(false);

         panel.add(new JLabel("Select preferred milk type:"));
         comboBoxForMilkType = new JComboBox<>(new String[]{
             Enums.TypeOfMilk.FULL_CREAM.getValue(),
             Enums.TypeOfMilk.SOY.getValue(),
             Enums.TypeOfMilk.SKIM.getValue(),
             Enums.TypeOfMilk.OAT.getValue(),
             Enums.TypeOfMilk.ALMOND.getValue(),
             Enums.TypeOfMilk.COCONUT.getValue(),
             Enums.TypeOfMilk.SKIP.getValue()
         });
         panel.add(comboBoxForMilkType);

         panel.add(new JLabel("Sugar Preferences"));
        comboBoxForSugar= new JComboBox<>(new String[]{
            "Yes","No","I don't mind"
        });
        panel.add(comboBoxForSugar);

        panel.add(new JLabel("Extras:"));
        comboBoxForExtras = new JComboBox<>(new String[]{
             "One", "More Than One", "Skip"
        });

        panel.add(comboBoxForExtras);
        extrasInputTextField = new JTextField();
        extrasInputTextField.setVisible(false);
        panel.add(new JLabel("Extras:"));
        panel.add(extrasInputTextField);
        comboBoxForExtras.addActionListener(e -> ActionForExtrasSelection());


        panel.add(new JLabel("Price Range:"));
        JPanel pricePanel = new JPanel(new GridLayout(1, 1));
        minimumPriceRangeField = new JTextField();
        maximumPriceRangeField = new JTextField();
        pricePanel.add(minimumPriceRangeField);
        pricePanel.add(maximumPriceRangeField);
        panel.add(pricePanel);

       JButton searchButton = new JButton("Search");
       searchButton.setBackground(Color.CYAN);
        searchButton.addActionListener(e -> searchBeverageMenu());
        panel.add(new JLabel(""));
        panel.add(searchButton);

        return panel;
    }

    /**
     * This showMatchedBeveragesResult() returns new JPanel with flow layout.
     * There is a  non-editable JTextArea inside a JScrollPane
     * Consists of back button so that user can go back to search panel
     * It has event listeners that changes to different  on clicking buttons.
     * @return panel
     */
    private JPanel showMatchedBeveragesResult() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        textAreaForResult = new JTextArea();
        textAreaForResult.setBackground(Color.GRAY);
        textAreaForResult.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaForResult);


        JButton backButton = new JButton("Back to Search");
        backButton.addActionListener(e -> cardTypeLayout.show(mainPanelOfFrame, "Search"));
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> cardTypeLayout.show(mainPanelOfFrame, "Input"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.MAGENTA);
        buttonPanel.add(backButton);
        buttonPanel.add(placeOrderButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.WEST);

        return panel;
    }

    /**
     * gatherUserInfo generates and provides a JPanel that is set up in a two-column GridLayout.
     * Details like name , number and order of customer are entered into indicated fields.
     * When submit button is clicked it calls placeOrder() method.
     * @return
     */
    private JPanel gatherUserInfo() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBackground(Color.ORANGE);
        JLabel usernameLabel=new JLabel("Name:");
        usernameLabel.setFont(new Font("Serif", Font.CENTER_BASELINE, 21));
        panel.add(usernameLabel);
        usersNameField = new JTextField(20);
        usersNameField.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        panel.add(usersNameField);

        JLabel phoneNumberLabel=new JLabel("Phone Number:");
        phoneNumberLabel.setFont(new Font("Serif", Font.CENTER_BASELINE, 21));
        panel.add(phoneNumberLabel);
        phoneNumberField = new JTextField();
        phoneNumberField.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        panel.add(phoneNumberField);

        JLabel selectionLabel=new JLabel("Select Beverage:");
        selectionLabel.setFont(new Font("Serif", Font.CENTER_BASELINE, 21));
        panel.add(selectionLabel);
        comboBoxForMatchedOrder= new JComboBox<>();
        comboBoxForMatchedOrder.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        panel.add(comboBoxForMatchedOrder);

        JButton submitButton = new JButton("Submit Order");
        submitButton.setBackground(Color.YELLOW);
        submitButton.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
        submitButton.addActionListener(e -> placeOrder());
        panel.add(new JLabel(""));
        panel.add(submitButton);

        return panel;
    }

    /**
     * coffeeOptions() method smakes certain properties visible and hidden
     */
    private void coffeeOptions() {
        comboBoxForShots.setVisible(true);
        comboBoxForSteepingTime.setVisible(false);
        comboBoxForTemperature.setVisible(false);
    }

    /**
     * teaOptions() method smakes certain properties visible and hidden
     */

    private void teaOptions() {
        comboBoxForShots.setVisible(false);
        comboBoxForSteepingTime.setVisible(true);
        comboBoxForTemperature.setVisible(true);
    }

    /**
     * ActionForExtrasSelection() when invoked , it determines visibility of extras textfield based on users choice
     * It has options like  One , More than one or Skip
     */

    private void ActionForExtrasSelection() {
        String selectedOption = (String) comboBoxForExtras.getSelectedItem();
        if ("One".equals(selectedOption) || "More Than One".equals(selectedOption)) {
            extrasInputTextField.setVisible(true);
        } else {
            extrasInputTextField.setVisible(false);
        }
        mainPanelOfFrame.revalidate();
        mainPanelOfFrame.repaint();
    }

    /**
     * searchBeverageMenu() method determines the flow of app.
     * The searchBeverageMenu method verifies the price range input and determines whether the beverage type (tea or coffee) is selected.
     * There are input validations so that user dont make mistakes.
     * User choices for temperature, steeping duration, number of shots, extras, sugar, and milk type are retrieved. 
     * Using these parameters, it identifies matching beverages from the menu. 
     * It shows a notice if no matches are discovered, else it moves to the results display panel and displays the results in a JTextArea.
     */
    private void searchBeverageMenu() {
        if (!coffeeSelectionButton.isSelected() && !teaSelectionButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a beverage type.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double minPrice, maxPrice;
        try {
            minPrice = Double.parseDouble(minimumPriceRangeField.getText());
            maxPrice = Double.parseDouble(maximumPriceRangeField.getText());
            if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
                JOptionPane.showMessageDialog(this, "Please enter a valid price range.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid decimal numbers for the price range.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String milkType = (String) comboBoxForMilkType.getSelectedItem();
        String extrasOption = (String) comboBoxForExtras.getSelectedItem();
        String extras = "";
        if ("One".equals(extrasOption) || "More Than One".equals(extrasOption)) {
            extras = extrasInputTextField.getText();
        }
    
        String sugar= (String) comboBoxForSugar.getSelectedItem();
        String steepingTime= (String) comboBoxForSteepingTime.getSelectedItem() ;
        String temp= (String) comboBoxForTemperature.getSelectedItem();

        String shots= (String) comboBoxForShots.getSelectedItem();

        ArrayList<Beverage> matchingBeverages = filterMatchingBeverages(
                coffeeSelectionButton.isSelected() ? Enums.BeverageTypeOptions.COFFEE.getValue() : Enums.BeverageTypeOptions.TEA.getValue(),
                minPrice, maxPrice, sugar, milkType, extras, steepingTime, temp,shots
        );

        if (matchingBeverages.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No matching items were found!", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder result = new StringBuilder();
            List<String> matchedBevName = new ArrayList<>();
            for (Beverage beverage : matchingBeverages) {
                result.append(beverage.getBevName()).append("(").append(beverage.getBeverageId()).append(")").append("\n");
                result.append(beverage.getDreamBeverageMenu().getDescription()).append("\n");
                result.append("Ingredients:").append("\n");
                if(beverage instanceof Coffee){
                    CoffeeSpecific coffeeDetails = (CoffeeSpecific) beverage.getDreamBeverageMenu();
                    result.append("No. of shots: ").append(coffeeDetails.getNumberOfShots()).append("\n");
                }
                if(beverage instanceof Tea){
                    TeaSpecific teaDetails = (TeaSpecific) beverage.getDreamBeverageMenu();
                    result.append("Temperature: ").append(teaDetails.getTemperature()).append("\n");
                    result.append("Steeping Time: ").append(teaDetails.getTeaSteepingTime()).append("\n");
                }
                result.append("Sugar: ").append(beverage.getDreamBeverageMenu().hasSugar()).append("\n");
                result.append("Milk Options").append(beverage.getDreamBeverageMenu().getMilkType()).append("\n");
                result.append("Extra/s:").append(beverage.getDreamBeverageMenu().getExtras()).append("\n");
                result.append("Price: $").append(beverage.getDreamBeverageMenu().getPriceOfBev()).append("\n\n");
                matchedBevName.add(beverage.getBevName());
            }
            textAreaForResult.setText(result.toString());

            Font resultFont= new Font("Serif",Font.BOLD,15);
            textAreaForResult.setFont(resultFont);
            cardTypeLayout.show(mainPanelOfFrame, "Results");

            //removing previous beverages name and add new ones for selection
            comboBoxForMatchedOrder.removeAllItems();;
            for(String name:matchedBevName){
                comboBoxForMatchedOrder.addItem(name);
            }
        }
    }

    /**
     * The placeOrder method checks that the phone and name entered by the user are not empty after reading the input.
     *  To locate the corresponding beverage ID, it reads data from menu.txt. 
     *  The order information are then written to order.txt, and a success message is shown.
     */
    private void placeOrder() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader("menu.txt"))){
            String name = usersNameField.getText();
            String phone = phoneNumberField.getText();
            String orderName = (String) comboBoxForMatchedOrder.getSelectedItem();
            String menuId = null;

        if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both name and phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            fileReader.readLine();
            String line;

            while ((line = fileReader.readLine()) != null) {
                ArrayList<String> beverageDetail= splitMenu(line);
                if(beverageDetail.get(2).equals(orderName)){
                    menuId= beverageDetail.get(1);

                }

            };
            FileWriter writeOrderTo= new FileWriter("order.txt");
            writeOrderTo.write("Order Details: \n");
            writeOrderTo.write("Name: "+name+"\n");
            writeOrderTo.write("Order Number: "+phone+"\n");
            writeOrderTo.write("Item: "+orderName+"("+menuId+") \n");
            writeOrderTo.close();
            JOptionPane.showMessageDialog(this, "Order has been placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();


        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }





    }

     /**
        *  splitMenu() method splits  menu by "," but the content within the [ ] remains same.
        *  The splitMenu method preserves segments enclosed in square brackets when splitting a comma-separated string, line, into an ArrayList<String>.
        *  The technique makes sure that text included in square brackets—even commas—is handled as a single section.
        * @param line
        * @return
        */
    public static ArrayList<String> splitMenu(String line){
        ArrayList<String> words = new ArrayList<>();
                StringBuilder currentPart = new StringBuilder();
                boolean containsSquareBrackets = false;

                for (char c : line.toCharArray()) {
                     if (c == '[') {
                        containsSquareBrackets = true;
                    } else if (c == ']') {
                        containsSquareBrackets = false;
                    }

                    if (c == ',' && !containsSquareBrackets) {
                        words.add(currentPart.toString());
                        currentPart.setLength(0);
                    } else {
                        currentPart.append(c);
                    }
                }
                words.add(currentPart.toString());
                return words;
     }

     /**
      * The readMenuFromFile function adds beverage data to a menu list by reading them from a file. 
      * It opens the menu.txt file, reads each line that follows, and ignores the header.
      * It produces and adds CoffeeDetails or TeaDetails objects to the menu based on whether the beverage type is coffee or tea. 
      * @param filename
      */

    public static void readMenuFromFile(String filename) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String line;
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                ArrayList<String> words= splitMenu(line);
                int id = Integer.parseInt(words.get(1));
                String type = words.get(0);
                String name = words.get(2);
                double price = Double.parseDouble(words.get(3));
                boolean sugar = words.get(7).equalsIgnoreCase("yes");
                ArrayList<String> milkType= parseBeverageList(words.get(8));
                ArrayList<String> extras= parseBeverageList(words.get(9));
                String description = words.get(10);

                if (type.equalsIgnoreCase("coffee")) {
                    int shots = Integer.parseInt(words.get(4));
                    CoffeeSpecific coffeeSpecificDetails = new CoffeeSpecific(price, sugar, shots, milkType, extras, description);
                    menu.add(new Coffee(id, name, coffeeSpecificDetails));
                } else if (type.equalsIgnoreCase("tea")) {
                    int temp = Integer.parseInt(words.get(5));
                    int time = Integer.parseInt(words.get(6));
                    TeaSpecific teaSpecifcDetails = new TeaSpecific(price, sugar, temp, time, milkType, extras, description);
                    menu.add(new Tea(id, name, teaSpecifcDetails));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     /**
     * parseBeverageList is used to remove square brackets from strings that may contain them.
     * While reading the menu , used for milk types and extras
     * return array
     * @param string
     * @return
     */
    private static ArrayList<String> parseBeverageList(String string) {
        ArrayList<String> list = new ArrayList<>();
        if (!string.isEmpty()) {
            String[] words = string.substring(1, string.length() - 1).split(",");
            for (String part : words) {
                list.add(part.trim());
            }
        }
        return list;
    }

    /**
     * the filterMatchingBeverages method uses following parameters  to filter the menu.
     * It works over preferences of user's choice.
     * It returns the list with the matched beverages added to it.
     * The approach ensures that only the pertinent characteristics are examined by handling distinct criteria for tea and coffee.
     * Returns empty array if not found.
     * @param beverageType 
     * @param shots 
     * @param sugar 
     * @param milkType 
     * @param temp 
     * @param steepingTime 
     * @param extras 
     * @return 
     */
    public ArrayList<Beverage> filterMatchingBeverages(String beverageType, double minPrice, double maxPrice,
                    String sugar, String milkType, String extras, String steepingTime, String temp,String shots){                                                        
        ArrayList<Beverage> matchingBeverages = new ArrayList<>();
        boolean sugarRequired = sugar.equals("Yes");
        boolean skipSugar = sugar.equals("I don't mind");
        for (Beverage beverage : menu) {
            if ((beverageType == Enums.BeverageTypeOptions.COFFEE.getValue() && beverage instanceof Coffee) ||
                (beverageType == Enums.BeverageTypeOptions.TEA.getValue() && beverage instanceof Tea)) {
                if (beverageType.equals("coffee")) {
                    //instantiate the CoffeDetails class to store coffee specific data
                    CoffeeSpecific coffeeSpecificDetails = (CoffeeSpecific) beverage.getDreamBeverageMenu();
                    if (coffeeSpecificDetails.getNumberOfShots() != Integer.parseInt(shots) && Integer.parseInt(shots) != 0) {
                        continue;
                    }
                } else {
                    TeaSpecific teaSpecifcDetails = (TeaSpecific) beverage.getDreamBeverageMenu();
                    if (!String.valueOf(teaSpecifcDetails.getTemperature()).equals(temp) && !temp.equals("Skip")) {
                        continue;
                    }
                    if (!String.valueOf(teaSpecifcDetails.getTeaSteepingTime()).equals(steepingTime) && !steepingTime.equals("Skip")) {
                        continue;
                    }
                }
                if (skipSugar || beverage.getDreamBeverageMenu().hasSugar() == sugarRequired) {
                    if (milkType.equals("Skip") || beverage.getDreamBeverageMenu().getMilkType().contains(milkType)) {
                        if (extras.isEmpty() || beverage.getDreamBeverageMenu().getExtras().stream().anyMatch(extras::contains)) {
                            matchingBeverages.add(beverage);
                        }
                    }
                }
            }
        }
        return matchingBeverages;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeekCafeApp app = new GeekCafeApp();
            app.setVisible(true);
        });
    }
}
