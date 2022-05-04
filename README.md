# individual-project-gthreehosmane
individual-project-gthreehosmane created by GitHub Classroom


Steps to run the program - 

1. Download the code as zip file from github.
2. Extract the zip file.
3. Import the extracted folder to Eclipse.
4. The "lib" folder that is present in the project folder(CMPE-202-IMS) parallel to src folder contains all external jars required to process input files. These jars must be added to classpath before running the program.
5. Select the project folder and right click on it to get a set of options. Selet Buildpath->Congifure Buildpath->Libraries.
6. After Navigating to Libraries tab, select classpath and select Add jars option. Select the CMPE-202-IMS/lib folder from the the Jar selection popup.
7. Now add all the jars present in the CMPE-202-IMS/lib folder and click on OK.
8. Refresh/Clean the project once and the program is now ready to run.


<img width="772" alt="Screen Shot 2022-05-01 at 8 00 36 PM" src="https://user-images.githubusercontent.com/13237444/166179338-a6e7a98b-82e2-437e-ae97-8bda2d30c38f.png">

<img width="1099" alt="Screen Shot 2022-05-01 at 8 01 38 PM" src="https://user-images.githubusercontent.com/13237444/166179353-52c95f9e-1fda-4435-b610-b3c504434617.png">



7. Goto the Billing class which is present under package com.ims.runner
8. You will be prompted to enter the order input file via console.
9. Please provide the absolute path of the input order file.
10. If the file name is empty the program will be terminated.
11. The output/error files will be generated in the same directory in which the project's src folder is present.
12. Predefined category caps according to project description - Essentials: 3, Luxury: 4, and Misc: 6


Project Details

Design patterns used - 

1. Singleton - Singleton design pattern is used to implement the in-memory database. The in-memory database will always have single instance. The billing.java class calls a method of Singleton class to get the unique instance.
2. Abstrct factory -  Abstrct factory pattern is used to implement the writing output/error messages to files. The ProcessOrderImpl class will decide which concrete class to call based on the error message or final price of the order. If error message is generated, TextFactoryImpl class will be called to write error message to OUTPUT.txt file. If final price message is generated, CSVFactoryImpl class will be called to order success message to OUTPUT.csv file. 




Some sample testcase outputs

1. Order contains  all valid items and item quantity does not exceed category cap or stock, so the out is a csv file with filnal price of the order. Also, the card is already present in databse so it will not be added.

<img width="1462" alt="ims-1" src="https://user-images.githubusercontent.com/13237444/166618477-d3c5e9b1-659e-4328-b519-956441f04b94.png">


2.  Order contains  all valid items and item quantity does not exceed category cap or stock, so the out is a csv file with filnal price of the order. But, the card is not present in databse so it will be added to the database.

<img width="1417" alt="ims-2" src="https://user-images.githubusercontent.com/13237444/166618488-29a92f48-647f-4079-b7c6-ab4a60888017.png">


3.  Order contains all valid items and quantities of some items exceed stock, so the out is a text file with error message and invalid item quantity. Also, the card will not be processed since the program encountered some error

<img width="1732" alt="ims-3" src="https://user-images.githubusercontent.com/13237444/166618499-482268d0-fdd1-496f-bc89-89cb6af658ba.png">


4. Order contains all valid items and quantities of some items exceed category cap, so the out is a text file with error message and invalid item quantity. Also, the card will not be processed since the program encountered some error.

<img width="1482" alt="ims-4" src="https://user-images.githubusercontent.com/13237444/166618509-c0784988-c440-4de0-a29e-fbae92a3edb1.png">

