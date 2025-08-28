package com.BookingManagementSystem.Service;

import com.BookingManagementSystem.Enum.BookingStatus;
import com.BookingManagementSystem.Model.Booking;
import com.BookingManagementSystem.Model.DTO.BookingDTO;
import com.BookingManagementSystem.Model.Patient;
import com.BookingManagementSystem.Repository.BookingRepo;
import com.BookingManagementSystem.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private NotificationService notificationService;



    @Cacheable(value = "bookings" , key = "#result.id")
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Patient patient = patientRepo.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

            Booking booking = Booking.builder()
                    .customer(patient)
                    .bookingStatus(BookingStatus.PENDING)
                    .checkOutDate(bookingDTO.getCheckOutDate())
                    .checkInDate(bookingDTO.getCheckInDate())
                    .build();
            notificationService.sendBookingConfirmationEmail(patient.getEmail());
            Booking savedBooking = bookingRepo.save(booking);
            return BookingDTO.fromDTO(savedBooking);
    }

    @Cacheable(value = "bookings" , key = "#id")
    public BookingDTO getBookingById(Integer id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow( () -> new RuntimeException("Booking with id " + id + "not found"));
        return BookingDTO.fromDTO(booking);

    }

    @Cacheable(value = "bookings", key = "'page_'+#page+'_'+#size+'_'+#sortBy")
    public Page<BookingDTO> getAllBookings(int page, int size, String sortBy) {
            Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
            Page<Booking> bookingPage = bookingRepo.findAll(pageable);
            return bookingPage.map(BookingDTO::fromDTO);
    }

    @CachePut(value = "bookings" , key = "#id")
    public BookingDTO updateBooking(Integer id, BookingDTO bookingDTO) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow( () -> new RuntimeException(("Booking with id" + id + "not found")));

        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setBookingStatus(bookingDTO.getBookingStatus());

        Booking updateBooking = bookingRepo.save(booking);
            return BookingDTO.fromDTO(updateBooking);
    }

    @CacheEvict(value = "bookings" , key = "#id")
    public void deleteBooking(Integer id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking with id" + id + "not found"));
        bookingRepo.delete(booking);
    }

    public List<BookingDTO> getBookingByStatus(BookingStatus status) {
            List<Booking> bookings = bookingRepo.findByBookingStatus(status);

            return  bookings.stream()
                    .map(BookingDTO::fromDTO)
                    .toList();
    }
}
