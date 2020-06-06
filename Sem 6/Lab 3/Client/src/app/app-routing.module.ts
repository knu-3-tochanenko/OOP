import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AppAuthGuard} from './service/AppAuthGuard';
import {AppComponent} from './app.component';
import {ClientProfileComponent} from './client/client-profile/client-profile.component';


const routes: Routes = [
  {
    path: 'user_profile',
    canActivate: [AppAuthGuard],
    component: ClientProfileComponent,
    data: {roles: ['client']}
  },
  {
    path: '',
    canActivate: [AppAuthGuard],
    component: AppComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
