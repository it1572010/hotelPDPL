package com.hotel.dao;

import com.hotel.entity.Guest;
import com.hotel.entity.Hotel;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.utility.DaoService;
import com.hotel.utility.Koneksi;
import com.hotel.utility.ViewUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Anthony
 */
public class ReservationDaoImpl implements DaoService<Reservation> {

    @Override
    public int addData(Reservation object) {
        int result = 0;
        try {
            Connection connection = Koneksi.createConnection();
            Timestamp t = new Timestamp(System.currentTimeMillis());
            String query
                    = "INSERT INTO reservation(tglMasuk, tglKeluar,tglPesan,hotel_kodeHotel, room_noKamar, guest_idguest) VALUES(?, ?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getTglMasuk().toString());
                ps.setString(2, object.getTglKeluar().toString());
                ps.setTimestamp(3, t);
                ps.setInt(4, object.getHotel().getIdHotel());
                ps.setString(5, object.getRoom().getNoKamar());
                ps.setInt(6, object.getGuest().getIdGuest());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReservationDaoImpl.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Reservation object) {
        int result = 0;
        try {
            Connection connection = Koneksi.createConnection();
            String query
                    = "UPDATE reservation SET tglMasuk=?, tglKeluar=?,hotel_kodeHotel=?, room_noKamar=?, guest_idguest=? WHERE idReservation = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getTglMasuk().toString());
                ps.setString(2, object.getTglKeluar().toString());
                ps.setInt(3, object.getHotel().getIdHotel());
                ps.setString(4, object.getRoom().getNoKamar());
                ps.setInt(5, object.getGuest().getIdGuest());
                ps.setInt(6, object.getIdReservation());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReservationDaoImpl.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return result;
    }

    @Override
    public int deleteData(Reservation object) {
        int result = 0;
        try {
            Connection connection = Koneksi.createConnection();
            String query
                    = "DELETE FROM reservation WHERE idReservation=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getIdReservation());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeesDaoImpl.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return result;
    }

    @Override
    public List<Reservation> showAllData() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Connection connection = Koneksi.createConnection();
            String query
                    = "SELECT r.*,h.*, ro.*,g.*,m.* FROM reservation r JOIN hotel h ON r.hotel_kodeHotel = h.idHotel JOIN room ro ON r.room_noKamar = ro.noKamar JOIN guest g ON r.guest_idguest = g.idGuest JOIN member m ON g.member_idMember = m.idMember ORDER BY r.idReservation";
            try (PreparedStatement ps = connection.prepareStatement(query);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setIdReservation(rs.getInt("idReservation"));
                    reservation.setTglMasuk(rs.getDate("tglMasuk"));
                    reservation.setTglKeluar(rs.getDate("tglKeluar"));
                    Hotel hotel = new Hotel();
                    hotel.setNamaHotel(rs.getString("namaHotel"));
                    hotel.setAlamatHotel(rs.getString("alamatHotel"));
                    hotel.setIdHotel(rs.getInt("hotel_kodeHotel"));
                    reservation.setHotel(hotel);
                    Room room = new Room();
                    room.setNoKamar(rs.getString("noKamar"));
                    room.setHargaKamar(rs.getInt("hargaKamar"));
                    room.setStatusKamar(rs.getString("statusKamar"));
                    room.setTipeKamar(rs.getString("tipeKamar"));
                    reservation.setRoom(room);
                    Guest guest = new Guest();
                    guest.setAlamatTamu(rs.getString("alamatTamu"));
                    guest.setFirstName(rs.getString("firstName"));
                    guest.setLastName(rs.getString("lastName"));
                    guest.setNoHandphone(rs.getString("noHandPhone"));
                    Member member = new Member();
                    member.setIdMember(rs.getInt("idMember"));
                    member.setTipeMember(rs.getString("tipeMember"));
                    guest.setMember(member);
                    guest.setIdGuest(rs.getInt("idGuest"));
                    reservation.setGuest(guest);
                    reservations.add(reservation);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return reservations;
    }

}
