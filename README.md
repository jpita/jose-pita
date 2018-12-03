# 1 - Test Cases / Test Plan for Monefy

This is a list of the things one should check when running a regression test suite.

This list is ordered by priority. In case we're short on time we should pay more attention to the top features then go down the list until we run out of time. We shouldn't deploy the app unless all the **smoke tests** are covered.

*The priority is based on the features that are more important for the company (where the company makes money) and the features more used by our users (this data can be fetched via metrics or talking with support/looking at reviews).*

## Group of smoke tests - this tests cannot be ignored when testing a new release

* Installation and Update - try both methods. Sometimes issues with cache and stored files arise when updating an app
  * on update, make sure the user's data is not deleted or changed
* Upgrade to Pro version - When you try to make an action that is exclusive to pro accounts you should get a screen to upgrade
  * Using a test credit card or a real one, make sure the transaction goes through and the user gets upgraded
* Synchronization - check that both Dropbox and Google Drive Sync are working
* Data Backup - check that both create and restore data features are working
* Review application - check that you are redirected to the store to leave a review
* Change language and currency - check that the default language/currency is the one selected on the phone (locale) and we can change it to whatever language/currency we provide. We should use a translation tool like [Loco](https://localise.biz) to have all our strings translated the same way throughout all the platforms we provide the app on.
  * Make sure all the apps have a default language to fallback to, otherwise the app can crash due to lack of strings to render
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

# 2 - Automated tests for Cocoin app (Android)

## Test Cases

Cocoin is a similar app to Monefy, so the tests would be similar.

* Login and sign up process
* Add, edit and delete expenses and incomes
* categories and accounts

I only had time to automate 3 tests:
(add link to the files on gh)
* [onBoardingAndPinCreationTest](https://github.com/jpita/jose-pita/blob/master/src/test/kotlin/appium/tests/cocoin/CocoinTests.kt#L17)  - this test goes through the onboarding process and creates a pin for the user
* [categoriesAreChangingTest](https://github.com/jpita/jose-pita/blob/master/src/test/kotlin/appium/tests/cocoin/CocoinTests.kt#L38) - this test checks if the categories of an expense change correctly
* [createAnEntryOnTheBooking](https://github.com/jpita/jose-pita/blob/master/src/test/kotlin/appium/tests/cocoin/CocoinTests.kt#L47) - this test checks if an expense is added correctly to the app

## Technologies used 

I used Java + Kotlin as languages, TestNG as a test runner with Gradle and Allure for the reporting.

Appium does the hardwork communicating and driving the phone.

I used the page object test design pattern to organize my tests.

In order to run the tests you need to have installed:
* [nodeJS](https://nodejs.org/en/download/)
* [npm](https://www.npmjs.com/get-npm)
* [Appium](http://appium.io/docs/en/about-appium/getting-started)
* [Appium doctor](https://www.npmjs.com/package/appium-doctor)
* [JDK](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html)
* [Android SDK](https://developer.android.com/studio/)
* [Gradle](https://gradle.org/install/)
* a real phone connected to the PC
  * The android version of the phone I used is 8, if you don't have a device with that version you need to change the version [HERE](https://github.com/jpita/jose-pita/blob/master/src/test/kotlin/appium/tests/BaseTest.kt#L33). You can also have an emulator running on you computer then the tests will run on the emulator.

Once you have everything installed you need to:

* clone the repo -  `git clone git@github.com:jpita/jose-pita.git`, and `cd` into it
* run `appium-doctor` to make sure everything is well installed. You should see something like this ![appium doctor](https://i0.wp.com/www.softwaretestingmaterial.com/wp-content/uploads/2017/09/Appium-With-NodeJs-9.png?ssl=1) 
* run `gradle build`
* if the tests don't run with the previous steps, do `gradle clean test`. If for some reason you get a `InvalidServerInstanceException` error, please start an appium server on a terminal by running `appium`. After trying to install the API playground you recommended I messed up my node installation and now appium can't be started programmatically.
* once the tests stop running, run `gradle allureReport`

* followed by `gradle allureServe`

After that there will be a URL shown on the terminal to a local server, all you need to do is open that URL on any browser and you'll see a nice report from Allure.

## Issues

* As you can see on videos [one](https://drive.google.com/file/d/1zKkd0kYi48wlp2OPbTBTSDjagILv5SYU/view?usp=sharing) and [two](https://drive.google.com/file/d/1j8KqoyVDv2xIJQ1Njq2bciymzIU1YSTu/view?usp=sharing), the app doesn't allow us to login to the main dashboard so it's pretty much useless. 
 I was able to do it with Appium because the numbers were still visible to the tool, but not to the human eye.

* I don't really understand what you mean by:
  * Outline the possibilities of automating proposed test cases on different levels, together with a short summary of pros and cons of each of them.

Do you mean automated tests of the UI and API?

If so, I totally agree. I'm a big supporter of the idea that we shouldn't automate everything on the UI. 

UI Tests are slow and expensive, we can do a lot through the API and DB to help our UI tests run a lot faster and we should have most of our testing effort in unit and regression tests.

On my current company our apps communicate with a mySql db and Firebase realtime, we have helper methods to communicate with both so we can change our test environments data to help our tests

## Improvements to my test

* Put log messages on every step (click, assert, send keys) so they show on Allure to guide us through the fails
* Add screenshots on fails
* Add tags to Allure like `login group` or `high priority` , etc...
* For some reason the report says 6 tests where there are only 3...
* Refactor and comment the code 
* Create a script to install everything and run the tests.
* Create a docker composer file that creates images with:
  * Zalenium
  * Jenkins
  * A java image to run the tests
  * An android image to avoid using devices or cloud providers


## Improvements to the app

* Everything :D

The app is really bad. 

The UI is horrible and the UX even worse. It should follow the platform's design guidelines.

* IDs on the app

It was very hard to find good ids to create good selectors. We should ask the developers to give us ids.

* Deep links and activities

We should have deeplinks or activities in the app, this way we can access any part of the app without going through the onboarding and login processes.

I tried finding the activity with `adb shell dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'
` and putting it on the desired capabilities but got a `Permission Denial: starting Intent` error.

# 3 - API Test

* I couldn't install the api playground mentioned in the test, [had some issues with sqlite3](https://gist.github.com/jpita/3a379a60f855a142adeca47c8f2bdcf0).

* I used [restful-booker](https://restful-booker.herokuapp.com/apidoc/index.html), an API playground I use during my workshops.
  * [Here](https://github.com/mwinteringham/restful-booker) you can find all the instructions to install and run it
  
## Tests Cases
This API is a service to create bookings for an hotel
* Get all the bookings
* [Create booking and check if it was created](https://github.com/jpita/jose-pita/blob/master/src/test/java/api/APITest.java#L29)
* [Edit the previously created booking](https://github.com/jpita/jose-pita/blob/master/src/test/java/api/APITest.java#L49)
* [Delete the previously created booking](https://github.com/jpita/jose-pita/blob/master/src/test/java/api/APITest.java#L41)

Notes:
* Most of the http status codes of this API are wrong in my point of view
  * we get a 200 when creating a new booking, should be 201
  * we get a 201 on the delete, should be a 200 or 204
  * There are some bugs left on purpose on the playground, regarding missing fields in the json and field format. Would be fun to play around with some of those but I didn't have the time.
  
  
# Feedback

This was by far the longest test I've ever done in my life.

That being sad, I had a lot of fun doing it because it helped me learn some new things and solve some issues I'd been struggling with in my own framework.

I hope this was more or less what you expected, can't wait to hear back from you.
  
