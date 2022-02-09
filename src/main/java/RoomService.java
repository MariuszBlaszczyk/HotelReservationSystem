public class RoomService {

    private final RoomRepository roomRepository = new RoomRepository();

    public Room createNewRoom(int roomNumber, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypes.length; i++) {
            bedTypes[i] = BedType.chooseFromNumberValue(bedTypesOptions[i]);
        }

        return roomRepository.createNewRoom(roomNumber, bedTypes);
    }


}
