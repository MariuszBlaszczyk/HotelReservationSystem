public enum Gender {
    MALE(1),
    FEMALE(2);

    final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static void chooseYourGender() {
        System.out.println("Choose your gender");
        for (Gender gender : Gender.values()) {
            System.out.println(gender.name() + " - choose " + gender.getValue() + ".");
        }
    }

    static Gender fromValue(int userValue) {
        Gender[] values = values();
        for (Gender gender : values) {
            if (gender.getValue() == userValue)
                return gender;
        }
        throw new WrongOptionException("Wrong option when selecting gender");
    }

}
