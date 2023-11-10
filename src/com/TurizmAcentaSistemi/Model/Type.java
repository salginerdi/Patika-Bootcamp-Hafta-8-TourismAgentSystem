package com.TurizmAcentaSistemi.Model;

import com.TurizmAcentaSistemi.Helper.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Type {
    private int id;
    private String type;
    private int hotel_id;

    private Hotel hotel;

    public Type(int id, String type, int hotel_id) {
        this.id = id;
        this.type = type;
        this.hotel_id = hotel_id;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public Type() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    // Belirli bir otel tipinin (konaklama tipinin) bilgilerini veritabanından alır ve ilgili Type nesnesi olarak döndürür.
    // This method retrieves the information of a specific hotel type from the database based on its ID and returns the corresponding Type object.
    public static Type getFetch(int id) {
        Type obj = null;
        String query = "SELECT * FROM type_hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Type(rs.getInt("id"), rs.getString("type"), rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    // Yeni bir otel tipi (konaklama tipi) ekler ve veritabanına kaydeder.
    // This method adds a new hotel type (accommodation type) and stores it in the database.
    public static boolean add(String type, int hotel_id) {
        String query = "INSERT INTO type_hotel (type, hotel_id) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,type);
            pr.setInt(2, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    // Belirli bir otelin sahip olduğu otel tiplerinin (konaklama tiplerinin) listesini veritabanından alır ve bir ArrayList olarak döndürür.
    // This method retrieves a list of hotel types (accommodation types) that belong to a specific hotel from the database and returns it as an ArrayList.
    public static ArrayList<Type> getListByHotelID(int id){
        ArrayList<Type> typeList = new ArrayList<>();
        Type obj;
        String query = "SELECT * FROM type_hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Type();
                obj.setId(rs.getInt("id"));
                obj.setType(rs.getString("type"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                typeList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeList;
    }
}
