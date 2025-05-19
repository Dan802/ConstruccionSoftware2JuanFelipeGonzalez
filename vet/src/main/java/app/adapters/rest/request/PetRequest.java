package app.adapters.rest.request;

public class PetRequest {
    private long documentOwner;
    private String name;
    private int age;
    private String specie;
    private String race;
    private String description;
    private double weight;

    public void setDocumentOwner(long documentOwner) {
        this.documentOwner = documentOwner;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public void setAge(int age) {
        this.age = age;
    }               

    public void setSpecie(String specie) {
        this.specie = specie;
    }   

    public void setRace(String race) {
        this.race = race;
    }              

    public void setDescription(String description) {
        this.description = description;
    }   

    public void setWeight(double weight) {
        this.weight = weight;
    }   

    public long getDocumentOwner() {
        return documentOwner;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecie() {
        return specie;
    }

    public String getRace() {
        return race;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "PetRequest{" +
                "documentOwner=" + documentOwner +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", specie='" + specie + '\'' +
                ", race='" + race + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                '}';
    }
}
