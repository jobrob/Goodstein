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
		final String[][] inputs =
		{
				{"Number:", "13"},
				{"Base:", "2"},
		};
		final String[] results = {"Base notation:" , "Next Term:"};
		
		final JTextField[] inputsFields = new JTextField[inputs.length];
		
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		for (int i = 0; i <inputs.length; i++)
		{
			c.gridx = 0;
			c.gridy = i;
			c.gridwidth = 1;
			JLabel label = new JLabel(inputs[i][0], JLabel.TRAILING);
			p.add(label, c);
			
			inputsFields[i] = new JTextField();
			inputsFields[i].setText(inputs[i][1]);
			c.gridx = 2;
			c.gridy = i;
			c.gridwidth = 2;
			p.add(inputsFields[i], c);
		}
		c.gridx = 0;
		c.gridy = inputs.length+1;
		JLabel baseNotation = new JLabel(results[0], JLabel.TRAILING);
		p.add(baseNotation,c);
		
		c.gridy = inputs.length+2;
		JLabel nextTerm = new JLabel(results[1], JLabel.TRAILING);
		p.add(nextTerm,c);
		
		
		c.gridx = 2;
		c.gridy = inputs.length +1;
		JLabel baseFormLabel = new JLabel("" + 0);
		p.add(baseFormLabel,c);
		
		c.gridy = inputs.length +2;
		JLabel nextTermLabel = new JLabel("" + 0);
		p.add(nextTermLabel,c);
		
		JButton button = new JButton(BUTTON_TEXT);
		c.gridx = 3;
		c.gridy = inputs.length+3;
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
									int[] base = Goodstien.baseN(Integer.valueOf(inputsFields[0].getText()),Integer.valueOf(inputsFields[1].getText()));
									String baseForm = "";
									for (int i = base.length-1; i>=0;i--)
									{
										baseForm += ("" + base[i] + ".");
									}
									
									baseFormLabel.setText(baseForm.substring(0,baseForm.length()-1));
									int x = Goodstien.nextTerm(Integer.valueOf(inputsFields[0].getText()),Integer.valueOf(inputsFields[1].getText()));
									nextTermLabel.setText("" + x);
									inputsFields[0].setText("" + x);
									inputsFields[1].setText("" + (Integer.valueOf(inputsFields[1].getText()) +1 ));
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
		JFrame frame = new JFrame("Goodstien");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(p);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
