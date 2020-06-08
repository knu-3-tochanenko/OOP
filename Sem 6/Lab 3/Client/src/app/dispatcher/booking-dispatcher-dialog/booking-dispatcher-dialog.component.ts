import {Component, OnInit} from '@angular/core';
import {Booking} from '../../models/booking.model';

@Component({
  selector: 'app-booking-dispatcher-dialog',
  templateUrl: './booking-dispatcher-dialog.component.html',
  styleUrls: ['./booking-dispatcher-dialog.component.css']
})
export class BookingDispatcherDialogComponent implements OnInit {
  booking: Booking;

  constructor() {
  }

  ngOnInit(): void {
  }

  submit() {

  }

  reject() {

  }
}
