# GUI_Fractal_Drawer_JAVA_FX #

## JavaFX GUI Application for drawing FRACTAL SHAPES ##

### What is this repository for? ###

* Java graphical user interface application for iterative drawing of fractal images
  This application is written for university purposes as final graduate work. 
  It is used to demonstrate fractal geometry to students. User interface allows student to generate some of famous fractal shapes iteratively step by step
* __Live DEMO could be found [HERE](http://130.255.72.230/fractalDrawer)__

### Fractal_Drawer image ###
![Fractal Drawer image](https://raw.githubusercontent.com/milanbojovic/GUI_Fractal_Drawer_JAVA_FX/master/Fraktal_images/Application_look.png)

### How do I run and use this app? ###

* In order to run this app. you must have Java JRE 1.7 or newer installed on your system (app. is platform independent - should run on Windows, Mac, Linux and other systems if Java is installed)
* To start using this application simply download and run __GUI_Fractal_Drawer_JAVA_FX.jar__ file
* Select fractal shape from __FRACTAL SHAPES LIST__
* Click on button: __Level ++__ to view next iteration in fractal construction
* Click on button: __Level --__ to view previous iteration in fractal construction
* Fractal dimension is displayed in bottom split pane


>### FRACTAL SHAPES LIST (which can be drawn in Fractal Drawer)
>>#####1.   Sierpinski Triangle
>>#####2.   Sierpinski Carpet
>>#####3.     Cantor Set
>>#####4.     Koch Curve
>>#####5.     Koch Curve (Quadratic_1)
>>#####6.    Koch Curve (Quadratic_2)
>>#####7.    Dragon Curve
>>#####8.    Twin Dragon Curve
>>#####9. Levy C curve
>>#####10. Koch Snowflake
>>#####11. Koch Anti Snowflake
>>#####12. Koch Coastline
>>#####13. Mandelbrot Set
>>#####14. Tree
>>#####15. Tree 60 degree
>>#####16. Tree 90 degree
>>#####17. Pythagoras tree
>>#####18. Hilbert Curve
>>#####19. TSquare
>>#####20. Hexaflake
>>#####21. Horizontal Circles
>>#####22. Horizontal and Vertical Circles


### Implementation details ###
Application is written using Java programming language using JavaFX framework (2.0), application look is completely described in __`"MainWindow.fxml"`__ file which can be easily modified using JavaFX Scene builder. Package __`"code"`__ holds source code used for presentation layer (JavaFx). Package __`"shape"`__ holds source code for all fractal shapes - it mainly consists of `FractalShape` class which is base class and individual derived shape classes eg. __`PythagorasTree`__, __`Koch Curve`__ and others which just redefine drawing method for particular shape. All shapes in this application are written using recursive methods with mechanism to stop recursion at desired level. New shapes could be added easily by deriving base class and redefining draw method.
Fractal dimension is displayed using lightweight HTML 5 Browser embedded in bottom split pane of the application - this browser displays pages which are generated using __MathML__ language when new shape is displayed on graphical canvas. MathML syntax could be found [here](http://www.tutorialspoint.com/html5/html5_mathml.htm).

### Who do I talk to? ###

MILAN BOJOVIC

Contact:
Email: milanbojovic@gmail.com
LinkedIn: https://rs.linkedin.com/in/mbojovic
Skype: milanbojovic
