package semesterproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryManagementGUI extends JFrame {
    
    private InventoryStack inventoryStack;
    private JList<String> itemList;           // Items display karne ke liye
    private DefaultListModel<String> listModel;  // JList ko control karne ke liye
    
    private JTextField txtItemName;     // Naam likhne ka box
    private JTextField txtQuantity;     // Quantity ka box
    private JTextField txtPrice;        // Price ka box
    
    private JButton btnAddItem;
    private JButton btnRemoveItem;
    private JButton btnViewTop;
    
    private JLabel lblStatus;
    
    public InventoryManagementGUI() {              // constructor
        inventoryStack = new InventoryStack();    // Stack banao
        
        setTitle("Inventory Management System");     //window ka title
        setSize(700, 500);                        // Window ka size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        // Create panels
        JPanel topPanel = createTopPanel();       //panels bnao
        JPanel centerPanel = createCenterPanel();
        JPanel bottomPanel = createBottomPanel();
        
        add(topPanel, BorderLayout.NORTH);     //panels add kro window ma  // Input boxes  (naam, quantity, price)
        add(centerPanel, BorderLayout.CENTER);   // Items list
        add(bottomPanel, BorderLayout.SOUTH);    // Buttons  push,pop
        
        setVisible(true);                        //window dikhao
    }
    
    private JPanel createTopPanel(){            
        JPanel panel = new JPanel();          //naya container bnaya
        panel.setLayout(new GridLayout(4, 2, 10, 10));    //4 rows 2 columns
        panel.setBorder(BorderFactory.createTitledBorder("Add New Item"));
        ((javax.swing.border.TitledBorder) panel.getBorder())
        .setTitleColor(new Color(230, 240, 255));
        ((javax.swing.border.TitledBorder) panel.getBorder())
        .setTitleFont(new Font("Arial", Font.BOLD, 16));

        panel.setBackground(new Color(41, 128, 185));
        
        
        JLabel lblName = new JLabel("Item Name:");   // jlabel means text display only
        txtItemName = new JTextField();              // jtextfield user type krskta ha
        
        
        JLabel lblQty = new JLabel("Quantity:");
        txtQuantity = new JTextField();
        
        JLabel lblPrice = new JLabel("Price:");
        txtPrice = new JTextField();
        
        lblName.setForeground(Color.WHITE);
        lblQty.setForeground(Color.WHITE);
        lblPrice.setForeground(Color.WHITE);
        
        btnAddItem = new JButton("Push Item (Add to Stack)");
        btnAddItem.setForeground(Color.WHITE);
        btnAddItem.setBackground(new Color(100, 200, 100));
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   //btn click hony pr kia krna ha btata ha
                addItemToStack();
            }
        });
        
        panel.add(lblName);
        panel.add(txtItemName);
        panel.add(lblQty);
        panel.add(txtQuantity);
        panel.add(lblPrice);
        panel.add(txtPrice);
        panel.add(new JLabel());
        panel.add(btnAddItem);
        
        return panel;
    }
    
    private JPanel createCenterPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Current Inventory Stack"));
        ((javax.swing.border.TitledBorder) panel.getBorder())
        .setTitleColor(new Color(41, 128, 185));
        ((javax.swing.border.TitledBorder) panel.getBorder())
        .setTitleFont(new Font("Arial", Font.BOLD, 14));
        panel.setBackground(new Color(230, 240, 255));
        
        listModel = new DefaultListModel<>();   //data store krta ha
        itemList = new JList<>(listModel);      //jlist scrn pr dikhata ha
        itemList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    //user srf ak item select krskta multiple nae
        
        JScrollPane scrollPane = new JScrollPane(itemList);    //scroll bar
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createBottomPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));   //items ko left to right rkho jese line ma khade ho
        panel.setBackground(new Color(230, 240, 255));
        
        btnRemoveItem = new JButton("Pop Item (Remove from Stack)");
        btnRemoveItem.setForeground(Color.WHITE);
        btnRemoveItem.setBackground(new Color(231, 76, 60));
        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItemFromStack();
            }
        });
        
        btnViewTop = new JButton("View Top Item (Peek)");
        btnViewTop.setForeground(Color.WHITE);
        btnViewTop.setBackground(new Color(41, 128, 185));
        btnViewTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTopItem();
            }
        });
        
        lblStatus = new JLabel("Status: Ready");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 12));
        
        panel.add(btnRemoveItem);
        panel.add(btnViewTop);
        panel.add(lblStatus);
        
        return panel;
    }
    
    private void addItemToStack(){
        try{
            String itemName = txtItemName.getText().trim();   //input boxes se values lo
            String qtyText = txtQuantity.getText().trim();
            String priceText = txtPrice.getText().trim();
            
            if(itemName.isEmpty() || qtyText.isEmpty() || priceText.isEmpty()){
                JOptionPane.showMessageDialog(this, 
                    "Please fill all fields!", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
                                //string ko int/double ma convert kro
            int quantity = Integer.parseInt(qtyText);
            double price = Double.parseDouble(priceText);
            
            if(quantity <= 0 || price <= 0){
                JOptionPane.showMessageDialog(this, 
                    "Quantity and Price must be positive!", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Item newItem = new Item(itemName, quantity, price);  //item obj bnao
            inventoryStack.push(newItem);                        //stack m push kro
            
            updateListDisplay();    //list refresh kro
            
            txtItemName.setText("");   //boxes khali kro
            txtQuantity.setText("");
            txtPrice.setText("");
            txtItemName.requestFocus();
            
            lblStatus.setText("Status: Item Added Successfully! Total Items: " + inventoryStack.getSize());
            lblStatus.setForeground(new Color(0, 150, 0));
            
        }catch(NumberFormatException ex){    //agr letters dale number ki jga
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers for Quantity and Price!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removeItemFromStack(){
        if(inventoryStack.isEmpty()){
            JOptionPane.showMessageDialog(this, 
                "Stack is Empty! No items to remove.", 
                "Stack Underflow", 
                JOptionPane.WARNING_MESSAGE);
            lblStatus.setText("Status: Stack Underflow - No items to remove");
            lblStatus.setForeground(Color.RED);
            return;
        }
        
        Item removedItem = inventoryStack.pop();  //pop kro item
        updateListDisplay();
        
        JOptionPane.showMessageDialog(this, 
            "Item Removed:\n" + removedItem.toString(), 
            "Item Removed", 
            JOptionPane.INFORMATION_MESSAGE);
        
        lblStatus.setText("Status: Item Removed! Remaining Items: " + inventoryStack.getSize());
        lblStatus.setForeground(new Color(200, 100, 0));
    }
    
    private void viewTopItem(){
        if(inventoryStack.isEmpty()){
            JOptionPane.showMessageDialog(this, 
                "Stack is Empty! No items to view.", 
                "Stack Empty", 
                JOptionPane.WARNING_MESSAGE);
            lblStatus.setText("Status: Stack is Empty");
            lblStatus.setForeground(Color.RED);
            return;
        }
        
        Item topItem = inventoryStack.peek();
        JOptionPane.showMessageDialog(this, 
            "Top Item:\n" + topItem.toString(), 
            "View Top Item", 
            JOptionPane.INFORMATION_MESSAGE);
        
        lblStatus.setText("Status: Viewing Top Item");
        lblStatus.setForeground(new Color(0, 100, 200));
    }
    
    private void updateListDisplay(){
        listModel.clear();                  // Purani list clear karo
        String[] items = inventoryStack.getAllItems();    // Stack se items lo
        
        if(items.length == 0){
            listModel.addElement("--- Stack is Empty ---");
        }else{
            listModel.addElement("=== TOP OF STACK ===");
            for(int i = 0; i < items.length; i++){
                listModel.addElement((i+1) + ". " + items[i]);
            }
            listModel.addElement("=== BOTTOM OF STACK ===");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InventoryManagementGUI();
            }
        });
    }
}