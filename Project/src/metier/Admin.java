package metier;
import java.util.List;

public class Admin extends Person{

	// Fields
	
	// Initialize
	public Admin(List<String> jobs, String name, String surname) {
		super(jobs, name, surname);
	}
	
	// Getters
	
	// Setters
	
	// Methods
	@Override
	public boolean isAdmin() {
		return true;
	}
}
