package ca.ece.ubc.cpen221.mp5;

public class Business implements DataEntry {

    /**
     * AF: Represents a business with special identifiers that may
     * identify the business, such as its name, its latitude and longitude
     * coordinates, its full address, and a unique id.
     */

    private String name;
    private double longitude; // Must be between -180 and 180
    private double latitude; // Must be between -180 and 180
    private String full_address; // Must be in the format of an address in the real world
    private String business_id; // For a given business, the id should be unique and not shared with any other
                                // business

    /**
     * @return the name of the business
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the latitude of the business
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * @return the longitude of the business
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * @return the full address of the business
     */
    public String getFullAddress() {
        return this.full_address;
    }

    /**
     * Get the unique string representation for the business object
     * 
     * @return the business id of the business
     */
    @Override
    public String toString() {
        return this.business_id;
    }

    /**
     * Compute the hashcode for the business object
     * 
     * @return the hashcode of the business object
     */
    @Override
    public int hashCode() {
        return this.business_id.hashCode();

    }

    /**
     * Compare two business objects for equality
     * 
     * @return true if the two businesses are the same (by id)
     */
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
