// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import {KeycloakConfig} from 'keycloak-angular';

const keycloakConfig: KeycloakConfig = {
  url: 'http://localhost:8080/auth',
  realm: 'Autobase',
  clientId: 'Client',
  credentials: {
    secret: '752c60e5-3608-4531-8c98-d814ed1b643b',
  }
};

export const environment = {
  production: true,
  envName: 'local',
  keycloak: keycloakConfig,
  registerService: 'http://localhost:8180/registration',
  logoutService: 'http://localhost:8180/sso/logout'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
