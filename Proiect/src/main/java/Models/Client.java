package Models;

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private int age;

    /**
     * Default constructor for the Client class.
     */
    public Client() {}

    /**
     * Constructor for the Client class.
     * @param id the identifier of the Client
     * @param name the name of the Client
     * @param address the address of the Client
     * @param email the email address of the Client
     * @param age the age of the Client
     */
    public Client(int id, String name, String address, String email, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * @return the identifier of the Client
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new identifier for the Client.
     * @param id the new identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets a new name for the Client
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new address for the Client
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets a new age for the Client
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets a new email address for the Client
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the name of the Client
     * @return the name of the Client
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the address of the Client
     * @return the Client's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the email address of the Client
     * @return the Client's email address
     */
    public String getEmail() {return email;}

    /**
     * Gets the age of the Client
     * @return the Client's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets a String representation of all the Client's data
     * @return the String representation
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
