import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {

    JTextArea textArea;
    JFileChooser fileChooser;
    File currentFile;

    public Notepad() {
        // Frame settings
        setTitle("Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Text Area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        String[] fileItems = {"New", "Open", "Save", "Save As", "Exit"};

        for (String item : fileItems) {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(this);
            fileMenu.add(menuItem);
        }

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        String[] editItems = {"Cut", "Copy", "Paste", "Select All"};

        for (String item : editItems) {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(this);
            editMenu.add(menuItem);
        }

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(this);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {

            case "New":
                textArea.setText("");
                currentFile = null;
                break;

            case "Open":
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileChooser.getSelectedFile();
                    openFile(currentFile);
                }
                break;

            case "Save":
                if (currentFile != null) {
                    saveFile(currentFile);
                } else {
                    saveAsFile();
                }
                break;

            case "Save As":
                saveAsFile();
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Cut":
                textArea.cut();
                break;

            case "Copy":
                textArea.copy();
                break;

            case "Paste":
                textArea.paste();
                break;

            case "Select All":
                textArea.selectAll();
                break;

            case "About":
                JOptionPane.showMessageDialog(this,
                        "Simple Notepad\nCreated using Java Swing",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    private void openFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            textArea.read(br, null);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error opening file");
        }
    }

    private void saveFile(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            textArea.write(bw);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file");
        }
    }

    private void saveAsFile() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            saveFile(currentFile);
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}