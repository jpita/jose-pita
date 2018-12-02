# Test Cases / Test Plan
This is a list of the things one should check when running a regression test suite.

This list is ordered by priority, in case we're short on time we should pay more attention to the top features then go down the list until we run out of time. We shouldn't deploy the app unless all the **smoke tests** are covered.

*The priority is based on the features that are more important for the company (where the company makes money) and the features more used by our users (this data can be fetched via metrics or talking with support/looking at reviews).*

## Group of smoke tests - this tests cannot be ignored when testing a new release

* Installation and Update - try both methods, sometimes issues with cache and stored files can arise when updating an app
  * on update, make sure the user's data is not deleted
* Upgrade to Pro version - When you try to make an action that is exclusive to pro accounts you should get a screen to upgrade
  * Using a test credit card or a real one, make sure the transaction goes through and the user gets upgraded
* Synchronization - check that both Dropbox and Google Drive Sync are working
* Data Backup - check that both create and restore data features are working
* Review application - check that you are redirected to the store to leave a review
* Change language and currency - check that the default language/currency is the one selected on the phone (locale) and we can change it to whatever language/currency we provide. We should use a translation tool like [Loco](https://localise.biz) to have all our strings translated the same way throughout all the platforms we provide the app on.
  * Make sure all the apps have a default language to fallback to, otherwise the app can crash due to lack of string to render
* Password Protection
  * check that you can put a passcode or use the fingerprint if your device supports it. 
  * check that you can change the frequency when the app requires the passcode.
  * check that you can change and remove the passcode and fingerprint
* Budget mode, carry over, and dark theme - check that you can enable and disable these options and the app changes accordingly.
  * Carry over - The money you didn’t spend from the previous date selected is shown as income on the next date’s balance
  * Budget mode - no matter how much income you add, the total amount to spend never increases above the monthly budget, except for the carry over
* Filters - check that you can filter by account (cash, payment card, etc) or by day/week/month/year or chose one day in the calendar 
  * If you filter by day and you have data from different days, you can swipe the dashboard left or right to see the details of each day
* Add expense - Check that you can add an expense by inputting an amount and choosing a category.
  * You can add an expense by using the “EXPENSE” button or tapping one of the categories in the dashboard
  * You can add categories when adding an expense.
  * You can change the account when adding an expense.
  * You can add a note to identify the expense
* Balance -  you can check your balance by tapping the balance button or the pie chart in the dashboard
  * You should see a list of categories and the total amount spent on each category
  * If you open a category you can see the details
  * Delete expense - You can delete an expense by taping the detail of the expense in the balance screen and taping the delete button 
* Add Income - you can add an income by tapping the “INCOME” button
  * You can add a note to identify the income
  * You can add categories when adding an income.
  * You can change the account when adding an income.
* After adding an expense or income, make sure the balance is updated
  * in the dashboard, the total income is shown in green and the total expenses is shown in red
  * The Balance is shown in green if positive, red if negative


## Rest of the regression suite

* Export to file - check that you can export your data to a file and open it on a text editor or a tool like google docs or excel
* Change First day of the week and first day of month
* Transfer money - check that you can transfer money from and to different accounts 
* You can add, edit and delete categories, both for expenses are incomes
  * you can edit the name, icon and if it is enabled or not
  * you can merge categories. When you do this, a backup will be created with the previous data that you can go back to
* You can add exchange rates by tapping currencies and selecting the currency you want to add
  * when you change the currency of an account, make sure the exchange rate calculation is made
* you can add, edit and delete accounts
  * you can chose the currency
  * go to the currency exchange rate screen
  * chose an initial balance and its date
  * you can chose to include it in the balance or not
  * you can chose the icon
  * you can merge accounts - a backup will be created that you can rollback to
  

## Non-functional tests

All of the previous tests are functional tests, they are directly related to the features of the app.

Non-functional tests are also very important, such as the performance of the app, usability testing and security testing (making sure our users data is saved and transmitted in a safe and encrypted way.

Although not as critical to run on every release due to their architectural nature, non-functional tests cannot be forgotten and should be run with some frequency.

In the case we have an app on which many users are doing the same action at the same time we should run some stress testing to make sure our architecture is scalable and never stops working no matter the usage.

Since we're talking about mobile apps, we should take into account the testing on different devices and platforms. Nowadays each mobile platform has several versions of OS's being used so we need to make sure the most widely used versions are supported by our app. 

In order to know the usage I would check some of these websites as example:
* https://data.apteligent.com/ios/
* https://developer.android.com/about/dashboards/
* http://gs.statcounter.com/android-version-market-share/mobile-tablet/worldwide
