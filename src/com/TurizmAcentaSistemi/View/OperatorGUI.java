package com.TurizmAcentaSistemi.View;

import com.TurizmAcentaSistemi.Helper.Config;
import com.TurizmAcentaSistemi.Helper.Helper;
import com.TurizmAcentaSistemi.Model.Operator;
import com.TurizmAcentaSistemi.Model.User;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JTable tbl_user_list;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JButton btn_logout;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_uname;
    private JButton btn_user_sh;
    private JComboBox cmb_sh_user_type;

    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    private final Operator operator;

    public OperatorGUI(Operator operator){
        this.operator = operator;
        add(wrapper);
        setSize(1200,600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

       // 7- Operator için (Admin) yetkiler bölümü.
        // 7- Authorizations section for Operator (Admin).

        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Yetki Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();
        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
        tbl_user_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString();
                fld_user_id.setText(select_user_id);
            }
            catch (Exception exception){
            }
        });

        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });

        btn_user_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }
            else{
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name,uname,pass,type)){
                    Helper.showMsg("done");
                    loadUserModel();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                    cmb_sh_user_type.setSelectedIndex(0);
                }
            }
        });

        btn_user_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_id)){
                Helper.showMsg("fill");
            }
            else{
                if (Helper.confirm("sure")){
                    int user_id = Integer.parseInt(fld_user_id.getText());
                    if (User.delete(user_id)){
                        Helper.showMsg("done");
                        loadUserModel();
                        fld_user_id.setText(null);
                        cmb_sh_user_type.setSelectedIndex(0);
                    }
                    else{
                        Helper.showMsg("error");
                    }
                }
            }
        });

        btn_user_sh.addActionListener(e -> {
            String name = fld_sh_user_name.getText();
            String uname = fld_sh_user_uname.getText();
            String type = cmb_sh_user_type.getSelectedItem().toString();

            String query = Helper.searchQuery(name, uname, type);
            ArrayList<User> searchingUser = User.searchUserList(query);
            loadUserModel(searchingUser);
        });

    }

    private void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (User obj : User.getList()){
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }


    public void loadUserModel(ArrayList<User> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : list){
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }
}
