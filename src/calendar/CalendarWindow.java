package calendar;
import javax.swing.*;
import javax.swing.table.*;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import reservation.Activity;
import reservation.Reservation;
import resources.ColorResources;
import resources.TextResources;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarWindow extends JFrame implements MouseListener{
	/**
	 * 
	 */
	
	private JTable tblCalendar;
	private JFrame frmMain;
	private Container container;
	private DefaultTableModel mtblCalendar; //Table model
	private JScrollPane stblCalendar; //The scrollpane
	private JPanel pnlCalendar;
	private int realYear, realMonth, realDay, currentYear, currentMonth;
	
	private JLabel dayLabel;
	private JLabel timeLabel;
	private JLabel peopleLabel;
	
	private ArrayList<Activity> activities;
	private Activity activity;
	private Reservation reservation;
	private JLabel priceLabel;
	private JLabel bagLabel;
	
	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;
	
	private JButton hour1Btn;
	private JButton hour2Btn;
	
	private ImageIcon plusIcon;
	private JLabel plusButtonLabel;
	private ImageIcon minusIcon;
	private JLabel minusButtonLabel;
	private JLabel quantinty;

	public CalendarWindow(ArrayList<Activity> activities , Activity activity, Reservation reservation, JLabel priceLabel, JLabel bagLabel) {
		
		new TextResources().changeLanguage();
		new ColorResources();
		this.activities = activities;
		this.activity = activity;
		this.reservation = reservation;
		this.priceLabel = priceLabel;
		this.bagLabel = bagLabel;
		

		initializePanel();
		
		hour1Btn.addActionListener(new ActionListener() { @Override
			public void actionPerformed(ActionEvent e) {

			if(e.getSource() == hour1Btn) {
				frmMain.dispose();
				activity.setSelHour(0);
				activity.PRINT();
				
//				 if(activity.checkLimit()) { // //column where activity starts
//				 activity.setColumn(activities.indexOf(activity)*2); // 
//				 reservation.addActivity(activity);
//				 priceLabel.setText(String.valueOf(reservation.calcCost()) + "€");
//				 bagLabel.setText(reservation.getActivities().size() + ""); }
				
			}
		}
		
		});
		
		hour2Btn.addActionListener(new ActionListener() { @Override
			public void actionPerformed(ActionEvent e) {

			if(e.getSource() == hour2Btn) {
				frmMain.dispose();
				activity.setSelHour(1);
				activity.PRINT();
				
//				 if(activity.checkLimit()) { // //column where activity starts
//				 activity.setColumn(activities.indexOf(activity)*2); // 
//				 reservation.addActivity(activity);
//				 priceLabel.setText(String.valueOf(reservation.calcCost()) + "€");
//				 bagLabel.setText(reservation.getActivities().size() + ""); }
		
				
			}
		}
		
		});
	
		backBtn.addActionListener(new ActionListener() { @Override
			public void actionPerformed(ActionEvent e) {

			if(e.getSource() == backBtn) {
				frmMain.dispose();
//				new LoginWindow();
			}

		}
			
		});
		
		
	}	
	public void initializePanel() {
		//Prepare frame
				frmMain = new JFrame ("Segaleo"); //Create frame
				//frmMain.setSize(new Dimension(375, 812)); //Set size to 400x400 pixels
				container = frmMain.getContentPane(); //Get content pane
				container.setPreferredSize(new Dimension(375, 812));
				container.setLayout(null); //Apply null layout
				frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
				container.setBackground(new Color(216,223,224));
				container.setBounds(25, 130, 320, 350);
				
				dayLabel = LabelFactory.createLabel(TextResources.chooseDay, Color.gray, FontFactory.poppins(22));
				dayLabel.setBounds(32,100,160,32);
				timeLabel = LabelFactory.createLabel(TextResources.chooseTime, Color.gray, FontFactory.poppins(22));
				timeLabel.setBounds(32,480,160,32);
				peopleLabel = LabelFactory.createLabel(TextResources.choosePeople, Color.gray, FontFactory.poppins(22));
				peopleLabel.setBounds(32,690,160,32);
				
				backBtn = ButtonFactory.createButtonIcon(backImage);
				backBtn.setBounds(15, 25, 64, 45);
				backBtn.setPressedIcon(backImage);
				
				hour1Btn = ButtonFactory.createButton(activity.getHour().get(0),FontFactory.poppins(14),
						ColorResources.timeBtn,Color.WHITE);
				hour1Btn.setBounds(111, 540, 154, 50);
				hour2Btn = ButtonFactory.createButton(activity.getHour().get(1),FontFactory.poppins(14),
						ColorResources.timeBtn,Color.WHITE);
				hour2Btn.setBounds(111, 610, 154, 50);
				
				
				plusIcon = new ImageIcon("./buttonImages/plus.png");
				plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
				plusButtonLabel.setIcon(plusIcon);
				plusButtonLabel.setBounds(190, 740, 24, 24);
				plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				plusButtonLabel.addMouseListener(this);
				plusButtonLabel.setName("plus");

				minusIcon = new ImageIcon("./buttonImages/minus.png");

				minusButtonLabel = LabelFactory.createIconLabel(minusIcon);
				minusButtonLabel.setBounds(130, 740, 24, 24);
				minusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				minusButtonLabel.addMouseListener(this);
				minusButtonLabel.setName("minus");

				quantinty = LabelFactory.createLabel(reservation.getAct().get(activity) +"x", Color.BLACK, FontFactory.poppins(13));
				quantinty.setBounds(160, 740, 50, 20);

				
				//Create controls
				mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
				tblCalendar = new JTable(mtblCalendar);
				tblCalendar.addMouseListener(new jtableListener());
				stblCalendar = new JScrollPane(tblCalendar);
				pnlCalendar = new JPanel(null);
				pnlCalendar.setBackground(new Color(216,223,224));

				//Add controls to pane
				container.add(pnlCalendar);
				pnlCalendar.add(stblCalendar);
				container.add(dayLabel);
				container.add(timeLabel);
				container.add(backBtn);
				container.add(hour1Btn);
				container.add(hour2Btn);
				container.add(peopleLabel);
				container.add(quantinty);
				container.add(minusButtonLabel);
				container.add(plusButtonLabel);

				//Set bounds
				pnlCalendar.setBounds(25, 150, 320, 300);
				stblCalendar.setBounds(10, 50, 300, 250);


				//Get real month/year
				GregorianCalendar cal = new GregorianCalendar(); //Create calendar
				realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
				realMonth = cal.get(GregorianCalendar.MONTH); //Get month
				realYear = cal.get(GregorianCalendar.YEAR); //Get year
				currentMonth = realMonth; //Match month and year
				currentYear = realYear;

				//Add headers
				String[] headers = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"}; //All headers
				String[] headersGR = {"ΚΥΡ", "ΔΕΥ", "ΤΡΙ", "ΤΕΤ", "ΠΕΜ", "ΠΑΡ", "ΣΑΒ"}; //All headers
				
				for (int i=0; i<7; i++){
					if (TextResources.isEnglish)
					{
						mtblCalendar.addColumn(headers[i]);
					}
					else
					{
						mtblCalendar.addColumn(headersGR[i]);
					}
				}

//				tblCalendar.setBackground(new Color(216,223,224));
//				tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background


				//No resize/reorder
				tblCalendar.getTableHeader().setResizingAllowed(false);
				tblCalendar.getTableHeader().setReorderingAllowed(false);

				//Single cell selection
				tblCalendar.setColumnSelectionAllowed(true);
				tblCalendar.setRowSelectionAllowed(true);
				tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				//Set row/column count
				tblCalendar.setRowHeight(38);
				mtblCalendar.setColumnCount(7);
				mtblCalendar.setRowCount(6);


				//Refresh calendar
				refreshCalendar (realMonth, realYear); //Refresh calendar
				
				//Make frame visible
						frmMain.setResizable(false);
						frmMain.setVisible(true);
						frmMain.pack();
						
	}
	
	public void refreshCalendar(int month, int year){
		int nod, som; //Number Of Days, Start Of Month

		//Clear table
		for (int i=0; i<6; i++){
			for (int j=0; j<7; j++){
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		//Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		//Draw calendar
		for (int i=1; i<=nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			mtblCalendar.setValueAt(i, row, column);
		}

		//Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}

	 class tblCalendarRenderer extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
//
//			setBackground(new Color(216,223,224));
//			if (value != null){
//				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
//					setBackground(new Color(220, 220, 255));
//				}
//			}
//			
//			setBorder(null);
//			setForeground(new Color(112, 112, 112));
			return this;
		}
	}
	 @Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() instanceof JLabel) {
				JLabel label = (JLabel) e.getSource();
				if (label.getName().equals("plus")) {
					reservation.addActivity(activity);
				} else if (label.getName().equals("minus")) {
					reservation.removeActivity(activity);
				}
			}
			initializePanel();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



	class jtableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			GregorianCalendar cal = new GregorianCalendar();
			int selectedDate = -1;
			int selectedWeek = -1;
			int selectedDay = -1;

			try {
				selectedDate = (int) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				selectedWeek = table.getSelectedRow();
				selectedDay = table.getSelectedColumn();
			}catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "wrong date");
			}



			int week = cal.get(GregorianCalendar.WEEK_OF_MONTH);
			int date = cal.get(GregorianCalendar.DAY_OF_MONTH);


			if(selectedDate == -1 || selectedWeek == -1) {
				return;
			}
			if(selectedWeek != week || selectedDate < date) {
				JOptionPane.showMessageDialog(null, "invalid week or day");
			}
			else {
				activity.setSelDay(table.getSelectedColumn());
				JOptionPane.showMessageDialog(null, "date: " + table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()) + " day: " + table.getSelectedColumn());
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}


}