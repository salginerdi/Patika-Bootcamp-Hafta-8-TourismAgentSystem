package com.TurizmAcentaSistemi.Model;

import com.TurizmAcentaSistemi.Helper.DBConnector;
import com.TurizmAcentaSistemi.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ResInfo {

    private int id;
    private String client_name;
    private String client_phone;
    private String client_email;
    private String client_note;
    private int room_id;
    private String check_in;
    private String check_out;
    private int adult_numb;
    private int child_numb;
    private int total_price;

    public ResInfo(int id, String client_name, String client_phone, String client_email, String client_note, int room_id, String check_in, String check_out, int adult_numb, int child_numb, int total_price) {
        this.id = id;
        this.client_name = client_name;
        this.client_phone = client_phone;
        this.client_email = client_email;
        this.client_note = client_note;
        this.room_id = room_id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.adult_numb = adult_numb;
        this.child_numb = child_numb;
        this.total_price = total_price;
    }

    public ResInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getClient_note() {
        return client_note;
    }

    public void setClient_note(String client_note) {
        this.client_note = client_note;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public int getAdult_numb() {
        return adult_numb;
    }

    public void setAdult_numb(int adult_numb) {
        this.adult_numb = adult_numb;
    }

    public int getChild_numb() {
        return child_numb;
    }

    public void setChild_numb(int child_numb) {
        this.child_numb = child_numb;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    //  Yeni bir rezervasyon bilgisini ekler ve veritabanına kaydeder.
    //  This method adds a new reservation information and stores it in the database.
    public static boolean add(String client_name, String client_phone, String client_email, String client_note, int room_id, String check_in, String check_out, int adult_numb, int child_numb, int total_price) {
        String query = "INSERT INTO reservation_info (client_name, client_phone, client_email, client_note, room_id, check_in, check_out, adult_numb, child_numb, total_price) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,client_name);
            pr.setString(2,client_phone);
            pr.setString(3,client_email);
            pr.setString(4,client_note);
            pr.setInt(5,room_id);
            pr.setString(6,check_in);
            pr.setString(7,check_out);
            pr.setInt(8,adult_numb);
            pr.setInt(9,child_numb);
            pr.setInt(10,total_price);

            int response = pr.executeUpdate();

            if (response == -1){
                Helper.showMsg("error");
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Tüm rezervasyon bilgilerinin listesini veritabanından alır ve bir ArrayList olarak döndürür. Her bir rezervasyonun ayrıntıları, ResInfo nesneleri içinde tutulur.
    // This method retrieves a list of all reservation information from the database and returns it as an ArrayList. The details of each reservation are stored in ResInfo objects.
    public static ArrayList<ResInfo> getList(){
        ArrayList<ResInfo> resInfos = new ArrayList<>();
        String query = "SELECT * FROM reservation_info";
        ResInfo obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new ResInfo();
                obj.setId(rs.getInt("id"));
                obj.setClient_name(rs.getString("client_name"));
                obj.setClient_phone(rs.getString("client_phone"));
                obj.setClient_email(rs.getString("client_email"));
                obj.setClient_note(rs.getString("client_note"));
                obj.setRoom_id(rs.getInt("room_id"));
                obj.setCheck_in(rs.getString("check_in"));
                obj.setCheck_out(rs.getString("check_out"));
                obj.setAdult_numb(rs.getInt("adult_numb"));
                obj.setChild_numb(rs.getInt("child_numb"));
                obj.setTotal_price(rs.getInt("total_price"));
                resInfos.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resInfos;
    }

    // Rezervasyon silme metodu
    // Reservation Deletion Method
    public static boolean deleteRes(int id){
        String query = "DELETE FROM reservation_info WHERE id=? ";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}

