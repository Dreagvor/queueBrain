import { Component, OnInit } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {DashboardService, QueueDataResponse} from "../_services/dashboard.service";

@Component({
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    managedQueues: QueueDataResponse[] = [];
    usedQueues: QueueDataResponse[] = [];
    userId: number;
    qName: string;

    constructor(private cookieService: CookieService,
                private router: Router,
                private dashboardService: DashboardService) {

    }

    ngOnInit() {
        // TODO: dirty, sooo dirty
        if (!(this.cookieService.get('JSESSIONID'))) {
            alert("Access denied");
            this.router.navigate(["/auth/login"]);
        }

        this.dashboardService.getUserData().subscribe(data => {
            this.userId = data.id;
            let managedQueues = data.managedQueues;
            managedQueues.forEach(element => {
                console.log(element);
                this.dashboardService.getQueue(element).subscribe(data=> {
                    data.id = element;
                    console.log(data);
                    this.managedQueues.push(data);
                })
            });

            let usingQueues = data.usingQueues;
            usingQueues.forEach(element => {
                console.log(element);
                this.dashboardService.getQueue(element).subscribe(data=> {
                    data.id = element;
                    data.loginedUserPosition = data.users.findIndex(e => this.userId == e);
                    this.usedQueues.push(data);
                })
            });

        }, error => {
            console.log(error);
        })
    }

    createQueue() {
        console.log(this.qName);
        this.dashboardService.createQueue(this.qName).subscribe(data => {
            console.log(data);
            this.dashboardService.getQueue(data.queueId).subscribe(data=> {
                console.log(data);
                this.managedQueues.push(data);
            })
        }, error => {
            alert("Can not create queue :(");
            console.log(error);
        })
    }

}
