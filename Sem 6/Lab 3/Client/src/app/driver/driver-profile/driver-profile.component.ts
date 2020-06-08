import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {getUser, User} from '../../models/user.model';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {UserService} from '../../service/userService/user.service';
import {RegistrationService} from '../../service/registrationService/registration.service';
import {map} from 'rxjs/operators';
import {Ride} from '../../models/ride.model';
import {RideService} from '../../ride.service';
import {MatDialog} from '@angular/material/dialog';
import {CarDialogComponent} from '../car-dialog/car-dialog.component';

@Component({
  selector: 'app-driver-profile',
  templateUrl: './driver-profile.component.html',
  styleUrls: ['./driver-profile.component.css']
})
export class DriverProfileComponent implements OnInit {
  userData: Observable<User>;
  rides: Observable<Ride[]>;

  constructor(private router: Router,
              private keycloakAngular: KeycloakService,
              private userService: UserService,
              private registrationService: RegistrationService,
              private rideService: RideService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    try {
      console.log(this.keycloakAngular.getKeycloakInstance().loadUserInfo());
      this.keycloakAngular.loadUserProfile(true).then(
        data => {
          const user = getUser(Number(data.id), data.email,
            data.firstName, data.lastName,
            'CLIENT', '',
            null);
          this.userData = this.registrationService.registerUser(user).pipe(
            map(_ => {
                this.userService.setCurrentUser(user);
                return user;
              },
              err => {
                console.log(err);
                alert(err.message);
              }),
          );
        },
        reason => {
          console.log(reason);
        }
      );
    } catch (e) {
      console.log('Failed to load user details');
    }

    this.loadBookings();
  }

  loadBookings() {
    const user = this.userService.getCurrentUser();
    if (user.car != null) {
      this.rides = this.rideService.getRidesByCar(user.car.id);
    }
  }


  setServiceable(serviceable: boolean) {
    const user = this.userService.getCurrentUser();
    user.car.serviceable = serviceable;
    user.car.lastInspection = Date.now().toString();
    this.userService.updateUser(user);
  }

  addCar() {
    const dialogRef = this.dialog.open(
      CarDialogComponent,
      {
        width: '30em',
        data: this.userService.getCurrentUser()
      }
    );

    dialogRef.afterClosed().subscribe(
      _ => this.userData = this.registrationService.registerUser(this.userService.getCurrentUser())
    );
  }
}
