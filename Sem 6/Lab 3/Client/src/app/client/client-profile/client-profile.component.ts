import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {getUser, User} from '../../models/user.model';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {UserService} from '../../service/userService/user.service';
import {map} from 'rxjs/operators';
import {RegistrationService} from '../../service/registrationService/registration.service';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {
  userData: Observable<User>;

  constructor(private router: Router,
              private keycloakAngular: KeycloakService,
              private userService: UserService,
              private registrationService: RegistrationService) {
  }

  ngOnInit(): void {
    try {
      console.log(this.keycloakAngular.getKeycloakInstance().loadUserInfo());
      this.keycloakAngular.loadUserProfile(true).then(
        data => {
          console.log('here');
          const user = getUser(Number(data.id), data.email, data.firstName, data.lastName, 'CLIENT', '');
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
  }

}
