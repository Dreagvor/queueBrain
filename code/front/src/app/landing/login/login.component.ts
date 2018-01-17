import {Component} from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import { CookieService } from 'ngx-cookie-service'
import {Router} from "@angular/router";

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
    email: string;
    password: string;

    constructor(private authService: AuthService,
                private cookieService: CookieService,
                private router: Router) {
    }

    doLogin() {
        this.authService.login(this.email, this.password).subscribe(data => {
            console.log(data);
            this.cookieService.set( 'JSESSIONID', data.sessionId );
            this.router.navigate(["/dashboard"]);
        }, error => {
            alert("Invalid credentials");
            console.log(error);
        })
    }

}
