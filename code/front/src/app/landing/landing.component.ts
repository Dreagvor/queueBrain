import { Component, OnInit } from '@angular/core';
import {StatisticsService} from "../_services/statistics.service";
// import { Location } from '@angular/common';

@Component({
    templateUrl: './landing.component.html',
    styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
    usersCount: number;
    queuesCount: number;

    constructor(private userService: StatisticsService) {

    }

    ngOnInit() {
        this.userService.getUsersCount().subscribe(response => {
            this.usersCount = response.usersCount
        });

        this.userService.getQueuesCount().subscribe(response => {
            this.queuesCount = response.queuesCount
        });
    }

}
