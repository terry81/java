import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SwingBox extends JFrame {

	private JButton buttonSelect = new JButton("Go");

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate localDate = LocalDate.now();
	private String currentDate = dtf.format(localDate);

	public SwingBox() {
		super("Logs downloader");

		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		String[] landscapeNames = new String[] { "EU1", "US1", "AP1" };

		// create a combo box with items specified in the String array:
		final JComboBox<String> landscapesList = new JComboBox<String>(landscapeNames);

		// customize some appearance:
		landscapesList.setForeground(Color.BLUE);
		landscapesList.setFont(new Font("Arial", Font.BOLD, 14));
		landscapesList.setMaximumRowCount(10);

		JLabel dateLabel = new JLabel("Date: ", JLabel.RIGHT);
		final JTextField dateText = new JTextField(currentDate);

		// make the combo box editable so we can add new item when needed
		landscapesList.setEditable(true);

		// add an event listener for the combo box
		landscapesList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedBook = (String) combo.getSelectedItem();

				DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) combo.getModel();

				int selectedIndex = model.getIndexOf(selectedBook);
				if (selectedIndex < 0) {
					// if the selected book does not exist before,
					// add it into this combo box
					model.addElement(selectedBook);
				}

			}
		});

		// add event listener for the button Select
		buttonSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String selectedLandscape = (String) landscapesList.getSelectedItem();
				String selectedDate = dateText.getText();

				switch (selectedLandscape) {
				case "EU1":
					JOptionPane.showMessageDialog(SwingBox.this,
							"Downloading logs from " + selectedDate + " for: " + selectedLandscape);
					break;
				case "US1":
					JOptionPane.showMessageDialog(SwingBox.this,
							"Downloading logs from " + selectedDate + " for: " + selectedLandscape);
					break;
				}

			}
		});

		// add components to this frame
		add(dateLabel);
		add(dateText);
		add(landscapesList);
		add(buttonSelect);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SwingBox().setVisible(true);
			}
		});
	}
}