# Toy Robot
A simple Java program that simulates a robot that is able to roam on a "table top" with dimensions of 5 x 5 units.

# Getting Started
### Project Download
No installation is needed to run the program but you need to clone the project or download the project zip file to your machine.

#### 1. Clone
Cloning requires you to have [git](https://git-scm.com/) installed on your machine. If you do have it installed, you can run the command:

`git clone https://github.com/j-savellano/toy-robot-v1.git`

As this is a public repository, no authentication/authorization is needed. 

#### 2. Download
If you don't have [git](https://git-scm.com/) installed, you can simply download the zip file of this repository. 

Steps to download:

1. Go to this project's [home page](https://github.com/j-savellano/toy-robot-v1)
2. Find the `Code` dropdown button (colored green) and click `Download ZIP`


![Screenshot 2022-08-21 at 10 48 27 PM](https://user-images.githubusercontent.com/44570184/185796886-53d97a74-3bf0-4df6-890d-6f46f13186be.png)

3. Unzip the file to your desired location.

### Verify Project Download
After cloning or downloading (and unzipping) the project, you should see the following files inside the project. If you're on Mac, you can use these commands to check:
```
% ls
toy-robot-v1
% cd toy-robot-v1 
% ls
README.md	src
% cd src
% ls
ToyRobotSimulator.java		ToyRobotSimulatorTest.java
```

If you can see the above files, cloning/download is succesful.

### Java
Next is we need to check if [Java](https://www.java.com/en/) is installed on your machine by opening the cli and running the command:

`java -version`

You should get the following:

```
openjdk version "11.0.16.1" 2022-08-12 LTS
OpenJDK Runtime Environment Corretto-11.0.16.9.1 (build 11.0.16.1+9-LTS)
OpenJDK 64-Bit Server VM Corretto-11.0.16.9.1 (build 11.0.16.1+9-LTS, mixed mode)
```

If not, chances are you don't have it installed or properly configured on your machine. Installation or proper configuration of [Java](https://www.java.com/en/) is not discussed here but you can refer to the following guides:
- [Amazon Corretto](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
- [OpenJDK](https://developers.redhat.com/openjdk-install)
- [Oracle JDK Installation Guide](https://docs.oracle.com/en/java/javase/18/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

Recommended version is `Java 8` and up.

# Running The Program
If you followed the example above on the **_Verify Project Download_** section of this page, you should already be inside the `src` directory and can see two `.java` files:

1. `ToyRobotSimulator.java`
2. `ToyRobotSimulatorTest.java`

Our only concern for now is the file `ToyRobotSimulator.java` as this is the file we need in order to run the program. The other file `ToyRobotSimulatorTest.java` is discussed on the **_Running The Tests_** section of this page.

To run the program, please execute the following commands:

```
% javac ToyRobotSimulator.java
% java ToyRobotSimulator
```
# Running The Tests
Running the project's integration and unit tests follows the same process as explained in **_Running The Program_** section of this page, only this time the target file will be the `ToyRobotSimulatorTest.java`.

```
% javac ToyRobotSimulatorTest.java
% java ToyRobotSimulatorTest
```

You should see the printed text displayed below at the last part after executing the `java ToyRobotSimulatorTest` command.

```
#####################
# All tests passed. #
#####################
```
