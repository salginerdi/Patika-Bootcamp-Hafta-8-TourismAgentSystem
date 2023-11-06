package com.TurizmAcentaSistemi.View;

import com.TurizmAcentaSistemi.Helper.Config;
import com.TurizmAcentaSistemi.Helper.Helper;
import com.TurizmAcentaSistemi.Model.Admin;
import com.TurizmAcentaSistemi.Model.Operator;
import com.TurizmAcentaSistemi.Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JButton btn_login;

    public LoginGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        // 7 - Farklı kullanıcı türlerine (admin ve acenta çalışanı için) uygun yetkiler atanan ekranlar yönlendiren bölüm.
        // 7 - The section that redirects screens that assign appropriate authorizations to different types of users (for admin and agency employee).

        // 8- Login işleminde kullanıcının kaydı olup olmadığının kontrol edildiği, kaydı yoksa ya da hatalı giriş yaptıysa kullanıcıya hatalı giriş mesajı verilen bölüm.
        // 8- The section where it is checked whether the user has a record in the login process, if there is no record or if the user has logged in incorrectly, the user is given an incorrect login message.
        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }
            else{
                User u = User.getFetch(fld_user_uname.getText(), fld_user_pass.getText());
                if (u==null){
                    Helper.showMsg("Kullanıcı bulunamadı! Kullanıcı adınız veya şifreniz hatalıdır.");
                }
                else{
                    switch (u.getType()){
                        case "operator":
                            OperatorGUI opGUI = new OperatorGUI((Operator) u);
                            break;
                        case "admin":
                            AdminGUI adGUI = new AdminGUI((Admin) u);
                            break;
                    }
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args){
        Helper.setLayout();
        LoginGUI login = new LoginGUI();
    }
}
