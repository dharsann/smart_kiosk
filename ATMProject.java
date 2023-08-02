import javafx.application.Application;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.logging.Logger;
import java.sql.*;



public class ATMProject extends Application {
    private static final Logger LOGGER = Logger.getLogger(ATMProject.class.getName());
    private Map<String, Integer> accounts;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Starting ATM simulator");
        accounts = new HashMap<>();
        accounts.put("1234", 1000);
        accounts.put("5678", 2000);
        accounts.put("9012", 3000);

        primaryStage.setTitle("ATM Simulator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label accountLabel = new Label("Enter account number:");
        grid.add(accountLabel, 0, 1);

        TextField accountTextField = new TextField();
        grid.add(accountTextField, 1, 1);

        Label pinLabel = new Label("Enter PIN:");
        grid.add(pinLabel, 0, 2);

        TextField pinTextField = new TextField();
        grid.add(pinTextField, 1, 2);

        Button loginButton = new Button("Log in");
        grid.add(loginButton, 1, 3);
        loginButton.setOnAction(event -> {
            String accountNumber = accountTextField.getText();
            String pin = pinTextField.getText();

            // Check if the account exists and the PIN is correct
            if (accounts.equals(accountNumber) && pin.equals("1234")) {
                openMainMenu(accountNumber);
            } else {
                Label errorLabel = new Label("Invalid account number or PIN");
                grid.add(errorLabel, 1, 4);
            }
        });


        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openMainMenu(String accountNumber) {
        // Create a new stage for the main menu
        Stage mainMenuStage = new Stage();
        mainMenuStage.setTitle("ATM Simulator - Main Menu");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label balanceLabel = new Label("Balance: $" + accounts.get(accountNumber));
        grid.add(balanceLabel, 0, 0);

        Button withdrawButton = new Button("Withdraw");
        grid.add(withdrawButton, 0, 1);

        Button depositButton = new Button("Deposit");
        grid.add(depositButton, 1, 1);

        Button exitButton = new Button("Exit");
        grid.add(exitButton, 1, 2);

        // Event handlers for the buttons
        withdrawButton.setOnAction(event -> {
            openWithdrawMenu(accountNumber);
        });

        depositButton.setOnAction(event -> {
            openDepositMenu(accountNumber);
        });

        exitButton.setOnAction(event -> {
            mainMenuStage.close();
        });
        Label amountLabel = new Label("Enter amount:");
        grid.add(amountLabel, 0, 0);

        TextField amountTextField = new TextField();
        grid.add(amountTextField, 1, 0);

        withdrawButton = new Button("Withdraw");
        grid.add(withdrawButton, 0, 1);

        Button cancelButton = new Button("Cancel");
        grid.add(cancelButton, 1, 1);
        Button loginButton = new Button();
        grid.add(loginButton, 1, 2);


// Event handlers for the buttons


        Stage WithdrawMenuStage = new Stage();
        withdrawButton.setOnAction(event -> {
            String amountText = amountTextField.getText();
            int amount = Integer.parseInt(amountText);
            if (amount > 0 && amount <= accounts.get(accountNumber)) {
                accounts.put(accountNumber, accounts.get(accountNumber) - amount);
                WithdrawMenuStage.close();
                openMainMenu(accountNumber);
            } else {
                Label errorLabel = new Label("Invalid amount");
                grid.add(errorLabel, 1, 2);
            }
        });

        cancelButton.setOnAction(event -> {
            WithdrawMenuStage.close();
        });


    }

    private void openDepositMenu(String accountNumber) {
        // Create a new stage for the deposit menu
        Stage depositMenuStage = new Stage();
        depositMenuStage.setTitle("ATM Simulator - Deposit Menu");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label amountLabel = new Label("Enter amount:");
        grid.add(amountLabel, 0, 0);

        TextField amountTextField = new TextField();
        grid.add(amountTextField, 1, 0);

        Button depositButton = new Button("Deposit");
        grid.add(depositButton, 0, 1);

        Button cancelButton = new Button("Cancel");
        grid.add(cancelButton, 1, 1);

        // Event handlers for the buttons
        depositButton.setOnAction(event -> {
            String amountText = amountTextField.getText();
            int amount = Integer.parseInt(amountText);
            if (amount > 0) {
                accounts.put(accountNumber, accounts.get(accountNumber) + amount);
                depositMenuStage.close();
                openMainMenu(accountNumber);
            } else {
                Label errorLabel = new Label("Invalid amount");
                grid.add(errorLabel, 1, 2);
            }
        });

        cancelButton.setOnAction(event -> {
            depositMenuStage.close();
        });

        Scene scene = new Scene(grid, 400, 275);
        depositMenuStage.setScene(scene);
        depositMenuStage.show();
    }

    private void openWithdrawMenu(String accountNumber) {
        // Create a new stage for the withdraw menu
        Stage withdrawMenuStage = new Stage();
        withdrawMenuStage.setTitle("ATM Simulator - Withdraw Menu");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label amountLabel = new Label("Enter amount:");
        grid.add(amountLabel, 0, 0);

        TextField amountTextField = new TextField();
        grid.add(amountTextField, 1, 0);

        Button withdrawButton = new Button("Withdraw");
        grid.add(withdrawButton, 0, 1);

        Button cancelButton = new Button("Cancel");
        grid.add(cancelButton, 1, 1);

        // Event handlers for the buttons
        withdrawButton.setOnAction(event -> {
            String amountText = amountTextField.getText();
            int amount = Integer.parseInt(amountText);
            if (amount > 0 && amount <= accounts.get(accountNumber)) {
                accounts.put(accountNumber, accounts.get(accountNumber) - amount);
                withdrawMenuStage.close();
                openMainMenu(accountNumber);
            } else {
                Label errorLabel = new Label("Invalid amount");
                grid.add(errorLabel, 1, 2);
            }
        });

        cancelButton.setOnAction(event -> {
            withdrawMenuStage.close();
        });

        Scene scene = new Scene(grid, 400, 275);
        withdrawMenuStage.setScene(scene);
        withdrawMenuStage.show();
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/mydatabase";

        // Database credentials
        String USER = "username";
        String PASS = "password";

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare statement to insert data
            String sql = "INSERT INTO employees (id, name, age, email) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Set values for parameters
            stmt.setInt(1, 123);
            stmt.setString(2, "John Smith");
            stmt.setInt(3, 30);
            stmt.setString(4, "john.smith@example.com");

            // Execute the insert statement
            stmt.executeUpdate();
            System.out.println("Data inserted successfully.");

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
    }
}
