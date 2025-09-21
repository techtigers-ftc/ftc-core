# Overview

This library includes code intended to be used by all kinds of teams in order to elevate their robot's 
software capabilities. Included in this library is code for programming LED displays, using route-planners, and more. 
We will fix bugs and add new features to this library over time, and we are open to any suggestions or questions.


# Installation 

1. Open your Android Studio project
2. In the Gradle Scripts folder, open `build.dependencies.gradle`
3. In `repositories`, add `maven { url 'https://jitpack.io' }`
4. In `dependencies`, add `implementation 'com.github.techtigers-ftc:ftc-core:2.0.0'`
5. Sync your Gradle files, and you should be all set!

# Usage

There are three main parts to this library:

- **Display**: The library contains all the code you need to program and use a LED matrix on your robot in
 just a few minutes. A custom Adafruit Neopixel driver is included to help you communicate to the Adafruit
  Neopixel and write data to the LEDs. Since it can be tricky to manage so many LEDs, we have also included
  our sprite-based system for controlling the LEDs. More information can be found on our [YouTube channel](https://youtu.be/B7pZr_9d6tI)
- **Pathfinder**: This is a route-planner based on Dijkstra's algorithm designed to be used with Roadrunner. This
  route planner will dynamically generate new trajectories if the robot is hit, keeping obstacles
   on the field in mind. Videos of Pathfinder in action can be found on our [YouTube channel](https://www.youtube.com/watch?v=y5MQFLuQmRU)
- **Utils**: This folder contains a few utilities for teams to use, in case they find them helpful.
   - One utility is GlobalState, which allows users to store information that is global to
     the entire robot. Users can extend the GlobalState class and store all kinds of robot information in that file.
   - Another utility is RobotSaveState, which gives users the ability to preserve robot information between
     the autonomous and tele-op period. This is very useful for certain types of information, like localization.
  
# Example Code (for display only)

An example repository with this library implemented can be viewed here: https://github.com/techtigers-ftc/tt-library-test

# Contact
Contact us via [email](mailto:techtigers33958@gmail.com) or our [YouTube Channel](https://www.youtube.com/channel/UCBVZWCttULV7aFZUiYqUAVQ)
