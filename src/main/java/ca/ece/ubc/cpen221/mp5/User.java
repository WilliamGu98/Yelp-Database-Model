package ca.ece.ubc.cpen221.mp5;

public class User implements DataEntry{
    
    private String user_id;
    private String name;
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
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
