# androidnativetest

Software Prerequisites :
    1. Appium
    2. Android Studio to have emulator with name "Android Emulator"
    3. JDK 1.8

Environment variables configuration:
For Mac:
    1. Create .bash_profile from terminal if it is not present.
    2. Follow below commands.
        nano ~/.bash_profile
    3. Change the value according to your system, in file.
        export ANDROID_HOME=/YOUR_PATH_TO/android-sdk
        export ANDROID_SDK_ROOT =/YOUR_PATH_TO/android-sdk
        export PATH=$ANDROID_HOME/platform-tools:$PATH
        export PATH=$ANDROID_HOME/tools:$PATH
    4. Save the file, then run below command in terminal.
        source ~/.bash_profile

Test cases :
    1. Validate app was launched successfully or not
    2. Validate that on homepage there is a text displayed as "Hello World!" or not
    3. Validate that if we click on message box icon then there is text displayed as "Replace with your own action"
    4. Validate that if we click on more options[three dots icon] on home page there is text displayed as "Settings"

Framework :
    1. Fore native app automation i used TestNG Framework using Appium.
    2. For test reports i used Extent report listners and created customised reports for all testcases. 
    These reports will be created under "TestResults/TestReports" folder after testsuit run is finished.
    3. For failure scenarios screenshots will capture under "TestResults/Screenshots" directory after testsuit run is finished







