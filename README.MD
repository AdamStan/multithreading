# How to start?
At the beginning you have to have installed java 11 or higher and downloaded javaFX libraries from:

In your favorite IDE:
1. Import as maven project.
2. run ServerRunner from package: com.adam.stan in <b>server</b> module
add to run configuration following property to VM arguments.
```
--module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml
```
3. run ClientApplication from package: com.adam.stan in <b>client</b> module
for this you also have to configure run configuration adding:
```
--module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml 
```
to VM arguments.
4. have fun