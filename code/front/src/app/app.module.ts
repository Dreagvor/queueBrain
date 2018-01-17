import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from "@angular/forms";

import { NgxQRCodeModule } from '@techiediaries/ngx-qrcode';


import { AppComponent } from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {LoginComponent} from "./landing/login/login.component";
import {RegistrationComponent} from "./landing/registration/registration.component";
import {LandingComponent} from "./landing/landing.component";
import {HttpClientModule} from "@angular/common/http";
import {StatisticsService} from "./_services/statistics.service";
import {HeaderComponent} from "./layout/header/header.component";
import {FooterComponent} from "./layout/footer/footer.component";
import {AuthService} from "./_services/auth.service";
import {ReglinkComponent} from "./reglink/reglink.component";
import {CookieService} from "ngx-cookie-service";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {QueueComponent} from "./queue/queue.component";
import {DashboardService} from "./_services/dashboard.service";


@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,

        LandingComponent,
        LoginComponent,
        RegistrationComponent,
        ReglinkComponent,

        DashboardComponent,
        QueueComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        NgxQRCodeModule
    ],
    providers: [
        StatisticsService,
        AuthService,
        CookieService,
        DashboardService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
