import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./landing/login/login.component";
import {LandingComponent} from "./landing/landing.component";
import {RegistrationComponent} from "./landing/registration/registration.component";
import {ReglinkComponent} from "./reglink/reglink.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {QueueComponent} from "./queue/queue.component";

const routes: Routes = [
    {
        path: 'auth', component: LandingComponent, children: [
        {path: 'login', component: LoginComponent},
        {path: 'registration', component: RegistrationComponent}
    ]
    },
    {path: 'regcomplete', component: ReglinkComponent},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'queue/:id', component: QueueComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
