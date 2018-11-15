package com.hotel;

import com.hotel.dao.EmployeesDaoImpl;
import com.hotel.dao.FasilitasDaoImpl;
import com.hotel.dao.FasilitasHasGuestDaoImpl;
import com.hotel.dao.GuestDaoImpl;
import com.hotel.dao.MemberDaoImpl;
import com.hotel.dao.ReservationDaoImpl;
import com.hotel.dao.RoleDaoImpl;
import com.hotel.dao.RoomDaoImpl;
import com.hotel.entity.Employees;
import com.hotel.entity.Fasilitas;
import com.hotel.entity.FasilitasHasGuest;
import com.hotel.entity.Guest;
import com.hotel.entity.Hotel;
import com.hotel.entity.Member;
import com.hotel.entity.Reservation;
import com.hotel.entity.Role;
import com.hotel.entity.Room;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Anthony
 */
public class MainApp {

    public static int menu;
    public static int subMenu;

    public static RoleDaoImpl roleDaoImpl;
    public static ReservationDaoImpl reservationDaoImpl;
    public static RoomDaoImpl roomDaoImpl;
    public static FasilitasDaoImpl fasilitasDaoImpl;
    public static EmployeesDaoImpl employeesDaoImpl;
    public static GuestDaoImpl guestDaoImpl;
    public static MemberDaoImpl memberDaoImpl;
    public static FasilitasHasGuestDaoImpl fasilitasHasGuestDaoImpl;
    public static List<FasilitasHasGuest> fasilitasHasGuests;
    public static List<Member> members;
    public static List<Guest> guests;
    public static List<Employees> employeeses;
    public static List<Fasilitas> fasilitases;
    public static List<Room> rooms;
    public static List<Reservation> reservations;
    public static List<Role> roles;

