

// import java.awt.*;
// import java.io.*;
// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;

// public class EmployeeManagerCSV extends JFrame {
//     private JTextField idField, nameField, ageField, departmentField, salaryField, designationField, searchField;
//     private JTable table;
//     private DefaultTableModel tableModel;
//     private static final String FILE_NAME = "EmployeeData.csv";

//     public EmployeeManagerCSV() {
//         setTitle("Employee Manager (CSV with ID, Department, Salary)");
//         setSize(950, 500);
//         setDefaultCloseOperation(EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);

//         // Font for labels and buttons
//         Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
//         Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

//         // Panel for input fields
//         JPanel inputPanel = new JPanel(new GridBagLayout());
//         inputPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
//         inputPanel.setBackground(new Color(245, 245, 245));
//         inputPanel.setPreferredSize(new Dimension(900, 220));
//         inputPanel.setFont(labelFont);

//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(8, 10, 8, 10);
//         gbc.anchor = GridBagConstraints.WEST;

//         // Labels and fields - arranged in 3 columns x 4 rows grid style
//         String[] labels = {"ID No.:", "Name:", "Age:", "Department:", "Salary:", "Designation:"};
//         JTextField[] fields = new JTextField[6];

//         for (int i = 0; i < labels.length; i++) {
//             gbc.gridx = (i % 2) * 2;
//             gbc.gridy = i / 2;

//             JLabel lbl = new JLabel(labels[i]);
//             lbl.setFont(labelFont);
//             inputPanel.add(lbl, gbc);

//             gbc.gridx = gbc.gridx + 1;
//             JTextField tf = new JTextField(15);
//             tf.setFont(fieldFont);
//             inputPanel.add(tf, gbc);
//             fields[i] = tf;
//         }

//         idField = fields[0];
//         nameField = fields[1];
//         ageField = fields[2];
//         departmentField = fields[3];
//         salaryField = fields[4];
//         designationField = fields[5];

//         // Buttons Panel inside input panel at the bottom
//         JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//         buttonsPanel.setBackground(new Color(245, 245, 245));
//         JButton addButton = new JButton("Add Employee");
//         addButton.setFont(labelFont);
//         addButton.setBackground(new Color(76, 175, 80)); // Green
//         addButton.setForeground(Color.WHITE);

//         JButton clearButton = new JButton("Clear Fields");
//         clearButton.setFont(labelFont);
//         clearButton.setBackground(new Color(244, 67, 54)); // Red
//         clearButton.setForeground(Color.WHITE);

//         buttonsPanel.add(addButton);
//         buttonsPanel.add(clearButton);

//         gbc.gridx = 0;
//         gbc.gridy = 3;
//         gbc.gridwidth = 4;
//         inputPanel.add(buttonsPanel, gbc);

//         // Table setup with new columns
//         tableModel = new DefaultTableModel(new String[]{"ID No.", "Name", "Age", "Department", "Salary", "Designation"}, 0);
//         table = new JTable(tableModel);
//         table.setFont(fieldFont);
//         table.setRowHeight(25);
//         table.getTableHeader().setFont(labelFont);

//         // ScrollPane for table
//         JScrollPane scrollPane = new JScrollPane(table);
//         scrollPane.setPreferredSize(new Dimension(900, 180));

//         // Panel for search and delete
//         JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
//         searchPanel.setBorder(BorderFactory.createTitledBorder("Search / Delete Employee"));
//         searchPanel.setBackground(new Color(245, 245, 245));

//         JLabel searchLabel = new JLabel("Search by Name:");
//         searchLabel.setFont(labelFont);
//         searchField = new JTextField(20);
//         searchField.setFont(fieldFont);

//         JButton searchButton = new JButton("Search");
//         searchButton.setFont(labelFont);
//         searchButton.setBackground(new Color(33, 150, 243)); // Blue
//         searchButton.setForeground(Color.WHITE);

//         JButton showAllButton = new JButton("Show All");
//         showAllButton.setFont(labelFont);
//         showAllButton.setBackground(new Color(255, 152, 0)); // Orange
//         showAllButton.setForeground(Color.WHITE);

