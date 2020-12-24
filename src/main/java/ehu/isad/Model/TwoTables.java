package ehu.isad.Model;

public class TwoTables {

    String firstName,lastName,sport,years,vegetarian,irudi;

    public TwoTables(String firstName, String lastName, String sport, String years, String vegetarian, String irudi) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.years = years;
        this.vegetarian = vegetarian;
        this.irudi = irudi;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getIrudi() {
        return irudi;
    }

    public void setIrudi(String irudi) {
        this.irudi = irudi;
    }
}
