import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QBSettings } from '../_properties/properties';
import {Observable} from "rxjs/Observable";

interface UsersStatisticResponse {
    usersCount : number;
}

interface QueuesStatisticResponse {
    queuesCount : number;
}

@Injectable()
export class StatisticsService {
    constructor(private http: HttpClient) { }

    getUsersCount() : Observable<UsersStatisticResponse> {
        console.log("Loading data from " + QBSettings.serverUrl + "/users");
        return this.http.get<UsersStatisticResponse>(QBSettings.serverUrl + "/users");
    }

    getQueuesCount() : Observable<QueuesStatisticResponse> {
        console.log("Loading data from " + QBSettings.serverUrl + "/queues");
        return this.http.get<QueuesStatisticResponse>(QBSettings.serverUrl + "/queues");
    }
}