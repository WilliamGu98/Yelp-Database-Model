Implementing YelpDB constructor:  
  use a file parser to separate each line into a JSON string
  use Gson.fromJson(String json, class) to convert each JSON string to its respective class (restaurant, review, user)
  each class will have its own instance variables which have been parsed by JSON
  
Private fields of yelpDB:  
Map of Restaurant ID (String) -> Restaurant ADT  
Map of User ID (String) -> User ADT  
Map of Review ID (String) -> Review ADT  
  
Subclassing:  
Generic DataEntry Interface  
  implemented by a "Business Class" and a "User Class"  
      YelpUser extends User Class  
      Restaurant extends Business Class   
      Review implements DataEntry Interface  
      
Main ADTS:
  Restaurant:  
    - extends a Business class that implements Generic DataEntry Interface  
    - contains private fields that represent a restaurant (name, address, etc.)  
    - has observer methods for parts of its private field we might need (name, address, etc.)  
  
  YelpUser:
    - extends a User class that implements Generic DataEntry Interface  
    - contains private fields that represent a yelp user  
    - has observer getter methods for parts of its private field we might need  
    
  Review:  
    - implements Generic DataEntry Interface
    - contains private fields that represent a review
    - has observer methods for parts of its private field we might need  
  
Methods to add to YelpDB:  
Observer methods :  
  getAllReviews()  
  getAllRestaurants()  
  getAllUsers()  
  
  //(requires a business/user/review ID to lookup specific specific business/user/review)  
  getRestaurant(String businessID)  
  getReview(String reviewID)  
  getUser(String userID)  
  
Mutator methods:  
  addReview(Review obj)    
  addUser(User obj)  
  addRestaurant(Restaurant obj)  

Private helper methods for implementing K-means clustering method, getPredictorFunction method, and getMatches method  
