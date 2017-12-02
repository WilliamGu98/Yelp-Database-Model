package ca.ece.ubc.cpen221.mp5;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AF: Represents a review of a restaurant with special identifiers. generic
 * identifies which are inherited form DataEntry. The review has a unique id, a
 * type, unique business id referring to the restaurant it reviews, text which
 * contains the contents of the review, unique user id of who wrote the review
 *
 */
public class Review implements DataEntry {
	//Rep invariants
	protected String review_id;		//unique review id. not null
	protected String type;			//type is "review"
	protected String business_id;	//unique business id corresponding to an existing business in the database
	protected Map<String, Integer> votes;	//must contain "cool" "useful" and "funny" as keys. their corresponding values must be >= 0
	protected String text;	//not null
	protected int stars;	//must be between 0 and 5
	protected String user_id;	//unique user id corresponding to an existing user in the database
	protected String date;		//must be valid date. before 2017-12-01 in yyyy/mm/dd format

	public String getUserId() {
		return this.user_id;
	}

	public int getStars() {
		return this.stars;
	}

	public String getBusinessId() {
		return this.business_id;
	}

	public String getType() {
		return this.type;
	}

	public String getText() {
		return this.text;
	}

	public String getDate() {
		return this.date;
	}

	public void generateNewReviewInfo(YelpDB db) {
		this.type = "review";
		this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (this.votes == null) {
			this.votes = new HashMap<String, Integer>();
			this.votes.put("cool", 0);
			this.votes.put("useful", 0);
			this.votes.put("funny", 0);
		}
		this.review_id = db.generateReviewID();
	}

	@Override
	public String getID() {
		return this.review_id;
	}

	@Override
	public int hashCode() {
		return this.review_id.hashCode();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Review)) {
			return false;
		}
		Review other = (Review) obj;
		return this.review_id.equals(other.review_id);
	}
}
