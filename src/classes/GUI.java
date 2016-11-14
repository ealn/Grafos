package classes;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame 
{
    /** Constants **/
    private static final long serialVersionUID = 1L;
    private static final int  SUCCESS = 0;
    private static final int  FAIL = -1;

    /** UI Components **/
    private JPanel         mainPanel;
    private JTabbedPane    mainTabPanel;
    
    /** UI tab **/
    private JPanel         panelGraphs; 
    private JTextField     citiesTextField;    
    private JLabel         map;
    private JTextField     outputText;
    private JLabel         city1Label;
    private JTextField     city1Text;
    private JLabel         city2Label;
    private JTextField     city2Text;
    private JButton        MSTButton;
    private JButton        SPAButton;
    private JLabel         algorithmLabel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the UI.
     */
    public GUI() 
    {
        setResizable(false);
        setTitle("Grafos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 610, 601);
        
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(mainPanel);
        
        mainTabPanel = new JTabbedPane(JTabbedPane.TOP);
        mainTabPanel.setBackground(Color.WHITE);
        mainPanel.add(mainTabPanel, BorderLayout.CENTER);
        
        createTab();
    }
    
    /** UI tab **/
    private void createTab()
    {
        panelGraphs = new JPanel();
        panelGraphs.setBackground(Color.WHITE);
        mainTabPanel.addTab("Grafos", null, panelGraphs, null);
        panelGraphs.setLayout(null);
        
        map = new JLabel("");
        map.setBounds(10, 10, 440, 267);
        map.setIcon(new ImageIcon(GUI.class.getResource("/images/mapa.png")));
        panelGraphs.add(map);
        
        citiesTextField = new JTextField();
        citiesTextField.setEditable(false);
        citiesTextField.setBounds(81, 299, 468, 169);
        panelGraphs.add(citiesTextField);
        citiesTextField.setColumns(10);
        
        MSTButton = new JButton("MST");
        MSTButton.setBounds(460, 29, 119, 23);
        panelGraphs.add(MSTButton);
        
        SPAButton = new JButton("SPA");
        SPAButton.setBounds(460, 72, 119, 23);
        panelGraphs.add(SPAButton);
        
        JLabel citiesLabel = new JLabel("Ciudades");
        citiesLabel.setBounds(10, 362, 64, 14);
        panelGraphs.add(citiesLabel);
        
        JLabel outputLabel = new JLabel("Resultado");
        outputLabel.setBounds(10, 479, 64, 14);
        panelGraphs.add(outputLabel);
        
        outputText = new JTextField();
        outputText.setEditable(false);
        outputText.setBounds(81, 479, 468, 44);
        panelGraphs.add(outputText);
        outputText.setColumns(10);
        
        city1Label = new JLabel("Ciudad #1:");
        city1Label.setBounds(460, 106, 119, 14);
        panelGraphs.add(city1Label);
        
        city1Text = new JTextField();
        city1Text.setBounds(460, 131, 119, 20);
        panelGraphs.add(city1Text);
        city1Text.setColumns(10);
        
        city2Label = new JLabel("Ciudad #2");
        city2Label.setBounds(460, 162, 89, 14);
        panelGraphs.add(city2Label);
        
        city2Text = new JTextField();
        city2Text.setBounds(460, 187, 119, 20);
        panelGraphs.add(city2Text);
        city2Text.setColumns(10);
        
        algorithmLabel = new JLabel("");
        algorithmLabel.setBounds(10, 504, 64, 14);
        panelGraphs.add(algorithmLabel);
        
        createActions();
    }
    /** Validates **/
    private int validateMST()
    {
        int ret = SUCCESS;
        
        return ret;
    }
    
    private int validateSPA()
    {
        int ret = SUCCESS;
        
        if (city1Text.getText().length() == 0)
        {
            //Show error message
            JOptionPane.showMessageDialog(null,
                                          "Por favor escriba la ciudad #1",
                                          "Error", 
                                          JOptionPane.ERROR_MESSAGE);
            ret = FAIL;
        }
        else if (city2Text.getText().length() == 0)
        {
            //Show error message
            JOptionPane.showMessageDialog(null,
                                          "Por favor escriba la ciudad #2",
                                          "Error", 
                                          JOptionPane.ERROR_MESSAGE);
            
            ret = FAIL;
        }
        
        return ret;
    }
    
    private void createActions()
    {                        
        MSTButton.addActionListener(new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
              if (validateMST() == SUCCESS)
              {
                  algorithmLabel.setText("del MST");
                  outputText.setText(MST.run());
              }
          }
        });
        
        SPAButton.addActionListener(new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
              if (validateSPA() == SUCCESS)
              {
                  algorithmLabel.setText("del SPA");
                  outputText.setText(SPA.run());
              }
          }
        });
    }
}
