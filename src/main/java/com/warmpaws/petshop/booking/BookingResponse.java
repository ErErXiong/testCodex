package com.warmpaws.petshop.booking;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        String bookingNo,
        String message,
        LocalDateTime appointmentTime,
        LocalDateTime createdAt
) {
}
