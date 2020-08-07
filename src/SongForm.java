import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.text.BadLocationException;
/*
 * Created by JFormDesigner on Thu Aug 06 13:38:12 PDT 2020
 */


/**
 * @author Diec Tin Toan
 */
public class SongForm {
    DefaultTableModel tableModel;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;

    public SongForm() {

        initComponents();
        tableModel = (DefaultTableModel) maintable1.getModel();
    }

//    private void Addbutton1MouseClicked(MouseEvent e) throws SQLException, BadLocationException {
//        // TODO add your code here
//
//
//        try {
//
//            if (SongNametextField1.getText().trim().isEmpty() || WrittertextField2.getText().trim().isEmpty() || SingertextField3.getText().trim().isEmpty() ||
//                    ReDatetextField4.getText().trim().isEmpty() || RevenuetextField5.getText().trim().isEmpty()||RevenuetextField5.getText().trim() == "") {
//
//                JOptionPane.showMessageDialog(null, " Please input all data");
//
//            } else {
//                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/song", "root", "");
//                preparedStatement = connection.prepareStatement("Select * from songs where name = ?");
//
//                preparedStatement.setString(1, SongNametextField1.getText());
//
//
//                ResultSet rs = preparedStatement.executeQuery();
//                if (rs.isBeforeFirst()) {
//
//                    JOptionPane.showMessageDialog(null, "Your song is already in the database");
//
//                    SongNametextField1.setText("");
//                    WrittertextField2.setText("");
//                    SingertextField3.setText("");
//                    ReDatetextField4.setText("");
//                    RevenuetextField5.setText("");
//                    return;
//                } else {
//
////                    NumberFormat percentFormat = NumberFormat.getNumberInstance();
////                    percentFormat.setMaximumFractionDigits(2);
////                    RevenuetextField5 = new JFormattedTextField(percentFormat);
//
//                    String sql = "insert into songs values (?,?,?,?,?)";
//                    preparedStatement = connection.prepareStatement(sql);
//                    preparedStatement.setString(1, SongNametextField1.getText());
//                    preparedStatement.setString(2, WrittertextField2.getText());
//                    preparedStatement.setString(3, SingertextField3.getText());
//                    preparedStatement.setString(4, ReDatetextField4.getText());
//                    preparedStatement.setString(5, RevenuetextField5.getText());
//
//
//                    preparedStatement.executeUpdate();
//                    JOptionPane.showMessageDialog(null, "Song added");
//
//                    SongNametextField1.setText("");
//                    WrittertextField2.setText("");
//                    SingertextField3.setText("");
//                    RevenuetextField5.setText("");
//                    ReDatetextField4.setText("");
//                    showData();
//
//
//                }
//            }
//
//
//        } catch (SQLException exception) {
//            Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
//        } catch (InputMismatchException inputMismatchException) {
//            JOptionPane.showMessageDialog(null, "Please input again");
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException exception) {
//                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
//                }
//            }
//            if (connection != null) {
//                try {
//
//                    connection.close();
//                } catch (SQLException exception) {
//                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
//                }
//            }
//        }
//    }

    public void showData() {


        tableModel.setRowCount(0);
        List<SongInfo> songs = new ArrayList<>();


        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/song", "root", "");
            statement = connection.createStatement();

            String sql = "select * from songs";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                SongInfo songInfo = new SongInfo(resultSet.getString("name"), resultSet.getString("writter"),
                        resultSet.getString("singer"), resultSet.getDate("releasedate"), resultSet.getDouble("revenue"));

                songs.add(songInfo);
            }

        } catch (SQLException exception) {
            Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
        } finally {
            if (connection != null) {
                try {

                    connection.close();
                } catch (SQLException exception) {
                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
            if (statement != null) {
                try {

                    statement.close();
                } catch (SQLException exception) {
                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
        }


        for (SongInfo songInfo : songs) {

            tableModel.addRow(new Object[]{songInfo.getSongName(), songInfo.getWritter(), songInfo.getSinger(), songInfo.getReDate(), songInfo.getRevenue()});
        }

    }

    private void RevenuetextField5KeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void RevenuetextField5KeyTyped(KeyEvent e) {
        // TODO add your code here

        char c = e.getKeyChar();
        if (Character.isLetter(c) || e.isAltDown()) {
            e.consume();
        }
        try {
            double i = 1;
            i = Double.parseDouble(RevenuetextField5.getText());
            invalidlabel6.setText("");
        } catch (NumberFormatException exception) {
//            RevenuetextField5.setText("");
//            JOptionPane.showMessageDialog(null, "Please input valid revenue");
            invalidlabel6.setText("Invalid revenue input");
        }
    }

//    private void Modifybutton1MouseClicked(MouseEvent e) throws SQLException {
//        // TODO add your code here
//        try {
//
//            if (SongNametextField1.getText().trim().isEmpty() || WrittertextField2.getText().trim().isEmpty() || SingertextField3.getText().trim().isEmpty() ||
//                    ReDatetextField4.getText().trim().isEmpty() || RevenuetextField5.getText().trim().isEmpty()) {
//
//                JOptionPane.showMessageDialog(null, " Please input all data");
//
//            } else {
//                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/song", "root", "");
//                preparedStatement = connection.prepareStatement("update songs set name = ?, writter=?, singer=?, releasedate=?,revenue=? where name=?");
//
//                preparedStatement.setString(1, SongNametextField1.getText());
//                preparedStatement.setString(2, WrittertextField2.getText());
//                preparedStatement.setString(3, SingertextField3.getText());
//                preparedStatement.setString(4, ReDatetextField4.getText());
//                preparedStatement.setString(5, RevenuetextField5.getText());
//                preparedStatement.setString(6, SongNametextField1.getText());
//
//                preparedStatement.executeUpdate();
//                JOptionPane.showMessageDialog(null,"Song Updated");
//
//                SongNametextField1.setText("");
//                WrittertextField2.setText("");
//                SingertextField3.setText("");
//                RevenuetextField5.setText("");
//                ReDatetextField4.setText("");
//
//                SongNametextField1.requestFocus();
//
//                }
//
//    }catch (SQLException exception) {
//            Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
//        } catch (InputMismatchException inputMismatchException) {
//            JOptionPane.showMessageDialog(null, "Please input again");
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException exception) {
//                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
//                }
//            }
//            if (connection != null) {
//                try {
//
//                    connection.close();
//                } catch (SQLException exception) {
//                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
//                }
//            }
//        }
//    }

    private void Deletebutton5MouseClicked(MouseEvent e) {
        // TODO add your code here
        try {

            if (SongNametextField1.getText().trim().isEmpty() ) {

                JOptionPane.showMessageDialog(null, " Please input song's name to delete");

            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/song", "root", "");
                preparedStatement = connection.prepareStatement("delete from songs where name=?");

                preparedStatement.setString(1, SongNametextField1.getText());

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Song Deleted");

                SongNametextField1.setText("");
                WrittertextField2.setText("");
                SingertextField3.setText("");
                RevenuetextField5.setText("");
                ReDatetextField4.setText("");

                SongNametextField1.requestFocus();

            }

        }catch (SQLException exception) {
            Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
        } catch (InputMismatchException inputMismatchException) {
            JOptionPane.showMessageDialog(null, "Please input again");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
            if (connection != null) {
                try {

                    connection.close();
                } catch (SQLException exception) {
                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
        }
    }

    private void Showbutton3MouseClicked(MouseEvent e) {
        // TODO add your code here
        showData();

    }

    private void ReDatetextField4ActionPerformed(ActionEvent e) {
        // TODO add your code here

        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            Date date = (Date) sdf.parse(ReDatetextField4.getText());
            if (!sdf.format(date).equals(ReDatetextField4.getText())) {
                throw new ParseException(ReDatetextField4.getText() + " is not a valid format for " + format, 0);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void Addbutton1MouseClicked(MouseEvent e) {
        // TODO add your code here

        try {

            if (SongNametextField1.getText().trim().isEmpty() || WrittertextField2.getText().trim().isEmpty() || SingertextField3.getText().trim().isEmpty() ||
                    ReDatetextField4.getText().trim().isEmpty() || RevenuetextField5.getText().trim().isEmpty()||RevenuetextField5.getText().trim() == "") {

                JOptionPane.showMessageDialog(null, " Please input all data");

            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/song", "root", "");
                preparedStatement = connection.prepareStatement("Select * from songs where name = ?");

                preparedStatement.setString(1, SongNametextField1.getText());


                ResultSet rs = preparedStatement.executeQuery();
                if (rs.isBeforeFirst()) {

                    JOptionPane.showMessageDialog(null, "Your song is already in the database");

                    SongNametextField1.setText("");
                    WrittertextField2.setText("");
                    SingertextField3.setText("");
                    ReDatetextField4.setText("");
                    RevenuetextField5.setText("");
                    return;
                } else {

//                    NumberFormat percentFormat = NumberFormat.getNumberInstance();
//                    percentFormat.setMaximumFractionDigits(2);
//                    RevenuetextField5 = new JFormattedTextField(percentFormat);

                    String sql = "insert into songs values (?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, SongNametextField1.getText());
                    preparedStatement.setString(2, WrittertextField2.getText());
                    preparedStatement.setString(3, SingertextField3.getText());
                    preparedStatement.setString(4, ReDatetextField4.getText());
                    preparedStatement.setString(5, RevenuetextField5.getText());


                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Song added");

                    SongNametextField1.setText("");
                    WrittertextField2.setText("");
                    SingertextField3.setText("");
                    RevenuetextField5.setText("");
                    ReDatetextField4.setText("");
                    showData();


                }
            }


        } catch (SQLException exception) {
            Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
        } catch (InputMismatchException inputMismatchException) {
            JOptionPane.showMessageDialog(null, "Please input again");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
            if (connection != null) {
                try {

                    connection.close();
                } catch (SQLException exception) {
                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
        }
    }

    private void Modifybutton2MouseClicked(MouseEvent e) {
        // TODO add your code here
        try {

            if (SongNametextField1.getText().trim().isEmpty() || WrittertextField2.getText().trim().isEmpty() || SingertextField3.getText().trim().isEmpty() ||
                    ReDatetextField4.getText().trim().isEmpty() || RevenuetextField5.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, " Please input all data");

            } else {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/song", "root", "");
                preparedStatement = connection.prepareStatement("update songs set name = ?, writter=?, singer=?, releasedate=?,revenue=? where name=?");

                preparedStatement.setString(1, SongNametextField1.getText());
                preparedStatement.setString(2, WrittertextField2.getText());
                preparedStatement.setString(3, SingertextField3.getText());
                preparedStatement.setString(4, ReDatetextField4.getText());
                preparedStatement.setString(5, RevenuetextField5.getText());
                preparedStatement.setString(6, SongNametextField1.getText());

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Song Updated");

                SongNametextField1.setText("");
                WrittertextField2.setText("");
                SingertextField3.setText("");
                RevenuetextField5.setText("");
                ReDatetextField4.setText("");

                SongNametextField1.requestFocus();

                }

    }catch (SQLException exception) {
            Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
        } catch (InputMismatchException inputMismatchException) {
            JOptionPane.showMessageDialog(null, "Please input again");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
            if (connection != null) {
                try {

                    connection.close();
                } catch (SQLException exception) {
                    Logger.getLogger(SongForm.class.getName()).log(Level.SEVERE, null, exception);
                }
            }
        }
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Diec Tin Toan
        mainPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        WrittertextField2 = new JTextField();
        SingertextField3 = new JTextField();
        ReDatetextField4 = new JTextField();
        Showbutton3 = new JButton();
        Deletebutton5 = new JButton();
        scrollPane1 = new JScrollPane();
        maintable1 = new JTable();
        SongNametextField1 = new JTextField();
        RevenuetextField5 = new JTextField();
        invalidlabel6 = new JLabel();
        Addbutton1 = new JButton();
        Modifybutton2 = new JButton();

        //======== mainPanel ========
        {
            mainPanel.setBorder(new TitledBorder(null, "Song Database", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 12)));
            mainPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,mainPanel. getBorder( )) ); mainPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            mainPanel.setLayout(null);

            //---- label1 ----
            label1.setText("Song's Name");
            mainPanel.add(label1);
            label1.setBounds(new Rectangle(new Point(25, 35), label1.getPreferredSize()));

            //---- label2 ----
            label2.setText("Writter");
            mainPanel.add(label2);
            label2.setBounds(25, 75, 75, 16);

            //---- label3 ----
            label3.setText("Singer");
            mainPanel.add(label3);
            label3.setBounds(25, 115, 50, 16);

            //---- label4 ----
            label4.setText("Release Date");
            mainPanel.add(label4);
            label4.setBounds(25, 155, 80, 16);

            //---- label5 ----
            label5.setText("Revenue");
            mainPanel.add(label5);
            label5.setBounds(25, 195, 80, 16);
            mainPanel.add(WrittertextField2);
            WrittertextField2.setBounds(120, 70, 255, 30);
            mainPanel.add(SingertextField3);
            SingertextField3.setBounds(120, 110, 255, 30);

            //---- ReDatetextField4 ----
            ReDatetextField4.addActionListener(e -> ReDatetextField4ActionPerformed(e));
            mainPanel.add(ReDatetextField4);
            ReDatetextField4.setBounds(120, 150, 255, 30);

            //---- Showbutton3 ----
            Showbutton3.setText("Show");
            Showbutton3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Showbutton3MouseClicked(e);
                }
            });
            mainPanel.add(Showbutton3);
            Showbutton3.setBounds(290, 260, 78, 30);

            //---- Deletebutton5 ----
            Deletebutton5.setText("Delete");
            Deletebutton5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Deletebutton5MouseClicked(e);
                }
            });
            mainPanel.add(Deletebutton5);
            Deletebutton5.setBounds(195, 260, 78, 30);

            //======== scrollPane1 ========
            {

                //---- maintable1 ----
                maintable1.setBorder(LineBorder.createBlackLineBorder());
                maintable1.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        "Song's Name", "Writter", "Singer", "Release Date", "Revenue"
                    }
                ));
                scrollPane1.setViewportView(maintable1);
            }
            mainPanel.add(scrollPane1);
            scrollPane1.setBounds(15, 300, 375, 255);
            mainPanel.add(SongNametextField1);
            SongNametextField1.setBounds(125, 35, 250, SongNametextField1.getPreferredSize().height);

            //---- RevenuetextField5 ----
            RevenuetextField5.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    RevenuetextField5KeyTyped(e);
                }
            });
            mainPanel.add(RevenuetextField5);
            RevenuetextField5.setBounds(125, 190, 245, RevenuetextField5.getPreferredSize().height);

            //---- invalidlabel6 ----
            invalidlabel6.setText("   ");
            invalidlabel6.setForeground(Color.red);
            mainPanel.add(invalidlabel6);
            invalidlabel6.setBounds(125, 230, 195, invalidlabel6.getPreferredSize().height);

            //---- Addbutton1 ----
            Addbutton1.setText("Add");
            Addbutton1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Addbutton1MouseClicked(e);
                }
            });
            mainPanel.add(Addbutton1);
            Addbutton1.setBounds(new Rectangle(new Point(20, 260), Addbutton1.getPreferredSize()));

            //---- Modifybutton2 ----
            Modifybutton2.setText("Modify");
            Modifybutton2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Modifybutton2MouseClicked(e);
                }
            });
            mainPanel.add(Modifybutton2);
            Modifybutton2.setBounds(new Rectangle(new Point(110, 260), Modifybutton2.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < mainPanel.getComponentCount(); i++) {
                    Rectangle bounds = mainPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = mainPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                mainPanel.setMinimumSize(preferredSize);
                mainPanel.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Songs");
        jFrame.setContentPane(new SongForm().mainPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Diec Tin Toan
    private JPanel mainPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField WrittertextField2;
    private JTextField SingertextField3;
    private JTextField ReDatetextField4;
    private JButton Showbutton3;
    private JButton Deletebutton5;
    private JScrollPane scrollPane1;
    private JTable maintable1;
    private JTextField SongNametextField1;
    private JTextField RevenuetextField5;
    private JLabel invalidlabel6;
    private JButton Addbutton1;
    private JButton Modifybutton2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
