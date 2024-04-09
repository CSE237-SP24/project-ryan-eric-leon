# base-project

Add description of what happened each iteration

# **Iteration 1**:

**Things completed in this iteration**:

We added features that allow users to manage their accounts similarly to a bank app. Users can deposit and withdraw money. They can switch between accounts if they have multiple ones and transfer money between them. For users without an account, there is an option to create one. We've provided clear instructions on the different commands to use and how to use them. Additionally, we wrote a function that enables user data to persist after closing the program. Their data is stored in a file using the file processor command we learned in class. We also wrote unit tests for our functions and created a script in the command line for users to run our code.

**Things intended to be completed in the next iteration**:

Since we can save user data in a file, we plan to write a function that allows users to create a username and password and link their data exclusively to their accounts. This will make it easier for us to manage user accounts and make our code more similar to a real bank app, as every user will have a username and password to log in. After implementing this function, we will add a feature to display account information, such as the users' balance, after they successfully log in, mimicking a typical bank app.
We are also considering adding a transaction history feature to allow users to view a list of recent transactions with details like date, amount, and description.

**Issues**:

We are quite satisfied with our progress so far. For the functions we wrote, we created corresponding unit tests in the test folder to ensure they work as expected, which they did. We also simulated being users to ensure the instructions are clear and that users can navigate our app effectively. Also, the default ('root') account will be initialized to 0 balance each time the user runs the program, regardless of the accounts file. This is an intended feature to ensure the unit tests work properly. The new account's name must be a single word.

**Commands needed to compile our code**:

If you wish to run our code, please navigate to our development branch. There, you should find a file named 'script.sh'. Clone our development branch and run this bash script to automatically compile and run our bankapp program. Alternatively, you can navigate to that branch through this link: https://github.com/CSE237-SP24/project-ryan-eric-leon/blob/development/


# **Iteration 2**:

Things completed in this iteration:

Based on the feedback we received, we noted that some methods in our menu were too lengthy. We also received suggestions to improve user instructions. With these points in mind, we decided to implement a GUI (Graphical User Interface) for the functions developed in iteration 1. This change eliminated the need for extensive while loops and detailed instructions, as the GUI is self-explanatory and aesthetically pleasing. Consequently, some functions in our original menu class became redundant and were removed.

Things intended to be completed in the next iteration:

With the introduction of the GUI for deposit, withdrawal, transfer, etc., we utilized straightforward functions in our BankAccounts class, rendering the processXXX functions in our Menu class obsolete. We have yet to remove these functions as they are integral to many unit tests in our tests folder. In iteration 3, we plan to address this issue and decide whether to delete these redundant functions and their associated tests or to slightly modify the GUI methods to utilize the processXXX methods instead of the basic XXX methods. Although we lean towards the second, a thorough discussion is scheduled for later. Additionally, as we have enabled users to persist their accounts and funds after closing the app, we are considering implementing the transaction history feature, which was initially planned for this iteration but not executed.

Issues:

The default ('root') account will be initialized to 0 balance each time the user runs the program, regardless of the accounts file. The new account's name must be a single word. For numeric input fields, if non-numeric values were entered, the GUI would ignore it and perform the button action using the last valid input. (For example, if you deposit 5 dollars then try to deposit 'some string', you would end up depositing another 5 dollars.) There weren't any other issues that we were aware of at least. The only one would be there were some functions that weren't doing anything in the Menu class. We are fully aware of it, and we will get them fixed in iteration 3.

Commands needed to compile our code:

If you wish to run our code, please navigate to our development branch. There, you should find a file named 'script.sh'. Clone our development branch and run this bash script to automatically compile and run our bankapp program. Alternatively, you can navigate to that branch through this link: https://github.com/CSE237-SP24/project-ryan-eric-leon/blob/development/

