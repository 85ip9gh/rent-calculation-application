# rent-calculation-application

## NOTE

- remember to always ask chatgpt for help
    - Centering JFrame code:
        ```
        import java.awt.Dimension;
        import java.awt.Toolkit;

        public class YourJFrame extends javax.swing.JFrame {

            public YourJFrame() {
                initComponents();
                centerFrameOnScreen();
            }

            private void centerFrameOnScreen() {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension frameSize = getSize();

                int x = (screenSize.width - frameSize.width) / 2;
                int y = (screenSize.height - frameSize.height) / 2;

                setLocation(x, y);
            }

            // ... rest of your code

            public static void main(String args[]) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new YourJFrame().setVisible(true);
                    }
                });
            }
        }

        ```

## 12/26/2023
- create table tenants(
    tenantID int NOT NULL AUTO_INCREMENT,
    roomNumber INT,
    name VARCHAR(255),
    address VARCHAR(255),
    rent DOUBLE,
    advance DOUBLE,
    dateRented DATE, 
    dateVacated DATE, 
    currentlyRented BOOLEAN,
    agreementValid BOOLEAN,
    dues DOUBLE,
    notes VARCHAR(255)
)
- insert into tenants 
(roomNumber, name, address, rent, advance, dateRented, dateVacated, currentlyRented, agreementValid, dues, notes)
values(
    12,
    'Sam Man',
    'Random Address',
    12000,
    120,
    '2023-02-14', 
    '2023-03-14', 
    0,
    1,
    0,
    'none'
)