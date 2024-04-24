# **Iteration 1**:

**Things completed in this iteration**:

We added features that allow users to manage their accounts similarly to a bank app. Users can deposit and withdraw money. They can switch between accounts if they have multiple ones and transfer money between them. For users without an account, there is an option to create one. We've provided clear instructions on the different commands to use and how to use them. Additionally, we wrote a function that enables user data to persist after closing the program. Their data is stored in a file using the file processor command we learned in class. We also wrote unit tests for our functions and created a script in the command line for users to run our code.

**Things intended to be completed in the next iteration**:

Since we can save user data in a file, we plan to write a function that allows users to create a username and password and link their data exclusively to their accounts. This will make it easier for us to manage user accounts and make our code more similar to a real bank app, as every user will have a username and password to log in. After implementing this function, we will add a feature to display account information, such as the users' balance, after they successfully log in, mimicking a typical bank app.
We are also considering adding a transaction history feature to allow users to view a list of recent transactions with details like date, amount, and description.

**Issues**:

We are quite satisfied with our progress so far. For the functions we wrote, we created corresponding unit tests in the test folder to ensure they work as expected, which they did. We also simulated being users to ensure the instructions are clear and that users can navigate our app effectively. Also, the default ('root') account will be initialized to 0 balance each time the user runs the program, regardless of the accounts file. This is an intended feature to ensure the unit tests work properly. The new account's name must be a single word.

**Commands needed to compile our code**:

If you wish to run our code, please clone this development branch. Here, you should find a file named 'script.sh'. Run this bash script to automatically compile and run our bankapp program.



# **Iteration 2**:

**Things completed in this iteration**:

> Based on the feedback we received, we noted that some methods in our menu were too lengthy. We also received suggestions to improve user instructions. With these points in mind, we originally decided to implement a GUI to eliminate the need for extensive while loops and wordy instruction in our code. **This was against the philosophy of this project, thus we canceled this idea.** Branches named 'menu-gui-*' are NOT merged into the development branch.

To address the suggestions we received, we first tried to refactor our Menu class by splitting it into a few classes: Menu, AccountManagement, UserInputValidity, ProcessTransaction. As someone suggested, to mimic a realistic bank app, we limit each single transaction to less than $1,000,000. To make our menu more intuitive, we implemented a command to display the balance of the current account as well as a feature to automatically display the balance upon switching accounts. The user can see a recent transaction history via command. We also wrote unit tests for the classes. Note that Menu & UserInputValidity are two classes only used for getting and processing user input via the command line. Thus, we do not have tests for these two.

**Things intended to be completed in the next iteration**:

We may have to improve our unit tests for Iteration 3. We are unsure about the code cleanness of the refactored Menu class, so we will take actions according to the feedback we receive from this iteration. Ideas we have for some new features include allowing user to create password protected accounts and allowing them change their account password.

**Issues**:

Our design of the bank app ensures the default ('root') account will be initialized to 0 balance each time the user runs the program, regardless of the accounts file. The new account's name must be one string (i.e. no spaces in between, you can use underscore or dash if you like).

**Commands needed to compile our code**:

If you wish to run our code, please clone this development branch. Here, you should find a file named 'script.sh'. Run this bash script to automatically compile and run our bankapp program.



# **Iteration 3**:

**Things completed in this iteration**:

We deleted some branches that are no longer in use to clean up our repo. We tried to refactor our Menu class even further by introducing 2 new classes: ProcessUserDisplay and ProcessUserInput (replacing the previous UserInputValidity class). Based on suggestions we received, we created more unit tests overall to achive better test coverage. The user can create accounts protected by password now. If they want to switch to an account, they need to give the correct password. They can also change the password of the current account, as long as they input the correct old password. The password, just like the account balance, is able to persist after our Bank App closes. Note that our Menu class is only used for command line interactions. Thus, we do not have tests for it.

**Issues**:

Our design of the bank app ensures the default ('root') account will be initialized to 0 balance and empty password each time the user runs the program, regardless of the accounts file. The passwords are not hashed when stored, so anyone with access to the 'accounts.txt' file can see all the plain text passwords. We think this is acceptable for the sake of this project. The new account's name must be one string (i.e. no spaces in between, you can use underscore or dash if you like). This restriction also applies to the password and the command option. This is why there is an underscore in the 'change_password' option.

**Commands needed to compile our code**:

If you wish to run our code, please clone this development branch. Here, you should find a file named 'script.sh'. Run this bash script to automatically compile and run our bankapp program.
