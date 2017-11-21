Implementing YelpDB constructor:  
  use a file parser to separate each line into a JSON string
  use Gson.fromJson(String json, class) to convert each JSON string to its respective class (restaurant, review, user)
  each class will have its own instance variables which have been parsed by JSON
  
Methods to add to YelpDB:  
Observer methods:  
  getReviewList()  
  getRestaurantList()  
  getUserList()  
  getRestaurant(String businessID)  
  // least-squares regression  
  getRestaurantPrice(Restaurant restaurant)  
  getUser-----  
   
  //k-means clustering  
  getRestaurantNeighborhood(Restaurannt restaurant)  
  getRestaurantLongitude(Restaurant restaurant)  
  getRestaurantLatitude(Restaurant restaurant)  
  getDistance(Restaurant r1, Restaurant r2)    

Mutator methods  
  addReview()    
  addUser()  
  addRestaurant()
  
Private fields of yelpDB:
Map of Restaurant ID -> Restaurant ADT
Map of User ID -> User ADT
Map of Review ID -> Review ADT

Subclassing stuff:
Generic DataEntry Interface
  extended by a "Business Interface" and a "User Interface"
      YelpUser implements User Interface
      Restaurant implements Business Interface
      Review implements DataEntry Interface
