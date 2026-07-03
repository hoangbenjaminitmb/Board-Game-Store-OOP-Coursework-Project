package app;

public class User {
	private final int id;
	private final String name;
	private final String role;
	private final Address address;

	public User(
		int id, 
		String name, 
		Address address, 
		String role
	) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.role = role;
	}

	public int getId() {return id;}
	public String getName() {return name;}
	public String getRole() {return this.role;}
	public Address getAddress() {return address;}
}