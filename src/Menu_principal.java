import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class Menu_principal {

	private JFrame frame;
	private JTextField textNom;
	private JTextField textPrix;
	private JTextField textTotal;
	private JTextField textReste;
	private JTextField textPayer;
	private final JTable txtTable = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_principal window = new Menu_principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	
	public Menu_principal() {
		initialize();
		Connect();
		Table();
	}
	
	public void Connect() {
	    try {
	        // Charger le pilote JDBC (driver) MySQL
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // Établir la connexion avec la base de données
	        String url = "jdbc:mysql://localhost/gestionpharmacie";
	        String username = "root";
	        String password = ""; // Remplacez par votre mdp si nécessaire
	        
	        con = DriverManager.getConnection(url, username, password);
	        
	        System.out.println("Connexion établie");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        System.err.println("Erreur : Pilote JDBC introuvable");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println("Erreur de connexion à la base de données");
	    }
	}

//	recupérer les infos depuis la base pour les afficher dans une entete
	
	public void Table() {
		try {
			Connect();
			String [] entet = {"Code","Nom","Prix","Qte","Total","Payer","Reste"};
			String [] ligne = new String[7];
			
//			car c'est un tableau donc il faut ajouter un model par default
			DefaultTableModel model = new DefaultTableModel(null, entet);
			
			String sql = "select * from table_pharma";
			Statement st= con.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				ligne[0] = rs.getString("code");
				ligne[1] = rs.getString("nom");
				ligne[2] = rs.getString("prix");
				ligne[3] = rs.getString("qte");
				ligne[4] = rs.getString("total");
				ligne[5] = rs.getString("payer");
				ligne[6] = rs.getString("reste");
				model.addRow(ligne);
			}
			
			txtTable.setModel(model);
			con.close();
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 679, 735);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 645, 78);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion de Pharmacie");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setForeground(new Color(0, 128, 192));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 6, 625, 61);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 103, 645, 296);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nom  :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(42, 13, 118, 25);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Quantité :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(42, 131, 118, 25);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Prix  :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(42, 73, 118, 25);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Reste :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(343, 130, 118, 25);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Total :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(343, 11, 118, 25);
		panel_1.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Payer :");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_6.setBounds(343, 70, 118, 25);
		panel_1.add(lblNewLabel_1_6);
		
		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(114, 11, 173, 32);
		panel_1.add(textNom);
		
		textPrix = new JTextField();
		textPrix.setColumns(10);
		textPrix.setBounds(114, 71, 173, 32);
		panel_1.add(textPrix);
		
		textTotal = new JTextField();
		textTotal.setHorizontalAlignment(SwingConstants.CENTER);
		textTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTotal.setForeground(new Color(255, 0, 0));
		textTotal.setBackground(new Color(0, 0, 0));
		textTotal.setColumns(10);
		textTotal.setBounds(429, 9, 173, 32);
		panel_1.add(textTotal);
		
		textReste = new JTextField();
		textReste.setHorizontalAlignment(SwingConstants.CENTER);
		textReste.setFont(new Font("Tahoma", Font.BOLD, 11));
		textReste.setForeground(new Color(0, 128, 192));
		textReste.setBackground(new Color(128, 128, 128));
		textReste.setColumns(10);
		textReste.setBounds(429, 128, 173, 32);
		panel_1.add(textReste);
		
		textPayer = new JTextField();
		textPayer.setHorizontalAlignment(SwingConstants.CENTER);
		textPayer.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPayer.addKeyListener(new KeyAdapter() {
		
			public void keyReleased(KeyEvent e) {
				int total = Integer.parseInt(textTotal.getText());
				int payer = Integer.parseInt(textPayer.getText());
				int reste = payer - total;
				textReste.setText(String.valueOf(reste));
			}
		});
		textPayer.setForeground(new Color(0, 0, 0));
		textPayer.setColumns(10);
		textPayer.setBackground(new Color(0, 128, 192));
		textPayer.setBounds(429, 68, 173, 32);
		panel_1.add(textPayer);
		
		final JSpinner textQuantite = new JSpinner();
		textQuantite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int prix = Integer.parseInt(textPrix.getText());
				int qte = Integer.parseInt(textQuantite.getValue().toString());
				int total = prix* qte;
				textTotal.setText(String.valueOf(total));
			}
		});
		textQuantite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textQuantite.setBounds(114, 131, 173, 32);
		panel_1.add(textQuantite);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect();
					pst = con.prepareStatement("insert into table_pharma( nom, prix, qte, total, payer, reste ) values (?,?,?,?,?,?)");
					pst.setString(1, textNom.getText());
					pst.setString(2, textPrix.getText());
					pst.setString(3, textQuantite.getValue().toString());
					pst.setString(4, textTotal.getText());
					pst.setString(5, textPayer.getText());
					pst.setString(6, textReste.getText());
					pst.executeUpdate();
					
					con.close();
					
					JOptionPane.showMessageDialog(null, textNom.getText()+"Ajouter");
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(429, 188, 173, 32);
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 425, 645, 244);
		frame.getContentPane().add(scrollPane);
		txtTable.setBackground(new Color(202, 238, 255));
		scrollPane.setViewportView(txtTable);
	}
}