    public static void main(String[] args) throws ParseException {
        Scanner s = new Scanner(System.in);
        OUTER:
        while (true) {
            printMenu();
            menu = s.nextInt();
            switch (menu) {
                case 1:
                    subMenu(menu);
                    subMenu = s.nextInt();
                    if (subMenu == 1) {
                        members = getMembers();
                        for (Member x : members) {
                            System.out.println(x.getIdMember() + " - " + x.
                                    getTipeMember());
                        }
                    } else if (subMenu == 2) {
                        System.out.print("Masukkan tipe member : ");
                        String tipeMember = s.next();
                        Member member = new Member();
                        member.setTipeMember(tipeMember);
                        getMemberDaoImpl().addData(member);
                        getMembers().clear();
                        getMembers().addAll(getMemberDaoImpl().showAllData());

                    } else if (subMenu == 3) {
                        members = getMembers();
                        for (Member x : members) {
                            System.out.println(x.getIdMember() + " - " + x.
                                    getTipeMember());
                        }
                        System.out.
                                print("Masukkan id member yang mau diubah : ");
                        int idMember = s.nextInt();
                        System.out.print("Masukkan tipe member yang baru : ");
                        String tipeMember = s.next();
                        Member member = new Member();
                        member.setIdMember(idMember);
                        member.setTipeMember(tipeMember);
                        getMemberDaoImpl().updateData(member);
                        getMembers().clear();
                        getMembers().addAll(getMemberDaoImpl().showAllData());

                    } else if (subMenu == 4) {
                        members = getMembers();
                        for (Member x : members) {
                            System.out.println(x.getIdMember() + " - " + x.
                                    getTipeMember());
                        }
                        System.out.
                                print("Masukkan id member yang mau dihapus : ");
                        int idMember = s.nextInt();
                        Member member = new Member();
                        member.setIdMember(idMember);
                        getMemberDaoImpl().deleteData(member);
                        getMembers().clear();
                        getMembers().addAll(getMemberDaoImpl().showAllData());
                    }
                    slightPause();
                    break;
                case 2:
                    subMenu(menu);
                    subMenu = s.nextInt();
                    if (subMenu == 1) {
                        guests = getGuests();
                        for (Guest x : guests) {
                            System.out.println("Nama : " + x.getFirstName()
                                    + "." + x.
                                            getLastName() + "  Alamat : " + x.
                                            getAlamatTamu() + "  No.HP : " + x.
                                            getNoHandphone() + "  Member : "
                                    + x.
                                            getMember().getTipeMember());
                        }
                    } else if (subMenu == 2) {
                        System.out.print("Masukkan nama depan : ");
                        String namaDepan = s.next();
                        System.out.print("Masukkan nama belakang : ");
                        String namaBelakang = s.next();
                        System.out.print("Masukkan alamat : ");
                        s.nextLine();
                        String alamat = s.nextLine();
                        System.out.print("No Handphone : ");
                        String noHandphone = s.next();
                        members = getMembers();
                        for (Member x : members) {
                            System.out.println(x.getIdMember() + " - " + x.
                                    getTipeMember());
                        }
                        System.out.print("Tipe Member : ");
                        int tipeMember = s.nextInt();
                        Guest guest = new Guest();
                        guest.setFirstName(namaDepan);
                        guest.setLastName(namaBelakang);
                        guest.setAlamatTamu(alamat);
                        guest.setNoHandphone(noHandphone);
                        Member member = new Member();
                        member.setIdMember(tipeMember);
                        guest.setMember(member);
                        getGuestDaoImpl().addData(guest);
                        getGuests().clear();
                        getGuests().addAll(getGuestDaoImpl().showAllData());

                    } else if (subMenu == 3) {
                        guests = getGuests();
                        for (Guest x : guests) {
                            System.out.println("Id : " + x.getIdGuest()
                                    + " Nama : " + x.getFirstName()
                                    + "." + x.getLastName() + "  Alamat : " + x.
                                    getAlamatTamu() + "  No.HP : " + x.
                                            getNoHandphone() + "  Member : "
                                    + x.getMember().getTipeMember());
                        }
                        System.out.
                                print("Masukkan id tamu yang mau diubah : ");
                        int idTamu = s.nextInt();
                        System.out.
                                print("Masukkan nama depan tamu yang baru : ");
                        String namaDepanTamu = s.next();
                        System.out.print(
                                "Masukkan nama belakang tamu yang baru : ");
                        String namaBelakangTamu = s.next();
                        System.out.print("Masukkan alamat tamu yang baru : ");
                        String alamatTamu = s.next();
                        System.out.print("Masukkan no HP tamu yang baru : ");
                        String noHpTamu = s.next();
                        members = getMembers();
                        for (Member x : members) {
                            System.out.println(x.getIdMember() + " - " + x.
                                    getTipeMember());
                        }
                        System.out.print("Masukkan tipe member yang baru : ");
                        int idMember = s.nextInt();
                        Member member = new Member();
                        member.setIdMember(idMember);
                        Guest g = new Guest();
                        g.setIdGuest(idTamu);
                        g.setFirstName(namaDepanTamu);
                        g.setLastName(namaBelakangTamu);
                        g.setAlamatTamu(alamatTamu);
                        g.setNoHandphone(noHpTamu);
                        g.setMember(member);
                        getGuestDaoImpl().updateData(g);
                        getGuests().clear();
                        getGuests().addAll(getGuestDaoImpl().showAllData());

                    } else if (subMenu == 4) {
                        guests = getGuests();
                        for (Guest x : guests) {
                            System.out.println("Id : " + x.getIdGuest()
                                    + " Nama : " + x.getFirstName()
                                    + "." + x.getLastName() + "  Alamat : " + x.
                                    getAlamatTamu() + "  No.HP : " + x.
                                            getNoHandphone() + "  Member : "
                                    + x.getMember().getTipeMember());
                        }
                        System.out.
                                print("Masukkan id tamu yang mau dihapus : ");
                        int idTamu = s.nextInt();
                        Guest guest = new Guest();
                        guest.setIdGuest(idTamu);
                        getGuestDaoImpl().deleteData(guest);
                        getGuests().clear();
                        getGuests().addAll(getGuestDaoImpl().showAllData());

                    }
                    slightPause();
                    break;
                case 3:
                    subMenu(menu);
                    subMenu = s.nextInt();
                    if (subMenu == 1) {
                        employeeses = getEmployeeses();
                        for (Employees x : employeeses) {
                            System.out.println("No KTP : " + x.getNoKTP()
                                    + " Nama :" + x.getNamaEmployee()
                                    + "  Alamat : " + x.getAlamatEmployee()
                                    + "  Jenis Kelamin : " + x.getJenisKelamin()
                                    + "  Role : "
                                    + x.getRole().getNamaRole());
                        }
                    } else if (subMenu == 2) {
                        System.out.print("Masukkan Id : ");
                        String id = s.next();
                        System.out.print("Masukkan No KTP : ");
                        String noKTP = s.next();
                        System.out.print("Masukkan Nama Pegawai : ");
                        s.nextLine();
                        String namaPegawai = s.nextLine();
                        System.out.print("Masukkan Alamat Pegawai : ");
                        String alamatPegawai = s.nextLine();
                        System.out.print("Masukkan Jenis Kelamin : ");
                        String jenisKelamin = s.next();
                        roles = getRoles();
                        for (Role x : roles) {
                            System.out.println(x.getIdRole() + ". "
                                    + x.getNamaRole());
                        }
                        System.out.print("Masukkan No Role : ");
                        int role = s.nextInt();
                        Employees employee = new Employees();
                        employee.setEmployee_id(id);
                        employee.setNamaEmployee(namaPegawai);
                        employee.setAlamatEmployee(alamatPegawai);
                        employee.setNoKTP(noKTP);
                        employee.setJenisKelamin(jenisKelamin);
                        Role r = new Role();
                        r.setIdRole(role);
                        employee.setRole(r);
                        Hotel h = new Hotel();
                        h.setIdHotel(1);
                        employee.setHotel(h);
                        getEmployeesDaoImpl().addData(employee);
                        getEmployeeses().clear();
                        getEmployeeses().addAll(getEmployeesDaoImpl().
                                showAllData());

                    } else if (subMenu == 3) {
                        employeeses = getEmployeeses();
                        for (Employees x : employeeses) {
                            System.out.println("ID : " + x.getEmployee_id()
                                    + " No KTP : " + x.getNoKTP()
                                    + " Nama :" + x.getNamaEmployee()
                                    + "  Alamat : " + x.getAlamatEmployee()
                                    + "  Jenis Kelamin : " + x.getJenisKelamin()
                                    + "  Role : "
                                    + x.getRole().getNamaRole());
                        }
                        System.out.
                                print("Masukan ID pegawai yang mau diubah : ");
                        String id = s.next();
                        System.out.print("No KTP Baru : ");
                        String noKTP = s.next();
                        System.out.print("Nama Baru : ");
                        s.nextLine();
                        String nama = s.nextLine();
                        System.out.print("Alamat Baru : ");
                        String alamat = s.nextLine();
                        System.out.print("Jenis Kelamin : ");
                        String jenisKelamin = s.next();
                        roles = getRoles();
                        for (Role x : roles) {
                            System.out.println(x.getIdRole() + ". "
                                    + x.getNamaRole());
                        }
                        System.out.print("Masukkan No Role : ");
                        int role = s.nextInt();
                        Hotel hotel = new Hotel();
                        hotel.setIdHotel(1);
                        Employees updateEmployees = new Employees(id, noKTP,
                                nama, alamat, jenisKelamin, roles.get(role - 1),
                                hotel);
                        getEmployeesDaoImpl().updateData(updateEmployees);
                        getEmployeeses().clear();
                        getEmployeeses().addAll(getEmployeesDaoImpl().
                                showAllData());

                    } else if (subMenu == 4) {
                        employeeses = getEmployeeses();
                        for (Employees x : employeeses) {
                            System.out.println("ID : " + x.getEmployee_id()
                                    + " No KTP : " + x.getNoKTP()
                                    + " Nama :" + x.getNamaEmployee()
                                    + "  Alamat : " + x.getAlamatEmployee()
                                    + "  Jenis Kelamin : " + x.getJenisKelamin()
                                    + "  Role : "
                                    + x.getRole().getNamaRole());
                        }
                        System.out.
                                print("Masukan ID pegawai yang mau dihapus : ");
                        String id = s.next();
                        Employees employees = new Employees();
                        employees.setEmployee_id(id);

                        getEmployeesDaoImpl().deleteData(employees);
                        getEmployeeses().clear();
                        getEmployeeses().addAll(getEmployeesDaoImpl().
                                showAllData());

                    }
                    slightPause();
                    break;
                case 4:
                    subMenu(menu);
                    subMenu = s.nextInt();
                    if (subMenu == 1) {
                        fasilitases = getFasilitases();
                        for (Fasilitas x : fasilitases) {
                            System.out.println("Nama : " + x.getNamaFasilitas()
                                    + " Harga : " + x.getHargaFasilitas());
                        }
                    } else if (subMenu == 2) {
                        System.out.print("Nama Fasilitas : ");
                        String namaFasilitas = s.next();
                        System.out.print("Harga Fasilitas : ");
                        int hargaFasilitas = s.nextInt();
                        Fasilitas fasilitas = new Fasilitas();
                        fasilitas.setHargaFasilitas(hargaFasilitas);
                        fasilitas.setNamaFasilitas(namaFasilitas);
                        getFasilitasDaoImpl().addData(fasilitas);
                        getFasilitases().clear();
                        getFasilitases().addAll(getFasilitasDaoImpl().
                                showAllData());

                    } else if (subMenu == 3) {
                        fasilitases = getFasilitases();
                        for (Fasilitas x : fasilitases) {
                            System.out.println("Id : " + x.getIdFasilitas()
                                    + " Nama : " + x.getNamaFasilitas()
                                    + " Harga : " + x.getHargaFasilitas());
                        }
                        System.out.
                                print("Masukkan id fasilitas yang mau diubah : ");
                        int idFasilitas = s.nextInt();
                        System.out.print("Masukkan nama fasilitas yang baru : ");
                        String namaFasilitas = s.next();
                        System.out.
                                print("Masukkan harga fasilitas yang baru : ");
                        int hargaFasilitas = s.nextInt();
                        Fasilitas fasilitas = new Fasilitas();
                        fasilitas.setNamaFasilitas(namaFasilitas);
                        fasilitas.setHargaFasilitas(hargaFasilitas);
                        getFasilitasDaoImpl().updateData(fasilitas);
                        getFasilitases().clear();
                        getFasilitases().addAll(getFasilitasDaoImpl().
                                showAllData());

                    } else if (subMenu == 4) {
                        fasilitases = getFasilitases();
                        for (Fasilitas x : fasilitases) {
                            System.out.println("Id : " + x.getIdFasilitas()
                                    + " Nama : " + x.getNamaFasilitas()
                                    + " Harga : " + x.getHargaFasilitas());
                        }
                        System.out.
                                print("Masukkan id fasilitas yang mau dihapus : ");
                        int idFasilitas = s.nextInt();
                        Fasilitas fasilitas = new Fasilitas();
                        fasilitas.setIdFasilitas(idFasilitas);
                        getFasilitasDaoImpl().deleteData(fasilitas);
                        getFasilitases().clear();
                        getFasilitases().addAll(getFasilitasDaoImpl().
                                showAllData());

                    }
                    slightPause();
                    break;
                case 5:
                    subMenu(menu);
                    subMenu = s.nextInt();
                    if (subMenu == 1) {
                        rooms = getRooms();
                        for (Room x : rooms) {
                            System.out.println("No Kamar : " + x.getNoKamar()
                                    + " - Tipe : " + x.getTipeKamar()
                                    + " - Status : " + x.getStatusKamar()
                                    + " - Harga : " + x.getHargaKamar());
                        }
                    } else if (subMenu == 2) {
                        System.out.print("No Kamar : ");
                        String noKamar = s.next();
                        System.out.print("Harga Kamar : ");
                        int hargaKamar = s.nextInt();
                        System.out.print("Tipe Kamar : ");
                        String tipeKamar = s.next();
                        Room room = new Room(noKamar, hargaKamar, tipeKamar,
                                "Available");
                        getRoomDaoImpl().addData(room);
                        getRooms().clear();
                        getRooms().addAll(getRoomDaoImpl().
                                showAllData());

                    } else if (subMenu == 3) {
                        rooms = getRooms();
                        for (Room x : rooms) {
                            System.out.println("No Kamar : " + x.getNoKamar()
                                    + " - Tipe : " + x.getTipeKamar()
                                    + " - Status : " + x.getStatusKamar()
                                    + " - Harga : " + x.getHargaKamar());
                        }
                        System.out.
                                print("Masukkan no kamar yang mau diubah : ");
                        String noKamar = s.next();
                        System.out.print("Masukkan tipe kamar yang baru : ");
                        String tipeKamar = s.next();
                        System.out.
                                print("Masukkan status kamar yang baru : ");
                        String statusKamar = s.next();
                        System.out.
                                print("Masukkan harga kamar yang baru : ");
                        int hargaKamar = s.nextInt();
                        Room room = new Room();
                        room.setNoKamar(noKamar);
                        room.setTipeKamar(tipeKamar);
                        room.setStatusKamar(statusKamar);
                        room.setHargaKamar(hargaKamar);
                        getRoomDaoImpl().updateData(room);
                        getRooms().clear();
                        getRooms().addAll(getRoomDaoImpl().showAllData());

                    } else if (subMenu == 4) {
                        rooms = getRooms();
                        for (Room x : rooms) {
                            System.out.println("No Kamar : " + x.getNoKamar()
                                    + " - Tipe : " + x.getTipeKamar()
                                    + " - Status : " + x.getStatusKamar()
                                    + " - Harga : " + x.getHargaKamar());
                        }
                        System.out.
                                print("Masukkan no kamar yang mau dihapus : ");
                        String noKamar = s.next();
                        Room room = new Room();
                        room.setNoKamar(noKamar);
                        getRoomDaoImpl().deleteData(room);
                        getRooms().clear();
                        getRooms().addAll(getRoomDaoImpl().showAllData());

                    }
                    slightPause();
                    break;
                case 6:
                    subMenu(menu);
                    subMenu = s.nextInt();
                    if (subMenu == 1) {
                        reservations = getReservations();
                        for (Reservation x : reservations) {
                            System.out.println(x.getIdReservation()
                                    + ". Tanggal masuk : " + x.
                                            getTglMasuk()
                                    + " - Tanggal keluar : " + x.getTglKeluar()
                                    + " - Hotel : " + x.getHotel().
                                            getNamaHotel()
                                    + " - Room : " + x.getRoom().getNoKamar()
                                    + " - Guest : " + x.getGuest().
                                            getFirstName() + " " + x.getGuest().
                                            getLastName());
                        }
                    } else if (subMenu == 2) {
                        System.out.println("Sudah Pernah Menginap ?");
                        String a = s.next();
                        Guest guest = new Guest();
                        if (a.toLowerCase().equals("sudah")) {
                            System.out.print("No Handphone : ");
                            String noHandphone = s.next();
                            List<Guest> listCheck = getGuests();
                            for (Guest g : listCheck) {
                                if (g.getNoHandphone().equals(noHandphone)) {
                                    guest = g;
                                }
                            }
                        } else {
                            System.out.print("Masukkan nama depan : ");
                            String namaDepan = s.next();
                            System.out.print("Masukkan nama belakang : ");
                            String namaBelakang = s.next();
                            System.out.print("Masukkan alamat : ");
                            s.nextLine();
                            String alamat = s.nextLine();
                            System.out.print("No Handphone : ");
                            String noHandphone = s.next();
                            members = getMembers();
                            for (Member x : members) {
                                System.out.println(x.getIdMember() + " - " + x.
                                        getTipeMember());
                            }
                            System.out.print("Tipe Member : ");
                            int tipeMember = s.nextInt();
                            guest.setFirstName(namaDepan);
                            guest.setLastName(namaBelakang);
                            guest.setAlamatTamu(alamat);
                            guest.setNoHandphone(noHandphone);
                            Member member = new Member();
                            member.setIdMember(tipeMember);
                            guest.setMember(member);
                            getGuestDaoImpl().addData(guest);
                            getGuests().clear();
                            getGuests().addAll(getGuestDaoImpl().showAllData());
                            List<Guest> listCheck = getGuests();
                            for (Guest g : listCheck) {
                                if (g.getNoHandphone().equals(noHandphone)) {
                                    guest = g;
                                }
                            }
                        }

                        System.out.print("Tanggal Masuk : ");
                        String tanggalMasuk = s.next();
                        System.out.print("Tanggal Keluar : ");
                        String tanggalKeluar = s.next();
                        System.out.print("No Kamar : ");
                        String noKamar = s.next();
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateMasuk = new java.sql.Date(format.parse(
                                tanggalMasuk).getTime());
                        Date dateKeluar = new java.sql.Date(format.parse(
                                tanggalKeluar).getTime());

                        Reservation reservation = new Reservation();
                        reservation.setTglMasuk(dateMasuk);
                        reservation.setTglKeluar(dateKeluar);
                        Room room = new Room();
                        room.setNoKamar(noKamar);
                        reservation.setRoom(room);
                        Hotel hotel = new Hotel();
                        hotel.setIdHotel(1);
                        reservation.setHotel(hotel);
                        reservation.setGuest(guest);

                        getReservationDaoImpl().addData(reservation);
                        getReservations().clear();
                        getReservations().addAll(getReservationDaoImpl().
                                showAllData());

                    } else if (subMenu == 3) {
                        reservations = getReservations();
                        for (Reservation x : reservations) {
                            System.out.println(x.getIdReservation()
                                    + ". Tanggal masuk : " + x.
                                            getTglKeluar()
                                    + " - Tanggal keluar : " + x.getTglMasuk()
                                    + " - Hotel : " + x.getHotel().
                                            getNamaHotel()
                                    + " - Room : " + x.getRoom().getNoKamar()
                                    + " - Guest : " + x.getGuest().
                                            getFirstName() + " " + x.getGuest().
                                            getLastName());
                        }
                        System.out.
                                print("Masukkan No Reservasi yang mau diubah : ");
                        int noReservasi = s.nextInt();
                        ///////////////////////////////////////////////////////////////////////////////
                        int idGuest = 0;
                        for (Reservation res : getReservations()) {
                            if (res.getIdReservation() == noReservasi) {
                                idGuest = res.getGuest().getIdGuest();
                            }
                        }

                        Guest guest = new Guest();
                        System.out.print("Masukkan nama depan : ");
                        String namaDepan = s.next();
                        System.out.print("Masukkan nama belakang : ");
                        String namaBelakang = s.next();
                        System.out.print("Masukkan alamat : ");
                        s.nextLine();
                        String alamat = s.nextLine();
                        System.out.print("No Handphone : ");
                        String noHandphone = s.next();
                        members = getMembers();
                        for (Member x : members) {
                            System.out.println(x.getIdMember() + " - " + x.
                                    getTipeMember());
                        }
                        System.out.print("Tipe Member : ");
                        int tipeMember = s.nextInt();
                        guest.setFirstName(namaDepan);
                        guest.setLastName(namaBelakang);
                        guest.setAlamatTamu(alamat);
                        guest.setNoHandphone(noHandphone);
                        Member member = new Member();
                        member.setIdMember(tipeMember);
                        guest.setMember(member);
                        guest.setIdGuest(idGuest);
                        getGuestDaoImpl().updateData(guest);
                        getGuests().clear();
                        getGuests().addAll(getGuestDaoImpl().showAllData());

                        ////////////////////////////////////////////////////////////////
                        System.out.print("Tanggal Masuk : ");
                        String tanggalMasuk = s.next();
                        System.out.print("Tanggal Keluar : ");
                        String tanggalKeluar = s.next();
                        System.out.print("No Kamar : ");
                        String noKamar = s.next();
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateMasuk = new java.sql.Date(format.parse(
                                tanggalMasuk).getTime());
                        Date dateKeluar = new java.sql.Date(format.parse(
                                tanggalKeluar).getTime());

                        Reservation reservation = new Reservation();
                        reservation.setTglMasuk(dateMasuk);
                        reservation.setTglKeluar(dateKeluar);
                        Room room = new Room();
                        room.setNoKamar(noKamar);
                        reservation.setRoom(room);
                        Hotel hotel = new Hotel();
                        hotel.setIdHotel(1);
                        reservation.setIdReservation(noReservasi);
                        reservation.setHotel(hotel);
                        reservation.setGuest(guest);
                        getReservationDaoImpl().updateData(reservation);
                        getReservations().clear();
                        getReservations().addAll(getReservationDaoImpl().
                                showAllData());
                        ///////////////////////////////////////////////////////////////////////////////

                    } else if (subMenu == 4) {
                        reservations = getReservations();
                        for (Reservation x : reservations) {
                            System.out.println(x.getIdReservation()
                                    + ". Tanggal masuk : " + x.
                                            getTglKeluar()
                                    + " - Tanggal keluar : " + x.getTglMasuk()
                                    + " - Hotel : " + x.getHotel().
                                            getNamaHotel()
                                    + " - Room : " + x.getRoom().getNoKamar()
                                    + " - Guest : " + x.getGuest().
                                            getFirstName() + " " + x.getGuest().
                                            getLastName());
                        }
                        System.out.
                                print("Masukkan No Reservasi yang mau dihapus : ");
                        int noReservasi = s.nextInt();
                        Reservation reservation = new Reservation();
                        reservation.setIdReservation(noReservasi);
                        getReservationDaoImpl().deleteData(reservation);
                        getReservations().clear();
                        getReservations().addAll(getReservationDaoImpl().
                                showAllData());
                    }
                    slightPause();
                    break;
                case 7:
                    System.out.println("Sub Menu : ");
                    System.out.println("1. Lihat extra fasilitas");
                    System.out.println("2. Tambah extra fasilitas");
                    System.out.println("0. Back");
                    System.out.print("Pilihan anda : ");
                    subMenu = s.nextInt();
                    if (subMenu == 1) {
                        fasilitasHasGuests = getFasilitasHasGuests();
                        for (FasilitasHasGuest x : fasilitasHasGuests) {
                            String nokmr = "-";
                            reservations = getReservations();
                            for (Reservation reservation : reservations) {
                                if (reservation.getGuest().getIdGuest() == x.
                                        getGuest().getIdGuest()) {
                                    nokmr = reservation.getRoom().getNoKamar();
                                }
                            }
                            System.out.println(nokmr);
                            System.out.println("Kamar : " + nokmr + " Nama : "
                                    + x.getGuest().
                                            getFirstName() + " " + x.getGuest().
                                            getLastName() + " Fasilitas : " + x.
                                            getFasilitas().getNamaFasilitas());
                        }
                    } else if (subMenu == 2) {
                        fasilitases = getFasilitases();
                        for (Fasilitas x : fasilitases) {
                            System.out.println("Id : " + x.getIdFasilitas()
                                    + " Nama : " + x.getNamaFasilitas()
                                    + " Harga : " + x.getHargaFasilitas());
                        }
                        System.out.print("Masukkan id fasilitas : ");
                        int idFasilitas = s.nextInt();
                        for (Guest x : guests) {
                            System.out.println("Id : " + x.getIdGuest()
                                    + " Nama : " + x.getFirstName()
                                    + "." + x.getLastName() + "  Alamat : " + x.
                                    getAlamatTamu() + "  No.HP : " + x.
                                            getNoHandphone() + "  Member : "
                                    + x.getMember().getTipeMember());
                        }
                        System.out.print("Masukkan id tamu : ");
                        int idTamu = s.nextInt();
                        FasilitasHasGuest fasilitasHasGuest
                                = new FasilitasHasGuest();
                        Fasilitas fasilitas = new Fasilitas();
                        fasilitas.setIdFasilitas(idFasilitas);
                        Guest guest = new Guest();
                        guest.setIdGuest(idTamu);
                        getFasilitasHasGuestDaoImpl().addData(fasilitasHasGuest);
                        getFasilitasHasGuests().clear();
                        getFasilitasHasGuests().addAll(
                                getFasilitasHasGuestDaoImpl().showAllData());
                    }
                    slightPause();
                    break;
                case 0:
                    break OUTER;
                default:
                    System.out.println(
                            "Pilihan yang dimasukkan tidak valid");
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("Menu : ");
        System.out.println("1. Member");
        System.out.println("2. Tamu");
        System.out.println("3. Pegawai");
        System.out.println("4. Fasilitas");
        System.out.println("5. Kamar");
        System.out.println("6. Reservasi");
        System.out.println("7. Extra Fasilitas");
        System.out.println("0. Keluar");
        System.out.print("Pilihan anda : ");
    }

    public static void slightPause() {
        Scanner s = new Scanner(System.in);
        System.out.println("\nPress enter to Continue...");
        s.nextLine();
    }

    public static void subMenu(int menu) {
        String mn = "";
        switch (menu) {
            case 1:
                mn = "member";
                break;
            case 2:
                mn = "tamu";
                break;
            case 3:
                mn = "pegawai";
                break;
            case 4:
                mn = "fasilitas";
                break;
            case 5:
                mn = "kamar";
                break;
            case 6:
                mn = "reservasi";
                break;
            default:
                break;
        }
        System.out.println("Sub Menu : ");
        System.out.println("1. Lihat " + mn);
        System.out.println("2. Tambah " + mn);
        System.out.println("3. Update " + mn);
        System.out.println("4. Delete " + mn);
        System.out.println("0. Back");
        System.out.print("Pilihan anda : ");
    }

    public static MemberDaoImpl getMemberDaoImpl() {
        if (memberDaoImpl == null) {
            memberDaoImpl = new MemberDaoImpl();
        }
        return memberDaoImpl;
    }

    public static List<Member> getMembers() {
        List<Member> result = getMemberDaoImpl().showAllData();
        return result;
    }

    public static GuestDaoImpl getGuestDaoImpl() {
        if (guestDaoImpl == null) {
            guestDaoImpl = new GuestDaoImpl();
        }
        return guestDaoImpl;
    }

    public static List<Guest> getGuests() {
        List<Guest> result = getGuestDaoImpl().showAllData();
        return result;
    }

    public static EmployeesDaoImpl getEmployeesDaoImpl() {
        if (employeesDaoImpl == null) {
            employeesDaoImpl = new EmployeesDaoImpl();
        }
        return employeesDaoImpl;
    }

    public static List<Employees> getEmployeeses() {
        List<Employees> result = getEmployeesDaoImpl().showAllData();
        return result;
    }

    public static FasilitasDaoImpl getFasilitasDaoImpl() {
        if (fasilitasDaoImpl == null) {
            fasilitasDaoImpl = new FasilitasDaoImpl();
        }
        return fasilitasDaoImpl;
    }

    public static List<Fasilitas> getFasilitases() {
        List<Fasilitas> result = getFasilitasDaoImpl().showAllData();
        return result;
    }

    public static RoomDaoImpl getRoomDaoImpl() {
        if (roomDaoImpl == null) {
            roomDaoImpl = new RoomDaoImpl();
        }
        return roomDaoImpl;
    }

    public static List<Room> getRooms() {
        List<Room> result = getRoomDaoImpl().showAllData();
        return result;
    }

    public static ReservationDaoImpl getReservationDaoImpl() {
        if (reservationDaoImpl == null) {
            reservationDaoImpl = new ReservationDaoImpl();
        }
        return reservationDaoImpl;
    }

    public static List<Reservation> getReservations() {
        List<Reservation> result = getReservationDaoImpl().showAllData();
        return result;
    }

    public static RoleDaoImpl getRoleDaoImpl() {
        if (roleDaoImpl == null) {
            roleDaoImpl = new RoleDaoImpl();
        }
        return roleDaoImpl;
    }

    public static List<Role> getRoles() {
        List<Role> result = getRoleDaoImpl().showAllData();
        return result;
    }

    public static FasilitasHasGuestDaoImpl getFasilitasHasGuestDaoImpl() {
        if (fasilitasHasGuestDaoImpl == null) {
            fasilitasHasGuestDaoImpl = new FasilitasHasGuestDaoImpl();
        }
        return fasilitasHasGuestDaoImpl;
    }

    public static List<FasilitasHasGuest> getFasilitasHasGuests() {
        List<FasilitasHasGuest> result = getFasilitasHasGuestDaoImpl().
                showAllData();
        return result;
    }
}
