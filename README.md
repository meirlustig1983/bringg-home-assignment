# BRINGG HOME ASSIGNMENT

## How To Apply New Calculation?

### Unit Test
* Open the project in your IDE
* Write a new test in DistanceMatrixServiceTest.java file

### Application Server
* Run the project as a service
* Send GET HTTP request 
* For example, in order to calculate the distance matrix for (1,2), (2,-2), (2, -1) with 3 threads.
  You should send the following 'Get Request': http://localhost:8501/calculate?points=1,2,2,-2,2,-1&threads=3  



note: the project has been compiled with JDK-11