//         JButton deleteButton = new JButton("Delete Selected");
//         deleteButton.setFont(labelFont);
//         deleteButton.setBackground(new Color(244, 67, 54)); // Red
//         deleteButton.setForeground(Color.WHITE);

//         searchPanel.add(searchLabel);
//         searchPanel.add(searchField);
//         searchPanel.add(searchButton);
//         searchPanel.add(showAllButton);
//         searchPanel.add(deleteButton);

//         // Main layout
//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(new BorderLayout(15, 15));
//         mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
//         mainPanel.setBackground(new Color(230, 230, 230));

//         mainPanel.add(inputPanel, BorderLayout.NORTH);
//         mainPanel.add(scrollPane, BorderLayout.CENTER);
//         mainPanel.add(searchPanel, BorderLayout.SOUTH);

//         add(mainPanel);

//         // Load existing data
//         loadData();

//         // Button actions
//         addButton.addActionListener(e -> addEmployee());
//         clearButton.addActionListener(e -> clearFields());
//         searchButton.addActionListener(e -> searchEmployee());
//         showAllButton.addActionListener(e -> loadData());
//         deleteButton.addActionListener(e -> deleteSelectedEmployee());

//         setVisible(true);
//     }

//     private void addEmployee() {
//         String id = idField.getText().trim();
//         String name = nameField.getText().trim();
//         String age = ageField.getText().trim();
//         String department = departmentField.getText().trim();
//         String salary = salaryField.getText().trim();
//         String designation = designationField.getText().trim();

//         if (id.isEmpty() || name.isEmpty() || age.isEmpty() || department.isEmpty() || salary.isEmpty() || designation.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "Please fill all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         try {
//             int idNum = Integer.parseInt(id);
//             if (idNum <= 0) {
//                 JOptionPane.showMessageDialog(this, "ID must be a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
//                 return;
//             }
//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this, "ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
//             return;
//         }

//         try {
//             int ageNum = Integer.parseInt(age);
//             if (ageNum <= 0) {
//                 JOptionPane.showMessageDialog(this, "Age must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
//                 return;
//             }
//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this, "Age must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
//             return;
//         }

//         try {
//             double salaryNum = Double.parseDouble(salary);
//             if (salaryNum < 0) {
//                 JOptionPane.showMessageDialog(this, "Salary must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
//                 return;
//             }
//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this, "Salary must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
//             return;
//         }

//         tableModel.addRow(new Object[]{id, name, age, department, salary, designation});
//         saveData();
//         JOptionPane.showMessageDialog(this, "Employee added successfully.");
//         clearFields();
//     }

//     private void clearFields() {
//         idField.setText("");
//         nameField.setText("");
//         ageField.setText("");
//         departmentField.setText("");
//         salaryField.setText("");
//         designationField.setText("");
//     }

//     private void searchEmployee() {
//         String searchText = searchField.getText().trim().toLowerCase();
//         if (searchText.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "Enter name to search.", "Info", JOptionPane.INFORMATION_MESSAGE);
//             return;
//         }

//         DefaultTableModel searchModel = new DefaultTableModel(new String[]{"ID No.", "Name", "Age", "Department", "Salary", "Designation"}, 0);

//         for (int i = 0; i < tableModel.getRowCount(); i++) {
//             String name = tableModel.getValueAt(i, 1).toString().toLowerCase();
//             if (name.contains(searchText)) {
//                 Object[] row = {
//                         tableModel.getValueAt(i, 0),
//                         tableModel.getValueAt(i, 1),
//                         tableModel.getValueAt(i, 2),
//                         tableModel.getValueAt(i, 3),
//                         tableModel.getValueAt(i, 4),
//                         tableModel.getValueAt(i, 5)
//                 };
//                 searchModel.addRow(row);
//             }
//         }

//         table.setModel(searchModel);
//     }

//     private void deleteSelectedEmployee() {
//         int selectedRow = table.getSelectedRow();
//         if (selectedRow == -1) {
//             JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         int confirm = JOptionPane.showConfirmDialog(this,
//                 "Are you sure you want to delete selected employee?", "Confirm Delete",
//                 JOptionPane.YES_NO_OPTION);

