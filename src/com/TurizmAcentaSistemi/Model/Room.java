package com.TurizmAcentaSistemi.Model;

import com.TurizmAcentaSistemi.Helper.DBConnector;
import com.TurizmAcentaSistemi.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id;
    private String room_type;
    private int stock;
    private int season_id;
    private int adult_price;
    private int child_price;
    private int hotel_type_id;
    private int hotel_id;


    private Hotel hotel;
    private Season season;
    private Type type;

    public Room() {

    }

    public Room(int id, String room_type, int stock, int season_id, int adult_price, int child_price, int hotel_type_id, int hotel_id) {
        this.id = id;
        this.room_type = room_type;
        this.stock = stock;
        this.season_id = season_id;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.hotel_type_id = hotel_type_id;
        this.hotel_id = hotel_id;
        this.hotel = Hotel.getFetch(hotel_id);
        this.season = Season.getFetch(season_id);
        this.type = Type.getFetch(hotel_type_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public int getHotel_type_id() {
        return hotel_type_id;
    }

    public void setHotel_type_id(int hotel_type_id) {
        this.hotel_type_id = hotel_type_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Season getHotelSeason() {
        return season;
    }

    public void setHotelSeason(Season season) {
        this.season = season;
    }

    public Type getHotelType() {
        return type;
    }

    public void setHotelType(Type type) {
        this.type = type;
    }

    public static ArrayList<Room> getList(){
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setRoom_type(rs.getString("room_type"));
                obj.setStock(rs.getInt("stock"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
                obj.setHotel_type_id(rs.getInt("hotel_type_id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomList;
    }

    // verilen bir id değerine sahip bir oda kaydını veritabanından seçip, bu kaydın bilgilerini bir Room nesnesi içinde döndürür.
    // selects a room record with a given id from the database and returns its information in a Room object.
    public static Room getFetch(int id) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Room(rs.getInt("id"), rs.getString("room_type"), rs.getInt("stock"), rs.getInt("season_id"), rs.getInt("adult_price"), rs.getInt("child_price"), rs.getInt("hotel_type_id"), rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    // Yeni oda kaydını veritabanına ekler.
    // Adds the new room record to the database.
    public static boolean add(String room_type, int stock, int season_id, int adult_price, int child_price, int hotel_type_id, int hotel_id) {
        String query = "INSERT INTO room (room_type, stock, season_id, adult_price, child_price, hotel_type_id, hotel_id) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,room_type);
            pr.setInt(2,stock);
            pr.setInt(3,season_id);
            pr.setInt(4,adult_price);
            pr.setInt(5,child_price);
            pr.setInt(6,hotel_type_id);
            pr.setInt(7,hotel_id);

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

    // Belirli bir otelin odalarının bilgilerini veritabanından alır ve ilgili Room nesnesi olarak döndürür.
    // This method retrieves the information of rooms for a specific hotel from the database and returns the corresponding
    public static Room getFetchByHotelID(int id) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Room(rs.getInt("id"), rs.getString("room_type"), rs.getInt("stock"), rs.getInt("season_id"), rs.getInt("adult_price"), rs.getInt("child_price"), rs.getInt("hotel_type_id"), rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Belirli bir otelin ve sezonun odalarının bilgilerini veritabanından alır ve ilgili Room nesnesi olarak döndürür.
    // This method retrieves the information of rooms for a specific season and hotel from the database and returns the corresponding Room object.
    public static Room getFetchByHotelIDSeasonID(int season_id, int hotel_id) {
        String query = "SELECT * FROM room WHERE season_id = ? AND hotel_id = ?";
        Room obj=null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, season_id);
            pr.setInt(2, hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setRoom_type(rs.getString("room_type"));
                obj.setStock(rs.getInt("stock"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
                obj.setHotel_type_id(rs.getInt("hotel_type_id"));
                obj.setHotel_id(rs.getInt("hotel_id"));  }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Belirli bir otelin odalarının listesini veritabanından alır ve bir ArrayList olarak döndürür.
    // This method retrieves a list of rooms for a specific hotel from the database and returns it as an ArrayList.
    public static ArrayList<Room> searchRoomList(int hotel_id){
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room WHERE hotel_id = ?";
        Room obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setRoom_type(rs.getString("room_type"));
                obj.setStock(rs.getInt("stock"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
                obj.setHotel_type_id(rs.getInt("hotel_type_id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomList;
    }

    // Belirtilen otel kimliğine sahip odaları veritabanından siler.
    // Deletes rooms with the specified hotel ID from the database.
    public static boolean deleteByHotelId(int id) {
        String query = "DELETE FROM room WHERE hotel_id =?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}

