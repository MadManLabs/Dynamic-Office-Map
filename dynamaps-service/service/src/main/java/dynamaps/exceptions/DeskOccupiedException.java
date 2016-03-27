package dynamaps.exceptions;

/**
 * Created by caldea on 3/27/2016.
 */
public class DeskOccupiedException extends Exception {
    private String description;

    public DeskOccupiedException(String description) {
        description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