//         if (confirm == JOptionPane.YES_OPTION) {
//             ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
//             saveData();
//             JOptionPane.showMessageDialog(this, "Employee deleted.");
//         }
//     }

//     private void saveData() {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
//             writer.write("ID No.,Name,Age,Department,Salary,Designation");
//             writer.newLine();

//             DefaultTableModel model = (DefaultTableModel) table.getModel();
//             for (int i = 0; i < model.getRowCount(); i++) {
//                 String id = model.getValueAt(i, 0).toString();
//                 String name = model.getValueAt(i, 1).toString();
//                 String age = model.getValueAt(i, 2).toString();
//                 String department = model.getValueAt(i, 3).toString();
//                 String salary = model.getValueAt(i, 4).toString();
//                 String designation = model.getValueAt(i, 5).toString();
//                 writer.write(id + "," + name + "," + age + "," + department + "," + salary + "," + designation);
//                 writer.newLine();
//             }
//         } catch (IOException e) {
//             JOptionPane.showMessageDialog(this, "Error saving data to file.", "Error", JOptionPane.ERROR_MESSAGE);
//         }
//     }

//     private void loadData() {
//         tableModel.setRowCount(0); // Clear existing rows

//         File file = new File(FILE_NAME);
//         if (!file.exists()) {
//             try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//                 writer.write("ID No.,Name,Age,Department,Salary,Designation");
//                 writer.newLine();
//             } catch (IOException e) {
//                 JOptionPane.showMessageDialog(this, "Error creating data file.", "Error", JOptionPane.ERROR_MESSAGE);
//             }
//             return;
//         }

//         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//             String line;
//             reader.readLine(); // Skip header line
//             while ((line = reader.readLine()) != null) {
//                 String[] parts = line.split(",");
//                 if (parts.length == 6) {
//                     tableModel.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]});
//                 }
//             }
//             table.setModel(tableModel);
//         } catch (IOException e) {
//             JOptionPane.showMessageDialog(this, "Error loading data from file.", "Error", JOptionPane.ERROR_MESSAGE);
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(EmployeeManagerCSV::new);
//     }
// }



