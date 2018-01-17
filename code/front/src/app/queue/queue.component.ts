import {Component, OnInit} from '@angular/core';
import { CookieService } from 'ngx-cookie-service'
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {DashboardService} from "../_services/dashboard.service";

@Component({
    templateUrl: './queue.component.html',
    styleUrls: ['./queue.component.css']
})
export class QueueComponent implements OnInit {
    userId: number;
    queueId: number;
    queueLink: string;
    queueName : string;
    users : number[] = [];
    currentUser : number;
    author: boolean;
    admin: number;
    alreadyIn: boolean;

    constructor(private cookieService: CookieService,
                private router: Router,
                private activeRoute: ActivatedRoute,
                private dashboardService: DashboardService) {
    }

    ngOnInit(): void {
        this.queueId = +this.activeRoute.snapshot.paramMap.get('id');
        this.queueLink = "/queue/" + this.activeRoute.snapshot.paramMap.get('id');
        this.dashboardService.getQueue(this.queueId).subscribe(data => {
            console.log(data);
            this.queueName = data.name;
            this.users = data.users;
            this.currentUser = data.currentUser;
            this.admin = data.admin;
        }, error => {
            console.log(error);
        });
        this.dashboardService.getUserData().subscribe(data => {

            console.log(data);
            if (data.id == this.admin) {
                this.author = true;
            }
            console.log("hoh");
            console.log(data.id);
            console.log(this.users);
            this.alreadyIn = this.users.includes(data.id);
        });
    }

    enter() {
        console.log(this.queueId);
        this.dashboardService.enterQueue(this.queueId).subscribe(data => {
            this.ngOnInit();
        }, error => {
            alert("Something went wrong :(");
            console.log(error);
        })
    }

    push() {
        this.dashboardService.pushQueue(this.queueId).subscribe(data => {
            this.ngOnInit();
        }, error => {
            alert("Something went wrong :(");
            console.log(error);
        })
    }
}