package ViewController;

import Model.ReportTuple;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/** This functional interface provides an abstract method to create reports.*/
@FunctionalInterface
public interface ReportsInterface {

    /**This is the generateReport method. It returns a report in the form of an observable list of ReportTuple objects.
     * @return The observable list that will appear in the Tableview of the report on the Reports screen.*/
    ObservableList<ReportTuple> generateReport() throws SQLException;
}
