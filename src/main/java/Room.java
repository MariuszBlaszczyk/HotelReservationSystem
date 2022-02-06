import java.util.Arrays;

public class Room {

    private final int number;
    private final BedType[] bedType;

    public Room(int number, BedType[] bedType) {
        this.number = number;
        this.bedType = bedType;
    }

    @Override
    public String toString() {
        return "- room number " + number + ".\n- number of beds: " + bedType.length +
                ".\n- type of bed: " + Arrays.toString(bedType) + ".";
    }
}
