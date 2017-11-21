package ca.ece.ubc.cpen221.mp5;

public class Business implements DataEntry {
    private String name;
    private double longitude;
    private double latitude;
    private String full_address;
    private String business_id;


    public String getName() {
        return this.name;
    }
    
    public double getLatitude() {
        return this.latitude;
    }
    
    public double getLongitude() {
        return this.longitude;
    }
    
    public String getFullAddress() {
        return this.full_address;
    }

    @Override
    public String toString() {
        return this.business_id;
    }

    @Override
    public int hashCode() {
        return this.business_id.hashCode();

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Business)) {
            return false;
        }
        Business other = (Business) obj;
        return this.business_id.equals(other.business_id);
    }
}
