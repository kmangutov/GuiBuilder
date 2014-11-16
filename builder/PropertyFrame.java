package builder;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * This class was made with the guibuilder haha
 */
public class PropertyFrame implements java.awt.event.ActionListener {
    public static void main(String[] args) {
        new PropertyFrame(null);
    }

    private ComponentContainer container;
    public TreeDisplay parent;

    public void associate(ComponentContainer cc) {
        container = cc;

        Textfield3.setText(cc.getName());
        Textfield1.setText(cc.component.getX() + "");
        Textfield2.setText(cc.component.getY() + "");
        Textfield4.setText(cc.component.getWidth() + "");
        Textfield5.setText(cc.component.getHeight() + "");
    }

    javax.swing.JFrame frame;

    public PropertyFrame(TreeDisplay parent) {

        this.parent = parent;
        frame = new javax.swing.JFrame();
        javax.swing.JPanel panel = new javax.swing.JPanel();
        frame.add(panel);
        panel.setLayout(null);

        frame.setSize(404, 340);
        frame.setResizable(false);

        Button1 = new javax.swing.JButton();
        Button1.setBounds(160, 260, 100, 32);
        Button1.setText("Cancel");
        Button1.addActionListener(this);

        Button2 = new javax.swing.JButton();
        Button2.setBounds(280, 260, 100, 32);
        Button2.setText("Submit");
        Button2.addActionListener(this);

        Label1 = new javax.swing.JLabel();
        Label1.setBounds(40, 20, 100, 32);
        Label1.setText("Name");


        Label2 = new javax.swing.JLabel();
        Label2.setBounds(40, 60, 100, 32);
        Label2.setText("X");

        Label3 = new javax.swing.JLabel();
        Label3.setBounds(40, 100, 100, 32);
        Label3.setText("Y");

        Textfield1 = new javax.swing.JTextField();
        Textfield1.setBounds(80, 60, 100, 32);

        Textfield2 = new javax.swing.JTextField();
        Textfield2.setBounds(80, 100, 100, 32);

        Textfield3 = new javax.swing.JTextField();
        Textfield3.setBounds(240, 20, 100, 32);

        Label4 = new javax.swing.JLabel();
        Label4.setBounds(200, 60, 100, 32);
        Label4.setText("W");

        Label5 = new javax.swing.JLabel();
        Label5.setBounds(200, 100, 100, 32);
        Label5.setText("H");

        Textfield4 = new javax.swing.JTextField();
        Textfield4.setBounds(240, 60, 100, 32);

        Textfield5 = new javax.swing.JTextField();
        Textfield5.setBounds(240, 100, 100, 32);


        panel.add(Button1);
        panel.add(Button2);
        panel.add(Label1);
        panel.add(Label2);
        panel.add(Label3);
        panel.add(Textfield1);
        panel.add(Textfield2);
        panel.add(Textfield3);
        panel.add(Label4);
        panel.add(Label5);
        panel.add(Textfield4);
        panel.add(Textfield5);
        frame.show();
    }

    public int atoi(String s) {
        return Integer.parseInt(s);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {

        JButton source = (JButton) e.getSource();
        if (source.getText().equals("Cancel"))
            frame.hide();
        else {

            try {

                container.setName(Textfield3.getText());
                container.component.setBounds(atoi(Textfield1.getText()),
                        atoi(Textfield2.getText()),
                        atoi(Textfield4.getText()),
                        atoi(Textfield5.getText()));

                frame.hide();
                parent.forceUpdate();

            } catch (Exception ee) {
                ee.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error!");
            }
        }
    }

    javax.swing.JButton Button1;

    javax.swing.JButton Button2;

    javax.swing.JLabel Label1;

    javax.swing.JLabel Label2;

    javax.swing.JLabel Label3;

    javax.swing.JTextField Textfield1;

    javax.swing.JTextField Textfield2;

    javax.swing.JTextField Textfield3;

    javax.swing.JLabel Label4;

    javax.swing.JLabel Label5;

    javax.swing.JTextField Textfield4;

    javax.swing.JTextField Textfield5;

}
