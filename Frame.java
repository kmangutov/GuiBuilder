public class Frame implements java.awt.event.ActionListener{
	public static void main(String[] args){new Frame();}
	public Frame(){
		javax.swing.JFrame frame = new javax.swing.JFrame();
		javax.swing.JPanel panel = new javax.swing.JPanel();
		frame.add(panel);
		panel.setLayout(null);
		frame.setSize(500, 500);
		frame.setResizable(false);

		Button1 = new javax.swing.JButton();
		Button1.setBounds(260,340,100,32);
		Button1.setText("text");
		Button1.addActionListener(this);

		Button2 = new javax.swing.JButton();
		Button2.setBounds(280,40,100,32);
		Button2.setText("text");
		Button2.addActionListener(this);

		Button3 = new javax.swing.JButton();
		Button3.setBounds(20,220,100,32);
		Button3.setText("text");
		Button3.addActionListener(this);



		panel.add(Button1);
		panel.add(Button2);
		panel.add(Button3);
		frame.show();
	}
	public void actionPerformed(java.awt.event.ActionEvent e){javax.swing.JOptionPane.showMessageDialog(null, e.toString());}

		javax.swing.JButton Button1;

		javax.swing.JButton Button2;

		javax.swing.JButton Button3;

}
