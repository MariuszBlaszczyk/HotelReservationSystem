import java.util.Objects;

public class Room {

    private int number;
    private BedType bedType;

    public Room(int number, BedType bedType) {
        this.number = number;
        this.bedType = bedType;
    }

    @Override
    public String toString() {
        return "- room number " + number + ".\n- type of bed: " + bedType.name() +".";
    }
}
