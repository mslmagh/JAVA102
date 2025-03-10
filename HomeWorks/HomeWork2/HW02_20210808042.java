/**
 * @author Müslüm Agah
 * @since 29.03.2024
 */
package HomeWork2;

import java.util.Date;
public class HW02_20210808042 {
    public static void main(String[] args) {
        City city1 = new City("56", "Siirt");
        City city2 = new City("07", "Antalya");
        Ticket ticket2 = new Ticket(city1, city2, 5);

    }
}

class Ticket {
    private City from;
    private City to;
    private Date date;
    private int seat;

    public Ticket(City from, City to, Date date, int seat) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.seat = seat;
    }

    public Ticket(City from, City to, int seat) {
        this.from = from;
        this.to = to;
        this.seat = seat;
        long millisInDay = 24 * 60 * 60 * 1000;
        date = new Date(System.currentTimeMillis() + millisInDay);
        System.out.println(date);
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public Date getDate() {
        return date;
    }

    public int getSeat() {
        return seat;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

}

class City {
    private String postalcode;;
    private String name;

    public City(String postalCode, String name) {
        this.postalcode = postalCode;
        this.name = name;
    }

    public String getPostalCode() {
        return postalcode;
    }

    public String getName() {
        return name;
    }
}

class Person {
    private String name;
    private String surname;
    private String phoneNumber;

    public Person(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
