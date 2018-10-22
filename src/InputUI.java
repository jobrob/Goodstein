import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InputUI extends JFrame
{
	private final String BUTTON_TEXT = "Calculate";
	public InputUI()
	{
		initComponents();
	}
	
	public void initComponents()
	{
		final String[][] fields =
		{
				{"Number:", "13"},
				{"Base:", "2"},
		};
		
		final JTextField[] textFields = new JTextField[fields.length];
		
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		for (int i = 0; i <fields.length; i++)
		{
			c.gridx = 0;
			c.gridy = i;
			c.gridwidth = 1;
			JLabel label = new JLabel(fields[i][0], JLabel.TRAILING);
			p.add(label, c);
			
			textFields[i] = new JTextField();
			textFields[i].setText(fields[i][1]);
			c.gridx = 2;
			c.gridy = i;
			c.gridwidth = 2;
			p.add(textFields[i], c);
		}
		c.gridx = 0;
		c.gridy = fields.length+1;
		JLabel results = new JLabel("Result");
		p.add(results,c);
		
		c.gridx = 2;
		c.gridy = fields.length +1;
		JLabel answer = new JLabel("" + 0);
		p.add(answer,c);
		
		JButton button = new JButton(BUTTON_TEXT);
		c.gridx = 3;
		c.gridy = fields.length+2;
		c.gridheight = 2;
		
		button.addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent actionEvent)
					{
						button.setText("Working...");
						button.setEnabled(false);
						button.repaint();
						button.revalidate();
						
						Thread t = new Thread()
						{
							public void run()
							{
								try
								{
									int x = Goodstien.nextTerm(Integer.valueOf(textFields[0].getText()),Integer.valueOf(textFields[1].getText()));
									answer.setText("" + x);
									textFields[0].setText("" + x);
									textFields[1].setText("" + (Integer.valueOf(textFields[1].getText()) +1 ));
									button.setText(BUTTON_TEXT);
									button.setEnabled(true);
								}
								catch(Exception ex)
								{
									Goodstien.baseN(416,3);
									button.setText(BUTTON_TEXT);
									button.setEnabled(true);
								}
							}
						};
						t.start();
					}
				}
		);
		p.add(button, c);
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(p);
		frame.pack();
		frame.setVisible(true);
	}
}
