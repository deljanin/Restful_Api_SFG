[![CircleCI](https://dl.circleci.com/status-badge/img/gh/deljanin/restfulApiSFG/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/deljanin/restfulApiSFG/tree/master)
[![codecov](https://codecov.io/github/deljanin/restfulApiSFG/branch/master/graph/badge.svg?token=IK87A9I2MZ)](https://codecov.io/github/deljanin/restfulApiSFG)

This is the repository of my spring boot rest application. Access the live version here: <a href="https://springrestapp.deljanin.dev/index.html">springrestapp.deljanin.dev</a>

This a back-end Rest application which has multiple endpoints. It uses Swagger to display available endpoints.
It uses H2 in memory database as it's a project for demonstrative purposes.

To run a clone on your local device follow these steps:

1. Make sure you have git installed. (<a href="https://git-scm.com/downloads">Download Git here</a>)


2. Make sure you have at least Java JDK 17 installed.
   (<a href="https://www.oracle.com/java/technologies/downloads/">Download Java</a>)


3. Make sure you have some editor installed, I recommend IntelliJ IDEA. <br />
   (<a href="https://www.jetbrains.com/idea/download/">Get Intellij IDEA</a>)


4. Open a terminal/cmd that can use git. Then run this command:<br />```git clone https://github.com/deljanin/restfulApiSFG.git ```


5. Open newly generated directory: ```restfulApiSFG``` with an editor and then right-click ```pom.xml``` file.
   From the dropdown menu click: Maven > Reload Project


6. Open your editors setting and search for 'annotation processing'. Enable annotation processing.


6. Now open ```src/main/java/com/deljanin/restfulApiSFG/``` folder.


7. Here you will find a java file: ```RestfulApiSfgApplication```.
   Open it and right-click on to the ```main``` method. In the dropdown click: ```Run 'RestfulApiSfgA...main()'```

Now you should be able to access the Swagger front-end, and see the api end-points, at the following location:
```localhost:8080/index.html```