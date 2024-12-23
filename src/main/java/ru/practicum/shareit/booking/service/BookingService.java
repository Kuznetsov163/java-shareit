package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingUpdateStatusDto;
import ru.practicum.shareit.booking.enums.BookingState;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(BookingCreateDto booking);

    BookingDto updateStatus(BookingUpdateStatusDto bookingUpdateStatusDto);

    BookingDto getBooking(long bookingId, long userId);

    List<BookingDto> getBookingsByBookerAndState(long bookerId, BookingState state);

    List<BookingDto> getBookingsByOwnerAndState(long ownerId, BookingState state);
}
