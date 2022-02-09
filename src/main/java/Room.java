import java.util.Arrays;

public record Room(int number, BedType[] bedType) {

    @Override
    public String toString() {
        return "- room number " + number + ".\n- number of beds: " + bedType.length +
                ".\n- type of bed: " + Arrays.toString(bedType) + ".";
    }
}
