# base-project

Add description of what happened each iteration

# **Iteration 1**:

**Things completed in this iteration**:

We added features that allow users to manage their accounts similarly to a bank app. Users can view their account balance, deposit, and withdraw money. They can switch between accounts if they have multiple ones and transfer money between them. For users without an account, there is an option to create one. We've provided clear instructions on the different commands to use and how to use them. Additionally, we wrote a function that enables user data to persist after closing the program. Their data is stored in a file using the file processor command we learned in class. We also wrote unit tests for our functions and created a script in the command line for users to run our code.

**Things intended to be completed in the next iteration**:

Since we can save user data in a file, we plan to write a function that allows users to create a username and password and link their data exclusively to their accounts. This will make it easier for us to manage user accounts and make our code more similar to a real bank app, as every user will have a username and password to log in. After implementing this function, we will add a feature to display account information, such as the users' balance, after they successfully log in, mimicking a typical bank app.
We are also considering adding a transaction history feature to allow users to view a list of recent transactions with details like date, amount, and description.

**Issues**:

We are quite satisfied with our progress so far. For the functions we wrote, we created corresponding unit tests in the test folder to ensure they work as expected, which they did. We also simulated being users to ensure the instructions are clear and that users can navigate our app effectively. Also, the default ('root') account will be initialized to 0 balance each time the user runs the program, regardless of the accounts file. This is an intended feature to ensure the unit tests work properly.

**Commands needed to compile our code**:

Since README does not support direct file attachments, if you wish to run our code, please clone this development branch. Here, you should find a file named 'script.sh'. Run this script from terminal to automatically compile and run our bankapp program.
