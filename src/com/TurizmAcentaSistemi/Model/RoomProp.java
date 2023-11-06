package com.TurizmAcentaSistemi.Model;

import com.TurizmAcentaSistemi.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomProp {

    private int id;
    private String property;
    private int room_id;
    private String bed;
    private int area;

    private Room room;

    public RoomProp(int id, String property, int room_id, String bed, int area) {
        this.id = id;
        this.property = property;
        this.room_id = room_id;
        this.bed = bed;
        this.area = area;
        this.room = Room.getFetch(room_id);
    }

    public RoomProp() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    // Belirtilen oda kimliğine sahip oda özelliklerini veritabanından çeker.
    // Pulls room properties with the specified room ID from the database.
    public static ArrayList<RoomProp> getListByRoomID(int id){
        ArrayList<RoomProp> roomPropList = new ArrayList<>();
        RoomProp obj;
        String query = "SELECT * FROM room_properties WHERE room_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new RoomProp();
                obj.setProperty(rs.getString("property"));
                obj.setBed(rs.getString("bed"));
                obj.setArea(rs.getInt("area"));
                roomPropList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomPropList;
    }

    // Belirtilen oda özelliklerini, oda kimliği ile birlikte veritabanına ekler.
    // Adds the specified room properties to the database along with the room ID.
    public static boolean add(String property, int room_id, String bed, int area){
        String query = "INSERT INTO room_properties (property, room_id, bed, area ) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,property);
            pr.setInt(2,room_id);
            pr.setString(3, bed);
            pr.setInt(4, area);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Belirtilen oda kimliği ile ilişkilendirilmiş oda özelliklerini veritabanından çeker.
    // Pulls the room properties associated with the specified room ID from the database.
    public static RoomProp getFetch(int id) {
        RoomProp obj = null;
        String query = "SELECT * FROM room_properties WHERE room_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new RoomProp(rs.getInt("id"), rs.getString("property"), rs.getInt("room_id"), rs.getString("bed"), rs.getInt("area"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }





}
