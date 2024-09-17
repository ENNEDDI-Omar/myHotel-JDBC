package Utils;

import Entities.Reservation;
import Exceptions.ReservationExeption;
import Service.RoomService;

public class ReservationValidation {
       private RoomService roomService;

       public ReservationValidation(RoomService roomService) {
           this.roomService = roomService;
       }

    public void reservationValidationChecking(Reservation reservation)
    {
         if (reservation.getStartDate() == null || reservation.getEndDate() == null)
         {
             throw new ReservationExeption("Reservation dates cannot be null");
         }
         if (reservation.getStartDate().isAfter(reservation.getEndDate()))
         {
             throw new ReservationExeption("Start date cannot be after end date");
         }
         if (reservation.getEndDate().isBefore(reservation.getStartDate()))
         {
             throw new ReservationExeption("End date cannot be before start date");
         }
         if (reservation.getRoom() == null || reservation.getRoom().getId() == 0)
         {
             throw new ReservationExeption("Room cannot be null");
         }
    }
}
