import {Component, Inject, OnInit} from '@angular/core';
import {Booking} from '../../models/booking.model';
import {BookingService} from '../../service/bookingService/booking.service';
import {Observable} from 'rxjs';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ClientProfileComponent} from '../../client/client-profile/client-profile.component';
import {User} from '../../models/user.model';
import {CarService} from '../../service/carService/car.service';

@Component({
  selector: 'app-booking-dispatcher-dialog',
  templateUrl: './booking-dispatcher-dialog.component.html',
  styleUrls: ['./booking-dispatcher-dialog.component.css']
})
export class BookingDispatcherDialogComponent implements OnInit {
  cars: Observable<Car[]>;

  constructor(public dialogRef: MatDialogRef<ClientProfileComponent>,
              @Inject(MAT_DIALOG_DATA) public booking: Booking,
              private bookingService: BookingService,
              private carService: CarService) {
  }

  ngOnInit(): void {
    this.cars = this.carService.getCarsByBooking(this.booking.id);
  }

  submit(car: Car) {
    this.booking.status = 'WAITING';
    this.updateBooking();
  }

  reject() {
    this.booking.status = 'REJECTED';
    this.updateBooking();
  }

  updateBooking() {
    this.bookingService.updateBooking(this.booking).subscribe(
      _ => this.dialogRef.close(),
      err => {
        console.log(err);
        alert(err.message);
      }
    );
  }
}
