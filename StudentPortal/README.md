## Setup JavaFX16
Download JavaFX16 from [here](https://gluonhq.com/download/javafx-16-sdk-windows/)

Make sure that you're running JavaJDK 16 if not then first login/signup then download it from [here](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)

Now download JFoenix Library from [here](https://search.maven.org/remotecontent?filepath=com/jfoenix/jfoenix/9.0.10/jfoenix-9.0.10.jar)

## Setting Up VS Code
First make sure that you're running JavaJDK 16 if not then press F1 and type and goto "settings.json". In settings.json scroll to the bottom of the file just before the curly bracket closes add a "," comma after closing of square brackets and paste this 
"java.home": "Enter here the filepath of the jdk",

mine was: "java.home": "C:\\Program Files\\AdoptOpenJDK\\jdk-16.0.1.9-hotspot",

After this search for .vscode folder in your selected file directory inside vscode. Inside this folder there must be launch.json if there is not a launch.json then goto Run and Debug tab inside vscode or press Ctrl+Shift+D and click create a launch.json file, now the launch.json file will be there click on the launch.json and paste this on your configurations
"vmArgs": "--module-path <Filepath of Javafx16 lib folder> --add-modules javafx.controls,javafx.fxml",

mine was: "vmArgs": "--module-path D:\\JavaFX\\javafx-sdk-16\\lib --add-modules javafx.controls,javafx.fxml",

After doing this my configurations looked like this

 "configurations": [
        {
            "type": "java",
            "name": "Launch Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "Launch App",
            "request": "launch",
            "vmArgs": "--module-path D:\\JavaFX\\javafx-sdk-16\\lib --add-modules javafx.controls,javafx.fxml",
            "mainClass": "JavaProject.src.App",
            "projectName": "JavaProject_41c9a341"
        },        
    ]

save the file and then head to extensions tab and install the "Java Extension Pack" by Microsoft.

Now press Ctrl+Shift+E to open the file explorer panel at the bottom you will find "JAVA PROJECTS" expand it and scroll down to the bottom of it to "Referenced Libraries" expand it and then by clicking at the plus icon "+" in front of Referenced Libraries add all the .jar files of Javafx16 present inside the lib folder of javafx and also add JFoenix .jar file.

After following the above steps your program must compile without any issues

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
