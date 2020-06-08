import {Component, Input, OnInit} from '@angular/core';
import {Ride} from '../../models/ride.model';
import {BookingService} from '../../service/bookingService/booking.service';

@Component({
  selector: 'app-ride-driver-item',
  templateUrl: './ride-driver-item.component.html',
  styleUrls: ['./ride-driver-item.component.css']
})
export class RideDriverItemComponent implements OnInit {
  @Input() ride: Ride;

  constructor(private bookingService: BookingService) {
  }

  ngOnInit(): void {
  }

  updateRide(ride: Ride) {
    this.bookingService.updateBooking(ride.bookingId, 'COMPLETED');
  }
}
