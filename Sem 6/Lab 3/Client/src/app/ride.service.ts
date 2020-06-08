import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Ride} from './models/ride.model';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RideService {

  constructor(private http: HttpClient) {
  }

  addRide(ride: Ride): Observable<Ride> {
    return this.http.post<Ride>(environment.rideService, ride);
  }

  getRidesByCar(carId: number): Observable<Ride[]> {
    return this.http.get<Ride[]>(environment.rideService + '/' + carId);
  }
}
