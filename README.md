# individual-project-gthreehosmane
individual-project-gthreehosmane created by GitHub Classroom


Steps to run the program - 

**This project needs JDK 11 or above to work properly**

1. Download the code as zip file from github.  Name of the downloaded zip file will be individual-project-gthreehosmane-main.zip
2. Extract the zip file to get a folder named - individual-project-gthreehosmane-main
3. Follow below steps to import project to eclipse
4. Import the extracted folder to Eclipse.
5. In the import wizard select General - > Projects from Folder or Archive, click on next,and in the next step, select the extracted project folder from step 2(individual-project-gthreehosmane-main)and select IMS folder that is present in extracted folder, click on finish
6. Now in eclipse you should be able to see the project folder IMS

<img width="806" alt="classpath-2" src="https://user-images.githubusercontent.com/13237444/166634920-28d4135a-98fe-4a36-84e5-e45d27ebd95b.png">

<img width="985" alt="classpath-3" src="https://user-images.githubusercontent.com/13237444/166634944-342a3916-42a3-4d33-a851-6087f5267fef.png">

<img width="933" alt="classpath-4" src="https://user-images.githubusercontent.com/13237444/166634958-6d23107a-8cfd-4343-8847-a0706f3c733c.png">

<img width="956" alt="classpath-5" src="https://user-images.githubusercontent.com/13237444/166634973-0613559f-ec74-40b2-85bf-72691c730572.png">


7. The "lib" folder that is present in the project folder(CMPE-202-IMS) parallel to src folder contains all external jars required to process input files. These jars must be present in the classpath.
8. If you encounter any errors for Apache POI related jars, please follow below steps.
9. Right-click on IMS folder in eclipse, select BuildPath -> Configure Buildpath - >Libraries -> classpath and select Add Jars option
<img width="820" alt="classpath-6" src="https://user-images.githubusercontent.com/13237444/166635057-6288f7bc-2939-4c2a-b5f1-1d58b55a3451.png">

<img width="1158" alt="classpath-7" src="https://user-images.githubusercontent.com/13237444/166635114-0b9f6d53-6d4e-4d31-bc6f-b13df4bbb441.png">


10.  In the Jar Selection wizard navigate to IMS->lib and select all the jars in lib folder and click on OK.

<img width="956" alt="classpath-8" src="https://user-images.githubusercontent.com/13237444/166635137-4a39a91a-d59b-4886-8eed-476647df2ac5.png">


11. Now add all the jars present in the IMS/lib folder and click on OK.
12. Refresh/Clean the project once and the program is now ready to run.
13. Goto the Billing class which is present under package com.ims.runner
14. You will be prompted to enter the order input file via console.
15. Please provide the absolute path of the input order file.
16. If the file name is empty the program will be terminated.
17. The output/error files will be generated in the same directory in which the project's src folder is present.
18. Predefined category caps according to project description - Essentials: 3, Luxury: 4, and Misc: 6


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

