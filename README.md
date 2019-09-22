# Fractal Drawer (visualizer) #
 JavaFx GUI application for drawing fractal shapes
![GitHub repo size](https://img.shields.io/github/repo-size/milanbojovic/fractal-drawer) ![GitHub](https://img.shields.io/github/license/milanbojovic/kaggle-kobe-bryant-shot-selection)

### Fractal_Drawer image ###
![Fractal Drawer image](images/fractal-drawer-demonstration.gif)

  ### Reqirements
|Reqirement||
|--|--|
|Git|![https://img.shields.io/badge/git-blue](https://img.shields.io/badge/git-blue)|
|Maven|![https://img.shields.io/badge/maven-3%2B-blue](https://img.shields.io/badge/maven-3%2B-blue)|
|Java|![https://img.shields.io/badge/java-11-blue](https://img.shields.io/badge/java-11-blue)|
|JavaFx|![https://img.shields.io/badge/javafx-13-blue](https://img.shields.io/badge/javafx-13-blue)|

### What is this repository for?

* This JavaFx gui application for visualizing fractal images was written as my final graduation work, it's purpose is to demonstrate how fractal shapes are formed. User interface allows user to choose fractal shape and to observe vizualization step by step for famous fractal shapes.

 ### Installation and usage
Once you install [Git](https://www.atlassian.com/git/tutorials/install-git), [Maven](https://maven.apache.org/install.html) and [JDK 11](https://jdk.java.net/11/) proceed with following steps:

Clone git repository:

    git clone git@github.com:milanbojovic/fractal-drawer.git
Run the app:

    mvn javafx:run
       
### How use this app?

* This application is platform independent, it should run on Windows, Mac, Linux and other systems if Java is installed)
* Select fractal shape from __FRACTAL SHAPES LIST__
* Click on button: __Level ++__ to view next iteration in fractal construction
* Click on button: __Level --__ to view previous iteration in fractal construction
* Fractal dimension is displayed in bottom split pane


### Shapes visualized

| Fractal shape |Visualization|
|--|--|
| **Horizontal Circles**|![Horizontal Circles](images/horizontal-circles.png)
|**Horizontal and Vertical Circles**|![Horizontal and Vertical Circles](images/vertical-circles.png)
|**Sierpinski Triangle**|![Sierpinski Carpet](images/sierpinski-triangle.png)
|**Sierpinski Carpet**|![Sierpinski Carpet](images/sierpinski-carpet.png)
|**Cantor Set**|![Cantor Set](images/cantor-set.png)
|**Koch Curve**|![Koch Curve](images/koch-curve.png)
|**Koch Curve (Quadratic 1)**|![Koch Curve Quadratic 1](images/koch-curve-quadratic-1.png)
|**Koch Curve (Quadratic 2)**|![Koch Curve Quadratic 2](images/koch-curve-quadratic-2.png)
|**Dragon Curve**|![Dragon Curve](images/dragon-curve.png)
|**Dragon Curve (double)**|![Dragon Curve Double](images/twin-dragon-curve.png)
|**Levy C curve**|![Levy Curve](images/levy-curve.png)
|**Koch Snowflake**|![Koch Snowflake](images/koch-snowflake.png)
|**Koch Anti Snowflake**|![Koch Anti Snowflake](images/koch-anti-snowflake.png)
|**Koch Costline**|![Koch Coastline](images/koch-coastline.png)
|**Mandelbrot Set**|![Mandelbrot Set](images/mandelbrot-set.png)
|**Tree**|![Tree](images/tree-basic.png)
|**Tree 60 degree**|![Tree 60 degree](images/tree-60-degree.png)
|**Tree 90 degree**|![Tree 90 degree](images/tree-90-degree.png)
|**Pythagoras tree**|![Pythagoras Tree](images/pythagoras-tree.png)
|**Hilbert Curve**|![Hilbert Curve](images/hilbert-curve.png)
|**TSquare**|![TSquare](images/t-square.png)
|**Hexaflake**|![Hexaflake](images/hexaflake.png)


### Implementation details
Application was written in Java using JavaFX framework, application's graphical interface is completely described in __`"MainWindow.fxml"`__ file which can be easily modified using JavaFX Scene builder. Resoucre folder contains *.fxml* used in presentation layer (JavaFx). Package __`"tk.milanbojovic.shape"`__ holds source code for all fractal shapes - it mainly consists of `FractalShape` class which is base class and individual derived shape classes eg. __`PythagorasTree`__, __`Koch Curve`__ and others which just implementing drawing method for particular shape. All shapes in this application are calculating and drawing states using recursive methods with mechanism to stop recursion at desired level. New shapes could be added easily by deriving base class and redefining draw method.
Fractal dimension is displayed using lightweight HTML 5 browser embedded in bottom split pane of the application - this browser displays math formulas which are generated using __MathML__ language when new shape is displayed on graphical canvas. MathML syntax could be found [here](http://www.tutorialspoint.com/html5/html5_mathml.htm).

Full pdf report can be found ![here](https://github.com/milanbojovic/fractal-drawer/blob/master/report/Diplomski%20rad%20Milan%20Bojovic_final.pdf). (it is written in Serbian language!)

### Who do I talk to? ###

MILAN BOJOVIC

Contact:
Email: milanbojovic@gmail.com
LinkedIn: https://rs.linkedin.com/in/mbojovic
Skype: milanbojovic

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTIwNDU5NDc3MzgsODUwMzAxMTQ1LDE0Mz
I2Mzc1NzUsMTI4NTA3NDA2OSwtNDkxMzg0NzIyLC0xNDA5MDI4
MjkwLC0xNjU1MTI2NDgxLC0xODUxMzU2NTYyLDE0NjU2ODk5MD
UsMTIwNjU4NzQ1MywtMTEyMzg2NDY4NywtOTQ3NzczOTI5LDE4
ODg5MTY1MTAsLTkyODI2ODQ5NywtNDU5MTg4MjQ1XX0=
-->