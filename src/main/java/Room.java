import java.util.Objects;

public class Room {

    private final int number;
    private final int beds;

    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number && beds == room.beds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, beds);
    }

    @Override
    public String toString() {
        return "Room number " + number + ". Number of beds: " + beds;
    }
}
