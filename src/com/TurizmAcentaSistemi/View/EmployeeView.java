package com.TurizmAcentaSistemi.View;

import com.TurizmAcentaSistemi.Helper.Config;
import com.TurizmAcentaSistemi.Helper.Helper;
import com.TurizmAcentaSistemi.Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Date;

public class EmployeeView extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JTabbedPane tab_admin;
    private JPanel pnl_hotel_list;
    private JScrollPane scrl_hotel_list;
    private JTable tbl_hotel_list;
    private JPanel pnl_hotel_top;
    private JButton btn_hotel_add;
    private JScrollPane scrl_hotel_list_left;
    private JTable tbl_hotel_type;
    private JPanel pnl_room_list;
    private JPanel pnl_hotel_bottom;
    private JScrollPane scrl_hotel_list_right;
    private JTable tbl_hotel_season;
    private JPanel pnl_room_top;
    private JPanel pnl_bottom;
    private JTable tbl_room_list;
    private JScrollPane scrl_room_property;
    private JTable tbl_room_property;
    private JTable tbl_room_price;
    private JButton btn_room_add;
    private JPanel room_sh_form;
    private JTextField fld_region_hotelName;
    private JTextField fld_check_in;
    private JTextField fld_check_out;
    private JTextField fld_adult_numb;
    private JTextField fld_child_numb;
    private JButton btn_room_sh;
    private JTextField fld_room_id;
    private JButton btn_room_reservation;
    private JPanel pnl_reservation_list;
    private JTable tbl_reservation_list;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    private JScrollBar scrollBar3;
    private JTextField fld_hotel_id;
    private JScrollBar scrl_room_list;
    private JScrollBar scrollBar5;
    private JScrollBar scrl_reservation_list;
    private JTextField fld_res_id;
    private JButton btn_res_delete;

    DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;

    DefaultTableModel mdl_hotel_type;
    private Object[] row_hotel_type;
    private int select_hotel_id;

    DefaultTableModel mdl_hotel_season;
    private Object[] row_hotel_season;

    DefaultTableModel mdl_room_list;
    private Object[] row_room_list;

    DefaultTableModel mdl_room_properties;
    private Object[] row_room_properties;
    private int select_room_id;

    private int adult_numb = 0;
    private int child_numb = 0;

    private String check_in;
    private String check_out;
    private int reservation_room_id;

    DefaultTableModel mdl_reservation_list;
    private Object[] row_reservation_list;


    private final Employee employee;

    public EmployeeView(Employee employee){
        this.employee = employee;
        add(wrapper);
        setSize(1200,600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        // 7- Employee (Acenta Çalışanı) için uygun yetkilerin olduğu bölüm.
        // 7- Section with appropriate authorizations for Employee (Agency Admin).

        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_list = {"id", "Hotel Adı", "Yıldız", "Tesis Özellikleri", "Adres", "Telefon", "E-mail"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(75);


        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                select_hotel_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
            }
            catch (Exception ex){

            }
            loadHotelTypeModel(select_hotel_id);
            loadHotelSeasonModel(select_hotel_id);
            select_hotel_id = 0;
        });


        mdl_hotel_type = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_type = {"Pansiyon Tipleri"};
        mdl_hotel_type.setColumnIdentifiers(col_hotel_type);
        row_hotel_type = new Object[col_hotel_type.length];
        //loadHotelTypeModel();
        tbl_hotel_type.setModel(mdl_hotel_type);
        tbl_hotel_type.getTableHeader().setReorderingAllowed(false);

        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String select_hotel_id = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString();
                fld_hotel_id.setText(select_hotel_id);
            }catch(Exception exception){
            }
        });


        mdl_hotel_season = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_season = {"Dönem Başlangıcı", "Dönem Bitişi"};
        mdl_hotel_season.setColumnIdentifiers(col_hotel_season);
        row_hotel_season = new Object[col_hotel_season.length];
        tbl_hotel_season.setModel(mdl_hotel_season);
        tbl_hotel_season.getTableHeader().setReorderingAllowed(false);


        mdl_room_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_list = {"id", "Hotel Adı", "Oda Tipi", "Stok", "Sezon Tarihleri", "Yetişkin Fiyatı","Çocuk Fiyatı", "Pansiyon Tipi"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        loadRoomListModel();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(75);

        mdl_room_properties = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_properties = {"Oda Özellikleri", "Yatak Bilgisi", "Alan(m2)" };
        mdl_room_properties.setColumnIdentifiers(col_room_properties);
        row_room_properties = new Object[col_room_properties.length];
        tbl_room_property.setModel(mdl_room_properties);
        tbl_room_property.getTableHeader().setReorderingAllowed(false);

        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                select_room_id = Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0).toString());
            }
            catch (Exception ex){

            }
            fld_room_id.setText(Integer.toString(select_room_id));
            loadRoomPropertiesModel(select_room_id);
            reservation_room_id = select_room_id;
            select_room_id = 0;
        });


        btn_logout.addActionListener(e -> {
            dispose();
            LoginView logGUI = new LoginView();

        });

        btn_hotel_add.addActionListener(e -> {
            AddHotelView hotelAdd = new AddHotelView(employee);
            hotelAdd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelModel();
                    tbl_hotel_list.getSelectionModel().clearSelection();
                }
            });
        });

        btn_room_add.addActionListener(e -> {
            AddRoomView addRoomView = new AddRoomView(employee);
            addRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomListModel();
                    tbl_room_list.getSelectionModel().clearSelection();
                    super.windowClosed(e);
                }
            });
        });

        btn_room_sh.addActionListener(e -> {
            String regionHotelName = fld_region_hotelName.getText();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            check_in = fld_check_in.getText().trim();
            check_out = fld_check_out.getText().trim();
            Date check_in_date = null;
            Date check_out_date = null;
            try {
                check_in_date = formatter.parse(check_in);
                check_out_date = formatter.parse(check_out);
            } catch (ParseException ex) {

            }
            /* 14 - Arama sonucuna uygun otellerin bilgileri (adres, yıldız, otel imkanları vb) ve odaların
            bilgileri (giriş, çıkış tarihi, yetişkin, çocuk sayısı, yatak sayısı, varsa, mini bar, TV vb)
            kullanıcıya gösterildiği bölüm.

            14 - Information about the hotels (address, star, hotel facilities, etc.) and rooms that match
            the search result information (check-in, check-out date, number of adults, children, number of beds,
            if available, mini bar, TV etc.) the section where it is shown to the user.
            */

            // 15 - Acente çalışanının, tarih aralığına, bölgeye veya otellere ve misafir bilgisine göre odaları aradığı bölüm.
            // 15 - The section where the agency employee searches for rooms by date range, region or hotels and guest information.

            String query = "SELECT * FROM hotel WHERE name LIKE '%{{name}}%' OR address LIKE '%{{address}}%'";
            query = query.replace("{{name}}", regionHotelName);
            query = query.replace("{{address}}", regionHotelName);
            ArrayList<Hotel> searchingHotel = Hotel.searchHotelList(query);

            ArrayList<Room> searchingRoom = new ArrayList<>();

            if (Helper.isFieldEmpty(fld_check_in) && Helper.isFieldEmpty(fld_check_out) && Helper.isFieldEmpty(fld_region_hotelName)){
                loadRoomListModel();
            }
            else if (Helper.isFieldEmpty(fld_check_in) && Helper.isFieldEmpty(fld_check_out)){
                for (Hotel hotel : searchingHotel){
                    Room obj = Room.getFetchByHotelID(hotel.getId());
                    searchingRoom.add(obj);
                }
                if (searchingRoom.size() == 0){
                    Helper.showMsg("Aradığınız kriterlere uygun oda bulunamadı!");
                }
                else {
                    loadRoomListModel(searchingRoom);
                }
            }
            else {
                for (Hotel obj : searchingHotel){
                    ArrayList<Season> searchingSeason = Season.getListByHotelID(obj.getId());
                    for (Season season : searchingSeason){
                        String season_start = season.getSeason_start();
                        String season_end = season.getSeason_end();
                        Date season_start_date = null;
                        Date season_end_date = null;
                        try {
                            season_start_date = formatter.parse(season_start);
                            season_end_date = formatter.parse(season_end);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        if (season_start_date.before(check_in_date) && season_end_date.after(check_out_date)){
                            Room room = Room.getFetchByHotelIDSeasonID(season.getId(), obj.getId());
                            if (room != null){
                                searchingRoom.add(room);
                            }

                        }
                    }
                }

                if (searchingRoom.size() == 0){
                    Helper.showMsg("Aradığınız kriterlere uygun oda bulunamadı!");
                }
                else {
                    loadRoomListModel(searchingRoom);
                }
            }
            fld_region_hotelName.setText(null);
        });

        btn_room_reservation.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_id) || Helper.isFieldEmpty(fld_check_in) || Helper.isFieldEmpty(fld_check_out) || Helper.isFieldEmpty(fld_adult_numb) || Helper.isFieldEmpty(fld_child_numb)){
                Helper.showMsg("Rezervasyon yapılacak odayı seçiniz! Giriş-Çıkış tarihlerini ve misafir sayıları kısmını doldurunuz!");
            }
            else {
                Room room = Room.getFetch(reservation_room_id);
                adult_numb = Integer.parseInt(fld_adult_numb.getText());
                child_numb = Integer.parseInt(fld_child_numb.getText());
                check_in = fld_check_in.getText().trim();
                check_out = fld_check_out.getText().trim();

                ReservationView resGUI = new ReservationView(room, adult_numb, child_numb, check_in, check_out);
                resGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        fld_check_in.setText(null);
                        fld_check_out.setText(null);
                        fld_adult_numb.setText(null);
                        fld_child_numb.setText(null);
                        fld_room_id.setText(String.valueOf(0));
                        super.windowClosed(e);
                        loadRoomListModel();
                        loadReservationModel();
                    }
                });
            }
        });

        mdl_reservation_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_reservation_list = {"id", "Ad Soyad", "Telefon", "E-mail", "Not", "Hotel Adı", "Oda tipi", "Giriş Tarihi", "Çıkış Tarihi", "Yetişkin Sayısı", "Çocuk Sayısı", "Toplam Ücret"};
        mdl_reservation_list.setColumnIdentifiers(col_reservation_list);
        row_reservation_list = new Object[col_reservation_list.length];
        loadReservationModel();
        tbl_reservation_list.setModel(mdl_reservation_list);
        tbl_reservation_list.getTableHeader().setReorderingAllowed(false);
        tbl_reservation_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_reservation_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String selected_res_id = tbl_reservation_list.getValueAt(tbl_reservation_list.getSelectedRow(), 0).toString();
                fld_res_id.setText(selected_res_id);
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        });


        /*btn_hotel_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_id)) {
                Helper.showMsg("fill");
            } else {
                int hotel_id = Integer.parseInt(fld_hotel_id.getText());
                if (Hotel.delete(hotel_id)) {
                    Helper.showMsg("done");
                    loadHotelModel();
                } else {
                    Helper.showMsg("error");
                }

            }
        });*/

        // Rezervasyon silme butonu işlevi
        // Reservation delete button function
        btn_res_delete.addActionListener(e -> {
           if(Helper.isFieldEmpty(fld_res_id)){
               Helper.showMsg("fill");
           }else{
               int res_id = Integer.parseInt(fld_res_id.getText());
               if(ResInfo.deleteRes(res_id)){
                   Helper.showMsg("done");
                   loadReservationModel();
               }else{
                   Helper.showMsg("error");
               }
           }
        });
    }

    // 18 - Acente çalışanlarının sistem üzerinden yapılan rezervasyonları listelediği bölüm.
    // 18 - The section where agency employees list the reservations made through the system
    private void loadReservationModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservation_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (ResInfo obj : ResInfo.getList()){
            i = 0;
            row_reservation_list[i++] = obj.getId();
            row_reservation_list[i++] = obj.getClient_name();
            row_reservation_list[i++] = obj.getClient_phone();
            row_reservation_list[i++] = obj.getClient_email();
            row_reservation_list[i++] = obj.getClient_note();
            row_reservation_list[i++] = Hotel.getFetch(Room.getFetch(obj.getRoom_id()).getHotel_id()).getName();
            row_reservation_list[i++] = Room.getFetch(obj.getRoom_id()).getRoom_type();
            row_reservation_list[i++] = obj.getCheck_in();
            row_reservation_list[i++] = obj.getCheck_out();
            row_reservation_list[i++] = obj.getAdult_numb();
            row_reservation_list[i++] = obj.getChild_numb();
            row_reservation_list[i++] = obj.getTotal_price();

            mdl_reservation_list.addRow(row_reservation_list);
        }
    }

    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : Hotel.getList()){
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getStar();
            row_hotel_list[i++] = obj.getProperty();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getPhone();
            row_hotel_list[i++] = obj.getEmail();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    private void loadHotelTypeModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_type.getModel();
        clearModel.setRowCount(0);
        int i;
        for (com.TurizmAcentaSistemi.Model.Type obj : com.TurizmAcentaSistemi.Model.Type.getListByHotelID(id)){
            i = 0;
            row_hotel_type[i++] = obj.getType();
            mdl_hotel_type.addRow(row_hotel_type);
        }
    }

    private void loadHotelSeasonModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_season.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Season obj : Season.getListByHotelID(id)){
            i = 0;
            row_hotel_season[i++] = obj.getSeason_start();
            row_hotel_season[i++] = obj.getSeason_end();
            mdl_hotel_season.addRow(row_hotel_season);
        }
    }


    private void loadRoomListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Room obj : Room.getList()){
            i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = Hotel.getFetch(obj.getHotel_id()).getName();
            row_room_list[i++] = obj.getRoom_type();
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = Season.getFetch(obj.getSeason_id()).getSeason_start() + " - " + Season.getFetch(obj.getSeason_id()).getSeason_end();
            row_room_list[i++] = obj.getAdult_price();
            row_room_list[i++] = obj.getChild_price();
            row_room_list[i++] = com.TurizmAcentaSistemi.Model.Type.getFetch(obj.getHotel_type_id()).getType();
            mdl_room_list.addRow(row_room_list);
        }
    }

    private void loadRoomListModel(ArrayList<Room> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i;
        if (list == null){
            Helper.showMsg("Aradığınız kriterlere uygun oda bulunamadı!");
        }
        else{
            for (Room obj : list){
                i = 0;
                row_room_list[i++] = obj.getId();
                row_room_list[i++] = Hotel.getFetch(obj.getHotel_id()).getName();
                row_room_list[i++] = obj.getRoom_type();
                row_room_list[i++] = obj.getStock();
                row_room_list[i++] = Season.getFetch(obj.getSeason_id()).getSeason_start() + " - " + Season.getFetch(obj.getSeason_id()).getSeason_end();
                row_room_list[i++] = obj.getAdult_price();
                row_room_list[i++] = obj.getChild_price();
                row_room_list[i++] = com.TurizmAcentaSistemi.Model.Type.getFetch(obj.getHotel_type_id()).getType();
                mdl_room_list.addRow(row_room_list);
            }
        }

    }

    private void loadRoomPropertiesModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_property.getModel();
        clearModel.setRowCount(0);
        int i;
        for (RoomProp obj : RoomProp.getListByRoomID(id)){
            i = 0;
            row_room_properties[i++] = obj.getProperty();
            row_room_properties[i++] = obj.getBed();
            row_room_properties[i++] = obj.getArea();
            mdl_room_properties.addRow(row_room_properties);
        }
    }
}
