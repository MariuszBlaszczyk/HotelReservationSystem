import java.util.Scanner;

public enum BedType {
    SINGLE(1),
    DOUBLE(2),
    KING_SIZE(3);

    int value;

    BedType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static BedType fromValue(int userValue) {
        BedType[] values = values();
        for (BedType bedType : values) {
            if (bedType.getValue() == userValue)
                return bedType;
        }
        return BedType.SINGLE;
    }


    static void availableBedTypes() {
        System.out.println("Available bed types. Select a number.");
        for (BedType bedtype : BedType.values()) {
            System.out.println(bedtype.name() + " - choose: " + bedtype.getValue());
        }
    }



    @Override
    public String toString() {
        return "value: " + value;
    }
}
