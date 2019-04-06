/**
 * This meets the requirement of creating a custom exception.
 * This meets the requirements of polymorphism by overriding the toString() method.
 *
 * Properties:
 * default message: The message to include in the exception.
 *
 * Methods:
 * public String toString(): The message to output.
 */

package sample;

public class IllegalItemException extends Throwable {

    String message = "";

    public IllegalItemException() {
        this("");
    }

    public IllegalItemException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "This item is illegal. Extra info: " + message;
    }
}
