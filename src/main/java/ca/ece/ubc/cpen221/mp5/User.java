package ca.ece.ubc.cpen221.mp5;

/**
 * 
 * AF: Represents a user with special identifiers. Each user has a name and
 * a unique user_id
 *
 */
public class User implements DataEntry {

	protected String user_id;	//unique string. not null
	protected String name;		//not null or empty.

	public String getName() {
		return this.name;
	}

	@Override
	public String getID() {
		return this.user_id;
	}

	@Override
	public int hashCode() {
		return this.user_id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return this.user_id.equals(other.user_id);
	}
}
