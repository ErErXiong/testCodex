package com.warmpaws.petshop.booking.service;

import com.warmpaws.petshop.booking.BookingRequest;
import com.warmpaws.petshop.booking.BookingResponse;
import com.warmpaws.petshop.booking.entity.Booking;
import com.warmpaws.petshop.booking.mapper.BookingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BookingService {

    private final BookingMapper bookingMapper;

    public BookingService(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    @Transactional
    public BookingResponse create(BookingRequest request) {
        LocalDateTime now = LocalDateTime.now();
        Booking booking = new Booking();
        booking.setBookingNo(createBookingNo(now));
        booking.setCustomerName(request.name());
        booking.setPhone(request.phone());
        booking.setPetType(request.petType());
        booking.setServiceName(request.service());
        booking.setAppointmentTime(request.appointmentTime());
        booking.setNote(request.note());
        booking.setStatus("PENDING");
        booking.setCreatedAt(now);

        bookingMapper.insert(booking);

        return new BookingResponse(
                booking.getId(),
                booking.getBookingNo(),
                "预约已提交，门店会尽快联系确认。",
                booking.getAppointmentTime(),
                booking.getCreatedAt()
        );
    }

    private String createBookingNo(LocalDateTime now) {
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int suffix = Math.abs((int) (System.nanoTime() % 10000));
        return "WP-" + date + "-" + String.format("%04d", suffix);
    }
}
