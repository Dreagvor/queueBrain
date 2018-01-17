import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute } from "@angular/router";
import {AuthService} from "../_services/auth.service";
import { CookieService } from 'ngx-cookie-service'

@Component({
    templateUrl: './reglink.component.html',
})
export class ReglinkComponent implements OnInit {
    constructor(private router: Router,
                private authService: AuthService,
                private activatedRoute: ActivatedRoute,
                private cookieService: CookieService) {
    }

    ngOnInit(): void {
        // 1) get cookie
        // 2) navigate to home
        // 3) on error, alert and navigate to login
        this.activatedRoute.queryParams.subscribe(params => {
            this.authService.completeRegistration(params["reglink"]).subscribe(data => {
                this.cookieService.set( 'JSESSIONID', data.sessionId );
                this.router.navigate(["/dashboard"]);
            }, error => {
                alert("Oo, error");
                console.log(error);
                this.router.navigate(["/auth/login"]);
            })
        });
    }
}
