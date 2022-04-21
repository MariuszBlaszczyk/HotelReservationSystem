package com.app.domain.reservation.dto;

import java.time.LocalDateTime;

public record ReservationDTO(long id, LocalDateTime from, LocalDateTime to, long roomId,
                             int roomNumber, long guestId, String guestName) {

    public long getId() {
        return id;
    }


}



