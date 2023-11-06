package com.TurizmAcentaSistemi.Model;

import com.TurizmAcentaSistemi.Helper.DBConnector;
import com.TurizmAcentaSistemi.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String star;
    private String property;
    private String address;
    private String phone;
    private String email;


    public Hotel(int id, String name, String star, String property, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.property = property;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Hotel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //  Tüm otellerin listesini veritabanından alır ve bir ArrayList olarak döndürür.
    // This method retrieves a list of all hotels from the database and returns it as an ArrayList.
    public static ArrayList<Hotel> getList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStar(rs.getString("star"));
                obj.setProperty(rs.getString("property"));
                obj.setAddress(rs.getString("address"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotelList;
    }

    // Belirli bir otelin bilgilerini veritabanından alır ve ilgili Hotel nesnesi olarak döndürür.
    // This method retrieves the information of a specific hotel from the database based on its ID and returns the corresponding Hotel object.
    public static Hotel getFetch(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("star"), rs.getString("property"), rs.getString("address"), rs.getString("phone"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    // Belirli bir otelin e-posta adresine göre bilgilerini veritabanından alır ve ilgili Hotel nesnesi olarak döndürür.
    // This method retrieves the information of a specific hotel from the database based on its email address and returns the corresponding Hotel object.
    public static Hotel getFetch(String email) {
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE email = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("star"), rs.getString("property"), rs.getString("address"), rs.getString("phone"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    // Yeni bir otel ekler ve veritabanına kaydeder. Eğer aynı e-posta adresine sahip bir otel zaten varsa kayıt yapılmadan önce bir hata mesajı gösterir.
    // This method adds a new hotel and stores it in the database. If a hotel with the same email address already exists, it displays an error message before attempting to register.
    public static boolean add(String name, String star, String property, String address, String phone, String email) {
        String query = "INSERT INTO hotel (name, star, property, address, phone, email) VALUES (?,?,?,?,?,?)";
        Hotel findHotel = Hotel.getFetch(email);
        if (findHotel != null) {
            Helper.showMsg("Bu otel daha önce sisteme kaydedilmiştir.");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, star);
            pr.setString(3, property);
            pr.setString(4, address);
            pr.setString(5, phone);
            pr.setString(6, email);

            int response = pr.executeUpdate();

            if (response == -1) {
                Helper.showMsg("error");
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    //  Verilen bir sorgu ile eşleşen otellerin listesini veritabanından alır ve bir ArrayList olarak döndürür.
    // This method retrieves a list of hotels that match the given query from the database and returns it as an
    public static ArrayList<Hotel> searchHotelList(String query) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStar(rs.getString("star"));
                obj.setProperty(rs.getString("property"));
                obj.setAddress(rs.getString("address"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    //  Belirli bir oteli veritabanından siler.
    // This method deletes a specific hotel from the database.

    /*
    public static boolean delete(int id) {
        String query = "DELETE FROM hotel WHERE id =?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Room.deleteByHotelId(id);
    } */
}

