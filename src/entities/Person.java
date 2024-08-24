package entities;

public abstract class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public abstract String whoyouare() ;

}
