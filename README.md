Description
----
This project was made to fulfill the request of Kiva's Assessment.

The request was:

* Using Java, Kotlin, PHP or Python, implement a Sudoku puzzle validator that meets the requirements and evaluation criteria listed below.

* Requirements
  * Implement a command-line Sudoku solution validator.
  * As an input, the validator must accept a CSV file representing 9 rows and 9 columns
  * The validator must output the result “valid” if the input CSV represents a correct Sudoku solution, or “invalid” if the input CSV does not represent a correct Sudoku solution. The validator may also output an appropriate error message.
    * A valid solution must:
       * Contain every number from 1 to 9 exactly once in each row.
       * Contain every number from 1 to 9 exactly once in each column.
       * Contain every number from 1 to 9 exactly once in each of nine 3x3 subgrids
  * Mention use of any special libraries and provide clear instructions to run the code.


  **Java**
  ----
 This is how the tool works:

Run the .jar file compiled with java -jar and pass the .csv file path as an argument with the pattern of --file=/path.csv

Example: 

      java -jar sudoku-validator-1.0-SNAPSHOT-all.jar --file=/Users/yarielinfantereyes/Downloads/example_valid.csv

  The program will output "Valid" if the file provided is a sudoku valid one, or "Invalid" if it is not.


Program Execution
----
System prerequisite:
- Java 11
- Gradle 7



Compile source code
----

If you want to compile the source code type :

        gradle clean shadowJar
