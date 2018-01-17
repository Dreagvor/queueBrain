import {Injectable, OnInit} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { QBSettings } from '../_properties/properties';
import {Observable} from "rxjs/Observable";
import {CookieService} from "ngx-cookie-service";

export interface QueuesStatusResponse {
    id: number;
    name: string;
    contacts: string;
    usingQueues: number[];
    managedQueues: number[];
}

export interface QueueDataResponse {
    id: number;
    name: string;
    admin: number;
    users: number[];
    currentUser: number
    startDate: string;
    loginedUserPosition: number;
}

export interface CreateQueueResponse {
    queueId: number;
}


@Injectable()
export class DashboardService implements OnInit {

    constructor(private http: HttpClient,
                private cookieService: CookieService) {
    }

    ngOnInit(): void {

    }

    getUserData(): Observable<QueuesStatusResponse> {
        return this.http.get<QueuesStatusResponse>(
            QBSettings.serverUrl + "/users/me",{withCredentials: true});
    }

    getQueue(queueId: number): Observable<QueueDataResponse> {
        return this.http.get<QueueDataResponse>(
            QBSettings.serverUrl + "/queues/" + queueId,{withCredentials: true});
    }

    createQueue(queueName: string): Observable<CreateQueueResponse> {
        let request = {
            name: queueName
        };
        return this.http.post<CreateQueueResponse>(
            QBSettings.serverUrl + "/queues/",request,{withCredentials: true});
    }

    enterQueue(queueId: number): Observable<any> {
        return this.http.post(
            QBSettings.serverUrl + "/queues/" + queueId + "/enter", {},{withCredentials: true});
    }

    pushQueue(queueId: number): Observable<any> {
        return this.http.put<CreateQueueResponse>(
            QBSettings.serverUrl + "/queues/" + queueId, {},{withCredentials: true});
    }
}