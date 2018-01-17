import {Component, OnInit} from '@angular/core';
import { CookieService } from 'ngx-cookie-service'
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {DashboardService} from "../../_services/dashboard.service";

@Component({
    selector: "app-header",
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    loggedOut: boolean;
    userName: string;

    constructor(private cookieService: CookieService,
                private router: Router,
                private activeRoute: ActivatedRoute,
                private userService: DashboardService) {
        this.router.events.subscribe((evt) => {
            if (evt instanceof NavigationEnd) {
                console.log(this.cookieService.get('JSESSIONID'));
                this.loggedOut = !(this.cookieService.get('JSESSIONID'));
                if (!this.loggedOut) {
                    userService.getUserData().subscribe(data => {
                        console.log(data);
                        this.userName = data.name;
                    });
                }
            }
        });
    }

    ngOnInit(): void {
    }

    logout() {
        console.log("Logging out");
        this.cookieService.delete('JSESSIONID');
        this.router.navigate(["/auth/login"]).catch(e => {
            console.log(e);
        });
    }
}