import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class EmployeeManagerCSV extends JFrame {
    private JTextField idField, nameField, ageField, departmentField, salaryField, designationField, searchField;
    private JTable table;
    private DefaultTableModel tableModel;
    private static final String FILE_NAME = "EmployeeData.csv";

    // Hardcoded login credentials for demo
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    public EmployeeManagerCSV() {
        // Call login first
        boolean loggedIn = showLoginDialog();
        if (!loggedIn) {
            // Exit if login failed or cancelled
            System.exit(0);
        }

        // After login success, initialize main UI
        setTitle("Employee Manager (CSV with ID, Department, Salary)");
        setSize(950, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Font for labels and buttons
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.setPreferredSize(new Dimension(900, 220));
        inputPanel.setFont(labelFont);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels and fields - arranged in 3 columns x 4 rows grid style
        String[] labels = {"ID No.:", "Name:", "Age:", "Department:", "Salary:", "Designation:"};
        JTextField[] fields = new JTextField[6];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = (i % 2) * 2;
            gbc.gridy = i / 2;

            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(labelFont);
            inputPanel.add(lbl, gbc);

            gbc.gridx = gbc.gridx + 1;
            JTextField tf = new JTextField(15);
            tf.setFont(fieldFont);
            inputPanel.add(tf, gbc);
            fields[i] = tf;
        }

        idField = fields[0];
        nameField = fields[1];
        ageField = fields[2];
        departmentField = fields[3];
        salaryField = fields[4];
        designationField = fields[5];

        // Buttons Panel inside input panel at the bottom
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(new Color(245, 245, 245));
        JButton addButton = new JButton("Add Employee");
        addButton.setFont(labelFont);
        addButton.setBackground(new Color(76, 175, 80)); // Green
        addButton.setForeground(Color.WHITE);

        JButton clearButton = new JButton("Clear Fields");
        clearButton.setFont(labelFont);
        clearButton.setBackground(new Color(244, 67, 54)); // Red
        clearButton.setForeground(Color.WHITE);

        buttonsPanel.add(addButton);
        buttonsPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        inputPanel.add(buttonsPanel, gbc);

        // Table setup with new columns
        tableModel = new DefaultTableModel(new String[]{"ID No.", "Name", "Age", "Department", "Salary", "Designation"}, 0);
        table = new JTable(tableModel);
        table.setFont(fieldFont);
        table.setRowHeight(25);
        table.getTableHeader().setFont(labelFont);

        // ScrollPane for table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 180));

        // Panel for search and delete
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search / Delete Employee"));
        searchPanel.setBackground(new Color(245, 245, 245));

        JLabel searchLabel = new JLabel("Search by Name:");
        searchLabel.setFont(labelFont);
        searchField = new JTextField(20);
        searchField.setFont(fieldFont);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(labelFont);
        searchButton.setBackground(new Color(33, 150, 243)); // Blue
        searchButton.setForeground(Color.WHITE);

        JButton showAllButton = new JButton("Show All");
        showAllButton.setFont(labelFont);
        showAllButton.setBackground(new Color(255, 152, 0)); // Orange
        showAllButton.setForeground(Color.WHITE);

        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.setFont(labelFont);
        deleteButton.setBackground(new Color(244, 67, 54)); // Red
        deleteButton.setForeground(Color.WHITE);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);
        searchPanel.add(deleteButton);

        // Main layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(230, 230, 230));

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(searchPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Load existing data
        loadData();

        // Button actions
        addButton.addActionListener(e -> addEmployee());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchEmployee());
        showAllButton.addActionListener(e -> loadData());
        deleteButton.addActionListener(e -> deleteSelectedEmployee());

        setVisible(true);
    }

    private boolean showLoginDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());

            if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                return true; // Login successful
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                return showLoginDialog(); // Retry login
            }
        } else {
            return false; // Cancelled
        }
    }

    private void addEmployee() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String department = departmentField.getText().trim();
        String salary = salaryField.getText().trim();
        String designation = designationField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || age.isEmpty() || department.isEmpty() || salary.isEmpty() || designation.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idNum = Integer.parseInt(id);
            if (idNum <= 0) {
                JOptionPane.showMessageDialog(this, "ID must be a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int ageNum = Integer.parseInt(age);
            if (ageNum <= 0) {
                JOptionPane.showMessageDialog(this, "Age must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double salaryNum = Double.parseDouble(salary);
            if (salaryNum < 0) {
                JOptionPane.showMessageDialog(this, "Salary must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Salary must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{id, name, age, department, salary, designation});
        saveData();
        JOptionPane.showMessageDialog(this, "Employee added successfully.");
        clearFields();
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        departmentField.setText("");
        salaryField.setText("");
        designationField.setText("");
    }

    private void searchEmployee() {
        String searchText = searchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter name to search.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultTableModel searchModel = new DefaultTableModel(new String[]{"ID No.", "Name", "Age", "Department", "Salary", "Designation"}, 0);

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String name = tableModel.getValueAt(i, 1).toString().toLowerCase();
            if (name.contains(searchText)) {
                Object[] row = {
                        tableModel.getValueAt(i, 0),
                        tableModel.getValueAt(i, 1),
                        tableModel.getValueAt(i, 2),
                        tableModel.getValueAt(i, 3),
                        tableModel.getValueAt(i, 4),
                        tableModel.getValueAt(i, 5)
                };
                searchModel.addRow(row);
            }
        }

        table.setModel(searchModel);
    }

    private void deleteSelectedEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete selected employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(selectedRow);
                saveData();
                JOptionPane.showMessageDialog(this, "Employee deleted.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    sb.append(tableModel.getValueAt(i, j));
                    if (j != tableModel.getColumnCount() - 1) {
                        sb.append(",");
                    }
                }
                pw.println(sb.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    tableModel.addRow(parts);
                }
            }
            table.setModel(tableModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeManagerCSV::new);
    }
}

