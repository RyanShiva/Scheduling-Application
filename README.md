# Scheduling Application
 JavaFX GUI-based scheduling desktop application.

## Purpose
This desktop application allows users to view appointment and customer data. It also generates reports and adds, updates, and deletes customer and appointment data.

## Author
Ryan Shiva
Contact: ryanshiva2@gmail.com

## Version
Scheduling Application 1.01
Released 12/2/2020

## Development Tools
IntelliJ IDEA Community Edition 2020.3.0
Java SE Development Kit 11.0.8
JavaFX-SDK-11.0.2

## Directions for Running the Application

### Login
Login with the username 'test' and password 'test'.

### Scheduling Application Menu
This is the main screen of the application. To view, add, update or delete customers, click the customers button. To view, add, update or delete appointments, click the appointments button. To view reports, click the reports button. Clicking exit will terminate the application.

### Customers
This screen allows the user to view and delete customers. The Customers table automatically displays all customers in the database. For adding and updating customers, the user is directed to separate screens. A customer must be selected in the tableview in order to access the update screen. Click Menu to return to the Scheduling Application Menu.

Delete a customer:
1. Select a customer in the tableview.
2. Click the Delete button to delete the selected customer.
3. Click OK on the confirmation alert to delete the selected customer. Any appointments associated with the customer are also deleted automatically.
4. The customer will disappear from the Customers Tableview, confirming that it has been deleted from the database.

### Add Customer
By clicking the Add button on the Customers screen, the user is directed to a blank Add Customer form.

Add a customer:
1. Fill in customer's information in the text boxes. Every field requires input. Text cannot exceed fifty characters in a single field.
2. Select the customer's country from the Country combo box.
3. Select the customer's first-level division from the First-Level Division combo box. The first level-division combo box is not populated until a country is selected.
4. Click the Save button to add the customer to the database. This will return the user to the Customers screen.
5. Click the Cancel button to return to the Customers screen without saving any data or modifying the database.

### Update Customer
By clicking the Update button on the Customers screen, the user is directed to the Update Customer form. 

Update a customer:
1. On the Customers screen, select a customer in the Tableview and click Update.
2. The fields are automatically filled with the customer's data. Make changes to the customer's data in the text fields and combo boxes.
3. Click the Save button to update the customer's data and return to the Customers screen.
4. Click the Cancel button to return to the Customers screen without saving any data or modifying the database.

### Appointments
This screen allows the user to view appointments with the option to filter by week and month. The Appointments table displays all appointments in the database by default. For adding and updating appointments, the user is directed to separate screens. Click Menu to return to the Scheduling Application Menu. To add, update, or delete appointments, follow the same procedure as for customers.

Filter appointments by month:
1. Select a date in the date picker.
2. Click the View By Month radio button.
3. The Appointments table will be updated to display all Appointments that are scheduled for the same month as the date selected in the date picker.
4. To view the appointments of another month, change the date in the date picker to a day with a different month. The Appointments table will be updated to display all appointments in the month of the new date in the datepicker.

Filter appointments by week:
1. Select a date in the date picker.
2. Click the View by Week radio button.
3. The Appointments table will be updated to display all Appointments that are scheduled for the same week as the date selected in the date picker. A week is defined as the span of seven days from the Monday on or before the selected date to the following Sunday.
4. To view the appointments of another week, change the date in the date picker to a day with a different week. The Appointments table will be updated to display all appointments in the week of the new date in the datepicker.

Note: as long as a date is selected in the datepicker, users may switch between radio buttons without changing the date and the Appointments table will be filtered accordingly.

### Reports
This screen allows the user to view reports on the appointment data in the database.

Number of Appointments by Type:
Each type of appointment is listed in the Type column. The table displays the number of appointments in the database that correspond to each type. If a type of appointment does not appear in the table, then there are zero appointments in the database of that type.

Number of Upcoming Appointments by Month:
The months of the year are listed in the Month column. The table displays the number of appointments in each month. This table only displays future appointments, so if an appointment start time has already passed, then it will not be counted in the number of appointments for the month for which it is scheduled (This is to prevent appointments on the same month but different years from being counted). The month count also excludes appointments scheduled for 12 months after the current month and beyond. For example, if the current date is July 16, 2020 then any appointments scheduled for July 2021 or later will not be counted.

Total Appointment Hours for the Next 10 Weeks:
This report satisfies the requirement to include "an additional report of [my] choice that is different from the two other required reports." The Week column displays 10 weeks in chronological order, starting from the week of the current date. Each week starts on Monday and ends on Sunday. The appointment hours are calculated by adding together the length of time of each appointment in the database for each week in the table. The Appt. Hours column displays the total hours and minutes in the format 'hh:mm'.

Contact Schedule:
The contact schedule is blank by default. To generate the schedule for a contact, select a contact in the combo box. All appointments associated with that contact will be displayed in the Contact Schedule table.
