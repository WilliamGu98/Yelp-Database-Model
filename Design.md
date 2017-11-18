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
  getRestaurantPriciness(Restaurant restaurant)  
  getUser-----  
    
  getRestaurantNeighborhood(Restaurannt restaurant)  
  getRestaurantLongitude(Restaurant restaurant)  
  getRestaurantLatitude(Restaurant restaurant)  
  getDistance(Restaurant r1, Restaurant r2)  
  getNeighborhoods //this is a method we're going to use for random neighborhood selection  
  convert list of sets to JSON format  
  getRestaurantLongitude(Restaurant restaurant)   
  getRestaurantLatitude(Restaurant restaurant)  
    
    
Mutator methods  
  addReview()    
  addUser()  
  addRestaurant()  
