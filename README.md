# Lyfter
To achieve the objective of ensuring that each rider out of a total of 100 riders experiences a wait time of under 5 minutes, it was necessary to determine the spatial distance equivalent to 5 minutes on our 1000x1000 space map. A conversion rate of 50 spaces to 1 mile was established, effectively transforming the map into a 20x20 mile area. With this conversion in mind, it was decided that cars would travel at a speed of 30 miles per hour.

Based on this speed, a car would take approximately 2 minutes to cover a distance of 1 mile. Therefore, within a 5-minute timeframe, a car would be able to travel approximately 2.5 miles. This translates to traversing a distance equivalent to 40 minutes of travel across the entire 20x20 mile map.

# Design and Implementation 
The user client begins by requesting the user's name and recording the input using a scanner. Subsequently, a rider object is created using this name. The client then prompts the user to enter their location, which is stored as x and y coordinates within the range of 1 to 1000. These coordinates are added to the rider object, thereby creating a rider with a designated location and name.

Next, the database populates an array list with 100 random names, which are used to assign them to 100 random driver objects with randomized x and y coordinates. Following this, the getClosest() method is invoked. It takes the rider's x and y coordinates as inputs and determines the closest driver to that location using the array list of 100 random driver objects. This is accomplished by iterating through the entire drivers array list and comparing the shortest path between the rider and each driver.

Subsequently, the calculateMin method is called to calculate the actual distance between the rider and the driver with the shortest path. The result is stored in a distance variable. This distance variable is then used twice in the subsequent print statement: first, to calculate the price of the ride using the getPrice method, and second, to display the number of miles that distance represents. The conversion from distance to miles is achieved by dividing the distance by 50. The getPrice method calculates the cost of the ride by multiplying each mile by $0.75 and adding a $1.50 service fee on top of it.

Finally, the client asks the user to confirm the ride before it is scheduled, awaiting a response from the user to proceed accordingly.

To calculate the minimum number of drivers needed for 100 riders at any given time:

First, an array list of 100 drivers with random names and locations was populated. Similarly, a list of 100 riders was created. The distance required to achieve a wait time of under 5 minutes was then calculated. Assuming a board size of 1000 x 1000 spaces, it was assumed that a distance of 50 spaces would be equivalent to 1 mile. Given that the car travels at a speed of 30 miles per hour, it would take approximately 2.5 minutes to drive 1 mile or 2 miles in 5 minutes. Since each mile is represented by 50 spaces, it was determined that any distance between a rider and driver under 100 spaces would result in a wait time of under 5 minutes.

To address this, a getDistance method was created, which calculates the exact distance between two sets of coordinates representing the rider and driver locations. This method is then utilized in the distanceChecker method to determine if the distance value is under the required 100 spaces.

These methods are utilized in the minDriverTest method, which iterates through each driver for each rider. Whenever a valid wait time is achieved, the rider is removed from the list. This process continues until the number of riders reaches 0. If the number of riders does not reach 0 within one complete iteration, a new driver is added, and a recursive call is made to repeat the method with the additional driver. The final result provides the total number of drivers in the list, which represents the minimum number required to ensure a wait time of 5 minutes or less for any random batch of 100 riders.
