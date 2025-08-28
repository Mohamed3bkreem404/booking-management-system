package com.BookingManagementSystem.Controller;


import com.BookingManagementSystem.Enum.BookingStatus;
import com.BookingManagementSystem.Model.DTO.BookingDTO;
import com.BookingManagementSystem.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

        @Autowired
        private BookingService bookingService;



        @GetMapping("/{id}")
        public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer id){
            return  ResponseEntity.ok(bookingService.getBookingById(id));
        }

        @GetMapping
        public ResponseEntity<Page<BookingDTO>> getAllBookings(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "5") int size,
                @RequestParam(defaultValue = "id") String sortBy){
            return ResponseEntity.ok(bookingService.getAllBookings(page,size,sortBy));
        }

        @GetMapping("/status/{status}")
        public ResponseEntity<List<BookingDTO>> getBookingByStatus(@PathVariable BookingStatus status){
            return ResponseEntity.ok(
                    bookingService.getBookingByStatus(status)
            );
        }

        @PostMapping
        public BookingDTO addBooking(@RequestBody @Valid BookingDTO bookingDTO){
                  return bookingService.createBooking(bookingDTO);
        }

        @PutMapping("/{id}")
        public ResponseEntity<BookingDTO> updateBooking(
                @Valid
                @PathVariable Integer id,
                @RequestBody BookingDTO bookingDTO) {
            return ResponseEntity.ok(bookingService.updateBooking(id, bookingDTO));
        }

        @DeleteMapping("/{id}")

        public ResponseEntity<Map<String,Object>> deleteBooking(@PathVariable Integer id) {
            bookingService.deleteBooking(id);
            Map<String , Object> response = new HashMap<>();
            response.put("message" , "Booking Deleted successfully");
            response.put("id" , id);
                return ResponseEntity.ok(response);
        }


}


