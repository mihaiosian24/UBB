package BookStore.Domain;

public class Client extends BaseEntity<Long> {
    private String name;

    public Client(){

    }

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return getName().equals(client.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}'+super.toString();
    }
}
